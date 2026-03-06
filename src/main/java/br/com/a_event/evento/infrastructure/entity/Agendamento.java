package br.com.a_event.evento.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_agendamento")
@EntityListeners(AuditingEntityListener.class)
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String evento;

    private String cliente;

    private LocalDateTime dataHoraPlanejada;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dataInsercao;

    private String telefone;
}
