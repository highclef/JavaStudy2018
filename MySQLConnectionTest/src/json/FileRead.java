package json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\high_\\Documents\\json\\memberinfo_JSON.json"));
        String jsonString = "";
        
        while(true) {
            String line = br.readLine();
            
            if (line==null) break;
            jsonString += line;
        }
        br.close();
        System.out.println(jsonString);
    }
}