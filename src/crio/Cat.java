package crio;

public class Cat {
    private final String name;
    private boolean satiety;
    private int appetite;

    Cat(String name, int appetite)
    {
        this.name = name;
        this.appetite = appetite;
        this.satiety = (appetite==0);
    }

    void eat(Plate plate)
    {
        if(plate.decreaseFood(appetite))
        {
            satiety = true;
            appetite = 0;
        }
    }

    void printInfo()
    {
        System.out.println("Cat: "+name +", appetite: "+appetite+", satiety: " + satiety );
    }
}
