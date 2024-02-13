package br.com.rodson.demo.adapter.database

import br.com.rodson.demo.adapter.database.entity.toDomain
import br.com.rodson.demo.adapter.database.entity.toEntitys
import br.com.rodson.demo.adapter.database.exception.CaracteristicaNaoEncontradaException
import br.com.rodson.demo.adapter.database.repository.CaracteristicaRepository
import br.com.rodson.demo.core.domain.Caracteristica
import br.com.rodson.demo.core.port.CaracteristicaPort
import org.springframework.stereotype.Component

@Component
class CaracteristicaAdapter(
    val repository: CaracteristicaRepository
) : CaracteristicaPort {
    override fun salva(caracteristica: Caracteristica) {
        repository.saveAll(caracteristica.toEntitys())
    }

    override fun busca(cpf: String): Caracteristica {
        return repository.findAllById(cpf)?.toDomain()
            ?: throw CaracteristicaNaoEncontradaException("Caracteristicas não encontrada")
    }

    override fun busca(cpf: String, atributo: String): Caracteristica {
        return repository.findByCpfAndAtributo(cpf, atributo)?.toDomain()
            ?: throw CaracteristicaNaoEncontradaException("Caracteristica não encontrada")
    }

    override fun deleta(cpf: String) {
        repository.delete(cpf)
    }

    override fun deleta(cpf: String, atributo: String) {
        repository.deleteByCpfAndAtributo(cpf, atributo)
    }
}
