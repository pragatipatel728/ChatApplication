package com.chatapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//swing come from java extended package so we use javax
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//action listner interface it has abstract method which name is actionPerformed
public class Server implements ActionListener {
  static JFrame f = new JFrame();
  // with the help of jframe object which is extend here we can call functions and
  // do work
  // for making frame we use jframe class so we extend here jframe class and
  // jframe class present in swing package
  JTextField text; // i declare it globally bcz i wnt to use it inside the constructor
  JPanel a1;
  static DataOutputStream dout;
  static Box vertical = Box.createVerticalBox();

  Server() { // constructor
    // all coding for the frame will be inside constructor
    // set the component on frame so use different layout
    f.setLayout(null);
    // panel is use for devide the component on frame
    JPanel p1 = new JPanel();
    p1.setBackground(new Color(7, 94, 84));
    // setBoundS:pass the coordinate
    p1.setBounds(0, 0, 450, 70);
    p1.setLayout(null);
    // WITH THE HELP OF ADD FUNCTION WE SET ANY COMPONENT ON FRAME
    f.add(p1);
    // if we take image form file directory then use classLoader.it has static
    // method getSystemResource
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
    Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel back = new JLabel(i3);
    back.setBounds(5, 20, 25, 25);
    p1.add(back);
    back.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent ae) {
        f.setVisible(false);
      }
    });

    ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
    Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    ImageIcon i6 = new ImageIcon(i5);
    JLabel profile = new JLabel(i6);
    profile.setBounds(40, 10, 50, 50);
    p1.add(profile);
    ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
    Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    ImageIcon i9 = new ImageIcon(i8);
    JLabel video = new JLabel(i9);
    video.setBounds(300, 20, 30, 30);
    p1.add(video);

    ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
    Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
    ImageIcon i12 = new ImageIcon(i11);
    JLabel phone = new JLabel(i12);
    phone.setBounds(360, 20, 35, 30);
    p1.add(phone);

    ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
    Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
    ImageIcon i15 = new ImageIcon(i14);
    JLabel morevert = new JLabel(i15);
    morevert.setBounds(420, 20, 10, 25);
    p1.add(morevert);

    // jLable:we can write anything on frame
    JLabel name = new JLabel("Pragati");
    name.setBounds(110, 20, 100, 18);
    name.setForeground(Color.WHITE);
    name.setFont(new Font("SAN SERIF", Font.BOLD, 18));
    p1.add(name);

    JLabel status = new JLabel("Active Now");
    status.setBounds(110, 40, 100, 18);
    status.setForeground(Color.WHITE);
    status.setFont(new Font("SAN SERIF", Font.BOLD, 14));
    p1.add(status);

    a1 = new JPanel();
    a1.setBounds(5, 75, 440, 570);
    f.add(a1);

    // JTextField:user can write msz
    text = new JTextField();
    text.setBounds(5, 590, 310, 40);
    text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
    f.add(text);

    JButton send = new JButton("send");
    send.setBounds(320, 590, 123, 40);
    send.setBackground(new Color(7, 94, 84));
    send.setForeground(Color.WHITE);
    send.addActionListener(this);
    send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
    f.add(send);

    f.setSize(450, 650); // set size function present in jframe class
    f.setLocation(200, 50); // we are setting the location of frame 200 from left,50 from up
    f.setUndecorated(true);
    f.getContentPane().setBackground(Color.white); // change frame background color
    // COLOR CLASS PRESENT IN AWT PACKAGE
    f.setVisible(true); // show the frame visiblity
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    try {
      String out = text.getText();

      JPanel p2 = formatLabel(out);

      a1.setLayout(new BorderLayout());
      JPanel right = new JPanel(new BorderLayout());
      right.add(p2, BorderLayout.LINE_END);
      vertical.add(right); // IN LINE BY LINE PRINT MSZ
      vertical.add(Box.createVerticalStrut(15)); // 15 is space between two verticals
      a1.add(vertical, BorderLayout.PAGE_START); // HERE WE WILL SHOW ALL MSZ INSIDE A1 PANEL and border layout add in
      // page start
      // with the help of jframe object we can call functions and do work
      //I want to empty text box after send msz
      dout.writeUTF(out); //FOR sending data here will use writeUTF

      text.setText("");
      f.repaint(); // we can directly call this function bcz function will call this
      f.invalidate();
      f.validate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //    I want messages in boxes
  public static JPanel formatLabel(String out) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //Box layout takes two arguments first is where you want to add it and second is which side you want to add
    JLabel output = new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>"); //in html tag I have defined width
    output.setFont(new Font("Tahoma", Font.PLAIN, 16)); //change font size of msz
    output.setBackground(new Color(37, 211, 102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15, 15, 15, 50)); //padding for msz
    panel.add(output);
    Calendar cal = Calendar.getInstance();
    //I want to show time when I will send msz
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    //if we want to write anything on frame then we need to put in JLabel
    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime())); //I want to set time dynamically so here i use setText function
    panel.add(time);
    return panel;
    //JPanel is return type
  }

  public static void main(String[] args) {
    new Server(); // annonymus object
    // when we create class object then constructor call here frame length is 450
    // and width is 700
    //i want that if i run class then it make a server
    try {
      //in try block I will make server with the help of server socket class
      ServerSocket skt = new ServerSocket(6001); //6000 is port number
      //I am maling server and i want  to run my server infinitely
      while (true) {
        Socket s = skt.accept();
        //for recieving msz we will use datainput stream class
        DataInputStream din = new DataInputStream(s.getInputStream());
        //SEND THE DATA
        dout = new DataOutputStream(s.getOutputStream()); //for send the data it work as a globally
        //infinitly recieve and send messages here we will use readUTF and writeUTF
        while (true) {
          //msz read with the help of readUTF
          String msg = din.readUTF();
          //I WANT TO DISPALY MSZ ON FRAME
          JPanel panel = formatLabel(msg);
          JPanel left = new JPanel(new BorderLayout());
          left.add(panel, BorderLayout.LINE_START);
          vertical.add(left);
          f.validate(); //I want to refresh frame

        }
      }

    } catch (Exception e) {
      e.printStackTrace();

    }
  }

}