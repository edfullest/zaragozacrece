/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import java.util.Date;

/**
 *
 * @author Lalo Serna
 */
public class Carta {
    
    private String fechaEntrada;
    private String carta;
    private String carta2;
    
    public Carta(String fechaEntrada,String carta){
        this.fechaEntrada = fechaEntrada; //
        this.carta = carta;
    }

    /**
     * @return the fechaEntrada
     */
    public String getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * @param fechaEntrada the fechaEntrada to set
     */
    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * @return the carta
     */
    public String getCarta() {
        return carta;
    }

    /**
     * @param carta the carta to set
     */
    public void setCarta(String carta) {
        this.carta = carta;
    }
    
}
