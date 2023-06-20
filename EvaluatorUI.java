import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EvaluatorUI extends JFrame implements ActionListener {
  private TextField txField = new TextField();
  private Panel buttonPanel = new Panel();
  private Evaluator evaluator = new Evaluator();
  private static String equation = "";
  private static String displayEquation = "";
  private static final String[] bText = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", "^", "=",
      "/", "(", ")", "C", "CE" };
  private boolean lastItemWasOperator = false;
  private boolean needToCloseParen = false;
  private Button[] buttons = new Button[bText.length];

  @SuppressWarnings("unused")
  public static void main(String argv[]) {
    EvaluatorUI calc = new EvaluatorUI();
  }

  public EvaluatorUI() {
    setLayout(new BorderLayout());

    txField = new TextField();
    txField.setEditable(false);
    txField.setFont(new Font("SansSerif", Font.BOLD, 24)); // Increase the font size
    txField.setBackground(Color.WHITE); // Set the background color
    add(txField, BorderLayout.NORTH);

    buttonPanel = new Panel();
    buttonPanel.setLayout(new GridLayout(5, 4));
    add(buttonPanel, BorderLayout.CENTER);

    Color numberButtonColor = new Color(200, 200, 200); // Light gray
    Color operatorButtonColor = new Color(150, 150, 150); // Dark gray
    Color otherButtonColor = new Color(100, 100, 100); // Darker gray

    for (int i = 0; i < bText.length; i++) {
      buttons[i] = new Button(bText[i]);
      buttons[i].setFont(new Font("SansSerif", Font.BOLD, 20)); // Increase the font size

      if (Character.isDigit(bText[i].charAt(0))) {
        buttons[i].setBackground(numberButtonColor);
      } else if ("+".equals(bText[i]) || "-".equals(bText[i]) || "*".equals(bText[i]) || "/".equals(bText[i])) {
        buttons[i].setBackground(operatorButtonColor);
      } else {
        buttons[i].setBackground(otherButtonColor);
      }

      buttonPanel.add(buttons[i]);
      buttons[i].addActionListener(this);
    }

    setTitle("Calculator");
    setSize(400, 400);
    setLocationByPlatform(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent arg0) {
    String buttonClick = arg0.getActionCommand();

    if (buttonClick.equals("=")) {
      handleEquals();
    } else if (buttonClick.equals("C") || buttonClick.equals("CE")) {
      clear();
    } else if (buttonClick.equals("-")) {
      handleNegative(buttonClick);
    } else if (buttonClick.equals("(")) {
      handleOpenParenthesis(buttonClick);
    } else if (buttonClick.equals(")")) {
      handleCloseParenthesis(buttonClick);
    } else {
      handleNumberOrOperator(buttonClick);
    }

    txField.setText(displayEquation);
  }

  private void addButtonActionListeners() {
    for (Button button : buttons) {
      button.addActionListener(this);
    }
  }

  private void handleEquals() {
    int result = evaluator.eval(equation);
    equation = String.valueOf(result);
    displayEquation = displayEquation.concat(" = " + String.valueOf(result) + "   Press C or CE.");
  }

  private void clear() {
    equation = "";
    displayEquation = "";
    lastItemWasOperator = false;
  }

  private void handleNegative(String buttonClick) {
    if (equation.length() == 0) {
      displayEquation = "(-";
      equation = "(0-";
      needToCloseParen = true;
    } else if (lastItemWasOperator) {
      displayEquation = displayEquation.concat("(-");
      equation = equation.concat("(0-");
      needToCloseParen = true;
    } else {
      displayEquation = displayEquation.concat(buttonClick);
      equation = equation.concat(buttonClick);
    }
    lastItemWasOperator = true;
  }

  private void handleOpenParenthesis(String buttonClick) {
    if (equation.length() != 0) {
      displayEquation = displayEquation.concat(buttonClick);
      equation = equation.concat(buttonClick);
    }
    lastItemWasOperator = true;
  }

  private void handleCloseParenthesis(String buttonClick) {
    if (needToCloseParen) {
      displayEquation = displayEquation.concat(")");
      equation = equation.concat(")");
      needToCloseParen = false;
    }
    displayEquation = displayEquation.concat(buttonClick);
    equation = equation.concat(buttonClick);
    lastItemWasOperator = false;
  }

  private void handleNumberOrOperator(String buttonClick) {
    displayEquation = displayEquation.concat(buttonClick);
    equation = equation.concat(buttonClick);
    lastItemWasOperator = false;
  }
}
