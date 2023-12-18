package me.dio.system.credit.kotlin.controller

import com.electronwill.nightconfig.core.conversion.Path
import me.dio.system.credit.kotlin.dto.CustomerDto
import me.dio.system.credit.kotlin.dto.CustomerUptadeDto
import me.dio.system.credit.kotlin.dto.CustomerView
import me.dio.system.credit.kotlin.entity.Customer
import me.dio.system.credit.kotlin.service.impl.CustomerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody customerDTO: CustomerDto): String {
        val savedCustomer = this.customerService.save(customerDTO.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping("/{id}")
    fun findbyID(@PathVariable id: Long): CustomerView {
        val customer: Customer = this.customerService.findById(id)
        return CustomerView(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun uptadeCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody customerUptade: CustomerUptadeDto
    ): CustomerView {
        val customer: Customer = this.customerService.findById(id)
        val customerUpdate: Customer = customerUptade.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerUpdate)
        return CustomerView(customerUpdated)
    }


}