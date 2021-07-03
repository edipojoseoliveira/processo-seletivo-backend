package com.edipo.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edipo.backend.model.StatusServico;
import com.edipo.backend.model.StatusServicoHistorico;
import com.edipo.backend.repository.StatusServicoHistoricoRepository;
import com.edipo.backend.repository.StatusServicoRepository;
import com.edipo.backend.repository.StatusServicoRepositoryCustom;

@RestController
public class StatusServicoHistoricoController {
	
	private final StatusServicoHistoricoRepository historicorepository;
	private final StatusServicoRepositoryCustom historicorepositoryCustom;
	private final StatusServicoRepository servicorepository;
	
	public StatusServicoHistoricoController(StatusServicoHistoricoRepository historicorepository,
			StatusServicoRepository servicorepository, StatusServicoRepositoryCustom historicorepositoryCustom) {
		this.historicorepository = historicorepository;
		this.servicorepository = servicorepository;
		this.historicorepositoryCustom = historicorepositoryCustom;
	}
	
	@GetMapping("/listar-status-por-data")
	public List<StatusServico> listarPorData(@RequestParam(value = "data") Date data) {
		List<StatusServico> listaStatusServicos = this.servicorepository.findAll();
		if (listaStatusServicos == null || listaStatusServicos.isEmpty()) {
			return new ArrayList<StatusServico>();
		}
		for (StatusServico servico : listaStatusServicos) {
			Optional<StatusServicoHistorico> optHistorico = this.historicorepository
					.findFirstByDataAndEstadoOrderByIdDesc(data, servico.getEstado());
			if (optHistorico.isPresent()) {
				StatusServicoHistorico historico = optHistorico.get();
				servico.setStatusAutorizacao(historico.getStatusAutorizacao());
				servico.setStatusConsultaCadastro(historico.getStatusConsultaCadastro());
				servico.setStatusConsultaProtocolo(historico.getStatusConsultaProtocolo());
				servico.setStatusInutilizacao(historico.getStatusInutilizacao());
				servico.setStatusRecepcaoEvento(historico.getStatusRecepcaoEvento());
				servico.setStatusRetornoAutorizacao(historico.getStatusRetornoAutorizacao());
				servico.setStatusServico(historico.getStatusServico());
			} else {
				servico.setStatusAutorizacao(null);
				servico.setStatusConsultaCadastro(null);
				servico.setStatusConsultaProtocolo(null);
				servico.setStatusInutilizacao(null);
				servico.setStatusRecepcaoEvento(null);
				servico.setStatusRetornoAutorizacao(null);
				servico.setStatusServico(null);
			}
		}
		return listaStatusServicos;
	}
	
	@GetMapping("/buscar-status-mais-indisponivel")
	public StatusServico buscarMaisIndisponivel() {
		return this.historicorepositoryCustom.buscarEstadoComMaisIndisponibilidade();
	}
	
}