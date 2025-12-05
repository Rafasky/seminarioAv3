package com.unileste.projetofinal.gui;

import com.unileste.projetofinal.entidades.Cliente;

import javax.swing.*;
import java.awt.*;

public class ClientePanel extends JPanel {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JButton btnSalvar;
    private Cliente cliente;

    public ClientePanel(Cliente cliente) {
        setLayout(new BorderLayout(10, 10));
        this.cliente = cliente;

        // Informações do Cliente
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(4, 2, 5, 5));

        panelInfo.add(new JLabel("Nome:"));
        txtNome = new JTextField(cliente.getNome());
        txtNome.setEditable(false);  // Nome não pode ser alterado
        panelInfo.add(txtNome);

        panelInfo.add(new JLabel("CPF:"));
        txtCpf = new JTextField(cliente.getCpf());
        txtCpf.setEditable(false);  // CPF não pode ser alterado
        panelInfo.add(txtCpf);

        panelInfo.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField(cliente.getEndereco());
        panelInfo.add(txtEndereco);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarCliente());
        panelInfo.add(btnSalvar);

        add(panelInfo, BorderLayout.NORTH);
    }

    // Método para salvar as alterações no cliente
    private void salvarCliente() {
        cliente.setEndereco(txtEndereco.getText());
        JOptionPane.showMessageDialog(this, "Endereço salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
