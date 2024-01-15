import java.util.ArrayList;

public class Team extends Squad {
    private int points;

    Team(String teamName, Manager manager) {
        super(teamName, manager);
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int x) {
        this.points = x;
    }
}