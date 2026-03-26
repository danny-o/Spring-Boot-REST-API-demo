package com.digitalskies.demo.todo;

//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Service
public class ToDoService {

    private static List<ToDo> toDos = new ArrayList<>();

    static {
        toDos.add(new ToDo(1,"DigitalSkies","Learn AWS", LocalDate.now().plusYears(1),false));

       toDos.add(new ToDo(2,"DigitalSkies","Learn Azure", LocalDate.now().plusYears(2),false));

       toDos.add(new ToDo(3,"DigitalSkies","Learn Google Cloud", LocalDate.now().plusYears(3),false));


    }
    List<ToDo> findByUserName(String username){

        Predicate<? super ToDo> predicate = todo->todo.getUsername().equals(username);
        return toDos.stream().filter(predicate).toList();
    }

    ToDo findByID(int id){

        Predicate <? super ToDo> predicate = todo->todo.getId()==id;
        return toDos.stream().filter(predicate).findFirst().get();
    }

    void addToDo(String userName,String description,boolean done){
        toDos.add(new ToDo(toDos.size()+1,userName,description,LocalDate.now(),done));
    }

    void updateToDo(ToDo updatedToDo){
        ToDo todo=findByID(updatedToDo.getId());

        todo.setDescription(updatedToDo.getDescription());

        todo.setDone(updatedToDo.isDone());

    }

    void deleteToDo(int id){
        Predicate<? super ToDo> predicate = todo-> todo.getId() ==id;

        toDos.removeIf(predicate);
    }
}
