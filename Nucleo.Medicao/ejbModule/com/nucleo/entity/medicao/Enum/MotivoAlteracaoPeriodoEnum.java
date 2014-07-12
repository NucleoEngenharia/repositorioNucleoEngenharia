package com.nucleo.entity.medicao.Enum;

import java.util.HashMap;
import java.util.Map;

public enum MotivoAlteracaoPeriodoEnum {
	CLIENTE(0, "Cliente solicitou altera��o"), NUCLEO(1, "Erro de medi��o da N�cleo"), DOISMOTIVOS(2, "Erro da N�cleo e Cliente solicita altera��o");

	private int valor;
	private String nome;
	private static Map<Integer, MotivoAlteracaoPeriodoEnum> relations;

	MotivoAlteracaoPeriodoEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static MotivoAlteracaoPeriodoEnum getPorValor(int valor) {
		return relations.get(valor);
	}

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (MotivoAlteracaoPeriodoEnum e : values())
			map.put(e.getValor(), e.getNome());

		return map;
	}

	static {
		relations = new HashMap<Integer, MotivoAlteracaoPeriodoEnum>();
		for (MotivoAlteracaoPeriodoEnum e : values())
			relations.put(e.getValor(), e);
	}
}
