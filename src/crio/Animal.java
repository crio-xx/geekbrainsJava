package crio;

public class Animal {

    protected String name;
    protected final String color;
    protected final int yearOfBirth;

    public Animal(String name, String color, int yearOfBirth)
    {
        this.name = name;
        this.color = color;
        this.yearOfBirth = yearOfBirth;
    }

    void jump(double height)
    {
        log("jump",height,true);
    }

    void run(double distance)
    {
        log("run",distance,true);
    }

    void swim(double distance)
    {
        log("swim",distance,true);
    }

    protected void log(String act, double distance ,boolean check)
    {
        System.out.println(name+" ("+act+" "+distance+" m): "+check);
    }

}
