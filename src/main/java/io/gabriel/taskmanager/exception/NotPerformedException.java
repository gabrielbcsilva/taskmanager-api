package io.gabriel.taskmanager.exception;

public class NotPerformedException extends CustomException {
    public NotPerformedException() {
        super("Ação não realizada!");
    }
}
