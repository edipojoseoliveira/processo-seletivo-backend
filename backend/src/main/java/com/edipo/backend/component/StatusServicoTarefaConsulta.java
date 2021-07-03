package com.edipo.backend.component;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.edipo.backend.model.StatusServico;
import com.edipo.backend.model.StatusServicoHistorico;
import com.edipo.backend.repository.StatusServicoHistoricoRepository;
import com.edipo.backend.repository.StatusServicoRepository;

@Component @EnableScheduling
public class StatusServicoTarefaConsulta {
	
	private final StatusServicoRepository repository;
	private final StatusServicoHistoricoRepository historicoRepository;
	
	public StatusServicoTarefaConsulta(StatusServicoRepository repository, StatusServicoHistoricoRepository historicoRepository) {
		this.repository = repository;
		this.historicoRepository = historicoRepository;
	}
	
	@Scheduled(fixedDelay = 1000 * 60 * 5)
	private void consultarStatusServicos() {
		try {
			Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
			Elements tableRows = doc.select(".tabelaListagemDados tbody tr");
			for (int i = 1; i < tableRows.size(); i++) {
				Elements tableTds = tableRows.get(i).select("td");
				
				String estado = tableTds.get(0).text();
				String statusAutorizacao = retornaStatusServico(tableTds, 1);
				String statusRetornoAutorizacao = retornaStatusServico(tableTds, 2);
				String statusInutilizacao = retornaStatusServico(tableTds, 3);
				String statusConsultaProtocolo = retornaStatusServico(tableTds, 4);
				String statusServico = retornaStatusServico(tableTds, 5);
				String statusConsultaCadastro = retornaStatusServico(tableTds, 7);
				String statusRecepcaoEvento = retornaStatusServico(tableTds, 8);

				this.salvarHistoricoStatusServicos(estado, statusAutorizacao,
						statusRetornoAutorizacao, statusInutilizacao, statusConsultaProtocolo, statusServico,
						statusConsultaCadastro, statusRecepcaoEvento);
				this.salvarStatusServicos(estado, statusAutorizacao, statusRetornoAutorizacao, statusInutilizacao,
						statusConsultaProtocolo, statusServico, statusConsultaCadastro, statusRecepcaoEvento);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void salvarStatusServicos(String estado, String statusAutorizacao, String statusRetornoAutorizacao,
			String statusInutilizacao, String statusConsultaProtocolo, String statusServico,
			String statusConsultaCadastro, String statusRecepcaoEvento) {
		StatusServico statusServicoNf;
		Optional<StatusServico> optStatusServico= this.repository.findByEstado(estado);
		if (optStatusServico.isPresent()) {
			statusServicoNf = optStatusServico.get();
			statusServicoNf.setStatusAutorizacao(statusAutorizacao);
			statusServicoNf.setStatusConsultaCadastro(statusConsultaCadastro);
			statusServicoNf.setStatusConsultaProtocolo(statusConsultaProtocolo);
			statusServicoNf.setStatusInutilizacao(statusInutilizacao);
			statusServicoNf.setStatusRecepcaoEvento(statusRecepcaoEvento);
			statusServicoNf.setStatusRetornoAutorizacao(statusRetornoAutorizacao);
			statusServicoNf.setStatusServico(statusServico);
		} else {
			statusServicoNf = new StatusServico(null, estado, statusAutorizacao, 
					statusRetornoAutorizacao, statusInutilizacao, statusConsultaProtocolo, 
					statusServico, statusConsultaCadastro, statusRecepcaoEvento);
		}

		this.repository.save(statusServicoNf);
	}

	private void salvarHistoricoStatusServicos(String estado, String statusAutorizacao,
			String statusRetornoAutorizacao, String statusInutilizacao, String statusConsultaProtocolo,
			String statusServico, String statusConsultaCadastro, String statusRecepcaoEvento) {
		StatusServicoHistorico historico = new StatusServicoHistorico(null, new Date(), estado, 
				statusAutorizacao, statusRetornoAutorizacao, statusInutilizacao, statusConsultaProtocolo, 
				statusServico, statusConsultaCadastro, statusRecepcaoEvento);
		this.historicoRepository.save(historico);
	}

	private String retornaStatusServico(Elements tableTds, Integer indexColuna) {
		Element img = tableTds.get(indexColuna).selectFirst("img");
		if (img == null) {
			return null;
		}
		String srcImg = img.attr("src");
		String status = "";
		if (srcImg.contains("verde")) {
			status = "Disponível";
		} else if (srcImg.contains("vermelho")) {
			status = "Indisponível";
		} else {
			status = "Instável";
		}
		return status;
	}

}
