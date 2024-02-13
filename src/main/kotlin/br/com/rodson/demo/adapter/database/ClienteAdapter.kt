package br.com.rodson.demo.adapter.database

import br.com.rodson.demo.adapter.database.entity.toDomain
import br.com.rodson.demo.adapter.database.entity.toEntitys
import br.com.rodson.demo.adapter.database.exception.ClienteNaoEncontradoException
import br.com.rodson.demo.adapter.database.repository.ClienteRepository
import br.com.rodson.demo.core.domain.Cliente
import br.com.rodson.demo.core.port.ClientePort
import org.springframework.stereotype.Component

@Component
class ClienteAdapter(
    val repository: ClienteRepository
) : ClientePort {
    override fun salva(cliente: Cliente) {
        repository.save(cliente.toEntitys())
    }

    override fun busca(cpf: String): Cliente {
        return repository.findById(cpf)?.toDomain()
            ?: throw ClienteNaoEncontradoException("Cliente não encontrado")
    }

    override fun deleta(cpf: String) {
        repository.delete(cpf)
    }
}