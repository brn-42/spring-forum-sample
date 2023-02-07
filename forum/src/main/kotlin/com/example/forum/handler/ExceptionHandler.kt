package com.example.forum.handler

import com.example.forum.dto.ErrorResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun noSuchElementException(request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponseDTO> {
        val error = ErrorResponseDTO(
            statusCode = HttpStatus.NOT_FOUND.value(),
            message = exception.message.orEmpty()
        )
        return ResponseEntity.badRequest().body(error)
    }

    @ExceptionHandler(value = [Exception::class])
    fun genericHandleException(request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponseDTO> {
        val error = ErrorResponseDTO(
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = exception.message.orEmpty()
        )
        return ResponseEntity.badRequest().body(error)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validationException(request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponseDTO> {
        val error = ErrorResponseDTO(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            message = "invalid"
        )
        return ResponseEntity.badRequest().body(error)
    }
}
