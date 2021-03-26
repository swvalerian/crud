package com.swvalerian.crud.repository;

import com.swvalerian.crud.model.Skill;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkillRepository {
    final private File file = new File("src\\main\\resources\\files\\skills.txt");

    // из строки получаем обьект типа Skill для добавления его в список
    private Skill convertStringToSkill(String s) {
        // разбиваем строку на две части
        String[] str = s.split(",");
        // первая часть - это число, загоняем его в ID
        Integer index = Integer.decode(str[0]);
        //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
        String name = str[1].substring(0, (str[1].length() - 1));
        return new Skill(index, name);
    }

    // из обьекта получаем строку (для добавления её в файл)
    private String convertSkillToString(Skill skill) {
        String name = skill.getId().toString() + "," + skill.getName() + "/" + "\n";
        return name;
    }

    // метод принимает обьект из списка, конвертирует с помощью приват метода в строку и пишет строку в файл!
    private void writeBuf(Skill s) {
        try (OutputStream out = new FileOutputStream(file, true);
             BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out)))
        {
            bufWrite.write(convertSkillToString(s)); // получаем наш формат строки в файле, куда и будет сделана запись
            bufWrite.flush(); // из буфера в файл за раз!

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // приватный метод, создание списка из файла, который повторяется по коду много раз.
    private List<Skill> getListFF() {
        List<Skill> skillList = new ArrayList<>();

        try (InputStream in = new FileInputStream(file);
             BufferedReader bufRead = new BufferedReader(new InputStreamReader(in))) {

            return bufRead.lines().map(s -> convertStringToSkill(s)).collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        }
       return skillList;
    }

    public List<Skill> getAll() {
        return getListFF();
    }

    public Skill getById(Integer id) {
        return getListFF().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Skill> update(Skill skills) {
        List<Skill> skillList = getListFF();

        file.delete(); // удалим файл, дабы сформировать новый!

        return skillList.stream().peek
                (s -> { if (s.getId().equals(skills.getId())) {
                            s.setName(skills.getName());
                            }
                        writeBuf(s);
                })
                .collect(Collectors.toList());
    }

    public Skill save(Skill skills) {
        // сохраним обьект
            writeBuf(skills);
        return skills;
    }

    public void deleteById(Integer id) {
        List<Skill> skillList = getListFF();
        skillList.removeIf(skill -> skill.getId().equals(id));

        file.delete(); // удалим файл, дабы сформировать новый!

        skillList.forEach(this::writeBuf); // пишем новый файл

    }
}