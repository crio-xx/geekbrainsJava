package crio;

import java.security.SecureRandom;
import java.util.Scanner;

public class hw3 {
    public static void main(String[] args) {
        System.out.println("Task 1.");
        numberGuessing(9,3);

        System.out.println("Task 2.");
        wordGuessing();
    }


    /* 1. Написать программу, которая загадывает случайное число от 0 до 9,
          и пользователю дается 3 попытки угадать это число.
          При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
          После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет».
     */

    static void numberGuessing(int maxNumber, int maxTryCount)
    {
        Scanner sc = new Scanner(System.in);
        byte isContinue = 1;
        boolean isGuessed = false;
        while(isContinue==1)
        {
            int answer = getRandomNumber(maxNumber);
            System.out.println("Старт игры. Компьютер загадал случайное число от 0 до "+maxNumber+". У вас есть "+ maxTryCount + " попыток.");
            for(int i=1; i<=maxTryCount;i++)
            {
                System.out.print("Попытка №"+i+". Введите число: ");
                int guess = sc.nextInt();

                if(guess==answer)
                {
                    System.out.println("Поздравляем. Вы отгадали число! Игра окончена.");
                    isGuessed = true;
                    break;
                }else if(answer>guess)
                {
                    System.out.println("Введенное число меньше чем загаданное.");
                }else if(answer<guess)
                {
                    System.out.println("Введенное число больше чем загаданное.");
                }
            }
            if(!isGuessed)
            {
                System.out.println("К сожалению, попытки закончились и отгадать число не получилось. Игра окончена.");
            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            isContinue = sc.nextByte();
            if(isContinue==0)
            {
                System.out.println("Спасибо за игру! До новых встреч.");
            }
        }
    }

    /*  2. При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
        сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
        Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
     */
    static void wordGuessing()
    {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
                "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        // Поиск максимальной длины слова для создания "шаблона" слова.
        int maxSize = words[0].length();
        for(String v:words)
        {
            maxSize = Math.max(maxSize,v.length());
        }
        maxSize*=2; //Для маскировки настоящей длины слова.

        int indexWord = getRandomNumberV2(words.length-1);
        String answer = words[indexWord];

        System.out.println("Старт игры. Компьютер загадал случайное слово. Попробуйте отгадать его. Для выхода введите \"exit\"");
        System.out.println(answer);
        String guess = "";
        while (!answer.equalsIgnoreCase(guess))
        {
            System.out.print("Введите ваш вариант ответа: ");
            Scanner sc = new Scanner(System.in);
            guess = sc.nextLine();
            if(guess.equalsIgnoreCase("exit"))
            {
                System.out.println("Жаль, что у вас не получилось отгадать. Было загадано слово " + answer + ". Игра окончена.");
                break;
            }
            System.out.println(checkAnswer(answer,guess.toLowerCase(),maxSize));
        }
        if(answer.equalsIgnoreCase(guess))
        {
            System.out.println("Поздравляю! Слово отгадано. Игра окончена");
        }
    }
    static String checkAnswer(String answer, String guess,int maxSize)
    {
        String s = "";

        int range = Math.min(answer.length(),guess.length());
        for(int i=0; i<range;i++)
        {
            if(answer.charAt(i)==guess.charAt(i))
            {
                s+=answer.charAt(i);
            }else
            {
                s+="#";
            }
        }
        for(int i=range; i<maxSize;i++)
        {
            s+="#";
        }
        return s;
    }


    //Вспомогательные методы для генерации рандомных чисел
    static int getRandomNumber(int n)
    {
        // (n+1), так как Math.random() генерирует число из [0;1), после чего происходит округление вниз
        // следовательно если нам нужны числа из [0,n], то нужно умножить на (n+1)
        return (int)(Math.random()*(n+1));
    }
    static int getRandomNumberV2(int n)
    {
        //Также, чтобы сгенерированные значения были из диапазона [0;n] устанавливаем границу (n+1)
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(n+1);
    }
}

