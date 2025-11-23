package com.unileste.projetofinal.gui;

import javax.swing.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class ClientePanel extends JFrame implements ActionListener {

    private JLabel labelNome, labelCpf, labelSenha, labelEndereco, labelContas, labelTipoCliente, labelOpcoes; 
    private JTextField campoNome, campoCpf; 
    private JPasswordField campoSenha; 
    private JTextArea areaEndereco; 
    private JRadioButton radioPf, radioPj; 
    private ButtonGroup grupoTipoCliente; 
    private JCheckBox checkNotificacoes; 
    private JButton botaoCadastrar; 
    private JComboBox comboContas;
    private JPanel painelPrincipal;
    private JFrame janela;
    private String tipoCliente, notificacoes;

    public ClientePanel() {
        
        janela = new JFrame("Cadastro de Cliente Simples");
        janela.setSize(500, 635); // Ajuste de altura para acomodar o novo campo
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null); // Centraliza a janela
        
        // Configuração do Painel (JPanel)
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null); // Desativamos o gerenciador de layout 
        janela.add(painelPrincipal);
        
        labelNome = new JLabel("Nome:");
        labelNome.setBounds(20, 20, 80, 25);
        painelPrincipal.add(labelNome);
        
        campoNome = new JTextField();
        campoNome.setBounds(100, 20, 350, 25);
        painelPrincipal.add(campoNome);
        
        labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(20, 55, 80, 25);
        painelPrincipal.add(labelCpf);
        
        campoCpf = new JTextField();
        campoCpf.setBounds(100, 55, 150, 25);
        painelPrincipal.add(campoCpf);
        
        labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(20, 90, 80, 25);
        painelPrincipal.add(labelSenha);
        
        campoSenha = new JPasswordField(); 
        campoSenha.setBounds(100, 90, 150, 25);
        painelPrincipal.add(campoSenha);

        labelEndereco = new JLabel("Endereço:");
        labelEndereco.setBounds(20, 125, 80, 25);
        painelPrincipal.add(labelEndereco);
        
        areaEndereco = new JTextArea();
        areaEndereco.setLineWrap(true);
        areaEndereco.setWrapStyleWord(true);
        JScrollPane scrollEndereco = new JScrollPane(areaEndereco);
        scrollEndereco.setBounds(100, 125, 350, 80);
        painelPrincipal.add(scrollEndereco);

        labelTipoCliente = new JLabel("Tipo Cliente:");
        labelTipoCliente.setBounds(20, 215, 100, 25);
        painelPrincipal.add(labelTipoCliente);
        
        radioPf = new JRadioButton("Pessoa Física");
        radioPf.setBounds(120, 215, 120, 25);
        radioPf.setSelected(true);
        radioPf.addActionListener(this);
        painelPrincipal.add(radioPf);

        radioPj = new JRadioButton("Pessoa Jurídica");
        radioPj.setBounds(250, 215, 150, 25);
        radioPj.addActionListener(this);
        painelPrincipal.add(radioPj);

        grupoTipoCliente = new ButtonGroup();
        grupoTipoCliente.add(radioPf);
        grupoTipoCliente.add(radioPj);

        labelOpcoes = new JLabel("Opções:");
        labelOpcoes.setBounds(20, 250, 80, 25); // Abaixo dos Rádios (215 + 35)
        painelPrincipal.add(labelOpcoes);
        
        checkNotificacoes = new JCheckBox("Receber Notificações");
        checkNotificacoes.setBounds(100, 250, 200, 25);
        checkNotificacoes.setSelected(true);
        checkNotificacoes.addActionListener(this);
        painelPrincipal.add(checkNotificacoes);
        
        labelContas = new JLabel("Contas:");
        labelContas.setBounds(20, 285, 80, 25); 
        painelPrincipal.add(labelContas);
        
        String[] contas = {"Conta Corrente - 12345-6", "Poupança -78901-2", "Investimento - 34567-8", "Conta Salário - 98765-4"};
        comboContas = new JComboBox<>(contas);
        comboContas.setBounds(100, 285, 350, 25);
        comboContas.setSelectedIndex(0);
        comboContas.addActionListener(this);
        painelPrincipal.add(comboContas);
        
        botaoCadastrar = new JButton("Cadastrar Cliente");
        botaoCadastrar.setBounds(150, 335, 200, 40); // Abaixo do ComboBox (285 + 25 + 25) 
        botaoCadastrar.addActionListener(this);
        painelPrincipal.add(botaoCadastrar);
        
        janela.setVisible(true);
        
    }

    @Override public void 
    actionPerformed(ActionEvent e) { 
        if (e.getSource() == botaoCadastrar) { 
            String nome = campoNome.getText(); 
            String cpf = campoCpf.getText();
            String senha = new String(campoSenha.getPassword()); 
            String endereco = areaEndereco.getText();
            if(radioPf.isSelected()){
                this.tipoCliente = "Pessoa Física";
            }
            else{
                this.tipoCliente = "Pessoa Jurídica";
            }

            if(checkNotificacoes.isSelected()){
                this.notificacoes = "Sim";
            }
            else{
                this.notificacoes = "Não";
            }

            String contaSelecionada = (String)
            comboContas.getSelectedItem();
            String mensagem = "--- Dados do Cliente ---\n";
            mensagem += "Nome: " + nome + "\n";
            mensagem += "CPF: " + cpf + "\n";
            mensagem += "Senha: " + senha + "\n"; // Adicionando a senha à mensagem 
            mensagem += "Endereço: " + endereco + "\n";
            mensagem += "Tipo: " + tipoCliente + "\n";
            mensagem += "Receber Notificações: " + notificacoes + "\n";
            mensagem += "Conta Selecionada: " + (contaSelecionada != null ? contaSelecionada : "Nenhuma") + "\n";
            JOptionPane.showMessageDialog(this, mensagem, "Cliente Cadastrado!", JOptionPane.INFORMATION_MESSAGE);

        }
        else if (e.getSource() == radioPf) {
                JOptionPane.showMessageDialog(this, "Tipo de Cliente selecionado: Pessoa Física", "Seleção de Rádio", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == radioPj) {
                JOptionPane.showMessageDialog(this, "Tipo de Cliente selecionado: Pessoa Jurídica", "Seleção de Rádio", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == checkNotificacoes) {
            if (checkNotificacoes.isSelected()) {
                JOptionPane.showMessageDialog(this, "Opção: Receber Notificações ATIVADA", "Opção de CheckBox", 
                JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(this, "Opção: Receber Notificações DESATIVADA", "Opção de CheckBox", 
                JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource() == comboContas) {
            String contaSelecionada = (String) 
            comboContas.getSelectedItem();
            JOptionPane.showMessageDialog(this, "Você selecionou: " + contaSelecionada, "Seleção de Conta",
            JOptionPane.INFORMATION_MESSAGE);
        }

    }
    
}
