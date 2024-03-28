package com.pma.calculator.controller;

import com.pma.calculator.using.HolidayConfiguration;
import com.pma.calculator.using.VacationCalculationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;

/**
 *  Сервис Spring Boot, который рассчитывает общую сумму отпускных на основе
 *  средней заработной платы и количества рабочих дней между двумя датами.
 *  Он исключает выходные и праздничные дни из расчета рабочих дней.
 *
 * @author Mishka2374
 */
@Service
public class VacationCalculatorService {

    /**
     * Метод вычисляет общую сумму отпускных для данного запроса. Сначала определяется
     * количество рабочих дней между датами начала и окончания, указанными в запросе,
     * с использованием метода getWorkingDaysBetweenTwoDates. Затем он умножает среднюю
     * зарплату из запроса на количество рабочих дней для расчета общей суммы отпускных.
     *
     * @param request DTO объект
     * @return общая сумма отпускных
     */
    public Double calculateVacationSum(VacationCalculationRequest request) {
        int workDays = getWorkingDaysBetweenTwoDates(request.getStartDate(), request.getEndDate());
        return request.getAverageSalary() * workDays;
    }

    /**
     * Метод вычисляет количество рабочих дней между двумя датами.
     *
     * @param startDate Дата начала периода каникул
     * @param endDate Дата окончания периода каникул
     * @return Количество рабочих дней, которое используется для расчета отпускных
     */
    private int getWorkingDaysBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        return (int) IntStream.range(0, (int) ChronoUnit.DAYS.between(startDate, endDate) + 1)
                .filter(i -> {
                    LocalDate date = startDate.plusDays(i);
                    return date.getDayOfWeek().getValue() < 6 && !HolidayConfiguration.isHoliday(date);
                })
                .count();
    }

    /*
    //для простого случая
    public Double calculateVacationSum(Double averageSalary, Integer vacationDays) {
        return (averageSalary * vacationDays) / 365;
    } */
}

