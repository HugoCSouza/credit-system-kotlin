package me.dio.system.credit.kotlin.controller

import me.dio.system.credit.kotlin.dto.CustomerDto
import me.dio.system.credit.kotlin.service.impl.CustomerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody customerDTO: CustomerDto): String{
        val savedCustomer = this.customerService.save(customerDTO.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }
}