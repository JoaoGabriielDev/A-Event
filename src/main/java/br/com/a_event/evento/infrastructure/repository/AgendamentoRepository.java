package br.com.a_event.evento.infrastructure.repository;

import br.com.a_event.evento.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Agendamento findByEventoAndDataHoraPlanejadaBetween(String servico,
                                                        LocalDateTime dataHoraInicio,
                                                        LocalDateTime dataHoraFinal);

    Agendamento findByDataHoraPlanejadaBetween(LocalDateTime dataHoraInicial, LocalDateTime DateTimeHoraFinal);

    Agendamento findByDataHoraPlanejadaAndCliente(LocalDateTime dataHoraAgendamento, String cliente);
}
