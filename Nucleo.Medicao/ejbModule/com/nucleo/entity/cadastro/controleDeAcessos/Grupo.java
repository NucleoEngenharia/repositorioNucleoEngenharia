package com.nucleo.entity.cadastro.controleDeAcessos;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.nucleo.entity.CommomEntity;
@Entity
@SequenceGenerator(allocationSize=1,name="seq_grupo", sequenceName="SEQ_GRUPO")
public class Grupo extends CommomEntity {
	public Grupo() {
		menus = new HashSet<PermissoesMenu>();
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_grupo")
	private int id;
	
	private String nome;
	
	@OneToMany(mappedBy="grupo", targetEntity=PermissoesMenu.class, cascade={CascadeType.ALL,CascadeType.PERSIST, CascadeType.MERGE})
	@Fetch(FetchMode.JOIN)
	private Set<PermissoesMenu>menus;
	
	
	public Set<PermissoesMenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<PermissoesMenu> menus) {
		this.menus = menus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	}
	
