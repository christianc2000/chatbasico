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

public class socketEventos extends EventObject {

    private String nombreUsuario;
    private Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    public socketEventos(Object source, String nombreUsuario, Socket socket) throws IOException {
        super(source);
        this.nombreUsuario = nombreUsuario;
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Socket getSocket() {
        return socket;
    }
}
