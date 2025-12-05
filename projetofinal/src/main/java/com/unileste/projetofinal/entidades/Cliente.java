package com.unileste.projetofinal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String cpf;
    private String endereco;
    private List<Conta> contas = new ArrayList<>();

    public Cliente(String nome, String cpf, String endereco) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome nao pode ficar vazio");
        if (cpf == null || cpf.isBlank())
            throw new IllegalArgumentException("CPF nao pode ficar vazio");

        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public List<Conta> getContas() {
        return contas;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEndereco() { return endereco; }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}
