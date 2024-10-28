import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MyFrame extends JFrame implements ActionListener {
    JButton button;
    JLabel userLabel;
    JTextField userText;
    JProgressBar progessbar;
    JLabel errorLabel;
    JLabel loading;
    JLabel ranks1;
    JLabel ranks2;
    JLabel ranks3;
    JLabel ranks4;
    JLabel ranks5;
    JLabel top;
    JLabel art1;
    JLabel art2;
    JLabel art3;
    JLabel art4;
    JLabel art5;
    JLabel artTop;

    MyFrame() {

        progessbar = new JProgressBar(0, 100);
        progessbar.setBounds(50, 200, 300, 30);
        progessbar.setStringPainted(false);
        progessbar.setValue(0);
        progessbar.setVisible(false);

        loading = new JLabel("Loading...");
        loading.setBounds(150, 170, 120, 25);
        loading.setVisible(false);

        userLabel = new JLabel("Last.fm Username ");
        userLabel.setBounds(10, 20, 120, 25);

        errorLabel = new JLabel("Invalid Username, try again");
        errorLabel.setBounds(10, 220, 170, 25);
        errorLabel.setVisible(false);

        userText = new JTextField();
        userText.setBounds(120, 20, 165, 25);

        button = new JButton("Enter");
        button.setBounds(10, 60, 80, 25);
        button.addActionListener(this);

        ranks1 = new JLabel();
        ranks1.setBounds(160, 20, 120, 25);
        ranks1.setVisible(false);

        ranks2 = new JLabel();
        ranks2.setBounds(160, 40, 120, 25);
        ranks2.setVisible(false);

        ranks3 = new JLabel();
        ranks3.setBounds(160, 60, 120, 25);
        ranks3.setVisible(false);

        ranks4 = new JLabel();
        ranks4.setBounds(160, 80, 120, 25);
        ranks4.setVisible(false);

        ranks5 = new JLabel();
        ranks5.setBounds(160, 100, 120, 25);
        ranks5.setVisible(false);

        top = new JLabel("Your top genres are");
        top.setBounds(160, 0, 120, 25);
        top.setVisible(false);


        art1 = new JLabel();
        art1.setBounds(10, 20, 120, 25);
        art1.setVisible(false);

        art2 = new JLabel();
        art2.setBounds(10, 40, 120, 25);
        art2.setVisible(false);

        art3 = new JLabel();
        art3.setBounds(10, 60, 120, 25);
        art3.setVisible(false);

        art4 = new JLabel();
        art4.setBounds(10, 80, 120, 25);
        art4.setVisible(false);

        art5 = new JLabel();
        art5.setBounds(10, 100, 120, 25);
        art5.setVisible(false);

        artTop = new JLabel("Your top artist are");
        artTop.setBounds(10, 0, 120, 25);
        artTop.setVisible(false);

        this.setTitle("Spotify Analyzer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(420, 420);
        this.setVisible(true);
        this.add(button);
        this.add(userText);
        this.add(userLabel);
        this.add(progessbar);
        this.add(errorLabel);
        this.add(loading);
        this.add(ranks1);
        this.add(ranks2);
        this.add(ranks3);
        this.add(ranks4);
        this.add(ranks5);
        this.add(top);
        this.add(art1);
        this.add(art2);
        this.add(art3);
        this.add(art4);
        this.add(art5);
        this.add(artTop);

        //setting logo in corner
        ImageIcon image = new ImageIcon("logo.png");
        this.setIconImage(image.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        System.out.println(user);
        String[] ArtistData = lastfm.lastfmRun(user);
        System.out.println(Arrays.toString(ArtistData));
        if (ArtistData[0] == (null)) {
            errorLabel.setVisible(true);
        } else {
            errorLabel.setVisible(false);
            userLabel.setVisible(false);
            userText.setVisible(false);
            button.setVisible(false);
            loading.setVisible(true);

            String[][] rankGenres = selenium.seleniumRun(ArtistData);
            String[] Ranking = Sorting.sortingRun(rankGenres);
            System.out.println(Arrays.toString(Ranking));

            loading.setVisible(false);

            ranks1.setText(Ranking[0]);
            ranks1.setVisible(true);

            ranks2.setText(Ranking[1]);
            ranks2.setVisible(true);

            ranks3.setText(Ranking[2]);
            ranks3.setVisible(true);

            ranks4.setText(Ranking[3]);
            ranks4.setVisible(true);

            ranks5.setText(Ranking[4]);
            ranks5.setVisible(true);

            top.setVisible(true);

            art1.setText(ArtistData[0]);
            art1.setVisible(true);

            art2.setText(ArtistData[1]);
            art2.setVisible(true);

            art3.setText(ArtistData[2]);
            art3.setVisible(true);

            art4.setText(ArtistData[3]);
            art4.setVisible(true);

            art5.setText(ArtistData[4]);
            art5.setVisible(true);

            artTop.setVisible(true);
        }
    }
}
