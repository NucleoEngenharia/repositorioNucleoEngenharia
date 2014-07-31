package com.nucleo.entity.cadastro.controleDeAcessos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.nucleo.entity.CommomEntity;


@Entity
@SequenceGenerator(allocationSize=1,name = "SEQ_MENU", sequenceName = "SEQ_menu")
public class PermissoesMenu extends CommomEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_MENU")
	private int id =0;
	
	private String descricao;
	
	private String url;
	
	private int idPai;
	
	private String acesso = "Liberado";
	@ManyToOne
	private Grupo grupo;
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPai() {
		return idPai;
	}

	public void setIdPai(int idPai) {
		this.idPai = idPai;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
