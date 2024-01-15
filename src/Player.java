public class Player extends Person{
    private String position;
    private double fitness;
    private double passingAccuracy;
    private double shotAccuracy;
    private double shotFrequency;
    private double defensiveness;
    private double aggression;
    private double positioning;
    private double dribbling;
    private double chanceCreation;
    private double offsideAdherence;

    public Player(String firstName, String surname, String team, String position,
                  double fitness, double passingAccuracy, double shotAccuracy, double shotFrequency,
                  double defensiveness, double aggression, double positioning, double dribbling,
                  double chanceCreation, double offsideAdherence) {
        super(firstName, surname, team);
        this.position = position;
        this.fitness = fitness;
        this.passingAccuracy = passingAccuracy;
        this.shotAccuracy = shotAccuracy;
        this.shotFrequency = shotFrequency;
        this.defensiveness = defensiveness;
        this.aggression = aggression;
        this.positioning = positioning;
        this.dribbling = dribbling;
        this.chanceCreation = chanceCreation;
        this.offsideAdherence = offsideAdherence;
    }

    // Getter and setter methods for position
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Getter and setter methods for fitness
    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    // Getter and setter methods for passingAccuracy
    public double getPassingAccuracy() {
        return passingAccuracy;
    }

    public void setPassingAccuracy(double passingAccuracy) {
        this.passingAccuracy = passingAccuracy;
    }

    // Getter and setter methods for shotAccuracy
    public double getShotAccuracy() {
        return shotAccuracy;
    }

    public void setShotAccuracy(double shotAccuracy) {
        this.shotAccuracy = shotAccuracy;
    }

    // Getter and setter methods for shotFrequency
    public double getShotFrequency() {
        return shotFrequency;
    }

    public void setShotFrequency(double shotFrequency) {
        this.shotFrequency = shotFrequency;
    }

    // Getter and setter methods for defensiveness
    public double getDefensiveness() {
        return defensiveness;
    }

    public void setDefensiveness(double defensiveness) {
        this.defensiveness = defensiveness;
    }

    // Getter and setter methods for aggression
    public double getAggression() {
        return aggression;
    }

    public void setAggression(double aggression) {
        this.aggression = aggression;
    }

    // Getter and setter methods for positioning
    public double getPositioning() {
        return positioning;
    }

    public void setPositioning(double positioning) {
        this.positioning = positioning;
    }

    // Getter and setter methods for dribbling
    public double getDribbling() {
        return dribbling;
    }

    public void setDribbling(double dribbling) {
        this.dribbling = dribbling;
    }

    // Getter and setter methods for chanceCreation
    public double getChanceCreation() {
        return chanceCreation;
    }

    public void setChanceCreation(double chanceCreation) {
        this.chanceCreation = chanceCreation;
    }

    // Getter and setter methods for offsideAdherence
    public double getOffsideAdherence() {
        return offsideAdherence;
    }

    public void setOffsideAdherence(double offsideAdherence) {
        this.offsideAdherence = offsideAdherence;
    }

    public double CalculateAverage () {
        double avg = (this.fitness + this.aggression + this.dribbling + this.chanceCreation + this.defensiveness + this.offsideAdherence +
                      this.passingAccuracy + this.positioning + this.shotAccuracy + this.shotFrequency) / 10;
        return avg;
    }
}
