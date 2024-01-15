import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Random;
public class Main {
    public static Squad[] squads = new Squad[32];
    public static ArrayList<Team> teams = new ArrayList<>();

    public static void main(String[] args){
        scanManagers();
        scanPlayers();
        runTournament();
        boolean breakpoint;
    }

    private static void scanPlayers () {
        try (Scanner scanner = new Scanner(new File("Players.csv"))) {
            // Ignore header row
            scanner.nextLine();
                while (scanner.hasNextLine()) {
                    // put findings as elements of a string array into temp array; divide strings by the comma
                    String[] data = scanner.nextLine().split(",");

                    // elements as attributes for a new Player object
                    Player player = new Player(data[0], data[1], data[2], data[3],
                            Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                            Double.parseDouble(data[6]), Double.parseDouble(data[7]),
                            Double.parseDouble(data[8]), Double.parseDouble(data[9]),
                            Double.parseDouble(data[10]), Double.parseDouble(data[11]),
                            Double.parseDouble(data[12]), Double.parseDouble(data[13]));
                    // newly created Player added to players arraylist
                    for (Squad s : squads) {
                        if (s.getTeamName().equals(player.getTeam())) {
                            s.addPlayer(player);
                        }
                }
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    private static void scanManagers () {
        try (Scanner scanner = new Scanner(new File("Managers.csv"))) {
            // Ignore header row
            scanner.nextLine();
            int i = 0;
            // try-catch to scan file and throw exceptions when they come
            while (scanner.hasNextLine()) {
                // put findings as elements of a string array into temp array; divide strings by the comma
                String[] data = scanner.nextLine().split(",");

                // elements as attributes for a new Manager object
                Manager manager = new Manager(data[0], data[1], data[2], data[3],
                        Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                        Double.parseDouble(data[6]), Double.parseDouble(data[7]));
                // newly created Manager added to managers arraylist
                squads[i] = new Squad (data[2], manager);
                if (i < 31) { ++i; }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Team getTeam(Squad s){
        Team t = new Team(s.getTeamName(), s.getManager());
        String[] formation = t.getManager().getFavouredFormation().split("-");

        for (int i = 0; i < formation.length; i++) {
            int posTotal = Integer.parseInt(formation[i]);
            ArrayList<Player> pHolder = new ArrayList<>();

            // determine the position being searched for
            String posTitle = switch (i) {
                case 0 -> " Defender";
                case 1 -> " Midfielder";
                case 2 -> " Forward";
                default -> "";
            };
            for (int j = 0; j < 26; j++) {
                if (s.getPlayer(j).getPosition().equals(posTitle)) {
                    pHolder.add(s.getPlayer(j));
                }
            }
            Comparator<Player> averageComparator = Comparator.comparingDouble(Player::CalculateAverage).reversed();
            pHolder.sort(averageComparator);
            for (int j = 0; j < posTotal; j++) {
                t.addPlayer(pHolder.getFirst());
                pHolder.removeFirst();
            }
        }
        ArrayList<Player> pHolder = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            if (s.getPlayer(j).getPosition().equals(" Goal Keeper")) {
                pHolder.add(s.getPlayer(j));
            }
        }
        Comparator<Player> averageComparator = Comparator.comparingDouble(Player::CalculateAverage).reversed();
        pHolder.sort(averageComparator);
        t.addPlayer(pHolder.getFirst());
        return t;
    }

    public static void runTournament(){
        Random random = new Random();
        ArrayList<Integer> competitors = new ArrayList<>();
        ArrayList<Team> teams = new ArrayList<>();
        competitors.add(random.nextInt(31));
        while  (competitors.size() <= 7) {
            boolean called = false;
            int x = random.nextInt(31);
            for (int i : competitors) {
                if (x == i) {
                    called = true;
                    break;
                }
            }
            if (!called) {
                competitors.add(x);
            }
        }
        for (int comp : competitors) {
            teams.add(getTeam(squads[comp]));
        }
        System.out.println("");
        System.out.println("--- 2024's super tournament of ballkicking and sweaty men! ---");
        System.out.println("");
        System.out.println("8 teams competed to be the best ballmen. Here was the lineup:");
        for (Team t : teams) {
            System.out.println("- "  + t.getTeamName());
        }
        System.out.println("");
        System.out.println("Here was the first bracket:");
        ArrayList<Team> winners1 = new ArrayList<>();
        for (int i = 0; i < teams.size()-1; i += 2) {
            System.out.println(teams.get(i).getTeamName() + " vs" + teams.get(i+1).getTeamName());
            Team winner = MatchUp(teams.get(i), teams.get(i+1));
            System.out.println(" - Winner was:" + winner.getTeamName());
            winners1.add(winner);
        }
        System.out.println("");
        System.out.println("Here was the second bracket:");
        ArrayList<Team> winners2 = new ArrayList<>();
        for (int i = 0; i < winners1.size()-1; i += 2) {
            System.out.println(winners1.get(i).getTeamName() + " vs" + winners1.get(i+1).getTeamName());
            Team winner = MatchUp(winners1.get(i), winners1.get(i+1));
            System.out.println(" - Winner was:" + winner.getTeamName());
            winners2.add(winner);
        }
        System.out.println("");
        System.out.println("Here was the Finale:");
        System.out.println(winners2.get(0).getTeamName() + " vs" + winners2.get(1).getTeamName());
        Team winner = MatchUp(winners2.get(0), winners2.get(1));
        System.out.println(" - The winner of the Finale and Ultimate Ballman team was" + winner.getTeamName());
    }
    public static Team MatchUp (Team t1, Team t2) {
        // winner winner variable dinner
        Team winner;
        // ensure all points are zero
        t1.setPoints(0);
        t2.setPoints(0);
        // group the defenders and offenders together
        // goalies get their own in case of a tiebreaker
        ArrayList<Player> t1Def = new ArrayList<>();
        ArrayList<Player> t1Off = new ArrayList<>();
        Player t1Goalie;
        for (Player p : t1.getPlayers()) {
            switch (p.getPosition()) {
                case " Defender":
                    t1Def.add(p);
                    break;
                case " Midfielder":
                    t1Def.add(p);
                    t1Off.add(p);
                    break;
                case " Forward":
                    t1Off.add(p);
                    break;
                default:
                    t1Def.add(p);
                    t1Goalie = p;
                    break;
            }
        }
        ArrayList<Player> t2Def = new ArrayList<>();
        ArrayList<Player> t2Off = new ArrayList<>();
        Player t2Goalie = null;
        for (Player p : t2.getPlayers()) {
            switch (p.getPosition()) {
                case " Defender":
                    t2Def.add(p);
                    break;
                case " Midfielder":
                    t2Def.add(p);
                    t2Off.add(p);
                    break;
                case " Forward":
                    t2Off.add(p);
                    break;
                default:
                    t2Def.add(p);
                    t2Goalie = p;
                    break;
            }
        }
        // get averages of defence and offense
        double t1DAvg = AvgCalc(t1Def);
        double t1OAvg = AvgCalc(t1Off);
        double t2DAvg = AvgCalc(t2Def);
        double t2OAvg = AvgCalc(t2Off);
        // larger average gets a point; offense avg pit against def avg
        if (t1OAvg > t2DAvg) {
            t1.setPoints(t1.getPoints() + 1);
        } else {
            t2.setPoints(t2.getPoints() + 1);
        }
        if (t2OAvg > t1DAvg) {
            t2.setPoints(t2.getPoints() + 1);
        } else {
            t1.setPoints(t1.getPoints() + 1);
        }
        // penalty shots in the case of tie
        if (t2.getPoints() == t1.getPoints()) {
            Comparator<Player> averageComparator = Comparator.comparingDouble(Player::CalculateAverage).reversed();
            t1Off.sort(averageComparator);
            if (t1Off.getFirst().CalculateAverage() > t2Goalie.CalculateAverage()) {
                t1.setPoints(t1.getPoints() + 1);
            } else {
                t2.setPoints(t2.getPoints() + 1);
            }
        }
        // declare winner
        if (t1.getPoints() > t2.getPoints()) {
            winner = t1;
        } else {
            winner = t2;
        }
        return winner;
    }
    // calculates total average between all player averages within a certain denomination
    public static double AvgCalc (ArrayList<Player> p) {
        double total = 0;
        for (Player i : p) {
            total += i.CalculateAverage();
        }
        return total/p.size();
    }
}
