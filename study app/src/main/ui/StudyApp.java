package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import java.awt.event.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Event;


// Represents a study app  that allows you to  setup study methods and  get a study analytics afterwards
public class StudyApp extends JFrame implements WindowListener {

    private static final String JSON_STORE = "./data/StudyAnalytics.json";

    private final JFrame frame;

    private StudyAnalytics analytics;

    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    private final Scanner scanner;

    private JTextField nameField;
    private JTextField descField;
    private JTextField timeField;
    private JTextArea outputArea;


    // Represents the Study application
    public StudyApp() throws FileNotFoundException {

        scanner = new Scanner(System.in);

        analytics = new StudyAnalytics(new ArrayList<>());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        frame = new JFrame();
        frame.setTitle("Study Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(this);

        allButton();
        frame.setVisible(true);


//        run();
    }

    //Effect: sets all the buttons
    //effects: sets all the buttons and displays an image under them
    private void allButton() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        addButton(buttonPanel);
        removeButton(buttonPanel);
        saveButton(buttonPanel);
        loadButton(buttonPanel);
        analyticsButton(buttonPanel);

        contentPane.add(buttonPanel, BorderLayout.WEST);

        ImageIcon imageIcon = new ImageIcon("src/image/logo.png");
        JLabel imageLabel = new JLabel(imageIcon);
        contentPane.add(imageLabel, BorderLayout.SOUTH);

        JTextArea outputArea = new JTextArea(250, 250);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        frame.setContentPane(contentPane);
    }


    //effects:creates analytics button
    private void analyticsButton(Container container) {
        JButton displayAnalyticsButton = new JButton("Display Study Analytics");
        displayAnalyticsButton.addActionListener(new DisplayAnalyticsListener());
        container.add(displayAnalyticsButton);

        JTextArea outputArea = new JTextArea(500, 500);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        container.add(scrollPane);
    }

    //Modifies:This
    //effects: add button
    private void addButton(Container container) {
        JButton addMethodButton = new JButton("Add Study Method");
        addMethodButton.addActionListener(new AddMethodListener());

        addMethodButton.setPreferredSize(new Dimension(150, 40));

        container.add(Box.createVerticalStrut(10));
        container.add(addMethodButton);
    }

    //effects: add button  actions
    private class AddMethodListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAddMethodDialog();
        }
    }

    //Modifies:This
    //Effect: add information to the  button
    private void openAddMethodDialog() {
        JFrame addDialog = new JFrame("Add Study Method");
        addDialog.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);

        JLabel descLabel = new JLabel("Description:");
        descField = new JTextField(20);

        JLabel timeLabel = new JLabel("Time (in minutes):");
        timeField = new JTextField(10);

        JButton addButton = new JButton("Add");
        AddButtonListener addButtonListener = new AddButtonListener(nameField, descField, timeField);
        addButton.addActionListener(addButtonListener);

        addDialog.add(nameLabel);
        addDialog.add(nameField);
        addDialog.add(descLabel);
        addDialog.add(descField);
        addDialog.add(timeLabel);
        addDialog.add(timeField);
        addDialog.add(addButton);

        addDialog.setSize(300, 200);
        addDialog.setVisible(true);
    }

    //effects: add information to the  button action
    private class AddButtonListener implements ActionListener {
        private JTextField nameField;
        private JTextField descField;
        private JTextField timeField;
        private JTextArea outputArea;

        //effects: sets the  name, desc, time of thee add button
        public AddButtonListener(JTextField nameField, JTextField descField, JTextField timeField) {
            this.nameField = nameField;
            this.descField = descField;
            this.timeField = timeField;
        }

        //effects: the action of th e savee button listener
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String description = descField.getText();
            long time = 0;

            try {
                time = Long.parseLong(timeField.getText());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            if (!name.isEmpty() && !description.isEmpty() && time > 0) {
                analytics.addStudyMethods(name, description, time);
            } else {
                //stub
            }
        }
    }

    //effects: creates remove button
    private void removeButton(Container container) {
        JButton removeMethodButton = new JButton("Remove Study Method");
        removeMethodButton.addActionListener(new RemoveMethodListener());


        removeMethodButton.setPreferredSize(new Dimension(180, 40));

        container.add(Box.createVerticalStrut(10));
        container.add(removeMethodButton);
    }

    //effects: the  action to remove th emethod
    private class RemoveMethodListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openRemoveMethodDialog();
        }
    }

    //Modifies: this
    //effects: create anothere page to remove a method
    private JFrame createRemoveMethodDialog() {
        JFrame removeDialog = new JFrame("Remove Study Method");
        removeDialog.setLayout(new GridLayout(2, 1));

        JLabel promptLabel = new JLabel("Enter the number of the study method to remove:");
        JTextField removeField = new JTextField(10);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(createRemoveButtonActionListener(removeField));

        removeDialog.add(promptLabel);
        removeDialog.add(removeField);
        removeDialog.add(removeButton);

        removeDialog.setSize(300, 200);
        return removeDialog;
    }

    //effect: nothing
    public void windowOpened(WindowEvent e) {
        //stub
    }

    //effect: nothing
    public void windowIconified(WindowEvent e) {
        //stub
    }

    //effect: nothing
    public void windowDeiconified(WindowEvent e) {
        //stub
    }

    //effect: nothing
    public void windowDeactivated(WindowEvent e) {
        //stub
    }

    //effect: nothing
    public void windowActivated(WindowEvent e) {
        //stub
    }

    @Override
    //effect: Shows eevents loggere wheen closed
    public void windowClosing(WindowEvent e) {
        System.out.println("Events in order:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.getDescription());
        }
    }

    @Override
    //effect: Shows eevents loggere wheen closed
    public void windowClosed(WindowEvent e) {
        //stub
    }


    //effects: the  action to remove th emethod
    private ActionListener createRemoveButtonActionListener(JTextField removeField) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeText = removeField.getText();
                handleRemoveAction(removeText);
            }
        };
    }

    //effects: the  action to close thee dialog
    private void handleRemoveAction(String removeText) {
        try {
            int studyMethodNumber = Integer.parseInt(removeText);
            analytics.removeStudyMethod(studyMethodNumber - 1);
        } catch (NumberFormatException ex) {
            outputArea.setText("Please enter a valid study method number.");
        }
    }


    //effects: open the dialog
    private void openRemoveMethodDialog() {
        JFrame removeDialog = createRemoveMethodDialog();
        removeDialog.setVisible(true);
    }


    //effects: createes the save button
    private void saveButton(Container container) {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        saveButton.setPreferredSize(new Dimension(150, 40));

        container.add(Box.createVerticalStrut(10));
        container.add(saveButton);
    }

    //effects: the action of th e savee button listener
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveWorkRoom();
        }
    }

    //effects: createes the load button
    private void loadButton(Container container) {
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new LoadButtonListener());
        loadButton.setPreferredSize(new Dimension(150, 40));

        container.add(Box.createVerticalStrut(10));
        container.add(loadButton);
    }

    //effects: the action of th e load button listener
    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadWorkRoom();
        }
    }


    // MODIFIES: this
    // EFFECTS: runs  the app
//    public void run() {
//        boolean exit = false;
//        while (!exit) {
//
//            analyticsButton();

//            int choice = scanner.nextInt();
//            scanner.nextLine();

//            if (choice == 6) {
//                exit = true;
//            } else {
//                choice(choice);
//            }
//        }
//    }

//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void choice(int choice) {
//        switch (choice) {
//            case 1:
//                listOutAnalytics();
//                break;
//            case 2:
//                addingMethod();
//                break;
//            case 3:
//                removingMethod();
//                break;
//            case 4:
//                saveWorkRoom();
//                break;
//            case 5:
//                loadWorkRoom();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//        }
//    }


    //effects: preforms  the eaction to open the display
    private class DisplayAnalyticsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayAnalytics();
        }
    }

    // EFFECTS: displays  different options
    private void displayAnalytics() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------\n");
        sb.append("Total Study Time: ").append(analytics.calculateTotalStudyTime()).append(" minutes\n");
        sb.append("Average Study Time Per Session: ")
                .append(analytics.calculateAverageStudyTimePerSession()).append(" minutes\n");
        sb.append("List of Study Methods:\n");
        int numberCount = 1;
        for (StudyMethod studyMethod : analytics.getStudyMethods()) {
            sb.append("---------------------------\n");
            sb.append("Study Method #").append(numberCount).append("\n");
            sb.append("Name: ").append(studyMethod.getName()).append("\n");
            sb.append("Description: ").append(studyMethod.getDescription()).append("\n");
            sb.append("Time: ").append(studyMethod.getTime()).append(" minutes\n");
            sb.append("---------------------------\n");
            numberCount++;
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Study Analytics", JOptionPane.PLAIN_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: prompt user for name and description and time of studyymethod and adds to studyy anayltics
//    private void addingMethod() {
//        boolean isAddingStudyMethods = true;
//
//        while (isAddingStudyMethods) {
//            System.out.println("Enter Study Method Details:");
//            System.out.print("Name: ");
//            String name = scanner.nextLine();
//            System.out.print("Description: ");
//            String description = scanner.nextLine();
//            System.out.print("Time (in minutes): ");
//            long time = scanner.nextLong();
//            scanner.nextLine();
//
//            analytics.addStudyMethods(name, description, time);
//
//
//            System.out.print("Add another study method? (yes/no): ");
//            String addAnother = scanner.nextLine();
//
//            if (!addAnother.equalsIgnoreCase("yes")) {
//                isAddingStudyMethods = false;
//            }
//        }
//    }

    // MODIFIES: this
    // EFFECTS: prompt user for name and description and time of studyymethod and removing studyy method
//    private void removingMethod() {
//        boolean removingStudyMethods = true;
//
//        while (removingStudyMethods) {
//            System.out.print("Are you sure you would like to remove a study method? (yes/no): ");
//            String remove = scanner.nextLine();
//
//            if (remove.equalsIgnoreCase("yes")) {
//                System.out.print("Enter the number of the study method to remove: ");
//                int studyMethodNumber = scanner.nextInt();
//                scanner.nextLine(); // Consume the newline character
//
//                if (analytics.removeStudyMethod(studyMethodNumber - 1)) {
//                    System.out.println("Study Method removed.");
//                } else {
//                    System.out.println("Invalid study method index. No study method removed.");
//                }
//            } else {
//                removingStudyMethods = false;
//            }
//        }
//    }




    // EFFECTS: list out study method and anayltic
//    private void listOutAnalytics() {
//        System.out.println("---------------------------");
//        System.out.println("Total Study Time: " + analytics.calculateTotalStudyTime() + " minutes");
//        System.out.println("Average Study Time Per Session: "
//                +  analytics.calculateAverageStudyTimePerSession() + " minutes");
//        System.out.println("List of Study Methods:");
//        int numberCount = 1;
//        for (StudyMethod studyMethod : analytics.getStudyMethods()) {
//            System.out.println("---------------------------");
//            System.out.printf("Study Method #%d\n", numberCount);
//            System.out.println("Name: " + studyMethod.getName());
//            System.out.println("Description: " + studyMethod.getDescription());
//            System.out.println("Time: " + studyMethod.getTime() + " minutes");
//            System.out.println("---------------------------");
//            numberCount++;
//        }
//    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(analytics);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            analytics = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
