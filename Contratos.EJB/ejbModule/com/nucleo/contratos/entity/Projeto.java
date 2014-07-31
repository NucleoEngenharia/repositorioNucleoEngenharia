package com.nucleo.contratos.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nucleo.entity.cadastro.Enum.AtividadeEnum;
import com.nucleo.entity.cadastro.Enum.SetorEnum;
import com.nucleo.entity.cadastro.Enum.StatusProjetoEnum;
@Entity
@SequenceGenerator(allocationSize=1, name="seqProjeto",sequenceName="SEQ_GENERATOR")
public class Projeto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="seqProjeto")
	private int id;
	
	private String descricao;
	
	private String cn;
	
	@Temporal(TemporalType.DATE)
	private Calendar dtInicio;
	
	@Temporal(TemporalType.DATE)
	private Calendar dtFim;
	
	private StatusProjetoEnum status = StatusProjetoEnum.ATIVO;
	
	private SetorEnum setor;
	
	private AtividadeEnum atividade;

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCn() {
		return cn;
	}

	public Calendar getDtInicio() {
		return dtInicio;
	}

	public Calendar getDtFim() {
		return dtFim;
	}

	public StatusProjetoEnum getStatus() {
		return status;
	}

	public SetorEnum getSetor() {
		return setor;
	}

	public AtividadeEnum getAtividade() {
		return atividade;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public void setDtInicio(Calendar dtInicio) {
		this.dtInicio = dtInicio;
	}

	public void setDtFim(Calendar dtFim) {
		this.dtFim = dtFim;
	}

	public void setStatus(StatusProjetoEnum status) {
		this.status = status;
	}

	public void setSetor(SetorEnum setor) {
		this.setor = setor;
	}

	public void setAtividade(AtividadeEnum atividade) {
		this.atividade = atividade;
	}
	
	
	
}
