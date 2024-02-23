package com.ramon.propostabackend.services;

import com.ramon.propostabackend.dto.PropostaRequestDto;
import com.ramon.propostabackend.dto.PropostaResponseDto;
import com.ramon.propostabackend.entity.Proposta;
import com.ramon.propostabackend.mapper.PropostaMapper;
import com.ramon.propostabackend.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaRepository repository;

    NotificationRabbitService notificationService;

    private String exchange;


    public PropostaService(PropostaRepository repository, NotificationRabbitService notificationService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.repository = repository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    public PropostaResponseDto createProposta(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        repository.save(proposta);

        notificarRabbitMQ(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public List<PropostaResponseDto> obterProposta() {
        return PropostaMapper.INSTANCE.converterListEntityToListDto(repository.findAll());
    }

    private void notificarRabbitMQ(Proposta proposta) {
        try {
            notificationService.notificar(proposta, exchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            repository.save(proposta);
        }
    }
}
