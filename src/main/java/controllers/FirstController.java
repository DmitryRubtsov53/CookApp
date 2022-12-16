package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping ("/")
    public String hello () {
       return "Привет!";
    }

    @GetMapping("/info")
    public String recipe() {
        return  "Студент: Дмитрий Рубцов. " +"\n" +
                "Проект: Кулинарная книга. " +"\n" +
                "Дата создания: 08/12/2022. " + "\n" +
                "Сборник рецептов традиционных блюд России.";
    }
}
