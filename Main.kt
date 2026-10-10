import java.util.Scanner

// Represents one task and its completion status.
class Task(
    val title: String,
    var isCompleted: Boolean = false
)

// Adds a new task to the task list after checking its title.
fun addTask(tasks: MutableList<Task>, scanner: Scanner) {
    print("Enter task title: ")
    val title = scanner.nextLine().trim()

    if (title.isNotBlank()) {
        val task = Task(title)
        tasks.add(task)
        println("Task added successfully!")
    } else {
        println("Task title cannot be empty.")
    }
}

// Displays every task and its current completion status.
fun viewTasks(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("Your task list is empty.")
        return
    }

    println("\n===== YOUR TASKS =====")

    for ((index, task) in tasks.withIndex()) {
        val status = when (task.isCompleted) {
            true -> "Completed"
            false -> "Not completed"
        }

        println("${index + 1}. ${task.title} - $status")
    }

    println("Total tasks: ${tasks.size}")
}

// Marks a selected task as completed.
fun completeTask(tasks: MutableList<Task>, scanner: Scanner) {
    if (tasks.isEmpty()) {
        println("There are no tasks to complete.")
        return
    }

    viewTasks(tasks)
    print("Enter the number of the task to complete: ")
    val input = scanner.nextLine().trim()
    val taskNumber = input.toIntOrNull()

    if (taskNumber == null) {
        println("Please enter a valid number.")
        return
    }

    if (taskNumber in 1..tasks.size) {
        val task = tasks[taskNumber - 1]

        if (task.isCompleted) {
            println("This task is already completed.")
        } else {
            task.isCompleted = true
            println("Task marked as completed!")
        }
    } else {
        println("That task number does not exist.")
    }
}

// Removes a selected task from the task list.
fun deleteTask(tasks: MutableList<Task>, scanner: Scanner) {
    if (tasks.isEmpty()) {
        println("There are no tasks to delete.")
        return
    }

    viewTasks(tasks)
    print("Enter the number of the task to delete: ")
    val input = scanner.nextLine().trim()
    val taskNumber = input.toIntOrNull()

    if (taskNumber == null) {
        println("Please enter a valid number.")
        return
    }

    if (taskNumber in 1..tasks.size) {
        val removedTask = tasks.removeAt(taskNumber - 1)
        println("Deleted task: ${removedTask.title}")
    } else {
        println("That task number does not exist.")
    }
}

// Displays a summary of completed and unfinished tasks.
fun showSummary(tasks: MutableList<Task>) {
    var completedCount = 0
    var unfinishedCount = 0

    for (task in tasks) {
        if (task.isCompleted) {
            completedCount++
        } else {
            unfinishedCount++
        }
    }

    println("\n===== TASK SUMMARY =====")
    println("Total tasks: ${tasks.size}")
    println("Completed: $completedCount")
    println("Not completed: $unfinishedCount")
}

// Displays the available menu options.
fun showMenu() {
    println("\n===== KOTLIN TASK MANAGER =====")
    println("1. Add a task")
    println("2. View tasks")
    println("3. Complete a task")
    println("4. Delete a task")
    println("5. View task summary")
    println("6. Exit")
    print("Choose an option: ")
}

// Runs the application and processes the user's menu selections.
fun main() {
    val scanner = Scanner(System.`in`)
    val tasks = mutableListOf<Task>()
    var running = true

    println("Welcome to your Kotlin Task Manager!")

    while (running) {
        showMenu()
        val choice = scanner.nextLine().trim()

        when (choice) {
            "1" -> addTask(tasks, scanner)
            "2" -> viewTasks(tasks)
            "3" -> completeTask(tasks, scanner)
            "4" -> deleteTask(tasks, scanner)
            "5" -> showSummary(tasks)
            "6" -> {
                println("Thank you for using the Task Manager!")
                running = false
            }
            else -> println("Invalid choice. Please select 1–6.")
        }
    }

    scanner.close()
}