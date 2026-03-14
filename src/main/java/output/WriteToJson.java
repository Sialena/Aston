package output;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToJson {
    private final ObjectMapper objectMapper;

    public WriteToJson() {
        objectMapper = new ObjectMapper();
    }

    public <Bus> void writeToJson(String filePath, List<Bus> busList) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            for(Bus bus : busList) {
                String jsonLine = objectMapper.writeValueAsString(bus);
                bufferedWriter.write(jsonLine);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            System.out.println(busList.size() + " objects were successfully written into JSON file!");
        }
        catch(IOException e) {
            System.out.println("An error has occurred while trying to write to a JSON file: " + e);
        }
    }
}
