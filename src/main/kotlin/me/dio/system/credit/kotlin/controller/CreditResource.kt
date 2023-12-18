package me.dio.system.credit.kotlin.controller

import me.dio.system.credit.kotlin.dto.CreditDto
import me.dio.system.credit.kotlin.dto.CreditViewList
import me.dio.system.credit.kotlin.entity.Credit
import me.dio.system.credit.kotlin.service.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): String{
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return "Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!"
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerID") customerId: Long): List<CreditViewList>{
        this.creditService.findAllByCustomer(customerId).stream()
            .map{credit: Credit -> CreditViewList(credit)}
            .collect(Collectors.toList())
    }
}