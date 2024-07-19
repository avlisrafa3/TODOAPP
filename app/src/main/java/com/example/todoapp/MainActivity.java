package com.example.todoapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Entities.Todo;
import com.example.todoapp.repository.TodoRepository;

public class MainActivity extends AppCompatActivity {

    private TodoViewModel todoViewModel;
    private TODOAdapter adapter;
    private RecyclerView TODOrecyclerView1;
    private EditText editText;
    private TextView displayTextView;
    private TodoRepository todoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        todoRepository = new TodoRepository(getApplication());
        editText = findViewById(R.id.editText);
        displayTextView = findViewById(R.id.displayTextView);
        TODOrecyclerView1 = findViewById(R.id.TODOrecyclerView1);

        TODOrecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        TODOrecyclerView1.setHasFixedSize(true);

        adapter = new TODOAdapter();
        TODOrecyclerView1.setAdapter(adapter);

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoViewModel.getAllTodos().observe(this, todos -> {
            // Update the cached copy of the todos in the adapter.
            adapter.setTodos(todos);
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> {
            String todoText = editText.getText().toString();
            if (!todoText.isEmpty()) {
                Todo todo = new Todo(todoText);
                todoViewModel.insert(todo);
                editText.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
