package com.model;

public class Problem {
    private int size;
    private int n;
    private int offset ;
    private int[][] board;
    private boolean solutionPossible;
    private boolean [] blockedRow;
    private boolean [] blockedColumn;
    private boolean [] blockedPrincipalDiagonal;
    private boolean [] blockedSecondaryDiagonal;

    public Problem(int size, Position[] blockedPositions)
    {
        this.size = size;
        this.n = size - 1;
        this.offset = n;
        this.solutionPossible = false;
        this.board = new int[size][size];
        this.blockedColumn = new boolean[size];
        this.blockedRow = new boolean[size];
        for (int i = 0 ; i <=  n ; ++i )blockedRow[i]= blockedColumn[i]= false;
        this.blockedSecondaryDiagonal = new boolean[size*2];
        this.blockedPrincipalDiagonal = new boolean[size*2];
        for(int i = 0 ; i < size*2 ; ++i ) blockedPrincipalDiagonal[i]=blockedSecondaryDiagonal[i] = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
            {
                board[i][j] = 0;
            }
        }
        /*
        for (Position pos : blockedPositions)
        {
            board[pos.row][pos.col] = -1;
            blockedRow[pos.row] = true;
            blockedColumn[pos.row ] = true;
        }*/
    }

    private Problem()
    {

    }

    private boolean isSafe(int i, int j)
    {
        return !blockedPrincipalDiagonal[i-j+offset] && !blockedSecondaryDiagonal[i+j-n +offset] && board[i][j] != -1 ;
    }

    private void blockAll( int i, int j )
    {
        blockedRow[i] = true;
        blockedColumn[j] = true;
        blockedPrincipalDiagonal[i-j+offset] = true;
        blockedSecondaryDiagonal[i+j-n +offset] = true ;
    }

    private void releaseAll( int i , int j)
    {
        blockedRow[i] = false;
        blockedColumn[j] = false;
        blockedPrincipalDiagonal[i-j+offset] = false;
        blockedSecondaryDiagonal[i+j-n +offset] = false ;
    }

    public void bkt(int k)
    {
        if( k == size )
        {
            solutionPossible = true;
            for(int i = 0 ; i <= n ; ++i )
            {
                for(int j = 0 ; j <= n ; ++j )
                {
                    System.out.print(board[i][j] );
                    System.out.print(" ");
                }
                System.out.print('\n');
            }
        }
        else
        {
            if(!solutionPossible)
            for(int i = 0 ; i <= n ; ++i )
            {
                for (int j = 0; j <= n ; ++j )
                {
                    if(isSafe(i, j) && !blockedRow[i] && !blockedColumn[j])
                    {
                        board[i][j] = k + 1;
                        blockAll(i, j);
                        bkt(k + 1);
                        releaseAll(i, j);
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    public void printSolution()
    {
        this.bkt(0);
        if(!solutionPossible) System.out.println("Solution is not possible");
    }
}
