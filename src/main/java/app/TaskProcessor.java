package app;

import annotations.TaskStatus;
import enums.Status;

public class TaskProcessor {

    @TaskStatus(assignedTo = "Alice", status = (String) "IN_PROGRESS", estimatedHours = 8)
    public void processData() {
        // Logik zur Datenverarbeitung
    }

    @TaskStatus(assignedTo = "Bob", status = "DONE", estimatedHours = 4)
    public void generateReport() {
        // Logik zur Berichtsgenerierung
    }

    public void utilityMethod() {
        // Eine normale Methode ohne Annotation
    }

    @TaskStatus(assignedTo = "Charlie", status = "TODO", estimatedHours = 12)
    private void internalCalculation() {
        // Private Methode, die auch eine Annotation haben kann
    }
}