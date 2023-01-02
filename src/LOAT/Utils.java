package LOAT;

import java.io.*;
import java.net.DatagramSocket;

public class Utils {

    public static void WriteFile(String File, String contents) {
            try {
                // 1. 파일 객체 생성
                File file = new File(File);

                // 2. 파일 존재여부 체크 및 생성
                if (!file.exists()) {
                    file.createNewFile();
                }

                // 3. Writer 생성
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);

                // 4. 파일에 쓰기
                writer.write(contents);
                // 5. PrintWriter close
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

