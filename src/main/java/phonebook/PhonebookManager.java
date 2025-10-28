package phonebook; // Ein neues Paket für die Telefonbuchlogik

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map; // Für Map.Entry.comparingByKey()

public class PhonebookManager {

    private HashMap<String, String> members;
    private final String filename;

    public PhonebookManager() {
        this("phonebook.txt"); // Standard-Dateiname
    }

    public PhonebookManager(String filename) {
        this.members = new HashMap<>();
        this.filename = filename;
        loadMembers();
    }

    // Lädt Mitglieder aus der Datei
    private void loadMembers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        members.put(parts[0], parts[1]);
                    }
                }
            }
            System.out.println("Telefonbuch aus '" + filename + "' geladen.");
        } catch (IOException e) {
            System.err.println("Kein vorhandenes Telefonbuch '" + filename + "' gefunden, starte frisch.");
        }
    }

    // Speichert Mitglieder in die Datei
    public void saveMembers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : members.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Telefonbuch in '" + filename + "' gespeichert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern des Telefonbuchs: " + e.getMessage());
        }
    }

    // Fügt einen neuen Eintrag hinzu
    public boolean addMember(String name, String phoneNumber) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Name darf nicht leer sein!");
            return false;
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.err.println("Telefonnummer darf nicht leer sein!");
            return false;
        }
        if (members.containsKey(name.trim())) {
            System.err.println("Mitglied '" + name.trim() + "' existiert bereits. Verwenden Sie 'Bearbeiten' für Änderungen.");
            return false;
        }
        members.put(name.trim(), phoneNumber.trim());
        System.out.println("Mitglied '" + name.trim() + "' hinzugefügt.");
        return true;
    }

    // Bearbeitet einen bestehenden Eintrag
    public boolean editMember(String name, String newPhoneNumber) {
        if (!members.containsKey(name.trim())) {
            System.err.println("Mitglied '" + name.trim() + "' nicht gefunden.");
            return false;
        }
        if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
            System.err.println("Neue Telefonnummer darf nicht leer sein!");
            return false;
        }
        members.put(name.trim(), newPhoneNumber.trim());
        System.out.println("Telefonnummer für '" + name.trim() + "' aktualisiert.");
        return true;
    }

    // Entfernt einen Eintrag
    public boolean removeMember(String name) {
        if (!members.containsKey(name.trim())) {
            System.err.println("Mitglied '" + name.trim() + "' nicht gefunden.");
            return false;
        }
        members.remove(name.trim());
        System.out.println("Mitglied '" + name.trim() + "' entfernt.");
        return true;
    }

    // Zeigt alle Mitglieder an
    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("Das Telefonbuch ist leer.");
            return;
        }
        System.out.println("\n--- Aktuelle Mitglieder ---");
        // Sortierte Ausgabe (optional, aber gut für Übersicht)
        members.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        System.out.println("-------------------------");
    }

    // Getter, falls du die HashMap in Tests direkt prüfen möchtest
    public Map<String, String> getMembers() {
        return new HashMap<>(members); // Gibt eine Kopie zurück, um die Kapselung zu wahren
    }
}