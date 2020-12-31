package lesson_5;

public class Cat extends Animal {

    Cat(String name, float maxRun, float maxSwim, float maxJump) {
        super("Кот", name, maxRun, maxSwim, maxJump);
    }
    @Override
    protected int swim(float distance){
        return Animal.SWIM_NONE;
    }
}
