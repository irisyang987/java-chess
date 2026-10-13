package IrisYangChess;

import tester.Tester;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

class Chess extends World {
  static int SIZE = 100;
  static RectangleImage GRAY_SQUARE    = new RectangleImage(SIZE, SIZE, OutlineMode.SOLID  , Color.DARK_GRAY);
  static CircleImage    GREEN_CIRCLE   = new CircleImage   (SIZE/4,     OutlineMode.OUTLINE, Color.GREEN);
  static CircleImage    MAGENTA_CIRCLE = new CircleImage   (SIZE/4,     OutlineMode.OUTLINE, Color.MAGENTA);

  HashMap<MyPosn, Piece> whitePieces;
  HashMap<MyPosn, Piece> blackPieces;
  Optional<Piece>        currentPiece;
  Team                   activeTeam;
  boolean                isGameOver;
  String                 winner;

  Chess() {
    this.whitePieces  = this.generateWhitePieces();
    this.blackPieces  = this.generateBlackPieces();
    this.currentPiece = Optional.empty();
    this.activeTeam   = Team.WHITE;
    this.isGameOver   = false;
    this.winner       = "";
  }

  public HashMap<MyPosn, Piece> generateWhitePieces() {
    HashMap<MyPosn, Piece> newWhitePieces = new HashMap<MyPosn, Piece>();    
    newWhitePieces.put(new MyPosn(4, 7), new King  ("King"  , new MyPosn(4, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKing.png")));
    newWhitePieces.put(new MyPosn(3, 7), new Queen ("Queen" , new MyPosn(3, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteQueen.png")));
    newWhitePieces.put(new MyPosn(2, 7), new Bishop("Bishop", new MyPosn(2, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteBishop.png")));
    newWhitePieces.put(new MyPosn(5, 7), new Bishop("Bishop", new MyPosn(5, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteBishop.png")));
    newWhitePieces.put(new MyPosn(1, 7), new Knight("Knight", new MyPosn(1, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKnight.png")));
    newWhitePieces.put(new MyPosn(6, 7), new Knight("Knight", new MyPosn(6, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKnight.png")));
    newWhitePieces.put(new MyPosn(0, 7), new Rook  ("Rook"  , new MyPosn(0, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteRook.png")));
    newWhitePieces.put(new MyPosn(7, 7), new Rook  ("Rook"  , new MyPosn(7, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteRook.png")));
    newWhitePieces.put(new MyPosn(0, 6), new Pawn  ("Pawn"  , new MyPosn(0, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    newWhitePieces.put(new MyPosn(1, 6), new Pawn  ("Pawn"  , new MyPosn(1, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    newWhitePieces.put(new MyPosn(2, 6), new Pawn  ("Pawn"  , new MyPosn(2, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    newWhitePieces.put(new MyPosn(3, 6), new Pawn  ("Pawn"  , new MyPosn(3, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    newWhitePieces.put(new MyPosn(4, 6), new Pawn  ("Pawn"  , new MyPosn(4, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    newWhitePieces.put(new MyPosn(5, 6), new Pawn  ("Pawn"  , new MyPosn(5, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    newWhitePieces.put(new MyPosn(6, 6), new Pawn  ("Pawn"  , new MyPosn(6, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    newWhitePieces.put(new MyPosn(7, 6), new Pawn  ("Pawn"  , new MyPosn(7, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    return newWhitePieces;
  }

  public HashMap<MyPosn, Piece> generateBlackPieces() {
    HashMap<MyPosn, Piece> newBlackPieces = new HashMap<MyPosn, Piece>();
    newBlackPieces.put(new MyPosn(4, 0), new King  ("King"  , new MyPosn(4, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackKing.png")));
    newBlackPieces.put(new MyPosn(3, 0), new Queen ("Queen" , new MyPosn(3, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackQueen.png")));
    newBlackPieces.put(new MyPosn(2, 0), new Bishop("Bishop", new MyPosn(2, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackBishop.png")));
    newBlackPieces.put(new MyPosn(5, 0), new Bishop("Bishop", new MyPosn(5, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackBishop.png")));
    newBlackPieces.put(new MyPosn(1, 0), new Knight("Knight", new MyPosn(1, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackKnight.png")));
    newBlackPieces.put(new MyPosn(6, 0), new Knight("Knight", new MyPosn(6, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackKnight.png")));
    newBlackPieces.put(new MyPosn(0, 0), new Rook  ("Rook"  , new MyPosn(0, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackRook.png")));
    newBlackPieces.put(new MyPosn(7, 0), new Rook  ("Rook"  , new MyPosn(7, 0), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackRook.png")));
    newBlackPieces.put(new MyPosn(0, 1), new Pawn  ("Pawn"  , new MyPosn(0, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    newBlackPieces.put(new MyPosn(1, 1), new Pawn  ("Pawn"  , new MyPosn(1, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    newBlackPieces.put(new MyPosn(2, 1), new Pawn  ("Pawn"  , new MyPosn(2, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    newBlackPieces.put(new MyPosn(3, 1), new Pawn  ("Pawn"  , new MyPosn(3, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    newBlackPieces.put(new MyPosn(4, 1), new Pawn  ("Pawn"  , new MyPosn(4, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    newBlackPieces.put(new MyPosn(5, 1), new Pawn  ("Pawn"  , new MyPosn(5, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    newBlackPieces.put(new MyPosn(6, 1), new Pawn  ("Pawn"  , new MyPosn(6, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    newBlackPieces.put(new MyPosn(7, 1), new Pawn  ("Pawn"  , new MyPosn(7, 1), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackPawn.png")));
    return newBlackPieces;
  }

  public WorldEnd worldEnds() {
    if (this.isGameOver) {
      return new WorldEnd(true, this.displayWinner(this.makeScene()));
    } else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  public WorldScene displayWinner(WorldScene scene) {
    WorldScene newScene = scene;
    new MyPosn(8 * SIZE/2, 8 * SIZE/2).placeMe(new TextImage(this.winner + " wins", SIZE, FontStyle.BOLD, Color.RED), newScene, 1);
    return newScene;
  }

  public void onMouseClicked(Posn posn) {
    MyPosn newPosn = new MyPosn(
        (int) Math.round((double) (posn.x - SIZE/2)/SIZE),
        (int) Math.round((double) (posn.y - SIZE/2)/SIZE));
    if (this.currentPiece.isPresent() && this.currentPiece.get().possibleMoves(this.whitePieces, this.blackPieces).contains(newPosn)) {
      if (this.activeTeam == Team.WHITE) {
        this.movePiece(this.whitePieces, newPosn);
        this.capture(blackPieces, newPosn, "White");
      } else if (this.activeTeam == Team.BLACK) {
        this.movePiece(this.blackPieces, newPosn);
        this.capture(whitePieces, newPosn, "Black");
      }
    }
    if (this.activeTeam == Team.WHITE && this.whitePieces.containsKey(newPosn)) {
      this.currentPiece = Optional.of(this.whitePieces.get(newPosn));
    }
    if (this.activeTeam == Team.BLACK && this.blackPieces.containsKey(newPosn)) {
      this.currentPiece = Optional.of(this.blackPieces.get(newPosn));
    }
  }

  public void movePiece(HashMap<MyPosn, Piece> pieces, MyPosn newPosn) {
    pieces.remove(this.currentPiece.get().position);
    this.currentPiece.get().position = newPosn;
    pieces.put(newPosn, this.currentPiece.get());
    if (this.activeTeam == Team.WHITE) {
      this.activeTeam = Team.BLACK;
    } else {
      this.activeTeam = Team.WHITE;
    }
    this.promotion();
    this.currentPiece = Optional.empty();
  }

  public void capture(HashMap<MyPosn, Piece> pieces, MyPosn newPosn, String winner) {
    if (pieces.containsKey(newPosn)) {
      if (pieces.get(newPosn).name.equals("King")) {
        pieces.remove(newPosn);
        this.winner = winner;
        this.isGameOver = true;
      } else {
        pieces.remove(newPosn);
      }
    }
  }

  public void promotion() {
    if (this.currentPiece != null && this.currentPiece.get().name.equals("Pawn")) {
      MyPosn posn1 = new MyPosn(this.currentPiece.get().position.x, this.currentPiece.get().position.y);
      if (this.currentPiece.get().team == 0 && this.currentPiece.get().position.y == 0) {
        this.whitePieces.put(posn1, new Queen("Queen", posn1, 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteQueen.png")));
      } else if (this.currentPiece.get().team == 1 && this.currentPiece.get().position.y == 7) {
        this.blackPieces.put(posn1, new Queen("Queen", posn1, 1, true, new FromFileImage("src/IrisYangChess/pieces/blackQueen.png")));
      }
    }
  }

  public void onKeyEvent(String key) {
    if (this.currentPiece.isPresent() && key.equals("escape")) {
      this.currentPiece = Optional.empty();
    }
  }

  public WorldScene makeScene() {
    WorldScene emptyScene = this.getEmptyScene();
    return this.drawPieces(this.emptyBoard(emptyScene));
  }

  public WorldScene drawPieces(WorldScene grid) {
    WorldScene newScene = grid;
    for (HashMap.Entry<MyPosn, Piece> entry : this.whitePieces.entrySet()) {
      MyPosn entryKey = this.modifyPosn(entry.getKey());
      Piece entryVal = entry.getValue();
      entryKey.placeMe(entryVal.image, newScene, 1);
    }
    for (HashMap.Entry<MyPosn, Piece> entry : this.blackPieces.entrySet()) {
      MyPosn entryKey = this.modifyPosn(entry.getKey());
      Piece entryVal = entry.getValue();
      entryKey.placeMe(entryVal.image, newScene, 1);
    }
    if (this.currentPiece.isPresent()) {
      this.modifyPosn(this.currentPiece.get().position).placeMe(GREEN_CIRCLE, newScene, 1);
      for (MyPosn posn : this.currentPiece.get().possibleMoves(this.whitePieces, this.blackPieces)) {
        this.modifyPosn(posn).placeMe(MAGENTA_CIRCLE, newScene, 1);
      }
    }
    return newScene;
  }

  public MyPosn modifyPosn(MyPosn posn) {
    return new MyPosn(posn.x * SIZE + SIZE/2, posn.y * SIZE + SIZE/2);
  }

  public WorldScene emptyBoard(WorldScene emptyScene) {
    WorldScene newScene = emptyScene;    
    for (int currHeight = 0; currHeight < 8; currHeight += 2) {
      for (int currWidth = 1; currWidth < 8; currWidth += 2) {
        new MyPosn(
            currWidth  * SIZE + SIZE/2,
            currHeight * SIZE + SIZE/2)
        .placeMe(GRAY_SQUARE, newScene, 1);
      }
    }
    for (int currHeight = 1; currHeight < 8; currHeight += 2) {
      for (int currWidth = 0; currWidth < 8; currWidth += 2) {
        new MyPosn(
            currWidth  * SIZE + SIZE/2,
            currHeight * SIZE + SIZE/2)
        .placeMe(GRAY_SQUARE, newScene, 1);
      }
    }
    return newScene;
  }

  void runChess() {
    this.bigBang(SIZE * 8, SIZE * 8, 0.1);
  }
  
  public static void main(String[] args) {
    Chess game = new Chess();
    game.runChess();
  }
}

abstract class Piece {
  String     name;
  MyPosn     position;
  int        team;
  boolean    state;
  WorldImage image;

  Piece(String name, MyPosn position, int team, boolean state, WorldImage image) {
    this.name     = name;
    this.position = position;
    this.team     = team;
    this.state    = state;
    this.image    = image;
  }

  abstract ArrayList<MyPosn> possibleMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces);

  public ArrayList<MyPosn> move(ArrayList<MyPosn> moves, HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces, String direction) {
    ArrayList<MyPosn> newMoves = moves;
    int step = 1;
    while (step < 8) {
      MyPosn posn = new MyPosn(0, 0);
      if (direction.equals("right")) { // TODO: "right" does not mean something, but Posn(1, 0) does
        posn = new MyPosn(position.x + step, position.y);
      } else if (direction.equals("left")) {
        posn = new MyPosn(position.x - step, position.y);
      } else if (direction.equals("up")) {
        posn = new MyPosn(position.x       , position.y - step);
      } else if (direction.equals("down")) {
        posn = new MyPosn(position.x       , position.y + step);
      } else if (direction.equals("upright")) {
        posn = new MyPosn(position.x + step, position.y - step);
      } else if (direction.equals("upleft")) {
        posn = new MyPosn(position.x - step, position.y - step);
      } else if (direction.equals("downright")) {
        posn = new MyPosn(position.x + step, position.y + step);
      } else if (direction.equals("downleft")) {
        posn = new MyPosn(position.x - step, position.y + step);
      }
      if (((this.team == 0) && (whitePieces.containsKey(posn))) ||
          ((this.team == 1) && (blackPieces.containsKey(posn)))) {
        step = 8;
      } else if (((this.team == 0) && (blackPieces.containsKey(posn))) ||
          ((this.team == 1) && (whitePieces.containsKey(posn)))) {
        newMoves.add(posn);
        step = 8;
      } else {
        newMoves.add(posn);
        step++;
      }
    }
    return newMoves;
  }

  public ArrayList<MyPosn> allPawnMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    ArrayList<MyPosn> possibleMoves = this.possibleMoves(whitePieces, blackPieces);
    if (this.team == 0) {
      MyPosn upright = new MyPosn(position.x + 1, position.y - 1);
      MyPosn upleft = new MyPosn(position.x - 1, position.y - 1);
      if (!(possibleMoves.contains(upright))) {
        possibleMoves.add(upright);
      }
      if (!(possibleMoves.contains(upleft))) {
        possibleMoves.add(upleft);
      }
    } else if (this.team == 1) {
      MyPosn downright = new MyPosn(position.x + 1, position.y + 1);
      MyPosn downleft  = new MyPosn(position.x - 1, position.y + 1);
      if (!(possibleMoves.contains(downright))) {
        possibleMoves.add(downright);
      }
      if (!(possibleMoves.contains(downleft))) {
        possibleMoves.add(downleft);
      }
    }
    return possibleMoves;
  }

  public ArrayList<MyPosn> allKingMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    return new ArrayList<MyPosn>(List.of(
        new MyPosn(position.x + 1, position.y    ),
        new MyPosn(position.x - 1, position.y    ),
        new MyPosn(position.x    , position.y + 1),
        new MyPosn(position.x    , position.y - 1),
        new MyPosn(position.x + 1, position.y + 1),
        new MyPosn(position.x - 1, position.y - 1),
        new MyPosn(position.x + 1, position.y - 1),
        new MyPosn(position.x - 1, position.y + 1)));
  }
}

enum Team {
  WHITE, BLACK;
}

class King extends Piece {

  King(String name, MyPosn position, int team, boolean state, WorldImage image) {
    super(name, position, team, state, image);
  }

  public ArrayList<MyPosn> possibleMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    ArrayList<MyPosn> moves = new ArrayList<MyPosn>(List.of(
        new MyPosn(position.x + 1, position.y    ),
        new MyPosn(position.x - 1, position.y    ),
        new MyPosn(position.x    , position.y + 1),
        new MyPosn(position.x    , position.y - 1),
        new MyPosn(position.x + 1, position.y + 1),
        new MyPosn(position.x - 1, position.y - 1),
        new MyPosn(position.x + 1, position.y - 1),
        new MyPosn(position.x - 1, position.y + 1)));
    ArrayList<MyPosn> toRemove = new ArrayList<MyPosn>();
    for (int i = 0; i < moves.size(); i++) {
      MyPosn posn = moves.get(i);
      if (((this.team == 0) && (whitePieces.containsKey(posn))) ||
          ((this.team == 1) && (blackPieces.containsKey(posn)))) {
        toRemove.add(posn);
      }
    }
    for (int i = 0; i < moves.size(); i++) {
      MyPosn posn = moves.get(i);
      HashMap<MyPosn, Piece> pieces = new HashMap<MyPosn, Piece>();
      if (this.team == 0) {
        pieces = blackPieces;
      } else if (this.team == 1) {
        pieces = whitePieces;
      }
      for (Entry<MyPosn, Piece> piece : pieces.entrySet()) {
        if ((piece.getValue().name.equals("Pawn") && piece.getValue().allPawnMoves(whitePieces, blackPieces).contains(posn)) ||
            (piece.getValue().name.equals("King") && piece.getValue().allKingMoves(whitePieces, blackPieces).contains(posn))) {
          toRemove.add(posn);
        } else if (!(piece.getValue().name.equals("King")) && piece.getValue().possibleMoves(whitePieces, blackPieces).contains(posn)) {
          toRemove.add(posn);
        }
      }
    }
    for (int i = 0; i < toRemove.size(); i++) {
      moves.remove(toRemove.get(i));
    }
    return moves;
  }
}

class Queen extends Piece {

  Queen(String name, MyPosn position, int team, boolean state, WorldImage image) {
    super(name, position, team, state, image);
  }

  public ArrayList<MyPosn> possibleMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    ArrayList<MyPosn> moves     = new ArrayList<MyPosn>();
    ArrayList<MyPosn> right     = this.move(moves    , whitePieces, blackPieces, "right");
    ArrayList<MyPosn> left      = this.move(right    , whitePieces, blackPieces, "left");
    ArrayList<MyPosn> up        = this.move(left     , whitePieces, blackPieces, "up");
    ArrayList<MyPosn> down      = this.move(up       , whitePieces, blackPieces, "down");
    ArrayList<MyPosn> upright   = this.move(down     , whitePieces, blackPieces, "upright");
    ArrayList<MyPosn> upleft    = this.move(upright  , whitePieces, blackPieces, "upleft");
    ArrayList<MyPosn> downright = this.move(upleft   , whitePieces, blackPieces, "downright");
    ArrayList<MyPosn> downleft  = this.move(downright, whitePieces, blackPieces, "downleft");
    return downleft;
  }
}

class Bishop extends Piece {

  Bishop(String name, MyPosn position, int team, boolean state, WorldImage image) {
    super(name, position, team, state, image);
  }

  public ArrayList<MyPosn> possibleMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    ArrayList<MyPosn> moves     = new ArrayList<MyPosn>();
    ArrayList<MyPosn> upright   = this.move(moves    , whitePieces, blackPieces, "upright");
    ArrayList<MyPosn> upleft    = this.move(upright  , whitePieces, blackPieces, "upleft");
    ArrayList<MyPosn> downright = this.move(upleft   , whitePieces, blackPieces, "downright");
    ArrayList<MyPosn> downleft  = this.move(downright, whitePieces, blackPieces, "downleft");
    return downleft;
  }
}

class Knight extends Piece {

  Knight(String name, MyPosn position, int team, boolean state, WorldImage image) {
    super(name, position, team, state, image);
  }

  public ArrayList<MyPosn> possibleMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    ArrayList<MyPosn> moves = new ArrayList<MyPosn>(List.of(
        new MyPosn(position.x - 1, position.y - 2),
        new MyPosn(position.x + 1, position.y - 2),
        new MyPosn(position.x + 2, position.y - 1),
        new MyPosn(position.x + 2, position.y + 1),
        new MyPosn(position.x + 1, position.y + 2),
        new MyPosn(position.x - 1, position.y + 2),
        new MyPosn(position.x - 2, position.y + 1),
        new MyPosn(position.x - 2, position.y - 1)));
    ArrayList<MyPosn> toRemove = new ArrayList<MyPosn>();
    for (int i = 0; i < moves.size(); i++) {
      MyPosn posn = moves.get(i);
      if (((this.team == 0) && (whitePieces.containsKey(posn))) ||
          ((this.team == 1) && (blackPieces.containsKey(posn)))) {
        toRemove.add(posn);
      }
    }
    for (int i = 0; i < toRemove.size(); i++) {
      moves.remove(toRemove.get(i));
    }
    return moves;
  }
}

class Rook extends Piece {

  Rook(String name, MyPosn position, int team, boolean state, WorldImage image) {
    super(name, position, team, state, image);
  }

  public ArrayList<MyPosn> possibleMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    ArrayList<MyPosn> moves = new ArrayList<MyPosn>();
    ArrayList<MyPosn> right = this.move(moves    , whitePieces, blackPieces, "right");
    ArrayList<MyPosn> left  = this.move(right    , whitePieces, blackPieces, "left");
    ArrayList<MyPosn> up    = this.move(left     , whitePieces, blackPieces, "up");
    ArrayList<MyPosn> down  = this.move(up       , whitePieces, blackPieces, "down");
    return down;
  }
}

class Pawn extends Piece {

  Pawn(String name, MyPosn position, int team, boolean state, WorldImage image) {
    super(name, position, team, state, image);
  }

  public ArrayList<MyPosn> possibleMoves(HashMap<MyPosn, Piece> whitePieces, HashMap<MyPosn, Piece> blackPieces) {
    ArrayList<MyPosn> moves = new ArrayList<MyPosn>();
    if (this.team == 0) {
      MyPosn up = new MyPosn(position.x, position.y - 1);
      if (!(whitePieces.containsKey(up)) && !(blackPieces.containsKey(up))) {
        moves.add(up);
      }
      MyPosn up2 = new MyPosn(position.x, position.y - 2);
      if ((this.position.y == 6) && !(whitePieces.containsKey(up)) && !(blackPieces.containsKey(up2))) {
        moves.add(up2);
      }
      MyPosn upright = new MyPosn(position.x + 1, position.y - 1);
      if (blackPieces.containsKey(upright)) {
        moves.add(upright);
      }
      MyPosn upleft = new MyPosn(position.x - 1, position.y - 1);
      if (blackPieces.containsKey(upleft)) {
        moves.add(upleft);
      }
    } else if (this.team == 1) {
      MyPosn down = new MyPosn(position.x, position.y + 1);
      if (!(whitePieces.containsKey(down)) && !(blackPieces.containsKey(down))) {
        moves.add(down);
      }
      MyPosn down2 = new MyPosn(position.x, position.y + 2);
      if ((this.position.y == 1) && !(whitePieces.containsKey(down2)) && !(blackPieces.containsKey(down2))) {
        moves.add(down2);
      }
      MyPosn downright = new MyPosn(position.x + 1, position.y + 1);
      if (whitePieces.containsKey(downright)) {
        moves.add(downright);
      }
      MyPosn downleft = new MyPosn(position.x - 1, position.y + 1);
      if (whitePieces.containsKey(downleft)) {
        moves.add(downleft);
      }
    }
    return moves;
  }
}

class MyPosn extends Posn {

  public MyPosn(int x, int y) {
    super(x, y);
  }

  public MyPosn(Posn p) {
    this(p.x, p.y);
  }

  public void placeMe(WorldImage image, WorldScene scene, int size) {
    scene.placeImageXY(image, this.x * size, this.y * size);
  }
}

class ExamplesChess {
  Chess game1 = new Chess();
  Chess game2 = new Chess();
  Chess game3 = new Chess();
  Chess game4 = new Chess();
  Chess game5 = new Chess();
  Chess game6 = new Chess();
  Chess game7 = new Chess();
  Chess game8 = new Chess();
  Chess game9 = new Chess();

  void testMovePiece(Tester t) {
    game2.currentPiece = Optional.of(game2.whitePieces.get(new MyPosn(3, 6)));
    game2.movePiece(game2.whitePieces, new MyPosn(3, 5));
    t.checkExpect(game2.whitePieces.get(new MyPosn(3, 5)), new Pawn("Pawn", new MyPosn(3, 5), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    t.checkExpect(game2.activeTeam, Team.BLACK);
    t.checkExpect(game2.currentPiece, Optional.empty());
  }
  
  void testCheckmate(Tester t) {
    HashMap<MyPosn, Piece> newWhitePieces = new HashMap<MyPosn, Piece>();    
    newWhitePieces.put(new MyPosn(5, 5), new King("King", new MyPosn(5, 5), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKing.png")));
    HashMap<MyPosn, Piece> newBlackPieces = new HashMap<MyPosn, Piece>();    
    newBlackPieces.put(new MyPosn(5, 6), new King("King", new MyPosn(5, 6), 1, true, new FromFileImage("src/IrisYangChess/pieces/blackKing.png")));
    game3.whitePieces = newWhitePieces;
    game3.blackPieces = newBlackPieces;
    game3.capture(game3.blackPieces, new MyPosn(5, 6), "White");
    t.checkExpect(game3.winner, "White");
    t.checkExpect(game3.isGameOver, true);
  }
  
  void testPromotion(Tester t) {
    HashMap<MyPosn, Piece> newWhitePieces = new HashMap<MyPosn, Piece>();    
    newWhitePieces.put(new MyPosn(5, 5), new King("King", new MyPosn(5, 5), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKing.png")));
    newWhitePieces.put(new MyPosn(5, 0), new Pawn("Pawn", new MyPosn(5, 0), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png")));
    game4.whitePieces = newWhitePieces;
    game4.currentPiece = Optional.of(game4.whitePieces.get(new MyPosn(5, 0)));
    game4.promotion();
    t.checkExpect(game4.whitePieces.get(new MyPosn(5, 0)), new Queen("Queen", new MyPosn(5, 0), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteQueen.png")));
  }
  
  boolean testPossibleMoves(Tester t) {
    HashMap<MyPosn, Piece> newWhitePieces = new HashMap<MyPosn, Piece>();
    King   king    = new King  ("King"  , new MyPosn(2, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKing.png"));
    Queen  queen   = new Queen ("Queen" , new MyPosn(3, 5), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteQueen.png"));
    Bishop bishop1 = new Bishop("Bishop", new MyPosn(5, 4), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteBishop.png"));
    Bishop bishop2 = new Bishop("Bishop", new MyPosn(5, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteBishop.png"));
    Knight knight1 = new Knight("Knight", new MyPosn(2, 5), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKnight.png"));
    Knight knight2 = new Knight("Knight", new MyPosn(6, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteKnight.png"));
    Rook   rook1   = new Rook  ("Rook"  , new MyPosn(3, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteRook.png"));
    Rook   rook2   = new Rook  ("Rook"  , new MyPosn(7, 7), 0, true, new FromFileImage("src/IrisYangChess/pieces/whiteRook.png"));
    Pawn   pawn1   = new Pawn  ("Pawn"  , new MyPosn(0, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    Pawn   pawn2   = new Pawn  ("Pawn"  , new MyPosn(1, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    Pawn   pawn3   = new Pawn  ("Pawn"  , new MyPosn(2, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    Pawn   pawn4   = new Pawn  ("Pawn"  , new MyPosn(3, 4), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    Pawn   pawn5   = new Pawn  ("Pawn"  , new MyPosn(4, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    Pawn   pawn6   = new Pawn  ("Pawn"  , new MyPosn(5, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    Pawn   pawn7   = new Pawn  ("Pawn"  , new MyPosn(6, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    Pawn   pawn8   = new Pawn  ("Pawn"  , new MyPosn(7, 6), 0, true, new FromFileImage("src/IrisYangChess/pieces/whitePawn.png"));
    
    newWhitePieces.put(new MyPosn(2, 7), king);
    newWhitePieces.put(new MyPosn(3, 5), queen);
    newWhitePieces.put(new MyPosn(5, 4), bishop1);
    newWhitePieces.put(new MyPosn(5, 7), bishop2);
    newWhitePieces.put(new MyPosn(2, 5), knight1);
    newWhitePieces.put(new MyPosn(6, 7), knight2);
    newWhitePieces.put(new MyPosn(3, 7), rook1);
    newWhitePieces.put(new MyPosn(7, 7), rook2);
    newWhitePieces.put(new MyPosn(0, 6), pawn1);
    newWhitePieces.put(new MyPosn(1, 6), pawn2);
    newWhitePieces.put(new MyPosn(2, 6), pawn3);
    newWhitePieces.put(new MyPosn(3, 4), pawn4);
    newWhitePieces.put(new MyPosn(4, 6), pawn5);
    newWhitePieces.put(new MyPosn(5, 6), pawn6);
    newWhitePieces.put(new MyPosn(6, 6), pawn7);
    newWhitePieces.put(new MyPosn(7, 6), pawn8);
    return t.checkExpect(king   .possibleMoves(newWhitePieces, game5.blackPieces), new ArrayList<MyPosn>(List.of(new MyPosn(1, 7), new MyPosn(3, 6), new MyPosn(1, 8), new MyPosn(2, 8), new MyPosn(3, 8))))
        && t.checkExpect(knight1.possibleMoves(newWhitePieces, game5.blackPieces), new ArrayList<MyPosn>(List.of(new MyPosn(1, 3), new MyPosn(0, 4), new MyPosn(1, 7), new MyPosn(3, 3), new MyPosn(4, 4))))
        && t.checkExpect(pawn1  .possibleMoves(newWhitePieces, game5.blackPieces), new ArrayList<MyPosn>(List.of(new MyPosn(0, 5), new MyPosn(0, 4))))
        && t.checkExpect(pawn4  .possibleMoves(newWhitePieces, game5.blackPieces), new ArrayList<MyPosn>(List.of(new MyPosn(3, 3))));
  }
  
  boolean testAllPawnAndKingMoves(Tester t) {
    return t.checkExpect(game6.whitePieces.get(new MyPosn(1, 6)).allPawnMoves(game6.whitePieces, game6.blackPieces), new ArrayList<MyPosn>(List.of(new MyPosn(1, 5), new MyPosn(1, 4), new MyPosn(0, 5), new MyPosn(2, 5))))
        && t.checkExpect(game6.whitePieces.get(new MyPosn(4, 7)).allKingMoves(game6.whitePieces, game6.blackPieces), new ArrayList<MyPosn>(List.of(new MyPosn(3, 6), new MyPosn(4, 6), new MyPosn(5, 6), new MyPosn(3, 7), new MyPosn(5, 7), new MyPosn(3, 8), new MyPosn(4, 8), new MyPosn(5, 8))));
  }

  void testGame(Tester t) {
    game1.runChess();
  }
}