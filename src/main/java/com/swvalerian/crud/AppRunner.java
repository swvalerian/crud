package com.swvalerian.crud;

import com.swvalerian.crud.model.Developer;
import com.swvalerian.crud.repository.JavaIODevRepImpl;
import com.swvalerian.crud.repository.SkillRepository;
import com.swvalerian.crud.view.SkillView;

public class AppRunner {
    public static void main(String[] args) {
//        SkillView skillView = new SkillView();
//        skillView.showMenu();

        JavaIODevRepImpl dev = new JavaIODevRepImpl();

        System.out.println(dev.getAll());;
        System.out.println();

        System.out.println(dev.getId(3L));
        System.out.println();

        dev.save(new Developer(5, "Vinni", "Puh", new SkillRepository().getAll()));

        dev.update(new Developer(1, "ChiCHi", "GaGa", new SkillRepository().getAll()));

        System.out.println("Вновь сформировавшийся список стал таким: " + dev.getAll());;
        System.out.println();

        dev.deleteById(5l);
        System.out.println("Вновь сформировавшийся список стал таким: " + dev.getAll());;
        System.out.println();
    }
}
