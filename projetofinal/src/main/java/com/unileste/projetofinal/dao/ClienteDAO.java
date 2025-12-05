package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.utilitarios.ClienteNaoEncontradoException;
import com.unileste.projetofinal.utilitarios.ContaNaoEncontradaException;

import java.util.List;

public interface ClienteDAO {
    void salvar(Cliente cliente) throws Exception;  // Inserir ou atualizar
    Cliente buscarPorCpf(String cpf) throws ClienteNaoEncontradoException;
    List<Cliente> listarTodos();
    void deletar(String cpf) throws ClienteNaoEncontradoException;
}

public interface ContaDAO {
    void salvar(Conta conta) throws Exception;  // Inserir ou atualizar
    Conta buscarPorNumero(String numero) throws ContaNaoEncontradaException;
    List<Conta> listarTodas();
    void deletar(String numero) throws ContaNaoEncontradaException;
    List<Conta> buscarPorCliente(String cpfCliente);
}