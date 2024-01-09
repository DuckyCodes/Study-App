package persistence;

import model.StudyAnalytics;
import model.StudyMethod;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StudyAnalytics analytics = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Pass: We expect an IOException because the file doesn't exist.
        }
    }

    @Test
    void testReaderEmptyStudyAnalytics() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyStudyAnalytics.json");
        try {
            StudyAnalytics analytics = reader.read();
            List<StudyMethod> studyMethods = analytics.getStudyMethods();
            assertEquals(0, studyMethods.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralStudyAnalytics() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralStudyAnalytics.json");
        try {
            StudyAnalytics analytics = reader.read();
            List<StudyMethod> studyMethods = analytics.getStudyMethods();
            assertEquals(2, studyMethods.size());

            checkStudyMethod("Study Method 1 Name", "Study Method 1 Description", 60, studyMethods.get(0));
            checkStudyMethod("Study Method 2 Name", "Study Method 2 Description", 90, studyMethods.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
