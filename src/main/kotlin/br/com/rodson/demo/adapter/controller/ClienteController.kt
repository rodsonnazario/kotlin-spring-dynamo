package br.com.rodson.demo.adapter.controller

import br.com.rodson.demo.core.domain.Cliente
import br.com.rodson.demo.core.service.ClienteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clientes")
class ClienteController(
    private val service: ClienteService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastra(
        @RequestBody cliente: Cliente
    ) {
        service.salva(cliente)
    }

    @GetMapping("/{cpf}")
    fun busca(
        @PathVariable cpf: String
    ): ResponseEntity<Cliente> {
        return ResponseEntity.ok(service.busca(cpf))
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deleta(
        @PathVariable cpf: String
    ) {
        service.deleta(cpf)
    }
}