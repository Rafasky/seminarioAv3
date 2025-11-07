/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appprojetofinal.entidades;

import com.mycompany.appprojetofinal.utilitarios.SaldoInsuficienteException;

public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    public ContaCorrente(String numero, Cliente propietario, double limiteChequeEspecial) {
        super(numero, propietario);
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
    public void transferir(Conta destino, double valor) throws
            SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("valor invalido");

        }
        if (getSaldo() < valor) {
            throw new SaldoInsuficienteException("saldo insuficiente");

        }
        sacar(valor);
        destino.depositar(valor);

        adicionarTransacao("Transferencia de R$:" + valor + "despositado na conta " + destino.getNumero());
        getHistoricoTransacoes().add("Transferencia de R$:" + valor + "despositado na conta " + destino.getNumero());
    }

}

