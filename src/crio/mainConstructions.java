package crio;

public class mainConstructions {
    public static void main(String[] args)
    {
        // Задать целочисленный массив, состоящий из элементов 0 и 1. С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int[] bytes = {1,1,0,0,1,0,1,1,0,0};
        System.out.println("Task 1: ");
        printArray(bytes);

        for(int i=0; i<bytes.length; i++)
        {
            if(bytes[i]==0)
            {
                bytes[i]=1;
            }else
            {
                bytes[i]=0;
            }
        }
        printArray(bytes);
        // Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        int[] someArray = new int[8];

        for (int i=0; i<someArray.length; i++)
        {
            someArray[i]=i*3;
        }
        System.out.println("Task 2: ");
        printArray(someArray);

        // Задать массив, пройти по нему циклом, и числа меньшие 6 умножить на 2;
        int[] anotherArray = {1,5,3,2,11,4,5,2,4,8,9,1};
        System.out.println("Task 3: ");
        printArray(anotherArray);
        for(int i=0; i<someArray.length;i++)
        {
            if(anotherArray[i]<6)
            {
                anotherArray[i]*=2;
            }
        }
        printArray(anotherArray);

        // Создать квадратный двумерный целочисленный массив, и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        int[][] squareArray = new int[7][7];
        System.out.println("Task 4: ");
        for(int i=0; i<squareArray.length;i++)
        {
            squareArray[i][i]=1;
        }
        for(int i=0;i<squareArray.length;i++)
        {
            for(int j=0; j<squareArray[i].length;j++)
            {
                System.out.print(squareArray[i][j]);
            }
            System.out.println();
        }

        // Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        int[] arrayMinMax = {1,5,3,2,11,4,5,2,4,8,9,111};
        System.out.println("Task 5: ");
        printArray(arrayMinMax);
        System.out.println("Min elem: "+findMin(arrayMinMax)+"; Max elem: "+findMax(arrayMinMax));

        System.out.println("Task 6: ");
        int[] balanceArray1={2,2,2,1,2,2,10,1};
        printArray(balanceArray1);
        System.out.println(checkBalance(balanceArray1));

        int[] balanceArray2={1,1,1,2,1};
        printArray(balanceArray2);
        System.out.println(checkBalance(balanceArray2));

        int[] balanceArray3={1,2,3,4,5};
        printArray(balanceArray3);
        System.out.println(checkBalance(balanceArray3));

        System.out.println("Task 7: ");
        int[] ShiftTest = {1,2,3,4,5,6,7,8,9,10};
        printArray(ShiftTest);
        //n=45
        shift(ShiftTest,45);
        printArray(ShiftTest);

        shift(ShiftTest,-85);
        printArray(ShiftTest);

    }

    static int findMin(int[] array)
    {
        int min = array[0];
        for (int v:array)
        {
            if(v<min)
            {
                min = v;
            }
        }
        return min;
    }
    static int findMax(int[] array)
    {
        int max = array[0];
        for (int v:array)
        {
            if(v>max)
            {
                max = v;
            }
        }
        return max;
    }

    // Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
    static boolean checkBalance(int[] array)
    {
        if(array.length==0)
        {
            return false;
        }
        long sumAll=0; //long чтобы избежать переполнения при суммировании всех элементов
        for(int v:array)
        {
            sumAll+=v;
        }
        long sumLeft = 0;
        for(int v:array)
        {
            sumLeft+=v;
            long sumRight = sumAll-sumLeft;
            if(sumRight==sumLeft)
            {
                return true;
            }
        }
        return false;
    }

    // *** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным), при этом метод должен сместить все элементымассива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    static void shift(int[] array, int n)
    {
        n = n%array.length;
        int leftShifts,rightShifts;
        if(n>0)
        {
            rightShifts=n;
            leftShifts=array.length-n;
        }else
        {
            rightShifts=array.length+n;
            leftShifts=-n;
        }
        if(leftShifts<=rightShifts)
        {
            for(int i=0; i<leftShifts;i++)
            {
                shiftElementsL(array);
            }
        }else
        {
            for(int i=0;i<rightShifts;i++)
            {
                shiftElementsR(array);
            }
        }
    }

    // *** Вспомогательный метод, осуществляющий сдвиг на один элемент вправо.
    static void shiftElementsR(int[] array)
    {
        if(array.length>1)
        {
            int tmp=array[array.length-1];
            for(int i=array.length-1; i>0;i--)
            {
                array[i]=array[i-1];
            }
            array[0]=tmp;
        }
    }
    // *** Вспомогательный метод, осуществляющий сдвиг на один элемент влево.
    static void shiftElementsL(int[] array)
    {
        if(array.length>1)
        {
            int tmp=array[0];
            for(int i=0; i<array.length-1;i++)
            {
                array[i]=array[i+1];
            }
            array[array.length-1]=tmp;
        }
    }

    // Метод для вывода одномерного массива
    static void printArray(int[] array)
    {
        for(int v:array)
        {
            System.out.print(v+" ");
        }
        System.out.println();
    }
}
