import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JPanel panel1;
    private JTextField textField;
    private JButton searchBTN;
    private JProgressBar progressBar1;
    private JButton loadBTN;
    private JButton clearBTN;
    private JPanel foundPanel;
    private JPanel loadPanel;
    private JLabel statsLabel1;
    private JLabel statsLabel2;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JButton loadAllButton;
    private Links links;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image loader");
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 650));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public Main() {
        links = new Links();
        textField.setText("https://vk.com/your_waifu");
        foundPanel.setPreferredSize(new Dimension(680, 2000));
        loadPanel.setPreferredSize(new Dimension(315, 2000));
        searchBTN.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinksSearcher searcher = new LinksSearcher(Main.this, links);
                links.clearAll();
                searcher.setAddress(textField.getText());
                searcher.start();
            }
        });
        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader loader = new Loader(links, Main.this);
                progressBar1.setMinimum(1);
                progressBar1.setMaximum(links.getSizeLFL());
                loader.start();
            }
        });
        clearBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                links.clearLFL();
                loadPanel.removeAll();
                loadPanel.updateUI();
            }
        });
        loadAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < links.getSize(); i++) {
                    links.addLFL(links.getLink(i));
                }
                Loader loader = new Loader(links, Main.this);
                progressBar1.setMinimum(1);
                progressBar1.setMaximum(links.getSizeLFL());
                loader.start();
            }
        });
    }

    public void btnSetEnabled(boolean enabled){
        getSearchBTN().setEnabled(enabled);
        getLoadBTN().setEnabled(enabled);
        getClearBTN().setEnabled(enabled);
        getLoadAllButton().setEnabled(enabled);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getSearchBTN() {
        return searchBTN;
    }

    public JProgressBar getProgressBar1() {
        return progressBar1;
    }

    public JButton getLoadBTN() {
        return loadBTN;
    }

    public JButton getClearBTN() {
        return clearBTN;
    }

    public JPanel getFoundPanel() {
        return foundPanel;
    }

    public JPanel getLoadPanel() {
        return loadPanel;
    }

    public JLabel getStatsLabel1() {
        return statsLabel1;
    }

    public JLabel getStatsLabel2() {
        return statsLabel2;
    }

    public JButton getLoadAllButton() {
        return loadAllButton;
    }
}
