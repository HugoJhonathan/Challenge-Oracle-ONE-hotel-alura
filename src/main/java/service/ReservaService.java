package service;

import DAO.HospedeDAO;
import DAO.ReservaDAO;
import exceptions.DatabaseException;
import exceptions.MinhaException;
import factory.ConnectionFactory;
import model.Reserva;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class ReservaService {

    private static final BigDecimal valorDiaria = new BigDecimal("250");

    private final ReservaDAO reservaDAO;
    private final HospedeDAO hospedeDAO;

    public ReservaService() {
        reservaDAO = new ReservaDAO();
        hospedeDAO = new HospedeDAO();
    }

    public void save(Reserva reserva) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false);
            try {
                hospedeDAO.cadastrar(conn, reserva.getHospede());
                reservaDAO.save(conn, reserva);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                throw new DatabaseException("Erro, rollback executado", e);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível cadastrar a reserva.", e);
        }
    }

    public List<Reserva> findAll() {
        return reservaDAO.findAll();
    }

    public Reserva findById(Long id) throws MinhaException, NoSuchElementException {
        return reservaDAO.findById(id);
    }

    public List<Reserva> findByAny(String value) {
        return reservaDAO.findByAny(value);
    }

    public void update(Reserva reserva) {
        reservaDAO.update(reserva);
    }

    public void delete(Long id) {
        reservaDAO.delete(id);
    }

    public static BigDecimal calcularValorDiaria(Date dataInicio, Date DataSaida) {
        if (DataSaida.compareTo(dataInicio) < 1) {
            throw new MinhaException("A data de checkin não pode ser maior que a de checkout!");
        }
        long differ = DataSaida.getTime() - dataInicio.getTime();
        int differDays = (int) (differ / (1000 * 60 * 60 * 24));
        if (differDays == 0) {
            throw new MinhaException("Mínimo 1 dia!");
        }
        return valorDiaria.multiply(new BigDecimal(differDays));
    }

}