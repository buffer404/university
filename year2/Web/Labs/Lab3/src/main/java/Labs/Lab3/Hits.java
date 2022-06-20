package Labs.Lab3;


import java.io.Serializable;

public class Hits  implements Serializable {
    private Double x;
    private Double y;
    private Double r;
    public boolean hit = false;
    public Hits(Double x, Double y, Double r){
        this.r = r;
        this.x = x;
        this.y = y;
        if(x<=0 && y>=0){
            if (y*y<=r*r-x*x){
                hit= true;
            }
        }
        else if(x>=0 && y>=0){
            if (y<=(-2*x)+(r)){
                hit= true;
            }
        }
        else if (x>=0 && y<=0){
            if(x<=r && Math.abs(y)<=r){
                hit= true;
            }
        }
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public String toString() {
        return "Hits{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                '}';
    }
}
