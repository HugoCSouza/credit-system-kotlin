package me.dio.system.credit.kotlin.service.impl

import me.dio.system.credit.kotlin.entity.Credit
import me.dio.system.credit.kotlin.exception.BusinessException
import me.dio.system.credit.kotlin.repository.CreditRepository
import me.dio.system.credit.kotlin.service.ICreditService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
    ): ICreditService {
    override fun save(credit: Credit): Credit {
        this.validDayFirstInstallment(credit.dayFirstInstallment)
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomer(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("CreditCode $creditCode not found!"))
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact Admin")
    }

    private fun validDayFirstInstallment(dayFirstInstallment: LocalDate): Boolean {
        return if (dayFirstInstallment.isBefore(LocalDate.now().plusMonths(3))) true
        else throw BusinessException("Invalid Date")
    }
}