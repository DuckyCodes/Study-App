package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudyMethodTest {

    private StudyMethod studyMethod;
    private StudyMethod studyMethod1;
    private StudyMethod studyMethod2;
    private StudyMethod studyMethod3;

    @Before
    public void setUp() {

        studyMethod = new StudyMethod("Math", "Algebra", 60);
        studyMethod1 = new StudyMethod("", "", 0);
        studyMethod2 = new StudyMethod("school", "torcher", 10000);
        studyMethod3 = new StudyMethod("cspc121", "good", 10000000);
    }

    @Test
    public void testGetName() {

        assertEquals("Math", studyMethod.getName());
        assertEquals("", studyMethod1.getName());
        assertEquals("school", studyMethod2.getName());
        assertEquals("cspc121", studyMethod3.getName());
    }

    @Test
    public void testGetDescription() {

        assertEquals("Algebra", studyMethod.getDescription());
        assertEquals("", studyMethod1.getDescription());
        assertEquals("torcher", studyMethod2.getDescription());
        assertEquals("good", studyMethod3.getDescription());
    }

    @Test
    public void testGetTime() {

        assertEquals(60, studyMethod.getTime());
        assertEquals(0, studyMethod1.getTime());
        assertEquals(10000, studyMethod2.getTime());
        assertEquals(10000000, studyMethod3.getTime());
    }

}
