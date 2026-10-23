package com.example.expensetracker.service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Говорим Spring, что это сервисный класс
@RequiredArgsConstructor // Lombok создаст конструктор для всех final-полей (для внедрения зависимостей)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Получить все категории
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Создать новую категорию
    public Category createCategory(Category category) {
        // Тут можно добавить проверку: а нет ли уже категории с таким именем?
        return categoryRepository.save(category);
    }

    // Получить категорию по ID (если не найдена - выбросим ошибку)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Категория с id " + id + " не найдена"));
    }

    // Удалить категорию
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}