// ============================================================
//  UNIT 1 & 2 — OOP CONCEPTS + INTRODUCTION TO JAVA
//  Covers: Classes, Objects, Abstraction, Inheritance,
//  Encapsulation, Polymorphism, Constructors, this keyword,
//  Method Overloading/Overriding, Static/Instance vars & methods,
//  Abstract Classes, Interfaces, Packages, Arrays,
//  String Class, Wrapper Classes, Inner Classes,
//  Garbage Collector, Parameter Passing
// ============================================================


import java.util.*;

// ============================================================
// SECTION 1: ENCAPSULATION
// Wrapping data (fields) and methods into a single unit (class)
// and restricting direct access via access modifiers.
// ============================================================
class BankAccount {
    // private fields — hidden from outside (Encapsulation)
    private String owner;
    private double balance;

    // Public constructor
    public BankAccount(String owner, double initialBalance) {
        this.owner = owner;          // 'this' keyword — refers to current object
        this.balance = initialBalance;
    }

    // Getter (accessor)
    public String getOwner()   { return owner; }
    public double getBalance() { return balance; }

    // Setter (mutator) — controlled access
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account[" + owner + ", Balance=" + balance + "]";
    }
}

// ============================================================
// SECTION 2: ABSTRACTION
// Hiding implementation details; showing only essential features.
// Achieved via abstract classes and interfaces.
// ============================================================
abstract class Shape {                    // ABSTRACT CLASS
    protected String color;

    public Shape(String color) { this.color = color; }

    // Abstract method — no body, must be overridden
    public abstract double area();
    public abstract double perimeter();

    // Concrete method
    public void displayInfo() {
        System.out.println("Shape: " + getClass().getSimpleName()
                + " | Color: " + color
                + " | Area: " + String.format("%.2f", area())
                + " | Perimeter: " + String.format("%.2f", perimeter()));
    }
}

// ============================================================
// SECTION 3: INHERITANCE
// Child class acquires properties/methods of parent class.
// Java supports single and multilevel inheritance.
// ============================================================
class Circle extends Shape {             // Single Inheritance
    private double radius;

    public Circle(String color, double radius) {
        super(color);                    // super() — calls parent constructor
        this.radius = radius;
    }

    @Override
    public double area()      { return Math.PI * radius * radius; }

    @Override
    public double perimeter() { return 2 * Math.PI * radius; }
}

class Rectangle extends Shape {
    private double width, height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area()      { return width * height; }

    @Override
    public double perimeter() { return 2 * (width + height); }
}

// Multilevel Inheritance: Square → Rectangle → Shape
class Square extends Rectangle {
    public Square(String color, double side) {
        super(color, side, side);
    }
}

// ============================================================
// SECTION 4: POLYMORPHISM
// "Many forms" — same method name, different behavior.
// a) Compile-time (Method Overloading)
// b) Run-time (Method Overriding)
// ============================================================

// --- (a) METHOD OVERLOADING — Compile-time Polymorphism ---
class Calculator {
    // Same method name, different parameter lists
    public int    add(int a, int b)             { return a + b; }
    public double add(double a, double b)       { return a + b; }
    public int    add(int a, int b, int c)      { return a + b + c; }
    public String add(String a, String b)       { return a + b; } // concatenation

    // Demonstrating all overloads
    public void demo() {
        System.out.println("int + int       = " + add(3, 4));
        System.out.println("double + double = " + add(3.5, 4.5));
        System.out.println("3 ints          = " + add(1, 2, 3));
        System.out.println("String concat   = " + add("Hello", " World"));
    }
}

// --- (b) METHOD OVERRIDING — Run-time Polymorphism ---
class Animal {
    public void speak() { System.out.println("Animal makes a sound"); }
}

class Dog extends Animal {
    @Override
    public void speak() { System.out.println("Dog says: Woof!"); }
}

class Cat extends Animal {
    @Override
    public void speak() { System.out.println("Cat says: Meow!"); }
}

// ============================================================
// SECTION 5: INTERFACES
// Pure abstraction — all methods abstract (before Java 8).
// A class can implement multiple interfaces (unlike inheritance).
// ============================================================
interface Drawable {
    void draw();                          // implicitly public abstract
}

interface Resizable {
    void resize(double factor);
}

// Class implementing multiple interfaces
class Canvas implements Drawable, Resizable {
    private double size;

    public Canvas(double size) { this.size = size; }

    @Override
    public void draw()                { System.out.println("Drawing canvas of size " + size); }

    @Override
    public void resize(double factor) { size *= factor; System.out.println("Resized to " + size); }
}

// ============================================================
// SECTION 6: CONSTRUCTORS + THIS KEYWORD
// ============================================================
class Student {
    private int    id;
    private String name;
    private double gpa;

    // Default Constructor
    public Student() {
        this(0, "Unknown", 0.0);   // Constructor chaining via this()
    }

    // Parameterized Constructor
    public Student(int id, String name, double gpa) {
        this.id   = id;            // 'this' disambiguates field from parameter
        this.name = name;
        this.gpa  = gpa;
    }

    // Copy Constructor
    public Student(Student other) {
        this(other.id, other.name, other.gpa);
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', gpa=" + gpa + "}";
    }
}

// ============================================================
// SECTION 7: STATIC vs INSTANCE — Variables & Methods
// ============================================================
class Counter {
    // Static variable — shared among ALL instances
    private static int totalCount = 0;

    // Instance variable — unique to each object
    private int instanceId;
    private String label;

    public Counter(String label) {
        totalCount++;                // Shared counter increments
        this.instanceId = totalCount;
        this.label = label;
    }

    // Static method — belongs to class, not object
    public static int getTotalCount() { return totalCount; }

    // Instance method — belongs to specific object
    public void display() {
        System.out.println("Counter #" + instanceId + " [" + label + "] | Total=" + totalCount);
    }
}

// ============================================================
// SECTION 8: INNER CLASSES
// ============================================================
class OuterClass {
    private int outerData = 100;

    // (a) Regular (non-static) Inner Class
    class InnerClass {
        public void show() {
            System.out.println("Inner class accessing outer data: " + outerData);
        }
    }

    // (b) Static Nested Class — does NOT need outer instance
    static class StaticNested {
        public void show() {
            System.out.println("Static nested class — no outer instance needed");
        }
    }

    // (c) Local Inner Class — defined inside a method
    public void localClassDemo() {
        class Local {
            void show() { System.out.println("Local inner class inside method"); }
        }
        new Local().show();
    }

    // (d) Anonymous Inner Class — inline implementation
    public void anonymousClassDemo() {
        Drawable d = new Drawable() {       // Anonymous class implementing interface
            @Override
            public void draw() {
                System.out.println("Anonymous inner class drawing!");
            }
        };
        d.draw();
    }
}

// ============================================================
// SECTION 9: STRING CLASS
// ============================================================
class StringDemo {
    public static void run() {
        System.out.println("\n===== STRING CLASS =====");
        String s1 = "Hello";
        String s2 = new String("World");

        // Common String methods
        System.out.println("Length    : " + s1.length());
        System.out.println("UpperCase : " + s1.toUpperCase());
        System.out.println("LowerCase : s2=" + s2.toLowerCase());
        System.out.println("Concat    : " + s1.concat(" " + s2));
        System.out.println("Substring : " + "HelloWorld".substring(5));
        System.out.println("IndexOf   : " + "HelloWorld".indexOf("World"));
        System.out.println("Replace   : " + "aabbcc".replace("b", "X"));
        System.out.println("Trim      : '" + "  spaces  ".trim() + "'");
        System.out.println("Contains  : " + "HelloWorld".contains("World"));
        System.out.println("Equals    : " + s1.equals("Hello"));
        System.out.println("CharAt(1) : " + s1.charAt(1));
        System.out.println("Split     : " + Arrays.toString("a,b,c".split(",")));
        System.out.println("valueOf   : " + String.valueOf(42));

        // String immutability — StringBuilder for mutability
        StringBuilder sb = new StringBuilder("Java");
        sb.append(" Programming");
        sb.insert(4, " is Great");
        System.out.println("StringBuilder: " + sb);

        // String pool demonstration
        String a = "test";
        String b = "test";
        String c = new String("test");
        System.out.println("a==b (pool): " + (a == b));        // true
        System.out.println("a==c (heap): " + (a == c));        // false
        System.out.println("a.equals(c): " + a.equals(c));     // true
    }
}

// ============================================================
// SECTION 10: WRAPPER CLASSES
// Wrap primitive types into objects. Enable use in Collections.
// ============================================================
class WrapperDemo {
    public static void run() {
        System.out.println("\n===== WRAPPER CLASSES =====");

        // Autoboxing — primitive to Wrapper
        int      primitiveInt = 42;
        Integer  wrapperInt   = primitiveInt;          // autoboxing
        System.out.println("Autoboxing  : " + wrapperInt);

        // Unboxing — Wrapper to primitive
        double   primDouble   = wrapperInt;             // unboxing
        System.out.println("Unboxing    : " + primDouble);

        // Useful methods
        System.out.println("MAX_VALUE   : " + Integer.MAX_VALUE);
        System.out.println("MIN_VALUE   : " + Integer.MIN_VALUE);
        System.out.println("parseInt    : " + Integer.parseInt("123"));
        System.out.println("toBinary    : " + Integer.toBinaryString(255));
        System.out.println("toHex       : " + Integer.toHexString(255));
        System.out.println("compare     : " + Integer.compare(5, 10));

        // Other wrappers
        Double  d = Double.valueOf("3.14");
        Boolean bool = Boolean.valueOf("true");
        System.out.println("Double      : " + d);
        System.out.println("Boolean     : " + bool);
        System.out.println("Character.isLetter('A'): " + Character.isLetter('A'));
        System.out.println("Character.isDigit('5') : " + Character.isDigit('5'));
    }
}

// ============================================================
// SECTION 11: ARRAYS
// ============================================================
class ArrayDemo {
    public static void run() {
        System.out.println("\n===== ARRAYS =====");

        // 1D Array
        int[] arr = {5, 3, 8, 1, 9, 2};
        System.out.println("Original : " + Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("Sorted   : " + Arrays.toString(arr));
        System.out.println("BinarySearch(8): " + Arrays.binarySearch(arr, 8));
        System.out.println("Length   : " + arr.length);

        // 2D Array (Matrix)
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("2D Array (Matrix):");
        for (int[] row : matrix) {
            System.out.println("  " + Arrays.toString(row));
        }

        // Dynamic array via ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        System.out.println("ArrayList: " + list);

        // Array copy
        int[] copy = Arrays.copyOf(arr, arr.length);
        System.out.println("Copy     : " + Arrays.toString(copy));
    }
}

// ============================================================
// SECTION 12: PARAMETER PASSING IN JAVA
// Java is ALWAYS pass-by-value.
// For primitives: value is copied.
// For objects: reference (address) is copied (not the object).
// ============================================================
class ParameterPassingDemo {
    // Primitive — original NOT modified
    static void incrementPrimitive(int x) {
        x = x + 100;
        System.out.println("Inside method, x = " + x);
    }

    // Object — object's state CAN be modified (reference is copied)
    static void modifyAccount(BankAccount acc) {
        acc.deposit(500);   // modifies the actual object
    }

    public static void run() {
        System.out.println("\n===== PARAMETER PASSING =====");
        int num = 10;
        System.out.println("Before: num = " + num);
        incrementPrimitive(num);
        System.out.println("After : num = " + num + " (unchanged — pass-by-value)");

        BankAccount acct = new BankAccount("Alice", 1000);
        System.out.println("Before: " + acct);
        modifyAccount(acct);
        System.out.println("After : " + acct + " (modified — object ref copied)");
    }
}

// ============================================================
// SECTION 13: GARBAGE COLLECTOR
// Java's automatic memory management.
// GC reclaims memory used by unreachable objects.
// ============================================================
class GarbageCollectorDemo {
    private String name;

    public GarbageCollectorDemo(String name) { this.name = name; }

    // finalize() called by GC before collecting (deprecated in Java 9+)
    @Override
    protected void finalize() throws Throwable {
        System.out.println("GC: Finalizing object: " + name);
        super.finalize();
    }

    public static void run() {
        System.out.println("\n===== GARBAGE COLLECTOR =====");
        GarbageCollectorDemo obj1 = new GarbageCollectorDemo("Object1");
        GarbageCollectorDemo obj2 = new GarbageCollectorDemo("Object2");

        obj1 = null;    // Object1 is now unreachable — eligible for GC
        obj2 = null;    // Object2 is now unreachable — eligible for GC

        System.gc();    // Suggest GC run (not guaranteed)
        System.out.println("GC requested. Objects eligible for collection.");
        System.out.println("Java manages memory automatically — no manual free()");

        // Memory info
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Memory : " + rt.totalMemory() / 1024 + " KB");
        System.out.println("Free Memory  : " + rt.freeMemory()  / 1024 + " KB");
        System.out.println("Max Memory   : " + rt.maxMemory()   / 1024 + " KB");
    }
}

// ============================================================
// SECTION 14: FEATURES OF JAVA (summary as demonstration)
// Simple, Object-Oriented, Platform-Independent (WORA),
// Robust, Secure, Multithreaded, Distributed, Dynamic
// ============================================================
class JavaFeatures {
    public static void run() {
        System.out.println("\n===== FEATURES OF JAVA =====");
        String[] features = {
            "1. Simple            — Easy syntax, no pointers",
            "2. Object-Oriented   — Everything is a class/object",
            "3. Platform-Indep.   — Write Once, Run Anywhere (JVM)",
            "4. Robust            — Strong type checking, exception handling",
            "5. Secure            — No explicit pointers, Bytecode verification",
            "6. Multithreaded     — Built-in thread support",
            "7. Distributed       — RMI, Sockets for network apps",
            "8. Dynamic           — Runtime class loading, reflection",
            "9. High Performance  — JIT compiler optimization",
            "10. Interpreted      — Bytecode interpreted by JVM"
        };
        for (String f : features) System.out.println(f);
    }
}

// ============================================================
// SECTION 15: C++ vs JAVA COMPARISON (as code comments + output)
// ============================================================
class CppVsJava {
    public static void run() {
        System.out.println("\n===== C++ vs JAVA COMPARISON =====");
        String[][] comparison = {
            {"Feature",             "C++",                      "Java"                     },
            {"Memory Mgmt",        "Manual (new/delete)",       "Automatic (GC)"           },
            {"Pointers",           "Explicit pointers",         "No explicit pointers"      },
            {"Multiple Inherit",   "Supported (classes)",       "Via interfaces only"       },
            {"Platform",           "Platform dependent",        "Platform independent (JVM)"},
            {"Compilation",        "To machine code",           "To bytecode"              },
            {"Operator Overload",  "Supported",                 "Not supported"            },
            {"Templates/Generics", "Templates",                 "Generics"                 },
            {"Header Files",       "Required (.h)",             "Not required"             },
            {"Main method",        "int main()",                "public static void main()"}
        };
        for (String[] row : comparison) {
            System.out.printf("%-20s | %-28s | %s%n", row[0], row[1], row[2]);
        }
    }
}

// ============================================================
// MAIN CLASS — runs all demos
// ============================================================
public class Unit1_2_OOP_Java {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   UNIT 1 & 2: OOP CONCEPTS + JAVA INTRO     ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        // ---- ENCAPSULATION ----
        System.out.println("\n===== ENCAPSULATION =====");
        BankAccount acc = new BankAccount("Rahul", 5000);
        acc.deposit(1500);
        acc.withdraw(800);
        System.out.println(acc);

        // ---- ABSTRACTION ----
        System.out.println("\n===== ABSTRACTION (Abstract Class) =====");
        Shape[] shapes = {
            new Circle("Red", 7),
            new Rectangle("Blue", 5, 10),
            new Square("Green", 4)
        };
        for (Shape s : shapes) s.displayInfo();

        // ---- INHERITANCE ----
        System.out.println("\n===== INHERITANCE =====");
        System.out.println("Circle is a Shape (Single Inheritance)");
        System.out.println("Square → Rectangle → Shape (Multilevel Inheritance)");

        // ---- POLYMORPHISM ----
        System.out.println("\n===== POLYMORPHISM =====");
        // Compile-time
        System.out.println("-- Method Overloading (Compile-time) --");
        new Calculator().demo();
        // Runtime
        System.out.println("-- Method Overriding (Runtime) --");
        Animal[] animals = { new Animal(), new Dog(), new Cat() };
        for (Animal a : animals) a.speak();  // Dynamic dispatch

        // ---- INTERFACE ----
        System.out.println("\n===== INTERFACE (Multiple Implementation) =====");
        Canvas c = new Canvas(100);
        c.draw();
        c.resize(1.5);
        c.draw();

        // ---- CONSTRUCTORS & THIS ----
        System.out.println("\n===== CONSTRUCTORS + THIS KEYWORD =====");
        Student s1 = new Student();
        Student s2 = new Student(101, "Priya", 9.2);
        Student s3 = new Student(s2);         // Copy constructor
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        // ---- STATIC vs INSTANCE ----
        System.out.println("\n===== STATIC vs INSTANCE VARIABLES & METHODS =====");
        Counter c1 = new Counter("Alpha");
        Counter c2 = new Counter("Beta");
        Counter c3 = new Counter("Gamma");
        c1.display();
        c2.display();
        c3.display();
        System.out.println("Total via static method: " + Counter.getTotalCount());

        // ---- INNER CLASSES ----
        System.out.println("\n===== INNER CLASSES =====");
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.show();
        OuterClass.StaticNested staticNested = new OuterClass.StaticNested();
        staticNested.show();
        outer.localClassDemo();
        outer.anonymousClassDemo();

        // ---- STRING CLASS ----
        StringDemo.run();

        // ---- WRAPPER CLASSES ----
        WrapperDemo.run();

        // ---- ARRAYS ----
        ArrayDemo.run();

        // ---- PARAMETER PASSING ----
        ParameterPassingDemo.run();

        // ---- GARBAGE COLLECTOR ----
        GarbageCollectorDemo.run();

        // ---- JAVA FEATURES ----
        JavaFeatures.run();

        // ---- C++ vs JAVA ----
        CppVsJava.run();

        System.out.println("\n============================");
        System.out.println(" Unit 1 & 2 Complete!");
        System.out.println("============================");
    }
}
