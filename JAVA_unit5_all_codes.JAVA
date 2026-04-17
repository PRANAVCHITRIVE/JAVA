// ============================================================
//  UNIT 5 — APPLETS
//  Covers: Introduction to HTML (for embedding applets),
//          Need for Applets, Applet Life Cycle,
//          Difference between Application and Applets,
//          Full Applet Demos with Graphics and Events
// ============================================================

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

// ============================================================
// NEED FOR APPLETS:
// - Web pages needed interactive/dynamic content
// - HTML alone is static; Applets brought Java programs INTO web
// - Could run Java programs inside a browser via Java Plugin
// - Now largely replaced by JavaScript/HTML5/Canvas
// ============================================================

// ============================================================
// APPLET LIFECYCLE METHODS:
//
//   init()    — Called ONCE when applet is first loaded
//               (like a constructor; initialize variables here)
//
//   start()   — Called each time the applet becomes visible
//               (browser tab re-opened, page re-visited)
//
//   paint()   — Called to render the applet (draw to screen)
//               Called after start(), and on repaint()
//
//   stop()    — Called when applet is no longer visible
//               (tab hidden, browser minimized)
//
//   destroy() — Called ONCE when applet is permanently removed
//               (browser closed; cleanup resources here)
//
// LIFECYCLE ORDER:
//   init() → start() → paint() → [stop() ↔ start()] → destroy()
// ============================================================

// ============================================================
// DEMO 1: BASIC APPLET — Hello World + Lifecycle Log
// ============================================================
/*
   HTML to embed this applet:
   <html>
   <body>
       <applet code="HelloApplet.class" width="500" height="300">
       </applet>
   </body>
   </html>
*/
// Note: Applets are declared public and extend java.applet.Applet
public class HelloApplet extends Applet {

    private String message;
    private int    visitCount = 0;
    private Color  bgColor;
    private Font   titleFont, bodyFont;

    // ---- LIFECYCLE METHOD 1: init() ----
    @Override
    public void init() {
        // Called once on first load — initialize everything here
        message   = "Hello from Java Applet!";
        bgColor   = new Color(200, 230, 255);
        titleFont = new Font("Arial", Font.BOLD, 28);
        bodyFont  = new Font("Monospaced", Font.PLAIN, 14);
        setBackground(bgColor);
        System.out.println("[LIFECYCLE] init()    — Applet initialized");
    }

    // ---- LIFECYCLE METHOD 2: start() ----
    @Override
    public void start() {
        // Called every time applet becomes visible
        visitCount++;
        System.out.println("[LIFECYCLE] start()   — Applet started (visit #" + visitCount + ")");
        repaint();   // Request a repaint
    }

    // ---- LIFECYCLE METHOD 3: paint() ----
    @Override
    public void paint(Graphics g) {
        System.out.println("[LIFECYCLE] paint()   — Applet rendering");

        // Background
        g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Title
        g.setFont(titleFont);
        g.setColor(new Color(30, 100, 200));
        g.drawString(message, 60, 60);

        // Underline
        g.setColor(new Color(30, 100, 200));
        g.drawLine(60, 68, 440, 68);

        // Body text
        g.setFont(bodyFont);
        g.setColor(Color.DARK_GRAY);
        String[] lines = {
            "Applet Lifecycle:",
            "  1. init()    — Runs once on load",
            "  2. start()   — Runs each time visible",
            "  3. paint()   — Renders the display",
            "  4. stop()    — Runs when hidden",
            "  5. destroy() — Runs on unload",
            "",
            "Visit count: " + visitCount,
            "Applet size: " + getWidth() + " x " + getHeight()
        };
        int y = 110;
        for (String line : lines) {
            g.drawString(line, 60, y);
            y += 22;
        }

        // Border
        g.setColor(new Color(30, 100, 200));
        g.drawRect(10, 10, getWidth() - 20, getHeight() - 20);
    }

    // ---- LIFECYCLE METHOD 4: stop() ----
    @Override
    public void stop() {
        System.out.println("[LIFECYCLE] stop()    — Applet stopped (hidden/tab changed)");
    }

    // ---- LIFECYCLE METHOD 5: destroy() ----
    @Override
    public void destroy() {
        System.out.println("[LIFECYCLE] destroy() — Applet destroyed (browser closed)");
    }
}

// ============================================================
// DEMO 2: APPLET WITH EVENT HANDLING
// ============================================================
/*
   HTML:
   <applet code="InteractiveApplet.class" width="500" height="400">
       <param name="title" value="My Interactive Applet">
       <param name="color" value="red">
   </applet>
*/
class InteractiveApplet extends Applet implements ActionListener, MouseListener {

    private TextField nameField;
    private Button    greetBtn, clearBtn;
    private Label     outputLabel;
    private String    paramTitle;
    private int       clickX = -1, clickY = -1;
    private Color     circleColor = Color.RED;

    @Override
    public void init() {
        // Reading HTML <param> tags
        paramTitle = getParameter("title");
        if (paramTitle == null) paramTitle = "Default Applet";

        String colorParam = getParameter("color");
        if ("blue".equals(colorParam))  circleColor = Color.BLUE;
        if ("green".equals(colorParam)) circleColor = Color.GREEN;

        setLayout(new FlowLayout());
        setBackground(new Color(255, 250, 230));

        nameField   = new TextField("Enter your name", 20);
        greetBtn    = new Button("Greet Me!");
        clearBtn    = new Button("Clear");
        outputLabel = new Label("Click Greet!", Label.CENTER);

        greetBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        this.addMouseListener(this);

        add(new Label("Title from param: " + paramTitle));
        add(new Label("Name:"));
        add(nameField);
        add(greetBtn);
        add(clearBtn);
        add(outputLabel);
        add(new Label("Click on applet to draw circle:"));
    }

    @Override
    public void start()   { System.out.println("InteractiveApplet: start()"); }
    @Override
    public void stop()    { System.out.println("InteractiveApplet: stop()"); }
    @Override
    public void destroy() { System.out.println("InteractiveApplet: destroy()"); }

    @Override
    public void paint(Graphics g) {
        // Draw circle where user clicked
        if (clickX >= 0) {
            g.setColor(circleColor);
            g.fillOval(clickX - 25, clickY - 25, 50, 50);
            g.setColor(Color.BLACK);
            g.drawString("(" + clickX + "," + clickY + ")", clickX + 30, clickY);
        }
        // Draw instruction
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.ITALIC, 12));
        g.drawString("Click anywhere below to draw a circle", 50, 320);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == greetBtn) {
            String name = nameField.getText().trim();
            outputLabel.setText("Hello, " + (name.isEmpty() ? "stranger" : name) + "! Welcome to Applets!");
        } else if (e.getSource() == clearBtn) {
            nameField.setText("");
            outputLabel.setText("Cleared!");
            clickX = clickY = -1;
            repaint();
        }
    }

    @Override public void mouseClicked(MouseEvent e) {
        clickX = e.getX(); clickY = e.getY();
        repaint();
    }
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited (MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e){}
}

// ============================================================
// DEMO 3: GRAPHICS APPLET — Animation using Thread
// ============================================================
class AnimatedApplet extends Applet implements Runnable {
    private Thread animThread;
    private int    ballX   = 0;
    private int    ballY   = 150;
    private int    dx      = 4;
    private int    dy      = 3;
    private int    RADIUS  = 30;
    private Color  ballColor = Color.RED;

    @Override
    public void init() {
        setBackground(Color.BLACK);
        System.out.println("AnimatedApplet: init()");
    }

    @Override
    public void start() {
        System.out.println("AnimatedApplet: start() — launching thread");
        animThread = new Thread(this);
        animThread.start();
    }

    @Override
    public void run() {
        while (animThread != null) {
            // Move ball
            ballX += dx;
            ballY += dy;
            // Bounce off walls
            if (ballX + RADIUS > getWidth()  || ballX - RADIUS < 0) dx = -dx;
            if (ballY + RADIUS > getHeight() || ballY - RADIUS < 0) dy = -dy;
            // Change color randomly every 50 frames
            if (Math.random() < 0.02) {
                ballColor = new Color((int)(Math.random()*256),
                                      (int)(Math.random()*256),
                                      (int)(Math.random()*256));
            }
            repaint();
            try { Thread.sleep(20); } catch (InterruptedException e) { break; }
        }
    }

    @Override
    public void paint(Graphics g) {
        // Double-buffering to reduce flicker
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw ball
        g.setColor(ballColor);
        g.fillOval(ballX - RADIUS, ballY - RADIUS, RADIUS * 2, RADIUS * 2);

        // Trail
        g.setColor(new Color(ballColor.getRed(), ballColor.getGreen(), ballColor.getBlue(), 80));
        g.fillOval(ballX - RADIUS - dx*3, ballY - RADIUS - dy*3, RADIUS * 2, RADIUS * 2);

        // Label
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Animated Applet (Bouncing Ball)", 100, 30);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Ball Position: (" + ballX + ", " + ballY + ")", 10, getHeight() - 10);
    }

    @Override
    public void stop() {
        System.out.println("AnimatedApplet: stop() — stopping thread");
        animThread = null;   // Stop the thread
    }

    @Override
    public void destroy() {
        System.out.println("AnimatedApplet: destroy()");
    }
}

// ============================================================
// APPLICATION vs APPLET COMPARISON
// ============================================================
class AppVsAppletDemo {
    public static void printComparison() {
        System.out.println("\n===== APPLICATION vs APPLET =====");
        String[][] table = {
            {"Feature",         "Java Application",             "Java Applet"                     },
            {"Entry Point",     "public static void main()",    "init(), start(), paint() etc."   },
            {"Execution",       "Standalone (cmd/terminal)",    "Inside browser (Java Plugin)"    },
            {"Security",        "Full system access",           "Sandbox — restricted access"     },
            {"File Access",     "Can read/write files",         "Cannot access local files"       },
            {"Network",         "Can open any connection",      "Only connects to server of origin"},
            {"Needs JRE",       "JRE on local machine",         "Browser Java Plugin"             },
            {"Compilation",     "javac → java",                 "javac → appletviewer / browser"  },
            {"HTML",            "Not required",                 "Embedded via <applet> tag"       },
            {"init()/main()",   "main() — entry point",         "init() — no main() needed"       },
            {"Usage Today",     "Very common",                  "Deprecated (use JS/HTML5)"       },
        };
        System.out.printf("%-18s | %-35s | %s%n", "─".repeat(18), "─".repeat(35), "─".repeat(35));
        for (String[] row : table) {
            System.out.printf("%-18s | %-35s | %s%n", row[0], row[1], row[2]);
        }
        System.out.printf("%-18s | %-35s | %s%n", "─".repeat(18), "─".repeat(35), "─".repeat(35));
    }
}

// ============================================================
// HTML FOR APPLETS (Printed as string — for reference)
// ============================================================
class HTMLForApplets {
    public static void printHTML() {
        System.out.println("\n===== HTML TO EMBED APPLETS =====");
        System.out.println(
            "<!-- Basic applet embedding -->\n" +
            "<html>\n" +
            "<head><title>Applet Demo</title></head>\n" +
            "<body>\n" +
            "  <h2>My Java Applet</h2>\n" +
            "\n" +
            "  <!-- Method 1: <applet> tag (deprecated HTML4) -->\n" +
            "  <applet code=\"HelloApplet.class\"\n" +
            "          width=\"500\"\n" +
            "          height=\"300\"\n" +
            "          codebase=\"./classes/\">\n" +
            "    <param name=\"title\" value=\"My Applet\">\n" +
            "    <param name=\"color\" value=\"blue\">\n" +
            "    Your browser does not support Java applets.\n" +
            "  </applet>\n" +
            "\n" +
            "  <!-- Method 2: <object> tag (HTML4/5) -->\n" +
            "  <object classid=\"clsid:8AD9C840-044E-11D1-B3E9-00805F499D93\"\n" +
            "          width=\"500\" height=\"300\">\n" +
            "    <param name=\"code\" value=\"HelloApplet.class\">\n" +
            "  </object>\n" +
            "\n" +
            "  <!-- Run with appletviewer (tool): -->\n" +
            "  <!-- $ appletviewer HelloApplet.html -->\n" +
            "\n" +
            "  <!-- Or run with appletviewer on .java with comment: -->\n" +
            "  <!-- Add this comment in .java file: -->\n" +
            "  <!-- <applet code=\"HelloApplet.class\" width=500 height=300> </applet> -->\n" +
            "  <!-- $ appletviewer HelloApplet.java -->\n" +
            "</body>\n" +
            "</html>"
        );
    }
}

// ============================================================
// MAIN — Console output for comparisons + instructions
// ============================================================
/*
  HOW TO RUN THE APPLETS:
  1. Compile:  javac HelloApplet.java InteractiveApplet.java AnimatedApplet.java
  2. Run:      appletviewer HelloApplet.java
               (make sure the HTML comment block at top of each class is present)

  Or use an online Java Applet runner.
  Modern browsers no longer support Java Applets natively.
*/
class Unit5_Applets {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║         UNIT 5: APPLETS                      ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        System.out.println("\n--- APPLET LIFECYCLE OVERVIEW ---");
        System.out.println(
            "  ┌─────────┐\n" +
            "  │  init() │ ← Called ONCE on first load\n" +
            "  └────┬────┘\n" +
            "       ↓\n" +
            "  ┌──────────┐\n" +
            "  │ start()  │ ← Called each time visible\n" +
            "  └────┬─────┘\n" +
            "       ↓\n" +
            "  ┌──────────┐\n" +
            "  │ paint()  │ ← Called to render/redraw\n" +
            "  └────┬─────┘\n" +
            "       ↓\n" +
            "  ┌──────────┐\n" +
            "  │  stop()  │ ← Called when applet hidden\n" +
            "  └────┬─────┘\n" +
            "       ↓\n" +
            "  ┌───────────┐\n" +
            "  │ destroy() │ ← Called ONCE when removed\n" +
            "  └───────────┘"
        );

        AppVsAppletDemo.printComparison();
        HTMLForApplets.printHTML();

        System.out.println("\n--- APPLET CLASSES DEFINED IN THIS FILE ---");
        System.out.println("1. HelloApplet        — Basic lifecycle demo with graphics");
        System.out.println("2. InteractiveApplet  — Event handling + HTML params");
        System.out.println("3. AnimatedApplet     — Thread-based animation (bouncing ball)");

        System.out.println("\n--- HOW TO RUN ---");
        System.out.println("$ javac Unit5_Applets.java");
        System.out.println("$ appletviewer Unit5_Applets.java");
        System.out.println("  (appletviewer reads the <applet> HTML comment above each class)");

        System.out.println("\n--- NEED FOR APPLETS ---");
        System.out.println("• Applets enabled rich, interactive Java programs in web browsers");
        System.out.println("• Before HTML5/JS, Java Applets were the only way to have");
        System.out.println("  animations, games, and dynamic content on web pages.");
        System.out.println("• Applets run in a security sandbox (JVM inside browser).");
        System.out.println("• Deprecated since Java 9, removed in Java 17.");
        System.out.println("• Modern alternatives: JavaScript, HTML5 Canvas, WebAssembly.");

        System.out.println("\n============================");
        System.out.println(" Unit 5 Complete!");
        System.out.println("============================");
    }
}

// ============================================================
// Applet HTML comments (for appletviewer tool):
// ============================================================
/*
<applet code="HelloApplet.class" width="500" height="350">
</applet>
*/

/*
<applet code="InteractiveApplet.class" width="500" height="400">
  <param name="title" value="My Interactive Applet">
  <param name="color" value="blue">
</applet>
*/

/*
<applet code="AnimatedApplet.class" width="600" height="400">
</applet>
*/
