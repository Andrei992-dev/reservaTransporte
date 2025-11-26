package com.example.reservaTransporte.repository

import com.example.reservaTransporte.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, Int> {
    fun existsByCpf(cpf: String): Boolean
}