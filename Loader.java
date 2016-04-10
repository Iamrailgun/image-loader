import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Loader extends Thread{

    private Links links;
    private Main mf;

    public Loader(Links links, Main mf) {
        this.links = links;
        this.mf = mf;
    }

    @Override
    public void run(){
        mf.getStatsLabel1().setText("Loading...");
        mf.btnSetEnabled(false);
        for (int i = 0; i < links.getSizeLFL(); i++) {
            mf.getProgressBar1().setValue(i+1);
            mf.getStatsLabel2().setText("Loaded " + (i + 1) + " of " + links.getSizeLFL());
            try {
                URL img = new URL(links.getLFL(i));
                BufferedImage image = ImageIO.read(img);
                File outFile = new File("img " + (i + 1) + ".jpg");
                ImageIO.write(image, "jpg", outFile);
            } catch (IOException e) {
                System.out.println("IOException");
                mf.getStatsLabel1().setText("ERROR!!!");
                mf.btnSetEnabled(true);
                return;
            }
        }
        mf.getStatsLabel1().setText("loading finished!");
        mf.btnSetEnabled(true);
    }
}
