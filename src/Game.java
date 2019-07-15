import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * CS18000 - Fall 2018
 * <p>
 * Project 2 - Reversi
 * <p>
 * Implementation of the game mechanics in Reversi
 *
 * @author Imtiaz Karim, karim7@purdue.edu
 * @author Marina Haliem, mwadea@purdue.edu
 * @author Evan Wang, wang3407@purdue.edu
 * @version 10.18/2018
 */

public class Game {

    public Point pointA;
    private final char[][] board;
    public int wScore;
    public int bScore;
    public int remaining;
    private final char[] boardX = new char[] { '1', '2', '3', '4', '5', '6', '7', '8' } ;

    public Game() {
        board = new char[][]{
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', 'W', 'B', '_', '_', '_', },
                {'_', '_', '_', 'B', 'W', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', },
                {'_', '_', '_', '_', '_', '_', '_', '_', }, };
    }

    public void displayBoard(Game b) {


        System.out.print("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < 8; i++) {
            System.out.print("\n" + (i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println("\n");
    }

    //There are three cases black win = -1, white win = 1, draw = 0

    public int gameResult(Point[] whitePlaceableLocations, Point[] blackPlaceableLocations) {


        updateScores();

        if (whitePlaceableLocations.length == 0 && blackPlaceableLocations.length == 0) {
            if (bScore > wScore)
                return -1;
            else if (wScore > bScore)
                return 1;
            else if (bScore == wScore)
                return 0;
        }


        if (remaining == 0 && wScore > bScore) {
            return 1;
        } else if (remaining == 0 && bScore > wScore) {
            return -1;
        } else if (wScore == 0 && bScore > 0) {
            return -1;
        } else if (bScore == 0 && wScore > 0) {
            return 1;
        }


        return 0;
    }


    public Point[] getPlaceableLocations(char player, char opponent) {


        Point[] placeablePositions = new Point[64];
        for (int i = 0; i < 64; i++) {
            placeablePositions[i] = new Point(-1, -1);
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == opponent) {

                    int p = i;
                    int q = j;

                    //

                    if (i > 0 && j > 0) {
                        if (board[i - 1][j - 1] == '_') {
                            i++;
                            j++;
                            while (i < 7 && j < 7 && board[i][j] == opponent) {
                                i++;
                                j++;
                            }
                            if (i < 8 && j < 8 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p - 1;
                                        placeablePositions[a].y = q - 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    i = p;
                    j = q;

                    //


                    if (i > 0 && j < 7) {
                        if (board[i - 1][j + 1] == '_') {
                            i++;
                            j--;
                            while (i < 7 && j > 0 && board[i][j] == opponent) {
                                i++;
                                j--;
                            }
                            if (i < 8 && j > -1 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p - 1;
                                        placeablePositions[a].y = q + 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    i = p;
                    j = q;


                    if (i > 0) {
                        if (board[i - 1][j] == '_') {
                            i++;
                            while (i < 7 && board[i][j] == opponent) {
                                i++;
                            }
                            if (i < 8 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p - 1;
                                        placeablePositions[a].y = q;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    i = p;

                    //


                    //


                    if (j > 0) {
                        if (board[i][j - 1] == '_') {
                            j++;
                            while (j < 7 && board[i][j] == opponent) {
                                j++;
                            }
                            if (j < 8 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p;
                                        placeablePositions[a].y = q - 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    j = q;

                    //


                    if (j < 7) {
                        if (board[i][j + 1] == '_') {
                            j--;
                            while (j > 0 && board[i][j] == opponent) {
                                j--;
                            }
                            if (j > -1 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p;
                                        placeablePositions[a].y = q + 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    j = q;

                    //
                    if (i < 7 && j < 7) {
                        if (board[i + 1][j + 1] == '_') {
                            i--;
                            j--;
                            while (i > 0 && j > 0 && board[i][j] == opponent) {
                                i--;
                                j--;
                            }
                            if (i > -1 && j > -1 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p + 1;
                                        placeablePositions[a].y = q + 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    i = p;
                    j = q;

                    //

                    if (i < 7) {
                        if (board[i + 1][j] == '_') {
                            i--;
                            while (i > 0 && board[i][j] == opponent) {
                                i--;
                            }
                            if (i > -1 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p + 1;
                                        placeablePositions[a].y = q;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    i = p;


                    //


                    if (i < 7 && j > 0) {
                        if (board[i + 1][j - 1] == '_') {
                            i--;
                            j++;
                            while (i > 0 && j < 7 && board[i][j] == opponent) {
                                i--;
                                j++;
                            }
                            if (i > -1 && j < 8 && board[i][j] == player) {
                                for (int a = 0; a < 64; a++) {
                                    if (placeablePositions[a].x == -1 && placeablePositions[a].y == -1) {
                                        placeablePositions[a].x = p + 1;
                                        placeablePositions[a].y = q - 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    i = p;
                    j = q;


                }
            }
        }

        return placeablePositions;
    }


    public void showPlaceableLocations(Point[] locations, char player, char opponent) {


        for (int i = 0; i < locations.length; i++) {
            if (locations[i].x != -1 && locations[i].y != -1) {
                Point p = locations[i];
                board[p.x][p.y] = '*';
            }
        }

        displayBoard(this);

        for (int i = 0; i < locations.length; i++) {
            if (locations[i].x != -1 && locations[i].y != -1) {
                Point p = locations[i];
                board[p.x][p.y] = '_';
            }
        }


    }

    public void placeMove(Point point, char player, char opponent) {


        int i = point.x;
        int j = point.y;
        char c = player;
        board[i][j] = c;
        int p = i;
        int q = j;


        if (j > 0) {
            if (board[i][j - 1] != player) {
                j--;
                while (j > 0 && board[i][j] == opponent) {
                    j--;
                }
                if (j > -1 && board[i][j] == player) {
                    while (j != q - 1) {
                        j++;
                        board[i][j] = player;
                    }
                }
            }
        }

        j = q;


//

        if (i > 0) {
            if (board[i - 1][j] != player) {
                i--;
                while (i > 0 && board[i][j] == opponent) {
                    i--;
                }
                if (i > -1 && board[i][j] == player) {
                    while (i != p - 1) {
                        i++;
                        board[i][j] = player;
                    }
                }
            }
        }

        i = p;

//


//


        if (i > 0 && j > 0) {
            if (board[i - 1][j - 1] != player) {
                i--;
                j--;
                while (i > 0 && j > 0 && board[i][j] == opponent) {
                    i--;
                    j--;
                }
                if (i > -1 && j > -1 && board[i][j] == player) {
                    while (i != p - 1 && j != q - 1) {
                        i++;
                        j++;
                        board[i][j] = player;
                    }
                }
            }
        }

        i = p;
        j = q;


//

        if (i < 7 && j < 7) {
            if (board[i + 1][j + 1] != player) {
                i++;
                j++;
                while (i < 7 && j < 7 && board[i][j] == opponent) {
                    i++;
                    j++;
                }
                if (i < 8 && j < 8 && board[i][j] == player) {
                    while (i != p + 1 && j != q + 1) {
                        i--;
                        j--;
                        board[i][j] = player;
                    }
                }
            }
        }

//

        if (i > 0 && j < 7) {
            if (board[i - 1][j + 1] != player) {
                i--;
                j++;
                while (i > 0 && j < 7 && board[i][j] == opponent) {
                    i--;
                    j++;
                }
                if (i > -1 && j < 8 && board[i][j] == player) {
                    while (i != p - 1 && j != q + 1) {
                        i++;
                        j--;
                        board[i][j] = player;
                    }
                }
            }
        }

        i = p;
        j = q;


//

        if (j < 7) {
            if (board[i][j + 1] != player) {
                j++;
                while (j < 7 && board[i][j] == opponent) {
                    j++;
                }
                if (j < 8 && board[i][j] == player) {
                    while (j != q + 1) {
                        j--;
                        board[i][j] = player;
                    }
                }
            }
        }
        j = q;


//


        if (i < 7 && j > 0) {
            if (board[i + 1][j - 1] != player) {
                i++;
                j--;
                while (i < 7 && j > 0 && board[i][j] == opponent) {
                    i++;
                    j--;
                }
                if (i < 8 && j > -1 && board[i][j] == player) {
                    while (i != p + 1 && j != q - 1) {
                        i--;
                        j++;
                        board[i][j] = player;
                    }
                }
            }
        }

        i = p;
        j = q;


        //


        if (i < 7) {
            if (board[i + 1][j] != player) {
                i++;
                while (i < 7 && board[i][j] == opponent) {
                    i++;
                }
                if (i < 8 && board[i][j] == player) {
                    while (i != p + 1) {
                        i--;
                        board[i][j] = player;
                    }
                }
            }
        }

        i = p;

        //
    }

    public void updateScores() {


        int wCount = 0;
        int bCount = 0;
        remaining = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'W')
                    wCount++;

                else if (board[i][j] == 'B')
                    bCount++;

                else
                    remaining++;
            }
        }

        wScore = wCount;
        bScore = bCount;


    }
}
