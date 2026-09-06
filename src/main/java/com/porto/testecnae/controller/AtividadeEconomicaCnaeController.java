package com.porto.testecnae.controller;

import com.porto.testecnae.dto.AtividadeEconomicaCnaeResponse;
import com.porto.testecnae.exceptions.CnaeNotFoundException;
import com.porto.testecnae.service.AtividadeEconomicaCnaeService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cnaes")
public class AtividadeEconomicaCnaeController {

    private final AtividadeEconomicaCnaeService service;

    @GetMapping
    public List<AtividadeEconomicaCnaeResponse> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/buscar")
    public List<AtividadeEconomicaCnaeResponse> buscarPorDescricao(@RequestParam String termo) {
        return service.buscarPorDescricao(termo);
    }

    @GetMapping("/codigo")
    public AtividadeEconomicaCnaeResponse buscarPorCodigo(@RequestParam String codigo) {

        // eu poderia trabalhar com responseEntity e criar um DTO padrão para respostas com erro para um codigo mais limpo,
        // porém estou mantendo o padrão já encontrado no codigo
        // para poder mostrar a mensagem, dado que por segurança fica abstraido, decidi criar um controller advice para tratar exceções
        AtividadeEconomicaCnaeResponse response = service.buscarPorCodigo(codigo);
        if (response == null) {
            throw new CnaeNotFoundException( "Codigo CNAE não encontrado para o código: " + codigo);
        }
        return response;
    }
}
