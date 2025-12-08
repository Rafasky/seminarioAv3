package com.unileste.projetofinal.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.entidades.Cliente;

public class CadastroClientePanel extends JPanel {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtEndereco;
    private JButton btnSalvar;

    private final ClienteDAO clienteDAO;

    public CadastroClientePanel(ClienteDAO clienteDAO) {

        this.clienteDAO = clienteDAO;

        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        add(txtCpf);

        add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        add(txtEndereco);

        btnSalvar = new JButton("Cadastrar");
        btnSalvar.addActionListener(e -> salvar());
        add(btnSalvar);
    }

    private void salvar() {
        try {
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();
            String endereco = txtEndereco.getText();

            if (nome.isBlank() || cpf.isBlank() || endereco.isBlank()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            Cliente novo = new Cliente(nome, cpf, endereco);
            clienteDAO.salvar(novo);

            JOptionPane.showMessageDialog(this,
                    "Cliente cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
