package com.swvalerian.crud.repository;

import com.swvalerian.crud.model.Skill;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SkillRepository {
    final private File file = new File("src\\main\\resources\\files\\skills.txt");

    // приватный метод, создание списка из файла, который повторяется по коду много раз.
    private List<Skill> getListFF() {
        List<Skill> skillList = new ArrayList<>();

        try (InputStream in = new FileInputStream(file)) {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));

            return bufRead.lines().map(s -> {
                String name = "";
                Integer index;
                // разбиваем строку на две части
                String[] str = s.split(",");
                // первая часть - это число, загоняем его в ID
                index = Integer.decode(str[0]);
                //вторая часть строки - значение элемента, но не вся строка, поэтому конец строки обрезаем
                name = str[1].substring(0, (str[1].length() - 1));
                // осталось лишь. наполнить наш список
                return new Skill(index, name);
            }).collect(Collectors.toList());

        } catch (NumberFormatException e) {
            System.err.println("Ошибка, скорее всего у вас пустая строка в файле!");
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

        //теперь нужно список записать в файл, т.е. открыть файл на перезапись и записать туда вновь получившийся список
        try (OutputStream out = new FileOutputStream(file, false);
             BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out)))
        {
            return skillList.stream()
                    .map(s -> {
                        if (s.getId().equals(skills.getId())) {
                            s.setName(skills.getName());
                        }

                        String name = "";
                        String index = "";
                        index = s.getId().toString() + ",";
                        name = s.getName() + "/";

                        try {
                            bufWrite.write(index + name + "\n");
                            bufWrite.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return s;
                    })
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    public Skill save(Skill skills) {
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
        List<Skill> skillList = getListFF();

        //теперь нужно список записать в файл, кроме элемента, который требовалось удалить, его мы пропустим и всё
        try (OutputStream out = new FileOutputStream(file, false)) {
            BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out));
            String name = "";
            String index = "";

            for (int i = 0; i < skillList.size(); i++) {

                if (id != skillList.get(i).getId()) {
                    index = skillList.get(i).getId().toString() + ",";
                    name = skillList.get(i).getName() + "/";
                    bufWrite.write(index + name + "\n");
                }

            }
            bufWrite.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}