package com.ramon.analisecredito.service.strategy;

import com.ramon.analisecredito.entity.Proposta;
import com.ramon.analisecredito.service.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class RendaMaiorValorSolicitadoImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorValorSolicitado(Proposta proposta) {
       return  proposta.getUser().getRenda() > proposta.getValorSolicitado();

    }
}
