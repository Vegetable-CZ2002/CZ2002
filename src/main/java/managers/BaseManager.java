package managers;

import java.io.IOException;
import java.util.List;

public abstract class BaseManager<T> {

    public abstract <T> List<T> read() throws IOException;

    public abstract <T> void add(T t);

    public abstract <T> void delete(int id) throws IOException;

    public abstract <T> int getSize() throws IOException;

    public abstract <T> void print();

}
