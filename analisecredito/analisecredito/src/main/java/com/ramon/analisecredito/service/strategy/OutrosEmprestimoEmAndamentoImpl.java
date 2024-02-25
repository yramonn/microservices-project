package com.ramon.analisecredito.service.strategy;

import com.ramon.analisecredito.entity.Proposta;
import com.ramon.analisecredito.service.CalculoPonto;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class OutrosEmprestimoEmAndamentoImpl implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return outrosEmpretimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmpretimosEmAndamento() {
        return new Random().nextBoolean();
    }
}
