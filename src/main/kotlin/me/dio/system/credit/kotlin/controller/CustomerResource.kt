package me.dio.system.credit.kotlin.controller

import jakarta.validation.Valid
import me.dio.system.credit.kotlin.dto.CustomerDto
import me.dio.system.credit.kotlin.dto.CustomerUptadeDto
import me.dio.system.credit.kotlin.dto.CustomerView
import me.dio.system.credit.kotlin.entity.Customer
import me.dio.system.credit.kotlin.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDTO: CustomerDto): ResponseEntity<CustomerView> {
        val savedCustomer = this.customerService.save(customerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(CustomerView(savedCustomer))
    }

    @GetMapping("/{id}")
    fun findbyID(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK)
            .body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)

    @PatchMapping
    fun uptadeCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUptade: CustomerUptadeDto
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val customerUpdate: Customer = customerUptade.toEntity(customer)
        val customerUpdated: Customer = this.customerService.save(customerUpdate)
        return ResponseEntity.status(HttpStatus.OK)
            .body(CustomerView(customerUpdated))
    }


}