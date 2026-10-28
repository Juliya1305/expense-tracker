package com.example.expensetracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExpenseRequestDto(
        @NotBlank(message = "Название не может быть пустым")
        String title,

        @NotNull(message = "Сумма обязательна")
        @Min(value = 0, message = "Сумма не может быть отрицательной")
        Double amount,

        String description,

        @NotNull(message = "Дата обязательна")
        LocalDate expenseDate,

        Long categoryId
) { }
