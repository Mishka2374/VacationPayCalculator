package com.pma.calculator.controller;

import com.pma.calculator.using.VacationCalculationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VacationCalculatorController.class)
public class VacationCalculatorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VacationCalculatorService vacationCalculatorService;

    @Test
    public void testCalculateVacation() throws Exception {
        VacationCalculationRequest request = new VacationCalculationRequest(1000.0, 20, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 20));
        Double expectedVacationSum = 20000.0;
        when(vacationCalculatorService.calculateVacationSum(request)).thenReturn(expectedVacationSum);

        mockMvc.perform(MockMvcRequestBuilders.post("/vacation/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"averageSalary\": 1000.0, \"vacationDays\": 20, \"startDate\": \"2024-01-01\", \"endDate\": \"2024-01-20\"}"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().json("20000.0"));
    }


}

