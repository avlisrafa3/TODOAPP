package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class TODOAdapter extends RecyclerView.Adapter<TODOAdapter.TODOViewHolder> {

    private List<Todo> todos = new ArrayList<>();

    @NonNull
    @Override
    public TODOViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TODOViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TODOViewHolder holder, int position) {
        Todo currentTodo = todos.get(position);
        holder.todoText.setText(currentTodo.getTodoText());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    class TODOViewHolder extends RecyclerView.ViewHolder {
        private TextView todoText;

        public TODOViewHolder(@NonNull View itemView) {
            super(itemView);
            todoText = itemView.findViewById(R.id.todoText);
        }
    }
}
