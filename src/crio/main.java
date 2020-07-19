package crio;

public class main {
    public static void main(String[] args) {
        Animal[] pets = new Animal[5];

        pets[0]= new Animal(
                "Squirrel",
                "Redhead",
                2019
        );

        pets[1]= new Cat(
                "Snezhok",
                "White",
                2016
        );

        pets[2]= new Dog(
                "SharikBobik",
                "Gray",
                2017
        );

        pets[3]= new Corgi(
                "Rex",
                "Beige",
                2020
        );

        pets[4]= new Retriever(
                "Dilan",
                "Brown",
                2015
        );

        int countCat = 0;
        int countDog = 0;

        for (Animal pet : pets)
        {
            pet.run(350);
            pet.swim(7);
            pet.jump(0.51);
            System.out.println();
            if (pet instanceof Dog)
            {
                countDog++;
            } else if (pet instanceof Cat)
            {
                countCat++;
            }
        }
        System.out.println("Количество собак: " + countDog);
        System.out.println("Количество кошек: " + countCat);
        System.out.println("Количество остальных животных: " + (pets.length-countCat-countDog));

    }

}
