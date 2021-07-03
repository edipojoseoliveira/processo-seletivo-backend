package com.edipo.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edipo.backend.model.StatusServico;
import com.edipo.backend.model.StatusServicoHistorico;
import com.edipo.backend.repository.StatusServicoHistoricoRepository;
import com.edipo.backend.repository.StatusServicoRepository;

@RestController
public class StatusServicoHistoricoController {
	
	private final StatusServicoHistoricoRepository historicorepository;
	private final StatusServicoRepository servicorepository;
	
	public StatusServicoHistoricoController(StatusServicoHistoricoRepository historicorepository,
			StatusServicoRepository servicorepository) {
		this.historicorepository = historicorepository;
		this.servicorepository = servicorepository;
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
		List<StatusServico> listaStatusServicos = this.servicorepository.findAll();
		if (listaStatusServicos == null || listaStatusServicos.isEmpty()) {
			return new StatusServico();
		}
		for (StatusServico statusServicoNf : listaStatusServicos) {
			long contadorIndisponibilidade = this.historicorepository.countIndisponibilidadeServico(statusServicoNf.getEstado());
			statusServicoNf.setQtdIndisponibilidades(contadorIndisponibilidade);
		}
		listaStatusServicos = listaStatusServicos.stream().sorted((s1, s2) -> s1.getQtdIndisponibilidades().compareTo(s2.getQtdIndisponibilidades())).collect(Collectors.toList());
		return listaStatusServicos.get(listaStatusServicos.size() - 1);
	}
	
}