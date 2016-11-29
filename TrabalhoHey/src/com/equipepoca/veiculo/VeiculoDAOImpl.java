package com.equipepoca.veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.equipepoca.connection.ConnectionFactory;
import com.equipepoca.locacao.Locacao;
import com.equipepoca.locacao.LocacaoDAO;
import com.equipepoca.locacao.LocacaoDAOImpl;
import com.mysql.jdbc.Statement;

public class VeiculoDAOImpl implements VeiculoDAO {

	private static final String STMT_INCLUIR = "INSERT INTO veiculo(tipo_veiculo, marca, estado, categoria, modelo, valor_compra, placa, ano) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String STMT_LOCAR = "UPDATE veiculo SET locacao_id=?, estado=? WHERE id=?";
	private static final String STMT_DEVOLVER = "UPDATE veiculo SET locacao_id=NULL, estado=? WHERE id=?";
	private static final String STMT_VENDER = "UPDATE veiculo SET estado=? WHERE id=?";

	private static final String STMT_LISTAR_POR_ESTADO = "SELECT * FROM veiculo WHERE estado=?";

	public void incluir(Veiculo veiculo) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_INCLUIR, Statement.RETURN_GENERATED_KEYS);
			
			String tipoVeiculo = null;
			if (veiculo instanceof Automovel)
				tipoVeiculo = TipoVeiculo.AUTOMOVEL.toString();
			else if(veiculo instanceof Motocicleta)
				tipoVeiculo = TipoVeiculo.MOTOCICLETA.toString();
			else if(veiculo instanceof Van)
				tipoVeiculo = TipoVeiculo.VAN.toString();
				

			stmt.setString(1, tipoVeiculo);
			stmt.setString(2, veiculo.getMarca().toString());
			stmt.setString(3, veiculo.getEstado().toString());
			stmt.setString(4, veiculo.getCategoria().toString());
			stmt.setString(5, veiculo.getModelo().toString());
			stmt.setDouble(6, veiculo.getValorParaVenda());
			stmt.setString(7, veiculo.getPlaca());
			stmt.setInt(8, veiculo.getAno());
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			veiculo.setId(rs.getInt(1));
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	@Override
	public void gravarLocacao(Veiculo veiculo) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_LOCAR);
			stmt.setInt(1, veiculo.getLocacao().getId());
			stmt.setString(2, veiculo.getEstado().toString());
			stmt.setInt(3, veiculo.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	@Override
	public void devolver(Veiculo veiculo) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_DEVOLVER);
			stmt.setString(1, veiculo.getEstado().toString());
			stmt.setInt(2, veiculo.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	@Override
	public void vender(Veiculo veiculo) {
		Connection con;
		PreparedStatement stmt;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(STMT_VENDER);
			stmt.setString(1, veiculo.getEstado().toString());
			stmt.setInt(2, veiculo.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	@Override
	public List<Veiculo> listarLocados() {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = con.prepareStatement(STMT_LISTAR_POR_ESTADO);
			stmt.setString(1, Estado.LOCADO.toString());
			ResultSet rs = stmt.executeQuery();

			return createListaVeiculo(rs);
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	@Override
	public List<Veiculo> listarDisponiveis() {
		try {
			Connection con = ConnectionFactory.getConnection();
			PreparedStatement stmt = con.prepareStatement(STMT_LISTAR_POR_ESTADO);
			stmt.setString(1, Estado.DISPONIVEL.toString());
			ResultSet rs = stmt.executeQuery();

			return createListaVeiculo(rs);
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}

	private List<Veiculo> createListaVeiculo(ResultSet rs) {
		try {
			List<Veiculo> lista = new ArrayList<>();

			while (rs.next()) {
				Locacao locacao = null;
				
				int idLocacao = rs.getInt("locacao_id");
				if (idLocacao != 0){
					LocacaoDAO locacaoDao = new LocacaoDAOImpl();
					locacao = locacaoDao.getById(idLocacao);
				}

				Veiculo veiculo = null;

				String tipoVeiculo = rs.getString("tipo_veiculo");

				if (tipoVeiculo.equals(TipoVeiculo.AUTOMOVEL.toString()))
					veiculo = new Automovel(Marca.valueOf(rs.getString("marca")),
							Estado.valueOf(rs.getString("estado")), locacao,
							Categoria.valueOf(rs.getString("categoria")), rs.getDouble("valor_compra"),
							rs.getString("placa"), rs.getInt("ano"), ModeloAutomovel.valueOf(rs.getString("modelo")));
				else if (tipoVeiculo.equals(TipoVeiculo.MOTOCICLETA.toString()))
					veiculo = new Motocicleta(Marca.valueOf(rs.getString("marca")),
							Estado.valueOf(rs.getString("estado")), locacao,
							Categoria.valueOf(rs.getString("categoria")), rs.getDouble("valor_compra"),
							rs.getString("placa"), rs.getInt("ano"), ModeloMotocicleta.valueOf(rs.getString("modelo")));
				else if (tipoVeiculo.equals(TipoVeiculo.VAN.toString()))
					veiculo = new Van(Marca.valueOf(rs.getString("marca")), Estado.valueOf(rs.getString("estado")),
							locacao, Categoria.valueOf(rs.getString("categoria")), rs.getDouble("valor_compra"),
							rs.getString("placa"), rs.getInt("ano"), ModeloVan.valueOf(rs.getString("modelo")));

				veiculo.setId(rs.getInt("id"));
				lista.add(veiculo);
			}
			return lista;
		} catch (SQLException ex) {
			throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
		}
	}
}
