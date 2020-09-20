package crio;

public class Wall implements Jumply, Barrier{

    int hight;

    Wall(int hight)
    {
        this.hight=hight;
    }

    public int getHight()
    {
        return this.hight;
    }

    public int getSize()
    {
        return getHight();
    }
}
