package service;

import DAO.UsuarioDAO;
import exceptions.LoginException;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
    }

    public boolean autenticar(String login, String senha) throws LoginException {
        return usuarioDAO.autenticar(login, senha);
    }

}
