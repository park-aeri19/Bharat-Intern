

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTask;
    private RecyclerView recyclerViewTasks;
    private List<TodoItem> todoList;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        todoList = new ArrayList<>();
        adapter = new TodoAdapter(todoList);
        recyclerViewTasks.setAdapter(adapter);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = editTextTask.getText().toString().trim();
                if (!taskName.isEmpty()) {
                    todoList.add(new TodoItem(taskName));
                    adapter.notifyDataSetChanged();
                    editTextTask.getText().clear();
                }
            }
        });
    }

    public class TodoItem {
        private String taskName;
        private boolean isCompleted;

        public TodoItem(String taskName) {
            this.taskName = taskName;
            this.isCompleted = false; // By default, tasks are not completed
        }

        public String getTaskName() {
            return taskName;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void setCompleted(boolean completed) {
            isCompleted = completed;
        }
    }

    public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
        private List<TodoItem> todoList;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textViewTask;

            public ViewHolder(View itemView) {
                super(itemView);
                textViewTask = itemView.findViewById(R.id.textViewTask);
            }
        }

        public TodoAdapter(List<TodoItem> todoList) {
            this.todoList = todoList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            TodoItem todoItem = todoList.get(position);
            holder.textViewTask.setText(todoItem.getTaskName());
        }

        @Override
        public int getItemCount() {
            return todoList.size();
        }
    }
}
