package example.controllers;

import example.domain.TodoItem;
import example.repositories.TodoRepository;
import example.services.ResourceLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class SimpleController {

    private final ResourceLoaderService resourceLoaderService;
    private TodoRepository todoRepository;

    @Autowired
    public SimpleController(ResourceLoaderService resourceLoaderService, TodoRepository todoRepository) {
        this.resourceLoaderService = resourceLoaderService;
        this.todoRepository = todoRepository;
    }

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public String resources() {
        String data = null;
        try {
            data = resourceLoaderService.loadResourceDataByClassPath("classpath:static/data.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TodoItem> index() {
        return todoRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TodoItem getById(@PathVariable(value = "id") Long id) throws Exception {
        TodoItem todoItem = todoRepository.findById(id)
                .orElseThrow(() -> new Exception("Item " + id + " not found"));
        return todoItem;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public TodoItem post(@Valid @RequestBody TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public TodoItem edit(@Valid @RequestBody TodoItem todoItem
    ) throws Exception {
        TodoItem todoItemToUpdate = todoRepository.findById(todoItem.getId())
                .orElseThrow(() -> new Exception("Item " + todoItem.getId() + " not found"));

        todoItemToUpdate.setDescription(todoItem.getDescription());
        todoItemToUpdate.setDetails(todoItem.getDetails());
        todoItemToUpdate.setDone(todoItem.isDone());

        final TodoItem updatedItem = todoRepository.save(todoItemToUpdate);
        return updatedItem;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long id) throws Exception {
        TodoItem todoItemToDelete = todoRepository.findById(id)
                .orElseThrow(() -> new Exception("Item " + id + " not found"));

        todoRepository.delete(todoItemToDelete);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
