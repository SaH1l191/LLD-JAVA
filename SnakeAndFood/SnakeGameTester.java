
import java.util.*;

abstract class FoodItem {

    private int row;
    private int col;
    private int scoreValue;

    public FoodItem(int row, int col, int scoreValue) {
        this.row = row;
        this.col = col;
        this.scoreValue = scoreValue;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}

class NormalFood extends FoodItem {

    public NormalFood(int row, int col) {
        super(row, col, 1);
    }
}

class BonusFood extends FoodItem {

    public BonusFood(int row, int col) {
        super(row, col, 2);
    }
}

class FoodFactory {

    public static FoodItem createFood(Pair<Integer, Integer> position, String type) {
        int row = position.getKey();
        int col = position.getValue();
        switch (type) {
            case "Normal":
                return new NormalFood(row, col);
            case "Bonus":
                return new BonusFood(row, col);
            default:
                throw new IllegalArgumentException("Unknown food type: " + type);
        }
    }
}

class GameBoard {

    private int width;
    private int height;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public static GameBoard instance;

    public static GameBoard getInstance(int width, int height) {
        if (instance == null) {
            instance = new GameBoard(width, height);
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

interface MovementStratergy {

    public Pair<Integer, Integer> getNextMove(Pair<Integer, Integer> currentPos, String Direction);
}

class HumanPlayerStratergy implements MovementStratergy {

    @Override
    public Pair<Integer, Integer> getNextMove(Pair<Integer, Integer> currentPos, String Direction) {
        int row = currentPos.getKey();
        int col = currentPos.getValue();
        switch (Direction) {
            case "W":
                return new Pair<>(row - 1, col);
            case "S":
                return new Pair<>(row + 1, col);
            case "A":
                return new Pair<>(row, col - 1);
            case "D":
                return new Pair<>(row, col + 1);
            default:
                return currentPos;
        }
    }
}

class AIPlayerStratergy implements MovementStratergy {

    @Override
    public Pair<Integer, Integer> getNextMove(Pair<Integer, Integer> currentPos, String Direction) {
        // Implement AI logic 
        return currentPos;
    }
}

class SnakeGame {

    private GameBoard board;
    private MovementStratergy movementStratergy;
    private FoodFactory foodFactory; //createFood(pair<int,int>pos,type)
    private int score;
    private Deque<Pair<Integer, Integer>> snake;
    private Map<Pair<Integer, Integer>, Boolean> snakeMap;
    private FoodItem[] foodItemList;
    private int foodIndex;

    public SnakeGame(int width, int height, FoodItem[] foodItemList) {
        foodFactory = new FoodFactory();
        board = GameBoard.getInstance(width, height);

        //initialize snake
        //offerFirst() → adds to head
        // pollLast() → removes tail
        this.foodItemList = foodItemList;
        snake = new LinkedList<>();
        Pair<Integer, Integer> initPos = new Pair<>(0, 0);
        snake.offerFirst(initPos);
        snakeMap = new HashMap<>();
        snakeMap.put(initPos, true);
        this.foodIndex = 0;
    }

    public void setMovementStratergy(MovementStratergy movementStratergy) {
        this.movementStratergy = movementStratergy;
    }

    public int getSnakeLength() {
        return snake.size();
    }

    public int getScore() {
        return score;
    }

    public int move(String direction) {
        Pair<Integer, Integer> currentHead = this.snake.peekFirst();

        Pair<Integer, Integer> newHead = this.movementStratergy.getNextMove(currentHead, direction);
        int newHeadRow = newHead.getKey();
        int newHeadCol = newHead.getValue();

        boolean isOutOfBounds = newHeadRow < 0 || newHeadRow >= board.getHeight() || newHeadCol < 0 || newHeadCol >= board.getWidth();

        //if snake collides with its body and it is not the current tail (since tail will move) 
        Pair<Integer, Integer> currentTail = this.snake.peekLast();
        boolean isCollideWithBody = this.snakeMap.containsKey(newHead)
                && !(newHead.getKey() == currentTail.getKey() && newHead.getValue() == currentTail.getValue());

        if (isOutOfBounds || isCollideWithBody) {
            this.score = -1;
            return -1;
        }

        boolean ateFood = (this.foodIndex < this.foodItemList.length)
                && (this.foodItemList[this.foodIndex].getRow() == newHeadRow)
                && (this.foodItemList[this.foodIndex].getCol() == newHeadCol);
        if (ateFood) {
            this.score += this.foodItemList[this.foodIndex].getScoreValue();
            this.foodIndex += 1;
        } else {
            //snake didnt eat food , remove tail 
            Pair<Integer, Integer> tail = this.snake.pollLast();
            this.snakeMap.remove(tail);
        }
        this.snake.addFirst(newHead);
        this.snakeMap.put(newHead, true);
        return this.score;
    }
}

public class SnakeGameTester {

    public static void main(String[] args) {
        testMoveWithoutFood();
        testEatNormalFood();
        testEatBonusFood();
        testWallCollision();
        testSelfCollision();
        testSnakeGrowth();
        testFoodSkipping();
    }

    static void assertEquals(String name, int exp, int got) {
        System.out.println(exp == got ? "[PASS] " + name : "[FAIL] " + name + " - Expected: " + exp + ", Got: " + got);
    }

    static void testMoveWithoutFood() {
        FoodItem[] food = {};
        SnakeGame g = new SnakeGame(5, 5, food);
        g.setMovementStratergy(new HumanPlayerStratergy());
        assertEquals("Move right without food", 0, g.move("D"));
        assertEquals("Move down without food", 0, g.move("S"));
    }

    static void testEatNormalFood() {
        FoodFactory f = new FoodFactory();
        FoodItem[] food = {f.createFood(new Pair<>(0, 1), "Normal")};
        SnakeGame g = new SnakeGame(5, 5, food);
        g.setMovementStratergy(new HumanPlayerStratergy());
        assertEquals("Eat normal food", 1, g.move("D"));
    }

    static void testEatBonusFood() {
        FoodFactory f = new FoodFactory();
        FoodItem[] food = {f.createFood(new Pair<>(0, 1), "Bonus")};
        SnakeGame g = new SnakeGame(5, 5, food);
        g.setMovementStratergy(new HumanPlayerStratergy());
        assertEquals("Eat bonus food", 2, g.move("D"));
    }

    static void testWallCollision() {
        FoodItem[] food = {};
        SnakeGame g = new SnakeGame(2, 2, food);
        g.setMovementStratergy(new HumanPlayerStratergy());
        assertEquals("Hit left wall", -1, g.move("A"));
        assertEquals("Hit top wall", -1, g.move("W"));
    }

    static void testSelfCollision() {
        FoodFactory f = new FoodFactory();
        FoodItem[] food = {
            f.createFood(new Pair<>(0, 1), "Normal"),
            f.createFood(new Pair<>(0, 2), "Normal"),
            f.createFood(new Pair<>(1, 2), "Normal")
        };
        SnakeGame g = new SnakeGame(5, 5, food);
        g.setMovementStratergy(new HumanPlayerStratergy());
        g.move("D"); // eats (0,1), score 1, length 2
        g.move("D"); // eats (0,2), score 2, length 3
        g.move("S");
        int res = g.move("W"); // move to (0,1) → collides
        assertEquals("Self collision", -1, res);
    }

    static void testSnakeGrowth() {
        FoodFactory f = new FoodFactory();
        FoodItem[] food = {f.createFood(new Pair<>(0, 1), "Normal")};
        SnakeGame g = new SnakeGame(5, 5, food);
        g.setMovementStratergy(new HumanPlayerStratergy());
        g.move("D");
        assertEquals("Snake length after eating", 2, g.getSnakeLength());
    }

    static void testFoodSkipping() {
        FoodFactory f = new FoodFactory();
        FoodItem[] food = {f.createFood(new Pair<>(0, 2), "Normal")};
        SnakeGame g = new SnakeGame(5, 5, food);
        g.setMovementStratergy(new HumanPlayerStratergy());
        g.move("D");
        assertEquals("Score after skipping food", 0, g.getScore());
        assertEquals("Snake length after skipping food", 1, g.getSnakeLength());
    }
}

class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return key.equals(pair.key) && value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return key.hashCode() * 31 + value.hashCode();
    }

    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
}
