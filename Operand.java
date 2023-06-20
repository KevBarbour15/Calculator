public class Operand {
  private int number;

  /**
   * Creates an Operand object from a string token.
   * @param token A string that should be able to be converted into an integer.
   * @throws IllegalArgumentException if the token cannot be parsed into an integer.
   */
  public Operand(String token) { 
    try {
      number = Integer.parseInt(token);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid token for Operand: " + token);
    }
  }

  /**
   * Creates an Operand object with a specified integer value.
   * @param value An integer to be stored as the value of the Operand.
   */
  public Operand(int value) {
    number = value;
  }

  /**
   * Returns the integer value of the Operand.
   * @return The integer value of the Operand.
   */
  public int getValue() {
    return number;
  }

  /**
   * Checks whether a string token can be converted into an integer.
   * @param token A string to be checked.
   * @return True if the token can be parsed into an integer, otherwise false.
   */
  public static boolean check(String token) {
    try {
      Integer.parseInt(token);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }
}
