/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screencapture;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author b4
 */
public class ScreenCaptureClient {
    Image newimg;
    static BufferedImage bimg;
    byte[]bytes;
    static JFrame frame;
    public static void main(String[]args){
        frame = new JFrame();
        JButton button = new JButton("Capturar pantalla");
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                screenCapture();
            }
        });
        frame.add(button, BorderLayout.CENTER);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Elija su pantalla");
        frame.setVisible(true);
    }
    private static void screenCapture(){
        String serverName = "192.168.12.69";
        int port = 6066;
        try{
            Socket client = new Socket(serverName, port);
            Robot bot;
            bot = new Robot();
            frame.setExtendedState(frame.ICONIFIED);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int  screenHeight = screenSize.height;
            int  screenWidth = screenSize.width;
            bimg = bot.createScreenCapture(new Rectangle(0, 0, 800, 600));
            ImageIO.write(bimg, "JPG", client.getOutputStream());
            client.close();
        }catch(IOException | AWTException e){
            e.printStackTrace();
            
        }finally{
            frame.setExtendedState(Frame.NORMAL);
        }
    }
    
}
