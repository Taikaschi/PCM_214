package de.fhs.pcm_214;

/**
 * Created by soma on 09.07.15.
 */
public class Day implements Comparable<Day>{

    public Timestamp timestamp;
    public final String[] day = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
    public int[] recipes;
    public int icon;


    public Day() {
        super();
    }


    public Day(Timestamp timestamp, int[] recipes) {
        this.timestamp = timestamp;
        this.recipes = recipes;
    }

    public Day(int[] recipes) {
        this.recipes = recipes;
    }


    public Day(String day) {
        String[] timestamp_and_recipes = day.split(",");
        this.setTimestamp(timestamp_and_recipes[0]);
    }
    /*
    public Day(String line) {

        String[] recipe_log = line.split("\\,");
        String[] ymd = recipe_log[0].split("\\-");

        this.timestamp = new Timestamp(Integer.valueOf(ymd[0]),Integer.valueOf(ymd[1]),Integer.valueOf(ymd[2]) );

        recipes = new int[recipe_log.length - 1];

        for (int i = 0; i != recipe_log.length; i++) {
            recipes[i] = Integer.parseInt(recipe_log[i + 1]);
        }

        this.setRecipes(recipes);

    }*/

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public void setTimestamp(String timestamp) {
        String[] ymd = timestamp.split("-");
        this.setTimestamp(new Timestamp(Integer.valueOf(ymd[0]), Integer.valueOf(ymd[1]), Integer.valueOf(ymd[2])));
    }

    public String[] getDay() {
        return day;
    }

    public int[] getRecipes() {
        return recipes;
    }

    public void addRecipe()

    public void setRecipes(int[] recipes) {
        this.recipes = recipes;
    }

    public String getDay(int day) {
        return this.day[day + 1];
    }

    public int getRecipeLength() {
        return this.recipes.length;
    }

    public int getListStatus() {
        if (getRecipeLength() == 0)
            return 0;

        if (getRecipeLength() > 0 && getRecipeLength() < 4)
            return 1;

        if (getRecipeLength() == 4)
            return 2;

        return -1;
    }


    @Override
    public int compareTo(Day another) {
        if (this.getTimestamp().getDate().isAfter(another.getTimestamp().getDate())) {
            return 1;
        }

        if (this.getTimestamp().getDate().isAfter(another.getTimestamp().getDate())) {
            return -1;
        }

        if (this.getTimestamp().getDate().isEqual(another.getTimestamp().getDate())){
            return 0;
        }

        return 23;

    }
}
