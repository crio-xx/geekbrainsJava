package crio;

import javafx.util.Pair;
import java.util.Scanner;

public class tikTakToe {
    //Constants
    static char[] DOT = {'.','X','O'}; // empty, first player, second player
    static char[][] map;
    static int SIZE = 3;
    static int DOT_WIN = 3;

    public static void main(String[] args) {
        int option;
        do {
            getSettingsAndInterface();
            initMap(SIZE);
            printMap();
            boolean isSomeoneWin = false;
            while (!isMapFull())
            {
                humanMove();
                printMap();
                if(isWin(1))
                {
                    System.out.println("Поздравляем с победой! Игра окончена.");
                    isSomeoneWin=true;
                    break;
                }
                if (isMapFull())
                {
                    break;
                }
                aiMove();
                printMap();
                if(isWin(2))
                {
                    System.out.println("К сожалению Компьютер победил. Игра окончена.");
                    isSomeoneWin=true;
                    break;
                }
            }
            if(!isSomeoneWin)
            {
                System.out.println("Ничья. Игра окончена.");
            }
            option = getNumber("Хотите сыграть ещё раз? 1(да)/0(нет) ","Повторите ввод. (Введите цифру 1 или 0.)");
            if(option!=1)
            {
                System.out.println("Спасибо за игру! Пока.");
            }
        }while (option == 1);

        //printMap();
    }


    static void getSettingsAndInterface()
    {
        String errorMessage = "Повторите ввод, так как ввод был некорректен.";
        //Приветствие
        System.out.println("Начало игры \"Крестики-Нолики\". Игра происходит с компьютером.");
        int choice;
        do {
            choice = getNumber("Вы ходите поменять текущие настройки? \nРазмер поля: "+SIZE+", Фишек до победы: "+DOT_WIN+", Ваша фишка: '"+DOT[1]+"'? 1(да)/0(нет): ",errorMessage);
        }while (choice<0||choice>1);
        if(choice==1)
        {
            getFieldSettings(errorMessage);
        }

    }
    static void getFieldSettings(String errorMessage)
    {
        System.out.println("Ввод параметров игры.");
        do
        {
            SIZE = getNumber("Введите желаемую длину стороны поля (от 2 до 9 включительно): ", errorMessage);
            if(SIZE<2||SIZE>9)
            {
                System.out.println(errorMessage);
            }else
            {
                break;
            }
        }while(true);

        do
        {
            DOT_WIN = getNumber("Введите желаемую длину фишек для победы (от 2 до "+SIZE+" включительно): ",errorMessage);
            if(DOT_WIN<2||DOT_WIN>SIZE)
            {
                System.out.println(errorMessage);
            }else
            {
                break;
            }
        }while (true);

        char dot = getCharXorO(errorMessage);
        if(dot == 'O')
        {
            DOT[1]='O';
            DOT[2]='X';
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
                System.out.println("Компьютер сделал ход на клетку "+(x+1)+" "+(y+1));
                break;
            }
            move = findCellWithCount(1,i);
            if(!move.equals(nothing))
            {
                x = move.getKey();
                y = move.getValue();
                makeMove(2,x,y);
                System.out.println("Компьютер сделал ход на клетку "+(x+1)+" "+(y+1));
                break;
            }
        }
    }


    static void humanMove()
    {
        String errorMessage = "Введите координаты пустой клетки (формат: X Y) в промежутке от 1 до " + SIZE;
        Scanner scanner = new Scanner(System.in);
        int x,y;
        do{
            System.out.print("Ваш ход: ");
            if(scanner.hasNextInt())
            {
                x = scanner.nextInt();
                if(scanner.hasNextInt())
                {
                    y = scanner.nextInt();
                    if(!isCellValid(x-1,y-1))
                    {
                        System.out.println(errorMessage);
                        scanner.nextLine();
                    }else
                    {
                        break;
                    }
                }else
                {
                    System.out.println(errorMessage);
                    scanner.nextLine();
                }
            }else
            {
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }while (true);
        makeMove(1,x-1,y-1);
    }

    static void makeMove(int player, int x, int y)
    {
        map[x][y]=DOT[player];
    }

    static boolean isCellValid(int x, int y)
    {
        return (x>=0)&&(y>=0)&&(x<SIZE)&&(y<SIZE)&&(map[x][y]==DOT[0]);
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

    static void printMap()
    {
        //Вывод шапки таблицы
        System.out.print("  ");
        for(int i=1; i<=SIZE;i++)
        {
            System.out.print(i+" ");
        }
        System.out.println();

        //Вывод поля вместе с левой колонкой
        for(int i=0; i<SIZE;i++)
        {
            System.out.print(i+1+" ");
            for(int j=0; j<SIZE;j++)
            {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
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
    static int getNumber(String offer, String errorMessage)
    {
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.print(offer);
            if(scanner.hasNextInt())
            {
                return scanner.nextInt();
            }
            System.out.println(errorMessage);
            scanner.nextLine();
        }while (true);
    }
    static char getCharXorO(String errorMessage)
    {
        Scanner scanner = new Scanner(System.in);
        String input;
        do
        {
            System.out.print("Введите желаемую фишку, за которую хотите играть (только X или O): ");
            if(scanner.hasNextLine())
            {
                input = scanner.nextLine();
                if(input.charAt(0)=='O'||input.charAt(0)=='X')
                {
                    return input.charAt(0);
                }else
                {
                    System.out.println("Требовалось ввести X или O");
                }
            }else
            {
                System.out.println(errorMessage);
                scanner.nextLine();
            }
        }while (true);
    }
}
