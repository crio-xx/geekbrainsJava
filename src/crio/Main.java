package crio;

import javafx.util.Pair;

public class Main
{
    //Constants
    static char[] DOT = {'.','X','O'}; // empty, first player, second player
    static char[][] map;
    static int SIZE = 3;
    static int DOT_WIN = 3;

    static windowField window;


    public static void main(String[] args)
    {
        new windowGreeting();
    }

    public static void answerAi()
    {
        if(isWin(1))
        {
            endGame("Поздравляем! Вы победили!");
            return;
        }
        if(isMapFull())
        {
            endGame("Ничья.");
            return;
        }

        aiMove();
        if(isWin(2))
        {
            endGame("К сожалению, вы проиграли.");
            return;
        }
        if(isMapFull())
        {
            endGame("Ничья.");
        }
    }

    static void endGame(String message)
    {
        new windowGreeting();
        new windowMessage(message);
        windowField.disableField(windowField.cells);
    }

    static void aiMove()
    {
        /* Логика поиска клетки для хода заключается сначала в проверке наличия:
         1) клетки, которая приведет к победе ai (должно быть i = 5 символов в ряду)
         2) клетки, которая приведет к победе другого игрока (должно быть i = 5 символов в ряду)
         3) клетки, которая приведет к ситуации когда у ai i=4 клетки в ряду
         4) клетки, которая приведет к ситуации когда у другого игрока i=4 клетки в ряду
         ...
         */
        Pair<Integer,Integer> move;
        Pair<Integer,Integer> nothing = new Pair<>(-1,-1);
        int x, y;
        for(int i=5;i>0;i--)
        {
            move = findCellWithCount(2,i);
            if(!move.equals(nothing))
            {
                x = move.getKey();
                y = move.getValue();
                makeMove(2,x,y);
                //System.out.println("Компьютер сделал ход на клетку "+(x+1)+" "+(y+1));
                break;
            }
            move = findCellWithCount(1,i);
            if(!move.equals(nothing))
            {
                x = move.getKey();
                y = move.getValue();
                makeMove(2,x,y);
                //System.out.println("Компьютер сделал ход на клетку "+(x+1)+" "+(y+1));
                break;
            }
        }
    }

    static boolean isWin(int player)
    {
        /*  идея алгоритма проверки победы заключается в обходе всех подходящих точек для начала последовательности элементов с помощью первых двух циклов
            и проверка самой последовательности с помощью третьего цикла
            count - количество присутствующих X/O в последовательности(в зависимости от игрока, сделавшего последний ход)
            border - координата крайней клетки, от которой уже не может начаться отсчёт. Равна SIZE-DOT_WIN.
                Минимальное значение равно 1, так как при совпадении размера и поля требуется выполненение цикла хотя бы 1 раз.
         */

        //Horizontal -
        int border = Math.max(SIZE-DOT_WIN,1);
        for(int i=0; i<border;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                int count = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i+k][j]==DOT[player])
                    {
                        count++;
                    }
                }
                if(count==DOT_WIN)
                {
                    return true;
                }
            }
        }

        //Vertical |
        for(int i=0; i<SIZE;i++)
        {
            for(int j=0;j<(border);j++)
            {
                int count = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i][j+k]==DOT[player])
                    {
                        count++;
                    }
                }
                if(count==DOT_WIN)
                {
                    return true;
                }
            }
        }

        //First Diagonal \
        for(int i=0; i<(border);i++)
        {
            for(int j=0;j<(border);j++)
            {
                int count = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i+k][j+k]==DOT[player])
                    {
                        count++;
                    }
                }
                if(count==DOT_WIN)
                {
                    return true;
                }
            }
        }
        //Second Diagonal /
        for(int i=DOT_WIN-1; i<SIZE;i++)
        {
            for(int j=0;j<(border);j++)
            {
                int count = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i-k][j+k]==DOT[player])
                    {
                        count++;
                    }
                }
                if(count==DOT_WIN)
                {
                    return true;
                }
            }
        }
        return false;
    }

    static Pair<Integer,Integer> findCellWithCount(int player, int count)
    {
        // Усовершенствование метода isWin
        // Метод ищет пустую клетку в ряду(вместе с диагональю), которой не хватает, чтобы получить "count в ряд"
        // player отвечает за то, рассматривать ряды из X или O
        // position позволяет за один проход запоминать позицию пустой клетки

        //Horizontal -
        int border = Math.max(SIZE-DOT_WIN,1);
        for(int i=0; i<border;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                boolean hasEmptyCell = false;
                int haveCount = 0;
                int position = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i+k][j]==DOT[player])
                    {
                        haveCount++;
                    }else if(map[i+k][j]==DOT[0])
                    {
                        hasEmptyCell=true;
                        position=k;
                    }
                }
                if(haveCount==count&&hasEmptyCell)
                {
                    return new Pair<>(i+position,j);
                }
            }
        }

        //Vertical |
        for(int i=0; i<SIZE;i++)
        {
            for(int j=0;j<(border);j++)
            {
                boolean hasEmptyCell = false;
                int haveCount = 0;
                int position = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i][j+k]==DOT[player])
                    {
                        haveCount++;
                    }else if(map[i][j+k]==DOT[0])
                    {
                        hasEmptyCell=true;
                        position=k;
                    }
                }
                if(haveCount==count&&hasEmptyCell)
                {
                    return new Pair<>(i,j+position);
                }
            }
        }

        //First Diagonal \
        for(int i=0; i<(border);i++)
        {
            for(int j=0;j<(border);j++)
            {
                boolean hasEmptyCell = false;
                int haveCount = 0;
                int position = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i+k][j+k]==DOT[player])
                    {
                        haveCount++;
                    }else if(map[i+k][j+k]==DOT[0])
                    {
                        hasEmptyCell=true;
                        position=k;
                    }
                }
                if(haveCount==count&&hasEmptyCell)
                {
                    return new Pair<>(i+position,j+position);
                }
            }
        }
        //Second Diagonal /
        for(int i=DOT_WIN-1; i<SIZE;i++)
        {
            for(int j=0;j<(border);j++)
            {
                boolean hasEmptyCell = false;
                int haveCount = 0;
                int position = 0;
                for(int k=0; k<DOT_WIN; k++)
                {
                    if(map[i-k][j+k]==DOT[player])
                    {
                        haveCount++;
                    }else if(map[i-k][j+k]==DOT[0])
                    {
                        hasEmptyCell=true;
                        position=k;
                    }
                }
                if(haveCount==count&&hasEmptyCell)
                {
                    return new Pair<>(i-position,j+position);
                }
            }
        }
        return new Pair<>(-1,-1);

    }

    static void makeMove(int player, int x, int y)
    {
        map[x][y]=DOT[player];
        windowField.changeIcon(windowField.cells[x][y],player);

    }

    static boolean isMapFull()
    {
        for(int i=0; i<SIZE; i++)
        {
            for(int j=0; j<SIZE;j++)
            {
                if(map[i][j]==DOT[0])
                {
                    return false;
                }
            }

        }
        return true;
    }

    static void initMap(int new_size)
    {
        SIZE = new_size;
        map = new char[SIZE][SIZE];
        for(int i=0; i<SIZE; i++)
        {
            for(int j=0; j<SIZE;j++)
            {
                map[i][j]=DOT[0];
            }

        }
    }
}