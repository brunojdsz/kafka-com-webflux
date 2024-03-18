package br.com.udemy.tasks.controller;

import br.com.udemy.tasks.controller.converter.TaskDTOConverter;
import br.com.udemy.tasks.controller.dto.TaskDTO;
import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private TaskDTOConverter converter;

    @GetMapping
    public Mono<List<TaskDTO>> getTasks(){
        return service.list()
                .map(converter::convertList);
    }

    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody Task task){
        return service.insert(task)
                .map(converter::convert);
    }

}
