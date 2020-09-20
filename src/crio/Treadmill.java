package crio;

public class Treadmill implements Runly, Barrier{

    int length;

    Treadmill(int length)
    {
        this.length = length;
    }

    public int getLength()
    {
        return this.length;
    }

    public int getSize()
    {
        return getLength();
    }
}
