package br.com.rodson.demo.configuration

import br.com.rodson.demo.adapter.database.entity.CaracteristicaEntity
import br.com.rodson.demo.adapter.database.entity.ClienteEntity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
class DynamoConfig {

    @Bean
    fun dynamoDbClient(): DynamoDbClient {
        return DynamoDbClient.builder()
            .credentialsProvider {
                AwsBasicCredentials.create("id", "secret")
            }
            .region(Region.US_EAST_1)
            .endpointOverride(URI.create("http://localhost:4566"))
            .build()
    }

    @Bean
    fun dynamoDbEnhancedClient(): DynamoDbEnhancedClient {
        return DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient())
            .build()
    }

    @Bean
    fun dynamoDbTableCliente(): DynamoDbTable<ClienteEntity>? {
        val table = dynamoDbEnhancedClient().table(
            ClienteEntity.NOME_TABELA,
            TableSchema.fromBean(ClienteEntity::class.java)
        )
        dynamoDbClient().listTables().tableNames().contains(ClienteEntity.NOME_TABELA).let {
            if (!it)
                table.createTable()
        }
        return table
    }

    @Bean
    fun dynamoDbTableCaracteristica(): DynamoDbTable<CaracteristicaEntity>? {
        val table = dynamoDbEnhancedClient().table(
            CaracteristicaEntity.NOME_TABELA,
            TableSchema.fromBean(CaracteristicaEntity::class.java)
        )
        dynamoDbClient().listTables().tableNames().contains(CaracteristicaEntity.NOME_TABELA).let {
            if (!it)
                table.createTable()
        }
        return table
    }
}