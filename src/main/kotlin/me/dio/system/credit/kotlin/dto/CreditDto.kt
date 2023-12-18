package me.dio.system.credit.kotlin.dto

import jakarta.persistence.*
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import me.dio.system.credit.kotlin.ennumeration.Status
import me.dio.system.credit.kotlin.entity.Credit
import me.dio.system.credit.kotlin.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class CreditDto(
    @field:NotNull(message = "Wrong value") val creditValue: BigDecimal,
    @field:Future val dayFirstInstallment: LocalDate,
    @field:Min(value = 1) @field:Max(value = 48) val numberOfInstallments: Int,
    @field:NotNull(message = "Wrong ID Customer") val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
