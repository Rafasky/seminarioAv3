package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Conta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "contas.dat";
    private static List<Conta> contas = new ArrayList<>();

    static {
        carregar();
    }

    public static void salvar(Conta conta) {
        contas.add(conta);
        gravarEmArquivo();
    }

    public static List<Conta> listar() {
        return contas;
    }

    // CORREÇÃO: receber String e comparar com equals
    public static Conta buscarPorNumero(String numero) {
        if (numero == null) return null;
        for (Conta c : contas) {
            if (numero.equals(c.getNumero())) {
                return c;
            }
        }
        return null;
    }

    // opcional: método que tenta buscar por int (faz parse)
    public static Conta buscarPorNumero(int numero) {
        String numeroStr = String.valueOf(numero);
        return buscarPorNumero(numeroStr);
    }

    public static void atualizar() {
        gravarEmArquivo(); // usado após depósito, saque, transferência etc
    }

    private static void gravarEmArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(contas);
        } catch (Exception e) {
            System.err.println("Erro ao salvar contas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregar() {
        File arquivo = new File(FILE_NAME);
        if (!arquivo.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contas = (ArrayList<Conta>) in.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao carregar contas: " + e.getMessage());
        }
    }
}
