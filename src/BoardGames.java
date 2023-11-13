public class BoardGames {

    private String name;
    private int maxTime;
    private int minTime;

    public BoardGames(String name, int maxTime, int minTime) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        int duration = maxTime - minTime;

        if (duration == 0) {
            double average = Math.floor((maxTime + minTime) / 2);
            duration = (int) average;
        }

        return duration;
    }

}