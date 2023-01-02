package LOAT;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

public class JUnitTest {
    @Test
    public void test(){
        JSONObject jo = new JSONObject(Utils.readContentsAsString(new File("MarketOptions")));

        System.out.println(jo.keys());
    }

}
