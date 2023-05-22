package DAO;

import exceptions.DatabaseException;
import factory.ConnectionFactory;
import model.Hospede;
import views.Config;

import java.sql.*;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class HospedeDAO {

    private final String SQL_CREATE = "INSERT INTO hospede (nome, sobrenome, data_nascimento, nacionalidade, telefone) VALUES (?, ?, ?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM hospede";
    private final String SQL_FINDBYID = "SELECT * FROM hospede WHERE id = ?";
    private final String SQL_FINDBYANY = "SELECT * FROM hospede WHERE nome LIKE ? OR sobrenome LIKE ? OR nacionalidade LIKE ? OR telefone LIKE ? OR data_nascimento LIKE ? OR CONCAT(nome, ' ', sobrenome) LIKE ?";
    private final String SQL_UPDATE = "UPDATE hospede SET nome = ?, sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ? WHERE id = ?";
    private final String SQL_DELETE = "DELETE FROM hospede WHERE id = ?";

    public Hospede cadastrar(Connection conn, Hospede hospede) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {
            convertHospedeToPreparedStatement(hospede, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                hospede.setId(rs.getLong(1));
            }
        }
        return hospede;
    }

    public void update(Hospede hospede) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, hospede.getNome());
            ps.setString(2, hospede.getSobrenome());
            ps.setDate(3, new Date(hospede.getDataNascimento().getTime()));
            ps.setString(4, hospede.getNacionalidade());
            ps.setString(5, hospede.getTelefone());
            ps.setLong(6, hospede.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível atualizar o hospede.", e);
        }
    }

    public List<Hospede> findAll() {
        List<Hospede> hospedes = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SELECT)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                hospedes.add(convertResultSetToHospede(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível buscar os hospedes.", e);
        }
        return hospedes;
    }

    public Hospede findById(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FINDBYID)) {
            ps.setLong(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) return convertResultSetToHospede(rs);
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível encontrar o hospede.", e);
        }
        throw new NoSuchElementException("Hospede de id " + id + " não existe!");
    }

    public List<Hospede> findByAny(String value) {
        List<Hospede> hospedes = new LinkedList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FINDBYANY)) {
            ps.setString(1, "%" + value + "%");
            ps.setString(2, "%" + value + "%");
            ps.setString(3, "%" + value + "%");
            ps.setString(4, "%" + value + "%");
            try {
                Date data = new Date(Config.simpleDateFormat.parse(value).getTime());
                ps.setDate(5, data);
            } catch (ParseException e) {
                ps.setDate(5, null);
            }
            ps.setString(6, "%" + value + "%");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                hospedes.add(convertResultSetToHospede(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível buscar os hospedes.", e);
        }
        return hospedes;
    }

    public void delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_DELETE)) {
            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new NoSuchElementException("Hospede de id " + id + " não existe!");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DatabaseException("Não foi possível deletar o hospede: possui reservas.", e);
        } catch (SQLException e) {
            throw new DatabaseException("Não foi possível deletar o hospede.", e);
        }
    }

    private Hospede convertResultSetToHospede(ResultSet rs) throws SQLException {
        Long idHospede = rs.getLong(1);
        String nome = rs.getString(2);
        String sobrenome = rs.getString(3);
        Date dataNascimento = rs.getDate(4);
        String nacionalidade = rs.getString(5);
        String telefone = rs.getString(6);
        return new Hospede(idHospede, nome, sobrenome, dataNascimento, nacionalidade, telefone);
    }

    private void convertHospedeToPreparedStatement(Hospede hospede, PreparedStatement ps) throws SQLException {
        ps.setString(1, hospede.getNome());
        ps.setString(2, hospede.getSobrenome());
        ps.setDate(3, new Date(hospede.getDataNascimento().getTime()));
        ps.setString(4, hospede.getNacionalidade());
        ps.setString(5, hospede.getTelefone());
    }

}
