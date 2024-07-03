package com.example.todoapp.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todoapp.DAO.TODODAO;
import com.example.todoapp.Entities.Todo;

public class DatabaseBuilder {


    @Database(entities = {Todo.class}, version = 1)
    public abstract static class TodoDatabase extends RoomDatabase {

        private static TodoDatabase instance;

        public abstract TODODAO todoDao();

        public static synchronized TodoDatabase getInstance(Context context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                                TodoDatabase.class, "todo_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }
    }
}