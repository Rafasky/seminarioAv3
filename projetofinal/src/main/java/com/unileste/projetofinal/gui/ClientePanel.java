package com.unileste.projetofinal.gui;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.entidades.Cliente;

import javax.swing.*;
import java.awt.*;

public class ClientePanel extends JPanel {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JButton btnSalvar;

    private final Cliente cliente;
    private final ClienteDAO clienteDAO;

    public ClientePanel(Cliente cliente, ClienteDAO clienteDAO) {

        this.cliente = cliente;
        this.clienteDAO = clienteDAO;

        setLayout(new BorderLayout(10, 10));

        JPanel panelInfo = new JPanel(new GridLayout(4, 2, 5, 5));

        panelInfo.add(new JLabel("Nome:"));
        txtNome = new JTextField(cliente.getNome());
        txtNome.setEditable(false);
        panelInfo.add(txtNome);

        panelInfo.add(new JLabel("CPF:"));
        txtCpf = new JTextField(cliente.getCpf());
        txtCpf.setEditable(false);
        panelInfo.add(txtCpf);

        panelInfo.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField(cliente.getEndereco());
        panelInfo.add(txtEndereco);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarCliente());
        panelInfo.add(btnSalvar);

        add(panelInfo, BorderLayout.NORTH);
    }

    private void salvarCliente() {
        cliente.setEndereco(txtEndereco.getText());
        clienteDAO.atualizar(cliente);

        JOptionPane.showMessageDialog(this,
                "Endereço atualizado!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
