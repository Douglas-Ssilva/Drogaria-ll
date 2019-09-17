package br.com.drugstore.util;

import java.util.Map;

import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static String getParam(String p) {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String param = map.get(p);
		return param;
	}

}
