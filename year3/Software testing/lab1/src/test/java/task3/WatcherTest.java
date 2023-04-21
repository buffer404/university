package task3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WatcherTest {

    @Test
    public void heroicTry() {
        Robot robot = new Robot(RobotState.GO, LocationInRoom.CROSSING_THE_ROOM, new Room(false, new ArrayList<Creature>()));
        Assert.assertTrue(new Watcher().watch(robot).getTryValue() == TryValue.HEROIC);
    }

    @Test
    public void ordinaryTry() {
        Robot robot = new Robot(RobotState.GO, LocationInRoom.IN_THE_CORNER, new Room(false, new ArrayList<Creature>()));
        Assert.assertTrue(new Watcher().watch(robot).getTryValue() == TryValue.ORDINARY);
    }
}
