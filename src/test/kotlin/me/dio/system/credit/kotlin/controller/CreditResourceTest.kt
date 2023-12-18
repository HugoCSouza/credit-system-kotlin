package me.dio.system.credit.kotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.dio.system.credit.kotlin.dto.CreditDto
import me.dio.system.credit.kotlin.dto.CustomerDto
import me.dio.system.credit.kotlin.entity.Address
import me.dio.system.credit.kotlin.entity.Credit
import me.dio.system.credit.kotlin.entity.Customer
import me.dio.system.credit.kotlin.repository.CreditRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
import java.time.LocalDate
import java.time.Month
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CreditResourceTest {

    @Autowired
    private lateinit var creditRepository: CreditRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/credits"
    }

    @Test
    fun `should create a credit and return 201` (){
        //given
        val customer = buildCustomerDto()
        val creditDto: Credit = buildCreditDTO(customer = customer)
        val valueAsString = objectMapper.writeValueAsString(creditDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Hugo"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Souza"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("05056881155"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("hugo@gmail.com"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.income").value("1000.0"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("79013330"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Rua Mundo da Lua"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print())

    }
    @Test
    fun `should find all credits by customer id` (){

        //given
        val customerId = 1L
        //when
        val creditList: List<Credit> = creditRepository.findAllByCustomer(customerId)
        //then
        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList.size).isEqualTo(1)
    }

    @Test
    fun `should find credit by credit code`() {
        //given
        val creditCode = UUID.fromString("aa547c0f-9a6a-451f-8c89-afddce916a29")
        //when
        val fakeCredit: Credit = creditRepository.findByCreditCode(creditCode)!!
        //then
        Assertions.assertThat(fakeCredit).isNotNull
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
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        address = Address(
            cep = cep,
            street = street,
        ),
        income = income,
        password = password
    )

    private fun buildCreditDTO(
        creditValue: BigDecimal = BigDecimal.valueOf(200000),
        dayFirstInstallment: LocalDate = LocalDate.of(2024, Month.JANUARY, 12),
        numberOfInstallments: Int = 7,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )
}