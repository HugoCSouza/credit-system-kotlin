package me.dio.system.credit.kotlin.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.system.credit.kotlin.entity.Customer
import java.math.BigDecimal

data class CustomerUptadeDto(
    @field:NotEmpty(message = "Name don't be empty") val firstName: String,
    @field:NotEmpty(message = "Last name don't be empty") val lastName: String,
    @field:NotNull(message = "Wrong income") val income: BigDecimal,
    @field:NotEmpty(message = "CEP don't be empty") val cep: String,
    @field:NotEmpty(message = "Street don't be empty") val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.cep = this.cep
        customer.address.street = this.street
        return customer
    }
}
