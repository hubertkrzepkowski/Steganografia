/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganografia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author HK
 */
class Steganografia
    {
        private BufferedImage  input;
        private BufferedImage  output;
        private  JFrame frame ;

        public Steganografia(String path)
        {
         
        try {
          input = ImageIO.read(new File(path));
            } catch (IOException e) {}
         frame = new JFrame();
        frame.setTitle("Obraz Bazowy");
        JLabel l = new JLabel() ;
        l.setIcon(new ImageIcon(input));
        frame.getContentPane().add(l,BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
       
          
         
        
        }
       
        private ArrayList convert(String s){
            ArrayList Wynik = new ArrayList<Character>();
         String tekst=s.toUpperCase().trim();
            char[] znaki =tekst.toCharArray();
           for(char znak :znaki){
           int z = (int)znak;
           String bin = Integer.toBinaryString(z);
           char[] binary =bin.toCharArray();
           if(binary.length<8)Wynik.add('0');
           for(char b :binary ){
           Wynik.add(b);
           }
           }
           Wynik.add('1');
           Wynik.add('1');
           Wynik.add('1');
           Wynik.add('1');
           Wynik.add('1');
           Wynik.add('1');
           Wynik.add('1');
           Wynik.add('1'); 
         return Wynik ;
        }
        
        
        
        
        
        public BufferedImage encrypt(String Text) 
                
        {
              JOptionPane optionPane = new JOptionPane();
      
            
            ArrayList<Character> a =  convert(Text);
 // System.out.print(a.toString());
 //System.out.print((char)Integer.parseInt("01000001", 2));
           if (a.size()>input.getHeight()*input.getWidth()*3){
               
                 optionPane.showMessageDialog(frame, "Cały tekst nie zmieścił się w obrazie ","Error",JOptionPane.WARNING_MESSAGE);
           }
            for (int i = 0; i < input.getWidth(); i++)
            {
                for (int j = 0; j < input.getHeight(); j++)
                {
                    Color pixelInput = new Color(input.getRGB(i, j));
                    int blue  = pixelInput.getBlue();
                    int red = pixelInput.getRed();
                    int green = pixelInput.getGreen();
                   
                     if(a.isEmpty()==false){
                      String bin = Integer.toBinaryString(red);
                      char[] binary =bin.toCharArray();
                      binary[binary.length-1]=a.get(0);
                      a.remove(0);
                     StringBuilder value = new StringBuilder();
                     value.append(binary);
                     String v=value.toString();
                     red=Integer.parseInt(v, 2);
                     }
                     
                     if(a.isEmpty()==false){
                      String bin = Integer.toBinaryString(green);
                      char[] binary =bin.toCharArray();
                      binary[binary.length-1]=a.get(0);
                      a.remove(0);
                     StringBuilder value = new StringBuilder();
                     value.append(binary);
                     String v=value.toString();
                     green=Integer.parseInt(v, 2);
                     }
                     
                      if(a.isEmpty()==false){
                      String bin = Integer.toBinaryString(blue);
                      char[] binary =bin.toCharArray();
                      binary[binary.length-1]=a.get(0);
                      a.remove(0);
                     StringBuilder value = new StringBuilder();
                     value.append(binary);
                     String v=value.toString();
                     blue=Integer.parseInt(v, 2);
                    }
                     Color out = new Color(red, green, blue); 
                     int rgb = out.getRGB();
                     input.setRGB(i, j, rgb);
                     
                    }
                  
                }
            return input;
            }
        
        
        
     public String decrypt(){
         ArrayList<Character> wynik =new ArrayList();
      
         for (int i = 0; i < input.getWidth(); i++)
            {
                for (int j = 0; j < input.getHeight(); j++)
                {
                    Color pixelInput = new Color(input.getRGB(i, j));
                    int blue  = pixelInput.getBlue();
                    int red = pixelInput.getRed();
                    int green = pixelInput.getGreen();
                      String binr = Integer.toBinaryString(red);
                      char[] binaryr =binr.toCharArray();
                      wynik.add(binaryr[binaryr.length-1]);
                      
                       String bing = Integer.toBinaryString(green);
                      char[] binaryg =bing.toCharArray();
                     wynik.add(binaryg[binaryg.length-1]);
         
                      
                        String binb = Integer.toBinaryString(blue);
                      char[] binaryb =binb.toCharArray();
                      wynik.add(binaryb[binaryb.length-1]);
                      
                    }
                 
     
     
                }
          StringBuilder text = new StringBuilder();
          StringBuilder value = new StringBuilder();
                       while(wynik.isEmpty()==false){
                       value.append(wynik.get(0));
                       wynik.remove(0);
                       }
                String s = value.toString();
                   for(int m=0 ;m<s.length();m=m){
                       if(m+8>s.length())break;
                 String  temp = s.substring(m, m+8);
                 if(temp.equalsIgnoreCase("11111111")) break;
                    m=m+8;
                     text.append((char)Integer.parseInt(temp, 2));
                     
                   }
                   
        return text.toString();
     }  
            

    /**
     * @return the input
     */
    public BufferedImage getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(BufferedImage input) {
        this.input = input;
    }

    /**
     * @return the part1
     */
    

    /**
     * @return the output
     */
    public BufferedImage getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(BufferedImage output) {
        this.output = output;
    }
        

}