package com.edipo.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity(name = "status_servico_historico")
public class StatusServicoHistorico {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Column(nullable = false, length = 25)
	private String estado;
	@Column(length = 25)
	private String statusAutorizacao;
	@Column(length = 25)
	private String statusRetornoAutorizacao;
	@Column(length = 25)
	private String statusInutilizacao;
	@Column(length = 25)
	private String statusConsultaProtocolo;
	@Column(length = 25)
	private String statusServico;
	@Column(length = 25)
	private String statusConsultaCadastro;
	@Column(length = 25)
	private String statusRecepcaoEvento;
	
	public StatusServicoHistorico() {}

	public StatusServicoHistorico(Long id, Date data, String estado, String statusAutorizacao,
			String statusRetornoAutorizacao, String statusInutilizacao, String statusConsultaProtocolo,
			String statusServico, String statusConsultaCadastro, String statusRecepcaoEvento) {
		super();
		this.id = id;
		this.data = data;
		this.estado = estado;
		this.statusAutorizacao = statusAutorizacao;
		this.statusRetornoAutorizacao = statusRetornoAutorizacao;
		this.statusInutilizacao = statusInutilizacao;
		this.statusConsultaProtocolo = statusConsultaProtocolo;
		this.statusServico = statusServico;
		this.statusConsultaCadastro = statusConsultaCadastro;
		this.statusRecepcaoEvento = statusRecepcaoEvento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getStatusAutorizacao() {
		return statusAutorizacao;
	}

	public void setStatusAutorizacao(String statusAutorizacao) {
		this.statusAutorizacao = statusAutorizacao;
	}

	public String getStatusRetornoAutorizacao() {
		return statusRetornoAutorizacao;
	}

	public void setStatusRetornoAutorizacao(String statusRetornoAutorizacao) {
		this.statusRetornoAutorizacao = statusRetornoAutorizacao;
	}

	public String getStatusInutilizacao() {
		return statusInutilizacao;
	}

	public void setStatusInutilizacao(String statusInutilizacao) {
		this.statusInutilizacao = statusInutilizacao;
	}

	public String getStatusConsultaProtocolo() {
		return statusConsultaProtocolo;
	}

	public void setStatusConsultaProtocolo(String statusConsultaProtocolo) {
		this.statusConsultaProtocolo = statusConsultaProtocolo;
	}

	public String getStatusServico() {
		return statusServico;
	}

	public void setStatusServico(String statusServico) {
		this.statusServico = statusServico;
	}

	public String getStatusConsultaCadastro() {
		return statusConsultaCadastro;
	}

	public void setStatusConsultaCadastro(String statusConsultaCadastro) {
		this.statusConsultaCadastro = statusConsultaCadastro;
	}

	public String getStatusRecepcaoEvento() {
		return statusRecepcaoEvento;
	}

	public void setStatusRecepcaoEvento(String statusRecepcaoEvento) {
		this.statusRecepcaoEvento = statusRecepcaoEvento;
	}

}
