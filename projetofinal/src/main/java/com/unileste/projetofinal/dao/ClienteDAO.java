package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Cliente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String FILE_NAME = "clientes.dat";
    private static List<Cliente> clientes = new ArrayList<>();

    static {
        carregar();
    }

    public static void salvar(Cliente cliente) {
        clientes.add(cliente);
        gravarEmArquivo();
    }

    public static List<Cliente> listar() {
        return clientes;
    }

    public static Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    private static void gravarEmArquivo() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(clientes);
        } catch (Exception e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregar() {
        File arquivo = new File(FILE_NAME);
        if (!arquivo.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            clientes = (ArrayList<Cliente>) in.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }
}
