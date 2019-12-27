package br.edu.faculdadedelta.projetoprocedimentojs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetoprocedimentojs.modelo.Procedimento;
import br.edu.faculdadedelta.projetoprocedimentojs.util.Conexao;

public class ProcedimentoDAO {

	public void incluir(Procedimento procedimento) 
			throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO procedimentos (paciente,valor_procedimento, "
				+ "data_inicio_procedimento, data_fim_procedimento, "
				+ "quantidade_exame_procedimento) VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, procedimento.getPaciente().trim());
			ps.setDouble(2, procedimento.getValor());
			ps.setDate(3, new java.sql.Date(procedimento.getDataInicio().getTime()));
			ps.setDate(4, new java.sql.Date(procedimento.getDataFim().getTime()));
			ps.setInt(5, procedimento.getQuantidade());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(Procedimento procedimento) 
			throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE procedimentos "
				+ " SET paciente = ?, "
				+ " valor_procedimento = ?, "
				+ " data_inicio_procedimento = ?, "
				+ " data_fim_procedimento = ?, "
				+ " quantidade_exame_procedimento = ? "
				+ " WHERE id_procedimento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, procedimento.getPaciente().trim());
			ps.setDouble(2, procedimento.getValor());
			ps.setDate(3, new java.sql.Date(procedimento.getDataInicio().getTime()));
			ps.setDate(4, new java.sql.Date(procedimento.getDataFim().getTime()));
			ps.setInt(5, procedimento.getQuantidade());
			ps.setLong(6, procedimento.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(Procedimento procedimento) 
			throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM procedimentos WHERE id_procedimento = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, procedimento.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public List<Procedimento> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_procedimento, paciente, valor_procedimento, "
				      + "data_inicio_procedimento, " + 
				      " data_fim_procedimento, quantidade_exame_procedimento" + 
				      " FROM procedimentos";
		PreparedStatement ps = conn.prepareStatement(sql);
		List<Procedimento> listaRetorno = new ArrayList<>();
		ResultSet rs = null;
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Procedimento procedimento = new Procedimento();
				procedimento.setId(rs.getLong("id_procedimento"));
				procedimento.setPaciente(rs.getString("paciente").trim());
				procedimento.setValor(rs.getDouble("valor_procedimento"));
				procedimento.setDataInicio(rs.getDate("data_inicio_procedimento"));
				procedimento.setDataFim(rs.getDate("data_fim_procedimento"));
				procedimento.setQuantidade(rs.getInt("quantidade_exame_procedimento"));
				listaRetorno.add(procedimento);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
}







