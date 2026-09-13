# Text Adventure Game

A terminal-based text adventure game written in Java. Players navigate through rooms, inspect objects, collect items, combine equipment, solve puzzles, and interact with various hazards and containers.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Compilation](#compilation)
- [Running the Game](#running-the-game)
- [Gameplay & Commands](#gameplay--commands)
- [Game State Configuration](#game-state-configuration)

---

## Prerequisites

- **Java Development Kit (JDK)**: Java 8 or higher (JDK 11 or higher recommended).
- Ensure `javac` and `java` commands are accessible in your terminal or command prompt.

---

## Project Structure

```text
.
├── src/
│   └── org/
│       └── uob/
│           └── a2/
│               ├── Game.java                   # Main entry point for the application
│               ├── game.txt                    # Default game state data file
│               ├── commands/                   # Command implementations (Move, Get, Use, etc.)
│               ├── gameobjects/                # Game models (Player, Room, Item, Equipment, etc.)
│               ├── parser/                     # Input tokeniser and parser
│               └── utils/                      # File parser utilities for game setup
└── README.md
```

---

## Compilation

To compile all Java source files into a `bin` directory, run the following command from the root directory of the project:

### Linux / macOS / Bash:
```bash
javac -d bin $(find src -name "*.java")
```

### Windows (Command Prompt / PowerShell):
```cmd
mkdir bin
javac -d bin src/org/uob/a2/Game.java src/org/uob/a2/commands/*.java src/org/uob/a2/gameobjects/*.java src/org/uob/a2/parser/*.java src/org/uob/a2/utils/*.java
```

---

## Running the Game

After compilation, run the game using `java` with the class path set to the `bin` directory and passing the game text file configuration (`src/org/uob/a2/game.txt`) as an argument:

```bash
java -cp bin org.uob.a2.Game src/org/uob/a2/game.txt
```

> **Note:** If no argument is supplied, ensure the game data file path matches the location specified in `Game.java`. Providing `src/org/uob/a2/game.txt` ensures cross-platform compatibility.

---

## Gameplay & Commands

Once the game starts, you will see a command prompt `>>`. Type commands and press **Enter** to interact with the world.

### Available Commands

| Command | Syntax Example | Description |
|---|---|---|
| `MOVE` | `move north`, `move east` | Move to an adjacent room in the specified direction. |
| `LOOK` | `look`, `look chair` | Inspect your current room or a specific object/feature. |
| `GET` | `get water`, `get key` | Pick up an item from the current room and add it to inventory. |
| `DROP` | `drop water` | Drop an item from inventory into the current room. |
| `USE` | `use pacifier on chair`, `use key` | Use an item or piece of equipment on a target object. |
| `COMBINE` | `combine wood and steel` | Combine two items from inventory into new equipment. |
| `STATUS` | `status`, `status inventory` | View player status and current inventory contents. |
| `HELP` | `help`, `help move` | Display general help or detailed help for a specific command. |
| `QUIT` | `quit` | Exit the game. |

---

## Game State Configuration

The world state is initialized using a text definition file (`game.txt`). The file defines:
- **Player**: Starting player attributes.
- **Rooms**: Room ID, name, description, hidden status.
- **Exits**: Exit ID, direction, description, target room ID, hidden status.
- **Items & Equipment**: Items that can be collected or combined, equipment with actions and target interactions.
- **Containers & Hazards**: Objects that contain items or block pathways until specific conditions are met.
