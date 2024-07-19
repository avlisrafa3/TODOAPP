package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Entities.Todo;

import java.util.ArrayList;
import java.util.List;

public class TODOAdapter extends RecyclerView.Adapter<TODOAdapter.TODOViewHolder> {

    private List<Todo> todos = new ArrayList<>();
    private OnItemClickListener listener;
    private int editingPosition = -1;

    public TODOAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

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

        if (position == editingPosition) {
            holder.todoText.setVisibility(View.GONE);
            holder.editTodoText.setVisibility(View.VISIBLE);
            holder.updateButton.setVisibility(View.VISIBLE);
            holder.editTodoText.setText(currentTodo.getTodoText());
        } else {
            holder.todoText.setVisibility(View.VISIBLE);
            holder.editTodoText.setVisibility(View.GONE);
            holder.updateButton.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (editingPosition != position) {
                notifyItemChanged(editingPosition);
                editingPosition = position;
                notifyItemChanged(editingPosition);
            }
        });

        holder.updateButton.setOnClickListener(v -> {
            String updatedText = holder.editTodoText.getText().toString();
            if (!updatedText.isEmpty()) {
                currentTodo.setTodoText(updatedText);
                if (listener != null) {
                    listener.onItemUpdate(currentTodo);
                }
                editingPosition = -1;
                notifyDataSetChanged();
            }
        });

        holder.deleteButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemDelete(currentTodo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemUpdate(Todo todo);
        void onItemDelete(Todo todo);
    }

    class TODOViewHolder extends RecyclerView.ViewHolder {
        private TextView todoText;
        private EditText editTodoText;
        private Button updateButton;
        private Button deleteButton;

        public TODOViewHolder(@NonNull View itemView) {
            super(itemView);
            todoText = itemView.findViewById(R.id.todoText);
            editTodoText = itemView.findViewById(R.id.editTodoText);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
