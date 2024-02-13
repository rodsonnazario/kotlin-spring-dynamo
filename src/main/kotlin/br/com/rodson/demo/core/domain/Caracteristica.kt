package br.com.rodson.demo.core.domain

data class Caracteristica(
    val cpf: String,
    val atributos: List<Atributo>
)

data class Atributo(
    val nome: String,
    val valor: String
)
