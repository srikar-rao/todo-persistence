package com.moduler.todo.integration;

import com.moduler.todo.entity.TodoEntity;
import com.moduler.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@Testcontainers
@SpringBootTest
public class TodoEntityRepoContainerTest extends PostgresContainerBase {

    //Require to run docker running in local

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void whenSave_thenFindById() {

        TodoEntity todo = new TodoEntity();
        todo.setTitle("Test Todo");
        todo.setDescription("Test Description");
        todo.setCompleted(false);

        TodoEntity savedTodo = todoRepository.save(todo);
        Optional<TodoEntity> retrievedTodo = todoRepository.findById(savedTodo.getId());

        assertThat(retrievedTodo).isPresent();
        assertThat(retrievedTodo.get().getTitle()).isEqualTo("Test Todo");
    }

}
