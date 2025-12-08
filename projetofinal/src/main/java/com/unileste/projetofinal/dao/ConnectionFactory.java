package com.unileste.projetofinal.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/projetobanco";
    private static final String USER = "root";
    private static final String PASSWORD = "sua_senha_aqui";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco!", e);
        }
    }
}