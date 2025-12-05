package com.unileste.projetofinal.gui;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

import javax.swing.*;
import java.awt.*;

public class OperacoesPanel extends JPanel {

    private JTextField txtValorOperacao;
    private JButton btnDepositar;
    private JButton btnSacar;
    private JButton btnTransferir;
    private Cliente cliente;

    public OperacoesPanel(Cliente cliente) {
        setLayout(new GridLayout(4, 2, 5, 5));
        this.cliente = cliente;

        add(new JLabel("Valor:"));
        txtValorOperacao = new JTextField();
        add(txtValorOperacao);

        btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(e -> realizarDeposito());
        add(btnDepositar);

        btnSacar = new JButton("Sacar");
        btnSacar.addActionListener(e -> realizarSaque());
        add(btnSacar);

        btnTransferir = new JButton("Transferir");
        btnTransferir.addActionListener(e -> realizarTransferencia());
        add(btnTransferir);
    }

    private void realizarDeposito() {
        Conta contaSelecionada = getContaSelecionada();
        if (contaSelecionada != null) {
            try {
                double valor = Double.parseDouble(txtValorOperacao.getText());
                contaSelecionada.depositar(valor);
                JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void realizarSaque() {
        Conta contaSelecionada = getContaSelecionada();
        if (contaSelecionada != null) {
            try {
                double valor = Double.parseDouble(txtValorOperacao.getText());
                contaSelecionada.sacar(valor);
                JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SaldoInsuficienteException e) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void realizarTransferencia() {
        Conta contaOrigem = getContaSelecionada();
        if (contaOrigem != null) {
            Conta contaDestino = (Conta) JOptionPane.showInputDialog(this, "Selecione a conta destino", "Transferir",
                    JOptionPane.QUESTION_MESSAGE, null, cliente.getContas().toArray(), null);

            if (contaDestino != null) {
                try {
                    double valor = Double.parseDouble(txtValorOperacao.getText());
                    contaOrigem.transferir(contaDestino, valor);
                    JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (SaldoInsuficienteException e) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Valor inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private Conta getContaSelecionada() {
        JList<Conta> listaContas = ((MainFrame) SwingUtilities.getWindowAncestor(this)).getContaPanel().getListaContas();
        return listaContas.getSelectedValue();
    }
}
