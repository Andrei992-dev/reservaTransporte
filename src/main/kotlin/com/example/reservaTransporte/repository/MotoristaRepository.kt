package com.example.reservaTransporte.repository

import com.example.reservaTransporte.Motorista
import org.springframework.data.jpa.repository.JpaRepository

interface MotoristaRepository : JpaRepository<Motorista, Int> {
    fun existsByCpf(cpf: String): Boolean
}