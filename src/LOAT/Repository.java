package LOAT;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

public class Repository {
    public JSONObject requestItem(String Sort, int CategoryCode, String CharacterClass, int ItemTier, String ItemGrade,
            String ItemName, int PageNo, String SortCondition) {
        JSONObject result = new JSONObject();
        result.put("Sort", Sort);
        result.put("CategoryCode", CategoryCode);
        result.put("CharacterClass", CharacterClass);
        result.put("ItemTier", ItemTier);
        result.put("ItemGrade", ItemGrade);
        result.put("ItemName", ItemName);
        result.put("PageNo", PageNo);
        result.put("SortCondition", SortCondition);
        return result;
    }

    public JSONObject requestItem(int CategoryCode) {
        JSONObject result = new JSONObject();
        result.put("Sort", "GRADE");
        result.put("CategoryCode", CategoryCode);
        result.put("CharacterClass", "");
        result.put("ItemTier", "");
        result.put("ItemGrade", "");
        result.put("ItemName", "");
        result.put("PageNo", 0);
        result.put("SortCondition", "ASC");
        return result;
    }

    public void MARKET(String Ops, URL URL) {
        // 생활 카테고리 90000, 재련재료(오레하 포함해서 검색) 50010
        // 검색해야할것.
        // 오레하 id 6861007~6861011 (10은 칼디르)

    }

    public static HttpURLConnection openConnection(String URL, String Method) {
        try {
            URL requstURL = new URL(URL);
            HttpURLConnection urlConn = (HttpURLConnection) requstURL.openConnection();
            urlConn.addRequestProperty("accept", "application/json");
            urlConn.setRequestProperty("authorization",
                    "bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAwMDY2MzUifQ.alvU4Pq4jIv07beiKAxoc_rNeYdwfLq7RWC6cstDjDrC4RcoOPMdpGFI8PdXxTI4suuhuh1MNmjd7DZxpxD0oZfkDo0kdouHcrYsACxwtcPQMwBj97E-h82eFIq1oitFGKtqHHXfAZqkpQMPJmyb7A3MixZrEBa_sgM-9hm8jJhqkpiyb2wU5BbLNweE1zI8KWQOCyYrhTCUlGExDYQ0bopgij8l5xboDA-tlaFW2s14diZ2gWECRki9bhqJqvISDU4VkKPbA5ybaMXsJceP7wuZyQl7yCbFqmc0WHk381-fRuDW5zB03AWcrDNcXZLwQGHuahwzmmTHk26CREfROQ");
            urlConn.setRequestMethod((Method == null) ? "GET" : Method);
            return urlConn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
