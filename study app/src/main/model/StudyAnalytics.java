package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import persistence.Writable;

//a class representing the annalytics of  study method such as total time avg time.
public class StudyAnalytics implements Writable {

    private List<StudyMethod> studyMethods;


    //Constructor
    //effects: gives out a list of study methods
    public StudyAnalytics(List<StudyMethod> studyMethods) {
        this.studyMethods = studyMethods;
    }

    // Methods for study analytics and insights
    //requires getTime >= 0
    //Modifies: this
    //Effects: Calculate the total study time across all study sessions
    public double calculateTotalStudyTime() {
        double totalStudyTime = 0.0;
        for (StudyMethod x : studyMethods) {
            totalStudyTime += x.getTime();
        }
        return totalStudyTime;
    }

    // Modifies: this
    // Effects: Calculate the average study time per session
    public double calculateAverageStudyTimePerSession() {
        double totalStudyTime = calculateTotalStudyTime();
        if (studyMethods.isEmpty()) {
            return 0.0; // Avoid division by zero
        }
        return totalStudyTime / studyMethods.size();
    }

    // Method to get a list of study methods
    public List<StudyMethod> getStudyMethods() {
        return studyMethods;
    }

    //modifies: this
    //effecs add study methods to the array
    public void addStudyMethods(String name, String description, long time)  {
        StudyMethod studyMethod = new StudyMethod(name, description, time);
        studyMethods.add(studyMethod);
        EventLog.getInstance().logEvent(new Event("Study Method added"));
    }

    public boolean removeStudyMethod(int studyMethodIndex) {
        if (studyMethodIndex >= 0 && studyMethodIndex < studyMethods.size()) {
            studyMethods.remove(studyMethodIndex);
            EventLog.getInstance().logEvent(new Event("Study Method Removed"));
            return true;
        }
        return false;
    }


    // Effects: Calculate the GPA based on course grades
    //    public double calculateGPA() {
    //        //stub
    //    }
    // maybe add more study analytics and insights
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studyMethods", studyMethodsToJson());
        EventLog.getInstance().logEvent(new Event("Study Method Save"));
        return json;
    }

    // EFFECTS: returns things in this study methods as a JSON array
    private JSONArray studyMethodsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (StudyMethod t : studyMethods) {
            jsonArray.put(t.toJson());
        }
        EventLog.getInstance().logEvent(new Event("Study Method Load"));
        return jsonArray;
    }
}
