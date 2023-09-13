package com.amelinroman.restapitz.controller;


import com.amelinroman.restapitz.exception.InvalidInputException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Amelin Roman
 * Этот класс представляет собой контроллер REST для обработки запросов, связанных с символами.
 */

@RestController
public class CharacterController {

    /**
     * Получает Map, содержащую количество каждого символа в предоставленной входной строке.
     * Входная строка должна содержать только буквенные символы.
     *
     * @param input входная строка.
     * @return Map с символами в качестве ключей и их количеством в качестве значений, отсортированных в порядке убывания количества.
     * @throws InvalidInputException если входная строка содержит неалфавитные символы.
     */

    @GetMapping("/symbols")
    public Map<Character, Integer> getCountSymbols(@RequestParam String input) {

        // Проверяем, содержит ли входная строка неалфавитные символы
        if (!input.matches("[a-zA-Z]+")) {
            throw new InvalidInputException("Входная строка должна сожержать только буквы");
        }

        // Преобразуем входную строку в нижний регистр, преобразуем ее в поток символов,
        // группируем символы по количеству, сортируем их по убыванию количества,
        // и собираем их в LinkedHashMap, чтобы сохранить порядок
        return input.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.summingInt(c -> 1)))
                .entrySet().stream()
                .sorted(Map.Entry.<Character,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldVal, newVal) -> oldVal, LinkedHashMap::new));
    }
}
