package com.swvalerian.crud;

import com.swvalerian.crud.controller.SkillController;
import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        List<Skill> newList = new ArrayList<>(); //

        SkillRepository rep = new SkillRepository();

        int index = 11;
        System.out.println("\nЭлемент с индексом [" + index + "] = "  + rep.getById(index).getName());

        newList.addAll(rep.getAll());

        System.out.println("\nНовый список элементов полученный из файла\n");
        newList.stream().forEach(s -> System.out.println(s.getId() + " : " + s.getName()));

        rep.update(new Skill(16, "Freeman")); // ничего не запишет, т.к. этот метод изменяет имеющийся список в файле

        rep.save(new Skill(16, "Freeman")); // а вот так запишет новый элемент

        // а теперь изменим элемент
        rep.update(new Skill(1, "Have a nice day!"));

        // проверим последний метод, удалим элемент
        rep.deleteById(2);


        // ТЕСТИРОВАНИЕ КОНТРОЛЛЕРА
        SkillController sc = new SkillController();

        //создание записи
        sc.create(2,"Валерон");

        // вывод всех записей
        sc.getAll();

        // удаление одной записи
        sc.delete(16);
    }
}
