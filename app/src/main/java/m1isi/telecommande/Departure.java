package m1isi.telecommande;

public class Departure {

    private int minutes;
    private int secondes;

    public Departure(int minutes , int secondes){
        this.minutes  = minutes;
        this.secondes = secondes;
    }
    public Departure(String departure){

        this(Integer.parseInt(departure.substring(0,1)),Integer.parseInt(departure.substring(3,4)));
    }
}
