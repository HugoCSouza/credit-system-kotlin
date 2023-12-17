package me.dio.system.credit.kotlin.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


@Embeddable
data class Address (
    @Column(nullable = false) var cep: String = "",
    @Column(nullable = false) var street: String = ""
)


