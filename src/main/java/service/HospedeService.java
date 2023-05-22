package service;

import DAO.HospedeDAO;
import factory.ConnectionFactory;
import model.Hospede;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HospedeService {

    private final HospedeDAO hospedeDAO;

    public HospedeService() {
        hospedeDAO = new HospedeDAO();
    }

    public void save(Hospede hospede) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            hospedeDAO.cadastrar(conn, hospede);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Hospede> findAll() {
        return hospedeDAO.findAll();
    }

    public List<Hospede> findByAny(String value) {
        return hospedeDAO.findByAny(value);
    }

    public Hospede findById(Long id) {
        return hospedeDAO.findById(id);
    }

    public void update(Hospede hospede) {
        hospedeDAO.update(hospede);
    }

    public void delete(Long id) {
        hospedeDAO.delete(id);
    }
}
