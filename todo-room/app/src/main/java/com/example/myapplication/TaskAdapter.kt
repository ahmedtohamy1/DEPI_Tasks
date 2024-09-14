import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private val tasks: List<Task>,
    private val onTaskClicked: (Task) -> Unit,
    private val onTaskChecked: (Task, Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.text_title)
        val description: TextView = itemView.findViewById(R.id.text_description)
        val dueDate: TextView = itemView.findViewById(R.id.text_due_date)
        val isDone: CheckBox = itemView.findViewById(R.id.checkbox_done)

        fun bind(task: Task) {
            title.text = task.title
            description.text = task.description
            dueDate.text = task.dueDate.toString()
            isDone.isChecked = task.isDone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
        holder.itemView.setOnClickListener { onTaskClicked(task) }
        holder.isDone.setOnCheckedChangeListener { _, isChecked ->
            onTaskChecked(task, isChecked)
        }
    }

    override fun getItemCount() = tasks.size
}
