package com.unileste.projetofinal.gui;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;

import javax.swing.*;
import java.awt.*;

public class ContaPanel extends JPanel {

    private DefaultListModel<Conta> listModel;
    private JList<Conta> listaContas;
    private JButton btnAdicionarConta;
    private Cliente cliente;

    public ContaPanel(Cliente cliente) {
        setLayout(new BorderLayout(10, 10));
        this.cliente = cliente;

        // Lista de Contas do Cliente
        listModel = new DefaultListModel<>();
        listaContas = new JList<>(listModel);
        listaContas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaContas);
        add(scrollPane, BorderLayout.CENTER);

        // Botão para adicionar nova conta
        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT));
        btnAdicionarConta = new JButton("Adicionar Conta");
        btnAdicionarConta.addActionListener(e -> adicionarConta());
        panelBotoes.add(btnAdicionarConta);

        add(panelBotoes, BorderLayout.SOUTH);

        // Preencher lista de contas
        carregarContas();
    }

    // Carregar as contas do cliente na lista
    private void carregarContas() {
        for (Conta conta : cliente.getContas()) {
            listModel.addElement(conta);
        }
    }

    // Adicionar uma nova conta ao cliente
    private void adicionarConta() {
        String numeroConta = JOptionPane.showInputDialog(this, "Informe o número da conta:");
        if (numeroConta != null && !numeroConta.trim().isEmpty()) {
            Conta novaConta = new ContaCorrente(numeroConta, cliente);  // Exemplo para ContaCorrente
            cliente.adcionarConta(novaConta);
            listModel.addElement(novaConta);
            JOptionPane.showMessageDialog(this, "Conta adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
