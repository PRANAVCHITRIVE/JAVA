// ============================================================
//  UNIT 4 — INTRODUCTION TO AWT + SWING
//  Covers: Need for AWT, AWT Components, Graphics Class,
//          AWT Containers, Event Handling, Adapter Classes,
//          Introduction to Swing, Swing Components,
//          Layout Managers
// ============================================================
//
// NOTE: Run this file with a display environment (not headless).
//       It creates multiple GUI windows demonstrating each topic.
// ============================================================

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

// ============================================================
// WHY AWT?
// Before AWT, Java had no standard GUI toolkit.
// AWT (Abstract Window Toolkit) provides platform-native
// UI components via the OS's native windowing system.
// Swing later improved on AWT with lightweight components.
// ============================================================

// ============================================================
// SECTION 1: AWT COMPONENTS + GRAPHICS CLASS
// ============================================================
class AWTComponentsDemo extends Frame {

    public AWTComponentsDemo() {
        setTitle("AWT Components Demo");
        setSize(600, 500);
        setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        setBackground(new Color(240, 240, 240));

        // --- Label ---
        Label lbl = new Label("AWT Label");
        lbl.setFont(new Font("Arial", Font.BOLD, 14));

        // --- TextField ---
        TextField tf = new TextField("Type here...", 20);

        // --- TextArea ---
        TextArea ta = new TextArea("Multi-line text\nLine 2\nLine 3", 4, 25);

        // --- Button ---
        Button btn = new Button("Click Me");
        btn.setBackground(Color.BLUE);
        btn.setForeground(Color.WHITE);

        // --- Checkbox ---
        Checkbox cb1 = new Checkbox("Java",   true);
        Checkbox cb2 = new Checkbox("Python", false);

        // --- CheckboxGroup (Radio Buttons in AWT) ---
        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox r1 = new Checkbox("Male",   cbg, true);
        Checkbox r2 = new Checkbox("Female", cbg, false);

        // --- Choice (Drop-down) ---
        Choice choice = new Choice();
        choice.add("Nagpur");
        choice.add("Mumbai");
        choice.add("Pune");

        // --- List ---
        List list = new List(4, false);
        list.add("C++");
        list.add("Java");
        list.add("Python");
        list.add("JavaScript");

        // --- Scrollbar ---
        Scrollbar sb = new Scrollbar(Scrollbar.HORIZONTAL, 50, 10, 0, 100);

        // Add all components
        add(new Label("=== AWT COMPONENTS ==="));
        add(lbl); add(tf); add(ta); add(btn);
        add(new Label("Checkboxes:")); add(cb1); add(cb2);
        add(new Label("Radio:")); add(r1); add(r2);
        add(new Label("Choice:")); add(choice);
        add(new Label("List:")); add(list);
        add(new Label("Scrollbar:")); add(sb);

        // Window close event
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
    }
}

// ============================================================
// SECTION 2: GRAPHICS CLASS
// The Graphics object lets you draw on AWT components.
// ============================================================
class GraphicsDemo extends Frame {

    public GraphicsDemo() {
        setTitle("AWT Graphics Class Demo");
        setSize(600, 500);

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
    }

    @Override
    public void paint(Graphics g) {
        // Set font
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("Graphics Class Demo", 180, 50);

        // Draw shapes
        g.setColor(Color.RED);
        g.drawLine(50, 70, 550, 70);        // Line

        g.setColor(Color.BLUE);
        g.drawRect(50, 90, 150, 80);        // Rectangle (outline)

        g.setColor(Color.GREEN);
        g.fillRect(250, 90, 150, 80);       // Filled rectangle

        g.setColor(Color.ORANGE);
        g.drawOval(50, 200, 150, 100);      // Oval/Circle

        g.setColor(Color.MAGENTA);
        g.fillOval(250, 200, 150, 100);     // Filled oval

        g.setColor(Color.CYAN);
        g.drawRoundRect(50, 330, 200, 80, 30, 30); // Rounded rect

        // Polygon (triangle)
        int[] xPts = {450, 400, 500};
        int[] yPts = {330, 420, 420};
        g.setColor(Color.PINK);
        g.fillPolygon(xPts, yPts, 3);

        // Arc
        g.setColor(Color.DARK_GRAY);
        g.drawArc(300, 330, 150, 80, 0, 270);

        // 3D Rectangle
        g.setColor(Color.GRAY);
        g.fill3DRect(470, 100, 100, 60, true);

        // Labels
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.setColor(Color.BLACK);
        g.drawString("drawLine",       50,  75);
        g.drawString("drawRect",       55, 175);
        g.drawString("fillRect",       255, 175);
        g.drawString("drawOval",       55, 305);
        g.drawString("fillOval",       255, 305);
        g.drawString("drawRoundRect",  55, 420);
        g.drawString("fillPolygon",    430, 435);
        g.drawString("drawArc",        300, 425);
        g.drawString("fill3DRect",     470,  90);
    }
}

// ============================================================
// SECTION 3: AWT CONTAINERS
// Frame, Panel, Dialog, ScrollPane are key containers.
// ============================================================
class AWTContainersDemo extends Frame {

    public AWTContainersDemo() {
        setTitle("AWT Containers: Frame + Panel");
        setSize(500, 350);
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.LIGHT_GRAY);

        // Panel — invisible container to group components
        Panel topPanel = new Panel();
        topPanel.setBackground(new Color(173, 216, 230)); // Light Blue
        topPanel.add(new Label("TOP PANEL (Panel inside Frame)"));
        topPanel.add(new Button("Panel Button"));

        Panel centerPanel = new Panel();
        centerPanel.setBackground(new Color(255, 255, 200)); // Light yellow
        centerPanel.setLayout(new GridLayout(2, 2, 5, 5));
        centerPanel.add(new Button("NW"));
        centerPanel.add(new Button("NE"));
        centerPanel.add(new Button("SW"));
        centerPanel.add(new Button("SE"));

        Panel bottomPanel = new Panel();
        bottomPanel.setBackground(new Color(200, 230, 200)); // Light green
        bottomPanel.add(new Label("BOTTOM PANEL — Status Bar"));

        add(topPanel,    BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
    }
}

// ============================================================
// SECTION 4: EVENT HANDLING IN AWT
// Event-driven programming: user actions (clicks, key press)
// generate Events → Listeners handle them.
// Steps: 1) Implement listener 2) Register with component
// ============================================================
class EventHandlingDemo extends Frame
        implements ActionListener, MouseListener,
                   KeyListener, ItemListener,
                   AdjustmentListener {

    private Label statusLabel;
    private TextField inputField;
    private TextArea logArea;
    private Scrollbar slider;
    private Checkbox check;

    public EventHandlingDemo() {
        setTitle("AWT Event Handling Demo");
        setSize(600, 500);
        setLayout(new BorderLayout(5, 5));

        // Top panel
        Panel topPanel = new Panel(new FlowLayout());
        Button clickBtn = new Button("Click Event");
        Button clearBtn = new Button("Clear Log");
        inputField = new TextField("Press keys here", 20);
        topPanel.add(clickBtn); topPanel.add(clearBtn); topPanel.add(inputField);

        // Center
        logArea = new TextArea(12, 55);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Bottom
        Panel bottomPanel = new Panel(new FlowLayout());
        statusLabel = new Label("Status: Ready          ");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        slider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 100);
        check = new Checkbox("Enable Logging", true);
        bottomPanel.add(statusLabel); bottomPanel.add(slider); bottomPanel.add(check);

        add(topPanel, BorderLayout.NORTH);
        add(logArea, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Register listeners
        clickBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        this.addMouseListener(this);          // MouseListener on frame
        inputField.addKeyListener(this);      // KeyListener on text field
        check.addItemListener(this);          // ItemListener on checkbox
        slider.addAdjustmentListener(this);   // AdjustmentListener on scrollbar

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
        });
    }

    private void log(String msg) {
        if (check.getState()) {
            logArea.append("[EVENT] " + msg + "\n");
        }
    }

    // --- ActionListener ---
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("Click Event".equals(cmd)) {
            statusLabel.setText("Button Clicked!  ");
            log("ActionEvent: Button 'Click Event' pressed");
        } else if ("Clear Log".equals(cmd)) {
            logArea.setText("");
        }
    }

    // --- MouseListener ---
    @Override public void mouseClicked (MouseEvent e) { log("MouseClicked  at (" + e.getX() + "," + e.getY() + ")"); }
    @Override public void mousePressed (MouseEvent e) { log("MousePressed  at (" + e.getX() + "," + e.getY() + ")"); }
    @Override public void mouseReleased(MouseEvent e) { log("MouseReleased at (" + e.getX() + "," + e.getY() + ")"); }
    @Override public void mouseEntered (MouseEvent e) { statusLabel.setText("Mouse Entered  "); }
    @Override public void mouseExited  (MouseEvent e) { statusLabel.setText("Mouse Exited   "); }

    // --- KeyListener ---
    @Override public void keyTyped  (KeyEvent e) { log("KeyTyped   : '" + e.getKeyChar() + "'"); }
    @Override public void keyPressed(KeyEvent e) { log("KeyPressed : " + KeyEvent.getKeyText(e.getKeyCode())); }
    @Override public void keyReleased(KeyEvent e){ log("KeyReleased: " + KeyEvent.getKeyText(e.getKeyCode())); }

    // --- ItemListener (Checkbox) ---
    @Override public void itemStateChanged(ItemEvent e) {
        log("ItemEvent: Checkbox = " + (check.getState() ? "Checked" : "Unchecked"));
    }

    // --- AdjustmentListener (Scrollbar) ---
    @Override public void adjustmentValueChanged(AdjustmentEvent e) {
        statusLabel.setText("Slider: " + slider.getValue() + "    ");
        log("AdjustmentEvent: Slider = " + slider.getValue());
    }
}

// ============================================================
// SECTION 5: ADAPTER CLASSES
// Problem: Implementing a listener interface forces you to
// implement ALL methods, even unused ones — boilerplate.
// Solution: Adapter classes provide empty implementations.
// You extend the adapter and override only what you need.
// ============================================================
class AdapterClassDemo extends Frame {

    public AdapterClassDemo() {
        setTitle("Adapter Classes Demo");
        setSize(450, 300);
        setBackground(Color.WHITE);
        Label info = new Label("Move/Click mouse. Only needed methods overridden.", Label.CENTER);
        Label status = new Label("Status: Waiting...", Label.CENTER);
        setLayout(new BorderLayout());
        add(info, BorderLayout.NORTH);
        add(status, BorderLayout.CENTER);

        // Using MouseAdapter (adapter class) — extend and override only needed methods
        // Without adapter, we'd need to implement all 5 MouseListener methods
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {   // Only override needed methods
                status.setText("Mouse Clicked at (" + e.getX() + "," + e.getY() + ")");
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                status.setText("Mouse Entered window");
            }
        });

        // WindowAdapter — just override windowClosing (without adapter, need 7 methods)
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { dispose(); }
            @Override public void windowOpened(WindowEvent e)  { status.setText("Window Opened"); }
        });

        // Available Adapter Classes in Java AWT:
        // MouseAdapter, MouseMotionAdapter, KeyAdapter,
        // WindowAdapter, FocusAdapter, ComponentAdapter
    }
}

// ============================================================
// SECTION 6: INTRODUCTION TO SWING + SWING COMPONENTS
// Swing: Built on AWT but all components are lightweight
// (drawn by Java, not native OS). More features, consistent UI.
// JFrame, JPanel, JButton, JLabel, JTextField, JTextArea,
// JCheckBox, JRadioButton, JComboBox, JList, JTable,
// JMenuBar, JMenu, JMenuItem, JScrollPane, JSlider, etc.
// ============================================================
class SwingComponentsDemo extends JFrame {

    public SwingComponentsDemo() {
        setTitle("Swing Components Demo");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Use tabbed pane to organise Swing components
        JTabbedPane tabs = new JTabbedPane();

        // --- Tab 1: Basic Components ---
        JPanel basicPanel = new JPanel(new GridLayout(0, 2, 10, 8));
        basicPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        basicPanel.add(new JLabel("JLabel (plain):"));
        basicPanel.add(new JLabel("Hello from JLabel!"));

        basicPanel.add(new JLabel("JLabel (icon text):"));
        JLabel iconLabel = new JLabel("★ Starred", JLabel.LEFT);
        iconLabel.setForeground(Color.BLUE);
        basicPanel.add(iconLabel);

        basicPanel.add(new JLabel("JTextField:"));
        basicPanel.add(new JTextField("Editable text field", 20));

        basicPanel.add(new JLabel("JPasswordField:"));
        basicPanel.add(new JPasswordField("secret"));

        basicPanel.add(new JLabel("JTextArea:"));
        JTextArea jta = new JTextArea("Multi\nline\nSwing", 4, 20);
        basicPanel.add(new JScrollPane(jta));

        basicPanel.add(new JLabel("JButton:"));
        JButton jbtn = new JButton("Swing Button");
        jbtn.setBackground(new Color(70, 130, 180));
        jbtn.setForeground(Color.WHITE);
        basicPanel.add(jbtn);

        basicPanel.add(new JLabel("JCheckBox:"));
        JPanel cbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbPanel.add(new JCheckBox("Option A", true));
        cbPanel.add(new JCheckBox("Option B"));
        basicPanel.add(cbPanel);

        basicPanel.add(new JLabel("JRadioButton:"));
        JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup bg = new ButtonGroup();
        JRadioButton rb1 = new JRadioButton("Male",   true);
        JRadioButton rb2 = new JRadioButton("Female");
        bg.add(rb1); bg.add(rb2);
        rbPanel.add(rb1); rbPanel.add(rb2);
        basicPanel.add(rbPanel);

        basicPanel.add(new JLabel("JComboBox:"));
        JComboBox<String> combo = new JComboBox<>(new String[]{"Nagpur","Mumbai","Pune","Delhi"});
        basicPanel.add(combo);

        basicPanel.add(new JLabel("JList:"));
        JList<String> jlist = new JList<>(new String[]{"Java","Python","C++","JavaScript","Kotlin"});
        jlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        basicPanel.add(new JScrollPane(jlist));

        basicPanel.add(new JLabel("JSlider:"));
        JSlider slider = new JSlider(0, 100, 40);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        basicPanel.add(slider);

        basicPanel.add(new JLabel("JSpinner:"));
        basicPanel.add(new JSpinner(new SpinnerNumberModel(5, 0, 100, 1)));

        tabs.addTab("Basic Components", basicPanel);

        // --- Tab 2: JTable ---
        String[] cols = {"ID", "Name", "Course", "GPA"};
        Object[][] data = {
            {101, "Aarav",  "CS",   9.2},
            {102, "Priya",  "IT",   8.7},
            {103, "Rohan",  "ECE",  7.9},
            {104, "Sneha",  "CS",   9.5},
            {105, "Vikram", "ME",   8.1},
        };
        JTable table = new JTable(data, cols);
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        tabs.addTab("JTable", new JScrollPane(table));

        // --- Tab 3: JMenuBar ---
        JPanel menuPanel = new JPanel(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        fileMenu.add(new JMenuItem("New"));
        fileMenu.add(new JMenuItem("Open"));
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Exit"));

        editMenu.add(new JMenuItem("Cut"));
        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));

        JCheckBoxMenuItem cbItem = new JCheckBoxMenuItem("Show Toolbar", true);
        JRadioButtonMenuItem rbItem1 = new JRadioButtonMenuItem("Light Theme", true);
        JRadioButtonMenuItem rbItem2 = new JRadioButtonMenuItem("Dark Theme");
        ButtonGroup themeGroup = new ButtonGroup();
        themeGroup.add(rbItem1); themeGroup.add(rbItem2);
        editMenu.addSeparator();
        editMenu.add(cbItem);
        editMenu.add(rbItem1);
        editMenu.add(rbItem2);

        helpMenu.add(new JMenuItem("About"));
        menuBar.add(fileMenu); menuBar.add(editMenu); menuBar.add(helpMenu);
        menuPanel.add(menuBar, BorderLayout.NORTH);
        menuPanel.add(new JLabel("Use the menu bar above", JLabel.CENTER), BorderLayout.CENTER);
        tabs.addTab("JMenuBar & JMenu", menuPanel);

        // --- Tab 4: JProgressBar + JToolBar ---
        JPanel progPanel = new JPanel(new GridLayout(0, 1, 10, 20));
        progPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JProgressBar pb1 = new JProgressBar(0, 100);
        pb1.setValue(65); pb1.setStringPainted(true);
        JProgressBar pb2 = new JProgressBar(0, 100);
        pb2.setValue(90); pb2.setStringPainted(true); pb2.setString("Loading: 90%");
        pb2.setForeground(new Color(0, 180, 0));

        JToolBar toolbar = new JToolBar("Tools");
        toolbar.add(new JButton("New"));
        toolbar.add(new JButton("Open"));
        toolbar.add(new JButton("Save"));
        toolbar.addSeparator();
        toolbar.add(new JButton("Cut"));
        toolbar.add(new JButton("Copy"));
        toolbar.add(new JButton("Paste"));

        progPanel.add(new JLabel("JProgressBar (65%):"));
        progPanel.add(pb1);
        progPanel.add(new JLabel("JProgressBar (90% styled):"));
        progPanel.add(pb2);
        progPanel.add(new JLabel("JToolBar:"));
        progPanel.add(toolbar);

        tabs.addTab("JProgressBar + JToolBar", progPanel);

        add(tabs, BorderLayout.CENTER);
    }
}

// ============================================================
// SECTION 7: LAYOUT MANAGERS
// Control how components are arranged in a container.
// ============================================================
class LayoutManagersDemo extends JFrame {

    public LayoutManagersDemo() {
        setTitle("Layout Managers Demo");
        setSize(750, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // 1. FlowLayout — left to right, wraps on overflow
        JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        flowPanel.setBorder(createTitled("FlowLayout — left to right, wraps"));
        for (int i = 1; i <= 9; i++) flowPanel.add(new JButton("Btn " + i));
        tabs.addTab("FlowLayout", flowPanel);

        // 2. BorderLayout — North, South, East, West, Center
        JPanel borderPanel = new JPanel(new BorderLayout(5, 5));
        borderPanel.setBorder(createTitled("BorderLayout — NORTH/SOUTH/EAST/WEST/CENTER"));
        borderPanel.add(makeBtn("NORTH",  Color.RED),             BorderLayout.NORTH);
        borderPanel.add(makeBtn("SOUTH",  Color.GREEN),           BorderLayout.SOUTH);
        borderPanel.add(makeBtn("EAST",   Color.CYAN),            BorderLayout.EAST);
        borderPanel.add(makeBtn("WEST",   Color.YELLOW),          BorderLayout.WEST);
        borderPanel.add(makeBtn("CENTER", new Color(180,180,255)),BorderLayout.CENTER);
        tabs.addTab("BorderLayout", borderPanel);

        // 3. GridLayout — equal-size cells in a grid
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        gridPanel.setBorder(createTitled("GridLayout — 3x3 equal cells"));
        String[] nums = {"7","8","9","4","5","6","1","2","3"};
        for (String n : nums) gridPanel.add(new JButton(n));
        tabs.addTab("GridLayout", gridPanel);

        // 4. GridBagLayout — most flexible, constraint-based
        JPanel gbPanel = new JPanel(new GridBagLayout());
        gbPanel.setBorder(createTitled("GridBagLayout — constraint-based"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addGBC(gbPanel, new JLabel("Username:"),       gbc, 0, 0, 1, 1);
        addGBC(gbPanel, new JTextField(15),            gbc, 1, 0, 2, 1);
        addGBC(gbPanel, new JLabel("Password:"),       gbc, 0, 1, 1, 1);
        addGBC(gbPanel, new JPasswordField(15),        gbc, 1, 1, 2, 1);
        addGBC(gbPanel, new JLabel("Course:"),         gbc, 0, 2, 1, 1);
        JComboBox<String> courseBox = new JComboBox<>(new String[]{"CS","IT","ECE"});
        addGBC(gbPanel, courseBox,                     gbc, 1, 2, 2, 1);
        gbc.gridwidth = 3; gbc.gridx = 0; gbc.gridy = 3;
        gbPanel.add(new JButton("Submit"), gbc);
        tabs.addTab("GridBagLayout", gbPanel);

        // 5. CardLayout — swap between panels like cards
        JPanel cardOuter = new JPanel(new BorderLayout());
        cardOuter.setBorder(createTitled("CardLayout — switch between panels"));
        CardLayout cl = new CardLayout();
        JPanel cards = new JPanel(cl);
        String[] cardNames = {"Login Card", "Register Card", "Home Card"};
        Color[]  cardColors = {new Color(255,220,200), new Color(200,255,220), new Color(200,220,255)};
        for (int i = 0; i < cardNames.length; i++) {
            JPanel card = new JPanel();
            card.setBackground(cardColors[i]);
            card.add(new JLabel(cardNames[i], JLabel.CENTER));
            cards.add(card, cardNames[i]);
        }
        JPanel nav = new JPanel(new FlowLayout());
        for (String name : cardNames) {
            JButton navBtn = new JButton(name);
            navBtn.addActionListener(e -> cl.show(cards, name));
            nav.add(navBtn);
        }
        cardOuter.add(nav, BorderLayout.NORTH);
        cardOuter.add(cards, BorderLayout.CENTER);
        tabs.addTab("CardLayout", cardOuter);

        // 6. BoxLayout — horizontal or vertical stacking
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setBorder(createTitled("BoxLayout — vertical stacking"));
        String[] items = {"Profile","Settings","Notifications","Help","Logout"};
        for (String item : items) {
            JButton b = new JButton(item);
            b.setMaximumSize(new Dimension(200, 35));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            boxPanel.add(Box.createVerticalStrut(8));
            boxPanel.add(b);
        }
        tabs.addTab("BoxLayout", boxPanel);

        add(tabs);
    }

    private Border createTitled(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), title);
    }

    private JButton makeBtn(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        return b;
    }

    private void addGBC(JPanel panel, Component comp, GridBagConstraints gbc,
                        int x, int y, int w, int h) {
        gbc.gridx = x; gbc.gridy = y;
        gbc.gridwidth = w; gbc.gridheight = h;
        panel.add(comp, gbc);
    }
}

// ============================================================
// SECTION 8: SWING EVENT HANDLING
// Same as AWT event handling but with Swing components.
// Also demonstrates Lambda expressions (Java 8+) for listeners.
// ============================================================
class SwingEventHandlingDemo extends JFrame {

    private JLabel resultLabel;

    public SwingEventHandlingDemo() {
        setTitle("Swing Event Handling Demo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Main content
        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 5, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        resultLabel = new JLabel("Action result will appear here", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 13));
        resultLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // ActionListener via lambda
        JButton calcBtn = new JButton("Calculate (5 + 3)");
        calcBtn.addActionListener(e -> resultLabel.setText("Result: " + (5 + 3)));

        // MouseListener on a label
        JLabel hoverLabel = new JLabel("[Hover over me!]", JLabel.CENTER);
        hoverLabel.setOpaque(true);
        hoverLabel.setBackground(Color.LIGHT_GRAY);
        hoverLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        hoverLabel.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                hoverLabel.setBackground(new Color(135, 206, 250));
                hoverLabel.setText("Mouse is INSIDE!");
            }
            @Override public void mouseExited(MouseEvent e) {
                hoverLabel.setBackground(Color.LIGHT_GRAY);
                hoverLabel.setText("[Hover over me!]");
            }
            @Override public void mouseClicked(MouseEvent e) {
                resultLabel.setText("Label Clicked! Clicks: " + e.getClickCount());
            }
        });

        // KeyListener on JTextField
        JTextField keyField = new JTextField("Type here...");
        keyField.addKeyListener(new KeyAdapter() {
            @Override public void keyTyped(KeyEvent e) {
                resultLabel.setText("Key typed: '" + e.getKeyChar() + "'");
            }
        });

        // ItemListener on JCheckBox (lambda)
        JCheckBox checkbox = new JCheckBox("Enable Feature");
        checkbox.addItemListener(e ->
            resultLabel.setText("Checkbox: " + (checkbox.isSelected() ? "ON" : "OFF")));

        // ChangeListener on JSlider (lambda)
        JSlider slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e ->
            resultLabel.setText("Slider value: " + slider.getValue()));

        mainPanel.add(new JLabel("=== SWING EVENT HANDLING ===", JLabel.CENTER));
        mainPanel.add(calcBtn);
        mainPanel.add(hoverLabel);
        mainPanel.add(new JLabel("KeyListener:", JLabel.LEFT));
        mainPanel.add(keyField);
        mainPanel.add(checkbox);
        mainPanel.add(slider);
        mainPanel.add(resultLabel);

        add(mainPanel, BorderLayout.CENTER);
    }
}

// ============================================================
// MAIN — Launch all windows
// ============================================================
public class Unit4_AWT_Swing {

    public static void main(String[] args) {
        // Run on EDT (Event Dispatch Thread) — Swing/AWT best practice
        SwingUtilities.invokeLater(() -> {

            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║      UNIT 4: AWT + SWING GUI DEMOS          ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.println("Opening multiple GUI windows...");

            // Position windows so they don't overlap
            int offset = 0;

            AWTComponentsDemo win1 = new AWTComponentsDemo();
            win1.setLocation(0 + offset, 0); win1.setVisible(true);

            GraphicsDemo win2 = new GraphicsDemo();
            win2.setLocation(620 + offset, 0); win2.setVisible(true);

            AWTContainersDemo win3 = new AWTContainersDemo();
            win3.setLocation(0 + offset, 520); win3.setVisible(true);

            EventHandlingDemo win4 = new EventHandlingDemo();
            win4.setLocation(520 + offset, 520); win4.setVisible(true);

            AdapterClassDemo win5 = new AdapterClassDemo();
            win5.setLocation(300 + offset, 0); win5.setVisible(true);

            SwingComponentsDemo win6 = new SwingComponentsDemo();
            win6.setLocation(50, 50); win6.setVisible(true);

            LayoutManagersDemo win7 = new LayoutManagersDemo();
            win7.setLocation(120, 120); win7.setVisible(true);

            SwingEventHandlingDemo win8 = new SwingEventHandlingDemo();
            win8.setLocation(200, 200); win8.setVisible(true);

            System.out.println("All windows launched! Close each window individually.");
        });
    }
}
