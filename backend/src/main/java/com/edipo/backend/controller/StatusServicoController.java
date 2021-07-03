package com.edipo.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edipo.backend.model.StatusServico;
import com.edipo.backend.repository.StatusServicoRepository;

@RestController
public class StatusServicoController {
	
	private final StatusServicoRepository repository;
	
	public StatusServicoController(StatusServicoRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/listar-status")
	public List<StatusServico> listar() {
		return this.repository.findAll();
	}
	
	@GetMapping("/listar-status-por-estado")
	public List<StatusServico> buscarPorEstado(@RequestParam(value = "estado") String estado) {
		return this.repository.findAllByEstado(estado);
	}
	
}
