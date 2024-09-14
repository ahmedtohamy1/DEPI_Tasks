import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TaskAdapter(emptyList(), onTaskClicked = { task ->
            // Edit task
        }, onTaskChecked = { task, isChecked ->
            taskViewModel.update(task.copy(isDone = isChecked))
        })

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let { adapter.submitList(it) }
        })

        button_add.setOnClickListener {
            // Show dialog to add a new task
        }
    }
}
