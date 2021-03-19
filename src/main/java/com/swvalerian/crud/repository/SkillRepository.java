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

        try (InputStream in = new FileInputStream(file)) {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));

            bufRead.lines().forEach(s -> {
                String name = "";
                Integer id;
                // разбиваем строку на две части
                String[] str = s.split(",");
                // первая часть - это число, загоняем его в ID
                id = Integer.decode(str[0]);
                //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
                name = str[1].substring(0, (str[1].length() - 1));
                // осталось лишь. наполнить наш список
                skillList.add(new Skill(id, name));

            });

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        }
        // и вернуть список тому, кому он был нужен
        return skillList;
    }

    public SkillRepository() {

    }
}
