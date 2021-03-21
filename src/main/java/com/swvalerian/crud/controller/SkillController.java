package com.swvalerian.crud.controller;

import com.swvalerian.crud.model.Skill;
import com.swvalerian.crud.repository.SkillRepository;

import java.util.List;

public class SkillController implements ControllerIF {
    final private SkillRepository skillRepository = new SkillRepository();

    public SkillController() {
    }

    @Override
    public Skill create(Integer id, String name) {
        System.out.println("Создали новую запись в файле!" + id + " : " + name);
        skillRepository.save(new Skill(id, name));
        return new Skill(id, name);
    }

    @Override
    public Skill read(Integer id) {
        System.out.println("Получили следующую запись из файла: ");
        System.out.println(skillRepository.getById(id));;
        return skillRepository.getById(id);
    }

    @Override
    public Skill update(Integer id, String name) {
        System.out.println("Запись в файле обновлена");
        skillRepository.update(new Skill(id, name));
        return new Skill(id, name);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("Запись номер " + id + " удалена");
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> getAll() {
        System.out.println("Вывод всех записей из файла: \n" + skillRepository.getAll());
        return skillRepository.getAll();
    }
}
