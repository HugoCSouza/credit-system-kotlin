package me.dio.system.credit.kotlin.repository

import me.dio.system.credit.kotlin.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
}