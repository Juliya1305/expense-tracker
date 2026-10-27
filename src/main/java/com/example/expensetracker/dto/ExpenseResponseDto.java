package com.example.expensetracker.dto;
import java.time.LocalDate;

public record ExpenseResponseDto(Long id,
                                 String title,
                                 Double amount,
                                 LocalDate expenseDate,
                                 String categoryName
) { }
