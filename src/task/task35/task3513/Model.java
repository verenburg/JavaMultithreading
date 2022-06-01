package task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;

    private Tile[][] gameTiles;
    int score;
    int maxTile;
    private Stack<Tile[][]> previousStates = new Stack<>();
    private Stack<Integer> previousScores = new Stack<>();
    private boolean isSaveNeeded = true;

    public Model(){
        resetGameTiles();
        score = 0;
        maxTile = 0;
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++){
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    public void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            Tile tile = emptyTiles.get((int) (Math.random() * emptyTiles.size()) % emptyTiles.size());
            tile.value = Math.random() < 0.9 ? 2 : 4;
        }

    }

    private List<Tile> getEmptyTiles(){
        List<Tile> result = new ArrayList<>();
        for (Tile[] tiles : gameTiles) {
            for (Tile tile : tiles) {
                if (tile.isEmpty()) {
                    result.add(tile);
                }
            }
        }
        return result;
    }





    private boolean compressTiles(Tile[] tiles) {
        Tile[] copyTiles = new Tile[tiles.length];
        for (int j = 0; j < FIELD_WIDTH; j++){
            copyTiles[j] = new Tile(tiles[j].value);
        }
        int insertPosition = 0;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (!tiles[i].isEmpty()) {
                if (i != insertPosition) {
                    tiles[insertPosition] = tiles[i];
                    tiles[i] = new Tile();
                }
                insertPosition++;
            }
        }
        boolean isChanged = false;
        for (int k = 0; k < FIELD_WIDTH; k++) {
            if (copyTiles[k].value != tiles[k].value) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        Tile[] copyTiles = new Tile[tiles.length];
        for (int j = 0; j < FIELD_WIDTH; j++){
            copyTiles[j] = new Tile(tiles[j].value);
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (i+1 < FIELD_WIDTH && tiles[i].value == tiles[i+1].value){
                int updatedValue = tiles[i].value * 2;
                if (maxTile < updatedValue) {
                    maxTile = updatedValue;
                }
                score += updatedValue;
                tiles[i].value = updatedValue;
                tiles[i +1] = new Tile();
            }
        }
        compressTiles(tiles);
        boolean isChanged = false;
        for (int k = 0; k < FIELD_WIDTH; k++) {
            if (copyTiles[k].value != tiles[k].value) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState();
        }
        boolean flag = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (mergeTiles(gameTiles[i]) | compressTiles(gameTiles[i])) {
                flag = true;
            }
        }
        if (flag) {
            addTile();
        }
        isSaveNeeded = true;
    }

    public void right() {
        saveState();
        gameTiles = rotateRight(gameTiles);
        gameTiles = rotateRight(gameTiles);
        left();
        gameTiles = rotateRight(gameTiles);
        gameTiles = rotateRight(gameTiles);
    }

    public void up() {
        saveState();
        gameTiles = rotateRight(gameTiles);
        gameTiles = rotateRight(gameTiles);
        gameTiles = rotateRight(gameTiles);
        left();
        gameTiles = rotateRight(gameTiles);
    }

    public void down() {
        saveState();
        gameTiles = rotateRight(gameTiles);
        left();
        gameTiles = rotateRight(gameTiles);
        gameTiles = rotateRight(gameTiles);
        gameTiles = rotateRight(gameTiles);
    }

    private Tile[][] rotateRight(Tile[][] tiles){
        Tile[][] result = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                result[j][FIELD_WIDTH - 1 - i] = tiles[i][j];
            }
        }
        return result;
    }

    public boolean canMove() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            return true;
        }

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                Tile t = gameTiles[i][j];
                if ((i < FIELD_WIDTH - 1 && t.value == gameTiles[i + 1][j].value)
                     || ((j < FIELD_WIDTH - 1) && t.value == gameTiles[i][j + 1].value))
                {
                    return true;
                }
            }
        }
        return false;
        
    }


    public Tile[][] getGameTiles() {
        return gameTiles;
    }

     void saveState() {
        Tile[][] copyTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                copyTiles[i][j] = new Tile(gameTiles[i][j].value);
            }
        }
        previousStates.push(copyTiles);
        previousScores.push(score);

        isSaveNeeded = false;
    }

     void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()){
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    void randomMove() {
        int n = (int) (Math.random() *100 % 4);
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }

    private boolean hasBoardChanged() {
        return sumOfTile(gameTiles) != sumOfTile(previousStates.peek());
    }

    private MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency = new MoveEfficiency(-1, 0, move);
        move.move();
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();
        return moveEfficiency;
    }

    private int sumOfTile(Tile[][] tiles) {
        int result = 0;
        for (int i =0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                result += tiles[i][j].value;
            }
        }

        return result;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());

        queue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                left();
            }
        }));
        queue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                right();
            }
        }));
        queue.offer(getMoveEfficiency(new Move() {
            @Override
            public void move() {
                up();
            }
        }));
        queue.offer(getMoveEfficiency(this::down));

        queue.peek().getMove().move();

    }

 
}
