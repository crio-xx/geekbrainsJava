package crio;

public class Corgi extends Dog
{
    public Corgi(String name, String color, int yearOfBirth) {
        super(name, color, yearOfBirth);

        restrictionJumping = 0.2;
        restrictionRunning = 400;
        restrictionSwimming = 6;
    }

}