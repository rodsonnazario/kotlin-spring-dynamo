package br.com.rodson.demo.adapter.controller

import br.com.rodson.demo.core.domain.Caracteristica
import br.com.rodson.demo.core.service.CaracteristicaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/caracteristicas")
class CaracteristicaController(
    private val service: CaracteristicaService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastra(
        @RequestBody caracteristica: Caracteristica
    ) {
        service.salva(caracteristica)
    }

    @GetMapping
    fun busca(
        @RequestParam("cpf", required = true) cpf: String,
        @RequestParam("atributo", required = false) atributo: String?
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(
            if (atributo == null) service.busca(cpf)
            else service.busca(cpf, atributo)
        )
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deleta(
        @RequestParam("cpf", required = true) cpf: String,
        @RequestParam("atributo", required = false) atributo: String?
    ) {
        if (atributo == null) service.deleta(cpf)
        else service.deleta(cpf, atributo)
    }
}