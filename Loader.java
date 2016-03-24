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

        try {
            if (url != null) {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
            }
            if (in != null) {
                while ((inputLine = in.readLine()) != null) {
                    Pattern pattern = Pattern.compile("data-src_big=\"(.*?)\\|");
                    Matcher matcher = pattern.matcher(inputLine);
                    for (int i = 0; i < inputLine.length(); i++) {
                        if (matcher.find()){
                            linkedList.add(matcher.group(1));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
            try {
                URL img = new URL(linkedList.get(i));
                image = ImageIO.read(img);
                File outFile = new File("img" + i + ".jpg");
                ImageIO.write(image, "jpg", outFile);
            } catch (IOException e) {
                System.out.println("iex");
            }
        }
    }
}
