package com.edipo.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity(name = "status_servico")
public class StatusServico {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@Transient
	private Long qtdIndisponibilidades;
	
	public StatusServico() {}

	public StatusServico(Long id, String estado, String statusAutorizacao, String statusRetornoAutorizacao,
			String statusInutilizacao, String statusConsultaProtocolo, String statusServico,
			String statusConsultaCadastro, String statusRecepcaoEvento) {
		super();
		this.id = id;
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

	public Long getQtdIndisponibilidades() {
		return qtdIndisponibilidades;
	}

	public void setQtdIndisponibilidades(Long qtdIndisponibilidades) {
		this.qtdIndisponibilidades = qtdIndisponibilidades;
	}
	
}
