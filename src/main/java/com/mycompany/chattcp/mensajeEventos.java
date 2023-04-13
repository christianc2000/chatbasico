/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chattcp;

import java.util.EventObject;

/**
 *
 * @author Christian
 */
public class mensajeEventos extends EventObject{
        String mensaje;

    public mensajeEventos(String mensaje, Object source) {
        super(source);
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
        
}
