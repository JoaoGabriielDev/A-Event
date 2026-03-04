package br.com.a_event.evento.service;

import br.com.a_event.evento.infrastructure.entity.Agendamento;
import br.com.a_event.evento.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgedamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento){

        LocalDateTime horaAgendamento = agendamento.getDataHoraPlanejada();
        LocalDateTime dataHoraPossivel = agendamento.getDataHoraPlanejada().plusHours(12);

        Agendamento agendados = agendamentoRepository.findByEventoAndDataHoraPlanejadaBetween(agendamento.getEvento(),
               horaAgendamento, dataHoraPossivel);

        if (Objects.nonNull(agendados)){
            throw new RuntimeException("O horario está indisponivel");
        }
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(Long id){
        agendamentoRepository.findById(id);
    }

    public Agendamento buscarAgendamentosDia(LocalDate data){

        LocalDateTime primeiroEventoDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

        return agendamentoRepository.findByDataHoraPlanejadaBetween(primeiroEventoDia, horaFinalDia);
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
