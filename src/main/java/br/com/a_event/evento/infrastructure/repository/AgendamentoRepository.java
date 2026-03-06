package br.com.a_event.evento.infrastructure.repository;

import br.com.a_event.evento.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByEventoAndDataHoraPlanejadaBetween(
            String evento,
            LocalDateTime inicio,
            LocalDateTime fim
    );

    boolean existsByDataHoraPlanejadaBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );

    Agendamento findByDataHoraPlanejadaBetween(LocalDateTime dataHoraInicial, LocalDateTime DateTimeHoraFinal);

    Agendamento findByDataHoraPlanejadaAndCliente(LocalDateTime dataHoraAgendamento, String cliente);
}
