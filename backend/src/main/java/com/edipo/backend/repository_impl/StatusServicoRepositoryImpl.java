package com.edipo.backend.repository_impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.edipo.backend.model.StatusServico;
import com.edipo.backend.repository.StatusServicoRepositoryCustom;

public class StatusServicoRepositoryImpl implements StatusServicoRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public StatusServico buscarEstadoComMaisIndisponibilidade() {
		try {
			String consulta = " select status.id, "
					+ "  status.estado, "
					+ "  status.status_autorizacao, "
					+ "  status.status_consulta_cadastro, "
					+ "  status.status_consulta_protocolo, "
					+ "  status.status_inutilizacao, "
					+ "  status.status_recepcao_evento, "
					+ "  status.status_retorno_autorizacao, "
					+ "  status.status_servico, "
					+ "  count(historico.id) as qtdIndisponivel "
					+ " from status_servico as status "
					+ "  inner join status_servico_historico as historico on status.estado = historico.estado " 
					+ " where "
					+ "  historico.status_autorizacao = 'Indisponível' "
					+ "  or historico.status_retorno_autorizacao = 'Indisponível' "
					+ "  or historico.status_inutilizacao = 'Indisponível' "
					+ "  or historico.status_consulta_protocolo = 'Indisponível' "
					+ "  or historico.status_servico = 'Indisponível' "
					+ "  or historico.status_consulta_cadastro = 'Indisponível' " 
					+ "  or historico.status_recepcao_evento = 'Indisponível' " 
					+ " group by status.id "
					+ " order by count(historico.id) desc " 
					+ " limit 1 ";
			
			Query query = em.createNativeQuery(consulta);
			Object[] resultado = (Object[])query.getSingleResult();
			
			if (resultado != null) {
				StatusServico status = new StatusServico();
				status.setId(resultado[0] != null ? Long.parseLong(resultado[0].toString()) : null);
				status.setEstado(resultado[1] != null ? resultado[1].toString() : null);
				status.setStatusAutorizacao(resultado[2] != null ? resultado[2].toString() : null);
				status.setStatusConsultaCadastro(resultado[3] != null ? resultado[3].toString() : null);
				status.setStatusConsultaProtocolo(resultado[4] != null ? resultado[4].toString() : null);
				status.setStatusInutilizacao(resultado[5] != null ? resultado[5].toString() : null);
				status.setStatusRecepcaoEvento(resultado[6] != null ? resultado[6].toString() : null);
				status.setStatusRetornoAutorizacao(resultado[7] != null ? resultado[7].toString() : null);
				status.setStatusServico(resultado[8] != null ? resultado[8].toString() : null);
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new StatusServico(null, "N/A", null, null, null, null, null, null, null);
	}

}
