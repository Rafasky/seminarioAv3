package com.unileste.projetofinal.entidades;

import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    public ContaCorrente(String numero, Cliente proprietario, double limiteChequeEspecial) {
        super(numero, proprietario);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("valor invalido");
            return;
        }
        saldo += valor;
        adicionarTransacao("Deposito de R$:" + valor);
    }

    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            System.out.println("valor invalido");
            return;
        }
        if (getSaldo() + limiteChequeEspecial < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente, mesmo com limite especial!");
        }
        saldo -= valor;
        adicionarTransacao("Saque de R$:" + valor);
    }

    @Override
    public void transferir(Conta destino, double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("valor invalido");
        }
        if (getSaldo() + limiteChequeEspecial < valor) {
            throw new SaldoInsuficienteException("saldo insuficiente");
        }
        // debita (considera limite ao sacar)
        this.sacar(valor);
        destino.depositar(valor);
        adicionarTransacao("Transferencia de R$:" + valor + " depositado na conta " + destino.getNumero());
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "numero=" + getNumero() +
                ", saldo=" + saldo +
                ", limiteChequeEspecial=" + limiteChequeEspecial +
                '}';
    }
}
