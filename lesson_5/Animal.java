package lesson_5;
import java.util.Random;


/*    Java. Уровень 1. Урок 5
            1. Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.
            2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
    В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
            3. У каждого животного есть ограничения на действия
(бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,; прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ;
 плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
            4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат. (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
            5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
*/
public abstract class Animal {
            private String typeAnimal;
            private String name;
            private float maxRun;
            private float maxSwim;
            private float maxJump;

    static final int SWIM_FAIL = 0;
    static final int SWIM_OK = 1;
    static final int SWIM_NONE = -1;
    private final Random random = new Random();
    Animal(String typeAnimal, String name, float maxRun, float maxSwim, float maxJump){
        float jumpDiff = random.nextFloat() * maxJump - (maxJump / 2);
        float runDiff = random.nextFloat() * maxRun - (maxRun / 2);
        float swimDiff = random.nextFloat() * maxSwim - (maxSwim / 2);
        this.typeAnimal = typeAnimal;
        this.name = name;
        this.maxJump = maxJump + jumpDiff;
        this.maxRun = maxRun + runDiff;
        this.maxSwim = maxSwim + swimDiff;

       }
        //getting
        String getTypeAnimal () {
        return this.typeAnimal;
    }
        String getName() {
        return this.name;
    }
        float getMaxRun() {
        return this.maxRun;
    }
        float getMaxSwim() {
        return this.maxSwim;
    }
        float getMaxJump() {
        return this.maxJump;
    }


        protected boolean run(float distance) {
        return (distance <= maxRun);
    }
        protected int swim(float distance) {
        return (distance <= maxSwim) ? SWIM_OK : SWIM_FAIL;
    }
        protected boolean jump(float height) {
        return (height <= maxJump);
    }


}



