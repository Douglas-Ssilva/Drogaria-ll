package br.com.drugstore.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesMyMessages {
	
	public static void addSuccessfully(String msg) {
		FacesMessage message= new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
		FacesContext context= FacesContext.getCurrentInstance(); 
		ExternalContext externalContext = context.getExternalContext();
		Flash flash = externalContext.getFlash(); //Memória temporária usada p trafegar dados durante a requisição
		flash.setKeepMessages(true); 				//como só dura por uma requisição, aqui falo p ela permanecer durante as requisições que ocorrerem
		context.addMessage(null, message);
	}
	public static void addError(String msg) {
		FacesMessage message= new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
		FacesContext context= FacesContext.getCurrentInstance(); 
		ExternalContext externalContext = context.getExternalContext();
		Flash flash = externalContext.getFlash(); //Memória temporária usada p trafegar dados durante a requisição
		flash.setKeepMessages(true); 				//como só dura por uma requisição, aqui falo p ela permanecer durante as requisições que ocorrerem
		context.addMessage(null, message);
	}

}
