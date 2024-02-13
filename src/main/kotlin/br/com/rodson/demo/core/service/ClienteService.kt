package br.com.rodson.demo.core.service

import br.com.rodson.demo.core.domain.Cliente
import br.com.rodson.demo.core.port.ClientePort
import org.springframework.stereotype.Service

@Service
class ClienteService(
    val port: ClientePort
) {
    fun salva(cliente: Cliente) {
        port.salva(cliente)
    }

    fun busca(cpf: String): Cliente {
        return port.busca(cpf)
    }

    fun deleta(cpf: String) {
        port.deleta(cpf)
    }
}