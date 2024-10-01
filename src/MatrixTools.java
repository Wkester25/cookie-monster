import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import java.awt.*;

public class MatrixTools {
    public static final int DEFAULT_BOX_WIDTH = 50;
    public static final int DEFAULT_BOX_HEIGHT = 50;
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    public static final Color DEFAULT_FONT_COLOR = Color.BLACK;
    public static final java.lang.String DEFAULT_FONT = "Arial";
    public static final int DEFAULT_FONT_SIZE = 12;
    public static final Border DEFAULT_BORDER = BorderFactory.createLineBorder(Color.BLACK);
    public static final int DEFAULT_FONT_STYLE = Font.PLAIN;


    public static class Integer{
        public static class Display{
            public static void displayMatrixJFrame(int [][] m, Font font, Color backgroundColor, Color fontColor, Border border){
                int widestRow = 0;
                for(int[] row : m) {
                    if(row.length > widestRow) {
                        widestRow = row.length;
                    }
                }
                Panel MatrixPanel = new Panel();
                MatrixPanel.setBackground(backgroundColor);
                MatrixPanel.setLayout(new GridLayout(m.length, widestRow));
                boolean toggle = true;
                for (int[] ints : m) {
                    for (int col = 0; col < widestRow; col++) {
                        JLabel label = new JLabel();
                        label.setSize(DEFAULT_BOX_WIDTH, DEFAULT_BOX_HEIGHT);
                        if (col >= ints.length) {
                            label.setText("");
                        } else {
                            label.setText(java.lang.Integer.toString(ints[col]));
                        }
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setVerticalAlignment(JLabel.CENTER);
                        label.setFont(font);
                        label.setForeground(fontColor);
                        label.setBorder(border);
                        MatrixPanel.add(label);
                    }
                }
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(MatrixPanel);
                frame.setVisible(true);
                frame.setSize(50 * m[1].length + frame.getInsets().left + frame.getInsets().right , 50 * m.length + frame.getInsets().top + frame.getInsets().bottom);
            }
            public static void displayMatrixJFrame(int [][] m){
                displayMatrixJFrame(m, new Font(DEFAULT_FONT, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE), DEFAULT_BACKGROUND_COLOR, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(int [][] m, Font font){
                displayMatrixJFrame(m, font, DEFAULT_BACKGROUND_COLOR, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(int [][] m, Font font, Color backgroundColor){
                displayMatrixJFrame(m, font, backgroundColor, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(int [][] m, Font font, Color backgroundColor, Color fontColor){
                displayMatrixJFrame(m, font, backgroundColor, fontColor, DEFAULT_BORDER);
            }






        }
        public static class Generation{
            public static int [][] randomMatrix(int rows, int cols, int min, int max){
                int [][] matrix = new int[rows][cols];
                for(int r = 0; r < rows; r++){
                    for(int c = 0; c < cols; c++){
                        matrix[r][c] = (int)(Math.random() * (max - min + 1) + min);
                    }
                }
                return matrix;
            }
            public static int [][] randomMatrix(int rows, int cols){
                return randomMatrix(rows, cols, java.lang.Integer.MIN_VALUE, java.lang.Integer.MAX_VALUE);
            }
            public static int [][] filledMatrix(int rows, int cols, int value){
                int [][] matrix = new int[rows][cols];
                for(int r = 0; r < rows; r++){
                    for(int c = 0; c < cols; c++){
                        matrix[r][c] = value;
                    }
                }
                return matrix;
            }
        }
        public static class Manipulation{
            public static void fillRectangle(int [][] m, int r1, int c1, int r2, int c2, int value){
                for(int r = r1; r <= r2; r++){
                    for(int c = c1; c <= c2; c++){
                        m[r][c] = value;
                    }
                }
            }
            public static void fillRectangle(int [][] m, int r1, int c1, int r2, int c2){
                fillRectangle(m, r1, c1, r2, c2, 0);
            }
            public static void clearMatrix(int [][] m){
                fillRectangle(m, 0, 0, m.length - 1, m[0].length - 1);
            }
            public static void fillRow(int [][] m, int r, int value){
                fillRectangle(m, r, 0, r, m[0].length - 1, value);
            }
            public static void fillRow(int [][] m, int r){
                fillRow(m, r, 0);
            }
            public static void fillColumn(int [][] m, int c, int value){
                fillRectangle(m, 0, c, m.length - 1, c, value);
            }
            public static void fillColumn(int [][] m, int c){
                fillColumn(m, c, 0);
            }

            public static void fillDiagonal(int [][] m, int startColumn, int value, boolean direction){
                if(direction){
                    for(int r = 0; r < m.length; r++){
                        if(startColumn + r < m[0].length){
                            m[r][startColumn + r] = value;
                        }
                    }
                }
                else {
                    for (int r = 0; r < m.length; r++) {
                        if (startColumn - r >= 0) {
                            m[r][startColumn - r] = value;
                        }
                    }
                }
            }
            public static void fillDiagonal(int [][] m, int startColumn, int value){
                fillDiagonal(m, startColumn, value, true);
            }
        }
    }

    public static class Double{
        public static class Display{
            public static void displayMatrixJFrame(double [][] m, Font font, Color backgroundColor, Color fontColor, Border border){
                int widestRow = 0;
                for(double[] row : m) {
                    if(row.length > widestRow) {
                        widestRow = row.length;
                    }
                }
                Panel MatrixPanel = new Panel();
                MatrixPanel.setBackground(backgroundColor);
                MatrixPanel.setLayout(new GridLayout(m.length, widestRow));
                boolean toggle = true;
                for (double[] doubles : m) {
                    for (int col = 0; col < widestRow; col++) {
                        JLabel label = new JLabel();
                        label.setSize(DEFAULT_BOX_WIDTH, DEFAULT_BOX_HEIGHT);
                        if (col >= doubles.length) {
                            label.setText("");
                        } else {
                            label.setText(java.lang.Double.toString(doubles[col]));
                        }
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setVerticalAlignment(JLabel.CENTER);
                        label.setFont(font);
                        label.setForeground(fontColor);
                        label.setBorder(border);
                        MatrixPanel.add(label);
                    }
                }
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(MatrixPanel);
                frame.setVisible(true);
                frame.setSize(50 * m[1].length + frame.getInsets().left + frame.getInsets().right , 50 * m.length + frame.getInsets().top + frame.getInsets().bottom);
            }
            public static void displayMatrixJFrame(double [][] m){
                displayMatrixJFrame(m, new Font(DEFAULT_FONT, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE), DEFAULT_BACKGROUND_COLOR, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(double [][] m, Font font){
                displayMatrixJFrame(m, font, DEFAULT_BACKGROUND_COLOR, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(double [][] m, Font font, Color backgroundColor){
                displayMatrixJFrame(m, font, backgroundColor, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(double [][] m, Font font, Color backgroundColor, Color fontColor){
                displayMatrixJFrame(m, font, backgroundColor, fontColor, DEFAULT_BORDER);
            }

        }
        public static class Generation{
            public static double [][] randomMatrix(int rows, int cols, double min, double max){
                double [][] matrix = new double[rows][cols];
                for(int r = 0; r < rows; r++){
                    for(int c = 0; c < cols; c++){
                        matrix[r][c] = (Math.random() * (max - min) + min);
                    }
                }
                return matrix;
            }
            public static double [][] randomMatrix(int rows, int cols){
                return randomMatrix(rows, cols, java.lang.Double.MIN_VALUE, java.lang.Double.MAX_VALUE);
            }
            public static double [][] filledMatrix(int rows, int cols, double value){
                double [][] matrix = new double[rows][cols];
                for(int r = 0; r < rows; r++){
                    for(int c = 0; c < cols; c++){
                        matrix[r][c] = value;
                    }
                }
                return matrix;
            }
        }
        public static class Manipulation{
            public static void fillRectangle(double [][] m, int r1, int c1, int r2, int c2, double value){
                for(int r = r1; r <= r2; r++){
                    for(int c = c1; c <= c2; c++){
                        m[r][c] = value;
                    }
                }
            }
            public static void fillRectangle(double [][] m, int r1, int c1, int r2, int c2){
                fillRectangle(m, r1, c1, r2, c2, 0);
            }
            public static void clearMatrix(double [][] m){
                fillRectangle(m, 0, 0, m.length - 1, m[0].length - 1);
            }
            public static void fillRow(double [][] m, int r, double value){
                fillRectangle(m, r, 0, r, m[0].length - 1, value);
            }
            public static void fillRow(double [][] m, int r){
                fillRow(m, r, 0);
            }
            public static void fillColumn(double [][] m, int c, double value){
                fillRectangle(m, 0, c, m.length - 1, c, value);
            }
            public static void fillColumn(double [][] m, int c){
                fillColumn(m, c, 0);
            }

            public static void fillDiagonal(double [][] m, int startColumn, double value, boolean direction){
                if(direction){
                    for(int r = 0; r < m.length; r++){
                        if(startColumn + r < m[0].length){
                            m[r][startColumn + r] = value;
                        }
                    }
                }
                else {
                    for (int r = 0; r < m.length; r++) {
                        if (startColumn - r >= 0) {
                            m[r][startColumn - r] = value;
                        }
                    }
                }
            }
            public static void fillDiagonal(double [][] m, int startColumn, double value){
                fillDiagonal(m, startColumn, value, true);
            }
        }
    }

    public static class String{
        public static class Display{
            public static void displayMatrixJFrame(java.lang.String[][] m, Font font, Color backgroundColor, Color fontColor, Border border){
                int widestRow = 0;
                for(java.lang.String[] row : m) {
                    if(row.length > widestRow) {
                        widestRow = row.length;
                    }
                }
                Panel MatrixPanel = new Panel();
                MatrixPanel.setBackground(backgroundColor);
                MatrixPanel.setLayout(new GridLayout(m.length, widestRow));
                boolean toggle = true;
                for (java.lang.String[] strings : m) {
                    for (int col = 0; col < widestRow; col++) {
                        JLabel label = new JLabel();
                        label.setSize(DEFAULT_BOX_WIDTH, DEFAULT_BOX_HEIGHT);
                        if (col >= strings.length) {
                            label.setText("");
                        } else {
                            label.setText(strings[col]);
                        }
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setVerticalAlignment(JLabel.CENTER);
                        label.setFont(font);
                        label.setForeground(fontColor);
                        label.setBorder(border);
                        MatrixPanel.add(label);
                    }
                }
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(MatrixPanel);
                frame.setVisible(true);
                frame.setSize(50 * m[1].length + frame.getInsets().left + frame.getInsets().right , 50 * m.length + frame.getInsets().top + frame.getInsets().bottom);
            }
            public static void displayMatrixJFrame(java.lang.String[][] m){
                displayMatrixJFrame(m, new Font(DEFAULT_FONT, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE), DEFAULT_BACKGROUND_COLOR, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(java.lang.String[][] m, Font font){
                displayMatrixJFrame(m, font, DEFAULT_BACKGROUND_COLOR, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(java.lang.String[][] m, Font font, Color backgroundColor){
                displayMatrixJFrame(m, font, backgroundColor, DEFAULT_FONT_COLOR, DEFAULT_BORDER);
            }
            public static void displayMatrixJFrame(java.lang.String[][] m, Font font, Color backgroundColor, Color fontColor){
                displayMatrixJFrame(m, font, backgroundColor, fontColor, DEFAULT_BORDER);
            }
        }
        public static class Generation{
            public static java.lang.String [][] randomMatrix(int rows, int cols, int minLength, int maxLength){
                java.lang.String [][] matrix = new java.lang.String[rows][cols];
                for(int r = 0; r < rows; r++){
                    for(int c = 0; c < cols; c++){
                        matrix[r][c] = randomString(minLength, maxLength);
                    }
                }
                return matrix;
            }
            public static java.lang.String [][] randomMatrix(int rows, int cols){
                return randomMatrix(rows, cols, 1, 10);
            }
            public static java.lang.String [][] filledMatrix(int rows, int cols, java.lang.String value){
                java.lang.String [][] matrix = new java.lang.String[rows][cols];
                for(int r = 0; r < rows; r++){
                    for(int c = 0; c < cols; c++){
                        matrix[r][c] = value;
                    }
                }
                return matrix;
            }
            public static java.lang.String randomString(int minLength, int maxLength){
                StringBuilder s = new StringBuilder();
                int length = (int)(Math.random() * (maxLength - minLength + 1) + minLength);
                for(int i = 0; i < length; i++){
                    s.append((char) ((int) (Math.random() * 26) + 97));
                }
                return s.toString();
            }
        }
        public static class Manipulation{
            public static void fillRectangle(java.lang.String [][] m, int r1, int c1, int r2, int c2, java.lang.String value){
                for(int r = r1; r <= r2; r++){
                    for(int c = c1; c <= c2; c++){
                        m[r][c] = value;
                    }
                }
            }
            public static void fillRectangle(java.lang.String [][] m, int r1, int c1, int r2, int c2){
                fillRectangle(m, r1, c1, r2, c2, "");
            }
            public static void clearMatrix(java.lang.String [][] m){
                fillRectangle(m, 0, 0, m.length - 1, m[0].length - 1);
            }
            public static void fillRow(java.lang.String [][] m, int r, java.lang.String value){
                fillRectangle(m, r, 0, r, m[0].length - 1, value);
            }
            public static void fillRow(java.lang.String [][] m, int r){
                fillRow(m, r, "");
            }
            public static void fillColumn(java.lang.String [][] m, int c, java.lang.String value){
                fillRectangle(m, 0, c, m.length - 1, c, value);
            }
            public static void fillColumn(java.lang.String [][] m, int c){
                fillColumn(m, c, "");
            }

            public static void fillDiagonal(java.lang.String [][] m, int startColumn, java.lang.String value, boolean direction){
                if(direction){
                    for(int r = 0; r < m.length; r++){
                        if(startColumn + r < m[0].length){
                            m[r][startColumn + r] = value;
                        }
                    }
                }
                else {
                    for (int r = 0; r < m.length; r++) {
                        if (startColumn - r >= 0) {
                            m[r][startColumn - r] = value;
                        }
                    }
                }
            }
            public static void fillDiagonal(java.lang.String [][] m, int startColumn, java.lang.String value){
                fillDiagonal(m, startColumn, value, true);
            }
        }
    }


}
