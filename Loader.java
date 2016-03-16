import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class Loader {

    private URL url = null;
    private BufferedReader in = null;
    private BufferedImage image = null;
    private String inputLine;
    private LinkedList<String> linkedList = new LinkedList<>();

    public void load(String address) {
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        }

        System.out.println("form html");
        try {
            if (url != null) {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
            }
            if (in != null) {
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.contains(".jpg")) {
                        linkedList.add(inputLine.substring(6, inputLine.length() - 1));
                        System.out.println(inputLine);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        System.out.println("from list");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
            try {
                URL img = new URL(linkedList.get(i));
                image = ImageIO.read(img);
                File ofile = new File("img " + (i - 1));
                ImageIO.write(image, "jpg", ofile);
            } catch (IOException e) {
                System.out.println("iex");
            }
        }
    }
}
