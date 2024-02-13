package br.com.rodson.demo.core.service

import br.com.rodson.demo.core.domain.Caracteristica
import br.com.rodson.demo.core.port.CaracteristicaPort
import org.springframework.stereotype.Service

@Service
class CaracteristicaService(
    val port: CaracteristicaPort
) {
    fun salva(caracteristica: Caracteristica) {
        port.salva(caracteristica)
    }

    fun busca(cpf: String): Caracteristica {
        return port.busca(cpf)
    }

    fun busca(cpf: String, atributo: String): Caracteristica {
        return port.busca(cpf, atributo)
    }

    fun deleta(cpf: String) {
        port.deleta(cpf)
    }

    fun deleta(cpf: String, atributo: String) {
        port.deleta(cpf, atributo)
    }
}