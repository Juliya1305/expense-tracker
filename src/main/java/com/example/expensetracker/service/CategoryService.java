package com.example.expensetracker.service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Говорим Spring, что это сервисный класс
@RequiredArgsConstructor // Lombok создаст конструктор для всех final-полей (для внедрения зависимостей)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Категория с id " + id + " не найдена"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Expense> getExpensesByCategoryId(Long categoryId){
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new RuntimeException("Категория с id " + categoryId + " не найдена"));
        return category.getExpenses();
    }
}