package org.familia.client.views;

import org.familia.client.views.components.ChatTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatBox extends JPanel implements ActionListener {
    private final int width;
    private final int height;
    private final int arc = 15;

    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";

    public ChatBox(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;

        setLayout(null);
        setOpaque(false);
        setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));

        textField = new ChatTextField(11, 152, 221, 30);
        add(textField);
    }

//    public ChatBox() {
//        setOpaque(false);
//        setBounds(5, 5, 50, 50);
//
//        textField = new JTextField(20);
//        textField.addActionListener(this);
//
//        textArea = new JTextArea(5, 20);
//        textArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(textArea);
//
//        //Add Components to this panel.
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridwidth = GridBagConstraints.REMAINDER;
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        add(textField, c);
//
//        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 1.0;
//        c.weighty = 1.0;
//        add(scrollPane, c);
//    }

    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        textArea.append(text + newline);
        textField.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);
    }
}
