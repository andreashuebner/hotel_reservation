package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, Double price, RoomType roomType, boolean isFree) {
        super(roomNumber, 0.0, roomType, true);
    }

    @Override
        public String toString() {
            return "Room Number: " + roomNumber + " Price: " + price + " Room Type: " + roomType;
        }

}
