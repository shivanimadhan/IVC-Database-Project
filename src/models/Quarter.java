package models;

public class Quarter 
{
    String name;
    int year;

    public Quarter(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public static Quarter previous(String current, int year) {
        return switch (current) {
            case "Spring" -> new Quarter("Winter", year);
            case "Winter" -> new Quarter("Fall", year - 1);
            case "Fall" -> new Quarter("Spring", year);
            default -> throw new IllegalArgumentException("Unknown quarter: " + current);
        };
    }

    public int getYear() {
        return (year == -1) ? 25 : year;
    }

    public String getQuarterName() {
        return switch (name) {
            case "S" -> "Spring";
            case "F" -> "Fall";
            case "W" -> "Winter";
            default -> "Spring";
        };
    }
}