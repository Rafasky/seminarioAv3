package com.unileste.projetofinal;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;

import javax.swing.*;
import java.util.List;

public class AppProjetoFinal {

    public static void main(String[] args) {

        while (true) {

            String[] opcoes = {
                "Cadastrar Cliente",
                "Consultar",
                "Adicionar Saldo",
                "Sair"
            };

            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Menu Principal:",
                    "Banco",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {

                case 0 -> cadastrarCliente();

                case 1 -> submenuConsultar();

                case 2 -> adicionarSaldo();

                default -> System.exit(0);
            }
        }
    }

    // ---------------------------
    // CADASTRAR CLIENTE
    // ---------------------------
    private static void cadastrarCliente() {

        String nome = JOptionPane.showInputDialog("Nome:");
        if (nome == null) return;

        String cpf = JOptionPane.showInputDialog("CPF:");
        if (cpf == null) return;

        String endereco = JOptionPane.showInputDialog("Endereço:");
        if (endereco == null) return;

        Cliente c = new Cliente(nome, cpf, endereco);
        ClienteDAO.salvar(c);

        JOptionPane.showMessageDialog(null, "Cliente cadastrado!");

        criarContaParaCliente(c);
    }

    // cria conta após cadastro
    private static void criarContaParaCliente(Cliente c) {

        String[] tipos = {"Conta Corrente", "Conta Poupança"};
        int tipo = JOptionPane.showOptionDialog(
                null,
                "Escolha o tipo da conta:",
                "Criar Conta",
                0,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                tipos,
                tipos[0]
        );

        String numero = JOptionPane.showInputDialog("Número da conta:");
        if (numero == null) return;

        Conta conta;

        if (tipo == 0) {
            conta = new ContaCorrente(numero, c, 500);
        } else {
            conta = new ContaPoupanca(numero, c, 0.5);
        }

        c.adicionarConta(conta);
        ContaDAO.salvar(conta);

        JOptionPane.showMessageDialog(null, "Conta criada!");
    }

    // ---------------------------
    // CONSULTAR
    // ---------------------------
    private static void submenuConsultar() {

        String[] op = {"Consultar Cliente", "Consultar Conta"};

        int es = JOptionPane.showOptionDialog(
                null,
                "Escolha:",
                "Consultar",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                op,
                op[0]
        );

        if (es == 0) consultarCliente();
        else consultarConta();
    }

    private static void consultarCliente() {
        List<Cliente> lista = ClienteDAO.listar();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!");
            return;
        }

        Cliente cli = (Cliente) JOptionPane.showInputDialog(
                null,
                "Selecione o cliente:",
                "Clientes",
                JOptionPane.QUESTION_MESSAGE,
                null,
                lista.toArray(),
                null
        );

        if (cli != null)
            JOptionPane.showMessageDialog(null, cli.toString());
    }

    private static void consultarConta() {

        List<Conta> contas = ContaDAO.listar();

        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada!");
            return;
        }

        Conta c = (Conta) JOptionPane.showInputDialog(
                null,
                "Selecione a conta:",
                "Contas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                contas.toArray(),
                null
        );

        if (c != null)
            JOptionPane.showMessageDialog(null, c.toString());
    }

    // ---------------------------
    // ADICIONAR SALDO
    // ---------------------------
    private static void adicionarSaldo() {

        List<Conta> contas = ContaDAO.listar();

        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma conta cadastrada!");
            return;
        }

        Conta conta = (Conta) JOptionPane.showInputDialog(
                null,
                "Escolha a conta:",
                "Contas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                contas.toArray(),
                null
        );

        if (conta == null) return;

        String valorStr = JOptionPane.showInputDialog("Valor do depósito:");
        if (valorStr == null) return;

        try {
            double valor = Double.parseDouble(valorStr);
            conta.depositar(valor);
            ContaDAO.atualizar();
            JOptionPane.showMessageDialog(null, "Depósito realizado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Valor inválido!");
        }
    }
}
