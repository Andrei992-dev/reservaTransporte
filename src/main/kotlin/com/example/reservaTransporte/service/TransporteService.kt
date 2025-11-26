package com.example.reservaTransporte.service

import com.example.reservaTransporte.Transporte
import com.example.reservaTransporte.repository.TransporteRepository
import org.springframework.stereotype.Service

@Service
class TransporteService(private val repository: TransporteRepository) {

    fun getAll(): List<Transporte> = repository.findAll()

    fun getById(id: Int): Transporte? = repository.findById(id).orElse(null)

    fun create(transporte: Transporte): Transporte {
        // Validação simples (exemplo)
        if (repository.existsByPlaca(transporte.placa)) {
            throw IllegalArgumentException("Placa já cadastrada: ${transporte.placa}")
        }
        // Validação do status
        val validStatuses = listOf("DISPONIVEL", "EM_USO", "MANUTENCAO")
        if (transporte.status.isNullOrBlank() || !validStatuses.contains(transporte.status.uppercase())) {
            throw IllegalArgumentException("Status inválido: ${transporte.status}. Deve ser um de ${validStatuses.joinToString(", ")}")
        }
        return repository.save(transporte)
    }

    fun update(id: Int, transporte: Transporte): Transporte? =
        if (repository.existsById(id)) {
            repository.save(transporte.copy(id = id))
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