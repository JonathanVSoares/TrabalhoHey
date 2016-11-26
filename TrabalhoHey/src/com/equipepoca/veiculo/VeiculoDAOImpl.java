package com.equipepoca.veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.equipepoca.connection.ConnectionFactory;
import com.mysql.jdbc.Statement;

public class VeiculoDAOImpl {

	private static final String STMT_INCLUIR = "INSERT INTO cliente(marca, estado, categoria, valor_compra, placa, ano) VALUES(?, ?, ?, ?, ?, ?)";
	
	public void incluir(Veiculo veiculo) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_INCLUIR, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, veiculo.getMarca().name());
			stmt.setString(2, veiculo.getEstado().name());
			stmt.setString(3, veiculo.getCategoria().name());
			stmt.setDouble(4, veiculo.getValorParaVenda());
			stmt.setString(5, veiculo.getPlaca());
			stmt.setInt(6, veiculo.getAno());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			veiculo.setId(rs.getInt(1));
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}
}
