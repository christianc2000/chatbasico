/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chattcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Christian
 */
public class HiloMensajes extends Thread {

    public Socket clientSocket;
    public BufferedReader reader;
    public PrintWriter writer;
    protected EventListenerList mensajeeventos = new EventListenerList();

    public HiloMensajes(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
        //clientes.add(writer); //USAR EL EVENTO (Agregar el PrintWriter del cliente a la lista de clientes)
    }

    @Override
    public void run() {

        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Error al recibir mensaje del servidor: " + e.getMessage());
        }

        /*  private void broadcast(String message) {
            for (PrintWriter client : clientes) {
                client.println(message); // Enviar el mensaje a todos los clientes
            }
        }*/
    }

    public void addMyEventListener(SocketInterface listener) {
        mensajeeventos.add(SocketInterface.class, listener);
    }

    public void removeMyEventListener(SocketInterface listener) {
        mensajeeventos.remove(SocketInterface.class, listener);
    }

    void fireMyEvent(mensajeEventos evt) {
        Object[] listeners = mensajeeventos.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == SocketInterface.class) {
                ((SocketInterface) listeners[i + 1]).onMessageReceived(evt);
            }
        }
    }
}
