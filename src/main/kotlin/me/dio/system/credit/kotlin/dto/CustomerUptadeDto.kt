package me.dio.system.credit.kotlin.dto

import me.dio.system.credit.kotlin.entity.Customer
import java.math.BigDecimal

data class CustomerUptadeDto(
    val firstName: String,
    val lastName: String,
    val income: BigDecimal,
    val cep: String,
    val street: String
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
