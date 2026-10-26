package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Говорим Spring, что это сервисный класс
@RequiredArgsConstructor // Lombok создаст конструктор для всех final-полей (для внедрения зависимостей)
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses(){return expenseRepository.findAll();}

    public Expense createExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(Long id){
        return expenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Расход с id " + id + " не найден"));
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

}
