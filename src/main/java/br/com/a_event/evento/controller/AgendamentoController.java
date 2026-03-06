package br.com.a_event.evento.controller;

import br.com.a_event.evento.infrastructure.entity.Agendamento;
import br.com.a_event.evento.service.AgedamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamentos/")
public class AgendamentoController {

    private final AgedamentoService agedamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamentoi(@RequestBody Agendamento agendamento){

        return ResponseEntity.ok().body(agedamentoService.salvarAgendamento(agendamento));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id){

        agedamentoService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Agendamento> buscarAgendamentosDia(@RequestParam LocalDate data){

        return ResponseEntity.ok().body(agedamentoService.buscarAgendamentosDia(data));
    }

    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamentos(@RequestBody Agendamento agendamento,
                                                           @RequestParam String cliente,
                                                           @RequestParam LocalDateTime dataHoraPlanejada){

        return ResponseEntity.accepted().body(agedamentoService.alterarAgendamento(agendamento, cliente, dataHoraPlanejada));
    }
}
