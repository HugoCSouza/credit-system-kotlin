package me.dio.system.credit.kotlin.service.impl

import me.dio.system.credit.kotlin.entity.Credit
import me.dio.system.credit.kotlin.repository.CreditRepository
import me.dio.system.credit.kotlin.service.ICreditService
import java.util.*

class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
    ): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findByAll(credit.customer?.id!!)
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
}