import java.util.*;
import java.lang.Math;

public abstract class Operator {
  abstract int priority();

  public static HashMap<String, Operator> operators = new HashMap<String, Operator>();
  static {
    operators.put("-", new SubtractOperator());
    operators.put("+", new AddOperator());
    operators.put("/", new DivideOperator());
    operators.put("*", new MultiplyOperator());
    operators.put("^", new ExponentOperator());
    operators.put("(", new LeftParenthesis());
    operators.put(")", new RightParenthesis());
  }

  public abstract Operand execute(Operand op1, Operand op2);

  public static boolean check(String token) {
    for (String key : operators.keySet()) {
      if (key.equals(token)) {
        return true;
      }
    }
    return false;
  }
}

class SubtractOperator extends Operator {
  public int priority() {
    return 2;
  }

  public Operand execute() {
    return null;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    int number = op1.getValue() - op2.getValue();
    return new Operand(number);
  }
}

class AddOperator extends Operator {
  public int priority() {
    return 2;
  }

  public Operand execute() {
    return null;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    int number = op2.getValue() + op1.getValue();
    return new Operand(number);
  }
}

class DivideOperator extends Operator {

  public int priority() {
    return 3;
  }

  public Operand execute() {
    return null;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    try {
      int number = op1.getValue() / op2.getValue();
      return new Operand(number);
    } catch (ArithmeticException e) {
      System.exit(1);
      return new Operand(0);
    }
  }
}

class MultiplyOperator extends Operator {

  public int priority() {
    return 3;
  }

  public Operand execute() {
    return null;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    int number = op2.getValue() * op1.getValue();
    return new Operand(number);
  }
}

class ExponentOperator extends Operator {

  public int priority() {
    return 4;
  }

  public Operand execute() {
    return null;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    int number = (int) Math.pow(op1.getValue(), op2.getValue());
    return new Operand(number);
  }
}

class LeftParenthesis extends Operator {

  public int priority() {
    return 0;
  }

  public Operand execute() {
    return null;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    return null;
  }
}

class RightParenthesis extends Operator {

  public int priority() {
    return 5;
  }

  public Operand execute() {
    return null;
  }

  @Override
  public Operand execute(Operand op1, Operand op2) {
    return null;
  }
}
