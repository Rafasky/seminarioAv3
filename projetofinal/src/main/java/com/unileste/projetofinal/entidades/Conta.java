package com.unileste.projetofinal.entidades;

import java.io.Serializable;

public abstract class Conta implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(String numero, Cliente cliente) {
        if (numero == null || numero.isBlank())
            throw new IllegalArgumentException("Numero inválido");
        if (cliente == null)
            throw new IllegalArgumentException("Cliente inválido");

        this.numero = numero;
        this.cliente = cliente;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public String getNumero() { return numero; }
    public double getSaldo() { return saldo; }
    public Cliente getCliente() { return cliente; }

    @Override
    public String toString() {
        return numero + " | Saldo: R$ " + saldo;
    }
}
