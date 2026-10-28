package com.example.expensetracker.service;

import com.example.expensetracker.dto.ExpenseRequestDto;
import com.example.expensetracker.dto.ExpenseResponseDto;
import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Говорим Spring, что это сервисный класс
@RequiredArgsConstructor // Lombok создаст конструктор для всех final-полей (для внедрения зависимостей)
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public List<Expense> getAllExpenses(){return expenseRepository.findAll();}

    private ExpenseResponseDto convertToDto(Expense expense) {
        String categoryName = null;
        if(expense.getCategory() != null){
            categoryName = expense.getCategory().getName();
        }
        return new ExpenseResponseDto(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getExpenseDate(),
                categoryName
        );
    }

    public List<ExpenseResponseDto> getAllExpenseResponseDto(){
        return expenseRepository.findAll().stream()
                .map(this :: convertToDto)
                .collect(Collectors.toList());
    }

    public Expense createExpense(ExpenseRequestDto expenseDto){
        Expense expense = new Expense();
        expense.setTitle(expenseDto.title());
        expense.setDescription(expenseDto.description());
        expense.setAmount(expenseDto.amount());
        expense.setExpenseDate(expenseDto.expenseDate());


        if(expenseDto.categoryId() != null){
            Long categoryId = expenseDto.categoryId();
            Category findCategory = categoryRepository.findById(categoryId).
                    orElseThrow(() -> new RuntimeException("Категория с id " + categoryId + "не найдена"));
            expense.setCategory(findCategory);
        }
        return expenseRepository.save(expense);
    }

    public ExpenseResponseDto getExpenseDtoById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Расход с id " + id + " не найден"));
        String categoryName = null;

        if(expense.getCategory() != null){
            categoryName = expense.getCategory().getName();
        }

        return new ExpenseResponseDto(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getExpenseDate(),
                categoryName
        );
    }

    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

}
