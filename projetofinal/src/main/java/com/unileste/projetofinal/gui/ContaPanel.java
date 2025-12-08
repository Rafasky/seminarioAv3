package com.unileste.projetofinal.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;

public class ContaPanel extends JPanel {

    private DefaultListModel<Conta> listModel = new DefaultListModel<>();
    private JList<Conta> listaContas = new JList<>(listModel);

    private final Cliente cliente;
    private final ContaDAO contaDAO;

    public ContaPanel(Cliente cliente, ContaDAO contaDAO) {

        this.cliente = cliente;
        this.contaDAO = contaDAO;

        setLayout(new BorderLayout(10, 10));

        listaContas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listaContas), BorderLayout.CENTER);

        JButton btnAdd = new JButton("Adicionar Conta");
        btnAdd.addActionListener(e -> adicionarConta());

        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl.add(btnAdd);
        add(pnl, BorderLayout.SOUTH);

        carregarContasDoCliente();
    }

    private void carregarContasDoCliente() {
        listModel.clear();
        cliente.getContas().forEach(listModel::addElement);
    }

    private void adicionarConta() {

        String num = JOptionPane.showInputDialog("Número da conta:");
        if (num == null || num.isBlank()) return;

        String[] tipos = {"Conta Corrente", "Conta Poupança"};
        String tipo = (String) JOptionPane.showInputDialog(
            this,
            "Selecione o tipo de conta",
            "Tipo de Conta",
            JOptionPane.QUESTION_MESSAGE,
            null,
            tipos,
            tipos[0]
        );

        if (tipo == null) return;

        Conta novaConta;

        if (tipo.equals("Conta Corrente")) {
            novaConta = new ContaCorrente(num, cliente, 300);
        } else {
            novaConta = new ContaPoupanca(num, cliente);
        }

        cliente.adicionarConta(novaConta);
        contaDAO.salvar(novaConta);
        listModel.addElement(novaConta);

        JOptionPane.showMessageDialog(this, "Conta adicionada!");
    }

    public JList<Conta> getListaContas() {
        return listaContas;
    }
}
