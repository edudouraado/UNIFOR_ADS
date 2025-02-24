public class Main {
    public static void main(String[] args) {
        String name = "Alice";
        int age = "25";

        printPerson(name, age);
    }

    public static void printPerson(Person person) {
        System.out.println("Person: " + person.getName() + ", Age: " + person.getAge());
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
