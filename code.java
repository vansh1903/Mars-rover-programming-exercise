import java.util.HashSet;  
import java.util.Set;  
// Enum representing the cardinal directions  
enum Direction {  
    N, E, S, W;  
    // Method to turn left  
    public Direction turnLeft() {  
        return values()[(ordinal() + 3) % 4];  
    }  
    // Method to turn right  
    public Direction turnRight() {  
        return values()[(ordinal() + 1) % 4];  
    }  
}  
public class MarsRover {  
    private int x;  
    private int y;  
    private Direction direction;  
    private Set<String> obstacles; // Set to store the positions of obstacles  
    // Constructor  
    public MarsRover(int x, int y, Direction direction) {  
        this.x = x;  
        this.y = y;  
        this.direction = direction;  
        this.obstacles = new HashSet<>();  
    }  
    // Method to rotate the rover  
    public void rotate(char rotation) {  
        if (rotation == 'L') {  
            direction = direction.turnLeft();  
        } else if (rotation == 'R') {  
            direction = direction.turnRight();  
        }  
    }  
    // Method to move the rover  
    public void move() {  
        int nextX = x;  
        int nextY = y;  
        switch (direction) {  
            case N:  
                nextY++;  
                break;  
            case E:  
                nextX++;  
                break;  
            case S:  
                nextY--;  
                break;  
            case W:  
                nextX--;  
                break;  
        }  
        // Check for obstacles and boundary conditions  
        String nextPosition = nextX + ":" + nextY;  
        if (!obstacles.contains(nextPosition) && isValidPosition(nextX, nextY)) {  
            x = nextX;  
            y = nextY;  
        }  
    }  
    // Method to check if a position is valid within the grid  
    private boolean isValidPosition(int x, int y) {  
        int maxX = 10; // Maximum X-coordinate of the grid  
        int maxY = 10; // Maximum Y-coordinate of the grid  
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;  
    }  
    // Method to add an obstacle to the grid  
    public void addObstacle(int x, int y) {  
        obstacles.add(x + ":" + y);  
    }  
  
    // Method to get the current position of the rover  
    public String getPosition() {  
        return x + ":" + y + " (" + direction + ")";  
    }  
    public static void main(String[] args) {  
        // Create a MarsRover object  
        MarsRover rover = new MarsRover(0, 0, Direction.N);  
        // Add obstacles to the grid  
        rover.addObstacle(2, 2);  
        rover.addObstacle(4, 6);  
        // Execute a series of commands  
        String commands = "LMLMLMLMM";  
        for (char command : commands.toCharArray()) {  
            if (command == 'L' || command == 'R') {  
                rover.rotate(command);  
            } else if (command == 'M') {  
                rover.move();  
            }  
        }  
        // Print the final position of the rover  
        System.out.println("Final position: " + rover.getPosition());  
    }  
}  
