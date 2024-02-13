package br.com.rodson.demo.adapter.database.entity

import br.com.rodson.demo.core.domain.Atributo
import br.com.rodson.demo.core.domain.Caracteristica
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey
import java.time.LocalDateTime

@DynamoDbBean
data class CaracteristicaEntity(
    @get:DynamoDbPartitionKey
    var cpf: String = "",
    @get:DynamoDbSortKey
    var atributo: String = "",
    var valor: String = "",
    @get:DynamoDbSecondarySortKey(indexNames = [NOME_INDEX])
    @get:DynamoDbAttribute("data_criacao")
    var dataCriacao: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        const val NOME_TABELA = "caracteristicas"
        const val NOME_INDEX = "idx"
    }
}

fun Caracteristica.toEntitys(): List<CaracteristicaEntity> {
    return this.atributos.map {
        CaracteristicaEntity(
            cpf = this.cpf,
            atributo = it.nome,
            valor = it.valor
        )
    }.toList()
}

fun CaracteristicaEntity.toDomain(): Caracteristica {
    return Caracteristica(
        cpf = this.cpf,
        atributos = listOf(
            Atributo(
                nome = this.atributo,
                valor = this.valor
            )
        )
    )
}

fun List<CaracteristicaEntity>.toDomain(): Caracteristica {
    return Caracteristica(
        cpf = this.first().cpf,
        atributos = this.map {
            Atributo(
                nome = it.atributo,
                valor = it.valor
            )
        }.toList()
    )
}