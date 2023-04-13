/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chattcp;

/**
 *
 * @author Christian
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;

public class HiloConexiones extends Thread {

    ServerSocket servidor;
    protected EventListenerList socketeventos = new EventListenerList();

    public HiloConexiones(ServerSocket servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {
        int key = 0;
        while (true) {
            try {
                key++;
                Socket clientsocket = servidor.accept();
                // Crear un evento y activarlo para notificar a los oyentes registrados
                socketEventos evento = new socketEventos(this, "Cliente" + key, clientsocket);
                this.fireMyEvent(evento);
               

                //System.out.println("Cliente conectado desde " + clientsocket.getInetAddress().getHostAddress());
            } catch (IOException ex) {
                Logger.getLogger(HiloConexiones.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void addMyEventListener(SocketInterface listener) {
        socketeventos.add(SocketInterface.class, listener);
    }

    public void removeMyEventListener(SocketInterface listener) {
        socketeventos.remove(SocketInterface.class, listener);
    }

    void fireMyEvent(socketEventos evt) {
        Object[] listeners = socketeventos.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == SocketInterface.class) {
                ((SocketInterface) listeners[i + 1]).nuevoClienteConectado(evt);
            }
        }
    }
}
