package com.unileste.projetofinal.gui;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {

    private boolean authenticated = false;

    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);

        setSize(350, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Componentes de login
        JLabel lblUser = new JLabel("Usuário:");
        JLabel lblPass = new JLabel("Senha:");

        JTextField txtUser = new JTextField();
        JPasswordField txtPass = new JPasswordField();

        JButton btnLogin = new JButton("Entrar");
        JButton btnCancelar = new JButton("Cancelar");

        // Ação para login
        btnLogin.addActionListener(e -> {
            String user = txtUser.getText();
            String pass = new String(txtPass.getPassword());

            if (user.equals("admin") && pass.equals("123")) {
                authenticated = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciais inválidas!!!");
            }
        });

        // Ação para cancelar o login
        btnCancelar.addActionListener(e -> dispose());

        // Adicionar os componentes
        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(btnLogin);
        add(btnCancelar);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
