package com.pma.calculator.using;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Класс предназначен для управления конфигурациями отпусков для приложения калькулятора отпусков.
 * Он использует блок статического инициализатора для загрузки праздничных данных из файла JSON и
 * сохранения их в Set(LocalDate) для эффективного поиска.
 *
 * @author Mishka2374
 */
public class HolidayConfiguration {

    private static final Set<LocalDate> HOLIDAYS = new HashSet<>();

    /**
     * Статический блок считывает данные праздничных дней из файла JSON.
     * Метод считывает все содержимое файла JSON в массив байтов, который
     * затем преобразуется в строку. Эта строка представляет собой
     * JSON-представление праздничных данных.
     */
    static {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/holidays.json")));
            Holiday[] holidays = new ObjectMapper().readValue(json, Holiday[].class);
            for (Holiday holiday : holidays) {
                HOLIDAYS.add(LocalDate.parse(holiday.getDate()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверяет, является ли данная дата праздничной, проверяя, существует ли она в HOLIDAYS наборе.
     *
     * @param date - дата, подозрительная на праздничную
     * @return true - праздничная дата, false - непраздничная дата
     */
    public static boolean isHoliday(LocalDate date) {
        return HOLIDAYS.contains(date);
    }

    // Класс-помошник для хранения данных об отпуске
    private static class Holiday {
        private String date;
        private String name;

        public void setDate(String date) {
            this.date = date;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public String getName() {
            return name;
        }

    }
}


