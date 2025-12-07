package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAOJdbc implements ContaDAO {

    @Override
    public void salvar(Conta conta) {
        String sql = "INSERT INTO conta (numero, saldo, cpf_cliente, tipo, limite) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, conta.getNumero());
            pst.setDouble(2, conta.getSaldo());
            pst.setString(3, conta.getProprietario().getCpf());

            if (conta instanceof ContaCorrente cc) {
                pst.setString(4, "CORRENTE");
                pst.setDouble(5, cc.getLimiteChequeEspecial());
            } else {
                pst.setString(4, "POUPANCA");
                pst.setNull(5, Types.DOUBLE);
            }

            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar conta.", e);
        }
    }

    @Override
    public Conta buscarPorNumero(String numero) {
        String sql = "SELECT * FROM conta WHERE numero = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, numero);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) return null;

            ClienteDAOJdbc clienteDAO = new ClienteDAOJdbc();
            Cliente proprietario = clienteDAO.buscarPorCPF(rs.getString("cpf_cliente"));

            String tipo = rs.getString("tipo");

            if (tipo.equals("CORRENTE")) {
                return new ContaCorrente(
                        rs.getString("numero"),
                        proprietario,
                        rs.getDouble("limite")
                );
            } else {
                return new ContaPoupanca(
                        rs.getString("numero"),
                        proprietario
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conta.", e);
        }
    }

    @Override
    public List<Conta> listarTodas() {
        String sql = "SELECT * FROM conta";
        List<Conta> contas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            ClienteDAOJdbc clienteDAO = new ClienteDAOJdbc();

            while (rs.next()) {
                Cliente prop = clienteDAO.buscarPorCPF(rs.getString("cpf_cliente"));
                String tipo = rs.getString("tipo");

                Conta conta;

                if (tipo.equals("CORRENTE")) {
                    conta = new ContaCorrente(
                            rs.getString("numero"),
                            prop,
                            rs.getDouble("limite")
                    );
                } else {
                    conta = new ContaPoupanca(
                            rs.getString("numero"),
                            prop
                    );
                }

                contas.add(conta);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contas.", e);
        }

        return contas;
    }

    @Override
    public void atualizarSaldo(Conta conta) {
        String sql = "UPDATE conta SET saldo = ? WHERE numero = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setDouble(1, conta.getSaldo());
            pst.setString(2, conta.getNumero());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar saldo.", e);
        }
    }
}
