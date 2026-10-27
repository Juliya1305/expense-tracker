package com.example.expensetracker.controller;
import com.example.expensetracker.dto.ExpenseResponseDto;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Говорим Spring, что этот класс обрабатывает HTTP-запросы и возвращает JSON
@RequestMapping("/api/v1/expenses") // Базовый путь для всех методов в этом классе
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    //@GetMapping
    // List<Expense> getAllExpenses(){
       // return expenseService.getAllExpenses();
    //}

    @GetMapping
    public List<ExpenseResponseDto> getAllExpenseResponseDto(){
        return expenseService.getAllExpenseResponseDto();
    }

    @GetMapping("/{id}/dto")
    public ExpenseResponseDto getExpenseDtoById(@PathVariable Long id){
        return expenseService.getExpenseDtoById(id);
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense){
        return expenseService.createExpense(expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }
}
