package utils;

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
    public static void saveUserData(String firstName, String lastName, String email, String password, String phoneNumber, String address) throws IOException, ParseException {
        String url="./src/test/resources/users.json";
        JSONParser jsonParser=new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(url));

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("firstName",firstName);
        jsonObject.put("lastName",lastName);
        jsonObject.put("email",email);
        jsonObject.put("password",password);
        jsonObject.put("phoneNumber",phoneNumber);
        jsonObject.put("address",address);

        jsonArray.add(jsonObject);

        FileWriter fw=new FileWriter(url);
        fw.write(jsonArray.toJSONString());
        fw.flush();
        fw.close();

    }

//    public static void main(String[] args) {
//        System.out.println(generateRandomNumber(1000,9999));
//    }
}
