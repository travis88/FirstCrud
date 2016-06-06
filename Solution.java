package com.javarush.test.level17.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String person = reader.readLine();
            String[] info = person.split(" ");
            int id = 0;
            Person human = null;
            if (!info[0].equals("-c")) {
                id = Integer.parseInt(info[1]);
                human = allPeople.get(id);
            }
            switch (info[0]) {
                case "-c":
                    if (info[2].equals("м")) {
                        allPeople.add(Person.createMale(info[1], new Date(info[3])));
                    } else if (info[2].equals("ж")) {
                        allPeople.add(Person.createFemale(info[1], new Date(info[3])));
                    } else {
                        System.out.println("Неверно указан пол");
                    }
                    break;
                case "-u":
                    human.setName(info[2]);
                    if (info[3].equals("м")) { human.setSex(Sex.MALE); }
                    else if (info[3].equals("ж")) { human.setSex(Sex.FEMALE); }
                    else {
                        System.out.println("Неверно указан пол");
                    }
                    human.setBirthDay(new Date(info[4]));
                    break;
                case "-d":
                    allPeople.remove(id);
                    break;
                case "-i":
                    String sex = "";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YYYY", Locale.ENGLISH);
                    if (human.getSex().equals(Sex.MALE)) { sex = "м"; }
                    else { sex = "ж"; }
                    System.out.println(human.getName() + " " + sex + " " + simpleDateFormat.format(human.getBirthDay()));
                    break;
                default:
                    System.out.println("Неизвестная комманда!"); break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Список людей
        for (Person p : allPeople) System.out.println(p);
    }
}
