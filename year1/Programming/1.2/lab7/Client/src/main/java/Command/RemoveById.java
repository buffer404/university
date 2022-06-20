package Command;

import Collection.Organization;
import Interface.CheckUpdate;
import Interface.ClearId;
import Interface.Command;
import NET.Send;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class RemoveById implements Serializable, Command {
    String s;
    int id;
    public RemoveById(int id){
        this.id=id;
    }

    @Override
    public void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException {
        for(Organization i : collection){
            if(i.getId()==id){
                if (CheckUpdate.checkUpdate(user, id)){
                    try {
                        ClearId.clearCollection(user, id);
                    } catch (SQLException throwables) {
                        System.err.println("Произошла ошибка при исполнении SQL команды");
                    }
                    s+=("Организация "+i.getFullName()+" успешно удалена");
                }
                else {
                    s+=("Вы не можете удалить данную оргпнизацию");
                }
            }
        }
        Send.send(new Msg(s));
    }
}
