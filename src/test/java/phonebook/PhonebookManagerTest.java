package phonebook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PhonebookManagerTest {

    // test-specified file name to avoid conflicts with real data
    private static final String TEST_FILENAME = "test_phonebook.txt";
    private static final Path TEST_FILE_PATH = Path.of(TEST_FILENAME);
    private PhonebookManager manager;

    /**
     * runs before each test. Initializes a fresh PhonebookManager instance.
     * @throws IOException
     */
    @BeforeEach
    void setup() throws IOException {
        // Sicherstellen, dass die Datei für jeden Test sauber ist
        Files.deleteIfExists(TEST_FILE_PATH);
        // Instanziiere den Manager mit dem speziellen Test-Dateinamen
        manager = new PhonebookManager(TEST_FILENAME);
    }

    /**
     * runs after each test. Cleans up the test file.
     * @throws IOException
     */
    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(TEST_FILE_PATH);
    }

    // --- tests for adding ---

    @Test
    @DisplayName("Sollte einen neuen Member erfolgreich hinzufügen")
    void shouldAddMemberSuccessfully() {
        assertTrue(manager.addMember("Alice", "1111"));
        assertEquals("1111", manager.getMembers().get("Alice"));
        assertEquals(1, manager.getMembers().size());
    }

    @Test
    @DisplayName("Sollte leere Namen oder Nummern ablehnen")
    void shouldNotAddWithEmptyOrNullData() {
        assertFalse(manager.addMember("", "1234"), "Leerer Name sollte fehlschlagen.");
        assertFalse(manager.addMember("Bob", ""), "Leere Nummer sollte fehlschlagen.");
        assertFalse(manager.addMember(null, "5555"), "Null-Name sollte fehlschlagen.");
        assertEquals(0, manager.getMembers().size(), "Es sollte kein Mitglied hinzugefügt worden sein.");
    }

    @Test
    @DisplayName("Sollte das Hinzufügen von Duplikaten ablehnen")
    void shouldNotAddDuplicateMember() {
        manager.addMember("Charlie", "3333");
        assertFalse(manager.addMember("Charlie", "4444"), "Duplikat sollte fehlschlagen.");
        assertEquals("3333", manager.getMembers().get("Charlie"), "Die Originalnummer sollte nicht überschrieben werden.");
    }

    // --- tests for searching ---
    @Test
    @DisplayName("Sollte Mitglieder basierend auf Suchbegriff im Namen oder der Nummer finden")
    void shouldSearchMembersSuccessfully() {
        // Füge zwei eindeutige Einträge hinzu, die beide "Anna" im Namen tragen (oder suche nach einem Teilstring)
        manager.addMember("Anna Müller", "01764567"); 
        manager.addMember("Anna Schmidt", "01511234"); // EINZIGARTIGER NAME
        manager.addMember("Max Meier", "01769999");
        
        // Korrigierte Erwartung: Sollte beide Anna-Einträge finden
        Map<String, String> results = manager.searchMembers("Anna"); 
        
        // Jetzt ist die Erwartung 2 korrekt, da wir zwei verschiedene Namen gespeichert haben
        assertEquals(2, results.size(), "Sollte 2 Einträge mit 'Anna' im Namen finden."); 
        assertTrue(results.containsKey("Anna Müller"));
        assertTrue(results.containsKey("Anna Schmidt"));
        assertFalse(results.containsKey("Max Meier"));
    }   

    // --- tests for editing ---

    @Test
    @DisplayName("Sollte einen bestehenden Member erfolgreich bearbeiten")
    void shouldEditMemberSuccessfully() {
        manager.addMember("David", "5555");
        assertTrue(manager.editMember("David", "5555-neu"));
        assertEquals("5555-neu", manager.getMembers().get("David"));
    }

    @Test
    @DisplayName("Sollte Bearbeiten für nicht existierende Member ablehnen")
    void shouldNotEditNonExistentMember() {
        assertFalse(manager.editMember("NichtExistiert", "1234"));
    }
    
    @Test
    @DisplayName("Sollte Bearbeiten mit leerer Nummer ablehnen")
    void shouldNotEditWithEmptyNewNumber() {
        manager.addMember("Eva", "6666");
        assertFalse(manager.editMember("Eva", ""));
        assertEquals("6666", manager.getMembers().get("Eva"), "Nummer sollte nicht gelöscht werden.");
    }

    // --- tests for removing ---

    @Test
    @DisplayName("Sollte einen bestehenden Member erfolgreich entfernen")
    void shouldRemoveMemberSuccessfully() {
        manager.addMember("Frank", "7777");
        assertTrue(manager.removeMember("Frank"));
        assertNull(manager.getMembers().get("Frank"));
        assertEquals(0, manager.getMembers().size());
    }

    @Test
    @DisplayName("Sollte Entfernen für nicht existierende Member ablehnen")
    void shouldNotRemoveNonExistentMember() {
        assertFalse(manager.removeMember("Unbekannt"));
    }

    // --- tests for saving & loading ---

    @Test
    @DisplayName("Sollte nach Speichern die korrekten Daten laden")
    void shouldLoadMembersFromFile() throws IOException {
        // 1. initialize and save members
        manager.addMember("Gaby", "8888");
        manager.addMember("Hans", "9999");
        manager.saveMembers();

        // 2. create new manager to load from file
        PhonebookManager newManager = new PhonebookManager(TEST_FILENAME);
        Map<String, String> loadedMembers = newManager.getMembers();

        // 3. check loaded data
        assertEquals(2, loadedMembers.size());
        assertEquals("8888", loadedMembers.get("Gaby"));
        assertEquals("9999", loadedMembers.get("Hans"));
    }

    @Test
    @DisplayName("Sollte ein leeres Telefonbuch initialisieren, wenn keine Datei existiert")
    void shouldInitializeEmptyWhenNoFileExists() {
        // the @BeforeEach-Hook already ensures the file does not exist
        assertEquals(0, manager.getMembers().size());
    }
}