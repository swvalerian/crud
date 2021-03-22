package com.swvalerian.crud.view;

import com.swvalerian.crud.controller.SkillController;
import com.swvalerian.crud.model.Skill;
import java.io.*;

public class KeyBoardInput {

    public void keyRead() { // думаю этот метод надо сделать final
        try (BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in))) {
            String str;
            ConsoleOut out = new ConsoleOut();
            System.out.println(out.getHello() + out.userCom);

            SkillController sc = new SkillController();

            while (true) {
                str = bufRead.readLine();

                // команды: list, get, create, update, delete, exit
                String[] com = str.split(" ");
                switch (com[0]) {
                    case "list" : sc.getAll();
                        break;
                    case "create" : sc.create(Integer.decode(com[1]), com[2]);
                        break;
                    case "get" : sc.read(Integer.decode(com[1]));
                        break;
                    case "update" : sc.update(Integer.decode(com[1]), com[2]);
                        break;
                    case "delete" : sc.delete(Integer.decode(com[1]));
                        break;
                    case "help" :
                        System.out.println(out.getExampleCom());
                        break;
                    case "exit" : return;

                    default :
                        System.out.println("Введите верную команду!");
                }
            }
        } catch (IOException ex) {
            System.err.println("ошибка ввода - вывода");
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Обращение к несуществующему элементу, введите команду верно!");
        }
    }
}