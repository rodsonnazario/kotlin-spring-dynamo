package br.com.rodson.demo.core.port

import br.com.rodson.demo.core.domain.Caracteristica

interface CaracteristicaPort {
    fun salva(caracteristica: Caracteristica)
    fun busca(cpf: String): Caracteristica
    fun busca(cpf: String, atributo: String): Caracteristica
    fun deleta(cpf: String)
    fun deleta(cpf: String, atributo: String)
}