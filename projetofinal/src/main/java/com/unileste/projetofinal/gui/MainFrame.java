package com.unileste.projetofinal.gui;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.entidades.Cliente;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final ClienteDAO clienteDAO;
    private final ContaDAO contaDAO;

    public MainFrame(ClienteDAO clienteDAO, ContaDAO contaDAO) {

        this.clienteDAO = clienteDAO;
        this.contaDAO = contaDAO;

        setTitle("Sistema Bancário");
        setSize(400, 200);
        setLayout(new GridLayout(3, 1, 5, 5));

        JButton btnCliente = new JButton("Gerenciar Clientes");
        JButton btnConta = new JButton("Gerenciar Contas");
        JButton btnSair = new JButton("Sair");

        btnCliente.addActionListener(e -> abrirGerenciarClientes());
        btnConta.addActionListener(e -> abrirGerenciarContas());
        btnSair.addActionListener(e -> System.exit(0));

        add(btnCliente);
        add(btnConta);
        add(btnSair);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void abrirGerenciarClientes() {

        String cpf = JOptionPane.showInputDialog(this, "CPF do cliente:");
        if (cpf == null || cpf.isBlank()) return;

        Cliente c = clienteDAO.buscarPorCPF(cpf);
        if (c == null) {
            int criar = JOptionPane.showConfirmDialog(this, "Cliente nao encontrado. Deseja criar?", "Criar cliente", JOptionPane.YES_NO_OPTION);
            if (criar == JOptionPane.YES_OPTION) {
                String nome = JOptionPane.showInputDialog(this, "Nome:");
                if (nome == null) return;
                String endereco = JOptionPane.showInputDialog(this, "Endereço:");
                if (endereco == null) return;
                Cliente novo = new Cliente(nome, cpf, endereco);
                clienteDAO.salvar(novo);
                JOptionPane.showMessageDialog(this, "Cliente cadastrado!");
                c = novo;
            } else {
                return;
            }
        }

        JFrame f = new JFrame("Cliente: " + c.getNome());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(new ClientePanel(c, clienteDAO));
        f.pack();
        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }

    private void abrirGerenciarContas() {
        
        String cpf = JOptionPane.showInputDialog(this, "CPF do cliente:");
        if (cpf == null || cpf.isBlank()) return;

        Cliente c = clienteDAO.buscarPorCPF(cpf);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado");
            return;
        }

        // CARREGAR CONTAS DO CLIENTE VIA JDBC
        c.getContas().clear();
        contaDAO.listarTodas().stream()
                .filter(conta -> conta.getProprietario().getCpf().equals(c.getCpf()))
                .forEach(c::adicionarConta);

        JFrame f = new JFrame("Contas do cliente: " + c.getNome());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ContaPanel contaPanel = new ContaPanel(c, contaDAO);
        f.add(contaPanel, BorderLayout.CENTER);

        OperacoesPanel ops = new OperacoesPanel(c, contaDAO, contaPanel.getListaContas());
        f.add(ops, BorderLayout.SOUTH);

        f.pack();
        f.setLocationRelativeTo(this);
        f.setVisible(true);
    }
    
}
