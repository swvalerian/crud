package com.swvalerian.crud.repository;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Developer(id, firstName, lastName, List<Skill> skills)

public class JavaIODevRepImpl implements DeveloperRepository {
    final private File file = new File("src\\main\\resources\\files\\developers.txt");

    private Developer convertStringToDev(String s) {
        s = s.substring(0, s.length()-1); // получили строку без последнего знака "/"

        String[] str = s.split(",");
        String firstName = str[1];
        String lastName = str[2];

        List<Skill> skillList = new ArrayList<>();
        int i = 3;
        while (i < str.length) {
            skillList.add(new SkillRepository().getById(Integer.decode(str[i])));
            i++;
        }

        Integer id = Integer.decode(str[0]);
        return new Developer(id, firstName, lastName, skillList);
    }

    private List<Developer> getListFFD() {
        List<Developer> devList = new ArrayList<>();

            try (InputStream in = new FileInputStream(file);
                 BufferedReader bufRead = new BufferedReader(new InputStreamReader(in))) {

                return bufRead.lines().map(s -> convertStringToDev(s)).collect(Collectors.toList());

            } catch (FileNotFoundException e) {
                System.err.println("Файл не найден!");
            } catch (IOException ex) {
                System.err.println("ошибка ввода - вывода");
            }
        return devList;
    }

    @Override // реализовал!
    public List<Developer> getAll() {
        return getListFFD();
    }

    @Override // реализовал!
    public Developer getId(Long id) { // работа со значением Long предполагает доп изврат в плане приведения типов ))
        return getListFFD().stream().filter( s -> s.getId().equals(id.intValue())).findFirst().orElse(null);
    }

    @Override // реализовал!
    public Developer save(Developer developer) {
            writeBuf(developer);
        return developer;
    }

    // приватный метод, уменьшаем основной код
    private void writeBuf(Developer d) {
        try (OutputStream out = new FileOutputStream(file, true);
             BufferedWriter bufWrite = new BufferedWriter(new OutputStreamWriter(out)))
        {
            bufWrite.write(convertDevToString(d)); // получаем наш формат строки в файле, куда и будет сделана запись
            bufWrite.flush(); // из буфера в файл за раз!

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertDevToString(Developer d) {
        String s = d.getId().toString() + "," + d.getFirstName() + "," + d.getLastName() + "/" +"\n";
        return s; // да можно не создавать переменную а сразу всё выражение записать в оператор. Пусть пока так побудет))
    }

    @Override
    public List<Developer> update(Developer developer) {
        List<Developer> listDev = getListFFD();

        file.delete();

        return listDev.stream().peek(s ->
                {
                    if (s.getId().equals(developer.getId())) {
                        s.setFirstName(developer.getFirstName());
                        s.setLastName(developer.getLastName());
                    }
                writeBuf(s); // пишем построчно в новый файл.
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> listDev = getListFFD();
        listDev.removeIf(s -> s.getId().equals(id.intValue()));

        file.delete();

        listDev.forEach(this::writeBuf);

    }
}
