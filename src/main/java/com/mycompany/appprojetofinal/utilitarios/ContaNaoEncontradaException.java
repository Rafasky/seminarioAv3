/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appprojetofinal.utilitarios;

/**
 *
 * @author alexandre.rafaschi
 */
public class ContaNaoEncontradaException extends Exception {
    public ContaNaoEncontradaException(){
        System.out.println("Conta nao encontrada");
    }
    public ContaNaoEncontradaException(String s){
        super(s);
    }
    
}
