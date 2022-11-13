package qiwi.tokenlogics;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONHelper {
    private static String USER_MODE_FILE_NAME = "user_mode.json";
    private static String PASSWORD_INPUT_TIME_FILE_NAME = "password_input_time.json";

    private static void writeStringToJSON(Context context, String s, String fileName) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(s);

        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readStringFromJSON(Context context, String fileName) {
        try(FileInputStream fileInputStream = context.openFileInput(fileName);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            return gson.fromJson(streamReader, String.class);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void writeUserMode(Context context, String mode) {
        writeStringToJSON(context, mode, USER_MODE_FILE_NAME);
    }

    public static String readUserMode(Context context) {
        return readStringFromJSON(context, USER_MODE_FILE_NAME);
    }

    public static void writePasswordInputTime(Context context, String time) {
        writeStringToJSON(context, time, PASSWORD_INPUT_TIME_FILE_NAME);
    }

    public static String readPasswordInputTime(Context context) {
        return readStringFromJSON(context, PASSWORD_INPUT_TIME_FILE_NAME);
    }
}