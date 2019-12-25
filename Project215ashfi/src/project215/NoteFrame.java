
package project215;


import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import Logic.Encryption;


public class NoteFrame extends JFrame implements ActionListener {

    private static final String CMD_NEW_File = "new";
    private static final String CMD_SAVE_File = "save";
    private static final String CMD_LOAD_File = "load";
    private static final String CMD_EXIT_File = "exit";
    private TextArea note = new TextArea(15, 30);

    public NoteFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Encrypt/Decrypt Text");

        add(note, BorderLayout.CENTER);
        buildMenuBar();
        pack();

    }

    private void buildMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem newFileItem = new MenuItem("New File");
        newFileItem.addActionListener(this);
        newFileItem.setShortcut(new MenuShortcut(KeyEvent.VK_N));
        newFileItem.setActionCommand(CMD_NEW_File);

        MenuItem saveFileItem = new MenuItem("Save");
        saveFileItem.setShortcut(new MenuShortcut(KeyEvent.VK_S));
        saveFileItem.addActionListener(this);
        saveFileItem.setActionCommand(CMD_SAVE_File);

        MenuItem loadFileItem = new MenuItem("Open");
        loadFileItem.setShortcut(new MenuShortcut(KeyEvent.VK_0));
        loadFileItem.addActionListener(this);
        loadFileItem.setActionCommand(CMD_LOAD_File);

        MenuItem exitFileItem = new MenuItem("Exit");
        exitFileItem.setShortcut(new MenuShortcut(KeyEvent.VK_0));
        exitFileItem.addActionListener(this);
        exitFileItem.setActionCommand(CMD_EXIT_File);

        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(loadFileItem);
        fileMenu.add(exitFileItem);
        menuBar.add(fileMenu);

        this.setMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Encryption crypt = new Encryption();

        String command = "";
        String message = "";
        if (e.getSource() instanceof MenuItem) {
            command = ((MenuItem) e.getSource()).getActionCommand();
        }

        System.out.println(command);
        switch (command) {
            case (CMD_NEW_File):
                NoteFrame th = new NoteFrame();
                th.setVisible(true);
                break;
            case (CMD_LOAD_File):
                File aLoadFile = (getFileLocation());
                ObjectInputStream ois = null;
                FileInputStream fis = null;

                String text = "";
                try {
                    fis = new FileInputStream(aLoadFile);
                    ois = new ObjectInputStream(fis);
                    text = ois.readUTF();
                    try {
                        note.setText(crypt.decrypt(text));
                    } catch (Exception ex) {

                    }

                } catch (EOFException eof) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        Logger.getLogger(NoteFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not Found...");
                } catch (NullPointerException npe) {
                    System.out.println("File Corrupt please delete it.");
                } catch (IOException ex) {
                    Logger.getLogger(NoteFrame.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        Logger.getLogger(NoteFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                break;
            case (CMD_SAVE_File):
                File aSaveFile = (getFileLocation());

                FileOutputStream fos = null;
                ObjectOutputStream oos;
                oos = null;

                if (!aSaveFile.getParentFile().exists()) {
                    aSaveFile.getParentFile().mkdir();
                }
                try {
                    if (!aSaveFile.exists()) {
                        aSaveFile.createNewFile();
                    } else {

                        aSaveFile.delete();

                        aSaveFile.createNewFile();
                    }
                    if (aSaveFile.length() > 0) {
                        fos = new FileOutputStream(aSaveFile, true);
                    } else {
                        fos = new FileOutputStream(aSaveFile, false);
                    }
                    if (oos == null) {
                        oos = new ObjectOutputStream(fos);
                    }
                    String textToEncrypt = note.getText();

                    oos.writeUTF(crypt.encrypt(textToEncrypt));
                    oos.flush();
                    note.setText("");
                } catch (FileNotFoundException ex) {

                } catch (Exception x) {
                } finally {
                    try {
                        oos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(NoteFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;

            case (CMD_EXIT_File):
                dispose();
                break;
        }
    }

    public File getFileLocation() {
        File aFile = new File("");
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(fileChooser);
        if (option == JFileChooser.APPROVE_OPTION) {
            aFile = fileChooser.getSelectedFile();
        }
        return aFile;
    }

    public TextArea getNote() {
        return note;
    }

    public void setNote(TextArea note) {
        this.note = note;
    }

}