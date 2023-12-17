package me.dio.system.credit.kotlin.dto

import me.dio.system.credit.kotlin.entity.Address
import me.dio.system.credit.kotlin.entity.Customer
import java.math.BigDecimal

data class CustomerDto(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val cep: String,
    val street: String,
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            cep = this.cep,
            street = this.street
        )
    )
}
