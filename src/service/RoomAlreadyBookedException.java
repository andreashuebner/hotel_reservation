package service;

public class RoomAlreadyBookedException extends Exception {

    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
