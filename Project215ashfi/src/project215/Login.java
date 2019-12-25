
package project215;


import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Logic.Verification;


public class Login extends JFrame{

    private static final String CMD_LOGIN = "login";

    Button btnLogin = new Button("Login");
    Button btnCancel = new Button("Cancel");
    Verification veri = new Verification();

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }
    private static final String CMD_CANCEL = "cancel";
    JTextField txtUsername = new JTextField(10);
    JPasswordField txtPassword = new JPasswordField(10);

    public Login() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblLogin = new JLabel("Username: ");
        JLabel lblCancel = new JLabel("Password: ");

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                                boolean log;
                log = veri.checkLogin(getTxtUsername().getText(), getTxtPassword().getText());
                if (log == true) {
                    System.out.println("You are log in!");
                    NoteFrame noteF = new NoteFrame();
                    noteF.setVisible(true);
                    setVisible(false);
                } else {
                    System.out.println("Please try again. " + getTxtUsername().getText() + " : " + getTxtPassword().getText());
                    
                }
            }
        });
        
        
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblCancel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnLogin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(btnCancel, gbc);
        pack();

    }

 

}