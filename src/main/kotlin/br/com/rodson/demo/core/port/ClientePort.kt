package br.com.rodson.demo.core.port

import br.com.rodson.demo.core.domain.Cliente

interface ClientePort {
    fun salva(cliente: Cliente)
    fun busca(cpf: String): Cliente
    fun deleta(cpf: String)
}