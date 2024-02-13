package br.com.rodson.demo.core.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class Cliente(
    val cpf: String,
    val nome: String,
    val sexo: String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val dataNascimento: LocalDate,
    var endereco: List<Endereco>
)

data class Endereco(
    val rua: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val uf: String
)