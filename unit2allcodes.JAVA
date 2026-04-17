// ============================================================
//  UNIT 3 — EXCEPTION HANDLING
//  Covers: Need for exception handling, try-catch, finally,
//          throw, throws, User-Defined Exceptions,
//          Checked vs Unchecked exceptions, Exception hierarchy
// ============================================================


import java.io.*;

// ============================================================
// WHY EXCEPTION HANDLING?
// Without it, a runtime error crashes the program abruptly.
// Exception handling lets us detect errors gracefully and
// respond without terminating the entire application.
// ============================================================

// ============================================================
// USER-DEFINED EXCEPTIONS
// Extend Exception (checked) or RuntimeException (unchecked)
// ============================================================

// Checked User-Defined Exception (must be declared with throws)
class InsufficientFundsException extends Exception {
    private double amount;

    public InsufficientFundsException(double amount) {
        super("Insufficient funds! Shortfall: ₹" + amount);
        this.amount = amount;
    }

    public double getAmount() { return amount; }
}

// Unchecked User-Defined Exception (no throws needed)
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

// Another custom exception
class NegativeNumberException extends Exception {
    public NegativeNumberException(int num) {
        super("Negative number not allowed: " + num);
    }
}

// ============================================================
// DEMO CLASSES THAT THROW EXCEPTIONS
// ============================================================
class Bank {
    private double balance;

    public Bank(double balance) { this.balance = balance; }

    // 'throws' declares that this method might throw a checked exception
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            // 'throw' actually throws the exception
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("✔ Withdrew ₹" + amount + " | Remaining: ₹" + balance);
    }

    public double getBalance() { return balance; }
}

class AgeValidator {
    // Throws unchecked exception — no throws declaration needed
    public static void validate(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Age " + age + " is out of valid range [0–150]");
        }
        System.out.println("✔ Valid age: " + age);
    }
}

// ============================================================
// MAIN DEMO CLASS
// ============================================================
public class Unit3_ExceptionHandling {

    // ---------------------------------------------------------
    // 1. BASIC try-catch
    // ---------------------------------------------------------
    static void basicTryCatch() {
        System.out.println("\n--- 1. BASIC try-catch ---");
        try {
            int result = 10 / 0;           // ArithmeticException
            System.out.println(result);    // Never reached
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------
    // 2. MULTIPLE catch blocks
    // ---------------------------------------------------------
    static void multipleCatch() {
        System.out.println("\n--- 2. MULTIPLE catch Blocks ---");
        String[] arr = {"10", null, "abc"};

        for (String s : arr) {
            try {
                int val = Integer.parseInt(s);    // Can throw NumberFormatException or NPE
                System.out.println("Parsed: " + val);
            } catch (NullPointerException e) {
                System.out.println("Caught NullPointerException: element is null");
            } catch (NumberFormatException e) {
                System.out.println("Caught NumberFormatException: '" + s + "' is not a number");
            }
        }
    }

    // ---------------------------------------------------------
    // 3. finally block — always executes (cleanup code)
    // ---------------------------------------------------------
    static void finallyBlock() {
        System.out.println("\n--- 3. finally Block ---");
        System.out.println("(finally ALWAYS runs, even if exception occurs)");

        // Case A: exception occurs
        try {
            System.out.println("Attempting risky operation...");
            int[] arr = new int[3];
            arr[10] = 5;                    // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        } finally {
            System.out.println("Finally block A: cleanup resources here");
        }

        // Case B: no exception
        try {
            System.out.println("Safe operation...");
            int x = 5 + 5;
        } catch (Exception e) {
            System.out.println("Won't run");
        } finally {
            System.out.println("Finally block B: still runs!");
        }
    }

    // ---------------------------------------------------------
    // 4. throw — manually throwing an exception
    // ---------------------------------------------------------
    static void throwDemo() {
        System.out.println("\n--- 4. throw Keyword ---");
        try {
            AgeValidator.validate(25);        // valid
            AgeValidator.validate(-5);        // throws InvalidAgeException
        } catch (InvalidAgeException e) {
            System.out.println("Caught InvalidAgeException: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------
    // 5. throws — declaring that a method may throw an exception
    // ---------------------------------------------------------
    static void throwsDemo() throws NegativeNumberException {
        System.out.println("\n--- 5. throws Keyword ---");
        int num = -3;
        if (num < 0) {
            throw new NegativeNumberException(num);
        }
        System.out.println("Number is valid: " + num);
    }

    // ---------------------------------------------------------
    // 6. User-Defined Exception — InsufficientFundsException
    // ---------------------------------------------------------
    static void userDefinedException() {
        System.out.println("\n--- 6. User-Defined Exception ---");
        Bank account = new Bank(1000.0);

        try {
            account.withdraw(500);           // OK
            account.withdraw(700);           // throws InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println("Shortfall amount: ₹" + e.getAmount());
        }
    }

    // ---------------------------------------------------------
    // 7. Nested try-catch
    // ---------------------------------------------------------
    static void nestedTryCatch() {
        System.out.println("\n--- 7. Nested try-catch ---");
        try {
            System.out.println("Outer try: start");
            try {
                System.out.println("Inner try: dividing...");
                int x = 1 / 0;              // Inner exception
            } catch (ArithmeticException e) {
                System.out.println("Inner catch: " + e.getMessage());
                throw new RuntimeException("Re-thrown from inner"); // propagate
            }
        } catch (RuntimeException e) {
            System.out.println("Outer catch: " + e.getMessage());
        } finally {
            System.out.println("Outer finally");
        }
    }

    // ---------------------------------------------------------
    // 8. Multi-catch (Java 7+) — catch multiple types in one block
    // ---------------------------------------------------------
    static void multiCatch() {
        System.out.println("\n--- 8. Multi-catch (Java 7+) ---");
        try {
            String s = null;
            s.length();                      // NullPointerException
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Multi-catch caught: " + e.getClass().getSimpleName());
        }
    }

    // ---------------------------------------------------------
    // 9. Exception Hierarchy overview
    // ---------------------------------------------------------
    static void exceptionHierarchy() {
        System.out.println("\n--- 9. EXCEPTION HIERARCHY ---");
        System.out.println(
            "Throwable\n" +
            "├── Error (serious, don't catch)\n" +
            "│   ├── OutOfMemoryError\n" +
            "│   ├── StackOverflowError\n" +
            "│   └── VirtualMachineError\n" +
            "└── Exception\n" +
            "    ├── Checked (must handle or declare)\n" +
            "    │   ├── IOException\n" +
            "    │   ├── SQLException\n" +
            "    │   └── InsufficientFundsException (user-defined)\n" +
            "    └── RuntimeException (Unchecked)\n" +
            "        ├── ArithmeticException\n" +
            "        ├── NullPointerException\n" +
            "        ├── ArrayIndexOutOfBoundsException\n" +
            "        ├── NumberFormatException\n" +
            "        └── InvalidAgeException (user-defined)"
        );
    }

    // ---------------------------------------------------------
    // 10. Common Built-in Exceptions Demo
    // ---------------------------------------------------------
    static void builtInExceptions() {
        System.out.println("\n--- 10. COMMON BUILT-IN EXCEPTIONS ---");

        // ArithmeticException
        try { int r = 5 / 0; }
        catch (ArithmeticException e) { System.out.println("ArithmeticException: " + e.getMessage()); }

        // NullPointerException
        try { String s = null; s.length(); }
        catch (NullPointerException e) { System.out.println("NullPointerException caught"); }

        // ArrayIndexOutOfBoundsException
        try { int[] a = new int[3]; int x = a[5]; }
        catch (ArrayIndexOutOfBoundsException e) { System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage()); }

        // NumberFormatException
        try { int n = Integer.parseInt("abc"); }
        catch (NumberFormatException e) { System.out.println("NumberFormatException: " + e.getMessage()); }

        // ClassCastException
        try { Object o = "hello"; Integer i = (Integer) o; }
        catch (ClassCastException e) { System.out.println("ClassCastException caught"); }

        // StackOverflowError
        try { methodThatRecurses(); }
        catch (StackOverflowError e) { System.out.println("StackOverflowError caught"); }
    }

    static void methodThatRecurses() { methodThatRecurses(); } // infinite recursion

    // ---- MAIN ----
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║      UNIT 3: EXCEPTION HANDLING              ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        basicTryCatch();
        multipleCatch();
        finallyBlock();
        throwDemo();

        // throwsDemo() throws checked exception — must catch here
        try {
            throwsDemo();
        } catch (NegativeNumberException e) {
            System.out.println("Caught in main: " + e.getMessage());
        }

        userDefinedException();
        nestedTryCatch();
        multiCatch();
        exceptionHierarchy();
        builtInExceptions();

        System.out.println("\n============================");
        System.out.println(" Unit 3 Complete!");
        System.out.println("============================");
    }
}
