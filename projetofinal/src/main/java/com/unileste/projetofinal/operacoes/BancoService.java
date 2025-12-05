package com.unileste.projetofinal.operacoes;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.entidades.*;

public class BancoService {

    private ClienteDAO clienteDAO;
    private ContaDAO contaDAO;

    public BancoService(ClienteDAO clienteDAO, ContaDAO contaDAO) {
        this.clienteDAO = clienteDAO;
        this.contaDAO = contaDAO;
    }

    public Cliente cadastrarCliente(String nome, String cpf, String endereco) throws Exception {
        Cliente cliente = new Cliente(nome, cpf, endereco);
        clienteDAO.salvar(cliente);
        return cliente;
    }

    public ContaCorrente criarContaCorrente(String numero, Cliente cliente, double limite) throws Exception {
        ContaCorrente conta = new ContaCorrente(numero, cliente, limite);
        contaDAO.salvar(conta);
        return conta;
    }

    public ContaPoupanca criarContaPoupanca(String numero, Cliente cliente, double taxa) throws Exception {
        ContaPoupanca conta = new ContaPoupanca(numero, cliente, taxa);
        contaDAO.salvar(conta);
        return conta;
    }
}
