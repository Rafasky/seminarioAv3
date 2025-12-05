package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;
import com.unileste.projetofinal.utilitarios.ContaNaoEncontradaException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAOJdbc implements ContaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/banco_sistema?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";  // Substitua pelo seu usuário do MySQL
    private static final String PASSWORD = "password";  // Substitua pela sua senha do MySQL

    @Override
    public void salvar(Conta conta) throws Exception {
        String sql = "INSERT INTO contas (numero, saldo, proprietario_cpf, tipo, limite_cheque_especial) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE saldo = VALUES(saldo), limite_cheque_especial = VALUES(limite_cheque_especial)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getNumero());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setString(3, conta.getProprietario().getCpf());
            if (conta instanceof ContaCorrente) {
                stmt.setString(4, "CORRENTE");
                stmt.setDouble(5, ((ContaCorrente) conta).getLimiteChequeEspecial());
            } else {
                stmt.setString(4, "POUPANCA");
                stmt.setDouble(5, 0.0);  // Poupança não tem limite de cheque especial
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar conta: " + e.getMessage());
        }
    }

    @Override
    public Conta buscarPorNumero(String numero) throws ContaNaoEncontradaException {
        String sql = "SELECT c.*, cl.nome, cl.endereco FROM contas c JOIN clientes cl ON c.proprietario_cpf = cl.cpf WHERE c.numero = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente proprietario = new Cliente(rs.getString("nome"), rs.getString("proprietario_cpf"), rs.getString("endereco"));
                String tipo = rs.getString("tipo");
                if ("CORRENTE".equals(tipo)) {
                    return new ContaCorrente(rs.getString("numero"), proprietario, rs.getDouble("limite_cheque_especial"));
                } else {
                    return new ContaPoupanca(rs.getString("numero"), proprietario);
                }
            } else {
                throw new ContaNaoEncontradaException("Conta com número " + numero + " não encontrada.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conta: " + e.getMessage());
        }
    }

    @Override
    public List<Conta> listarTodas() {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT c.*, cl.nome, cl.endereco FROM contas c JOIN clientes cl ON c.proprietario_cpf = cl.cpf";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente proprietario = new Cliente(rs.getString("nome"), rs.getString("proprietario_cpf"), rs.getString("endereco"));
                String tipo = rs.getString("tipo");
                if ("CORRENTE".equals(tipo)) {
                    contas.add(new ContaCorrente(rs.getString("numero"), proprietario, rs.getDouble("limite_cheque_especial")));
                } else {
                    contas.add(new ContaPoupanca(rs.getString("numero"), proprietario));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contas: " + e.getMessage());
        }
        return contas;
    }

    @Override
    public void deletar(String numero) throws ContaNaoEncontradaException {
        String sql = "DELETE FROM contas WHERE numero = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numero);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ContaNaoEncontradaException("Conta com número " + numero + " não encontrada para exclusão.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar conta: " + e.getMessage());
        }
    }

    @Override
    public List<Conta> buscarPorCliente(String cpfCliente) {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT c.*, cl.nome, cl.endereco FROM contas c JOIN clientes cl ON c.proprietario_cpf = cl.cpf WHERE c.proprietario_cpf = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpfCliente);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente proprietario = new Cliente(rs.getString("nome"), rs.getString("proprietario_cpf"), rs.getString("endereco"));
                String tipo = rs.getString("tipo");
                if ("CORRENTE".equals(tipo)) {
                    contas.add(new ContaCorrente(rs.getString("numero"), proprietario, rs.getDouble("limite_cheque_especial")));
                } else {
                    contas.add(new ContaPoupanca(rs.getString("numero"), proprietario));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar contas por cliente: " + e.getMessage());
        }
        return contas;
    }
}