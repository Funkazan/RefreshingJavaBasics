package app;

import annotations.TaskStatus;

public class TaskProcessor {

    @TaskStatus(assignedTo = "Alice", status = (String) "IN_PROGRESS", estimatedHours = 8)
    public void processData() {
        //data processing logic
    }

    @TaskStatus(assignedTo = "Bob", status = "DONE", estimatedHours = 4)
    public void generateReport() {
        // Report generation logic
    }

    public void utilityMethod() {
        // method logic
    }

    @TaskStatus(assignedTo = "Charlie", status = "TODO", estimatedHours = 12)
    private void internalCalculation() {
        // private method logic
    }
}