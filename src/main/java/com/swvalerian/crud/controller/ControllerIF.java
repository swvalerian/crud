package com.swvalerian.crud.controller;

import com.swvalerian.crud.model.Skill;
import java.util.List;
import java.util.Optional;

public interface ControllerIF {
    Skill create(Integer id, String name);
    Skill read(Integer id);
    Skill update(Integer id, String name);
    void delete(Integer id);
    List<Skill> getAll();
}
