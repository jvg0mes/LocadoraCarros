import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvService{

    private String line = "";
    private String splitBy = ";";
    private String fileName;
    public List<String[]> result = new ArrayList<>();

    public CsvService(String fileName) {
        this.fileName = fileName;
    }

    public void read() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.fileName), StandardCharsets.UTF_8);
        for(String l:lines){
            this.result.add(l.split(","));
        }
    }

    public void write(String line) throws FileNotFoundException {
        try {
            Files.write(Paths.get(this.fileName), ("\n"+line).getBytes(),
                    StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void setLine(int lineNumber, String data) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(this.fileName), StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);
        Files.write(Paths.get(this.fileName), lines, StandardCharsets.UTF_8);
    }

    public void deleteLine(int lineNumber) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(this.fileName), StandardCharsets.UTF_8);
        lines.remove(lineNumber - 1);
        Files.write(Paths.get(this.fileName), lines, StandardCharsets.UTF_8);
    }

    public String[] get(int id){
        for(String[] l: this.result){
            if (String.valueOf(id).equals(l[0].split(";")[0])){
                return l[0].split(";");
            }
        }
        String[] emptyList = {};
        return(emptyList);
    };

    public int getLineNumber(int id){
        int count = 0;
        for(String[] l: this.result){
            if (String.valueOf(id).equals(l[0].split(";")[0])){
                return count;
            }
            count++;
        }
        return(-1);
    };

    public String getMaxId() throws IOException {
      read();
      return((this.result.get(this.result.size()-1)[0]).split(";")[0]);
    };

    public long countLines() {

        long lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;

    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}