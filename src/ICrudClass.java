import java.io.FileNotFoundException;
import java.io.IOException;

public interface ICrudClass {

    void create() throws FileNotFoundException;
    void update() throws IOException;
    void delete() throws IOException;


}