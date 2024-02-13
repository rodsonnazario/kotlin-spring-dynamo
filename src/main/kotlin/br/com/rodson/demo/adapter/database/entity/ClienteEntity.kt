package br.com.rodson.demo.adapter.database.entity

import br.com.rodson.demo.core.domain.Cliente
import br.com.rodson.demo.core.domain.Endereco
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.time.LocalDate
import java.time.LocalDateTime

@DynamoDbBean
data class ClienteEntity(
    @get:DynamoDbPartitionKey
    var cpf: String = "",
    var nome: String = "",
    var sexo: String = "",
    var dataNascimento: LocalDate = LocalDate.MIN,
    var rua: String = "",
    var numero: String = "",
    var bairro: String = "",
    var cidade: String = "",
    var uf: String = "",
    var dataCriacao: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        val NOME_TABELA = "clientes"
    }
}

fun Cliente.toEntitys(): ClienteEntity {
    return ClienteEntity(
        cpf = this.cpf,
        nome = this.nome,
        sexo = this.sexo,
        dataNascimento = this.dataNascimento,
        rua = endereco.first().rua,
        numero = endereco.first().numero,
        bairro = endereco.first().bairro,
        cidade = endereco.first().cidade,
        uf = endereco.first().uf
    )
}

fun ClienteEntity.toDomain(): Cliente {
    return Cliente(
        cpf = this.cpf,
        nome = this.nome,
        sexo = this.sexo,
        dataNascimento = this.dataNascimento,
        endereco = mutableListOf(
            Endereco(
                rua = this.rua,
                numero = this.numero,
                bairro = this.bairro,
                cidade = this.cidade,
                uf = this.uf
            )
        )
    )
}