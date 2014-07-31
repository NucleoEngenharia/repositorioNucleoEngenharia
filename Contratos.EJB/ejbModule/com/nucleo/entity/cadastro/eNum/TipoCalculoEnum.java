package com.nucleo.entity.cadastro.eNum;

import java.util.HashMap;
import java.util.Map;

public enum TipoCalculoEnum {
	SIMPLES(0, "Simples"), 
	POREQUIPE(1, "Calculo por Equipe (D/(Du*N)*S)"), 
	PORCOMPLEXIDADE(2, "Calculo por Complexidade (Db/Du*Sb+Dm/Du*Sm+Da/Du*As)");

	private int valor;
	private String nome;

	private static Map<Integer, TipoCalculoEnum> relations;

	TipoCalculoEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static TipoCalculoEnum getPorValor(int valor) {
		return relations.get(valor);
	}

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (TipoCalculoEnum e : values()){
			map.put(e.getValor(), e.getNome());
		}
		return map;
	}

	static {
		relations = new HashMap<Integer, TipoCalculoEnum>();
		for (TipoCalculoEnum e : values()){
			relations.put(e.getValor(), e);
		}
	}
}
