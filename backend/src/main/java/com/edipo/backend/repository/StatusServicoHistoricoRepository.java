package com.edipo.backend.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edipo.backend.model.StatusServicoHistorico;

public interface StatusServicoHistoricoRepository extends JpaRepository<StatusServicoHistorico, Long> {
	
	Optional<StatusServicoHistorico> findFirstByDataAndEstadoOrderByIdDesc(Date data, String estado);
	@Query(value = "SELECT COUNT(*) FROM status_servico_historico WHERE estado = ?1 and (status_autorizacao = 'Indisponível' or status_retorno_autorizacao = 'Indisponível' or status_inutilizacao = 'Indisponível' or status_consulta_protocolo = 'Indisponível' or status_servico = 'Indisponível' or status_consulta_cadastro = 'Indisponível' or status_recepcao_evento = 'Indisponível') ", nativeQuery = true)
	long countIndisponibilidadeServico(String estado);
	
}
