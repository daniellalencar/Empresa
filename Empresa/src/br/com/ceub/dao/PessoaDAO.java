package br.com.ceub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.jdbc4.Jdbc4Connection;

import br.com.ceub.bo.Pessoa;

public class PessoaDAO extends EmpresaDAO {

	public Integer insere(Pessoa pessoa) throws SQLException {
		String sql = "INSERT INTO public.pessoa(" + "            nome, telefone, endereco)"
				+ "    VALUES (?, ?, ?);";
		Integer retorno = null;
		int i = -1;
		try {
			// String sql = "INSERT INTO public.log( " + " linha, mensagem_erro) " + "
			// VALUES ( ?, ?);";

			Jdbc4Connection conexaoPostGres = Conexao.getCONEXAOPostGres();
			PreparedStatement ps = conexaoPostGres.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getTelefone());
			ps.setString(3, pessoa.getEndereco());

			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				retorno = generatedKeys.getInt(1);
			}
			generatedKeys.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		// return log;
		return retorno;
	}

	public void atualiza(Pessoa pessoa) throws SQLException {
		String sql = "UPDATE public.pessoa " + "   SET  nome=?, telefone=?, endereco=? " + " WHERE id = ?";
		Integer retorno = null;
		int i = -1;
		try {
			// String sql = "INSERT INTO public.log( " + " linha, mensagem_erro) " + "
			// VALUES ( ?, ?);";

			Jdbc4Connection conexaoPostGres = Conexao.getCONEXAOPostGres();
			PreparedStatement ps = conexaoPostGres.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getTelefone());
			ps.setString(3, pessoa.getEndereco());
			ps.setInt(4, pessoa.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Pessoa consulta(Integer id) {
		String sql = "SELECT id, nome, telefone, endereco " + "  FROM public.pessoa where id=?";
		Pessoa pessoa = null;
		int i = -1;
		try {
			// String sql = "SELECT id, linha, mensagem_erro FROM public.log where linha=?";

			PreparedStatement ps = Conexao.getCONEXAOPostGres().prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pessoa = new Pessoa();
				pessoa.setId(rs.getInt(1));
				pessoa.setNome(rs.getString(2));
				pessoa.setTelefone(rs.getString(3));
				pessoa.setEndereco(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoa;
	}
	
	public List<Pessoa> consultaPorNome(String nome) {
		String sql = "SELECT id, nome, telefone, endereco " + "  FROM public.pessoa where nome  like %"+nome+"%";
		if(nome==null || nome.equals("")) {
			sql = "SELECT id, nome, telefone, endereco " + "  FROM public.pessoa";
		}
		List<Pessoa> pessoaRet = new ArrayList<Pessoa>();
		int i = -1;
		try {
			// String sql = "SELECT id, linha, mensagem_erro FROM public.log where linha=?";

			PreparedStatement ps = Conexao.getCONEXAOPostGres().prepareStatement(sql);
//			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt(1));
				pessoa.setNome(rs.getString(2));
				pessoa.setTelefone(rs.getString(3));
				pessoa.setEndereco(rs.getString(4));
				pessoaRet.add(pessoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoaRet;
	}

	public void remove(Integer id) throws SQLException {
		String sql = "DELETE FROM public.pessoa " + " WHERE id = ?";
		// String sql = "UPDATE public.pessoa " + " SET nome=?, telefone=?, endereco=? "
		// + " WHERE id = ?";
		Integer retorno = null;
		int i = -1;
		try {
			// String sql = "INSERT INTO public.log( " + " linha, mensagem_erro) " + "
			// VALUES ( ?, ?);";

			Jdbc4Connection conexaoPostGres = Conexao.getCONEXAOPostGres();
			PreparedStatement ps = conexaoPostGres.prepareStatement(sql);
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
