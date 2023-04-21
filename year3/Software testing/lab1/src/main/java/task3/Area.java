package task3;

import java.util.ArrayList;

public class Area {

    private ArrayList<Room> rooms;

    public Area(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Room findTrillianRoom(ArrayList<Room> rooms) {
        if (rooms != null) {
            for (Room room : rooms) {
                if (room != null) {
                    if (room.isTrillianHere()) return room;
                }
            }
        }
        return null;
    }
}
