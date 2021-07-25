package model;

public class Room  implements IRoom{
    protected String roomNumber;
    protected Double price;
    protected RoomType roomType;
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
    public final String getRoomNumber() {
        return roomNumber;
    }

    public final Double getRoomPrice() {
        return price;
    }

    @Override
    public final RoomType getRoomType() {
        return roomType;
    }
    @Override
    public final boolean isFree() {
        return isFree;
    }
}

