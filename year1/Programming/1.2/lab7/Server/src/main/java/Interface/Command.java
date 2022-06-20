package Interface;

import Collection.Organization;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayDeque;

public interface Command {
    void strat(ArrayDeque<Organization> collection, String user) throws IOException, ClassNotFoundException, SQLException;
}
