package com.example.reservaTransporte.controller

import com.example.reservaTransporte.Motorista
import com.example.reservaTransporte.repository.MotoristaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/motoristas")
class MotoristaController(private val repository: MotoristaRepository) {

    @GetMapping
    fun getAll(): List<Motorista> = repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<Motorista> =
        repository.findById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody motorista: Motorista): Motorista = repository.save(motorista)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody motorista: Motorista): ResponseEntity<Motorista> =
        if (repository.existsById(id)) {
            ResponseEntity.ok(repository.save(motorista.copy(id = id)))
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