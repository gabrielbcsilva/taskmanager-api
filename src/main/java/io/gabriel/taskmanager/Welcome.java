package io.gabriel.taskmanager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Welcome", description = "TaskAPI welcome message")
@RestController
public class Welcome {

    @Operation(summary = "Welcome to TaskAPI application!")
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to TaskAPI back-end application!";
    }
}
