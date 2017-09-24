import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.imageio.ImageIO;

public class main extends JComponent
{
    final double SCREENPERCENT = .8;
    static main start = new main();    
    static File wayneFolder = new File("waynePictures");
    static File spiderFolder = new File("spiderPictures");
    static JFrame frame = new JFrame();
    static boolean snyder;
    static int state = 0;
    public static void main(String[] theory)
    {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int gameWidth = (int)(screenSize.getWidth()*SCREENPERCENT);
        final int gameHeight = (int)(screenSize.getHeight()*SCREENPERCENT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setBounds(0, 0, gameWidth, gameHeight);
        frame.add(start);
        frame.setVisible(true);
        frame.setSize(width, height);
		frame.addKeyListener(new KeyAdapter(){
                public void keyPressed(KeyEvent e)
                {
                
					Integer key = e.getKeyCode();
					if(key == KeyEvent.VK_RIGHT)
                    {
                        frame.repaint();
                        if(state==0){
                        if(!snyder)
                            state = 1;
                        else
                            state = 2;
                        }
                        else
                        {
                            state = 0;
                        }
                        frame.repaint();

                    }

                    if(key == KeyEvent.VK_LEFT)
                    {
                        if(state==0){
                         if(snyder)
                            state = 1;
                        else
                            state = 2;
                        }
                        else
                        {
                            state = 0;
                        }
                        frame.repaint();

                    }

                    }
        });
	frame.repaint();
                
    }

    public void paintComponent(Graphics g)
    {
    if(state==0){
    File[] files;
    if(Math.random()>.5)
    {
    snyder = true;
	files = wayneFolder.listFiles();
    }
    else
    {
    snyder = false;
    files = spiderFolder.listFiles();
    }
    int index = (int)(Math.random()*files.length);
	BufferedImage photo = null;
    String path = files[index].getPath();
	try
        {
            photo = ImageIO.read(main.class.getResourceAsStream(path));
        }
        catch (Exception e)
        {
            System.out.println("image loading error");
        }
        
        g.drawImage(photo,0,0,400,400,null);
    }
    else if(state == 2)
    {
        g.setColor(Color.green);
        g.fillRect(0,0,500,500);
        g.setColor(Color.white);
        g.drawString("Correct!!!",50,50);
    }
    else
    {
        g.setColor(Color.red);
        g.fillRect(0,0,500,500);
        g.setColor(Color.white);
        g.drawString("False!!!",50,50);

    }
    }


    }
