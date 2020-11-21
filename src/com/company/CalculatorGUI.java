package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

/**
 * the CalculatorGUI class represents a calculator with 2 modes : 1- basic and 2- scientific.
 * the basic one have operators + , - , * , / , and %.
 * the scientific one in addition to these features , have log , exp , sin , cos , tan and cot operators.
 *
 * @author Erfan Majedi
 * @version 1.0
 * @since 2020.11.21
 */

public class CalculatorGUI {

    //a frame for calculator.
    private JFrame calcFrame;

    //text area to show calculations.
    private JTextArea display;

    //last number in textarea.
    private double lastNumber;

    //operand for each calculation.
    private String op = " ";

    //the tan button.
    private JButton tanBtn;
    //the sin button.
    private JButton sinBtn;
    //the log button.
    private JButton logBtn;
    //the e and pi button.
    private JButton eAndPiBtn;

    public CalculatorGUI() {

        //init the frame.
        makeFrame();

        //add key listener.
        calcFrame.addKeyListener(new KeyAction());
        //init the menu bar.
        initMenu();


        //a panel for basic mode.
        JPanel basicPanel = new JPanel();
        basicPanel.setLayout(new GridLayout(5, 4));

        //a panel for scientific mode.
        JPanel scientificPanel = new JPanel();
        scientificPanel.setLayout(new GridLayout(5, 5));
        scientificPanel.setName("scientificPanel");

        //text area to show details.
        display = new JTextArea(3, 10);
        display.setEditable(false);
        display.setFont(new Font("Serif", Font.ITALIC, 16));
        display.setToolTipText("display calculation here");
        //add scroll pane for text area.
        JScrollPane scrollPane = new JScrollPane(display);


        //make buttons for basic mode.
        initBasicOperatorButtons(basicPanel);
        //make buttons for scientific mode.
        initBasicOperatorButtons(scientificPanel);

        //make 2 tabs to switch between 2 modes.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("                 Basic                ", basicPanel);
        tabbedPane.add("                 Scientific                 ", scientificPanel);


        //add text area and tab to main frame.
        JPanel jPanel = new JPanel();
        jPanel.setFocusable(true);
        jPanel.requestFocusInWindow();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(scrollPane, BorderLayout.NORTH);
        jPanel.add(tabbedPane, BorderLayout.CENTER);
        calcFrame.add(jPanel, BorderLayout.CENTER);

        calcFrame.setVisible(true);

    }

    /**
     * this makeFrame method initializes the frame.
     */
    public void makeFrame() {

        calcFrame = new JFrame();
        calcFrame.setTitle("AUT Calculator");
        calcFrame.setSize(400, 500);
        calcFrame.setLocation(100, 200);
        calcFrame.setLayout(new BorderLayout());
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcFrame.setFocusable(true);
        calcFrame.requestFocusInWindow();
    }

    /**
     * this initNumberButtons method makes the numerical buttons of the calculator.
     *
     * @param basicPanel the panel want to add buttons to it.
     */
    public void initNumberButtons(JPanel basicPanel) {

        for (int i = 9; i > 0; i--) {

            JButton btn = new JButton();
            btn.addActionListener(new ActionClass());
            btn.addKeyListener(new KeyAction());

            switch (i) {
                case 9:
                    btn.setText("⑨");
                    basicPanel.add(btn);
                    break;
                case 8:
                    btn.setText("⑧");
                    basicPanel.add(btn);
                    break;
                case 7:
                    btn.setText("⑦");
                    basicPanel.add(btn);

                    //make sum buttons.
                    JButton sumBtn = new JButton();
                    sumBtn.setText("+");
                    sumBtn.setBackground(Color.RED);
                    basicPanel.add(sumBtn);
                    sumBtn.addActionListener(new ActionClass());
                    sumBtn.setToolTipText("sum button");
                    sumBtn.addKeyListener(new KeyAction());

                    //make log and exp buttons.
                    if (basicPanel.getName() != null) {
                        logBtn = new JButton();
                        logBtn.setText("log");
                        logBtn.setBackground(Color.WHITE);
                        basicPanel.add(logBtn);
                        logBtn.addActionListener(new ActionClass());
                        logBtn.addKeyListener(new KeyAction());

                    }
                    break;
                case 6:
                    btn.setText("⑥");
                    basicPanel.add(btn);
                    break;
                case 5:
                    btn.setText("⑤");
                    basicPanel.add(btn);
                    break;
                case 4:
                    btn.setText("④");
                    basicPanel.add(btn);

                    //make multiply button.
                    JButton multiplyBtn = new JButton();
                    multiplyBtn.setText("✕");
                    multiplyBtn.setBackground(Color.RED);
                    basicPanel.add(multiplyBtn);
                    multiplyBtn.addActionListener(new ActionClass());
                    multiplyBtn.setToolTipText("multiply button");
                    multiplyBtn.addKeyListener(new KeyAction());

                    //make e and pi button.
                    if (basicPanel.getName() != null) {
                        eAndPiBtn = new JButton();
                        eAndPiBtn.setText("ℯ");
                        eAndPiBtn.setBackground(Color.WHITE);
                        basicPanel.add(eAndPiBtn);
                        eAndPiBtn.addActionListener(new ActionClass());
                        eAndPiBtn.addKeyListener(new KeyAction());

                    }

                    break;
                case 3:
                    btn.setText("③");
                    basicPanel.add(btn);
                    break;
                case 2:
                    btn.setText("②");
                    basicPanel.add(btn);
                    break;
                case 1:
                    btn.setText("①");
                    basicPanel.add(btn);
                    break;
            }
        }
    }

    /**
     * this initBasicOperatorButtons method makes the operation buttons of the calculator.
     *
     * @param basicPanel the panel want to add buttons to it.
     */

    public void initBasicOperatorButtons(JPanel basicPanel) {

        //make sin and cos button.
        if (basicPanel.getName() != null) {
            sinBtn = new JButton();
            sinBtn.setText("sin");
            sinBtn.setBackground(Color.WHITE);
            basicPanel.add(sinBtn);
            sinBtn.addActionListener(new ActionClass());
            sinBtn.addKeyListener(new KeyAction());

        }

        //make parentheses button.
        JButton ParanBtn = new JButton();
        ParanBtn.setText("( )");
        ParanBtn.setBackground(Color.pink);
        basicPanel.add(ParanBtn);
        ParanBtn.addKeyListener(new KeyAction());
        ParanBtn.addActionListener(new ActionClass());

        //make radical button.
        JButton sqrtBtn = new JButton();
        sqrtBtn.setText("√");
        sqrtBtn.setBackground(Color.pink);
        basicPanel.add(sqrtBtn);
        sqrtBtn.addActionListener(new ActionClass());
        sqrtBtn.setToolTipText("radical button");
        sqrtBtn.addKeyListener(new KeyAction());

        //make minezB button.
        JButton minezBtn = new JButton();
        minezBtn.setText("-");
        minezBtn.setBackground(Color.pink);
        basicPanel.add(minezBtn);
        minezBtn.addActionListener(new ActionClass());
        minezBtn.addKeyListener(new KeyAction());


        //make remaining button.
        JButton amountBtn = new JButton();
        amountBtn.setText("%");
        amountBtn.setBackground(Color.RED);
        basicPanel.add(amountBtn);
        amountBtn.addActionListener(new ActionClass());
        amountBtn.addKeyListener(new KeyAction());

        //make tan and cot button.
        if (basicPanel.getName() != null) {

            tanBtn = new JButton();
            tanBtn.setText("tan");
            tanBtn.setBackground(Color.WHITE);
            basicPanel.add(tanBtn);
            tanBtn.addActionListener(new ActionClass());
            tanBtn.addKeyListener(new KeyAction());

        }

        //make numbers.
        initNumberButtons(basicPanel);

        //make division button.
        JButton divBtn = new JButton();
        divBtn.setText("÷");
        divBtn.setBackground(Color.RED);
        basicPanel.add(divBtn);
        divBtn.addActionListener(new ActionClass());
        divBtn.setToolTipText("division button");
        divBtn.addKeyListener(new KeyAction());

        //make shift button.
        if (basicPanel.getName() != null) {
            JButton shiftBtn = new JButton();
            shiftBtn.setText("⇧");
            shiftBtn.setBackground(Color.GRAY);
            basicPanel.add(shiftBtn);
            shiftBtn.addActionListener(new ActionClass());
            shiftBtn.setToolTipText("shift button");
            shiftBtn.addKeyListener(new KeyAction());
        }

        //make C button.
        JButton cBtn = new JButton();
        cBtn.setText("C");
        cBtn.setBackground(Color.WHITE);
        basicPanel.add(cBtn);
        cBtn.addActionListener(new ActionClass());
        cBtn.addKeyListener(new KeyAction());

        //make zero button.
        JButton zeroBtn = new JButton();
        zeroBtn.setText("⓪");
        basicPanel.add(zeroBtn);
        zeroBtn.addActionListener(new ActionClass());
        zeroBtn.addKeyListener(new KeyAction());

        //make dot button.
        JButton dotBtn = new JButton();
        dotBtn.setText(".");
        dotBtn.setBackground(Color.WHITE);
        basicPanel.add(dotBtn);
        dotBtn.addActionListener(new ActionClass());
        dotBtn.addKeyListener(new KeyAction());

        //make equal button.
        JButton doBtn = new JButton();
        doBtn.setText("=");
        doBtn.setBackground(Color.YELLOW);
        basicPanel.add(doBtn);
        doBtn.addActionListener(new ActionClass());
        doBtn.setToolTipText("equal button");
        doBtn.addKeyListener(new KeyAction());
    }

    /**
     * this initMenu method makes the menu bar with name file
     * and add copy and exit to it as a menuItem.
     */

    public void initMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("File");
        jMenu.setMnemonic('F');
        JMenuItem exitItem = new JMenuItem("EXIT");
        JMenuItem copyItem = new JMenuItem("COPY");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        copyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(display.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
                display.setText(display.getText() + "\nText copied to clipboard !");
            }
        });

        jMenu.add(exitItem);
        jMenu.add(copyItem);
        jMenuBar.add(jMenu);
        calcFrame.add(jMenuBar, BorderLayout.NORTH);
    }

    /**
     * keyAction class implements KeyListener and its used to override keyPressed method
     * to take inputs from keyboard and use it for the calculator.
     */
    private class KeyAction implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        char operand = ' ';

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.isShiftDown()) {
                if (sinBtn.getText().equals("sin"))
                    sinBtn.setText("cos");
                else sinBtn.setText("sin");

                if (tanBtn.getText().equals("tan"))
                    tanBtn.setText("cot");
                else tanBtn.setText("tan");

                if (logBtn.getText().equals("log"))
                    logBtn.setText("exp");
                else logBtn.setText("log");

                if (eAndPiBtn.getText().equals("ℯ"))
                    eAndPiBtn.setText("π");
                else eAndPiBtn.setText("ℯ");

            }
            if (e.getKeyChar() == '+' || e.getKeyChar() == '%' || e.getKeyChar() == '-' || e.getKeyChar() == '/' || e.getKeyChar() == '*') {

                if (e.getKeyChar() != '\uFFFF') {
                    lastNumber = Double.valueOf(display.getText());
                    display.setText("0");
                    operand = e.getKeyChar();
                }
            } else if (!e.isShiftDown() && (e.getKeyChar() == '1' || e.getKeyChar() == '2' || e.getKeyChar() == '3' || e.getKeyChar() == '4' || e.getKeyChar() == '5' || e.getKeyChar() == '6' || e.getKeyChar() == '7' || e.getKeyChar() == '8' || e.getKeyChar() == '9' || e.getKeyChar() == '0'|| e.getKeyChar() == '.')) {
                if (display.getText().equals("0")) display.setText("" + e.getKeyChar());
                else display.setText(display.getText() + e.getKeyChar());
            }
            double result = 0;
            if (e.getKeyChar() == '=') {
                switch (operand) {
                    case '+':
                        result = lastNumber + Double.valueOf(display.getText());
                        break;
                    case '-':
                        result = lastNumber - Double.valueOf(display.getText());
                        break;
                    case '/':
                        result = lastNumber / Double.valueOf(display.getText());
                        break;
                    case '%':
                        result = lastNumber % Double.valueOf(display.getText());
                        break;
                    case '*':
                        result = lastNumber * Double.valueOf(display.getText());
                        break;
                }
                display.setText(String.valueOf(result));

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * ActionsClass which implements ActionListener , is used to override actionPerformed method
     * to listen to button clicks.
     */
    private class ActionClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String btnLable = ((JButton) e.getSource()).getText();

            if (btnLable.equals("⓪")) {
                if (display.getText().equals("0")) display.setText("0");
                else display.setText(display.getText() + "0");
            }
            if (btnLable.equals("①")) {
                if (display.getText().equals("0")) display.setText("1");
                else display.setText(display.getText() + "1");
            }
            if (btnLable.equals("②")) {
                if (display.getText().equals("0")) display.setText("2");
                else display.setText(display.getText() + "2");
            }
            if (btnLable.equals("③")) {
                if (display.getText().equals("0")) display.setText("3");
                else display.setText(display.getText() + "3");
            }
            if (btnLable.equals("④")) {
                if (display.getText().equals("0")) display.setText("4");
                else display.setText(display.getText() + "4");
            }
            if (btnLable.equals("⑤")) {
                if (display.getText().equals("0")) display.setText("5");
                else display.setText(display.getText() + "5");
            }
            if (btnLable.equals("⑥")) {
                if (display.getText().equals("0")) display.setText("6");
                else display.setText(display.getText() + "6");
            }
            if (btnLable.equals("⑦")) {
                if (display.getText().equals("0")) display.setText("7");
                else display.setText(display.getText() + "7");
            }
            if (btnLable.equals("⑧")) {
                if (display.getText().equals("0")) display.setText("8");
                else display.setText(display.getText() + "8");
            }
            if (btnLable.equals("⑨")) {
                if (display.getText().equals("0")) display.setText("9");
                else display.setText(display.getText() + "9");
            }
            if (btnLable.equals("C")) {
                display.setText("0");
            }
            if (btnLable.equals(".")) {
                display.setText(display.getText() + ".");
            }
            if (btnLable.equals("( )")) {
                display.setText(display.getText() + "(");
            }
            if (btnLable.equals("⇧")) {

                if (sinBtn.getText().equals("sin"))
                    sinBtn.setText("cos");
                else sinBtn.setText("sin");

                if (tanBtn.getText().equals("tan"))
                    tanBtn.setText("cot");
                else tanBtn.setText("tan");

                if (logBtn.getText().equals("log"))
                    logBtn.setText("exp");
                else logBtn.setText("log");

                if (eAndPiBtn.getText().equals("ℯ"))
                    eAndPiBtn.setText("π");
                else eAndPiBtn.setText("ℯ");
            }
            if (btnLable.equals("+") || btnLable.equals("%") || btnLable.equals("-") || btnLable.equals("÷") || btnLable.equals("√") || btnLable.equals("✕")) {
                lastNumber = Double.valueOf(display.getText());
                display.setText("0");
                op = btnLable;
            }
            if (btnLable.equals("√")) {
                double result = Math.sqrt(lastNumber);
                display.setText(String.valueOf(result));
            }
            if (btnLable.equals("sin")) {
                lastNumber = Double.valueOf(display.getText());
                double degree = Math.toRadians(lastNumber);
                double result = Math.sin(degree);
                display.setText(String.valueOf(result));
            }
            if (btnLable.equals("cos")) {

                lastNumber = Double.valueOf(display.getText());
                double degree = Math.toRadians(lastNumber);
                double result = Math.cos(degree);
                display.setText(String.valueOf(result));
            }
            if (btnLable.equals("tan")) {
                lastNumber = Double.valueOf(display.getText());
                double degree = Math.toRadians(lastNumber);
                double result = Math.tan(degree);
                display.setText(String.valueOf(result));
            }
            if (btnLable.equals("cot")) {
                lastNumber = Double.valueOf(display.getText());
                double degree = Math.toRadians(lastNumber);
                double result = (1 / Math.tan(degree));
                display.setText(String.valueOf(result));
            }
            if (btnLable.equals("log")) {
                lastNumber = Double.valueOf(display.getText());
                double result = Math.log(lastNumber);
                display.setText(String.valueOf(result));
            }
            if (btnLable.equals("π")) {
                display.setText(display.getText() + "π");
            }
            if (btnLable.equals("ℯ")) {
                display.setText(display.getText() + "ℯ");
            }
            if (btnLable.equals("=")) {
                double result = 0;
                if (op.equals("+")) {
                    result = lastNumber + Double.valueOf(display.getText());
                }
                if (op.equals("%")) {
                    result = lastNumber % Double.valueOf(display.getText());
                }
                if (op.equals("-")) {
                    result = lastNumber - Double.valueOf(display.getText());
                }
                if (op.equals("÷")) {
                    result = lastNumber / Double.valueOf(display.getText());
                }
                if (op.equals("✕")) {
                    result = lastNumber * Double.valueOf(display.getText());
                }
                display.setText(String.valueOf(result));
            }

        }

    }
}

