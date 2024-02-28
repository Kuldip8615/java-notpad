import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class Main implements ActionListener, KeyListener
{
    JFrame f;
    JMenuBar mb;
    JMenu file,option;
    JTextArea ta;
    JMenuItem newfile,open,save;
    JMenuItem cut,copy,paste,selectAll;

    Main()
    {
        f=new JFrame("Notpad");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\kuldip\\java\\notpad\\src\\icon.png");
        f.setIconImage(icon);
        Font font=new Font("arial",Font.BOLD,15);
        f.setFont(font);
        f.setSize(800,600);
        f.setLayout(null);
        f.setResizable(false);
        //f.setLocationRelativeTo(null);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        mb=new JMenuBar();
        file=new JMenu("File");
        newfile=new JMenuItem("New File");
        open=new JMenuItem("Open");
        save=new JMenuItem("Save");
        newfile.setFont(font);
        open.setFont(font);
        save.setFont(font);
        newfile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        file.add(newfile);
        file.add(open);
        file.add(save);
        file.setFont(font);
        mb.add(file);

        option=new JMenu("Option");
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("selectAll");
        cut.setFont(font);
        copy.setFont(font);
        paste.setFont(font);
        selectAll.setFont(font);

        option.add(cut);
        option.add(copy);
        option.add(paste);
        option.setFont(font);
        option.add(selectAll);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        mb.add(option);
        f.setJMenuBar(mb);

        ta=new JTextArea();
        ta.setFont(font);
        ta.addKeyListener(this);
        JScrollPane scrollPane=new JScrollPane(ta);
        scrollPane.setBounds(3,5,780,530);
       // scrollPane.setHorizontalScrollBarPolicy(ta.HORIZONTAL_SCROLLBAR_ALWAYS);
        f.add(scrollPane);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==cut)
        {
            ta.cut();
        }
        if (e.getSource()==copy)
        {
            ta.copy();
        }
        if (e.getSource()==paste)
        {
            ta.paste();
        }
        if (e.getSource()==selectAll)
        {
            ta.selectAll();
        }
        if (e.getSource()==newfile)
        {
            ta.setText("");
        }
        if (e.getSource()==open)
        {
            try {
                String str = "";
                String loc;
                JFileChooser jf = new JFileChooser("c:");
                int ans = jf.showOpenDialog(null);
                loc = jf.getSelectedFile().getAbsolutePath();
                if (ans == 0) {
                    try {
                        FileReader fr = new FileReader(loc);
                        int i;
                        while ((i = fr.read()) != -1) {
                            str += (char) i;
                        }
                        fr.close();
                        ta.setText(str);
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
            }catch (Exception e2)
            {
                System.out.println(e2);
            }


        }
        if (e.getSource()==save)
        {
            try {
                String str = "";
                str=ta.getText();
                String loc;
                JFileChooser jf = new JFileChooser("c:");
                int ans = jf.showSaveDialog(null);
                loc = jf.getSelectedFile().getAbsolutePath();
                if (ans == 0) {
                    try {
                        FileWriter fw = new FileWriter(loc);
                        int i;
                        for (i=0;i<str.length();i++)
                        {
                            char c=str.charAt(i);
                            fw.write(c);
                        }
                        fw.close();
                    } catch (Exception e1) {
                        System.out.println(e1);
                    }
                }
            }catch (Exception e2)
            {
                System.out.println(e2);
            }


        }


    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode()==27)
        {
            int ans = JOptionPane.showConfirmDialog(null, "Do You Want To Exit");
            if (ans == 0) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args)
    {
        new Main();

    }



}