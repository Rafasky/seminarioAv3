package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Conta;
import java.util.List;

public interface ContaDAO {

    void salvar(Conta conta);
    Conta buscarPorNumero(String numero);
    List<Conta> listarTodas();
    void atualizarSaldo(Conta conta);
}
