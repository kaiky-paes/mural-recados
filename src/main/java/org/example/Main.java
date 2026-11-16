package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private static final RecadoDAO DAO = new RecadoDAO();

    public static void main(String[] args) throws Exception {
        testConnection();

        //0.0.0.0 aceita conexoes de qualquer ip da rede (outro pc, celular)
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8080), 0);

        server.createContext("/api/recados", RecadoController::listenRecados);
        server.createContext("/", RecadoController::openPage);
        server.start();

        System.out.println("Mural aberto em http://localhost:8080");
        System.out.println("Celulares podem acessar pelo IP da rede local na porta 8080");
    }

    public static void testConnection() throws SQLException {
        try (Connection ignored = Conexao.open()) {
            System.out.println("Banco de dados conectado.");
        }
    }
}