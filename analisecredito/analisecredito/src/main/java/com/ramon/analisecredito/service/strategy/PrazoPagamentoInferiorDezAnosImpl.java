package com.ramon.analisecredito.service.strategy;

import com.ramon.analisecredito.entity.Proposta;
import com.ramon.analisecredito.service.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class PrazoPagamentoInferiorDezAnosImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() < 120 ? 80 : 0;
    }
}
