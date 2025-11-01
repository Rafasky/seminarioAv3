package com.mycompany.appprojetofinal.entidades;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.appprojetofinal.utilitarios.SaldoInsuficienteException;
import java.util.Objects;

public abstract class Conta {

    private String numero;
    private double saldo;
    private Cliente propietario;
    private List<String> historicoTransacoes;

    public Conta(String numero, Cliente propietario) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("Numero da conta nao pode ficar vazio ou ser nulo");
        }
        if (propietario == null) {
            throw new IllegalArgumentException("Propietario da conta nao pode ser nulo");
        }
        this.numero = numero;
        this.propietario = propietario;
        this.saldo = 0.0;
        this.historicoTransacoes = new ArrayList<>();

        propietario.adcionarConta(this);
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public List<String> getHistoricoTransacoes() {
        return historicoTransacoes;
    }

    protected void adcionarTransacao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descricao nao pode ser vazia ou nula");
        }
        this.historicoTransacoes.add(descricao);

    }

    public abstract void depositar(double valor);

    
    public abstract void sacar(double valor) throws SaldoInsuficienteException;

    public abstract void transferir(Conta destino, double valor) throws
            SaldoInsuficienteException;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.numero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conta other = (Conta) obj;
        return Objects.equals(this.numero, other.numero);
    }

}
