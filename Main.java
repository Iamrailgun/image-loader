import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws MalformedURLException {

        final Loader loader = new Loader();

        JFrame jf = new JFrame("Image loader");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setPreferredSize(new Dimension(400, 150));
        jf.setMinimumSize(new Dimension(400, 150));

        JPanel panel = new JPanel();

        final JTextField jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(350, 25));
        jtf.setMaximumSize(new Dimension(350, 25));

        JButton jb = new JButton();
        jb.setText("Start");
        jb.setPreferredSize(new Dimension(100, 25));
        jb.setMinimumSize(new Dimension(100, 25));

        panel.add(jtf);
        panel.add(jb);
        jf.setContentPane(panel);
        jf.pack();
        jf.setVisible(true);

        jb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loader.load(jtf.getText());
            }
        });
    }
}
