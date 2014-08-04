package com.nucleo.entity.cadastro.eNum;

import java.util.HashMap;
import java.util.Map;

public enum SetorEnum {
	
	NAOINFORMADO(0,"Não informado"),URBANISMOEEDIFICACOES(1, "Urbanismo e Edificações"), TRANSPORTE(2,"Transportes"),
	OLEOEGAS(3, "Petróleo e Gás"), 	SANEAMENTO(4,"Saneamento e meio ambiente"),
	ENERGIA(5, "Energia"), ADMINISTRATIVO(6,"Administrativo"), 
	PRIVADO(7,"Privado"), INFRAESTRUTURA(8,"Infraestrutura"), MINERACAO(9, "Mineração");
	
	
	private int valor;
	private String nome;
	private static Map<Integer, SetorEnum> relations;
	
	SetorEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static SetorEnum getPorValor(int valor) {
		return relations.get(valor);
	}

	public static Map<Integer, String> getMap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (SetorEnum e : values())
			map.put(e.getValor(), e.getNome());
		
		return map;
	}
	
	static {
		relations = new HashMap<Integer, SetorEnum>();
		for (SetorEnum e : values())
			relations.put(e.getValor(), e);
	}	
}
