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

    // Verwende einen dedizierten Dateinamen für die Tests
    private static final String TEST_FILENAME = "test_phonebook.txt";
    private static final Path TEST_FILE_PATH = Path.of(TEST_FILENAME);
    private PhonebookManager manager;

    /**
     * Führt vor jedem Test aus. Stellt sicher, dass die Testdatei existiert,
     * aber leer ist (simuliert einen neuen Start).
     */
    @BeforeEach
    void setup() throws IOException {
        // Sicherstellen, dass die Datei für jeden Test sauber ist
        Files.deleteIfExists(TEST_FILE_PATH);
        // Instanziiere den Manager mit dem speziellen Test-Dateinamen
        manager = new PhonebookManager(TEST_FILENAME);
    }

    /**
     * Führt nach jedem Test aus. Löscht die Testdatei, um die Umgebung zu bereinigen.
     */
    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(TEST_FILE_PATH);
    }

    // --- TESTFÄLLE FÜR HINZUFÜGEN ---

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

    // --- TESTFÄLLE FÜR BEARBEITEN ---

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

    // --- TESTFÄLLE FÜR ENTFERNEN ---

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

    // --- TESTFÄLLE FÜR SPEICHERN/LADEN ---

    @Test
    @DisplayName("Sollte nach Speichern die korrekten Daten laden")
    void shouldLoadMembersFromFile() throws IOException {
        // 1. Initialisiere und füge Daten hinzu
        manager.addMember("Gaby", "8888");
        manager.addMember("Hans", "9999");
        manager.saveMembers();

        // 2. Erstelle einen NEUEN Manager, um die Daten zu laden
        PhonebookManager newManager = new PhonebookManager(TEST_FILENAME);
        Map<String, String> loadedMembers = newManager.getMembers();

        // 3. Überprüfen
        assertEquals(2, loadedMembers.size());
        assertEquals("8888", loadedMembers.get("Gaby"));
        assertEquals("9999", loadedMembers.get("Hans"));
    }

    @Test
    @DisplayName("Sollte ein leeres Telefonbuch initialisieren, wenn keine Datei existiert")
    void shouldInitializeEmptyWhenNoFileExists() {
        // Der @BeforeEach-Hook löscht die Datei, also sollte der Manager leer starten
        assertEquals(0, manager.getMembers().size());
    }
}