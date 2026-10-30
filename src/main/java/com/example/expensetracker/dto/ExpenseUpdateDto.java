package com.example.expensetracker.dto;

import java.time.LocalDate;

public record ExpenseUpdateDto(
        String title,
        Double amount,
        String description,
        LocalDate expenseDate,
        Long categoryId
) { }
