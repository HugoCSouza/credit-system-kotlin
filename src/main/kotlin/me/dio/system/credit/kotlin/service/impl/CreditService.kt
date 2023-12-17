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
        this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        TODO("Not yet implemented")
    }

    override fun findByCreditCode(customerId: UUID): Credit {
        TODO("Not yet implemented")
    }
}