package Labs.Lab3.Bean;

import Labs.Lab3.DBhits;
import Labs.Lab3.HandShake;
import Labs.Lab3.Hits;
import Labs.Lab3.Util;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

@Named("TableBean")
@ApplicationScoped
public class TableBean implements Serializable {
    private final long serialVersionUID = 1;
    private ArrayList<Hits> hitsArrayList = new ArrayList<>();
    private Double x;
    private Double y;
    private Double r;
    private Hits hits;
    private TableBean(){}

    public void clear(){
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getSessionId(false);
        System.out.println("clear");
        DBhits.ClearDB(id);
        hitsArrayList.clear();
    }

    public void add() throws SQLException {
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getSessionId(false);
        System.out.println(id);
        if(x!=null && y != null && r!=null){
            if(Util.getCountsOfDigits(y)>=7){
                y = Util.cutDouble(y);
            }
            if(Util.getCountsOfDigits(r)>=7){
                r = Util.cutDouble(r);
            }
            hits = new Hits(x,y,r);
            DBhits.addDB(hits, id);
            hitsArrayList = DBhits.loadDB(id);
            System.out.println("load collection");
        }
        for(Hits arrayList : hitsArrayList){
            System.out.println(arrayList.toString());
        }
    }

    public ArrayList<Hits> getHitsArrayList() {
        return hitsArrayList;
    }

    public void setHitsArrayList(ArrayList<Hits> hitsArrayList) {
        this.hitsArrayList = hitsArrayList;
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

    public Hits getHits() {
        return hits;
    }

    public void setHits(Hits hits) {
        this.hits = hits;
    }
}
