package br.com.a_event.evento.service;

import br.com.a_event.evento.infrastructure.entity.Agendamento;
import br.com.a_event.evento.infrastructure.repository.AgendamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class AgedamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento){

        LocalDateTime dataHora = agendamento.getDataHoraPlanejada();

        LocalDateTime inicioJanela = dataHora.minusHours(24);
        LocalDateTime fimJanela = dataHora.plusHours(24);

        boolean existe = agendamentoRepository
                .existsByDataHoraPlanejadaBetween(
                        inicioJanela,
                        fimJanela
                );

        if(existe){
            throw new RuntimeException("Já existe um evento dentro do intervalo de 24h");
        }

        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(Long id){

        if (!agendamentoRepository.existsById(id)){
            throw new RuntimeException("Agendamento não encontrado");
        }

        agendamentoRepository.deleteById(id);
    }

    public Agendamento buscarAgendamentosDia(LocalDate data){

        return agendamentoRepository.findByDataHoraPlanejadaBetween(
                data.atStartOfDay(),
                data.atTime(LocalTime.MAX)
        );
    }

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraPlanejada){

        Agendamento agenda = agendamentoRepository.findByDataHoraPlanejadaAndCliente(dataHoraPlanejada, cliente);

        if (Objects.isNull(agenda)){
            throw new RuntimeException("O horario não está preenchido");
        }

        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }
}
