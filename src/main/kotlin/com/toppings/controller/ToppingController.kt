package com.toppings.controller

import com.toppings.dto.ToppingsDTO
import com.toppings.dto.StatisticDTO
import com.toppings.service.ToppingService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import javax.persistence.EntityNotFoundException

@RestController
@RequestMapping("/toppings")
class ToppingController(val toppingService: ToppingService) {

    @Operation(summary = "Allows for the front end team to grab the list of toppings currently submitted and the number of unique customers that have requested that topping.")
    @Secured("ROLE_USER")
    @GetMapping("/statistic")
    fun getStatistic(): StatisticDTO = toppingService.getStatistic()

    @Operation(summary = "Allows for customers to submit their email address along with the list of toppings that they would be interested in")
    @PostMapping("/favorite")
    fun sendFavorite(@RequestBody toppingsDTO: ToppingsDTO): ToppingsDTO = toppingService.saveOrUpdateFavorite(toppingsDTO)

    @Operation(summary = "Allows for the front end team to grab the list of toppings currently submitted and the number of unique customers that have requested that topping.")
    @GetMapping("/mine")
    fun getMyFavorite(principal: Principal?): ToppingsDTO = toppingService.getByEmail(principal)

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<String>? {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

}