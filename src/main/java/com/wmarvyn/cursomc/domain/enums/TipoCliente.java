package com.wmarvyn.cursomc.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa juridica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
	 return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) throws IllegalAccessException {
		if (cod ==null) {
			return null;		}

	
	for(TipoCliente x : TipoCliente.values()) {
		if (cod.equals(x.getCod())) {
			return x;
		}
	} 
	throw new IllegalAccessException("Id invalido" + cod);
	}
}
