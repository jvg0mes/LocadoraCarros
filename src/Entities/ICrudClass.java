package Entities;

import java.io.IOException;

public interface ICrudClass {

    void create() throws IOException;
    void update() throws IOException;
    void delete() throws IOException;


}
