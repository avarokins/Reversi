

import java.util.Scanner;

/**
 * CS18000 - Fall 2018
 * <p>
 * Project 2 - Reversi
 * <p>
 * Abstraction of and launcher for a Reversi game
 *
 * @author Imtiaz Karim, karim7@purdue.edu
 * @author Marina Haliem, mwadea@purdue.edu
 * @author Evan Wang, wang3407@purdue.edu
 * @version 10.18/2018
 */

public class Reversi {


    public static boolean isEmpty(Point[] p) {


        int count = 0;

        for (int i = 0; i < p.length; i++) {
            if (p[i] == null) {
                count++;
            }
        }

        return count > 0;


    }

    //Check whether a Point is the Points array or not

    public static boolean contains(Point[] points, Point p) {

        for (int i = 0; i < points.length; i++) {
            if (points[i] == p) {
                return true;
            }
        }

        return false;

    }

    public static void start(Game g) {


        String draw = "It is a draw.";
        String blackMove = "Place move (Black): row then column: ";
        String blackSkipping = "Black needs to skip... Passing to white";
        String whiteMove = "Place move (White): row then column: ";
        String whiteSkipping = "White needs to skip... Passing to Black";
        String exit = "Exiting!";

        Scanner sc = new Scanner(System.in);

        int skipWhite = 0;
        int blackCheck = 0;
        int whiteCheck = 0;
        int skipBlack = 0;
        int outerCount = 0;
        boolean skip = false;
        String move;

        while (true) {

                skip = false;
            if (outerCount % 2 == 0) {

                Point[] arrayPoint = g.getPlaceableLocations('B', 'W');
                skipBlack = 0;
                int i = 0;
                while(i < arrayPoint.length){
                    if (arrayPoint[i].x != -1) {
                        skipBlack++;
                    }
                    i++;
                }

                if (skipBlack == 0) {
                    outerCount++;
                }
            }

            if (outerCount % 2 != 0) {

                Point[] arrayPoint = g.getPlaceableLocations('W', 'B');
                skipWhite = 0;
                int i = 0;
                while(i < arrayPoint.length){
                    if (arrayPoint[i].x != -1) {
                        skipWhite++;
                    }
                    i++;
                }

                if (skipWhite == 0) {
                    outerCount++;
                }
            }


            //If no valid moves

            if (skipBlack == 0 && skipWhite == 0)
                break;

            //If board full

            g.updateScores();
            if (g.remaining == 0)
                break;


            if (outerCount % 2 == 0) {

                Point[] arrayPoint = g.getPlaceableLocations('B', 'W');
                skipBlack = 0;
                int i = 0;
                while(i < arrayPoint.length){
                    if (arrayPoint[i].x != -1) {
                        skipBlack++;
                    }
                    i++;
                }

                if (skipBlack == 0) {
                    System.out.println(blackSkipping);
                    outerCount++;
                }
            }

            if (outerCount % 2 != 0) {

                Point[] arrayPoint = g.getPlaceableLocations('W', 'B');
                skipWhite = 0;
                int i = 0;
                while(i < arrayPoint.length) {
                    if (arrayPoint[i].x != -1) {
                        skipWhite++;
                    }
                    i++;
                }

                if (skipWhite == 0) {
                    System.out.println(whiteSkipping);
                    outerCount++;
                }
            }


            if (outerCount % 2 == 0) {

                g.updateScores();


                if (outerCount != 0)
                    System.out.println("White: " + g.wScore + " Black: " + g.bScore + "\n");
                else
                    System.out.println();

                g.showPlaceableLocations(g.getPlaceableLocations('B', 'W'), 'B', 'W');

                while (blackCheck == 0) {

                    System.out.println(blackMove);
                    move = sc.next();

                    if (move.equals("exit")) {
                        System.out.println(exit);
                        return;
                    }

                    int row = Integer.parseInt(move.substring(0, 1)) - 1;
                    int column = Integer.parseInt(move.substring(1, 2)) - 1;

                    Point p = new Point(row, column);
                    Point[] arrayPoint = g.getPlaceableLocations('B', 'W');


                    for (int i = 0; i < arrayPoint.length; i++) {

                        if (p.x == arrayPoint[i].x && p.y == arrayPoint[i].y) {
                            g.placeMove(p, 'B', 'W');
                            blackCheck = 1;
                            break;
                        }
                    }

                    if (blackCheck == 0) {
                        System.out.println("Invalid move!");
                    }


                }
                blackCheck = 0;
            }

            if (outerCount % 2 != 0) {

                g.updateScores();
                System.out.println("Black: " + g.bScore + " White: " + g.wScore + "\n");

                g.showPlaceableLocations(g.getPlaceableLocations('W', 'B'), 'W', 'B');

                while (whiteCheck == 0) {


                    System.out.println(whiteMove);
                    move = sc.next();

                    if (move.equals("exit")) {
                        System.out.println(exit);
                        return;
                    }

                    int row = Integer.parseInt(move.substring(0, 1)) - 1;
                    int column = Integer.parseInt(move.substring(1, 2)) - 1;


                    Point p = new Point(row, column);
                    Point[] arrayPoint = g.getPlaceableLocations('W', 'B');


                    for (int i = 0; i < arrayPoint.length; i++) {

                        if (p.x == arrayPoint[i].x && p.y == arrayPoint[i].y) {
                            g.placeMove(p, 'W', 'B');
                            whiteCheck = 1;
                            break;
                        }
                    }

                    if (whiteCheck == 0) {
                        System.out.println("Invalid move!");
                    }
                }
                whiteCheck = 0;
            }
            outerCount++;
        }


        int result = g.gameResult(g.getPlaceableLocations('W', 'B'), g.getPlaceableLocations('B', 'W'));

        if (result == -1) {
            g.updateScores();
            System.out.println("Black wins: " + g.bScore + ":" + g.wScore);
        } else if (result == 1) {
            g.updateScores();
            System.out.println("White wins: " + g.wScore + ":" + g.bScore);
        } else
            System.out.println(draw);


    }

    public static void main(String[] args) {
        Game b = new Game();
        start(b);

    }

    }
