package me.dio.system.credit.kotlin.dto

import me.dio.system.credit.kotlin.entity.Customer
import java.math.BigDecimal

data class CustomerView(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val cep: String,
    val street: String,
    val id: Long?
) {
    constructor(customer: Customer) : this(
        firstName = customer.firstName,
        lastName = customer.lastName,
        cpf = customer.cpf,
        income = customer.income,
        email = customer.email,
        cep = customer.address.cep,
        street = customer.address.street,
        id = customer.id
    )
}
