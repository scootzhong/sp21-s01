package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 * 2048游戏的状态
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board.
     * 棋盘的当前内容 */
    private Board board;
    /** Current score.
     * 当前得分*/
    private int score;
    /** Maximum score so far.  Updated when game ends.
     * 到目前为止的最高得分。游戏结束时更新。*/
    private int maxScore;
    /** True iff game is ended.
     * 游戏是否结束的标志*/
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     * 坐标系统：棋盘的列C、行R（其中行0，列0是棋盘的左下角）对应于board.tile(c, r)。
     * 要小心！它的工作方式类似于（x，y）坐标。
     */

    /** Largest piece value.
     * 常量：最大方块值2048*/
    public static final int MAX_PIECE = 2048;

    /** 构造函数：在大小为SIZE的棋盘上创建一个新的2048游戏，没有方块且得分为0（初始化一个游戏）
     * A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes.
     * 创建一个新的2048游戏，其中RAWVALUES包含方块的值（如果为空则为0），
     * SCORE为得分，maxScore为最大得分，gameOver表示游戏是否结束。用于测试目的。*/
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  返回（COL, ROW）处的当前方块，其中 0 <= ROW < size()，0 <= COL < size()。
     *  如果那里没有方块，则返回null。 用于测试。 应该被弃用并删除。
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed.
     *  返回棋盘一边的方块数。 用于测试。 应该被弃用并删除*/
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board).
     *  返回true，当游戏结束（没有可移动的方块，或者棋盘上有值为2048的方块）。*/
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score.
     * 返回当前得分*/
    public int score() {
        return score;
    }

    /** 返回棋盘 */
    public Board getBoard() {
        return board;
    }

    /** Return the current maximum game score (updated at end of game).
     * 返回当前最高游戏分数（在游戏结束时更新）*/
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score.
     * 清空棋盘并重置得分*/
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position.
     *  添加TILE到棋盘。在相同位置没有Tile的情况下才能添加*/
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    /** 以SIDE方向倾斜棋盘。如果此操作更改了棋盘(如果关于面板的任何内容发生变化)，则返回true(修改局部变量changed)。
     *
     * 1. 如果两个Tile对象在运动方向上相邻且具有相同的值，则它们将合并为一个值是原始值两倍的Tile，并且该新值将添加到得分变量中(实例变量score)
     * 2. 作为合并结果的Tile将不会在该倾斜中再次合并。所以每次移动，每个Tile最多只能参与一个合并（可能为零）。
     * 3. 当在运动方向上的三个相邻的方块具有相同的值时，则在运动方向上的前两个方块合并，并且尾随的方块不合并。
     * 要求：
     * 所有在棋盘上的方块移动必须使用 move 类提供的方法完成
     * 所有棋盘上的方块必须使用 Board 类提供的 tile 方法访问
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        // TODO: 修改this.board（可能还有this.score）来考虑倾斜到Side SIDE。如果棋盘发生更改，请将更改的局部变量设置为true。

        // 若只考虑向上方向时:
        // 最上面一层空格不动
        // 将倒数第二行的所有方格向上移动
        // 将倒数第三行的所有方格向上移动
        // ...
        // 将最后一行的所有方格向上移动

        int size = this.size(); // 获取棋盘的边长
        for (int i = 0; i < size - 1; i++) { // 循环size-1次，从size-1行（倒数第二行）开始一直到最后一行，将空格上移
            moveColAllTileUp(this.board, size - 2 - i);
        }

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** 将棋盘中row行全部方格往上移动 */
    public static void moveColAllTileUp(Board b, int row) {
        return;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     *  检查游戏是否结束，并适当地设置gameOver变量
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over.
     * 确定游戏是否结束*/
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     * 如果给定的棋盘中的任何一个方块为空，该方法应返回 true
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        // 获取棋盘的宽和高（即行和列）
        // 判断每一个格子是否有方块，若存在没有方块的格子，则返回ture
        // 若判断完所有格子都有方块，则返回false

        int size = b.size(); // 获取棋盘的宽和高（即行和列），由于棋盘是正方形，所以宽高相同
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                if (b.tile(col, row) == null) {  // 循环判断每一个格子是否没有方块
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 游戏结束的条件之一：形成一个包含 2048 的方块
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     * 如果棋盘中的任何一个方块等于获胜方块的值 2048，则此方法应返回 true
     */
    public static boolean maxTileExists(Board b) {
        // 获取棋盘的宽和高（即行和列）
        // 判断每一个格子是否有方块，若有方块，则判断方块值是否为MAX_PIECE，若是，则返回true
        // 若判断完所有格子中的方块，则返回false

        int size = b.size(); // 获取棋盘的宽和高（即行和列），由于棋盘是正方形，所以宽高相同
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                Tile tempTile = b.tile(col, row); // 获取格子中的方块（若没有方块则为NULL）
                if (tempTile == null) {
                    continue; // 若格子中没有方块，则跳过循环
                }
                if (tempTile.value() == MAX_PIECE) {
                    return true; // 格子中的方块内值为MAX_PIECE，返回true
                }
            }
        }
        return false; // 循环完成都没有找到目标，则返回false
    }

    /**
     * 游戏结束的条件之一：没有可用的移动（没有方块可以改变棋盘）
     * Returns true if there are any valid moves on the board.
     * 如果存在任何有效的移动，则应返回 true
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 1、棋盘上至少有一个空格
     * 2. There are two adjacent tiles with the same value.
     * 2、有两个相邻的方块具有相同的值
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        // 判断棋盘中是否有空格（emptySpaceExists为true）,若有则返回true
        // 判断棋盘中的每个格子，若格子相邻放歌具有相同的值，则返回true
        // 判断完所有格子的方块，则返回false

        if (emptySpaceExists(b)) {
            return true; // 棋盘中有空格，true
        }
        int size = b.size();
        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                if (areAdjacentValuesSame(b, col, row)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 若指定棋盘上的格子（col row）具有相同值的相邻格子，则返回true
     */
    public static boolean areAdjacentValuesSame(Board b, int col, int row) {
        // 判断制定格子上、下、左、右的格子，若有相同的值，则返回true
        // 否则返回false

        int goalValue = b.tile(col, row).value(); // 指定格子的值
        int size = b.size(); // 棋盘的长度
        if (col - 1 >= 0 && b.tile(col - 1, row).value() == goalValue) {
            return true; // 左边相邻的格子（需存在）与指定格子的值相同，则返回true
        }
        if (col + 1 < size && b.tile(col + 1, row).value() == goalValue) {
            return true; // 右边相邻的格子（需存在）与指定格子的值相同，则返回true
        }
        if (row - 1 >= 0 && b.tile(col, row - 1).value() == goalValue) {
            return true; // 下边相邻的格子（需存在）与指定格子的值相同，则返回true
        }
        if (row + 1 < size && b.tile(col, row + 1).value() == goalValue) {
            return true; // 上边相邻的格子（需存在）与指定格子的值相同，则返回true
        }
        return false;
    }


    @Override
    /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
