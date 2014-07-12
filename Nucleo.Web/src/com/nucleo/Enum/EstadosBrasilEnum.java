package com.nucleo.Enum;

import java.util.HashMap;
import java.util.Map;

public enum EstadosBrasilEnum {
	AC("AC", "Acre"),
	AL("AL", "Alagoas"), 
	AP("AP","Amap�"), // Amap�
	AM("AM","Amazonas"), // Amazonas
	BA("BA","Bahia"), // Bahia
	CE("CE","Cear�"), // Cear�
	DF("DF","Distrito Federal"), // Distrito Federal
	ES("ES","Esp�rito Santo"), // Esp�rito Santo
	GO("GO","Goi�s"), // Goi�s
	MA("MA","Maranh�o"), // Maranh�o
	MT("MT","Mato Grosso"), // Mato Grosso
	MS("MS","Mato Grosso do Sul"), // Mato Grosso do Sul
	MG("MG","Minas Gerais"), // Minas Gerais
	PA("PA","Par�"), // Par�
	PB("PB","Para�ba"), // Para�ba
	PR("PR","Paran�"), // Paran�
	PE("PE","Pernambuco"), // Pernambuco
	PI("PI","Piau�"), // Piau�
	RR("RR","Roraima"), // Roraima
	RO("RO","Rond�nia"), // Rond�nia
	RJ("RJ","Rio de Janeiro"), // Rio de Janeiro
	RN("RN","Rio Grande do Norte"), // Rio Grande do Norte
	RS("RS","Rio Grande do Sul"), // Rio Grande do Sul
	SC("SC","Santa Catarina"), // Santa Catarina
	SP("SP","S�o Paulo"), // S�o Paulo
	SE("SE","Sergipe"), // Sergipe
	TO("TO","Tocantins");  // Tocantins
	
	private String valor;
	private String nome;
	private static Map<String, EstadosBrasilEnum> relations;
	
	EstadosBrasilEnum(String valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public String getNome() {
		return nome;
	}

	public static EstadosBrasilEnum getPorValor(int valor) {
		return relations.get(valor);
	}
	
	static {
		relations = new HashMap<String, EstadosBrasilEnum>();
		for (EstadosBrasilEnum e : values())
			relations.put(e.getValor(), e);
	}	
	
}
