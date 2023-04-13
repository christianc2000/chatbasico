/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.chattcp;

/**
 *
 * @author Christian
 */
public class ChatTCP {

    public static void main(String[] args) {
        Servidor server=new Servidor(5000);
        server.iniciar();
    }
}
