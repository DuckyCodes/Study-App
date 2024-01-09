package persistence;

import model.StudyAnalytics;
import model.StudyMethod;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            List<StudyMethod> studyMethods = createSampleStudyMethods();
            StudyAnalytics analytics = new StudyAnalytics(studyMethods);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudyAnalytics() {
        try {
            StudyAnalytics analytics = new StudyAnalytics(new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStudyAnalytics.json");
            writer.open();
            writer.write(analytics);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStudyAnalytics.json");
            analytics = reader.read();
            assertEquals(0, analytics.getStudyMethods().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudyAnalytics() {
        try {
            List<StudyMethod> studyMethods = createSampleStudyMethods(); // Create some sample data
            StudyAnalytics analytics = new StudyAnalytics(studyMethods);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudyAnalytics.json");
            writer.open();
            writer.write(analytics);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudyAnalytics.json");
            analytics = reader.read();
            List<StudyMethod> loadedStudyMethods = analytics.getStudyMethods();
            assertEquals(studyMethods.size(), loadedStudyMethods.size());

             assertEquals("Study Method 1 Name", loadedStudyMethods.get(0).getName());
             assertEquals("Study Method 1 Description", loadedStudyMethods.get(0).getDescription());
             assertEquals(60, loadedStudyMethods.get(0).getTime());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    private List<StudyMethod> createSampleStudyMethods() {
        List<StudyMethod> studyMethods = new ArrayList<>();
        studyMethods.add(new StudyMethod("Study Method 1 Name", "Study Method 1 Description", 60));
        studyMethods.add(new StudyMethod("Study Method 2 Name", "Study Method 2 Description", 90));

        return studyMethods;
    }
}
