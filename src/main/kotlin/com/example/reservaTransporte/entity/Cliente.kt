package com.example.reservaTransporte

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "clientes")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "nome", nullable = false)
    var nome: String,

    @Column(name = "cpf", nullable = false, unique = true)
    var cpf: String,

    @Column(name = "telefone", nullable = false)
    var telefone: String,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "endereco")
    var endereco: String? = null,

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    val dataCadastro: Timestamp = Timestamp(System.currentTimeMillis())
)