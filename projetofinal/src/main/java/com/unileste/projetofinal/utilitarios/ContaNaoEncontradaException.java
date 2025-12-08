package com.unileste.projetofinal.utilitarios;

public class ContaNaoEncontradaException extends Exception {

    public ContaNaoEncontradaException(){
        super("Conta nao encontrada");
    }

    public ContaNaoEncontradaException(String s){
        super(s);
    }
}
