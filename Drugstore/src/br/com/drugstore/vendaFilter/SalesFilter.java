package br.com.drugstore.vendaFilter;

import java.util.Date;

public class SalesFilter {
	//Coloco os atributos pelos quais quero fazer o filtro, assim não preciso de colocar todos os critérios no param
	private Date dataInicial;
	private Date dataFinal;
	
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
}
