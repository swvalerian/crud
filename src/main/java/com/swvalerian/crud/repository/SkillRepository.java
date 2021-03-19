package com.swvalerian.crud.repository;

import com.swvalerian.crud.model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkillRepository {

    public List<Skill> getAll() {
        File file = new File("src\\main\\resources\\files\\skills.txt");
        List<Skill> skillList = new ArrayList<>();
        String name = "";
        Integer id;
        //String[] str = new String[100]; // создал массив на сто элементов
        int i = 0;

        try (InputStream in = new FileInputStream(file)) {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));


            bufRead.lines().forEach(s -> {
                String[] str = s.split(","); // разбиваем строку на две части

                // первая часть - это число, загоняем его как ID в экземпляр обьетка Skill
                skillList.add(new Skill(Integer.decode(str[0]),
                        //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
                        str[1].substring(0, (str[1].length() - 1))));
            });

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        }

        return skillList;
    }

    public SkillRepository() {

    }
}
