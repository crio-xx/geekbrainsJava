package crio;

// * Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
public class Person {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    //* Конструктор класса должен заполнять эти поля при создании объекта;
    Person(String fullName, String position, String email, String phoneNumber, int salary, int age)
    {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }
    Person()
    {
        this("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);
    }

    int getAge()
    {
        return age;
    }
    //* Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
    void printInformation()
    {
        System.out.println("ФИО: "+fullName);
        System.out.println("Должность: "+position);
        System.out.println("Почта: "+email);
        System.out.println("Телефон: "+phoneNumber);
        System.out.println("Зарплата: "+salary);
        System.out.println("Возраст: "+age);
        System.out.println();

    }

}
