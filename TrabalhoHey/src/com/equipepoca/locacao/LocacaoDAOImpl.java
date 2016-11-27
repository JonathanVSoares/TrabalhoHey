package com.equipepoca.locacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.equipepoca.cliente.Cliente;
import com.equipepoca.cliente.ClienteDAO;
import com.equipepoca.cliente.ClienteDAOImpl;
import com.equipepoca.connection.ConnectionFactory;
import com.mysql.jdbc.Statement;

public class LocacaoDAOImpl implements LocacaoDAO {

	private final static String STMT_INCLUIR = "INSERT INTO locacao(dias, valor, data, id_cliente) VALUES(?, ?, ?, ?)";
	private final static String STMT_EXCLUIR = "DELETE FROM locacao WHERE id=?";
	
	private final static String STMT_GET_BY_ID = "SELECT * FROM locacao WHERE id=?";

	public void incluir(Locacao locacao) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_INCLUIR, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, locacao.getDias());
			stmt.setDouble(2, locacao.getValor());
			stmt.setDate(3, (Date) locacao.getData().getTime());
			stmt.setInt(4, locacao.getCliente().getId());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			locacao.setId(rs.getInt(1));
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	public void excluir(Locacao locacao) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_EXCLUIR);
			stmt.setInt(1, locacao.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	@Override
	public Locacao getById(int id) {
		Connection con;
		PreparedStatement stmt;
		ResultSet rs;
		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_GET_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.next();

			ClienteDAO clienteDao = new ClienteDAOImpl();
			Cliente cliente = clienteDao.getById(rs.getInt("id_cliente"));
			
			Calendar cal =  new GregorianCalendar();
			cal.setTime(rs.getDate("data"));
			
			Locacao locacao = new Locacao(rs.getInt("dias"), rs.getDouble("valor"), cal, cliente);
			
			return locacao;
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}
}
