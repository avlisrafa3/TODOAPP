package com.example.todoapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoapp.DAO.TODODAO;
import com.example.todoapp.Entities.Todo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {

    private TODODAO todoDao;
    private LiveData<List<Todo>> allTodos;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public TodoRepository(Application application) {
        DatabaseBuilder.TodoDatabase db = DatabaseBuilder.TodoDatabase.getInstance(application);
        todoDao = db.todoDao();
        allTodos = todoDao.getAllTodos();
    }

    public LiveData<List<Todo>> getAllTodos() {
        return allTodos;
    }

    public void insert(Todo todo) {
        executorService.execute(() -> todoDao.insert(todo));
    }

    public void update(Todo todo) {
        executorService.execute(() -> todoDao.update(todo));
    }

    public void delete(Todo todo) {
        executorService.execute(() -> todoDao.delete(todo));
    }
}