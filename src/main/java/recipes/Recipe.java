package recipes;

public class Recipe {
private final long id;
private final String title;
private final String description;

public Recipe (long id, String title, String desc){
    this.id = id;
    this.title = title;
    this.description = desc;

}

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }
}
