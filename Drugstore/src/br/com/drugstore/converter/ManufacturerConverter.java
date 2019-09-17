package br.com.drugstore.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.drugstore.model.Fabricante;

@FacesConverter(value = "manufacturerConverter")
public class ManufacturerConverter implements Converter {

	// É executado quando selecionamos algum objeto na view do combo
	// Recebe o ID e retorna o objeto
	@Override
	public Object getAsObject(FacesContext context, UIComponent componente, String value) {
		// Preciso que o value seja um numero
		if (value == null || !value.matches("\\d+")) {
			return null; // É o padrão retornar null dele
		}
		//TENHO QUE NO MODEL IMPLEMENTAR SERIALIZABLE, SENÃO OS DADOS DO OBJETO CHEGAM NULL
		return this.getAttributesFrom(componente).get(value);  //Tenho retorno de um HashMap, com o get retorno a chave
		// FabricanteDAO dao = new FabricanteDAO();
		// Fabricante fabricante = dao.select(Long.parseLong(value));
		// return fabricante;
		
	}

	// É executado quando assim que a tela abre, tem como função montar o combo box
	// Recebe um objeto, e dentro do método tenho que retornar o ID do Objeto
	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object object) {
		if (object != null && !object.equals("")) {
			Fabricante fabricante = (Fabricante) object;
			if (fabricante.getId() != null) {

				//Add ao selectOneMenu, o fabricante
				this.addAttribute(componente, fabricante);
				return fabricante.getId().toString();
			}
		}
		return null;
	}
	
	//Como já faço uma busca no banco já no componente p listar os fabricantes, posso add atributos aos componentes, assim não preciso
	//de fazer 2 buscas.
	private Map<String, Object> getAttributesFrom(UIComponent component){
		return component.getAttributes(); //Pegando atributos do componente
	}
	
	//Add o atributo quando estou usando o getAsString
	private void addAttribute(UIComponent component, Fabricante fabricante) {
		this.getAttributesFrom(component).put(fabricante.getId().toString(), fabricante);
	}
}
