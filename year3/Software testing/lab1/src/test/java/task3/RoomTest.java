package task3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class RoomTest {


    @Test
    public void trillianHereTest() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(new Human("Trillian"));
        Room room = new Room(false, peopleIn);
        Assert.assertTrue(room.isTrillianHere());
    }

    @Test
    public void trillianHereTest2() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(new Human("noTrillian"));
        peopleIn.add(new Human("Trillian"));
        Room room = new Room(false, peopleIn);
        Assert.assertTrue(room.isTrillianHere());
    }

    @Test
    public void noTrillianHere() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(new Human("NOTrillian"));
        Room room = new Room(false, peopleIn);
        Assert.assertFalse(room.isTrillianHere());
    }

    @Test
    public void nobodyHere() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        Room room = new Room(false, peopleIn);
        Assert.assertFalse(room.isTrillianHere());
    }

    @Test
    public void nobodyHere2() {
        Room room = new Room(false, null);
        Assert.assertFalse(room.isTrillianHere());
    }

    @Test
    public void nobodyHere3() {
        ArrayList<Creature> peopleIn = new ArrayList<>();
        peopleIn.add(null);
        Room room = new Room(false, peopleIn);
        Assert.assertFalse(room.isTrillianHere());
    }
}
