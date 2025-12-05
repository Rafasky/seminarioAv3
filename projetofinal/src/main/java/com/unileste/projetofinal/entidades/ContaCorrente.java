package com.unileste.projetofinal.entidades;

public class ContaCorrente extends Conta {

    private static final long serialVersionUID = 1L;

    private double limite;

    public ContaCorrente(String numero, Cliente cliente, double limite) {
        super(numero, cliente);
        this.limite = limite;
    }

    public double getLimite() { return limite; }

    @Override
    public String toString() {
        return "CC " + numero + " | Saldo: R$ " + saldo + " | Limite: " + limite;
    }
}
