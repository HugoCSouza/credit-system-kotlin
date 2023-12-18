package me.dio.system.credit.kotlin.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {
    @Bean
    fun publicApi(): GroupedOpenApi?{
        return GroupedOpenApi.builder()
            .group("springSystemCreditKotlinApplication-public")
            .pathsToMatch("/api/customer","api/credits/")
            .build()
    }
}