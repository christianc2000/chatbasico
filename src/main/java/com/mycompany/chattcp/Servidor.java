/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chattcp;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian
 */
public class Servidor implements SocketInterface {

    int port;
    public Map<String, Socket> Clientes = new HashMap<String, Socket>();
    private ArrayList<PrintWriter> clientesConectados = new ArrayList<>();
ServerSocket servidor;
    public Servidor(int port) {
        this.port = port;
    }

    public void iniciar() {

        try {
             servidor = new ServerSocket(this.port);
            System.out.println("Servidor iniciado en el puerto: " + this.port);
            HiloConexiones hc = new HiloConexiones(servidor);
           
            hc.addMyEventListener(this);
             hc.start();//correr el hilo conexiones

          //  System.out.println("fuera del hilo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nuevoClienteConectado(socketEventos evento) {
        Clientes.put(evento.getNombreUsuario(), evento.getSocket());
        System.out.println(evento.getNombreUsuario() + " conectado");
        HiloMensajes hmensaje;
        //System.out.println("son: " + Clientes.size());
        clientesConectados.add(evento.writer);
        try {
            hmensaje = new HiloMensajes(evento.getSocket());
            hmensaje.addMyEventListener(this);
            hmensaje.start();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessageReceived(mensajeEventos evento) {
        System.out.println("mensaje: " + evento.getMensaje());

        for (PrintWriter writer : clientesConectados) {
            writer.println(evento.getMensaje());
            writer.flush();
        }

    }

}
