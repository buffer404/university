package task3;

import java.util.ArrayList;

public class Room {

    private boolean isLocked;
    private ArrayList<Creature> peopleIn;

    public Room(boolean isLocked, ArrayList<Creature> peopleIn) {
        this.isLocked = isLocked;
        this.peopleIn = peopleIn;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public ArrayList<Creature> getPeopleIn() {
        return peopleIn;
    }

    public boolean isTrillianHere() {
        if (peopleIn != null) {
            for (Creature creature : peopleIn) {
                if (creature != null) {
                    if (creature.getName() != null) {
                        if (creature.getName().equals("Trillian")) return true;
                    }
                }
            }
        }
        return false;
    }
}
