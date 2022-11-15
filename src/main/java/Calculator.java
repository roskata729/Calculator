import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Constants;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[Constants.CALCULATOR_NUMBER_BUTTONS_COUNT];
    JButton[] functionButtons = new JButton[Constants.CALCULATOR_FUNCTIONAL_BUTTONS_COUNT];
    JButton addButton,subButton, mulButton, divButton;
    JButton decButton, eqiButton, delButton, clrButton, negButton;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator(){
        startCalculator();
    }

    public static void main(String[] args){
        Calculator calculator = new Calculator();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if(e.getSource() == numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource() == decButton){
            textField.setText(textField.getText().concat(String.valueOf(Constants.DECIMAL_BUTTON_TEXT)));
        }
        if(e.getSource() == addButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if(e.getSource() == subButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if(e.getSource() == mulButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if(e.getSource() == divButton){
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if(e.getSource() == eqiButton){
            calculateResult();
        }
        if(e.getSource() == clrButton){
            textField.setText("");
        }
        if(e.getSource() == delButton){
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
        if(e.getSource() == negButton){
            double temp;
            try{
                temp = Double.parseDouble(textField.getText()) * -1;
            } catch (NumberFormatException exception){
                temp = 0;
            }
            textField.setText(String.valueOf(temp));
        }
    }

    private void startCalculator() {
        setupFrame();
        
        setupTextField();

        setupFunctionalButtons();

        setupNumberButtons();

        setupBoundsOfLastButtons();

        addItemsToTheFrame();
    }

    private void calculateResult() {
        num2 = Double.parseDouble(textField.getText());
        switch(operator){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        textField.setText(String.valueOf(result));
        num1 = result;
    }

    private void addItemsToTheFrame() {
        JPanel panel = setupPanel();
        frame.add(negButton);
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    private void setupBoundsOfLastButtons() {
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);
    }

    private void setupNumberButtons() {
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(Constants.CALCULATOR_MAIN_FONT);
            numberButtons[i].setFocusable(false);
        }
    }

    private JPanel setupPanel() {
        JPanel panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(Constants.CALCULATOR_PANEL_LAYOUT);
        //panel.setBackground(Color.GRAY);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(eqiButton);
        panel.add(divButton);
        return panel;
    }

    private void setupFunctionalButtons() {
        addButton = new JButton(Constants.ADD_BUTTON_TEXT);
        subButton = new JButton(Constants.SUBSTRACT_BUTTON_TEXT);
        mulButton = new JButton(Constants.MULTIPLY_BUTTON_TEXT);
        divButton = new JButton(Constants.DIVIDE_BUTTON_TEXT);
        decButton = new JButton(Constants.DECIMAL_BUTTON_TEXT);
        eqiButton = new JButton(Constants.EQUALS_BUTTON_TEXT);
        delButton = new JButton(Constants.DELETE_BUTTON_TEXT);
        clrButton = new JButton(Constants.CLEAR_BUTTON_TEXT);
        negButton = new JButton(Constants.NEGATIVE_BUTTON_TEXT);

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = eqiButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(Constants.CALCULATOR_MAIN_FONT);
            functionButtons[i].setFocusable(false);
        }
    }

    private void setupTextField() {
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(Constants.CALCULATOR_MAIN_FONT);
        textField.setEditable(false);
    }

    private void setupFrame() {
        frame = new JFrame(Constants.CALCULATOR_TITLE_NAME_TEXT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.CALCULATOR_WINDOW_SIZE_WIDTH, Constants.CALCULATOR_WINDOW_SIZE_HEIGHT);
        frame.setLayout(null);
    }
}