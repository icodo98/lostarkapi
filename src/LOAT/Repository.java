package LOAT;

import org.json.JSONObject;

import java.net.URL;

public class Repository {
    public JSONObject requestItem(String Sort, int CategoryCode, String CharacterClass, int ItemTier, String ItemGrade,
            String ItemName, int PageNo, String SortCondition) {
        // JSONObject result = new JSONObject("{\n" +
        // " \"Sort\": \"GRADE\",\n" +
        // " \"CategoryCode\": 0,\n" +
        // " \"CharacterClass\": \"string\",\n" +
        // " \"ItemTier\": 0,\n" +
        // " \"ItemGrade\": \"string\",\n" +
        // " \"ItemName\": \"string\",\n" +
        // " \"PageNo\": 0,\n" +
        // " \"SortCondition\": \"ASC\"\n" +
        // "}");
        JSONObject result = new JSONObject();
        result.append("Sort", Sort);
        result.append("CategoryCode", CategoryCode);
        result.append("CharacterClass", CharacterClass);
        result.append("ItemTier", ItemTier);
        result.append("ItemGrade", ItemGrade);
        result.append("ItemName", ItemName);
        result.append("PageNo", PageNo);
        result.append("SortCondition", SortCondition);

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

    public void MARKET(String Ops, URL URL) {
        // 생활 카테고리 90000, 재련재료(오레하 포함해서 검색) 50010
        // 검색해야할것.
        // 오레하 id 6861007~6861011 (10은 칼디르)

    }

}
