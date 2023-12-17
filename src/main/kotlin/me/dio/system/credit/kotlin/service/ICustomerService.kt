package me.dio.system.credit.kotlin.service

import me.dio.system.credit.kotlin.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer

    fun findByAll (id: Long): Customer

    fun delete(id: Long)
}