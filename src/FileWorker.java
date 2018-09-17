import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileWorker{
    public static String read(String fileName) {
        System.out.println(fileName);
        StringBuilder sb =  new StringBuilder();
        try {
            FileReader fr = new FileReader(fileName);
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                sb.append(scan.nextLine());
            }

            fr.close();
        }
        catch (IOException e){System.out.println("File not found");}

        return sb.toString();
    }
}
