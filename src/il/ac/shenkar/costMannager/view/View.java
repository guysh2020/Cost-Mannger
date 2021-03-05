/*
 * Guy Sharir: 310010244
 * Ido Betesh: 307833822
 */

package il.ac.shenkar.costMannager.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.List;

import il.ac.shenkar.costMannager.model.CostManagerException;
import il.ac.shenkar.costMannager.model.Category;

import javax.swing.JPanel;

import il.ac.shenkar.costMannager.model.CostItem;
import il.ac.shenkar.costMannager.vm.IViewModel;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

/**
 * This class defines the front end of our application.
 * The variables are shared in all screens
 */
public class View implements IView {
    /**
     * @param vm (IViewModel) viewModel interface
     * @param ui (ApplicationUI) the applicarion ui class
     */
    private IViewModel vm;
    private View.ApplicationUI ui;

    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new View.ApplicationUI();
                View.this.ui.createHomePage();
            }
        });
    }

    @Override
    public void displayPieChart(Map map) {
        ui.displayPieChart(map);
    }

    @Override
    public void showMessage(String message) {
        ui.showMessage(message);
    }

    @Override
    public void showItems(List<CostItem> data) {

        CostItem[] res = new CostItem[data.size()];
        for (int i = 0; i < data.size(); i++) {
            res[i] = data.get(i);
        }
        this.ui.showItems(res);
    }

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void setCategories(List<Category> categories) {
        ui.setCategories(categories);
    }


    /**
     * This class displays the front end of our application
     * using swing library
     */
    public class ApplicationUI {

        private JFrame mainFrame, frame;
        private JButton submitBtn;
        private final JButton backToHomePage;
        private JPanel messagePnl;
        private JPanel backHomePnl;
        private JPanel dataPnl;
        private JComboBox currienciesCmbo, categories;
        private JLabel header;
        private final JLabel labelMsg;
        private JTextArea descriptionField;
        private JTextField dateField;
        private JTextField amountField;
        private JTextField newCategoryField;
        private JTextField fromField;
        private JTextField toField;
        private final JTextField message;
        private JTextField toDelete;
        private JScrollPane sp;


        /**
         * Constructor, initiates the variables
         */
        public ApplicationUI() {
            this.mainFrame = new JFrame("Cost Manager");
            this.mainFrame.setSize(500, 40);

            mainFrame.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when a window is in the process of being closed.
                 * The close operation can be overridden at this point.
                 *
                 * @param e
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            header = new JLabel();
            labelMsg = new JLabel("Message:");
            labelMsg.setFont(new Font("Calibri", Font.ITALIC, 22));
            message = new JTextField(25);
            message.setEditable(false);

            backToHomePage = new JButton("< Back Home");
            backToHomePage.setSize(50, 30);
            backToHomePage.setFont(new Font("Calibri", Font.BOLD, 14));

            backToHomePage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.frame.dispose();
                    ApplicationUI.this.mainFrame.setVisible(true);
                }
            });
        }

        /**
         * This method creates the main menu of the application
         */
        public void createHomePage() {

            JButton addExpanseBtn = new JButton("Add Expense");
            JButton addCategoryBtn = new JButton("Add Category");
            JButton deleteExpenseBtn = new JButton("Delete Expense");
            JButton getPieChartBtn = new JButton("Pie Chart summary");
            JButton getCategorySummaryBtn = new JButton("Category summary");
            JButton getListSummaryBtn = new JButton("List summary");
            addExpanseBtn.setFont(new Font("Calibri", Font.BOLD, 25));
            addCategoryBtn.setFont(new Font("Calibri", Font.BOLD, 25));
            deleteExpenseBtn.setFont(new Font("Calibri", Font.BOLD, 25));
            getPieChartBtn.setFont(new Font("Calibri", Font.BOLD, 25));
            getCategorySummaryBtn.setFont(new Font("Calibri", Font.BOLD, 25));
            getListSummaryBtn.setFont(new Font("Calibri", Font.BOLD, 25));

            addExpanseBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.mainFrame.setVisible(false);
                    vm.getCategories();
                    ApplicationUI.this.addExpense();
                }
            });

            addCategoryBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.mainFrame.setVisible(false);
                    ApplicationUI.this.addCategory();
                }
            });

            deleteExpenseBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.mainFrame.setVisible(false);
                    ApplicationUI.this.deleteExpense();
                    vm.getCostItems(new ArrayList<String>());
                }
            });

            getPieChartBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.mainFrame.setVisible(false);
                    ApplicationUI.this.getDatesForSummary(true);
                }
            });

            getCategorySummaryBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.mainFrame.setVisible(false);
                    vm.getCategories();
                    ApplicationUI.this.getCategoryForSummary();
                }
            });

            getListSummaryBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.mainFrame.setVisible(false);
                    ApplicationUI.this.getDatesForSummary(false);
                }
            });

            addExpanseBtn.setPreferredSize(new Dimension(200, 100));
            addCategoryBtn.setPreferredSize(new Dimension(200, 100));
            deleteExpenseBtn.setPreferredSize(new Dimension(200, 100));
            getPieChartBtn.setPreferredSize(new Dimension(200, 100));
            getCategorySummaryBtn.setPreferredSize(new Dimension(200, 100));
            getListSummaryBtn.setPreferredSize(new Dimension(200, 100));

            header = new JLabel("Home Page");
            header.setFont(new Font("Calibri", Font.BOLD, 34));
            JPanel homePagePnl = new JPanel();
            JPanel headerPnl = new JPanel();

            headerPnl.setLayout(new FlowLayout());
            headerPnl.add(header);
            mainFrame.add(headerPnl, BorderLayout.NORTH);

            homePagePnl.setLayout(new GridLayout(3, 2));

            homePagePnl.add(addExpanseBtn);
            homePagePnl.add(addCategoryBtn);
            homePagePnl.add(deleteExpenseBtn);
            homePagePnl.add(getPieChartBtn);
            homePagePnl.add(getCategorySummaryBtn);
            homePagePnl.add(getListSummaryBtn);

            mainFrame.add(homePagePnl, BorderLayout.CENTER);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(700, 700);
            mainFrame.setVisible(true);
        }

        /**
         * This method asks for input from the user and creates a new expense
         */

        public void addExpense() {
            message.setText("");
            class JTextFieldLimit extends PlainDocument {
                private int limit;

                JTextFieldLimit(int limit) {
                    super();
                    this.limit = limit;
                }

                JTextFieldLimit(int limit, boolean upper) {
                    super();
                    this.limit = limit;
                }

                public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                    if (str == null)
                        return;
                    if ((getLength() + str.length()) <= limit) {
                        super.insertString(offset, str, attr);
                    }
                }
            }

            dateField = new JTextField();
            JTextField currencyField = new JTextField();
            amountField = new JTextField();
            newCategoryField = new JTextField();

            frame = new JFrame("Add Expense");
            frame.setSize(600, 600);
            frame.setResizable(false);

            dateField = new JTextField(20);
            amountField = new JTextField(20);

            descriptionField = new JTextArea(1, 10);
            descriptionField.setLineWrap(true);

            descriptionField.setDocument(new JTextFieldLimit(80));

            dateField.setFont(new Font("Calibri", Font.PLAIN, 16));
            amountField.setFont(new Font("Calibri", Font.PLAIN, 16));
            descriptionField.setFont(new Font("Calibri", Font.PLAIN, 16));

            dateField.setMaximumSize(new Dimension(200, 30));
            amountField.setMaximumSize(new Dimension(200, 30));
            descriptionField.setMaximumSize(new Dimension(200, 80));

            String[] currenciesStrings = {"USD", "ILS", "GBP", "EURO"};

            categories = new JComboBox();
            categories.setFont(new Font("Calibri", Font.PLAIN, 16));
            categories.setMaximumSize(new Dimension(200, 30));

            currienciesCmbo = new JComboBox(currenciesStrings);
            currienciesCmbo.setFont(new Font("Calibri", Font.PLAIN, 16));
            currienciesCmbo.setMaximumSize(new Dimension(200, 30));

            JPanel addExpensePnl = new JPanel();
            BoxLayout boxlayout = new BoxLayout(addExpensePnl, BoxLayout.Y_AXIS);
            addExpensePnl.setLayout(boxlayout);

            JLabel dateLbl = new JLabel("date YYYY-MM-DD");
            JLabel categoryLbl = new JLabel("category");
            JLabel currencyLbl = new JLabel("currency");
            JLabel amountLbl = new JLabel("amount");
            JLabel descriptionLbl = new JLabel("description");

            dateLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoryLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            currencyLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            amountLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            descriptionLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

            dateLbl.setFont(new Font("Calibri", Font.BOLD, 25));
            categoryLbl.setFont(new Font("Calibri", Font.BOLD, 25));
            currencyLbl.setFont(new Font("Calibri", Font.BOLD, 25));
            amountLbl.setFont(new Font("Calibri", Font.BOLD, 25));
            descriptionLbl.setFont(new Font("Calibri", Font.BOLD, 25));

            submitBtn = new JButton("Submit");

            submitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    message.setText("");
                    Date date = null;
                    String description = null;
                    try {
                        description = descriptionField.getText();
                        if (description == null || description.length() == 0) {
                            throw new CostManagerException("Description cannot be empty");
                        }

                        double sum = Double.parseDouble(amountField.getText());
                        if (sum < 0)
                            throw new CostManagerException("Amount must be positive number");


                        if (dateField.getText().length() != 10) {
                            throw new CostManagerException("Invalid date");
                        }

                        String[] splitDate = dateField.getText().split("[-/.]"); // [yyyy, mm, dd]

                        int day = 0;
                        int month = 0;
                        try {
                            month = Integer.parseInt(splitDate[1]);
                            day = Integer.parseInt(splitDate[2]);
                        } catch (NumberFormatException ex) {
                            throw new CostManagerException("date must be Numeric");
                        }

                        if (day > 31 || day < 1 || month > 12 || month < 1) {
                            throw new CostManagerException("Invalid date");
                        }

                        date = Date.valueOf(dateField.getText());

                        CostItem item = null;
                        try {
                            item = new CostItem(date, (String) categories.getSelectedItem(),
                                    (String) currienciesCmbo.getSelectedItem(), sum, description);
                        } catch (CostManagerException costManagerException) {
                            ui.showMessage(costManagerException.getMessage());
                        }
                        vm.addCostItem(item);

                    } catch (NumberFormatException ex) {
                        ui.showMessage("Illegal amount entered");
                    } catch (CostManagerException ex) {
                        ui.showMessage("problem with entered data: " + ex.getMessage());
                    }
                }
            });

            submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            submitBtn.setFont(new Font("Calibri", Font.BOLD, 25));

            addExpensePnl.add(dateLbl);
            addExpensePnl.add(dateField);

            addExpensePnl.add(categoryLbl);
            addExpensePnl.add(categories);

            addExpensePnl.add(currencyLbl);
            addExpensePnl.add(currienciesCmbo);

            addExpensePnl.add(amountLbl);
            addExpensePnl.add(amountField);

            addExpensePnl.add(descriptionLbl);
            addExpensePnl.add(descriptionField);

            addExpensePnl.add(submitBtn);

            messagePnl = new JPanel();
            messagePnl.setLayout(new FlowLayout());
            messagePnl.add(labelMsg);
            messagePnl.add(message);

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backToHomePage, BorderLayout.WEST);

            frame.add(addExpensePnl, BorderLayout.CENTER);
            frame.add(messagePnl, BorderLayout.SOUTH);
            frame.add(backHomePnl, BorderLayout.NORTH);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.pack();
            frame.setVisible(true);
        }

        /**
         * This method is used to delete an expense from the DB
         */
        public void deleteExpense() {
            message.setText("");
            frame = new JFrame("Delete Expense");

            frame.setSize(700, 700);
            frame.setResizable(false);

            dataPnl = new JPanel();
            BoxLayout boxlayout = new BoxLayout(dataPnl, BoxLayout.Y_AXIS);
            dataPnl.setLayout(boxlayout);

            JLabel getIdLbl = new JLabel("Enter id:");
            getIdLbl.setFont(new Font("Calibri", Font.BOLD, 16));
            JButton deleteBtn = new JButton("Delete");
            deleteBtn.setFont(new Font("Calibri", Font.BOLD, 16));

            deleteBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int idToDelete = Integer.parseInt(toDelete.getText());
                        if (idToDelete < 0) {
                            throw new CostManagerException("id can NOT be negative!");
                        } else {
                            vm.deleteCostItemsById(idToDelete);
                        }

                    } catch (CostManagerException ex) {
                        View.this.showMessage(ex.getMessage());
                    } catch (NumberFormatException ex) {
                        ui.showMessage(ex.getMessage());
                    }

                    dataPnl.remove(sp);
                    vm.getCostItems(new ArrayList<String>());
                }
            });

            toDelete = new JTextField(20);
            toDelete.setFont(new Font("Calibri", Font.PLAIN, 16));

            JPanel actionPnl = new JPanel();
            actionPnl.setLayout(new FlowLayout());
            actionPnl.add(getIdLbl);
            actionPnl.add(toDelete);
            actionPnl.add(deleteBtn);

            dataPnl.add(actionPnl);

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backToHomePage, BorderLayout.WEST);

            messagePnl = new JPanel();
            messagePnl.setLayout(new FlowLayout());
            messagePnl.add(labelMsg);
            messagePnl.add(message);

            frame.add(backHomePnl, BorderLayout.NORTH);
            frame.add(dataPnl, BorderLayout.CENTER);
            frame.add(messagePnl, BorderLayout.SOUTH);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.setVisible(true);
        }

        /**
         * This method asks for dates from the user in which he wants to get the summary
         * false where display by dates, true when called from displayPieChart
         */
        public void getDatesForSummary(boolean isChart) {
            message.setText("");
            frame = new JFrame("Enter Dates");
            frame.setSize(400, 400);
            frame.setResizable(false);

            JPanel getDatesPnl = new JPanel();
            BoxLayout boxlayout = new BoxLayout(getDatesPnl, BoxLayout.Y_AXIS);
            getDatesPnl.setLayout(boxlayout);

            JLabel fromDateLbl = new JLabel("From date");
            JLabel toDateLbl = new JLabel("To Date");

            fromField = new JTextField(20);
            toField = new JTextField(20);

            fromField.setFont(new Font("Calibri", Font.PLAIN, 16));
            toField.setFont(new Font("Calibri", Font.PLAIN, 16));

            fromField.setMaximumSize(new Dimension(250, 30));
            toField.setMaximumSize(new Dimension(250, 30));

            fromDateLbl.setFont(new Font("Calibri", Font.BOLD, 20));
            toDateLbl.setFont(new Font("Calibri", Font.BOLD, 20));

            submitBtn = new JButton("Submit");
            submitBtn.setFont(new Font("Calibri", Font.PLAIN, 16));

            fromDateLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            toDateLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            fromField.setAlignmentX(Component.CENTER_ALIGNMENT);
            toField.setAlignmentX(Component.CENTER_ALIGNMENT);
            submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            getDatesPnl.add(fromDateLbl);
            getDatesPnl.add(fromField);
            getDatesPnl.add(toDateLbl);
            getDatesPnl.add(toField);
            getDatesPnl.add(submitBtn);

            submitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String fromStr = null;
                    String toStr = null;
                    boolean valid = true;

                    try {
                        if (fromField.getText().length() == 0 || toField.getText().length() == 0) {
                            throw new CostManagerException("One or more of the two dates is missing!");
                        }

                        fromStr = fromField.getText();
                        toStr = toField.getText();

                        if (fromStr.length() != 10 || toStr.length() != 10) {
                            throw new CostManagerException("one or more date is invalid");
                        }

                        String[] splitDateTo = toStr.split("[-/.]"); // [yyyy, mm, dd]
                        String[] splitDateFrom = fromStr.split("[-/.]"); // [yyyy, mm, dd]
                        int monthTo = 0;
                        int dayTo = 0;
                        int monthFrom = 0;
                        int dayFrom = 0;

                        try {
                            monthTo = Integer.parseInt(splitDateTo[1]);
                            dayTo = Integer.parseInt(splitDateTo[2]);

                            monthFrom = Integer.parseInt(splitDateFrom[1]);
                            dayFrom = Integer.parseInt(splitDateFrom[2]);
                        } catch (NumberFormatException ex) {
                            throw new CostManagerException("date must be Numeric");
                        }

                        if (dayTo > 31 || dayTo < 1 || monthTo > 12 || monthTo < 1) {
                            throw new CostManagerException("Invalid date");
                        }

                        if (dayFrom > 31 || dayFrom < 1 || monthFrom > 12 || monthFrom < 1) {
                            throw new CostManagerException("Invalid date");
                        }

                        if (toStr.compareTo(fromStr) < 0) {
                            throw new CostManagerException("Dates are incorrect!");
                        }

                    } catch (CostManagerException ex) {
                        valid = false;
                        View.this.showMessage(ex.getMessage());
                    }

                    if (valid) {
                        frame.dispose();
                        ArrayList<String> query = new ArrayList<>();
                        query.add(fromStr);
                        query.add(toStr);

                        if (isChart) {
                            vm.getPieChartData(query);
                        } else {
                            ApplicationUI.this.generateDatesSummary();
                            vm.getCostItems(query);
                        }
                    }
                }
            });

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backToHomePage, BorderLayout.WEST);

            messagePnl = new JPanel();
            messagePnl.setLayout(new FlowLayout());
            messagePnl.add(labelMsg);
            messagePnl.add(message);

            frame.add(backHomePnl, BorderLayout.NORTH);
            frame.add(getDatesPnl, BorderLayout.CENTER);
            frame.add(messagePnl, BorderLayout.SOUTH);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.setVisible(true);
        }

        /**
         * This function creates summary according to the user request (by dates / by category)
         */
        public void generateCategorySummary() {
            message.setText("");
            frame = new JFrame();
            frame.setTitle("Category summary");

            frame.setSize(700, 600);
            frame.setVisible(true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            dataPnl = new JPanel();
            BoxLayout boxlayout = new BoxLayout(dataPnl, BoxLayout.Y_AXIS);
            dataPnl.setLayout(boxlayout);

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backToHomePage, BorderLayout.WEST);

            frame.add(backHomePnl, BorderLayout.NORTH);
            frame.add(dataPnl, BorderLayout.CENTER);
            frame.setResizable(false);
        }

        /**
         * This function creates summary according to the user request (by dates / by category)
         */
        public void generateDatesSummary() {
            message.setText("");
            frame = new JFrame();
            frame.setTitle("Date summary");

            frame.setSize(700, 600);
            frame.setVisible(true);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            dataPnl = new JPanel();
            BoxLayout boxlayout = new BoxLayout(dataPnl, BoxLayout.Y_AXIS);
            dataPnl.setLayout(boxlayout);

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backToHomePage, BorderLayout.WEST);

            frame.add(backHomePnl, BorderLayout.NORTH);
            frame.add(dataPnl, BorderLayout.CENTER);
            frame.add(messagePnl, BorderLayout.SOUTH);
            frame.setResizable(false);
        }

        /**
         * This function creates window that ask the user to enter the category he wants to get summary by.
         */
        public void getCategoryForSummary() {

            message.setText("");
            frame = new JFrame("Get Category");
            frame.setSize(400, 400);
            frame.setResizable(false);

            JLabel chooseCategoryLbl = new JLabel("Choose category");

            categories = new JComboBox();
            categories.setFont(new Font("Calibri", Font.PLAIN, 16));

            categories.setMaximumSize(new Dimension(250, 30));

            chooseCategoryLbl.setFont(new Font("Calibri", Font.BOLD, 20));

            submitBtn = new JButton("Submit");
            submitBtn.setFont(new Font("Calibri", Font.PLAIN, 16));
            submitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ApplicationUI.this.frame.dispose();
                    ApplicationUI.this.generateCategorySummary();
                    String category = "category-" + (String) categories.getSelectedItem();
                    ArrayList<String> tmp = new ArrayList<String>();
                    tmp.add(category);
                    vm.getCostItems(tmp);
                }
            });

            chooseCategoryLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            categories.setAlignmentX(Component.CENTER_ALIGNMENT);
            submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel getCategoryPnl = new JPanel();
            getCategoryPnl.add(chooseCategoryLbl);
            getCategoryPnl.add(categories);
            getCategoryPnl.add(submitBtn);

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backToHomePage, BorderLayout.WEST);

            frame.add(backHomePnl, BorderLayout.NORTH);
            frame.add(getCategoryPnl, BorderLayout.CENTER);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.setVisible(true);
        }

        /**
         * This function creates window that ask the user to add new category
         */
        public void addCategory() {
            message.setText("");
            frame = new JFrame("Add Category");

            frame.setSize(400, 400);
            frame.setResizable(false);

            JLabel enterCategoryLbl = new JLabel("Enter new category");

            newCategoryField = new JTextField(20);

            newCategoryField.setFont(new Font("Calibri", Font.PLAIN, 16));
            newCategoryField.setMaximumSize(new Dimension(250, 30));

            enterCategoryLbl.setFont(new Font("Calibri", Font.BOLD, 20));

            submitBtn = new JButton("Submit");
            submitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    message.setText("");
                    vm.addCategory(new Category(newCategoryField.getText()));
                }
            });

            submitBtn.setFont(new Font("Calibri", Font.PLAIN, 16));

            enterCategoryLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            newCategoryField.setAlignmentX(Component.CENTER_ALIGNMENT);
            submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel addCategoryPnl = new JPanel();
            addCategoryPnl.add(enterCategoryLbl);
            addCategoryPnl.add(newCategoryField);
            addCategoryPnl.add(submitBtn);

            messagePnl = new JPanel();
            messagePnl.setLayout(new FlowLayout());
            messagePnl.add(labelMsg);
            messagePnl.add(message);

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backToHomePage, BorderLayout.WEST);

            frame.add(backHomePnl, BorderLayout.NORTH);
            frame.add(addCategoryPnl, BorderLayout.CENTER);
            frame.add(messagePnl, BorderLayout.SOUTH);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.setVisible(true);
        }

        /**
         * This function gets a map <String, Double> between category and costs
         */
        public void displayPieChart(Map map) {

            DefaultPieDataset dataset = new DefaultPieDataset();

            for (Object key : map.keySet()) {
                dataset.setValue((String) key, (Double) map.get(key));
            }

            JFreeChart chart = ChartFactory.createPieChart("pie chart", dataset, true, true, false);

            ChartFrame pieFrame = new ChartFrame("pie chart", chart);

            pieFrame.setSize(600, 600);
            JButton backBtn = new JButton("Back Home");
            backBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pieFrame.dispose();
                    ApplicationUI.this.mainFrame.setVisible(true);
                }
            });

            backHomePnl = new JPanel();
            backHomePnl.setLayout(new BorderLayout());
            backHomePnl.add(backBtn, BorderLayout.WEST);

            pieFrame.add(backHomePnl, BorderLayout.SOUTH);
            pieFrame.setVisible(true);
        }

        /**
         * This function displays the feedback messages at the view from the model
         */
        public void showMessage(String text) {
            if (SwingUtilities.isEventDispatchThread()) {
                message.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        message.setText(text);
                    }
                });
            }
        }

        /**
         * This function creates the table deplaying the retrived data in a table
         */
        public void generateTable(CostItem[] items) {

            String[][] data = new String[items.length][6];

            for (int i = 0; i < items.length; i++) {
                data[i][0] = String.valueOf(items[i].getId());
                data[i][1] = items[i].getDate().toString();
                data[i][2] = items[i].getCategory();
                data[i][3] = items[i].getCurrency();
                data[i][4] = String.valueOf(items[i].getSum());
                data[i][5] = items[i].getDescription();
            }

            String[] columnNames = {"Id", "Date", "Category", "Currency", "Amount", "Description"};

            JTable costsTable = new JTable(data, columnNames);
            costsTable.getColumnModel().getColumn(0).setPreferredWidth(160);
            costsTable.getColumnModel().getColumn(1).setPreferredWidth(160);
            costsTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            costsTable.getColumnModel().getColumn(3).setPreferredWidth(160);
            costsTable.getColumnModel().getColumn(4).setPreferredWidth(160);
            costsTable.getColumnModel().getColumn(5).setPreferredWidth(720);

            sp = new JScrollPane(costsTable);
            sp.setSize(700, 400);
            costsTable.setEnabled(false);

            dataPnl.add(sp);

            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            frame.validate();
            frame.repaint();
        }

        /**
         * This function displays the rerived data in a list
         */
        public void showItems(CostItem[] items) {
            if (SwingUtilities.isEventDispatchThread()) {
                this.generateTable(items);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ApplicationUI.this.generateTable(items);
                    }
                });
            }
        }

        /**
         * This function displays the cateogries retrived from the Data base
         */
        public void showCategories(List<Category> categories) {
            for (Category cat : categories) {
                this.categories.addItem(cat.toString());
            }

        }

        /**
         * This gets the cateogires in the ui
         */
        public void setCategories(List<Category> categories) {
            if (SwingUtilities.isEventDispatchThread()) {
                this.showCategories(categories);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ApplicationUI.this.showCategories(categories);
                    }
                });
            }
        }
    }
}