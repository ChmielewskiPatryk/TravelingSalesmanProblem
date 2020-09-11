import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CitiesRepo implements Comparable<CitiesRepo> {


    public static final City A = new City("A", 1, 22);
    public static final City B = new City("B", 2.3, 42);
    public static final City C = new City("C",14, 21);
    public static final City D = new City("D",2,2.24);
    public static final City E = new City("E",54, 20);
    public static final City F = new City("F",13, 21);
    public static final City G = new City("G",23, 24);
    public static final City H = new City("H",80, 21);
    public static final City I = new City("I",41, 45);
    public static final City J = new City("J",5, 2);




    LinkedList<City> cities;

    private double route = 0;

    public CitiesRepo() {
        cities = new LinkedList<>();
        cities.add(A);
        cities.add(B);
        cities.add(C);
        cities.add(D);
        cities.add(E);
        cities.add(F);
        cities.add(G);
        cities.add(H);
        cities.add(I);
        cities.add(J);

    }

    public LinkedList<City> getCities() {
        return cities;
    }


    public void setCities(LinkedList<City> cities) {
        this.cities = cities;
    }

    public double getRoute() {
        return route;
    }

    public void setRoute(double route) {
        this.route = route;
    }





    @Override
    public int compareTo(CitiesRepo o) {

        return Double.compare(this.route, o.route);
    }

    public List<CitiesRepo> testSamples(int populations) {

        LinkedList<CitiesRepo> list = new LinkedList<>();

        for (int i = 0; i < populations; i++) {
            list.add(this.shuffleList());
            list.get(i).setRoute(list.get(i).calculateRouteLength());
        }
        return list;
    }

    public double calculateRouteLength() {
        double route = 0.0;
        for (int i = 0; i < cities.size() ; i++) {

            if (i == cities.size()-1) {
                route += calculateLength(cities.get(0), cities.get(cities.size() - 1));   //adding a return to start point
            } else {
                route += calculateLength(cities.get(i), cities.get(i + 1));
            }
        }

        return Math.round(route);
    }

    private double calculateLength(City a, City b) {
        double result = 0.0;
        double x = Math.abs(a.getCoordinateX() - b.getCoordinateX());
        double y = Math.abs(a.getCoordinateY() - b.getCoordinateY());
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    private CitiesRepo shuffleList() {
        CitiesRepo newCitiesRepo = new CitiesRepo();

        LinkedList<City> newList = (LinkedList) cities.clone();
        newList.remove(A);
        Collections.shuffle(newList);
        newList.addFirst(A);
        newCitiesRepo.setCities((LinkedList) newList);
        return newCitiesRepo;
    }



}
