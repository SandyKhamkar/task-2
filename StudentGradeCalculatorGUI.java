import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField[] subjectFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculatorGUI() {
        frame = new JFrame("Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        subjectFields = new JTextField[5]; // Assuming 5 subjects
        for (int i = 0; i < subjectFields.length; i++) {
            panel.add(new JLabel("Subject " + (i + 1) + " Marks:"));
            subjectFields[i] = new JTextField();
            panel.add(subjectFields[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        panel.add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        panel.add(totalMarksLabel);
        panel.add(averagePercentageLabel);
        panel.add(gradeLabel);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAndDisplayResults();
            }
        });

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void calculateAndDisplayResults() {
        int totalMarks = 0;
        int numSubjects = subjectFields.length;

        for (int i = 0; i < numSubjects; i++) {
            try {
                int marks = Integer.parseInt(subjectFields[i].getText());
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(frame, "Marks for Subject " + (i + 1) + " should be between 0 and 100.");
                    return;
                }
                totalMarks += marks;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input for Subject " + (i + 1));
                return;
            }
        }

        double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;

        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        gradeLabel.setText("Grade: " + grade);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentGradeCalculatorGUI();
            }
        });
    }
}
