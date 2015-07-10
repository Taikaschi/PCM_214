package de.fhs.pcm_214;

/**
 * Created by soma on 09.07.15.
 */
public class Day {

    public Timestamp timestamp;
    public final String[] day = {"Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"};
    public int[] recipes;
    public int icon;

    public Day() {
        super();
    }

    public Day(int[] recipes) {
        this.recipes = recipes;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String[] getDay() {
        return day;
    }

    public int[] getRecipes() {
        return recipes;
    }

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


}
