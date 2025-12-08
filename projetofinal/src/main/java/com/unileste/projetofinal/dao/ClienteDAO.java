package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Cliente;
import java.util.List;

public interface ClienteDAO {

    void salvar(Cliente cliente);
    Cliente buscarPorCPF(String cpf);
    List<Cliente> listarTodos();
    void atualizar(Cliente cliente);
    void deletar(String cpf);
}
