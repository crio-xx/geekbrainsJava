package crio;

public class Cat extends Animal
{
    static double restrictionJumping = 2;
    static double restrictionRunning = 200;

    public Cat(String name, String color, int yearOfBirth) {
        super(name, color, yearOfBirth);
    }

    @Override
    void jump(double height)
    {
        super.log("jump",height,(height<=restrictionJumping));
    }

    @Override
    void run(double distance)
    {
        super.log("run",distance,(distance<=restrictionRunning));
    }

    @Override
    void swim(double distance)
    {
        super.log("swim",distance,false);
    }
}
