package LOAT;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JUnitTest {
    @Test
    public void test() {
        JSONObject jo = new JSONObject(Utils.readContentsAsString(new File("MarketOptions")));

        System.out.println(jo.keys());
    }

    @Test
    public void JSonbuilderTest() {
        Repository r = new Repository();
        JSONObject jo = r.requestItem("GRADE", 0, "ee", 2, "ee", "ee", 0, "ASC");
    }

}
