package com.example.reservaTransporte.controller

import com.example.reservaTransporte.Reserva
import com.example.reservaTransporte.repository.ReservaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservas")
class ReservaController(private val repository: ReservaRepository) {

    @GetMapping
    fun getAll(): List<Reserva> = repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<Reserva> =
        repository.findById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody reserva: Reserva): Reserva = repository.save(reserva)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody reserva: Reserva): ResponseEntity<Reserva> =
        if (repository.existsById(id)) {
            ResponseEntity.ok(repository.save(reserva.copy(id = id)))
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