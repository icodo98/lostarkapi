package LOAT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class test {

    public static void main(String[] args) {
        StringBuilder headerURL = new StringBuilder("https://developer-lostark.game.onstove.com/");
        // headerURL.append("guilds/rankings?serverName=%EB%A3%A8%ED%8E%98%EC%98%A8");
        headerURL.append("markets/options");
        try {
            HttpURLConnection urlConn = Repository.openConnection(headerURL.toString(), "GET");
            System.out.println(urlConn.getResponseCode());
            BufferedReader rd;
            if (urlConn.getResponseCode() == 200) {
                rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(urlConn.getErrorStream()));
            }
            StringBuilder result = new StringBuilder();
            String temp = rd.readLine();
            while (temp != null) {
                result.append(temp);
                temp = rd.readLine();
            }
            rd.close();

            // Utils.writeContents("MarketOptions", result.toString());
            urlConn.disconnect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
