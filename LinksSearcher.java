import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinksSearcher extends Thread {

    private URL url = null;
    private BufferedReader in = null;
    private String address = null;
    private int count = 0;
    private String dl1 = null;
    private String dl2 = null;
    private int imgCount = 0;
    private Main mf;
    private Links links;

    public LinksSearcher(Main mf, Links links) {
        this.mf = mf;
        this.links = links;
    }

    public void setAddress(String address){
        this.address = address;
    }

    private void createBtn(URL img, int imgnum){
        final JButton button;
        button = new JButton(new ImageIcon(img));
        button.setToolTipText(imgnum + "");
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setAlignmentY(Component.TOP_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel(button.getIcon());
                if (links.getSize() != 0) {
                    links.addLFL(links.getLink(Integer.parseInt(button.getToolTipText())));
                } else {
                    links.addLFL(links.getFLLink(Integer.parseInt(button.getToolTipText())));
                }
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                mf.getLoadPanel().add(label);
                mf.getLoadPanel().updateUI();
            }
        });
        mf.getStatsLabel1().setText("Found: " + links.getSize() + " images");
        mf.getFoundPanel().add(button);
        mf.getFoundPanel().updateUI();
    }

    private void addLinkToFL(String link){
        links.addFLLink(link);
    }

    @Override
    public void run() {
        try {
            url = new URL(address);
            mf.getStatsLabel1().setText("Connecting...");
        } catch (MalformedURLException e) {
            mf.getStatsLabel1().setText("ERROR!!!");
        }

        try {
            mf.btnSetEnabled(false);
            if (url != null) {
                in = new BufferedReader(new InputStreamReader(url.openStream()));
            }
        } catch (Exception e) {
            System.out.println("Can't open stream!");
            mf.getStatsLabel1().setText("Can't open stream!");
            mf.btnSetEnabled(true);
            return;
        }
        try {
            mf.getFoundPanel().removeAll();
            mf.getStatsLabel1().setText("Searching...");
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                Pattern p1 = Pattern.compile("data-src_big=\"(.*?)\\|");
                Matcher m1 = p1.matcher(inputLine);
                Pattern p2 = Pattern.compile("img src=\"(.*?)\"");
                Matcher m2 = p2.matcher(inputLine);
                for (int i = 0; i < inputLine.length(); i++) {
                    mf.getFoundPanel().updateUI();
                    if (m1.find()){
                        links.addLink(m1.group());
                    }
                    if (m2.find()){
                        count ++;
                        if (count == 1) {
                            dl1 = m2.group();
                            addLinkToFL(dl1);
                        }
                        if (count == 2) {
                            dl2 = m2.group(1);
                            addLinkToFL(dl2);
                        }
                        if (count > 2 && !Objects.equals(dl2, m2.group(1))) {
                            try {
                                addLinkToFL(m2.group(1));
                                final URL img;
                                img = new URL(m2.group(1));
                                createBtn(img, imgCount);
                                imgCount++;
                            } catch (Exception e) {
                                System.out.println("Can't load image!!!");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
        mf.btnSetEnabled(true);
        if (links.getSize() == 0 && links.getFLSize() == 0) {
            mf.getStatsLabel1().setText("Images not found!");
        }
    }
}
