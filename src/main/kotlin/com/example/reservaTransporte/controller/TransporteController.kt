package com.example.reservaTransporte.controller

import com.example.reservaTransporte.Transporte
import com.example.reservaTransporte.repository.TransporteRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transportes")
class TransporteController(private val repository: TransporteRepository) {

    @GetMapping
    fun getAll(): List<Transporte> = repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<Transporte> =
        repository.findById(id).map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    fun create(@RequestBody transporte: Transporte): Transporte = repository.save(transporte)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody transporte: Transporte): ResponseEntity<Transporte> =
        if (repository.existsById(id)) {
            ResponseEntity.ok(repository.save(transporte.copy(id = id)))
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