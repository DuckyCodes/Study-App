package model;

import model.StudyAnalytics;
import model.StudyMethod;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class StudyAnalyticsTest {

    private StudyAnalytics analytics;

    @Before
    public void setUp() {
        List<StudyMethod> studyMethods = new ArrayList<>();
        studyMethods.add(new StudyMethod("Math", "Algebra", 60));
        studyMethods.add(new StudyMethod("History", "World History", 45));
        analytics = new StudyAnalytics(studyMethods);
    }

    @Test
    public void testAddStudyMethod() {
        analytics.addStudyMethods("Physics", "Mechanics", 30);
        List<StudyMethod> studyMethods = analytics.getStudyMethods();
        boolean found = false;
        for (StudyMethod method : studyMethods) {
            if (method.getName().equals("Physics") && method.getDescription().equals("Mechanics") && method.getTime() == 30) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testRemoveStudyMethod_validIndex() {
        int initialSize = analytics.getStudyMethods().size();
        boolean removed = analytics.removeStudyMethod(0); // Remove the first study method
        assertTrue(removed);
        assertEquals(initialSize - 1, analytics.getStudyMethods().size());
    }

    @Test
    public void testRemoveStudyMethod_invalidIndex() {
        int initialSize = analytics.getStudyMethods().size();
        boolean removed = analytics.removeStudyMethod(-1); // Attempt to remove with an invalid index
        assertFalse(removed);
        assertEquals(initialSize, analytics.getStudyMethods().size());
    }

    @Test
    public void testRemoveStudyMethod_outOfBoundsIndex() {
        int initialSize = analytics.getStudyMethods().size();
        boolean removed = analytics.removeStudyMethod(initialSize + 1); // Attempt to remove with an out-of-bounds index
        assertFalse(removed);
        assertEquals(initialSize, analytics.getStudyMethods().size());
    }



    @Test
    public void testCalculateTotalStudyTime() {
        double totalStudyTime = analytics.calculateTotalStudyTime();
        assertEquals(105.0, totalStudyTime,1);
    }

    @Test
    public void testCalculateAverageStudyTimePerSession() {
        double averageStudyTime = analytics.calculateAverageStudyTimePerSession();
        assertEquals(52.5, averageStudyTime,1);
    }

    @Test
    public void testCalculateAverageStudyTimePerSessionWithEmptyList() {
        List<StudyMethod> emptyList = new ArrayList<>();
        StudyAnalytics emptyAnalytics = new StudyAnalytics(emptyList);
        double averageStudyTime = emptyAnalytics.calculateAverageStudyTimePerSession();
        assertEquals(0.0, averageStudyTime, 0.01);
    }

    @Test
    public void testGetStudyMethods() {
        List<StudyMethod> retrievedStudyMethods = analytics.getStudyMethods();
        assertEquals(2, retrievedStudyMethods.size());
        assertEquals("Math", retrievedStudyMethods.get(0).getName());
        assertEquals("History", retrievedStudyMethods.get(1).getName());
    }


}
