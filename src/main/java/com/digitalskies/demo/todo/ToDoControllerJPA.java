package com.digitalskies.demo.todo;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@SessionAttributes("name")
public class ToDoControllerJPA {

    private ToDoRepository toDoRepository;

    Logger logger= LoggerFactory.getLogger(getClass());

    public ToDoControllerJPA(ToDoService toDoService, ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }



    @RequestMapping("/")
    String listToDo(ModelMap modelMap){



        var toDos = toDoRepository.findByUsername(getLoggedInUsername());
        modelMap.addAttribute("ToDos",toDos);

        return "listToDo";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    String showNewToDoPage(ModelMap modelMap){
        String username = getLoggedInUsername();
        var toDo=new ToDo();
        toDo.setUsername(username);
        toDo.setDescription("");
        toDo.setTargetDate(LocalDate.now().plusYears(1));
        toDo.setDone(false);
        modelMap.put("todo",toDo);
        return "add-to-do";
    }



    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    String addNewToDo(ModelMap modelMap, @Valid ToDo toDo, BindingResult bindingResult){

        logger.debug("adding to do {}", toDo.toString());
        if(bindingResult.hasErrors()){

            for(ObjectError error: bindingResult.getAllErrors()){
                logger.debug("error adding todo {}",error);
            }

            return "add-to-do";
        }

        String username = getLoggedInUsername();

        toDo.setUsername(username);

        toDoRepository.save(toDo);

        return "redirect:/";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    String showUpdateToDoPage(ModelMap modelMap,@RequestParam int id){

        ToDo toDo=toDoRepository.findById(id).get();

        modelMap.put("todo",toDo);


        return "updateToDo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    String updateToDo(ModelMap modelMap,@Valid ToDo todo){

        toDoRepository.save(todo);


        return "redirect:/";
    }

    @RequestMapping(value = "delete-todo", method = RequestMethod.GET)
    String deleteToDo(@RequestParam int id){

    toDoRepository.deleteById(id);


        return "redirect:/";
    }

    private String getLoggedInUsername() {
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        return "user";
    }
}
