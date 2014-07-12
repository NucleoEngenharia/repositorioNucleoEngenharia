package com.nucleo.entity.cadastro.controleDeAcessos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.nucleo.entity.CommomEntity;
import com.nucleo.entity.cadastro.Projeto;

@Entity
@SequenceGenerator(allocationSize=1,name = "SEQ_ACESSOS", sequenceName = "SEQ_ACESSOS")
public class AcessosUsuario extends CommomEntity {
	private static final long serialVersionUID = 1L;
	@PostConstruct
	public void init(){
		projetosAcessiveis = new ArrayList<Projeto>();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ACESSOS")
	private int id =0;
	
	private int id_usuario;
	
	private boolean administrador = false;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Set<Grupo>grupos;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<Projeto>projetosAcessiveis;
	
	public List<Projeto> getProjetosAcessiveis() {
		return projetosAcessiveis;
	}
	public void setProjetosAcessiveis(List<Projeto> projetosAcessiveis) {
		this.projetosAcessiveis =projetosAcessiveis;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public Set<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
}
