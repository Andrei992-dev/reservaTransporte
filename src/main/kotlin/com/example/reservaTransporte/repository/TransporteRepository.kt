package com.example.reservaTransporte.repository

import com.example.reservaTransporte.Transporte
import org.springframework.data.jpa.repository.JpaRepository

interface TransporteRepository : JpaRepository<Transporte, Int> {
    fun existsByPlaca(placa: String): Boolean
}