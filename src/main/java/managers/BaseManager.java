package managers;

import java.util.List;

public abstract class BaseManager<T> {

    public abstract <T> List<T> read();

    public abstract <T> void add(T t);

    public abstract <T> void delete(int id);

    public abstract <T> int getSize();


}
