package com.unileste.projetofinal.entidades;

import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Conta {

    private String numero;
    protected double saldo;
    private Cliente proprietario;
    private List<String> historicoTransacoes;

    public Conta(String numero, Cliente proprietario) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Numero da conta nao pode ficar vazio ou ser nulo");
        }
        if (proprietario == null) {
            throw new IllegalArgumentException("Proprietario da conta nao pode ser nulo");
        }
        this.numero = numero;
        this.proprietario = proprietario;
        this.saldo = 0.0;
        this.historicoTransacoes = new ArrayList<>();
        proprietario.adicionarConta(this);
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public List<String> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    protected void adicionarTransacao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descricao nao pode ser vazia ou nula");
        }
        this.historicoTransacoes.add(descricao);
    }

    public abstract void depositar(double valor);

    public abstract void sacar(double valor) throws SaldoInsuficienteException;

    public abstract void transferir(Conta destino, double valor) throws SaldoInsuficienteException;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.numero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Conta other = (Conta) obj;
        return Objects.equals(this.numero, other.numero);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero='" + numero + '\'' +
                ", saldo=" + saldo +
                ", proprietario=" + proprietario.getNome() +
                '}';
    }
}
