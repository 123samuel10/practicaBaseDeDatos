package org.example.controller;

import org.example.model.Cliente;
import org.example.service.serviceImpl.ClienteServiceImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClienteController {
    ClienteServiceImpl clienteService=new ClienteServiceImpl();

    public void agregar(){

        Long id= Long.valueOf(JOptionPane.showInputDialog("ingrese el id"));
        String nombre=JOptionPane.showInputDialog("ingrese el nombre");
        int precio= Integer.parseInt(JOptionPane.showInputDialog("ingrese el precio"));
        LocalDate localDate=LocalDate.now();
        clienteService.agregar(id,nombre,precio,localDate);
    }

    public void eliminar(){
        String nombreEliminar=JOptionPane.showInputDialog("ingrese el nombre para eliminar");
        Cliente cliente=clienteService.getByName(nombreEliminar);
        clienteService.eliminar(cliente.getNombre());
    }
    public  void listar(){
        clienteService.listar();
    }
    public void modificar() {
        String nombreModificar = JOptionPane.showInputDialog("Ingrese el nombre del cliente que desea modificar:");
        String nombreNuevo = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
        int precioNuevo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo precio:"));

        clienteService.modificar(nombreModificar, nombreNuevo, precioNuevo);
    }
}
