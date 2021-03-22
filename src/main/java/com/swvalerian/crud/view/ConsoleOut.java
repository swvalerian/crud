package com.swvalerian.crud.view;

public class ConsoleOut {
    final String hello = "\n!!!===          Вас приветствует программа по работе с базой данных         ===!!! \n" +
            "\nРабота осуществляется по командам, которые вводит пользователь" +
            "\nДоступны следующие команды: \n";

    final String userCom = "list -=> Выводит весь список из файла\n" +
            "get (номер элемента) -=> Выведет элемент из файла по указанному номеру \n" +
            "create (номер элемента) (значение элемента) -=> Создаст элемент в файле под указанным номером \n" +
            "update (номер элемента) (значение элемента) -=> Заменит значение элемента на указанное \n" +
            "delete (номер элемента) -=> удалит элемент из файла \n" +
            "exit -=> ВЫХОД ИЗ ПРОГРАММЫ \n" +
            "help -=> вызов справки\n";

    final String exampleCom = "get 2 -=> данная команда удалит из файла элемент под номер 2 \n" +
            "update 10 HelloJava! -=> изменилось значение элемента под номер 10 и стало = HelloJava! \n" +
            "list -=> выводит номера всех элементов и их значения! \n";

    public String getHello() {
        return hello;
    }

    public String getUserCom() {
        return userCom;
    }

    public String getExampleCom() {
        System.out.println("\n=====================++++++++++++===================");
        return exampleCom;
    }
}
