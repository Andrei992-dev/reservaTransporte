package com.example.reservaTransporte.repository

import com.example.reservaTransporte.Reserva
import org.springframework.data.jpa.repository.JpaRepository

interface ReservaRepository : JpaRepository<Reserva, Int>