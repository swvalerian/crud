package com.swvalerian.crud;

import com.swvalerian.crud.view.DevView;
import com.swvalerian.crud.view.TeamView;

import java.io.IOException;

public class AppRunner {
    public static void main(String[] args) {
            DevView devView = new DevView();
            devView.showMenu();



            // почему тут ошибка?
            TeamView teamView = new TeamView();
            teamView.showMenu();
    }
}
