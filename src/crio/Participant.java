package crio;

public interface Participant {

    boolean toPass(Barrier barrier);
    boolean run(Runly barrier);
    boolean jump(Jumply barrier);

}
