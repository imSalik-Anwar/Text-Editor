// Importing the Swing package
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
// Creating a new fresh class and implementing the ActionListener interface to add listen to the action events
public class TextEditor implements ActionListener{
    // Declaring Properties of Text Editor

    // 1. A frame for application window
    JFrame frame;
    // 2. The menubar
    JMenuBar menuBar;
    // 3. Menus to embed into the menubar
    JMenu file, edit;
    // 4. Menu items to put into the menus
    JMenuItem newFile, saveFile, openFile, cut, copy, paste, selectAll, close;
    // 5. The text area
    JTextArea textArea;

    TextEditor(){
        // 1. Intialize a new frame
        frame = new JFrame();

        // 2. Intializing a menubar
        menuBar = new JMenuBar();

        // 3. Initializing the menues
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // 4. Initializing the menu items
        // 4.1 Intializing the menu items for the file menu
        newFile = new JMenuItem("New Window");
        saveFile = new JMenuItem("Save");
        openFile = new JMenuItem("Open");
        // 4.2 Intializing the menu items for the edit menu
        cut = new JMenuItem("Cut                      Ctrl+X");
        copy = new JMenuItem("Copy                   Ctrl+C");
        paste = new JMenuItem("Paste                  Ctrl+V");
        selectAll = new JMenuItem("Select All           Ctrl+A");
        close = new JMenuItem("Close Window  Alt+F4");

        // 5. Intializing the text area
        textArea = new JTextArea();

        // 6. Before adding the menu items to the menubar, we add action listener functionality to all the menu items
        // because these menu items will have action events and these action events must be listened by the ActionListener interface
        // so that these action events can use the actionPerformed method existing the ActionListener interface
        // 6.1 Adding ActionListener to the File menu items
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);
        // 6.2 Adding ActionListener to the Edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        

        // 6. Adding menu items to their respective menues
        // 6.1 Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        // 6.2 Adding menu items to edit menu 
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // 7. Adding the menues to the menubar
        menuBar.add(file);
        menuBar.add(edit);

        // 8. Adding the menubar to the frame
        frame.setJMenuBar(menuBar);

        // 9. Initialize a panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        // 10. Add text area to the panel
        panel.add(textArea, BorderLayout.CENTER);

        // 11. Initialize scroll pane to add scroll bar to the text area. Also, add the text area to the scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // 12. Add the scrollpane to the panel
        panel.add(scrollPane);

        // 13. Add the panel to the frame
        frame.add(panel);
        

        // 13. Setting dimensions of the frame
        frame.setBounds(100, 100, 400, 300);
        frame.setTitle("Text Editor (Mini Project)");
        frame.setVisible(true);
        frame.setLayout(null);

        
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // Performing edit menu operations using inbuilt functions of JTextArea class
        if(actionEvent.getSource() == cut){
            // perfrom cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            // perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            // perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            // perform select all operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            // perform close window operation
            System.exit(0);
        }

        //performing file menu operations
        if(actionEvent.getSource() == newFile){
            // perform open new window operation
            new TextEditor();
        }
        if(actionEvent.getSource() == openFile){
            // 1. Initiate an object for JFileChooser class
            JFileChooser fileChooser = new JFileChooser("C:");
            // 2. keep a flag variable to check if user is selecting the Open option or Cancel option from the file chooser window
            int chosenOption = fileChooser.showOpenDialog(null);
            // 3. In case user has selected Open option
            if(chosenOption == JFileChooser.APPROVE_OPTION){
                // 3.1 get the selected file
                File file = fileChooser.getSelectedFile();
                // 3.2 get the path of the slected file
                String filePath = file.getPath();
                try{
                    // 3.3 Initialize a file reader
                    FileReader fileReader = new FileReader(filePath);
                    // 3.4 Intialize a buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    // 3.5 Take two strings. One for line-by-line file reading, second for saving the whole text
                    String intermediate = "", output = "";
                    // 3.6 Run a loop to read the whole text line-by-line
                    while((intermediate = bufferedReader.readLine()) != null){
                        // 3.7 Add the line to the output string
                        output += intermediate + "\n";
                    }
                    // 3.8 set the output string to the text area
                    textArea.setText(output);
                    // 3.9 handle the execption
                } catch (IOException fileNotFouIoException){
                    fileNotFouIoException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            // 1. Initialize an object for JFileChooser class
            JFileChooser fileChooser = new JFileChooser("C:");
            // 2. keep a flag variable to check if user has selected the save option or not
            int chosenOption = fileChooser.showSaveDialog(fileChooser);
            // 3. If the selected option is save
            if(chosenOption == JFileChooser.APPROVE_OPTION){
                // 3.1 Create a new file with chosen directory path, file name and file extension
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try{
                    // 3.2 Initialize a new file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // 3.3 Initialize a buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // 3.4 write the text into text area
                    textArea.write(bufferedWriter);
                    // 3.5 close the buffered writer
                    bufferedWriter.close();
                    // 3.6 handle the exception
                } catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        new TextEditor();
    }
}