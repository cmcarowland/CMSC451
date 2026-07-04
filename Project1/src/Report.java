import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Report extends JFrame {
    private Container c;
    private File selectedFile;
    private DefaultTableModel tableModel;
    private JTable table;
    
    public static void main(String[] args) throws Exception
    {
        if(args.length > 0) {
            String fileName = args[0];
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                JOptionPane.showMessageDialog(null, "The specified file does not exist or is not a valid file.", "File Not Found", JOptionPane.WARNING_MESSAGE);
                fileName = "";
            }
            Report f = new Report(fileName);
        } else {
            Report f = new Report("");
        }
    }

    private static final String[] COLUMN_NAMES = {
        "Size",
        "Avg Count",
        "Coef Counts",
        "Avg Time",
        "Coef Time"
    };


    public Report(String fileName)
    {
        setTitleWithFileName();
        setBounds(300, 90, 400, 275);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
        table = new JTable(tableModel);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getTableHeader().setDefaultRenderer(headerRenderer);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int col = 1; col < table.getColumnCount(); col++)
            table.getColumnModel().getColumn(col).setCellRenderer(rightRenderer);

        c.add(new JScrollPane(table), BorderLayout.CENTER);

        setJMenuBar(buildMenuBar());

        setVisible(true);
        if (!fileName.isEmpty()) {
            selectedFile = new File(fileName);
            readFile(selectedFile);
        } else {
            loadFile();
        }
    }

    private void setTitleWithFileName()
    {
        setTitle("Project 1 - Report " + (selectedFile == null ? "" : "(" + selectedFile.getName() + ")"));
    }

    private JMenuBar buildMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(e -> loadFile());
        fileMenu.add(loadItem);

        JMenuItem closeItem = new JMenuItem("Close Report");
        closeItem.addActionListener(e -> close());
        fileMenu.add(closeItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        return menuBar;
    }

    private void loadFile()
    {
        JFileChooser chooser = new JFileChooser("/workspaces/CMSC451/Project1");
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            setTitleWithFileName();
            readFile(selectedFile);
        }
    }

    private void close()
    {
        tableModel.setRowCount(0);
        selectedFile = null;
        setTitleWithFileName();
    }

    private void readFile(File file)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            tableModel.setRowCount(0);
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                populateTable(line);
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    }

    private void populateTable(String line)
    {
        String[] tokens = line.split("\\s+");
        int n = Integer.parseInt(tokens[0]);
        int pairCount = (tokens.length - 1) / 2;

        double[] counts = new double[pairCount];
        double[] times = new double[pairCount];
        int countIndex = 0;
        for (int i = 1; i < tokens.length; i += 2) {
            counts[countIndex] = Double.parseDouble(tokens[i]);
            times[countIndex] = Double.parseDouble(tokens[i + 1]);
            countIndex++;
        }

        double countAvg = average(counts);
        double timeAvg = average(times);

        tableModel.addRow(new Object[] {
            n,
            String.format("%.2f", countAvg),
            String.format("%.2f%%", coefficientOfVariation(counts, countAvg)),
            String.format("%.2f", timeAvg),
            String.format("%.2f%%", coefficientOfVariation(times, timeAvg))
        });
    }

    private double average(double[] values)
    {
        double sum = 0;
        for (double v : values)
            sum += v;

        // System.out.println("Sum: " + sum + ", Length: " + values.length + ", Average: " + (sum / values.length));
        return sum / values.length;
    }

    private double coefficientOfVariation(double[] values, double mean)
    {
        double variance = 0;
        for (double v : values)
            variance += (v - mean) * (v - mean);

        variance /= values.length;

        double stdDev = Math.sqrt(variance);
        return mean == 0 ? 0 : (stdDev / mean) * 100;
    }
}
