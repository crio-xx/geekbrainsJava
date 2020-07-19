package crio;

public class Retriever extends Dog
{
    public Retriever(String name, String color, int yearOfBirth) {
        super(name, color, yearOfBirth);

        restrictionJumping = 0.8;
        restrictionRunning = 600;
        restrictionSwimming = 14;
    }
}