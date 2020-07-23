package crio;

public class Main {
    public static void main(String[] args) {

        Cat[] cats = new Cat[3];

        cats[0] = new Cat("Murzik",12);
        cats[1] = new Cat("Tom",18);
        cats[2] = new Cat("Snezhok",15);

        Plate plate = new Plate(35);

        logAll(cats,plate);

        eatAllCats(cats, plate);

        logAll(cats,plate);

        plate.addFood(10);

        eatAllCats(cats,plate);

        logAll(cats,plate);

    }

    static void eatAllCats(Cat[] cats, Plate plate)
    {
        for (Cat cat : cats)
        {
            cat.eat(plate);
        }
    }

    static void logAllCats(Cat[] cats)
    {
        for (Cat cat : cats)
        {
            cat.printInfo();
        }
    }

    static void logAll(Cat[] cats, Plate plate)
    {
        logAllCats(cats);
        plate.printTnfo();
        System.out.println();
    }
}

