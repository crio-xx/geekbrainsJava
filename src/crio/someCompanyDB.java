package crio;

public class someCompanyDB
{
    public static void main(String[] args)
    {
        Person[] personArray = new Person[5]; // Вначале объявляем массив объектов
        // * Создать массив из 5 сотрудников
        personArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
        personArray[1] = new Person("Frolov Igor","DevOps","frolovigor@list.ru","+792312785",45000,49);
        personArray[2] = new Person("Sidorenko Vlad","Programmer","sidvlad@mail.ru","+749512875",40000,43);
        personArray[3] = new Person("Skripkin Sasha","Tester","scripsha@gmail.com","891355417",25000,25);
        personArray[4] = new Person("Romanenko Nikita","Analyst","romNik@yahoo.com","+692872222",35000,41);

        // * С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
        for(Person v:personArray)
        {
            if(v.getAge()>40)
            {
                v.printInformation();
            }
        }

    }
}