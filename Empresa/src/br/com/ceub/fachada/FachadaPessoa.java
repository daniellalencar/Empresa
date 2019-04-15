package br.com.ceub.fachada;

import java.sql.SQLException;
import java.util.List;

import br.com.ceub.bo.Pessoa;
import br.com.ceub.dao.PessoaDAO;

public class FachadaPessoa {
	private PessoaDAO pessoaDAO;

	public List<Pessoa> consultaPorNome(String nome) {
		return  getPessoaDAO().consultaPorNome(nome);
	}

	public Integer insere(Pessoa pessoa) throws SQLException {
		return getPessoaDAO().insere(pessoa);
	}

	public void atualiza(Pessoa pessoa) throws SQLException {
		getPessoaDAO().atualiza(pessoa);
	}

	public Pessoa consulta(Integer id) {
		return getPessoaDAO().consulta(id);
	}

	public void remove(Integer id) throws SQLException {
		getPessoaDAO().remove(id);
	}

	protected PessoaDAO getPessoaDAO() {
		if (pessoaDAO == null) {
			pessoaDAO = new PessoaDAO();
		}
		return pessoaDAO;
	}

	protected void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	};

}
