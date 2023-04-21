package task3;


public class Robot implements Creature{
    private RobotState robotState;
    private LocationInRoom location;
    private Room currentRoom;

    public Robot(RobotState robotState, LocationInRoom location, Room room) {
        this.robotState = robotState;
        this.location = location;
        this.currentRoom = room;
        this.currentRoom.getPeopleIn().add(this);
    }

    public void sit() {
        robotState = RobotState.SIT;
        location = LocationInRoom.IN_THE_CORNER;
    }

    public void stand() {
        robotState = RobotState.STAND;
    }

    public Room findTrillian(Area area) {
        if (this.currentRoom.isTrillianHere()) return this.currentRoom;
        return area.findTrillianRoom(area.getRooms());
    }

    public void goToTrillianRoom(Room room) throws Exception {
        if (room == null) throw new RobotException("Триллиан нигде нет(");
        if (this.currentRoom.isLocked()) throw new RobotException("Не могу выйти из первой комнаты");
        if (room.isLocked()) throw new RobotException("Комната Триллиан закрыта");
        else {
            this.currentRoom = room;
            room.getPeopleIn().add(this);
        }
    }

    public void go() {
        robotState = RobotState.GO;
        location = LocationInRoom.CROSSING_THE_ROOM;
    }

    public void stop() {
        robotState = RobotState.STAND;
        location = LocationInRoom.IN_FRONT_OF_TRILLIAN;
    }

    public String watch() {
        return "Вижу плечо хз";
    }

    public RobotState getRobotState() {
        return robotState;
    }

    public LocationInRoom getLocation() {
        return location;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    @Override
    public String getName() {
        return null;
    }


}
