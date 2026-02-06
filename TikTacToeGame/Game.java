
import java.util.Scanner;

enum SYMBOL{
    X, O, EMPTY
} 
 

enum GameResult {
    WIN,
    TIE,
    IN_PROGRESS
}


interface PlayerStrategy {
    void makeMove(Board board,SYMBOL move);
}

// class AiPlayerStrategy implements PlayerStrategy {
//     @Override
//     public void makeMove(Board board,MOVE move  ) {
//         // Implement AI strategy for playing the game
//     } 
// }

class HumanPlayerStrategy implements PlayerStrategy {

    private final Scanner scanner;
    private final String playerName;

    public HumanPlayerStrategy(String playerName) {
        this.scanner = new Scanner(System.in);
        this.playerName = playerName;
        
    }

    @Override
    public void makeMove(Board board,SYMBOL symbol) {
        while(true){
            System.out.println(playerName + ", enter your move (row and column): ");
            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if(board.isValidMove(row, col)){
                    board.placeMove(row, col, symbol);
                    return;
                }
                System.out.println("Invalid move. Try again.");

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numbers for row and column.");
                scanner.nextLine();
            }
        }
    }
}

class Player {
    private final SYMBOL symbol  ;
    private final PlayerStrategy strategy;

    public Player(SYMBOL symbol, PlayerStrategy strategy) {
        this.symbol = symbol;
        this.strategy = strategy;
    }
    public SYMBOL getSymbol() {
        return symbol;
    }
    public void makeMove(Board board) {
        strategy.makeMove(board,symbol);
    }
}

interface GameState {
    void next(GameContext context,Player player,boolean hasWon,boolean isTie);
    boolean isGameOver();
    String getResultMessage();
}



class OTurnState implements GameState {
    @Override
    public void next(GameContext context,Player player,boolean hasWon,boolean isTie) {
        // Transition to O's turn
        if (hasWon) {
            context.setState(player.getSymbol() == SYMBOL.X ? new XWonState():new OWonState());
        } else if (isTie) {
            context.setState(new TieState());
        } else {
            context.setState(new XTurnState());
        }
    }
    @Override
    public boolean isGameOver() {
        // Check if the game is over
        return false;
    }
    @Override
    public String getResultMessage(){
        return "Turn : Player X =>";
    }
}
class XWonState implements GameState {
    @Override
    public void next(GameContext context,Player player,boolean hasWon,boolean isTie) {
    }
    @Override
    public boolean isGameOver() {
        // Check if the game is over
        return true;
    }
    @Override
    public String getResultMessage(){
        return "Player X won!";
    }
}
class OWonState implements GameState {
    @Override
    public void next(GameContext context,Player player,boolean hasWon,boolean isTie) {
        // Transition to O's turn
    }

    @Override
    public boolean isGameOver() {
        // Check if the game is over
        return true;
    }
    @Override
    public String getResultMessage(){
        return "Player O won!";
    }
}
class TieState implements GameState {
    @Override
    public void next(GameContext context,Player player,boolean hasWon,boolean isTie) {
        // Transition to O's turn
    }

    @Override
    public boolean isGameOver() {
        // Check if the game is over
        return true;
    }
    @Override
    public String getResultMessage(){
        return "The game is a tie!";
    }
}
class XTurnState implements GameState {
    @Override
    public void next(GameContext context,Player player,boolean hasWon,boolean isTie) {
        // Transition to O's turn
        if (hasWon) {
            context.setState(player.getSymbol() == SYMBOL.X ? new XWonState():new OWonState());
        } else if (isTie) {
            context.setState(new TieState());
        } else {
            context.setState(new OTurnState());
        }
    }

    @Override
    public boolean isGameOver() {
        // Check if the game is over
        return false;
    }
    @Override
    public String getResultMessage(){
        return "Turn : Player O =>";
    }
}

class GameContext {
    private GameState currentState;

    public GameContext() {
        currentState = new XTurnState(); 
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public GameState getState() {
        return currentState;
    }

    public void next(Player player,boolean hasWon,boolean isTie) {
        currentState.next(this,player,hasWon,isTie);
    }

    public boolean isGameOver() {
        return currentState.isGameOver();
    }
}


class Board {
    private final int size; 
    private final SYMBOL[][] board;

    public Board(int size) {
        this.size = size; 
        board = new SYMBOL[size][size];
        //init board with empty moves
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = SYMBOL.EMPTY;
            }
        }   
        System.out.println("Initial Board:");
    }
    public boolean isValidMove( int row, int col) {
        if(row < 0 || row >= size || col < 0 || col >= size || board[row][col] != SYMBOL.EMPTY){
            return false;
        }
        return true;
    }
    public void placeMove(int row, int col,SYMBOL symbol) {
        board[row][col] = symbol;
    }


    public GameResult checkState() {
        // rows
        for (int i = 0; i < size; i++) {
            if (board[i][0] != SYMBOL.EMPTY && isWinningLine(board[i])) {
                return GameResult.WIN;
            }
        }

        // columns
        for (int col = 0; col < size; col++) {
            SYMBOL first = board[0][col];
            if (first == SYMBOL.EMPTY) continue;

            boolean win = true;
            for (int row = 1; row < size; row++) {
                if (board[row][col] != first) {
                    win = false;
                    break;
                }
            }
            if (win) return GameResult.WIN;
        }

        // main diagonal
        SYMBOL firstDiagonal = board[0][0];
        if (firstDiagonal != SYMBOL.EMPTY) {
            boolean win = true;
            for (int i = 1; i < size; i++) {
                if (board[i][i] != firstDiagonal) {
                    win = false;
                    break;
                }
            }
            if (win) return GameResult.WIN;
        }

        // anti-diagonal
        SYMBOL firstAnti = board[0][size - 1];
        if (firstAnti != SYMBOL.EMPTY) {
            boolean win = true;
            for (int i = 1; i < size; i++) {
                if (board[i][size - 1 - i] != firstAnti) {
                    win = false;
                    break;
                }
            }
            if (win) return GameResult.WIN;
        }

        // check tie
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == SYMBOL.EMPTY) {
                    return GameResult.IN_PROGRESS;
                }
            }
        }
        return GameResult.TIE;
    }
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                SYMBOL symbol = board[i][j];   
                switch(symbol){
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    default:
                        System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
    private boolean isWinningLine(SYMBOL[] line) {
        SYMBOL first = line[0];
        for (SYMBOL symbol : line) {
            if (symbol != first) return false;
        }
        return true;
    }

}

interface BoardGames {
    void play();
}

class TikTacToe implements BoardGames {
    private int size; 
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private Board board; 
    private GameContext gameContext;

    public TikTacToe(int size,PlayerStrategy xStrategy, PlayerStrategy oStrategy) {
        this.size = size;
        board = new Board(size);
        playerX = new Player(SYMBOL.X, xStrategy);
        playerO = new Player(SYMBOL.O,oStrategy);
        currentPlayer = playerX;
        gameContext = new GameContext();
    }

    private void announceResult(GameContext context) {
        //commented part overengineered as state should control behaviour and not just
        // for knowing states if else if else block bulding  so added new getResultMessage()
//        if (context.getState() instanceof XWonState) {
//            System.out.println("Player X wins!");
//        } else if (context.getState() instanceof OWonState) {
//            System.out.println("Player O wins!");
//        } else if (context.getState() instanceof TieState) {
//            System.out.println("The game is a tie!");
//        }
        context.getState().getResultMessage();
    }
    private void switchPlayer(){
        currentPlayer = (currentPlayer.getSymbol() == SYMBOL.X) ? playerO : playerX;
    }

    @Override
    public void play() {
        do{
            board.displayBoard();
            currentPlayer.makeMove(board);
//            board.placeMove(size, size, currentPlayer.getSymbol());  board.placeMove(row, col, symbol);
            GameResult result = board.checkState();
            boolean hasWon = (result == GameResult.WIN);
            boolean isTie = (result == GameResult.TIE);
            gameContext.next(currentPlayer, hasWon, isTie);
            switchPlayer();
        }while(!gameContext.isGameOver());
        announceResult(gameContext);
    }
}

public class Game { 
    public static void main(String[] args) {
        PlayerStrategy playerXStrategy = new HumanPlayerStrategy("Player X");
        PlayerStrategy playerOStrategy = new HumanPlayerStrategy("Player O");
        BoardGames ticTacToe = new TikTacToe(3,playerXStrategy, playerOStrategy);
        ticTacToe.play();
    }
}