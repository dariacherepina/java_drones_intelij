package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;






public class MyFrame extends JFrame {  //MyFrame is the child class of JFrame

    //JFrame = a GUI window to add components to

    private JTable table;

         MyFrame() {

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit out of application
            this.setResizable(false); // prevent frame from being resized
            this.setSize(1220, 820); // sets the x-dimension & the y-dimension of the frame
            this.setTitle("Drones simulator"); //sets title of the frame

            ImageIcon image = new ImageIcon("drone.jpg"); //creates an ImageIcon
            this.setIconImage(image.getImage()); //change Icon of the frame
            this.getContentPane().setBackground(Color.BLACK); //change color of background
            this.setLayout(null); //Layout of the frame

            JPanel panel = new JPanel();
            panel.setLayout(null);
            panel.setBounds(0,0,140,820);
            panel.setBackground(Color.ORANGE);
            this.add(panel);

            createButton("DASHBOARD",     0, 50, 140, 40, panel);
            createButton("DRONE CATALOG", 0, 90, 140, 40,panel);
            createButton("DRONES",        0, 130, 140, 40,panel);
            createButton("DRONE TYPES",   0, 170, 140, 40,panel);
            createButton("FLIGHT DYNAMICS",0, 210, 140, 40,panel);
            createButton("RETURN5MINUTES", 0, 700, 140, 40,panel);


            JButton button1 = new JButton("Refresh");
            button1.setLayout(null);
            button1.setBounds(1050, 20, 100, 40);
            button1.setBackground(Color.white);
            this.add(button1);


            JLabel label1 = createLabel("DRONE TYPES",  380, 40, 600, 350);

            DefaultTableModel model = new DefaultTableModel();//JTable uses it to manipulate data like adding or removing rows
            table= new JTable(model); //creates table
             table.setPreferredScrollableViewportSize(new Dimension(800, 200));

             model.addColumn("ID");  //adds columns to the model
             model.addColumn("Manufacturer");
             model.addColumn("Typename");
             model.addColumn("Weight");
             model.addColumn("Maximum Speed");
             model.addColumn("Battery Capacity");
             model.addColumn("Control Range");
             model.addColumn("Maximum Carriage");




             JScrollPane scrollPane = new JScrollPane(table);
             scrollPane.setBounds(180,400,950,350);
             this.add(scrollPane);
             this.setVisible(true); // makes frame visible, in the end in order to see every component
}











            private JButton createButton(String text, int x, int y, int width, int height, Container container) {
                JButton button = new JButton(text);
                button.setBounds(x, y, width, height);
                button.setBackground(Color.white);  //color of button
                container.add(button);
                return button;

            }

            private JLabel createLabel(String text, int x, int y, int width, int height ) {
                JLabel label1 = new JLabel(text);
                ImageIcon image2 = new ImageIcon("drones.jpg");
                label1.setIcon(image2);
                label1.setHorizontalTextPosition(JLabel.CENTER); //sets text left, center, right of Imageicon
                label1.setVerticalTextPosition(JLabel.TOP); //sets text top, center, bottom of Imageicon
                label1.setForeground(Color.ORANGE); //sets font color of text
                label1.setIconTextGap(30); //sets gap of text to image
                label1.setVerticalAlignment(JLabel.CENTER); //sets vertical position of icon + text within label
                label1.setHorizontalAlignment(JLabel.CENTER); //sets horizontal position of icon + text within label
                label1.setBounds(x, y, width, height);//sets x, y & dimension of the label within frame
                this.add(label1); //add label1 to frame
                return label1;
            }




    public static void main(String[] args) {


            MyFrame myFrame = new MyFrame(); //creates an instance of the MyFrame class




    }


}
