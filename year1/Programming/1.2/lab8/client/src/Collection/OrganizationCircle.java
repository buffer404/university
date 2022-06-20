package Collection;

import javafx.scene.shape.Circle;

public class OrganizationCircle extends Circle {
    private int id;

    public OrganizationCircle(int i, int i1, int i2) {
        super(i,i1,i2);
    }

    public int getIdd() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
