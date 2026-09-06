package com.porto.testecnae.service.impl;

import com.porto.testecnae.dto.AtividadeEconomicaCnaeResponse;
import com.porto.testecnae.exceptions.TermNotFoundException;
import com.porto.testecnae.repository.AtividadeEconomicaCnaeRepository;
import com.porto.testecnae.service.AtividadeEconomicaCnaeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtividadeEconomicaCnaeServiceImpl implements AtividadeEconomicaCnaeService {

    private final AtividadeEconomicaCnaeRepository repository;

    @Override
    public List<AtividadeEconomicaCnaeResponse> listarTodas() {
        return repository.findAll()
                .stream()
                .map(AtividadeEconomicaCnaeResponse::fromEntity)
                .toList();
    }

    @Override
    public List<AtividadeEconomicaCnaeResponse> buscarPorDescricao(String termo) {
        List<AtividadeEconomicaCnaeResponse> response = repository.buscarPorDescricao(termo)
                .stream()
                .map(AtividadeEconomicaCnaeResponse::fromEntity)
                .toList();
        if (response.size() == 0) {
            throw new TermNotFoundException("Não foi encontrado o termo: " + termo + " nas descrições dos Cnaes.");
        }
        return response;
    }

    @Override
    public AtividadeEconomicaCnaeResponse buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo)
                .map(AtividadeEconomicaCnaeResponse::fromEntity)
                .orElse(null);
    }
}
