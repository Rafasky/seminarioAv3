package com.unileste.projetofinal.gui;

import com.unileste.projetofinal.operacoes.BancoService;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(BancoService banco) {

        setTitle("Sistema Bancário");
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));

        JButton btnCliente = new JButton("Gerenciar Clientes");
        JButton btnConta = new JButton("Gerenciar Contas");
        JButton btnOperacoes = new JButton("Operações");

        btnCliente.addActionListener(e -> new ClientePanel(this, banco).setVisible(true));
        btnConta.addActionListener(e -> new ContaPanel(this, banco).setVisible(true));
        btnOperacoes.addActionListener(e -> new OperacoesPanel(this, banco).setVisible(true));

        add(btnCliente);
        add(btnConta);
        add(btnOperacoes);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
