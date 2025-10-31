package phonebook; // new package for phonebook management

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map; // for Map.Entry
import java.util.stream.Collectors;

/**
 * Manages a phonebook with functionalities to add, edit, remove, display, load, and save members.
 */
public class PhonebookManager {

    private HashMap<String, String> members;
    private final String filename;

    /**
     * Default constructor. Initializes the Manager and loads members from "phonebook.txt".
     */
    public PhonebookManager() {
        this("phonebook.txt"); // default filename
    }

    /**
     * Constructor with custom filename.
     * Mainly for testing purposes.
     * @param filename the file to load/save the phonebook
     */
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

    /**
     * adds a new member to the phonebook.
     * @param name The name of the new member. Is not allowed to be null.
     * @param phoneNumber The phone number of the new member. Is not allowed to be null.
     * @return {@code true} if the member was added successfully, {@code false} otherwise.
     */
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

    /**
     * edits an existing member's phone number.
     * @param name The name of the member to edit.
     * @param newPhoneNumber The new phone number. Is not allowed to be null.
     * @return {@code true} if the member was edited successfully, {@code false} otherwise.
     */
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

    /**
     * removes a member from the phonebook based on the name.
     * @param name The name of the member to remove.
     * @return {@code true} if the member was removed successfully, {@code false} otherwise.
     */
    public boolean removeMember(String name) {
        if (!members.containsKey(name.trim())) {
            System.err.println("Member '" + name.trim() + "' not found.");
            return false;
        }
        members.remove(name.trim());
        System.out.println("Member '" + name.trim() + "' removed.");
        return true;
    }

    /**
     * displays all members in the phonebook, sorted by name.
     */
    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("Phonebook is empty.");
            return;
        }
        System.out.println("\n--- Current Members ---");
        // sorted display
        members.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> System.out.println(entry.getKey() + " => " + entry.getValue()));
        System.out.println("-------------------------");
    }

    /**
     * returns a copy of the members map.
     * @return a map (name -> phone number) of all members
     */
    public Map<String, String> getMembers() {
        return new HashMap<>(members); // returns a copy to avoid external modification
    }

    /**
     * searches for members whose name contains the search term (case insensitive)
     * or whose phone number contains the search term (case sensitive).
     * @param term The search term. Is not allowed to be null.
     */
    public Map<String, String> searchMembers(String term) {
        if (term == null || term.trim().isEmpty()) {
            System.out.println("Search term is not allowed to be null!");
            return new HashMap<>(); // return empty result if term is invalid
        }

        String lowerCaseTerm = term.trim().toLowerCase();

        System.out.println("\n--- Search Results for '" + term + "' ---");

        // filtering with streams API
        Map<String, String> searchResults = members.entrySet().stream()
            .filter(entry -> 
            entry.getKey().toLowerCase().contains(lowerCaseTerm) || //Filter 1: name contains term (case insensitive)
            entry.getValue().contains(term)                        //Filter 2: phone number contains term (case sensitive)
            )
            .collect(Collectors.toMap(
                Map.Entry::getKey, 
                Map.Entry::getValue
            ));

        // Optional: display results
        if (searchResults.isEmpty()) {
            System.out.println("No matches found for '" + term + "'.");
        } else {
            System.out.println("Results for: '" + term + "':");
            searchResults.forEach((name, phone) -> System.out.println(name + " => " + phone));
            System.out.println("-------------------------");
            System.out.println(searchResults.size() + " match(es) found.");
        }
        return searchResults; // return the results for potential further processing
    }
}