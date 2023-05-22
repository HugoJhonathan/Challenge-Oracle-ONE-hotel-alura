package DAO;

import exceptions.DatabaseException;
import exceptions.MinhaException;
import factory.ConnectionFactory;
import model.Hospede;
import model.MetodoPagamento;
import model.Reserva;
import views.Config;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReservaDAO {

    private final String SQL_CREATE = "INSERT INTO reserva (data_entrada, data_saida, valor, forma_pagamento, hospede_id) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM reserva inner JOIN hospede ON reserva.hospede_id  = hospede.id;";
    private final String SQL_FINDBYID = "SELECT * FROM reserva WHERE id = ?";
    private final String SQL_FINDBYANY = "SELECT * FROM reserva INNER JOIN hospede ON reserva.hospede_id = hospede.id WHERE data_entrada LIKE ? OR data_saida LIKE ? OR valor = ? OR forma_pagamento = ? OR CONCAT(nome, ' ', sobrenome) LIKE ?";
    private final String SQL_UPDATE = "UPDATE reserva SET data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento = ? WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM reserva WHERE id = ?";

    public Reserva save(Connection conn, Reserva reserva) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new Date(reserva.getDataEntrada().getTime()));
            ps.setDate(2, new Date(reserva.getDataSaida().getTime()));
            ps.setBigDecimal(3, reserva.getValor());
            ps.setInt(4, reserva.getFormaPagamento().getId());
            ps.setLong(5, reserva.getHospede().getId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                reserva.setId(rs.getLong(1));
            }
        }
        return reserva;
    }

    public List<Reserva> findAll() {
        List<Reserva> reservas = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SELECT)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                reservas.add(convertResultSetToReservaAndHospede(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Não foi possível buscar as reservas.", e);
        }
        return reservas;
    }

    public Reserva findById(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FINDBYID)) {
            ps.setLong(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) return convertResultSetToReserva(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Não foi possível buscar a reserva.", e);
        }
        throw new NoSuchElementException("Reserva de id " + id + " não existe!");
    }

    public List<Reserva> findByAny(String value) {
        List<Reserva> reservas = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FINDBYANY)) {
            try {
                Date data = new Date(Config.simpleDateFormat.parse(value).getTime());
                ps.setDate(1, data);
                ps.setDate(2, data);
            } catch (ParseException e) {
                ps.setDate(1, null);
                ps.setDate(2, null);
            }
            try {
                ps.setBigDecimal(3, new BigDecimal(value));
            } catch (NumberFormatException e) {
                ps.setBigDecimal(3, null);
            }
            try {
                Integer id = MetodoPagamento.findByName(value).getId();
                ps.setInt(4, id);
            } catch (MinhaException e) {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            ps.setString(5, "%" + value + "%");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                reservas.add(convertResultSetToReservaAndHospede(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível buscar as reservas.", e);
        }
        return reservas;
    }

    public void update(Reserva reserva) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, new Date(reserva.getDataEntrada().getTime()));
            ps.setDate(2, new Date(reserva.getDataSaida().getTime()));
            ps.setBigDecimal(3, reserva.getValor());
            ps.setInt(4, reserva.getFormaPagamento().getId());
            ps.setLong(5, reserva.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new NoSuchElementException("Reserva de id " + reserva.getId() + " não existe!");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível atualizar a reserva.", e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new NoSuchElementException("Reserva de id " + id + " não existe!");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível deletar a reserva.", e);
        }
    }

    private Reserva convertResultSetToReservaAndHospede(ResultSet rs) throws SQLException {
        Long idBanco = rs.getLong(1);
        Date data_entrada = rs.getDate(2);
        Date data_saida = rs.getDate(3);
        BigDecimal valor = rs.getBigDecimal(4);
        int formaPagamentoId = rs.getInt(5);

        Long idHospede = rs.getLong(7);
        String nomeHospede = rs.getString(8);
        String sobrenomeHospede = rs.getString(9);
        Date dataNascimento = rs.getDate(10);
        String nacionalidadeHospede = rs.getString(11);
        String telefoneHospede = rs.getString(12);
        Hospede hospede = new Hospede(idHospede, nomeHospede, sobrenomeHospede, dataNascimento, nacionalidadeHospede, telefoneHospede);
        Reserva reserva = new Reserva(idBanco,
                new java.util.Date(data_entrada.getTime()),
                new java.util.Date(data_saida.getTime()),
                valor, MetodoPagamento.findById(formaPagamentoId));
        reserva.setHospede(hospede);
        return reserva;
    }

    private Reserva convertResultSetToReserva(ResultSet rs) throws SQLException {
        Long idBanco = rs.getLong(1);
        Date data_entrada = rs.getDate(2);
        Date data_saida = rs.getDate(3);
        BigDecimal valor = rs.getBigDecimal(4);
        int formaPagamentoId = rs.getInt(5);

        Reserva reserva = new Reserva(idBanco,
                new java.util.Date(data_entrada.getTime()),
                new java.util.Date(data_saida.getTime()),
                valor, MetodoPagamento.findById(formaPagamentoId));

        return reserva;
    }

}
