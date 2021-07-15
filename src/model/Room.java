package model;

public class Room  implements IRoom{
    String roomNumber;
    Double price;
    RoomType roomType;
    boolean isFree;

    public Room(String roomNumber, Double price, RoomType roomType, boolean isFree) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = isFree;
    }

    public String toString() {
        return "Room Number: " + roomNumber + " Price: " + price + " Room Type: " + roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }
    @Override
    public boolean isFree() {
        return isFree;
    }
}
