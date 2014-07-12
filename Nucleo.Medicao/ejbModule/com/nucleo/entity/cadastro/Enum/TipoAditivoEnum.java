package com.nucleo.entity.cadastro.Enum;

public enum TipoAditivoEnum {
	PRAZO(0, "Prazo"), PRECO(1, "Preço"), OBSERVACAO(2, "Observação");
	
	private int valor;
	private String nome;
	
	TipoAditivoEnum(int valor, String nome) {
		this.valor = valor;
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}
	public String getNome() {
		return nome;
	}
}
