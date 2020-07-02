package crio;

public class firstHW {
    public static void main(String[] args)
    {
        // 2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte b = 0;
        short t = 150;
        int i = 100000;
        long l = 3*10^9;

        char c = 'a';

        boolean k = false;

        float f = 3.14f;
        double d = 5.;

        //Проверка и вызов методов.
        System.out.println("2 * (3 + (5 / 2)) = "+task3(2,3,5,2));

        System.out.println("10<=(5+6)<=20? "+task4(5,6));

        if(task6(-8))
        {
            task5(-8);
        }

        task7("SomeName");

        task8(2100);
    }

    //3. метод вычисляющий выражение a * (b + (c / d))
    static float task3(int a, int b, int c, int d)
    {
        return a * (b + ((float)c / d));
    }
    //4. метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно)
    static boolean task4(int a, int b)
    {
        return (a+b)>=10&&(a+b)<=20;
    }
    //5. метод, который должен напечатать в консоль положительное ли число передали
    static void task5(int a)
    {
        String s= a>=0?"Число положительное":"Число отрицательное";
        System.out.println(s);
    }
    //6. метод, который должен вернуть true, если число отрицательное;
    static boolean task6(int a)
    {
        return a<0;
    }
    //7. метод, который вывести в консоль сообщение «Привет, указанное_имя!»;
    static void task7(String name)
    {
        System.out.println("Привет, "+name+"!");
    }
    //8. метод, который определяет является ли год високосным
    static void task8(int year)
    {
        if((year%4==0&&year%100!=0)||(year%400==0))
        {
            System.out.println("Год "+year+" високосный!");
        }else
        {
            System.out.println("Год "+year+" не високосный!");
        }

    }
}