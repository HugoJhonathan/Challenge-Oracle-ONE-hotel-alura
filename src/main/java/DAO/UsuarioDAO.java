package DAO;

import exceptions.DatabaseException;
import exceptions.LoginException;
import exceptions.LoginInvalidLoginException;
import exceptions.LoginInvalidPasswordException;
import factory.ConnectionFactory;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean autenticar(String login, String senha) throws LoginException {
        String sql = "SELECT * FROM usuario WHERE login = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String password = rs.getString("senha");
                if (BCrypt.checkpw(senha, password)) {
                    return true;
                }
                throw new LoginInvalidPasswordException("Senha inválida!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Erro ao tentar fazer login", e);
        }
        throw new LoginInvalidLoginException("Usuário não encontrado!");
    }

}