package agents;
import agents.MLRAgent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgentGUI extends JFrame {
    private MLRAgent myAgent;
    private JTextField x1Field,x2Field,x3Field;

    AgentGUI(MLRAgent a) {
        super(a.getLocalName());

        myAgent = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(10,1));
        p.add(new JLabel("The Normal equation approach"));
        p.add(new JLabel("Set X1 and X2 values to predict Y"));
        x1Field = new JTextField(5);
        p.add(new JLabel("X1"));
        p.add(x1Field);
        x2Field = new JTextField(5);
        p.add(new JLabel("X2"));
        p.add(x2Field);
        p.add(new JLabel("Gradient"));
        p.add(new JLabel("Set X value to predict Y"));
        x3Field = new JTextField(5);
        p.add(new JLabel("X1"));
        p.add(x3Field);
        getContentPane().add(p, BorderLayout.CENTER);
        JButton addButton = new JButton("Calculate MLR");
        addButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    double x1 = Double.parseDouble(x1Field.getText());
                    double x2 = Double.parseDouble(x2Field.getText());
                    myAgent.executeAgentMLR(x1,x2);
                    x1Field.setText("");
                    x2Field.setText("");
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(AgentGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } );
        JButton addButton2 = new JButton("Calculate Gradient");
        addButton2.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    double x1 = Double.parseDouble(x3Field.getText());

                    myAgent.executeAgentGradient(x1);
                    x1Field.setText("");

                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(AgentGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } );

        p = new JPanel();
        p.add(addButton);
        p.add(addButton2);

        getContentPane().add(p, BorderLayout.SOUTH);


        addWindowListener(new	WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                myAgent.doDelete();
            }
        } );

        setResizable(false);
    }

    public void showGui() {
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int)screenSize.getWidth() / 2;
        int centerY = (int)screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
        super.setVisible(true);
    }
}