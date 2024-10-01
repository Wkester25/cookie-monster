import java.util.Queue;

public class Branch extends Location{
    private Queue<Location> previousLocations;
    int totalValue;
    public Branch(int row, int col, boolean multiplePaths, int value, Queue<Location> previousLocations, int totalValue) {
        super(row, col, multiplePaths, value);
        this.previousLocations = previousLocations;
        this.totalValue = totalValue;
    }

    public Branch(Location location, Queue<Location> previousLocations, int totalValue){
        super(location.getRow(), location.getCol(), location.hasMultiplePaths(), location.getValue());
        this.previousLocations = previousLocations;
        this.totalValue = totalValue;
    }

    public Queue<Location> getPreviousLocations() {
        return previousLocations;
    }

}
