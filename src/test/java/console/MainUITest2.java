package console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainUITest2 {

    @BeforeEach
    void setUp() {
        File text = new File("src/main/resources/file/b.txt");
        Scanner in = null;
        try {
            in = new Scanner(text);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void main() {
    }
}