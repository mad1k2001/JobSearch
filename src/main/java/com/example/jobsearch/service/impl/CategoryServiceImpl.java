package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.CategoryDao;
import com.example.jobsearch.model.Category;
import com.example.jobsearch.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = categoryDao.getCategories();
        return categories;
    }
}
