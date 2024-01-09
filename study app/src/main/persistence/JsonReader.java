package persistence;

import model.StudyMethod;
import model.StudyAnalytics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a reader that reads StudyAnalytics from JSON data stored in a file
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads StudyAnalytics from the file and returns it; throws IOException
    // if an error occurs reading data from the file
    public StudyAnalytics read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudyAnalytics(jsonObject);
    }

    // EFFECTS: reads the source file as a string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses StudyAnalytics from the JSON object and returns it
    private StudyAnalytics parseStudyAnalytics(JSONObject jsonObject) {
        // Obtain the studyMethods JSON array and create a StudyAnalytics object.
        JSONArray studyMethodsArray = jsonObject.getJSONArray("studyMethods");
        List<StudyMethod> studyMethods = parseStudyMethods(studyMethodsArray);
        return new StudyAnalytics(studyMethods);
    }

    // EFFECTS: parses an array  of  study method from the JSON object and returns it
    private List<StudyMethod> parseStudyMethods(JSONArray jsonArray) {
        List<StudyMethod> studyMethods = new ArrayList<>();

        for (Object json : jsonArray) {
            JSONObject studyMethodJson = (JSONObject) json;

            String name = studyMethodJson.getString("name");
            String description = studyMethodJson.getString("description");
            long time = studyMethodJson.getLong("time");

            StudyMethod studyMethod = new StudyMethod(name, description, time);
            studyMethods.add(studyMethod);
        }

        return studyMethods;
    }
}
