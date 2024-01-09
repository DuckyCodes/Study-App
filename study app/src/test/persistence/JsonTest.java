package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;


import model.StudyMethod;
public class JsonTest {
    protected void checkStudyMethod(String name, String description, long time, StudyMethod studyMethod) {
        assertEquals(name, studyMethod.getName());
        assertEquals(description, studyMethod.getDescription());
        assertEquals(time, studyMethod.getTime());
    }
}