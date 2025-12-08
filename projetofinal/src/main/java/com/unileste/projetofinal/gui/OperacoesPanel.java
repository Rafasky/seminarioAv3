package com.unileste.projetofinal.gui;

import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

import javax.swing.*;
import java.awt.*;

public class OperacoesPanel extends JPanel {

    private JTextField txtValor;
    private JButton btnDepositar, btnSacar, btnTransferir;

    private final Cliente cliente;
    private final ContaDAO contaDAO;
    private final JList<Conta> listaContas;

    public OperacoesPanel(Cliente cliente, ContaDAO contaDAO, JList<Conta> listaContas) {

        this.cliente = cliente;
        this.contaDAO = contaDAO;
        this.listaContas = listaContas;

        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Valor:"));
        txtValor = new JTextField();
        add(txtValor);

        btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(e -> depositar());
        add(btnDepositar);

        btnSacar = new JButton("Sacar");
        btnSacar.addActionListener(e -> sacar());
        add(btnSacar);

        btnTransferir = new JButton("Transferir");
        btnTransferir.addActionListener(e -> transferir());
        add(btnTransferir);
    }

    private Conta getContaSelecionada() {
        return listaContas.getSelectedValue();
    }

    private void depositar() {
        Conta c = getContaSelecionada();
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma conta");
            return;
        }
        try {
            double val = Double.parseDouble(txtValor.getText());
            c.depositar(val);
            contaDAO.atualizarSaldo(c);
            JOptionPane.showMessageDialog(this, "Depósito realizado!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido");
        }
    }

    private void sacar() {
        Conta c = getContaSelecionada();
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma conta");
            return;
        }
        try {
            double val = Double.parseDouble(txtValor.getText());
            c.sacar(val);
            contaDAO.atualizarSaldo(c);
            JOptionPane.showMessageDialog(this, "Saque realizado!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido");
        } catch (SaldoInsuficienteException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void transferir() {
        Conta origem = getContaSelecionada();
        if (origem == null) {
            JOptionPane.showMessageDialog(this, "Selecione a conta de origem");
            return;
        }
        Object[] contasDestino = cliente.getContas().stream().filter(c -> !c.equals(origem)).toArray();
        if (contasDestino.length == 0) {
            JOptionPane.showMessageDialog(this, "Cliente nao possui outras contas para destino");
            return;
        }

        Conta destino = (Conta) JOptionPane.showInputDialog(this,
                "Selecione a conta destino",
                "Transferir",
                JOptionPane.QUESTION_MESSAGE,
                null,
                contasDestino,
                contasDestino[0]);

        if (destino == null) return;

        try {
            double val = Double.parseDouble(txtValor.getText());
            origem.transferir(destino, val);
            contaDAO.atualizarSaldo(origem);
            contaDAO.atualizarSaldo(destino);
            JOptionPane.showMessageDialog(this, "Transferência realizada!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido");
        } catch (SaldoInsuficienteException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
