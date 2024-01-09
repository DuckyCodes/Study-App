package model;


import org.json.JSONObject;
import persistence.Writable;

// a class for representing a study method with a name, description, and time.
public class StudyMethod implements Writable {

    private String name;
    private String description;
    private long time;
//    StudyTimer timer;

    //Constructor
    //requires names,description, and time  to not be null
    //effects: determing nname descriptino  andn  time of the method
    public StudyMethod(String name, String description, long time) {
        this.name = name;
        this.description  = description;
        this.time  = time;

    }

//    //Setter methods
//    public void setName(String name1) {
//        this.name = name1;
//    }
//
//    public void setDescription(String description1) {
//        this.description = description1;
//    }
//
//    public void time(String time1) {
//        this.time = time1;
//    }

    //Getter methods
    public String getName() {
        return this.name;
    }

//    //startMethod
//    public void startMethod() {
//
//        System.out.println("Starting the study method: " + name);
//        timer.start(); // Start the timer
//    }
//
//    // End the study session
//    public void endMethod() {
//        System.out.println("Ending the study method: " + name);
//        timer.stop(); // Stop the timer
//    }

    public String getDescription() {
        return this.description;
    }

    public long getTime() {
        return this.time;
    }

    //modifies: this
    //effect  : save method
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("time", time);
        return json;
    }


}

