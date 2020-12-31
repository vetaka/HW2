package lesson_5;

/*    Java. Уровень 1. Урок 5
            1. Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.
            2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
    В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
            3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,;
            прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).
            4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат. (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')
            5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
*/

public class HW5 {

    static String nameString;
    static String eventName;
    static String eventResult;

        public static void main(String[] args) {

            Cat cat1 = new Cat("Мурка", 200, 200, 2);
            Cat cat2 = new Cat("Барсик", 200, 200, 2);
            Dog dog = new Dog("Тузик", 500, 500, 0.5f);
            Horse horse = new Horse("Сивка", 1500,1500,3);
            Beard beard = new Beard("Киви", 5, 0,0.2f);
            Animal[] arr = {cat1, cat2, dog, horse,beard};
            float toJump = 3.5f;
            float toRun = 450;
            float toSwim = 10;

            for (int i = 0; i < arr.length; i++) {
                nameString = arr[i].getTypeAnimal() + " " + arr[i].getName() + " может ";

                jumpMarathon(arr[i], toJump);
                runMarathon(arr[i], toRun);
                swimMarathon(arr[i], toSwim);


            }
        }

        private static void jumpMarathon(Animal animal, float distance) {
            eventName = String.format("прыгнуть на %.2f м. Пытается прыгнуть на ", animal.getMaxJump());
            eventResult = (animal.jump(distance)) ? " м и это получилось" : " м. и это не получилось";
            System.out.println(nameString + eventName + distance + eventResult);
        }

        private static void runMarathon(Animal animal, float distance) {
            eventName = String.format("пробежать на %.2f м. Пытается пробежать на ", animal.getMaxRun());
            eventResult = animal.run(distance) ? " м. и это получилось" : " м. и это не получилось";
            System.out.println(nameString + eventName + distance + eventResult);
        }

        private static void swimMarathon(Animal animal, float distance) {
            int swimResult = animal.swim(distance);
            eventName = String.format("проплыть на %.2f м. Попытка проплыть на ", animal.getMaxSwim());
            eventResult = (swimResult == Animal.SWIM_OK) ? " это получилось" : " это не получилось";
            if (swimResult == Animal.SWIM_NONE) {
                eventResult = " это не получилось, т.к. не умеет плавать";
            }
            System.out.println(nameString + eventName + distance + " м и" + eventResult);
        }
    }

