import javax.swing.*;


public class HomePage {

    //public instance of the main frame for calling it on the main file
    public JPanel homePanel;
  

    public HomePage() {

        //make the home page here
        homePanel = new JPanel();
        JLabel view = new JLabel("Welcome to Home Panel");


        //Adding every button
        homePanel.add(view);

        

    }


}
