package org.example.service.serviceImpl;

import org.example.ConexionDB;
import org.example.model.Cliente;
import org.example.service.ClienteService;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteServiceImpl implements ClienteService {
    List<Cliente> clientes = new ArrayList<>();

    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance();
    }

    @Override
    public void agregar(Long id, String nombre, int precio, LocalDate fechaRegistro) {
        clientes.add(new Cliente(id, nombre, precio, fechaRegistro));
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("""
            INSERT INTO Producto(nombre, precio, date_register) VALUES (?, ?, ?)""")) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2,precio);
            java.sql.Date fechaRegistroSql = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(3, fechaRegistroSql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(String nombre) {
        Iterator<Cliente> iterator = clientes.iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente != null && cliente.getNombre().equals(nombre)) {
                iterator.remove();
                try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Producto WHERE nombre=?")) {
                    preparedStatement.setString(1, nombre);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private Cliente createProduct(ResultSet resultSet) throws SQLException {
        Cliente producto = new Cliente();
        producto.setId(resultSet.getLong("id"));
        producto.setNombre(resultSet.getString("nombre"));
        producto.setPrecio(resultSet.getInt("precio"));
        producto.setFechaRegistro(resultSet.getDate("date_register").toLocalDate());
        return producto;
    }

    @Override
    public Cliente getByName(String nombre) {
        Cliente producto=null;
        try (PreparedStatement preparedStatement=getConnection().prepareStatement(" SELECT  * FROM  Producto where nombre=?")) {
            preparedStatement.setString(1,nombre);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                producto=createProduct(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void modificar(String nombreModificar, String nombre, int precio) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getNombre().equals(nombreModificar)) {
                cliente.setNombre(nombre);
                cliente.setPrecio(precio);

                try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                        "UPDATE Producto SET nombre=?, precio=? WHERE nombre=?")) {
                    preparedStatement.setString(1, nombre);
                    preparedStatement.setInt(2, precio);
                    preparedStatement.setString(3, nombreModificar);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Override
    public void listar() {
        clientes.stream().forEach(cliente -> {
            if (cliente != null) {
                JOptionPane.showMessageDialog(null,"nombre: "+cliente.getNombre()+" precio: "+cliente.getPrecio());
            }
        });
    }



}
