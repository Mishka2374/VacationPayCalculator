package com.pma.calculator.controller;

import com.pma.calculator.using.VacationCalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  Контроллер Spring Boot.
 *  Он отвечает за обработку HTTP-запросов, связанных с расчетами отпуска.
 *  1. RestController: аннотация означает, что возвращаемые контроллером значения
 *  автоматически сериализуются в JSON и передаются обратно в HttpResponse объект.
 *  2. RequestMapping: эта аннотация сопоставляет все HTTP-запросы к методам внутри этого контроллера.
 *  3. Autowired: отвечает за бизнес-логику, связанную с расчетами отпусков.
 *  4. GetMapping: аннотация сопоставляет HTTP GET-запросы с неким путем к неким методам.
 *
 * @author Mishka2374
 */

@RestController
@RequestMapping("/vacation")
public class VacationCalculatorController {
    private final VacationCalculatorService vacationCalculatorService;

    @Autowired
    public VacationCalculatorController(VacationCalculatorService vacationCalculatorService) {
        this.vacationCalculatorService = vacationCalculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> calculateVacation(@RequestBody VacationCalculationRequest request) {
        Double vacationSum = vacationCalculatorService.calculateVacationSum(request);
        return ResponseEntity.ok("Сумма отпускных: " + vacationSum);
    }

    /*
    // Тело json в простом случае, не подключая праздничные дни
    // {
    //    "averageSalary": 1000.0,
    //    "vacationDays": 20,
    // }
    @GetMapping("/calculate")
    public ResponseEntity<Double> calculateVacation(@RequestBody VacationCalculationRequest request) {
        double vacationSum = vacationCalculatorService.calculateVacationSum(request.averageSalary, request.vacationDays);
        return ResponseEntity.ok(vacationSum);
    }
    */
}

/**
 * Postman делает запросы к айпи сервису, само находится на http://localhost:8080/vacation/calculate
 * далее в проге postman  делаю запрос на json по телу за весь запрос
 * {
 *     "averageSalary": 1000.0,
 *     "vacationDays": 20,
 *     "startDate": "2024-01-01",
 *     "endDate": "2024-01-20"
 * }
 */

