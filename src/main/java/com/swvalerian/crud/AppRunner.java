package com.swvalerian.crud;

import com.swvalerian.crud.controller.DevController;
import com.swvalerian.crud.controller.TeamController;
import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.model.Team;
import com.swvalerian.crud.repository.JavaIODevRepImpl;
import com.swvalerian.crud.repository.JavaIOTeamRepImpl;
import com.swvalerian.crud.repository.SkillRepository;
import com.swvalerian.crud.view.SkillView;

public class AppRunner {
    public static void main(String[] args) {
        DevController devController = new DevController();

        devController.getAll();
        devController.create(20,"Тимур", "Родригез");
        devController.read(20l);
        devController.update(20, "Al", "Pachino");
        devController.read(20l);
        devController.delete(20l);
        devController.read(20l);
        System.out.println();

        TeamController teamController = new TeamController();

        teamController.getAll();

        teamController.create(200, "чебуратор");

        System.out.println("ЗАпись состоялась");

        teamController.read(200);
        teamController.update(200, "ТЕРМИНАТОР");
        teamController.read(200);
        teamController.delete(80);
        teamController.getAll();
    }
}
