package com.nucleo.entity.medicao.Enum;

import java.util.HashMap;
import java.util.Map;

public enum StatusPeriodoEnum {

	EMABERTO(0, "Em Aberto"), PENDENTEVALIDACAO(1, "Pendente Validação"), 
	PENDENTEAPROVACAO(2, "Pendente Aprovação"), APROVADO(3, "Aprovado");

	private int valor;
	private String nome;
	private static Map<Integer, StatusPeriodoEnum> relations;

	StatusPeriodoEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static StatusPeriodoEnum getPorValor(int valor) {
		return relations.get(valor);
	}

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (StatusPeriodoEnum e : values())
			map.put(e.getValor(), e.getNome());

		return map;
	}

	static {
		relations = new HashMap<Integer, StatusPeriodoEnum>();
		for (StatusPeriodoEnum e : values())
			relations.put(e.getValor(), e);
	}
}
