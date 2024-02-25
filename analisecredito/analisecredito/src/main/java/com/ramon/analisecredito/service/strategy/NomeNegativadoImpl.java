package com.ramon.analisecredito.service.strategy;

import com.ramon.analisecredito.constante.MensagemConstante;
import com.ramon.analisecredito.entity.Proposta;
import com.ramon.analisecredito.exceptions.StrategyException;
import com.ramon.analisecredito.service.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
@Order(1)
public class NomeNegativadoImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativado()) {
            throw new StrategyException(String.format(MensagemConstante.CLIENTE_NEGATIVADO, proposta.getUser().getNome()));
        }
        return 100;
    }

    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }
}
