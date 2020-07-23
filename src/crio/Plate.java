package crio;

public class Plate
{
    private int food;

    Plate(int food)
    {
        this.food = food;
    }

    boolean addFood(int food)
    {
        if(food<0)
        {
            return false;
        }

        this.food += food;
        return true;
    }

    boolean decreaseFood(int food)
    {
        if(this.food < food)
        {
            return false;
        }
        this.food -= food;
        return true;
    }

    void printTnfo()
    {
        System.out.println("Food in plate: " + food);
    }

}
