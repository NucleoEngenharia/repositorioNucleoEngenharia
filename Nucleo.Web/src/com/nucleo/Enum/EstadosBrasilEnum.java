package com.nucleo.Enum;

import java.util.HashMap;
import java.util.Map;

public enum EstadosBrasilEnum {
	AC("AC", "Acre"),
	AL("AL", "Alagoas"), 
	AP("AP","Amapá"), // Amapá
	AM("AM","Amazonas"), // Amazonas
	BA("BA","Bahia"), // Bahia
	CE("CE","Ceará"), // Ceará
	DF("DF","Distrito Federal"), // Distrito Federal
	ES("ES","Espírito Santo"), // Espírito Santo
	GO("GO","Goiás"), // Goiás
	MA("MA","Maranhão"), // Maranhão
	MT("MT","Mato Grosso"), // Mato Grosso
	MS("MS","Mato Grosso do Sul"), // Mato Grosso do Sul
	MG("MG","Minas Gerais"), // Minas Gerais
	PA("PA","Pará"), // Pará
	PB("PB","Paraíba"), // Paraíba
	PR("PR","Paraná"), // Paraná
	PE("PE","Pernambuco"), // Pernambuco
	PI("PI","Piauí"), // Piauí
	RR("RR","Roraima"), // Roraima
	RO("RO","Rondônia"), // Rondônia
	RJ("RJ","Rio de Janeiro"), // Rio de Janeiro
	RN("RN","Rio Grande do Norte"), // Rio Grande do Norte
	RS("RS","Rio Grande do Sul"), // Rio Grande do Sul
	SC("SC","Santa Catarina"), // Santa Catarina
	SP("SP","São Paulo"), // São Paulo
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
