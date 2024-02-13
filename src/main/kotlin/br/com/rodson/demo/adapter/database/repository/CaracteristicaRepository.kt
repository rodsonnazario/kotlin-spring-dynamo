package br.com.rodson.demo.adapter.database.repository

import br.com.rodson.demo.adapter.database.entity.CaracteristicaEntity
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional

@Repository
class CaracteristicaRepository(
    private val dynamoDbTable: DynamoDbTable<CaracteristicaEntity>,
) {
    fun saveAll(caracteristica: List<CaracteristicaEntity>) {
        caracteristica.forEach {
            dynamoDbTable.putItem(it)
        }
    }

    fun findAllById(cpf: String): List<CaracteristicaEntity>? {
        val query = QueryConditional.keyEqualTo(key(cpf))
        return dynamoDbTable.query(query).items().takeIf { it.toList().isNotEmpty() }?.toList()
    }

    fun findByCpfAndAtributo(cpf: String, atributo: String): CaracteristicaEntity? {
        return dynamoDbTable.getItem(key(cpf, atributo))
    }

    fun delete(cpf: String) {
        val query = QueryConditional.keyEqualTo(key(cpf))
        dynamoDbTable.query(query).items().forEach {
            deleteByCpfAndAtributo(it.cpf, it.atributo)
        }
    }

    fun deleteByCpfAndAtributo(cpf: String, atributo: String): CaracteristicaEntity? {
        return dynamoDbTable.deleteItem(key(cpf, atributo))
    }

    private fun key(pk: String): Key? {
        return Key.builder()
            .partitionValue(pk)
            .build()
    }

    private fun key(pk: String, sk: String): Key? {
        return Key.builder()
            .partitionValue(pk)
            .sortValue(sk)
            .build()
    }
}