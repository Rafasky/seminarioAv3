package com.unileste.projetofinal.operacoes;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;
import com.unileste.projetofinal.utilitarios.ClienteNaoEncontradoException;
import com.unileste.projetofinal.utilitarios.ContaNaoEncontradaException;
import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alexandre.rafaschi
 */
public class BancoService {
    private String nomeBanco;
    private Map<String, Cliente> clientes;
    private Map<String, Conta> contas;
    private int proximoNumeroConta;

    public BancoService(String nomeBanco) {
        this.nomeBanco = nomeBanco;
        this.clientes = new HashMap<>();
        this.contas = new HashMap<>();
        this.proximoNumeroConta = 1;

    }

    public void cadastrarCliente(Cliente cliente) {
        if (clientes.containsKey(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF ja cadastrado");
        }
        clientes.put(cliente.getCpf(), cliente);
    }

    public Conta abrirConta(Cliente cliente, String tipoConta, double... parametros) throws ClienteNaoEncontradoException {
        if (!clientes.containsKey(cliente.getCpf())) {
            throw new ClienteNaoEncontradoException("Cliente nao encontrado: " + cliente.getCpf());
        }
        String numeroGerado = String.valueOf(proximoNumeroConta++);
        Conta novaConta;
        if (tipoConta.equalsIgnoreCase("corrente")) {
            double limite = parametros.length > 0 ? parametros[0] : 0.0;
            novaConta = new ContaCorrente(numeroGerado, cliente, limite);
        } else if (tipoConta.equalsIgnoreCase("poupanca")) {
            novaConta = new ContaPoupanca(numeroGerado, cliente);
        } else {
            throw new IllegalArgumentException("Tipo de conta inválido.");
        }

        contas.put(numeroGerado, novaConta);
        return novaConta;
    }

    public Cliente buscarCliente(String cpf) throws ClienteNaoEncontradoException {
        Cliente c = clientes.get(cpf);
        if (c == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado: " + cpf);
        }
        return c;
    }

    public Conta buscarConta(String numeroConta) throws ContaNaoEncontradaException {
        Conta conta = contas.get(numeroConta);
        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta não encontrada: " + numeroConta);
        }
        return conta;
    }

    public void realizarDeposito(String numeroConta, double valor) throws ContaNaoEncontradaException {
        Conta conta = buscarConta(numeroConta);
        conta.depositar(valor);
    }

    public void realizarSaque(String numeroConta, double valor)
            throws ContaNaoEncontradaException, SaldoInsuficienteException {

        Conta conta = buscarConta(numeroConta);
        conta.sacar(valor);
    }

    public void realizarTransferencia(String contaOrigem, String contaDestino, double valor)
            throws ContaNaoEncontradaException, SaldoInsuficienteException {

        Conta origem = buscarConta(contaOrigem);
        Conta destino = buscarConta(contaDestino);

        origem.transferir(destino, valor);
    }

    public Map<String, Cliente> getClientes() {
        return new HashMap<>(clientes);
    }

    public Map<String, Conta> getContas() {
        return new HashMap<>(contas);
    }

    public String getNomeBanco() {
        return nomeBanco;
    }
}