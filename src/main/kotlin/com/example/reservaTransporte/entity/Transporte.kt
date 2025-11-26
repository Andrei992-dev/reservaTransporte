package com.example.reservaTransporte

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "transportes")
data class Transporte(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "motorista_id", nullable = false)
    var motoristaId: Int,

    @Column(name = "placa", nullable = false, unique = true)
    var placa: String,

    @Column(name = "modelo", nullable = false)
    var modelo: String,

    @Column(name = "marca", nullable = false)
    var marca: String,

    @Column(name = "ano_fabricacao")
    var anoFabricacao: Int? = null,

    @Column(name = "capacidade_passageiros", nullable = false)
    var capacidadePassageiros: Int,

    @Column(name = "cor")
    var cor: String? = null,

    /* @Column(name = "status", columnDefinition = "ENUM('disponivel', 'em_uso', 'manutencao') DEFAULT 'disponivel'")
    @Enumerated(EnumType.STRING)
    var status: StatusTransporte = StatusTransporte.DISPONIVEL, */

    @Column(name = "status", nullable = false)
    var status: String = "DISPONIVEL",

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    val dataCadastro: Timestamp = Timestamp(System.currentTimeMillis())
)

 /* enum class StatusTransporte {
    DISPONIVEL, EM_USO, MANUTENCAO
} */