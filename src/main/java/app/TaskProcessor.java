package app;

import annotations.TaskStatus;

/**
 * The {@code TaskProcessor} class contains methods for processing tasks,
 * generating reports, and performing internal calculations. Some methods are
 * annotated with {@link annotations.TaskStatus} to indicate their assignment,
 * current status, and estimated hours required for completion.
 *
 * 
 * Methods:
 * 
 *   {@code processData()} - Processes data and is currently in progress.
 *   {@code generateReport()} - Generates a report and is marked as done.
 *   {@code utilityMethod()} - Provides utility logic (not annotated).
 *   {@code internalCalculation()} - Performs internal calculations and is marked as to-do.
 * 
 * 
 */
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