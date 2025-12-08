package com.unileste.projetofinal.entidades;

import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

public class ContaPoupanca extends Conta {
    private final double taxaRendimentoMensal;

    public ContaPoupanca(String numero, Cliente proprietario) {
        super(numero, proprietario);
        this.taxaRendimentoMensal = 0.5;
    }

    // opcional: construtor permitindo especificar taxa
    public ContaPoupanca(String numero, Cliente proprietario, double taxaRendimentoMensal) {
        super(numero, proprietario);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
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
        if (getSaldo() < valor) {
            throw new SaldoInsuficienteException("saldo insuficiente");
        }
        saldo -= valor;
        adicionarTransacao("Saque de R$:" + valor);
    }

    @Override
    public void transferir(Conta destino, double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("valor invalido");
        }
        if (getSaldo() < valor) {
            throw new SaldoInsuficienteException("saldo insuficiente");
        }
        sacar(valor);
        destino.depositar(valor);
        adicionarTransacao("Transferencia de R$:" + valor + " depositado na conta " + destino.getNumero());
    }

    public void renderJuros() {
        double rendimento = getSaldo() * taxaRendimentoMensal / 100.0;
        setSaldo(getSaldo() + rendimento);
        System.out.println("Rendimento mensal de R$:" + rendimento);
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "numero=" + getNumero() +
                ", taxaRendimentoMensal=" + taxaRendimentoMensal +
                ", saldo=" + saldo +
                '}';
    }
}
