package com.nucleo.entity.cadastro.eNum;

import java.util.HashMap;
import java.util.Map;

public enum AtividadeEnum {

	NAOINFORMADO(0,"Não informado"),GERENCIAMENTO(1, "Gerenciamento"), APOIOTECNICO(2,
			"Apoio técnico"), PROJETO(3, "Projeto"), SUPERVISAO(
			4, "Supervisão");

	private int valor;
	private String nome;
	private static Map<Integer, AtividadeEnum> relations;
	
	AtividadeEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static AtividadeEnum getPorValor(int valor) {
		return relations.get(valor);
	}

	public static Map<Integer, String> getMap(){
		Map<Integer, String> ramoMap = new HashMap<Integer, String>();
		for (AtividadeEnum e : values())
			ramoMap.put(e.getValor(), e.getNome());
		
		return ramoMap;
	}
	
	static {
		relations = new HashMap<Integer, AtividadeEnum>();
		for (AtividadeEnum e : values())
			relations.put(e.getValor(), e);
	}

}
