package crio;

public class Cat implements Participant{
    private final String name;
    private final int runLimit;
    private final int jumpLimit;

    Cat(String name, int runLimit, int jumpLimit)
    {
        this.name=name;
        this.runLimit=runLimit;
        this.jumpLimit=jumpLimit;
    }

    @Override
    public boolean toPass(Barrier barrier)
    {
        if(barrier instanceof Wall)
        {
            return this.jump((Jumply) barrier);
        }
        else {
            return this.run((Runly) barrier);
        }
    }

    @Override
    public boolean run(Runly barrier)
    {
        if(this.runLimit>=barrier.getLength())
        {
            System.out.println(name + " successfully ran " + barrier.getLength());
        }else
        {
            System.out.println(name + " couldn`t  ran " + barrier.getLength());
        }
        return this.runLimit>=barrier.getLength();
    }

    @Override
    public boolean jump(Jumply barrier)
    {
        if(this.jumpLimit>=barrier.getHight())
        {
            System.out.println(name + " successfully jump " + barrier.getHight());
        }else
        {
            System.out.println(name + " couldn`t  jump " + barrier.getHight());
        }
        return this.jumpLimit>=barrier.getHight();
    }
}
