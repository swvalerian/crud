package com.swvalerian.crud.repository;

import com.swvalerian.crud.model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkillRepository {

    public List<Skill> getAll() {
        File file = new File("src\\main\\resources\\files\\skills.txt");
        List<Skill> skillList = new ArrayList<>();

        try (InputStream in = new FileInputStream(file)) {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));
            // воспользуемся stream API
            bufRead.lines().forEach(s -> {
                String name = "";
                Integer index;
                // разбиваем строку на две части
                String[] str = s.split(",");
                // первая часть - это число, загоняем его в ID
                index = Integer.decode(str[0]);
                //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
                name = str[1].substring(0, (str[1].length() - 1));
                // осталось лишь. наполнить наш список
                skillList.add(new Skill(index, name));
            });
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        }
        return skillList;
    }

    public Skill getById(Integer id) {
        File file = new File("src\\main\\resources\\files\\skills.txt");
        List<Skill> skillList = new ArrayList<>();

        try (InputStream in = new FileInputStream(file)) {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));

            bufRead.lines().forEach(s -> {
                String name = "";
                Integer index;
                // разбиваем строку на две части
                String[] str = s.split(",");
                // первая часть - это число, загоняем его в ID
                index = Integer.decode(str[0]);
                //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
                name = str[1].substring(0, (str[1].length() - 1));
                // осталось лишь. наполнить наш список
                skillList.add(new Skill(index, name));
            });
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        }
        return skillList.get(id - 1); // счет с нуля в списке, а в файле с единицы, делаем кооректировку
    }

    public Skill update(Skill skills) {
        File file = new File("src\\main\\resources\\files\\skills.txt");
        List<Skill> skillList = new ArrayList<>();

        try (InputStream in = new FileInputStream(file)) {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));

            bufRead.lines().forEach(s -> {
                String name = "";
                Integer index;
                // разбиваем строку на две части
                String[] str = s.split(",");
                // первая часть - это число, загоняем его в ID
                index = Integer.decode(str[0]);
                //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
                name = str[1].substring(0, (str[1].length() - 1));
                // осталось лишь. наполнить наш список
                skillList.add(new Skill(index, name));
            });
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        }

        // изменяем элемент в списке
        skillList.forEach(s -> {
            if (s.getId().equals(skills.getId())) {
                s.setName(skills.getName());
            }
        });

        //теперь нужно список записать в файл, т.е. открыть файл на перезапись и записать туда вновь получившийся список
        try (OutputStream out = new FileOutputStream(file, false)) {
            BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out));
            String name = "";
            String index = "";

            for (int i = 0; i < skillList.size(); i++) {
                index = skillList.get(i).getId().toString() + ",";
                name = skillList.get(i).getName() + "/";

                bufWrite.write(index + name + "\n");
            }
            bufWrite.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skills;
    }

    public Skill save(Skill skills) {

        File file = new File("src\\main\\resources\\files\\skills.txt");
        // сохраним обьект, создадим поток и завернем его в буфер
        try (OutputStream out = new FileOutputStream(file, true)) {
            BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out));
            String name = "";
            String index = "";
            // формируем из id и элемента списка - строку, для записи в файл
            index = skills.getId().toString() + ",";
            name = skills.getName() + "/";

            bufWrite.write(index + name + "\n"); // получаем наш формат строки в файле, куда и будет сделана запись
            bufWrite.flush(); // из буфера в файл за раз!
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skills;
    }

    public void deleteById(Integer id) {
        File file = new File("src\\main\\resources\\files\\skills.txt");
        List<Skill> skillList = new ArrayList<>();

        try (InputStream in = new FileInputStream(file)) {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));

            bufRead.lines().forEach(s -> {
                String name = "";
                Integer index;
                // разбиваем строку на две части
                String[] str = s.split(",");
                // первая часть - это число, загоняем его в ID
                index = Integer.decode(str[0]);
                //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
                name = str[1].substring(0, (str[1].length() - 1));

                // осталось лишь. наполнить наш список, но пропустим элемент, который нужно удалить.
                if (id != index) {
                    skillList.add(new Skill(index, name));
                }
            });
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        }

        //теперь нужно список записать в файл, в списке будет недоставать того самого элемента, который надо было удалить
        try (OutputStream out = new FileOutputStream(file, false)) {
            BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out));
            String name = "";
            String index = "";

            for (int i = 0; i < skillList.size(); i++) {
                index = skillList.get(i).getId().toString() + ",";
                name = skillList.get(i).getName() + "/";

                bufWrite.write(index + name + "\n");
            }
            bufWrite.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}