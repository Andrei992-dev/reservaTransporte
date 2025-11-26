package com.example.reservaTransporte.service

import com.example.reservaTransporte.Reserva
import com.example.reservaTransporte.repository.ReservaRepository
import org.springframework.stereotype.Service

@Service
class ReservaService(private val repository: ReservaRepository) {

    fun getAll(): List<Reserva> = repository.findAll()

    fun getById(id: Int): Reserva? = repository.findById(id).orElse(null)

    fun create(reserva: Reserva): Reserva {
        // Validação simples
        if (repository.existsById(reserva.id ?: 0)) {
            throw IllegalArgumentException("Reserva já existe com ID: ${reserva.id}")
        }
        // Validação do status
        val validStatuses = listOf("PENDENTE", "CONFIRMADA", "EM_ANDAMENTO", "CONCLUIDA", "CANCELADA")
        if (reserva.status.isNullOrBlank() || !validStatuses.contains(reserva.status.uppercase())) {
            throw IllegalArgumentException("Status inválido: ${reserva.status}. Deve ser um de ${validStatuses.joinToString(", ")}")
        }
        return repository.save(reserva)
    }

    fun update(id: Int, reserva: Reserva): Reserva? =
        if (repository.existsById(id)) {
            repository.save(reserva.copy(id = id))
        } else {
            null
        }

    fun delete(id: Int): Boolean {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return true
        }
        return false
    }
}