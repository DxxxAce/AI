package com;

import com.model.Position;
import com.model.Problem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);

        int size;
        Position[] blockedPositions;

        try {

            System.out.println("Please enter the board size:");

            do
            {
                size = scanner.nextInt();
            }

            while (size < 4);

            System.out.println("How many positions would you like to block?");

            int blockedSize;
            do {
                blockedSize = scanner.nextInt();
            }
            while (blockedSize < 0);

            System.out.println("Please enter the blocked positions indices:");
            blockedPositions = new Position[blockedSize];

            for (int i = 0; i < blockedPositions.length; i++)
            {
                blockedPositions[i] = new Position();

                System.out.println("Row");
                do {
                    blockedPositions[i].setRow(scanner.nextInt());
                }
                while (blockedPositions[i].getRow() < 0 || blockedPositions[i].getRow() > blockedSize);

                System.out.println("Col");
                do {
                    blockedPositions[i].setCol(scanner.nextInt());
                }
                while (blockedPositions[i].getCol() < 0 || blockedPositions[i].getCol() > blockedSize);
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println("You have entered an invalid input. Here is a default problem example.");
            size = 4;

            blockedPositions = new Position[]{
                    new Position(1, 1),
                    new Position(2, 2),
                    new Position(4, 3)
            };

            System.out.println("Size = " + size);
            System.out.println("Blocked positions:");
            for (int i = 0; i < blockedPositions.length; i++)
            {
                System.out.println((i + 1) + ". Row = " + blockedPositions[i].row +
                        ", Column = " + blockedPositions[i].col);
            }
        }

        Problem queens = new Problem(size, blockedPositions);
        queens.printSolution();
    }
}
