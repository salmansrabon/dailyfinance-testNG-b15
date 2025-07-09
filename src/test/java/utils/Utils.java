package utils;

import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static int generateRandomNumber(int min, int max){
        double randomNumber= Math.random()*(max-min)+min;
        return  (int) randomNumber;
    }
    public static void saveUserData(JSONObject jsonObject, String jsonFilePath) throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(jsonFilePath));
        jsonArray.add(jsonObject);

        FileWriter fw=new FileWriter(jsonFilePath);
        fw.write(jsonArray.toJSONString());
        fw.flush();
        fw.close();

    }

//    public static void main(String[] args) {
//        System.out.println(generateRandomNumber(1000,9999));
//    }
}
