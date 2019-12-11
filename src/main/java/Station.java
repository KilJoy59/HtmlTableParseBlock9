
public class Station {

    private String name;
    private String lineNumber;

    public Station(String name, String lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }

    public Station() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
