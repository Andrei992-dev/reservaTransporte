package com.example.reservaTransporte.service

import com.example.reservaTransporte.Cliente
import com.example.reservaTransporte.repository.ClienteRepository
import org.springframework.stereotype.Service

@Service
class ClienteService(private val repository: ClienteRepository) {

    fun getAll(): List<Cliente> = repository.findAll()

    fun getById(id: Int): Cliente? = repository.findById(id).orElse(null)

    fun create(cliente: Cliente): Cliente {
        // Validação simples (exemplo)
        if (repository.existsByCpf(cliente.cpf)) {
            throw IllegalArgumentException("CPF já cadastrado: ${cliente.cpf}")
        }
        return repository.save(cliente)
    }

    fun update(id: Int, cliente: Cliente): Cliente? =
        if (repository.existsById(id)) {
            repository.save(cliente.copy(id = id))
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