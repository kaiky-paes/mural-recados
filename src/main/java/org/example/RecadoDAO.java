package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecadoDAO {
    public void create(Recado recado) throws SQLException {
        String sql = "INSERT INTO recado (autor, message) VALUES(?,?)";

        try (Connection connection = Conexao.open();
             PreparedStatement command = connection.prepareStatement(sql)) {
            command.setString(1, recado.getAutor());
            command.setString(2, recado.getMessage());
            command.executeUpdate();
        }
    }

    public List<Recado> list() throws SQLException {
        String sql = "SELECT id, autor, message FROM recado ORDER BY id DESC";
        List<Recado> recados = new ArrayList<>();

        try (Connection connection = Conexao.open();
        PreparedStatement command = connection.prepareStatement(sql);
             ResultSet result = command.executeQuery()) {

            while (result.next()) {
                Recado recado = new Recado(
                        result.getInt("id"),
                        result.getString("autor"),
                        result.getString("message")
                );
                recados.add(recado);
            }
        }
        return recados;
    }
}