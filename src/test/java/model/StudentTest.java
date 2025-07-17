package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentTest {
    //to test the introduce()-method, which accesses to System.out.println
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    // --- tests for constructor and getter ---

    @Test
    @DisplayName("student-constructor should set the name, age and subject correctly")
    void constructor_ShouldSetAttributesCorrectly() {
        Student student = new Student("Anna", 20, "Computer Sciences");

        // to check, attributes being inherited correctly
        assertEquals("Anna", student.getName(), "the name should be Anna");
        assertEquals(20, student.getAge(), "the age should be 20");

        // to check, the specific attribute been set correctly
        // this works only, if the getter getSubject() have been added
        assertEquals("Computer Sciences", student.getSubject(), "the subject should be Computer Sciences");
    }

    @Test
    @DisplayName("getSubject should return the subject which has been set")
    void getSubject_ShouldReturnCorrectSubject() {
        Student student = new Student("Ben", 22, "Physics");
        assertEquals("Physics", student.getSubject(), "getSubject should return 'Physics'");
    }

    // --- to test the introduce()-method (System.out.println Output)
    @Test
    @DisplayName("introduce() should print out the correct message")
    void introduce_ShouldPrintCorrectIntroduction() {
        Student student = new Student("Clara", 21, "Mathematics");
        student.introduce(); // calls the method, which should access the System.out.println

        String expectedOutput = "Hello, my name is Clara and I am 21 years old. I'm currently studying Mathematics." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "the message should be correct");
    }

    @Test
    @DisplayName("introduce() should print out different student data correctly")
    void introduce_ShouldHandleDifferentStudentData() {
        Student student = new Student("David", 19, "Art History");
        student.introduce();

        String expectedOutput = "Hello, my name is David and I am 19 years old. I'm currently studying Art History." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "The message should be correct.");
    }
}
