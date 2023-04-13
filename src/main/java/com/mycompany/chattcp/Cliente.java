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

public class Cliente {

     private String direccionServidor;
    private int puerto;
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    public Cliente(String direccionServidor, int puerto) {
        this.direccionServidor = direccionServidor;
        this.puerto = puerto;
    }

    public void iniciar() {
        try {
            socket = new Socket(direccionServidor, puerto);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader lectorConsola = new BufferedReader(new InputStreamReader(System.in));
            String mensajeConsola;
            while ((mensajeConsola = lectorConsola.readLine()) != null) {
                salida.println(mensajeConsola);
                String mensajeServidor = entrada.readLine();
                System.out.println(mensajeServidor);
            }
            System.out.println("se sale del while");
            socket.close();
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("localhost", 5000);
        cliente.iniciar();
    }
}
