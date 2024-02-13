package br.com.rodson.demo.adapter.database.repository

import br.com.rodson.demo.adapter.database.entity.ClienteEntity
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key

@Repository
class ClienteRepository(
    private val dynamoDbTable: DynamoDbTable<ClienteEntity>
) {
    fun save(cliente: ClienteEntity) {
        dynamoDbTable.putItem(cliente)
    }

    fun findById(cpf: String): ClienteEntity? {
        return dynamoDbTable.getItem(key(cpf))
    }

    fun delete(cpf: String): ClienteEntity? {
        return dynamoDbTable.deleteItem(key(cpf))
    }

    private fun key(pk: String): Key? {
        return Key.builder()
            .partitionValue(pk)
            .build()
    }
}