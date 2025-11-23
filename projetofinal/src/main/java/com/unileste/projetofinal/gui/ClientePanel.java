package com.unileste.projetofinal.gui;

import javax.swing.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class ClientePanel extends JPanel implements ActionListener {

    private JLabel labelNome, labelCpf, labelSenha, labelEndereco, labelContas, labelTipoCliente, labelOpcoes;
    private JTextField campoNome, campoCpf;
    private JPasswordField campoSenha;
    private JTextArea areaEndereco;
    private JRadioButton radioPf, radioPj;
    private ButtonGroup grupoTipoCliente;
    private JCheckBox checkNotificacoes;
    private JButton botaoCadastrar;
    private JComboBox comboContas;

    private String tipoCliente, notificacoes;

    public ClientePanel() {

        setLayout(null); // manter layout absoluto igual ao seu original

        labelNome = new JLabel("Nome:");
        labelNome.setBounds(20, 20, 80, 25);
        add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(100, 20, 350, 25);
        add(campoNome);

        labelCpf = new JLabel("CPF:");
        labelCpf.setBounds(20, 55, 80, 25);
        add(labelCpf);

        campoCpf = new JTextField();
        campoCpf.setBounds(100, 55, 150, 25);
        add(campoCpf);

        labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(20, 90, 80, 25);
        add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(100, 90, 150, 25);
        add(campoSenha);

        labelEndereco = new JLabel("Endereço:");
        labelEndereco.setBounds(20, 125, 80, 25);
        add(labelEndereco);

        areaEndereco = new JTextArea();
        areaEndereco.setLineWrap(true);
        areaEndereco.setWrapStyleWord(true);
        JScrollPane scrollEndereco = new JScrollPane(areaEndereco);
        scrollEndereco.setBounds(100, 125, 350, 80);
        add(scrollEndereco);

        labelTipoCliente = new JLabel("Tipo Cliente:");
        labelTipoCliente.setBounds(20, 215, 100, 25);
        add(labelTipoCliente);

        radioPf = new JRadioButton("Pessoa Física");
        radioPf.setBounds(120, 215, 120, 25);
        radioPf.setSelected(true);
        radioPf.addActionListener(this);
        add(radioPf);

        radioPj = new JRadioButton("Pessoa Jurídica");
        radioPj.setBounds(250, 215, 150, 25);
        radioPj.addActionListener(this);
        add(radioPj);

        grupoTipoCliente = new ButtonGroup();
        grupoTipoCliente.add(radioPf);
        grupoTipoCliente.add(radioPj);

        labelOpcoes = new JLabel("Opções:");
        labelOpcoes.setBounds(20, 250, 80, 25);
        add(labelOpcoes);

        checkNotificacoes = new JCheckBox("Receber Notificações");
        checkNotificacoes.setBounds(100, 250, 200, 25);
        checkNotificacoes.setSelected(true);
        checkNotificacoes.addActionListener(this);
        add(checkNotificacoes);

        labelContas = new JLabel("Contas:");
        labelContas.setBounds(20, 285, 80, 25);
        add(labelContas);

        String[] contas = {
            "Conta Corrente - 12345-6",
            "Poupança -78901-2",
            "Investimento - 34567-8",
            "Conta Salário - 98765-4"
        };

        comboContas = new JComboBox<>(contas);
        comboContas.setBounds(100, 285, 350, 25);
        comboContas.addActionListener(this);
        add(comboContas);

        botaoCadastrar = new JButton("Cadastrar Cliente");
        botaoCadastrar.setBounds(150, 335, 200, 40);
        botaoCadastrar.addActionListener(this);
        add(botaoCadastrar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoCadastrar) {

            String nome = campoNome.getText();
            String cpf = campoCpf.getText();
            String senha = new String(campoSenha.getPassword());
            String endereco = areaEndereco.getText();

            tipoCliente = radioPf.isSelected() ? "Pessoa Física" : "Pessoa Jurídica";
            notificacoes = checkNotificacoes.isSelected() ? "Sim" : "Não";
            String contaSelecionada = (String) comboContas.getSelectedItem();

            String mensagem =
                "--- Dados do Cliente ---\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "Senha: " + senha + "\n" +
                "Endereço: " + endereco + "\n" +
                "Tipo: " + tipoCliente + "\n" +
                "Receber Notificações: " + notificacoes + "\n" +
                "Conta Selecionada: " + contaSelecionada;

            JOptionPane.showMessageDialog(this, mensagem);
        }
    }
}