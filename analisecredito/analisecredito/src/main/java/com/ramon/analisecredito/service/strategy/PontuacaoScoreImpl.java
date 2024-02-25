package com.ramon.analisecredito.service.strategy;

import com.ramon.analisecredito.constante.MensagemConstante;
import com.ramon.analisecredito.entity.Proposta;
import com.ramon.analisecredito.exceptions.StrategyException;
import com.ramon.analisecredito.service.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
@Order(2)
public class PontuacaoScoreImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        int score = score();

        if(score < 200) {
            throw new StrategyException(String.format(MensagemConstante.PONTUACAO_SERASA_BAIXO, proposta.getUser().getNome()));
        } else if (score <= 400) {
            return 150;
        } else if(score <= 600) {
            return 180;
        } else {
            return 220;
        }
    }
    private int score() {
        return new Random().nextInt(0,  100);
    }
}
