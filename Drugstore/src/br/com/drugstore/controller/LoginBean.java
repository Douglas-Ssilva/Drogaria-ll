package br.com.drugstore.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drugstore.dao.FuncionarioDAO;
import br.com.drugstore.model.Funcionario;
import br.com.drugstore.util.FacesMyMessages;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;

	public String logar() {
		System.out.println("Método logar");
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionario = dao.authenticate(funcionario.getCpf(), DigestUtils.md5Hex(funcionario.getSenha()));
			
			if (funcionario != null) {
					FacesContext context= FacesContext.getCurrentInstance(); 	//INFO DO AMBIENTE DE EXECUÇÃO
					ExternalContext context2= context.getExternalContext();
					context2.getSessionMap().put("user", funcionario);
					FacesMyMessages.addSuccessfully("Logged successfully!");
					System.out.println("logou");
				return "/pages/main.xhtml?faces-redirect=true";
			} else {
				FacesMyMessages.addError("User or password invalid(s)!");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String logout() {
		// funcionario= null; Também funcionaria
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/login.xhtml?faces-redirect=true"; // Fica na mesma página
	}

	public Funcionario getFuncionario() {
		if (funcionario == null) {
			funcionario = new Funcionario();
		}
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
