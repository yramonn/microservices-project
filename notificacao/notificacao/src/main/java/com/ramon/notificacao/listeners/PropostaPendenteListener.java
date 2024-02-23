package com.ramon.notificacao.listeners;

import com.ramon.notificacao.constante.MensagemConstante;
import com.ramon.notificacao.domain.Proposta;
import com.ramon.notificacao.service.NotificacaoSnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    private NotificacaoSnsService notificacaoSnsService;
    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        String mensagem = String.format(MensagemConstante.PROPOSTA_EM_ANALISE, proposta.getUser().getNome());
        notificacaoSnsService.notificar(proposta.getUser().getTelefone(),mensagem);
    }
}
