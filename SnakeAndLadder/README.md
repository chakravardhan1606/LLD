# Snake and Ladder LLD

Multi-player Snake and Ladder game played on an `n x n` grid. Players take turns
rolling a die and advancing their token; landing on a snake sends the player
down, landing on a ladder sends the player up. First player to reach the last
cell (exact roll required) wins.

## Package Structure

```
SnakeAndLadder/
├── Main.java                # Entry point - sets up players and starts the game
├── SnakeAndLadder.java       # Game controller - turn loop, win detection
├── manager/
│   └── Board.java            # Owns board state: snakes, ladders, player positions
└── model/
    ├── Color.java             # Player color/token enum
    ├── Player.java             # Player identity + dice roll behavior
    ├── Position.java           # Base cell (row, col) on the board
    ├── SnakePosition.java      # Position representing a snake head -> tail
    └── LadderPosition.java     # Position representing a ladder start -> end
```

## Class Diagram

![Snake and Ladder Class Diagram](class-diagram.png)

<details>
<summary>Mermaid source</summary>

```mermaid
classDiagram
    class Main {
        +main(String[] args) void
    }

    class SnakeAndLadder {
        -Board board
        -Queue~Player~ players
        +SnakeAndLadder(List~Player~ playerList, int size)
        -initializeBoard() void
        +play() void
    }

    class Board {
        -int size
        -int totalCells
        -Map~Integer, Integer~ snakes
        -Map~Integer, Integer~ ladders
        -Map~Color, Integer~ playerPositions
        +Board(int size)
        -initializePlayers() void
        +addSnake(int head, int tail) void
        +addLadder(int start, int end) void
        +getPlayerPosition(Color color) int
        +move(Color color, int diceValue) boolean
    }

    class Player {
        -String playerId
        -String playerName
        -Color color
        +Player(String playerId, String playerName, Color color)
        +roll() int
        +getPlayerId() String
        +getPlayerName() String
        +getColor() Color
    }

    class Color {
        <<enumeration>>
        Red
        White
        Blue
        Yellow
    }

    class Position {
        #int row
        #int col
        -Set~Color~ colors
        +Position(int row, int col)
        +getColors() Set~Color~
        +addColors(Color color) void
        +removeColor(Color color) void
    }

    class SnakePosition {
        -Position position
        +SnakePosition(int row, int col, Position position)
        +getPosition() Position
    }

    class LadderPosition {
        -Position position
        +LadderPosition(int row, int col, Position position)
        +getPosition() Position
    }

    Main --> SnakeAndLadder : creates
    Main --> Player : creates
    SnakeAndLadder --> Board : owns
    SnakeAndLadder --> Player : manages queue of
    Board --> Color : keyed by
    Player --> Color : has
    SnakePosition --|> Position : extends
    LadderPosition --|> Position : extends
    Position --> Color : has
```

</details>

## Key Design Points

- **`Board`** (manager) encapsulates all board state and mutation logic
  (snakes, ladders, player positions) so `SnakeAndLadder` only orchestrates
  turns and win detection.
- **`Player`** owns its own dice-roll behavior via `roll()`.
- **`SnakePosition`** / **`LadderPosition`** extend `Position` to model
  special cells on the board distinctly from plain cells.
