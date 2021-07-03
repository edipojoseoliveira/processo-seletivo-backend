package com.edipo.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edipo.backend.model.StatusServico;

public interface StatusServicoRepository extends JpaRepository<StatusServico, Long> {

	List<StatusServico> findAllByEstado(String estado);
	Optional<StatusServico> findByEstado(String estado);
	
}
