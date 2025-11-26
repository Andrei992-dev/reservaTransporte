package com.example.reservaTransporte.controller

import com.example.reservaTransporte.Cliente
import com.example.reservaTransporte.repository.ClienteRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clientes")
class ClienteController(private val repository: ClienteRepository) {

    @GetMapping
    fun getAll(): List<Cliente> = repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<Cliente> =
        repository.findById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody cliente: Cliente): Cliente = repository.save(cliente)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody cliente: Cliente): ResponseEntity<Cliente> =
        if (repository.existsById(id)) {
            ResponseEntity.ok(repository.save(cliente.copy(id = id)))
        } else {
            ResponseEntity.notFound().build()
        }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> =
        if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
}