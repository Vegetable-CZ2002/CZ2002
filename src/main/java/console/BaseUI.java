package console;

import java.io.IOException;

public abstract class BaseUI {
    abstract void print() throws IOException;

    abstract void mainUI() throws IOException;
}
