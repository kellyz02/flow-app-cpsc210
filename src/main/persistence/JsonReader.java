package persistence;

import model.FlowMonth;
import model.FlowDay;

//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//
//import org.json.*;
//
//// Represents a reader that reads the user's cycles from JSON data stored in file
//public class JsonReader {
//    private String source;
//
//    // EFFECTS: constructs reader to read from source file
//    public JsonReader(String source) {
//        this.source = source;
//    }
//
//    // EFFECTS: reads user from file and returns it;
//    // throws IOException if an error occurs reading data from file
//    public FlowUser read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseFlowUser(jsonObject);
//    }
//}
