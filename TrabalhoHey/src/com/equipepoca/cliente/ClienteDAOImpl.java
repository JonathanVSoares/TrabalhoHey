package com.equipepoca.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.equipepoca.connection.ConnectionFactory;
import com.mysql.jdbc.Statement;

public class ClienteDAOImpl implements ClienteDAO {

	private final static String STMT_INCLUIR = "INSERT INTO cliente(nome, sobrenome, rg, cpf, endereco) VALUES(?, ?, ?, ?, ?)";
	private final static String STMT_UPDATE = "UPDATE cliente SET nome=?, sobrenome=?, rg=?, cpf=?, endereco=? WHERE id=?";
	private final static String STMT_EXCLUIR = "DELETE FROM cliente WHERE id=?";

	private final static String STMT_LISTAR = "SELECT * FROM cliente";
	private final static String STMT_GET_BY_ID = "SELECT * FROM cliente WHERE id=?";

	public void incluir(Cliente cliente) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_INCLUIR, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSobreNome());
			stmt.setString(3, cliente.getRg());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getEndereco());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			cliente.setId(rs.getInt(1));
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	public void atualizar(Cliente cliente) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_UPDATE);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getSobreNome());
			stmt.setString(3, cliente.getRg());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getEndereco());
			stmt.setInt(6, cliente.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	public void excluir(Cliente cliente) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_EXCLUIR);
			stmt.setInt(1, cliente.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	public List<Cliente> listarClientes() {
		Connection con;
		PreparedStatement stmt;
		ResultSet rs;
		List<Cliente> lista = new ArrayList<>();
		
		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_LISTAR);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"),
						rs.getString("rg"), rs.getString("cpf"), rs.getString("endereco"));
				lista.add(cliente);
			}
			return lista;
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	@Override
	public Cliente getById(int id) {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = con.prepareStatement(STMT_GET_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			Cliente cliente = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"),
					rs.getString("rg"), rs.getString("cpf"), rs.getString("endereco"));

			return cliente;
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}
}
