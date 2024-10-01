public class Location {
    int row;
    int col;
    int value;
    boolean multiplePaths;

    public Location(int row, int col, boolean multiplePaths, int value) {
        this.row = row;
        this.col = col;
        this.multiplePaths = multiplePaths;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasMultiplePaths() {
        return multiplePaths;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }

}
