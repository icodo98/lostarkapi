package LOAT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class test {

    public static void main(String[] args) {
        StringBuilder headerURL = new StringBuilder("https://developer-lostark.game.onstove.com/");
//        headerURL.append("guilds/rankings?serverName=%EB%A3%A8%ED%8E%98%EC%98%A8");
        headerURL.append("markets/options");
        try {
            URL requstURL = new URL(headerURL.toString());
            HttpURLConnection urlConn = (HttpURLConnection) requstURL.openConnection();
            urlConn.addRequestProperty("accept","application/json");
            urlConn.setRequestProperty("authorization","bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwMDY2MzUifQ.alvU4Pq4jIv07beiKAxoc_rNeYdwfLq7RWC6cstDjDrC4RcoOPMdpGFI8PdXxTI4suuhuh1MNmjd7DZxpxD0oZfkDo0kdouHcrYsACxwtcPQMwBj97E-h82eFIq1oitFGKtqHHXfAZqkpQMPJmyb7A3MixZrEBa_sgM-9hm8jJhqkpiyb2wU5BbLNweE1zI8KWQOCyYrhTCUlGExDYQ0bopgij8l5xboDA-tlaFW2s14diZ2gWECRki9bhqJqvISDU4VkKPbA5ybaMXsJceP7wuZyQl7yCbFqmc0WHk381-fRuDW5zB03AWcrDNcXZLwQGHuahwzmmTHk26CREfROQ");
            urlConn.setRequestMethod("GET");
            System.out.println(urlConn.getResponseCode());
            BufferedReader rd;
            if(urlConn.getResponseCode() == 200){
                rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(urlConn.getErrorStream()));
            }
            StringBuilder result = new StringBuilder();
            String temp = rd.readLine();
            while (temp != null){
                result.append(temp);
                temp = rd.readLine();
            }
            rd.close();
            Utils.WriteFile("MarketOptions",result.toString());

            urlConn.disconnect();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
