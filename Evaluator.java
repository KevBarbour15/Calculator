import java.util.*;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;

  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/() ";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int eval(String expression) {
    String token;
    this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

    while (this.tokenizer.hasMoreTokens()) {
      if (!(token = this.tokenizer.nextToken()).equals(" ")) {
        processToken(token);
      }
    }

    return processRemainingOperators();
  }

  private void processToken(String token) {
    if (Operand.check(token)) {
      operandStack.push(new Operand(token));
    } else if (Operator.check(token)) {
      processOperator(token);
    } else {
      throw new IllegalArgumentException("Invalid token: " + token);
    }
  }

  private void processOperator(String token) {
    Operator newOperator = Operator.operators.get(token);

    while (shouldExecuteOperator(newOperator)) {
      executeTopOperator();
    }

    if (isClosingParenthesis(newOperator)) {
      executeUntilOpeningParenthesis();
    } else {
      operatorStack.push(newOperator);
    }
  }

  private boolean shouldExecuteOperator(Operator newOperator) {
    return !operatorStack.isEmpty() && newOperator.priority() != 0 && operatorStack.peek().priority() >= newOperator.priority();
  }

  private void executeTopOperator() {
    Operator oldOpr = operatorStack.pop();
    Operand op2 = operandStack.pop();
    Operand op1 = operandStack.pop();
    operandStack.push(oldOpr.execute(op1, op2));
  }

  private boolean isClosingParenthesis(Operator operator) {
    return operator.priority() == 5;
  }

  private void executeUntilOpeningParenthesis() {
    while (!operatorStack.isEmpty() && operatorStack.peek().priority() != 0) {
      executeTopOperator();
    }
    operatorStack.pop();
  }

  private int processRemainingOperators() {
    while (operandStack.size() > 1) {
      executeTopOperator();
    }
    return operandStack.pop().getValue();
  }
}
