// app/TaskProcessor.java
package app;

import annotations.TaskStatus;
import enums.Status;

public class TaskProcessor {

    @TaskStatus(assignedTo = "Alice", status = Status.TODO, estimatedHours = 5)
    public void initializeDatabase() {
        System.out.println("Datenbank wird initialisiert...");
        // Logik zur Datenbankinitialisierung
    }

    @TaskStatus(assignedTo = "Bob", status = Status.IN_PROGRESS) // estimatedHours nutzt den Standardwert
    public void processUserData() {
        System.out.println("Benutzerdaten werden verarbeitet...");
        // Logik zur Benutzerdatenverarbeitung
    }

    @TaskStatus(assignedTo = "Alice", status = Status.DONE, estimatedHours = 3)
    public void generateReport() {
        System.out.println("Bericht wird generiert...");
        // Logik zur Berichterstellung
    }
}