package io.gabriel.taskmanager.exception;

public class MailSendException extends CustomException {
    public MailSendException() {
        super("Falha ao enviar email!");
    }
}
