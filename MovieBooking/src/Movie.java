public class Movie {
    private final String id;
    private final String title;
    private final String description;
    private final int durationInMinutes;

    public Movie(String id, String title, String description, int durationInMinutes){
        this.description = description;
        this.id = id;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

}
