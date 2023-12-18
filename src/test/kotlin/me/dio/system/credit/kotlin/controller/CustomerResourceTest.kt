package me.dio.system.credit.kotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.dio.system.credit.kotlin.dto.CustomerDto
import me.dio.system.credit.kotlin.entity.Address
import me.dio.system.credit.kotlin.entity.Customer
import me.dio.system.credit.kotlin.repository.CustomerRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerResourceTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/customers"
    }

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    fun `should create a customer and return 201 status`(){
        //giver
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
            .content(valueAsString)).andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Hugo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Souza"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("05056881155"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("hugo@gmail.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("79013330"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua Mundo da Lua"))
            .andDo(MockMvcResultHandlers.print())
    }

    fun `should create customer with same unique parameters`(){
        //given
        customerRepository.save(buildCustomerDto().toEntity())
        val customerDto: CustomerDto = buildCustomerDto()
        val valueAsString: String = objectMapper.writeValueAsString(customerDto)
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
            .contentType(MediaType.APPLICATION_JSON).content(valueAsString))
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Conflict! Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(409))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class org.springframework.dao.DataIntegrityViolationException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }


    private fun buildCustomerDto(
        firstName: String = "Hugo",
        lastName: String = "Souza",
        password: String = "123456",
        cpf: String = "05056881155",
        email: String = "hugo@gmail.com",
        cep: String = "79013330",
        street: String = "Rua Mundo da Lua",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        cep = cep,
        street = street,
        income = income,
        password = password
    )
}