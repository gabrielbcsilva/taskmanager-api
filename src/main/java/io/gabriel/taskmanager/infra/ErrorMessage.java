package io.gabriel.taskmanager.infra;

public enum ErrorMessage {
    UNAUTHORIZED("Usuário não autorizado!"),
    FORBIDDEN("Acesso negado!"),
    MAIL_SEND_ERROR("Falha ao enviar email!"),
    BAD_REQUEST("Requisição mal sucedida!"),
    NOT_PERFORMED("Ação não realizada"),
    WORKSPACE_CONFLICT("Espaço de trabalho já existe!"),
    WORKSPACE_NOT_FOUND("Espaço de trabalho não encontrado!"),
    FOLDER_CONFLICT("Pasta já existe!"),
    FOLDER_NOT_FOUND("Pasta não encontrada!"),
    TASK_CONFLICT("Tarefa já existe!"),
    TASK_NOT_FOUND("Tarefa não encontrada!"),
    SUBTASK_CONFLICT("Subtarefa já existe!"),
    SUBTASK_NOT_FOUND("Subtarefa não encontrada!"),
    PROJECT_CONFLICT("Projeto já existe!"),
    PROJECT_NOT_FOUND("Projeto não encontrado!"),
    USER_EMAIL_CONFLICT("Email informado já está sendo usado !"),
    USER_NOT_FOUND("Usuário não encontrado!"),
    INTERNAL_SERVER_ERROR("Erro interno desconhecido!");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
