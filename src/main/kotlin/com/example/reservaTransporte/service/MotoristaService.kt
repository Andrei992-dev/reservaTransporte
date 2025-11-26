package com.example.reservaTransporte.service

import com.example.reservaTransporte.Motorista
import com.example.reservaTransporte.repository.MotoristaRepository
import org.springframework.stereotype.Service

@Service
class MotoristaService(private val repository: MotoristaRepository) {

    fun getAll(): List<Motorista> = repository.findAll()

    fun getById(id: Int): Motorista? = repository.findById(id).orElse(null)

    fun create(motorista: Motorista): Motorista {
        // Validação simples (exemplo)
        if (repository.existsByCpf(motorista.cpf)) {
            throw IllegalArgumentException("CPF já cadastrado: ${motorista.cpf}")
        }
        return repository.save(motorista)
    }

    fun update(id: Int, motorista: Motorista): Motorista? =
        if (repository.existsById(id)) {
            repository.save(motorista.copy(id = id))
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