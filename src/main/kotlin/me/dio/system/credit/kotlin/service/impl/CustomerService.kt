package me.dio.system.credit.kotlin.service.impl

import me.dio.system.credit.kotlin.entity.Customer
import me.dio.system.credit.kotlin.repository.CustomerRepository
import me.dio.system.credit.kotlin.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) : ICustomerService {
    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)


    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow {
        throw RuntimeException("Id $id not found")
    }

    override fun delete(id: Long) = this.customerRepository.deleteById(id)
}