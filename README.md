# Task Manager Spring Boot Application

## Overview

Esta aplicação Spring Boot é projetada para o gerenciamento de tarefas e subtarefas. Ela fornece APIs RESTful para gerenciar tarefas, subtarefas e usuários. A aplicação utiliza o Swagger para documentação e teste das APIs.
## Accessing Swagger UI

Você pode acessar o Swagger UI para a documentação da sua API no seguinte URL:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

O Swagger UI fornece uma interface conveniente para explorar e testar os endpoints expostos por esta aplicação.
## API Endpoints

### Subtask API

**Base URL:** `/subtask`

- **`GET /subtask`**
    - **Summary:** Get subtask by ID
    - **Parameters:** `subtaskId` (UUID, required)
    - **Response:** `SubtaskResponse`

- **`GET /subtask/from-task`**
    - **Summary:** List subtasks by task ID
    - **Parameters:** `taskId` (UUID, required)
    - **Response:** `List<SubtaskResponse>`

- **`POST /subtask`**
    - **Summary:** Create a new subtask
    - **Request Body:** `NewSubtaskDto` (JSON)
    - **Response:** `SubtaskResponse`

- **`PUT /subtask`**
    - **Summary:** Update a subtask
    - **Request Body:** `UpdateSubtaskDto` (JSON)
    - **Response:** `SubtaskResponse`

- **`PUT /subtask/priority`**
    - **Summary:** Change subtask priority
    - **Request Body:** `ChangePriorityDto` (JSON)
    - **Response:** `SubtaskResponse`

- **`PUT /subtask/status`**
    - **Summary:** Change subtask status
    - **Request Body:** `ChangeStatusDto` (JSON)
    - **Response:** `SubtaskResponse`

- **`DELETE /subtask`**
    - **Summary:** Remove a subtask
    - **Parameters:** `subtaskId` (UUID, required)
    - **Response:** `No Content`

### Task API

**Base URL:** `/task`

- **`GET /task`**
    - **Summary:** List the tasks of a specific user
    - **Parameters:** `userId` (UUID, required)
    - **Response:** `List<TaskResponse>`

- **`POST /task`**
    - **Summary:** Create a new task
    - **Request Body:** `NewTaskDto` (JSON)
    - **Response:** `TaskResponse`

- **`PUT /task`**
    - **Summary:** Update a task
    - **Request Body:** `UpdateTaskDto` (JSON)
    - **Response:** `TaskResponse`

- **`PUT /task/status`**
    - **Summary:** Change task status
    - **Request Body:** `ChangeStatusDto` (JSON)
    - **Response:** `TaskResponse`

- **`PUT /task/priority`**
    - **Summary:** Change task priority
    - **Request Body:** `ChangePriorityDto` (JSON)
    - **Response:** `TaskResponse`

- **`DELETE /task/remove`**
    - **Summary:** Remove a task
    - **Parameters:** `taskId` (UUID, required)
    - **Response:** `No Content`

### User API

**Base URL:** `/user`

- **`GET /user`**
    - **Summary:** Get all users
    - **Response:** `List<UserResponse>`

- **`GET /user/by-id`**
    - **Summary:** Get user by ID
    - **Parameters:** `userId` (UUID, required)
    - **Response:** `UserResponse`

- **`POST /user`**
    - **Summary:** Add a new user
    - **Request Body:** `NewUserDto` (JSON)
    - **Response:** `UserResponse`

- **`PUT /user`**
    - **Summary:** Update a specific user
    - **Request Body:** `UpdateUserDto` (JSON)
    - **Response:** `UserResponse`

## Dependencies

- Spring Boot
- Swagger
- Jakarta Validation

## Running the Application

1. Java >= 17 (Verifique se o JAVA 17 está instalado e apontado na variável JAVA_HOME).
2. Clone.
3. Run(Pela sua IDE de preferência ou ):
   ```bash
   ./mvnw spring-boot:run
