package com.example.reservaTransporte

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "reservas")
data class Reserva(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "cliente_id", nullable = false)
    var clienteId: Int,

    @Column(name = "transporte_id", nullable = false)
    var transporteId: Int,

    @Column(name = "origem", nullable = false)
    var origem: String,

    @Column(name = "destino", nullable = false)
    var destino: String,

    @Column(name = "data_hora_inicio", nullable = false)
    var dataHoraInicio: Timestamp,

    @Column(name = "data_hora_fim")
    var dataHoraFim: Timestamp? = null,

    @Column(name = "valor_total")
    var valorTotal: Double? = null,

    /*  @Column(name = "status", columnDefinition = "ENUM('pendente', 'confirmada', 'em_andamento', 'concluida', 'cancelada') DEFAULT 'pendente'")
    @Enumerated(EnumType.STRING)
    var status: StatusReserva = StatusReserva.PENDENTE, */

    @Column(name = "status", nullable = false)
    var status: String = "PENDENTE",

    @Column(name = "observacoes")
    var observacoes: String? = null,

    @Column(name = "data_criacao", nullable = false, updatable = false)
    val dataCriacao: Timestamp = Timestamp(System.currentTimeMillis())
)

/* enum class StatusReserva {
    PENDENTE, CONFIRMADA, EM_ANDAMENTO, CONCLUIDA, CANCELADA
} */