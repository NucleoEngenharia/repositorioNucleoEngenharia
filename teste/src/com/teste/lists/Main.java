package com.teste.lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teste.model.Modelo;

public class Main {
	public static void main(String[] args) {
	 Map<Integer, List<Modelo>>map = new HashMap<Integer, List<Modelo>>();
	 List<Modelo>list = new ArrayList<>();
	 Modelo pessoa = new Modelo();
	 pessoa.setId(1);
	 pessoa.setIdade(23);
	 pessoa.setNome("Raphael Bernoldi");
	 list.add(pessoa);
	 pessoa.setId(2);
	 pessoa.setIdade(23);
	 pessoa.setNome("Outro");
	 list.add(pessoa);
     map.put(1,list);
     
     //imprime velho
	}
	
	public void altera(){
		
	}
}
