package org.example.service;

import org.example.model.Cliente;

import java.time.LocalDate;

public interface ClienteService {
    void agregar(Long id, String nombre, int precio, LocalDate fechaRegistro);
    void  eliminar(String nombre);
    void listar();
    Cliente getByName(String nombre);
    void  modificar(String nombreModificar,String nombre,int precio);

}
