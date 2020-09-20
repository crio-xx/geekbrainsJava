package crio;

public class Main {
    public static void main(String[] args) {

        Wall firstWall = new Wall(5);
        Wall secondWall = new Wall(10);
        Wall thirdWall = new Wall(15);

        Treadmill firstTreadmill = new Treadmill(20);
        Treadmill secondTreadmill = new Treadmill(40);
        Treadmill thirdTreadmill = new Treadmill(60);

        Cat someCat = new Cat("Murzik",10,4);
        Cat anotherCat = new Cat("Snezhok",25,7);

        Human amateur = new Human("Alex", 55,9);
        Human professional = new Human("Bolt", 150,25);

        Robot minEquipment = new Robot("T200",30,10);
        Robot bestEquipment = new Robot("T800",2000, 100);

        Barrier[] barriers = {firstTreadmill, firstWall, secondTreadmill, secondWall, thirdTreadmill, thirdWall};

        final Participant[] participants = new Participant[]{someCat, amateur, minEquipment, anotherCat, professional, bestEquipment};

        for(Participant participant:participants)
        {
            System.out.println("The next participant.");
            for(Barrier barrier:barriers)
            {
                if(!participant.toPass(barrier))
                {
                    break;
                }
            }
            System.out.println();
        }

    }
}
