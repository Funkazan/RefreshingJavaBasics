package phonebook; // new package for phonebook management

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map; // for Map.Entry

public class PhonebookManager {

    private HashMap<String, String> members;
    private final String filename;

    public PhonebookManager() {
        this("phonebook.txt"); // default filename
    }

    public PhonebookManager(String filename) {
        this.members = new HashMap<>();
        this.filename = filename;
        loadMembers();
    }

    // loads members from the file
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
            System.out.println("Phonebook from '" + filename + "' loads.");
        } catch (IOException e) {
            System.err.println("Phonebook '" + filename + "' not found, start new.");
        }
    }

    // saves members to the file
    public void saveMembers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : members.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Phonebook in '" + filename + "' saved.");
        } catch (IOException e) {
            System.err.println("Error while saving the phonebook: " + e.getMessage());
        }
    }

    // adds a new entry
    public boolean addMember(String name, String phoneNumber) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Name is not allowed to be null!");
            return false;
        }
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.err.println("Phonenumber is not allowed to be null!");
            return false;
        }
        if (members.containsKey(name.trim())) {
            System.err.println("Member '" + name.trim() + "' exists already. Use 'Edit' to change.");
            return false;
        }
        members.put(name.trim(), phoneNumber.trim());
        System.out.println("Member '" + name.trim() + "' added.");
        return true;
    }

    // edit an existing entry
    public boolean editMember(String name, String newPhoneNumber) {
        if (!members.containsKey(name.trim())) {
            System.err.println("Member '" + name.trim() + "' not found.");
            return false;
        }
        if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
            System.err.println("New number is not allowed to be null!");
            return false;
        }
        members.put(name.trim(), newPhoneNumber.trim());
        System.out.println("Phonenumber of '" + name.trim() + "' updated.");
        return true;
    }

    // remove an entry
    public boolean removeMember(String name) {
        if (!members.containsKey(name.trim())) {
            System.err.println("Member '" + name.trim() + "' not found.");
            return false;
        }
        members.remove(name.trim());
        System.out.println("Member '" + name.trim() + "' removed.");
        return true;
    }

    // show all entries
    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("Phonebook is empty.");
            return;
        }
        System.out.println("\n--- Current Members ---");
        // sorted display
        members.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        System.out.println("-------------------------");
    }

    // Getter, if needed
    public Map<String, String> getMembers() {
        return new HashMap<>(members); // returns a copy to avoid external modification
    }
}