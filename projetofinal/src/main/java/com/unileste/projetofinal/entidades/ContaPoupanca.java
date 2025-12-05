package com.unileste.projetofinal.entidades;

public class ContaPoupanca extends Conta {

    private static final long serialVersionUID = 1L;

    private double rendimento;

    public ContaPoupanca(String numero, Cliente cliente, double rendimento) {
        super(numero, cliente);
        this.rendimento = rendimento;
    }

    public double getRendimento() { return rendimento; }

    @Override
    public String toString() {
        return "CP " + numero + " | Saldo: R$ " + saldo + " | Rendimento: " + rendimento;
    }
}
