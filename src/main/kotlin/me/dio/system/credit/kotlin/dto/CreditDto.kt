package me.dio.system.credit.kotlin.dto

import jakarta.persistence.*
import me.dio.system.credit.kotlin.ennumeration.Status
import me.dio.system.credit.kotlin.entity.Credit
import me.dio.system.credit.kotlin.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int,
    val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
