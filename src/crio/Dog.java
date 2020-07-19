package crio;

public class Dog extends Animal{

    protected double restrictionJumping = 0.5;
    protected double restrictionRunning = 500;
    protected double restrictionSwimming = 10;

    public Dog(String name, String color, int yearOfBirth) {
        super(name, color, yearOfBirth);
    }

    @Override
    void jump(double height)
    {
        super.log("jump",height,height <= restrictionJumping);
    }

    @Override
    void run(double distance)
    {
        super.log("run",distance,distance <= restrictionRunning);
    }

    @Override
    void swim(double distance)
    {
        super.log("swim",distance,distance <= restrictionSwimming);
    }

}
