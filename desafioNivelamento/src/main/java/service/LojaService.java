package service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import model.Loja;
import persistence.CidadeDAO;
import persistence.LojaDAO;

public class LojaService {
	private HashMap<String, Loja> LojaMap;
	LojaDAO dao = new LojaDAO();
	CidadeDAO cidadeDAO = new CidadeDAO();
	public LojaService() {
		LojaMap = new HashMap<>();
	}

	public String inserirLoja(Loja loja) throws SQLException {	
			boolean camposValidos = true;
			String retorno;
			loja.setCnpj(somenteNumeros(loja.getCnpj()));
			retorno = verificaCNPJ(loja)==true?"":"CNPJ deve conter 14 digitos";
			loja.setTelefone(somenteNumeros(loja.getTelefone()));
			retorno += cidadeDAO.procuraCidade(loja.getCodigoCidade())==true?"":(retorno==""?"Esse código de cidade não existe":", Esse código de cidade não existe");
			if(retorno.equals("")) {
				LojaMap.put(Integer.toString(loja.getId()), loja);
				dao.inserir(loja);
			}			
			return retorno;	
	}
	
	
	
	public Collection<Loja> listarLojas(String codigoEstado, String codigoCidade) {
		LojaMap.clear();
		codigoEstado = codigoEstado != null ? codigoEstado : "0";
		codigoCidade = codigoCidade != null ? codigoCidade : "0";
		try {
			List<Loja> vetLoja;
			vetLoja = dao.listarLojas(Integer.parseInt(codigoEstado),Integer.parseInt(codigoCidade));
			for (int i = 0; i < vetLoja.size(); i++) {
				Loja aux = vetLoja.get(i);
				LojaMap.put(Integer.toString(aux.getId()), aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LojaMap.values();		
	}
	
	public Loja obterLoja(String id) {
		Loja loja = new Loja();
		try {
			loja = dao.obterLoja(Integer.parseInt(id));
			LojaMap.put(Integer.toString(loja.getId()), loja);
			System.out.println("Loja:" + loja.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return LojaMap.get(id);
	}
	
	public String excluirLoja(String id) throws NumberFormatException, Exception  {		
		   String retorno;
		   retorno = dao.verificaLoja(Integer.parseInt(id)) == true?"":"Esse identificador de loja não existe";
		   if(retorno.equals("")) {
				dao.excluir(Integer.parseInt(id));
				LojaMap.remove(id);	
				return retorno;
		   }else {
			   return retorno;
		   }
	}

	public String atualizarLoja(Loja loja) throws Exception {
		boolean camposValidos = true;
		String retorno;
		loja.setCnpj(somenteNumeros(loja.getCnpj()));
		retorno = dao.verificaLoja(loja.getId()) == true?"":"Esse identificador de loja não existe";
		if(retorno.equals("")) {
			retorno = verificaCNPJ(loja)==true?"":"CNPJ deve conter 14 digitos";
			loja.setTelefone(somenteNumeros(loja.getTelefone()));
			retorno += cidadeDAO.procuraCidade(loja.getCodigoCidade())==true?"":(retorno==""?"Esse código de cidade não existe":", Esse código de cidade não existe");
			if(retorno.equals("")) {
				LojaMap.put(Integer.toString(loja.getId()), loja);
				dao.atualizar(loja);
			}			
			return retorno;	
		}
		return retorno;	
		
	}
	
	private String somenteNumeros(String numero) {
	    StringBuffer sb = new StringBuffer();

	    char [] caracteres = numero.toCharArray();

	    for (Character caracter : caracteres) {
	        if (Character.isDigit(caracter)) {
	            sb.append(caracter);
	        }
	    }
	return sb.toString();
	}
	public boolean verificaJSON(Loja Loja) {
		boolean jsonValido = true;
		if(Loja.getCnpj() == null) jsonValido = false;
		if(Loja.getTelefone() == null) jsonValido = false;
		if(Loja.getEndereco() == null) jsonValido = false;
		if(Loja.getNome() == null) jsonValido = false;
		if(Loja.getCodigoCidade() == null) jsonValido = false;
		return jsonValido ;
	}
	private boolean verificaCNPJ(Loja Loja) {
		return Loja.getCnpj().length() == 14? true : false;
	}
	

}
