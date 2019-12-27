package br.edu.faculdadedelta.projetoprocedimentojs.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.projetoprocedimentojs.dao.ProcedimentoDAO;
import br.edu.faculdadedelta.projetoprocedimentojs.modelo.Procedimento;

@ManagedBean
@SessionScoped
public class ProcedimentoController {

	private Procedimento procedimento = new Procedimento();
	private ProcedimentoDAO dao = new ProcedimentoDAO();

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public void limparCampos() {
		procedimento = new Procedimento();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (procedimento.getId() == null) {
				dao.incluir(procedimento);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(procedimento);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, " + "tente novamente mais tarde! " + e.getMessage());
		}
		return "cadastroProcedimento.xhtml";
	}

	public String editar() {
		return "cadastroProcedimento.xhtml";
	}

	public String excluir() {
		try {
			dao.excluir(procedimento);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, " + "tente novamente mais tarde! " + e.getMessage());
		}
		return "listaProcedimento.xhtml";
	}

	public List<Procedimento> getListar() {
		List<Procedimento> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, " + "tente novamente mais tarde! " + e.getMessage());
		}
		return listaRetorno;
	}
}
