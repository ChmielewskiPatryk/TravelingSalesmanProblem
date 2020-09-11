import java.util.*;
import java.util.zip.CheckedInputStream;


public class Main {

    public static void main(String[] args) {

/*        CitiesRepo citiesRepo = new CitiesRepo();
        List<CitiesRepo> citiesRepoList = citiesRepo.testSamples(10);
        for (int i = 0; i <citiesRepoList.size()-1 ; i++) {
            System.out.println(citiesRepoList.get(i).cities.toString());

        }
        System.out.println(" ------------------------------------ ");
        for (int j = 0; j <10 ; j++) {
            mutation(citiesRepoList,2);
            for (int i = 0; i <citiesRepoList.size()-1 ; i++) {
                System.out.println(citiesRepoList.get(i).cities.toString());

            }
        }*/

      travelingSalesmanProblem(10,100,2,2);

    }

    public static void travelingSalesmanProblem(int population ,int iterations, int mutation,int split) {

        if (validateInput(population, split)) {
            CitiesRepo citiesRepo = new CitiesRepo();
            List<CitiesRepo> citiesRepoList = citiesRepo.testSamples(population);  //creating new list of samples to test
            citiesRepoList.sort(CitiesRepo::compareTo);  //sorting the list by route length
            double best = citiesRepoList.get(0).getRoute();
            for (int i = 0; i < iterations; i++) {

                citiesRepoList.add(crossFunction(citiesRepoList, split));     //crossing gens function przy samej funkcji zaczyna w pewnym momencie kopiować to samo
                citiesRepoList.sort(CitiesRepo::compareTo);
                citiesRepoList.remove(citiesRepoList.size() - 1);
                if (best > citiesRepoList.get(0).getRoute()) {
                    best = citiesRepoList.get(0).getRoute();
                }

               /* mutation(citiesRepoList, mutation);  //przy mutacji zaczyna powtarzać wszystkie geny*/
                System.out.println("Iteration number: "+i+" result:"+best);
                for (int i1 = 0; i1 < citiesRepoList.size(); i1++) {
                    System.out.println(i1 + " sample: " + citiesRepoList.get(i1).getRoute() + "     CITIES LIST: " + citiesRepoList.get(i1).cities.toString());
                }
                System.out.println("--------------------------------");
            }
        }
    }

    private static CitiesRepo crossFunction(List<CitiesRepo> citiesRepo, int split) {

        List<Integer> activeCitiesList = candidates(citiesRepo,split);   //how many random candidates we will use
        CitiesRepo mixedCitiesRepo = new CitiesRepo();  // new mixed repo
        LinkedHashSet<City> modifiedCitiesSet = new LinkedHashSet<>();
        LinkedList<City> mixedCityList;
        while (modifiedCitiesSet.size() < citiesRepo.get(0).cities.size()){
        for (int i = 0; i < activeCitiesList.size(); i++) {
            int gens = 0;
            int element = 0;


                while (gens < split) {
                    if (element > citiesRepo.get(0).cities.size() - 1) {     //next list
                        break;
                    }
                    if (modifiedCitiesSet.add(citiesRepo.get(activeCitiesList.get(i)).cities.get(element))) {

                        gens++;
                    }
                    element++;
                }
        }
        }
        mixedCityList = new LinkedList<City>(modifiedCitiesSet);
        mixedCitiesRepo.setCities(mixedCityList);
        mixedCitiesRepo.setRoute(mixedCitiesRepo.calculateRouteLength());
        return mixedCitiesRepo;
    }

    private static List<Integer> candidates(List<CitiesRepo> citiesRepo, int split){


        List<Integer> activeCitiesList = new ArrayList<>();
        int amountOfCandidates =citiesRepo.get(0).cities.size()/split +citiesRepo.get(0).cities.size()%split;
        for (int i = 0; i <amountOfCandidates ; i++) {
            Random random = new Random();
            activeCitiesList.add(random.nextInt(citiesRepo.get(0).cities.size()));
        }
        return activeCitiesList;
    }

    private static void mutation(List<CitiesRepo> citiesRepo, int counter){

        for (int i = 0; i <counter ; i++) {
            Random random = new Random();
            int a = random.nextInt(citiesRepo.size());    //generating random element to swap
            System.out.println("In sample no "+a);
            citiesRepo.get(a).setCities(swap(citiesRepo.get(a).cities));

        }


    }

    private static  LinkedList<City> swap(LinkedList<City> list){

        Random random = new Random();
        int a =random.nextInt(list.size() -1)+1;
        int b = random.nextInt(list.size() -1)+1;
        while ( a == b){
            b = random.nextInt(list.size() -1)+1;
        }
            City tempACity = new City(list.get(a).getName(),list.get(a).getCoordinateX(), list.get(a).getCoordinateY());
            City tempBCity = new City(list.get(b).getName(),list.get(b).getCoordinateX(), list.get(b).getCoordinateY());
            list.set(a,tempBCity);
            list.set(b, tempACity);
        System.out.println(tempACity.getName()+" chagne place with "+tempBCity.getName());
        return list;
    }

    private static boolean validateInput(int pop, int split){
        if (pop < split){
            System.out.println("Population must be bigger than split");
            return false;
        } else if (pop < 10){
            System.out.println("Minimal population to test is 10 ");
            return false;
        }
        System.out.println("Start testing");
        return true;

    }
}
