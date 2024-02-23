package com.ramon.propostabackend.controller;

import com.ramon.propostabackend.dto.PropostaRequestDto;
import com.ramon.propostabackend.dto.PropostaResponseDto;
import com.ramon.propostabackend.entity.Proposta;
import com.ramon.propostabackend.services.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    @Autowired
    private PropostaService service;

    @PostMapping
    ResponseEntity<PropostaResponseDto>criar(@RequestBody PropostaRequestDto requestDto) {
        PropostaResponseDto responseDto = service.createProposta(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(responseDto.getId())
        .toUri())
                .body(responseDto);
    }

    @GetMapping
    ResponseEntity<List<PropostaResponseDto>>obterProposta() {
        List<PropostaResponseDto> response = service.obterProposta();
        return ResponseEntity.ok(response);
    }


}
