package agents;
import agents.MLRAgent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AgentGUI extends JFrame {
    private MLRAgent myAgent;
    private JTextField x1Field,x2Field;

    AgentGUI(MLRAgent a) {
        super(a.getLocalName());

        myAgent = a;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 1));
        p.add(new JLabel("Linear Regression"));
        p.add(new JLabel("Set an X value to predict Y"));
        x1Field = new JTextField(15);
        x2Field = new JTextField(15);
        p.add(x1Field);
        p.add(x2Field);
        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Calculate");
        addButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x1 = Double.parseDouble(x1Field.getText());
                    Double x2 = Double.parseDouble(x2Field.getText());
                    myAgent.executeAgent(x1,x2);
                    x1Field.setText("");
                    x2Field.setText("");
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(AgentGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } );
        p = new JPanel();
        p.add(addButton);
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