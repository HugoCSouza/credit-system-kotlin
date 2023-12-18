package me.dio.system.credit.kotlin.exception

data class BusinessException(override val message: String?): RuntimeException(message) {
}