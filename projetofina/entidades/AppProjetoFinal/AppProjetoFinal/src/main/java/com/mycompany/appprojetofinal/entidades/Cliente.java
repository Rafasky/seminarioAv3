package com.mycompany.appprojetofinal.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {

    private final String nome;
    private final String cpf;
    private String endereco;
    private List<Conta> contas;

    public Cliente(String nome, String cpf, String endereco) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome nao pode ficar vazio");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF nao pode ficar vazio");
        }
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.contas=new ArrayList<>();
    }

    public List getContas() {
        return contas;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void adcionarConta(Conta conta) {
        contas.add(conta);
    }

    @Override
    public String toString() {
        return "Cliente{"
                + "nome: " + nome + "\n"
                + "CPF: " + cpf + "\n"
                + "endereco: " + endereco + "\n"
                + "contas: " + contas.size()
                + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
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
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.cpf, other.cpf);
    }

}
