package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOJdbc implements ClienteDAO {

    @Override
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (cpf, nome, endereco) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, cliente.getCpf());
            pst.setString(2, cliente.getNome());
            pst.setString(3, cliente.getEndereco());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente.", e);
        }
    }

    @Override
    public Cliente buscarPorCPF(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente.", e);
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                );
                lista.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes.", e);
        }

        return lista;
    }

    @Override
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET endereco = ? WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, cliente.getEndereco());
            pst.setString(2, cliente.getCpf());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    @Override
    public void deletar(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, cpf);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente.", e);
        }
    }
}
