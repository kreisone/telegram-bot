
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Main {
    
    public void TelegramInit() {
        
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MyTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
    }
    
     public String getTranslation(String word) {
         
        HttpURLConnection connection = null;
       
        String url_yandex = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20180207T085236Z.123tg312t.e9175ab4211434c8bbbddq23fg2309661875bbc2&text=" + word + "&lang=kk-ru";
        
        String query = url_yandex;
        
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                
                StringBuilder data = new StringBuilder();
                
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))
                    ) {
                    
                    String line;
                    while ((line = reader.readLine()) != null) {
                        data.append(line);
                    }
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
               JSONObject obj = new JSONObject(data.toString());
               
               // for Yandex
               JSONArray arr = obj.getJSONArray("text");
                return arr.getString(0);

                

            } else {
                return "err";
            }
        } catch (Exception ignored) {
        } finally {
            connection.disconnect();
        }
        return "er";
    }
    
    
    
}
