package LOAT;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.ObjectOutputStream.PutField;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.RuleBasedCollator;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

import javax.net.ssl.HttpsURLConnection;

public class Repository {
    static String MarketURL = "https://developer-lostark.game.onstove.com/markets/items";

    public JSONObject requestItem(String Sort, int CategoryCode, String CharacterClass, int ItemTier, String ItemGrade,
            String ItemName, int PageNo, String SortCondition) {
        JSONObject result = new JSONObject();
        result.put("Sort", (Sort == null) ? "GRADE" : Sort);
        result.put("CategoryCode", CategoryCode);
        result.put("CharacterClass", CharacterClass);
        result.put("ItemTier", ItemTier);
        result.put("ItemGrade", ItemGrade);
        result.put("ItemName", ItemName);
        result.put("PageNo", PageNo);
        result.put("SortCondition", (SortCondition == null) ? "ASC" : SortCondition);
        return result;
    }

    public void MARKET(String Ops, String URL) {
        // 생활 카테고리 90000, 재련재료(오레하 포함해서 검색) 50010
        // 검색해야할것.
        // 오레하 id 6861007~6861011 (10은 칼디르)
        // 오레하 검색
        HttpURLConnection urlConn = openConnection(MarketURL, "POST");
        urlConn.setRequestProperty("Content-Type", "application/json");
        urlConn.setDoOutput(true);

        JSONObject orehaRequest = requestItem("", 50010, "", 0, "", "오레하", 0, "");
        try (OutputStream os = urlConn.getOutputStream()) {
            byte[] input = orehaRequest.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
            JSONObject orehaResponse = new JSONObject(ReadRequest(urlConn));
            os.flush();
            os.close();
            JSONObject orehaLow = orehaResponse.optJSONArray("Items").getJSONObject(0);
            JSONObject orehaMiddle = orehaResponse.optJSONArray("Items").getJSONObject(1);
            JSONObject orehaHigh = orehaResponse.optJSONArray("Items").getJSONObject(2);
            JSONObject orehaExtraHigh = orehaResponse.optJSONArray("Items").getJSONObject(3);
            RequestMaterial(orehaLow, orehaMiddle, orehaHigh, orehaExtraHigh);
            System.out.println(orehaExtraHigh.getDouble("w2AvgPrice"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
        }

    }

    public void RequestMaterial(JSONObject... org) {

        try {
            for (int i = 0; i < org.length; i++) {
                StringBuilder sb = new StringBuilder(MarketURL);
                sb.append("/");
                sb.append(org[i].get("Id").toString());
                HttpURLConnection urlConn = openConnection(sb.toString(), "GET");
                String result = ReadRequest(urlConn);
                JSONObject JOResult = new JSONObject(ReadRequest(urlConn));
                double WeekAvgPrice = w2AvgPrice(JOResult.getJSONArray("stats"));
                JOResult.put("w2AvgPrice", WeekAvgPrice);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
        }

    }

    public double w2AvgPrice(JSONArray arg) {
        IntSummaryStatistics stats = new IntSummaryStatistics();
        for (int i = 0; i < arg.length(); i++) {
            stats.accept(arg.getJSONObject(i).getInt("AvgPrice"));
        }
        return stats.getAverage();
    }

    public String ReadRequest(HttpURLConnection urlConn) throws IOException {
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
        return result.toString();
    }

    public static HttpURLConnection openConnection(String URL, String Method) {
        try {
            URL requstURL = new URL(URL);
            HttpURLConnection urlConn = (HttpURLConnection) requstURL.openConnection();
            // urlConn.setReadTimeout(1000);
            // urlConn.setConnectTimeout(1000);
            urlConn.addRequestProperty("accept", "application/json");
            urlConn.setRequestProperty("authorization",
                    "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwMDY2MzUifQ.alvU4Pq4jIv07beiKAxoc_rNeYdwfLq7RWC6cstDjDrC4RcoOPMdpGFI8PdXxTI4suuhuh1MNmjd7DZxpxD0oZfkDo0kdouHcrYsACxwtcPQMwBj97E-h82eFIq1oitFGKtqHHXfAZqkpQMPJmyb7A3MixZrEBa_sgM-9hm8jJhqkpiyb2wU5BbLNweE1zI8KWQOCyYrhTCUlGExDYQ0bopgij8l5xboDA-tlaFW2s14diZ2gWECRki9bhqJqvISDU4VkKPbA5ybaMXsJceP7wuZyQl7yCbFqmc0WHk381-fRuDW5zB03AWcrDNcXZLwQGHuahwzmmTHk26CREfROQ");
            urlConn.setRequestMethod((Method == null) ? "GET" : Method);
            urlConn.setReadTimeout(100);
            return urlConn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
