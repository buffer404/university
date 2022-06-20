package Interface;

import Collection.Organization;

import java.io.IOException;
import java.util.ArrayDeque;

public interface Command {
    void strat(ArrayDeque<Organization> collection) throws IOException, ClassNotFoundException;
}
