package com.mycompany.appprojetofinal.utilitarios;

public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente");
    }

    public SaldoInsuficienteException(String s) {
        super(s);
    }
}
