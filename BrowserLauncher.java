// By Aesteric 7/9/2020
/*
INSTRUCTIONS
- MAKE SURE THE PATHWAY TO CHROME IS RIGHT
1. enter the number of screenshots
2. enter the website number
3. enter which screenshot you want to be displayed
4. fullscreen the swing window
 */
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class BrowserLauncher {
    public static void main(String[] args) {
        // PLUG IN YOUR CHROME PATH BELOW
        String ChromePath = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"; // THIS IS THE PATHWAY TO CHROME ON YOUR DEVICE (for .app), CHANGE IT IF IT IS INCORRECT
        Scanner sc = new Scanner(System.in);
        ArrayList<String> urlList = new ArrayList<>(Arrays.asList("https://www.google.com", "https://www.apple.com", "https://www.fbi.gov", "https://www.cnn.com")); // add whatever you want here
        ArrayList<String> Screenies = new ArrayList<>();

        System.out.print("Enter the number of screenshots you want (enter -1 for none) : ");
        int screenies = sc.nextInt();
        int isValid = 0;
        int choice = 0;

        while(isValid != 1) {
            System.out.println("Please make your selection");
            for (int i = 0; i < urlList.size();i++ ) {
                System.out.println((i + 1) + ". " + urlList.get(i));
            }
            choice = sc.nextInt();
            if (choice > 0 && choice <= urlList.size()) {
                isValid = 1;
            } else {
                System.out.println("Invalid choice");
            }
        }
        System.out.println(choice);

        try {
            String[] cmd = {ChromePath, urlList.get(choice - 1)};
            Runtime.getRuntime().exec(cmd);

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < screenies ; i++) {
            for (int j = 0; j <= i; j++) {
                try {

                if (screenies > 3) {
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(600);
                }
                Robot robot = new Robot();
                Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
                File file = new File("screen-capture.png");
                boolean status = ImageIO.write(bufferedImage, "png", file);
                System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());
                Screenies.add(file.getAbsolutePath());


                } catch (AWTException | IOException | InterruptedException ex) {
                    System.err.println(ex);
                }
            }

        }


        class ShowPNG extends JFrame
        {
            public void display(String arg)
            {
                if (arg == null ) {
                  return;
                }
                JPanel panel = new JPanel();
                panel.setSize(500,640);
                panel.setBackground(Color.WHITE);
                ImageIcon icon = new ImageIcon(arg);
                JLabel label = new JLabel();
                label.setIcon(icon);
                panel.add(label);
                this.getContentPane().add(panel);
                this.setVisible(true);
            }
        }

        if  (!Screenies.isEmpty()) {

            System.out.print("Enter which screenshot number you would like to display: ");
            int imageDisplayed = sc.nextInt();
            String yourImageDisplayed = Screenies.get(imageDisplayed-1);

            ShowPNG draw = new ShowPNG();
            draw.display(yourImageDisplayed);
        } else {
            System.out.println("******* Goodbye, you took no screenshots! ********");
        }


    }
}

