package com.example.neoproject.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Random;

/*
* {"data_rilevazione":1676926198.811136446,"temperatura":39,"id":{"measure":1,"idObservationTemp":1,"idNeonato":1},"idSensore":1},*/

public class ObservationJsonObject {

    private static Integer NUM_BAMBINI = 1;
    private static Integer OBSERVATIONS = 20000;
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String argv[]) {

        JSONArray array = new JSONArray();

        for (int i = 1; i <= NUM_BAMBINI; i++) {
            for (int j = 1; j <= OBSERVATIONS; j++) {
                JSONObject observation = new JSONObject();
                observation.put("data_rilevazione", Instant.now());
                JSONObject id = new JSONObject();

                id.put("measure", j);
                id.put("idObservationTemp", i);
                id.put("idNeonato", i);

                observation.put("id", id);
                observation.put("idSensore", i);
                observation.put("temperatura",new Random().nextInt(36, 41));
                array.add(observation);
            }
            System.out.println(array);
            writeIntoFile(array,i);
        }
    }

    public static void writeIntoFile(JSONArray array, int i){
        try{
            System.out.println("file numero: "+i);
            String val = mapper.writeValueAsString(array);
            FileWriter file = new FileWriter("/home/venturino/Scrivania/obs" + i+".json");
            file.write(val);
            file.close();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            array.clear();
        }
    }

}
