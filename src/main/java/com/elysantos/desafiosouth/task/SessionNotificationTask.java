package com.elysantos.desafiosouth.task;

import com.elysantos.desafiosouth.model.domain.Sessao;
import com.elysantos.desafiosouth.repository.SessaoRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Run session verification every minute and notify if session is closed
 */
@Slf4j
@EnableScheduling
@Component
@RequiredArgsConstructor
public class SessionNotificationTask {
  private final SessaoRepository              sessaoRepository;
  private final KafkaTemplate<String, String> kafkaTemplate;

  @Scheduled(cron = "0 0/1 * * * *")
  private void run() {
    List<Sessao> sessoesFechadas = sessaoRepository.findByStatusNotClosedAndVotingClosed();
    sessoesFechadas.forEach(sessao -> {
      sessao.encerrarVotacao();
      kafkaTemplate.send("assembleias-resultados", sessao.toMessage());
    });
    log.info("Current time is :: {} | sessoes fechadas {}", LocalDateTime.now(), sessoesFechadas.size());
  }
}
