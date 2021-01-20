//package view;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.IOException;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import javax.swing.*;
//import javax.swing.text.AttributeSet;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.PlainDocument;
//import java.awt.*;
//
//import model.Category;
//
//import javax.swing.JPanel;
//
//import model.CostItem;
//
///**
// * This class defines the front end of our application.
// * The variables are shared in all screens
// */
//public class Gui implements IView {
//    private JFrame frame;
//    private JButton btn1, btn2, btn3, btn4, btn5, btn6, backToHomePage;
//    private JPanel panel1, panel2, panel3, panel4, panel5;
//
//    private JComboBox categories, sortBy, curriencies;
//    private JTextField dateField, currencyField, amountField, newCategoryField, fromField, toField, message, toDelete;
//    private JTextArea descriptionField, costs;
//    private ArrayList<JCheckBox> checkboxes = null;
//    private JLabel header, label1, label2, label3, label4, label5, label6, labelMsg;
//    private JScrollPane scroll;
//    private JTable costsTable;
////     private JFreeChart pieChart;
//
//    /**
//     * Constructor, initiates the variables
//     */
//    public Gui() {
//        this.frame = new JFrame("Cost Manager");
//        this.frame.setSize(500, 40);
//        frame.addWindowListener(new WindowAdapter() {
//            /**
//             * Invoked when a window is in the process of being closed.
//             * The close operation can be overridden at this point.
//             *
//             * @param e
//             */
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
//
//        btn1 = new JButton();
//        btn2 = new JButton();
//        btn3 = new JButton();
//        btn4 = new JButton();
//        btn5 = new JButton();
//        btn6 = new JButton();
//        backToHomePage = new JButton();
//
//        panel1 = new JPanel();
//        panel2 = new JPanel();
//        panel3 = new JPanel();
//
//        categories = new JComboBox();
//        sortBy = new JComboBox();
//
////        descriptionField = new JTextArea();
//        dateField = new JTextField();
//        currencyField = new JTextField();
//        amountField = new JTextField();
//        newCategoryField = new JTextField();
//        fromField = new JTextField();
//        toField = new JTextField();
//
//        checkboxes = new ArrayList<>();
//        header = new JLabel();
//        labelMsg = new JLabel("Message:");
//        labelMsg.setFont(new Font("Ariel", Font.ITALIC, 22));
//        message = new JTextField(25);
//        message.setEditable(false);
//
//        backToHomePage = new JButton("< Back Home");
//        backToHomePage.setSize(50, 30);
//        backToHomePage.setFont(new Font("Ariel", Font.BOLD, 14));
//    }
//
//    /**
//     * This method creates the main menu of the application
//     */
//    @Override
//    public void createHomePage() {
//
//        btn1 = new JButton("Add Expense");
//        btn2 = new JButton("Add Category");
//        btn3 = new JButton("Delete Expense");
//
//        btn4 = new JButton("Pie Chart summary");
//        btn5 = new JButton("Category summary");
//        btn6 = new JButton("List summary");
//
//        btn1.setPreferredSize(new Dimension(200, 100));
//        btn2.setPreferredSize(new Dimension(200, 100));
//        btn3.setPreferredSize(new Dimension(200, 100));
//        btn4.setPreferredSize(new Dimension(200, 100));
//        btn5.setPreferredSize(new Dimension(200, 100));
//        btn6.setPreferredSize(new Dimension(200, 100));
//
//        header = new JLabel("Home Page");
//        header.setFont(new Font("Ariel", Font.BOLD, 34));
//
//        panel1 = new JPanel();
//
//        panel2.setLayout(new FlowLayout());
//        panel2.add(header);
//        frame.add(panel2, BorderLayout.NORTH);
//
//        panel1.setLayout(new GridLayout(2, 3));
//        panel1.add(btn1);
//        panel1.add(btn2);
//        panel1.add(btn3);
//        panel1.add(btn4);
//        panel1.add(btn5);
//        panel1.add(btn6);
//
//        frame.add(panel1, BorderLayout.CENTER);
//        frame.setSize(700, 700);
//        frame.setVisible(true);
//    }
//
//    /**
//     * This method asks for input from the user and creates a new expense
//     */
//    @Override
//    public void addExpense() {
//        class JTextFieldLimit extends PlainDocument {
//            private int limit;
//
//            JTextFieldLimit(int limit) {
//                super();
//                this.limit = limit;
//            }
//
//            JTextFieldLimit(int limit, boolean upper) {
//                super();
//                this.limit = limit;
//            }
//
//            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
//                if (str == null)
//                    return;
//                if ((getLength() + str.length()) <= limit) {
//                    super.insertString(offset, str, attr);
//                }
//            }
//        }
//
//        dateField = new JTextField();
//        currencyField = new JTextField();
//        amountField = new JTextField();
//        newCategoryField = new JTextField();
//
//        frame = new JFrame("Add Expense");
//        frame.setSize(400, 600);
//        frame.setResizable(false);
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        dateField = new JTextField(20);
//        amountField = new JTextField(20);
//
//
//        descriptionField = new JTextArea(1, 10);
//        descriptionField.setLineWrap(true);
//
//        descriptionField.setDocument(new JTextFieldLimit(80));
//
//
//        dateField.setFont(new Font("Ariel", Font.PLAIN, 16));
//        amountField.setFont(new Font("Ariel", Font.PLAIN, 16));
//        descriptionField.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        dateField.setMaximumSize(new Dimension(200, 30));
//        amountField.setMaximumSize(new Dimension(200, 30));
//        descriptionField.setMaximumSize(new Dimension(200, 80));
//
//        String[] CategoriesStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
//        String[] currenciesStrings = {"USD", "ILS", "GBP", "EURO"};
//
//        categories = new JComboBox(CategoriesStrings);
//        curriencies = new JComboBox(currenciesStrings);
//
//        categories.setFont(new Font("Ariel", Font.PLAIN, 16));
//        curriencies.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        categories.setMaximumSize(new Dimension(200, 30));
//        curriencies.setMaximumSize(new Dimension(200, 30));
//
//        panel1 = new JPanel();
//        BoxLayout boxlayout = new BoxLayout(panel1, BoxLayout.Y_AXIS);
//        panel1.setLayout(boxlayout);
//
//        label1 = new JLabel("date");
//        label2 = new JLabel("category");
//        label3 = new JLabel("currency");
//        label4 = new JLabel("amount");
//        label5 = new JLabel("description");
//
//        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
//        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
//        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
//        label4.setAlignmentX(Component.CENTER_ALIGNMENT);
//        label5.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        label1.setFont(new Font("Ariel", Font.BOLD, 25));
//        label2.setFont(new Font("Ariel", Font.BOLD, 25));
//        label3.setFont(new Font("Ariel", Font.BOLD, 25));
//        label4.setFont(new Font("Ariel", Font.BOLD, 25));
//        label5.setFont(new Font("Ariel", Font.BOLD, 25));
//
//        btn2 = new JButton("Submit");
//
////        btn2.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                    System.out.println(descriptionField.getText());
////            }
////        });
//
//        btn2.setAlignmentX(Component.CENTER_ALIGNMENT);
//        btn2.setFont(new Font("Ariel", Font.BOLD, 25));
//
//        panel1.add(label1);
//        panel1.add(dateField);
//
//        panel1.add(label2);
//        panel1.add(categories);
//
//        panel1.add(label3);
//        panel1.add(curriencies);
//
//        panel1.add(label4);
//        panel1.add(amountField);
//
//        panel1.add(label5);
//        panel1.add(descriptionField);
//
//        panel1.add(btn2);
//
//        panel3 = new JPanel();
//        panel3.setLayout(new FlowLayout());
//        panel3.add(header);
//
//        panel4 = new JPanel();
//        panel4.setLayout(new FlowLayout());
//        panel4.add(labelMsg);
//        panel4.add(message);
//
//        panel2 = new JPanel();
//        panel2.setLayout(new BorderLayout());
//        panel2.add(backToHomePage, BorderLayout.WEST);
//
//        frame.add(panel1, BorderLayout.CENTER);
//        frame.add(panel4, BorderLayout.SOUTH);
//        frame.add(panel2, BorderLayout.NORTH);
//
//        frame.setVisible(true);
//    }
//
//    /**
//     * This method is used to delete an expense from the DB
//     */
//    @Override
//    public void deleteExpense(ArrayList<CostItem> data) {
//        frame = new JFrame("Delete Expense");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(700, 700);
//        frame.setResizable(false);
//
//        panel1 = new JPanel();
//        BoxLayout boxlayout = new BoxLayout(panel1, BoxLayout.Y_AXIS);
//        panel1.setLayout(boxlayout);
//
//        costs = new JTextArea(25, 60);
//        costs.setFont(new Font("Ariel", Font.PLAIN, 16));
//        costs.setLineWrap(true);
//        costs.setEditable(false);
//
////         for(CostItem item : data){
////             costs.append(item.toString() + '\n');
////         }
//
//        scroll = new JScrollPane(costs);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//
//        label1 = new JLabel("Enter id:");
//        label1.setFont(new Font("Ariel", Font.BOLD, 16));
//        btn1 = new JButton("Delete");
//        btn1.setFont(new Font("Ariel", Font.BOLD, 16));
//
//        toDelete = new JTextField(20);
//        toDelete.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        panel2 = new JPanel();
//        panel2.setLayout(new FlowLayout());
//        panel2.add(label1);
//        panel2.add(toDelete);
//        panel2.add(btn1);
//
//        panel1.add(panel2);
//        panel1.add(scroll);
//
//        panel2 = new JPanel();
//        panel2.setLayout(new BorderLayout());
//        panel2.add(backToHomePage, BorderLayout.WEST);
//
//        panel3 = new JPanel();
//        panel3.setLayout(new FlowLayout());
//        panel3.add(labelMsg);
//        panel3.add(message);
//
//        frame.add(panel2, BorderLayout.NORTH);
//        frame.add(panel1, BorderLayout.CENTER);
//        frame.add(panel3, BorderLayout.SOUTH);
//        frame.setVisible(true);
//    }
//
//
//    /**
//     * This method asks for dates from the user in which he wants to get the summary
//     */
//    @Override
//    public void getDatesForSummary(boolean isChart) { // false where display by dates, true when called from displayPieChart
//        frame = new JFrame("Enter Dates");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.setResizable(false);
//
//        panel1 = new JPanel();
//        BoxLayout boxlayout = new BoxLayout(panel1, BoxLayout.Y_AXIS);
//        panel1.setLayout(boxlayout);
//
//        label1 = new JLabel("From date");
//        label2 = new JLabel("To Date");
//
//        fromField = new JTextField(20);
//        toField = new JTextField(20);
//
//        fromField.setFont(new Font("Ariel", Font.PLAIN, 16));
//        toField.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        fromField.setMaximumSize(new Dimension(250, 30));
//        toField.setMaximumSize(new Dimension(250, 30));
//
//        label1.setFont(new Font("Ariel", Font.BOLD, 20));
//        label2.setFont(new Font("Ariel", Font.BOLD, 20));
//
//        btn1 = new JButton("Submit");
//        btn1.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
//        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
//        fromField.setAlignmentX(Component.CENTER_ALIGNMENT);
//        toField.setAlignmentX(Component.CENTER_ALIGNMENT);
//        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        panel1.add(label1);
//        panel1.add(fromField);
//        panel1.add(label2);
//        panel1.add(toField);
//        panel1.add(btn1);
//
//        panel3 = new JPanel();
//        panel3.setLayout(new BorderLayout());
//        panel3.add(backToHomePage, BorderLayout.WEST);
//
//        frame.add(panel3, BorderLayout.NORTH);
//        frame.add(panel1, BorderLayout.CENTER);
//        frame.setVisible(true);
//
//        // forward dates to relevant function
//        if (isChart) {
//            //displayPieChart(vm.generateMap())
//        } else {
//            // call function of dates summary
//        }
//
//    }
//
//    /**
//     * This function creates summary according to the user requent (by dates / by category)
//     */
//    @Override
//    public void generateSummary(String[] params) {
//
////        boolean dates = true;
//        String title = "Dates summary";
//        if (params.length == 1)
//            title = "Category summary";
//
//        frame = new JFrame();
//
//        frame.setTitle(title);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //select * from data where date between xxx and xxx
//        //select * from data where category = "cat"
//
//        String[][] data = { // String[][] data  <- vm.getData(params)
//                {"Kundan Kumar Jha", "09-09-8080", "CSE", "", "none", ""},
//                {"Anand Jha", "6014", "IT", "", "none", "01234567890123456789012345678901234567890123456789012345678901234567890123456789"},
//                {"Anand Jha", "99.99.9999", "IT", "", "none", "when we went travelling and haf coffeffd"},
//        };
//
//        String[] columnNames = {"Id", "Date", "Category", "Currency", "Amount", "Description"};
//
//        costsTable = new JTable(data, columnNames);
//
//        costsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
//        costsTable.getColumnModel().getColumn(1).setPreferredWidth(120);
//        costsTable.getColumnModel().getColumn(2).setPreferredWidth(200);
//        costsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
//        costsTable.getColumnModel().getColumn(4).setPreferredWidth(120);
//        costsTable.getColumnModel().getColumn(5).setPreferredWidth(720);
//
//        JScrollPane sp = new JScrollPane(costsTable);
//
//        frame.add(sp);
//
//        frame.setSize(1400, 1000);
//        frame.setVisible(true);
//        costsTable.setEnabled(false);
//
//    }
//
//    /**
//     * This function creates window that ask the user to enter the category he wants to get summary by.
//     */
//    @Override
//    public void getCategoryForSummary() {
//        frame = new JFrame("Add Category");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.setResizable(false);
//
//        label1 = new JLabel("Choose category");
//
//        String[] CategoriesStrings = {"food", "clubs", "cars", "fun", "Drugs"};
//        categories = new JComboBox(CategoriesStrings); // vm.getCategories
//        categories.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        categories.setMaximumSize(new Dimension(250, 30));
//
//
//        label1.setFont(new Font("Ariel", Font.BOLD, 20));
//
//
//        btn1 = new JButton("Submit");
//        btn1.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
//        categories.setAlignmentX(Component.CENTER_ALIGNMENT);
//        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        panel1.add(label1);
//        panel1.add(categories);
//        panel1.add(btn1);
//
////        panel2 = new JPanel();
////        panel2.setLayout(new FlowLayout());
////        panel2.add(labelMsg);
////        panel2.add(message);
//
//        panel3 = new JPanel();
//        panel3.setLayout(new BorderLayout());
//        panel3.add(backToHomePage, BorderLayout.WEST);
//
//        frame.add(panel3, BorderLayout.NORTH);
//        frame.add(panel1, BorderLayout.CENTER);
////        frame.add(panel2, BorderLayout.SOUTH);
//        frame.setVisible(true);
//    }
//
//    /**
//     * This function creates window that ask the user to add new category
//     */
//    @Override
//    public void addCategory() {
//        frame = new JFrame("Add Category");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.setResizable(false);
//
//
//        label1 = new JLabel("Enter new category");
//
//
//        newCategoryField = new JTextField(20);
//
//        newCategoryField.setFont(new Font("Ariel", Font.PLAIN, 16));
//        newCategoryField.setMaximumSize(new Dimension(250, 30));
//
//
//        label1.setFont(new Font("Ariel", Font.BOLD, 20));
//
//
//        btn1 = new JButton("Submit");
//        btn1.setFont(new Font("Ariel", Font.PLAIN, 16));
//
//        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
//        newCategoryField.setAlignmentX(Component.CENTER_ALIGNMENT);
//        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        panel1.add(label1);
//        panel1.add(newCategoryField);
//        panel1.add(btn1);
//
//        panel2 = new JPanel();
//        panel2.setLayout(new FlowLayout());
//        panel2.add(labelMsg);
//        panel2.add(message);
//
//        panel3 = new JPanel();
//        panel3.setLayout(new BorderLayout());
//        panel3.add(backToHomePage, BorderLayout.WEST);
//
//        frame.add(panel3, BorderLayout.NORTH);
//        frame.add(panel1, BorderLayout.CENTER);
//        frame.add(panel2, BorderLayout.SOUTH);
//        frame.setVisible(true);
//    }
//
//    /**
//     * This function gets a map <String, Intenger> between category and costs
//     */
//    @Override
//    public void displayPieChart(Map map) {
////
//        frame = new JFrame("Pie Chart");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(700, 700);
//        frame.setResizable(false);
//    }
//}