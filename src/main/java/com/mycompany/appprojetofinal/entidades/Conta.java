package com.mycompany.appprojetofinal.entidades;
import java.util.ArrayList;
import java.util.List;

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
    
    //Criar exceção para o metodo quando implementar nas classes contaCorrente e contaPoupança
    public abstract void sacar(double valor);
    

    
}
