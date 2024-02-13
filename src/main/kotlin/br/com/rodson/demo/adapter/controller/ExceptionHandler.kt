package br.com.rodson.demo.adapter.controller

import br.com.rodson.demo.adapter.database.exception.CaracteristicaNaoEncontradaException
import br.com.rodson.demo.adapter.database.exception.ClienteNaoEncontradoException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(
        ClienteNaoEncontradoException::class,
        CaracteristicaNaoEncontradaException::class
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun handlerClienteException(ex: Exception) {
    }
}