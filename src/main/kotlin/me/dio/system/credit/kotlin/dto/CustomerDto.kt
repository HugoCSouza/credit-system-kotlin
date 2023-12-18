package me.dio.system.credit.kotlin.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.system.credit.kotlin.entity.Address
import me.dio.system.credit.kotlin.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Name don't be empty") val firstName: String,
    @field:NotEmpty(message = "Last name don't be empty") val lastName: String,
    @field:NotEmpty(message = "Wrong Field")
    @field:CPF(message = "This invalid CPF") val cpf: String,
    @field:NotNull(message = "Wrong income") val income: BigDecimal,
    @field:NotEmpty(message = "CEP don't be empty")
    @field:Email val email: String,
    @field:NotEmpty(message = "Password don't be empty") val password: String,
    @field:NotEmpty(message = "CEP don't be empty") val cep: String,
    @field:NotEmpty(message = "Street don't be empty") val street: String,
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
