package com.swvalerian.crud;

import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        List<Skill> newList = new ArrayList<>(); //

        SkillRepository rep = new SkillRepository();

        newList.addAll(rep.getAll());

        System.out.println("\nНовый список элементов полученный из файла\n");
        newList.stream().forEach(s -> System.out.println(s.getId() + " : " + s.getName()));

        int index = 12;
        System.out.println("\nЭлемент с индексом [" + index + "] = "  + rep.getById(index).getName());



    }
}
