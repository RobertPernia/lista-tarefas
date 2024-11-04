package com.robert.listatarefas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val editTextTask: EditText = findViewById(R.id.editTextTask)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)

        // Configurando o Adapter
        taskAdapter = TaskAdapter(tasks) { task -> deleteTask(task) }
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adicionando o clique no botão
        buttonAdd.setOnClickListener {
            val taskDescription = editTextTask.text.toString()
            if (taskDescription.isNotBlank()) {
                addTask(Task(taskDescription))
                editTextTask.text.clear()
            }
        }
    }

    // Método para adicionar uma tarefa
    private fun addTask(task: Task) {
        tasks.add(task)
        taskAdapter.notifyItemInserted(tasks.size - 1)
    }

    // Método para excluir uma tarefa
    private fun deleteTask(task: Task) {
        val position = tasks.indexOf(task)
        if (position >= 0) {
            tasks.removeAt(position)
            taskAdapter.notifyItemRemoved(position)
        }
    }
}
