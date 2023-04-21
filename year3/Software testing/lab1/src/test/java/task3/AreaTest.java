package task3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class AreaTest {

    @Test
    public void trillianRoom() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(new Human("Trillian"));
        Room room = new Room(false, peopleIn);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room);
        Area area = new Area(rooms);
        Assert.assertTrue(area.findTrillianRoom(rooms) == room);
    }

    @Test
    public void trillianRoom2() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(new Human("Trillian"));
        Room room = new Room(false, peopleIn);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(null);
        rooms.add(room);
        Area area = new Area(rooms);
        Assert.assertTrue(area.findTrillianRoom(rooms) == room);
    }

    @Test
    public void trillianRoom3() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(new Human("NoTrillian"));
        Room noTrillianRoom = new Room(false, peopleIn);
        ArrayList<Creature> trillianPeopleIn = new ArrayList<>();
        trillianPeopleIn.add(new Human("Trillian"));
        Room trillianRoom = new Room(false, trillianPeopleIn);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(null);
        rooms.add(noTrillianRoom);
        rooms.add(trillianRoom);
        Area area = new Area(rooms);
        Assert.assertTrue(area.findTrillianRoom(rooms) == trillianRoom);
    }

    @Test
    public void noTrillianRoom() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(new Human("NoTrillian"));
        Room room = new Room(false, peopleIn);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room);
        Area area = new Area(rooms);
        Assert.assertTrue(area.findTrillianRoom(rooms) == null);
    }

    @Test
    public void noTrillianRoom2() {
        Room room = new Room(false, null);
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room);
        Area area = new Area(rooms);
        Assert.assertTrue(area.findTrillianRoom(rooms) == null);
    }
}
