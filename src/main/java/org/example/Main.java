package org.example;

import org.example.controller.ClienteController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //agregar
//        ProductoRepositoryImpl productoRepository = new ProductoRepositoryImpl();
//
//        Cliente producto = new Cliente();
//        producto.setNombre("fresa");
//        producto.setPrecio(6000);
//        producto.setFechaRegistro(LocalDate.now());
//        productoRepository.save(producto);
        boolean bandera = true;
        ClienteController clienteController = new ClienteController();

        while (bandera) {
            switch (JOptionPane.showInputDialog("1.agregarCliente\n2.eliminarCliente\n3.modificar\n4.listar")) {
                case "1":
                    clienteController.agregar();
                    break;
                case "2":
                    clienteController.eliminar();
                    break;
                case "3":
                   clienteController.modificar();
                    break;
                case "4":
                    clienteController.listar();

                    break;
                case "5":

                    break;
                case "6":


            }
        }
    }
}
