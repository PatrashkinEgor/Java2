package Lesson4;

/**
 * Java2. Lesson 4. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 05, 2018
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatForm extends JFrame {
    ChatForm() {
        setTitle("ChatForHomeWork");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 300, 400, 400);
        JPanel bottomPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        bottomPanel.setBackground(new Color(175, 127, 40));

        bottomPanel.setPreferredSize(new Dimension(1, 80));

        add(bottomPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        centerPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());

        final JTextArea jta = new JTextArea();
        jta.setBackground(new Color(253, 253, 156));
        jta.setEditable(false);
        jta.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jta);
        centerPanel.add(jsp, BorderLayout.CENTER);

        final JTextField jtf = new JTextField();
        jtf.setPreferredSize(new Dimension(100, 30));
        final JTextField jtf2 = new JTextField();
        jtf2.setPreferredSize(new Dimension(100, 30));
        jtf2.setText("Nickname");
        JButton jb = new JButton("Send");
        bottomPanel.add(jtf2);
        bottomPanel.add(jtf);
        bottomPanel.add(jb);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterTheMessage(jtf,jtf2,jta);
            }
        });

        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enterTheMessage(jtf,jtf2,jta);
                }
                super.keyReleased(e);
            }
        });
        setVisible(true);
    }

    private void enterTheMessage(JTextField jtf,JTextField jtf2,JTextArea jta){
        jta.append(jtf2.getText()+ " says: \n");
        jta.append(jtf.getText() + "\n");
        jtf.setText(null);
        jtf.grabFocus();
    }

}


