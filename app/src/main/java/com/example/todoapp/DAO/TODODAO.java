package com.example.todoapp.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.Entities.Todo;

import java.util.List;

@Dao
public interface TODODAO {
    //Inserts a Vacation object into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Todo todo);

    //Updates an existing Vacation object in the database.
    @Update
    void update(Todo todo);

    //Deletes a Vacation object from the database.
    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getAllTodos();

    @Query("SELECT * FROM todo WHERE todoID = :id")
    LiveData<Todo> getTodoById(int id);
}
