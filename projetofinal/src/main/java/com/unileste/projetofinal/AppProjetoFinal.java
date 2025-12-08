package com.unileste.projetofinal;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ClienteDAOJdbc;
import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.dao.ContaDAOJdbc;
import com.unileste.projetofinal.gui.MainFrame;

import javax.swing.*;

public class AppProjetoFinal {

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAOJdbc();
        ContaDAO contaDAO = new ContaDAOJdbc();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(clienteDAO, contaDAO);
            frame.setVisible(true);
        });
    }
}
