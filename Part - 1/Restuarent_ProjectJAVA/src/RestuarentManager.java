import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
public class RestuarentManager {
    private  ArrayList<Restuarent> restuarents = new ArrayList<>();
    private  ArrayList<CatagoryHolder> catagories = new ArrayList<>();
    private  ArrayList<Food> foods = new ArrayList<>();
    public  ArrayList<Restuarent> getRestuarents() {
        return restuarents;
    }
    
    Scanner sc = new Scanner(System.in);
    public void addCatagories(CatagoryHolder cat){
        for(CatagoryHolder x : catagories){
            if(x.getCatagoryName().equals(cat.getCatagoryName())){
                return;
            }
        }
        catagories.add(cat);
    }
    public void searchRestuarent(int option){
        if (option == 1) {
            System.out.print("Enter a name : ");
            String name = sc.nextLine();
            int flag = 0;
            for (Restuarent x : restuarents) {
                if (x.getRestaurantName().equals(name)) {
                    x.printDetails();
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("No such restaurant with this name");
            }
        } 
        else if (option == 2) {
            System.out.print("Enter Score range:");
            double r1 = sc.nextDouble();
            //sc.nextLine();
            double r2 = sc.nextDouble();
            sc.nextLine();
            int flag = 0;
            for (Restuarent x : restuarents) {
                double score =x.getScore();
                if (score >= r1 && score <= r2) {
                    x.printDetails();
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("No such restaurant with this score range");
            }
        } 
        else if (option == 3) {
            System.out.print("Enter Catagory:");
            String cat = sc.nextLine();
            int flag = 0;
            for (Restuarent x : restuarents) {
                if (x.containCatagory(cat)) {
                    x.printDetails();
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("No such restaurant with this category");
            }
        } 
        else if (option == 4) {
            System.out.print("Enter Price : ");
            String price = sc.nextLine();
            int flag = 0;
            for (Restuarent x : restuarents) {
                if (x.getPrice().equals(price)) {
                    x.printDetails();
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("No such restaurant with this price");
            }
        } 
        else if (option == 5) {
            System.out.print("Enter Zip Code : ");
            String zipCode = sc.nextLine();
            int flag = 0;
            for (Restuarent x : restuarents) {
                if (x.getZipCode().equals(zipCode)) {
                    x.printDetails();
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("No such restaurant with this zip code");
            }
        } 
        else if (option == 6) {
            for(CatagoryHolder x : catagories){
                System.out.print(x.getCatagoryName()+": ");
                boolean prev = false;
                for(Restuarent y : restuarents){
                    if(y.containCatagory(x.getCatagoryName()) && prev == false){
                        System.out.print(y.getRestaurantName());
                        prev = true;
                        //continue;
                    }
                    else if(y.containCatagory(x.getCatagoryName()) && prev == true){
                        System.out.print(","+y.getRestaurantName());
                    }
                }
                System.out.println();        
            }
        } 
    }
    public void searchFoodItem(int option){
        if(option == 1){
            int flag = 0;
            System.out.print("Enter Food's Name : ");
            String foodName = sc.nextLine();
            for(Food x : foods){
                if(x.getFoodName().equals(foodName)){
                    flag = 1;
                    System.out.println(foodName);
                }
            }
            if(flag == 0){
                System.out.println("No such food item with this name");
            }
        }
        if(option ==2){
            int flag = 0;
            System.out.print("Enter Restuarent name : ");
            String restuarentName = sc.nextLine();
            for(Restuarent x : restuarents){
                if(x.getRestaurantName().equals(restuarentName)){
                    System.out.print("Enter Food's name : ");
                    String foodName = sc.nextLine();

                    for(Food y : foods){
                        if(y.getFoodName().equals(foodName) && y.getRestaurantID() == x.getID()){
                            flag = 1;
                            System.out.println(y.getFoodName());
                        }
                    }
                    if(flag == 0) System.out.println("No such food item with this name on the menu of this restaurant");

                    return;
                }
            }
            if(flag == 0){
                System.out.println("No such restaurant with this name");
            }
        }
        if(option == 3){
            int flag = 0;
            System.out.print("Enter Catagory of food : ");
            String foodCatagory = sc.nextLine();
            for(Food x : foods){
                if(x.getCatagory().equals(foodCatagory)){
                    System.out.println(x.getFoodName());
                    flag = 1;
                }
            }
            if(flag == 0){
                System.out.println("No such food item with this category");
            }
        }
        if(option == 4){
            int flag = 0;
            System.out.print("Enter Restuarent Name : ");
            String restuarentName = sc.nextLine();
            for(Restuarent x : restuarents){

                if(x.getRestaurantName().equals(restuarentName)){
                    System.out.print("Enter food's Catagory : ");
                    String foodCatagory = sc.nextLine();
                    for(Food y : foods){
                        if(y.getCatagory().equals(foodCatagory) && x.getID() == y.getRestaurantID()){
                            flag = 1;
                            System.out.println(y.getFoodName());
                        }
                    }
                    if(flag == 0){
                        System.out.println("No such food item with this category on the menu of this restaurant");
                    return;
                    }
                }
            }
            if(flag == 0){
                System.out.println("No such restaurant with this name");
            }
        }
        if(option == 5){
            System.out.print("Enter Price Range : ");
            double p1 = sc.nextDouble();
            double p2 = sc.nextDouble();
            sc.nextLine();
            int flag = 0;
            for(Food x : foods){
                if(x.getFoodPrice()>=p1 && x.getFoodPrice()<=p2){
                    flag = 1;
                    System.out.println(x.getFoodName());
                }
            }
            if(flag == 0){
                System.out.println("No such food item with this price range");
            }
        }
        if(option == 6){
            int flag = 0;
            System.out.print("Enter Restuarent Name : ");
            String restuarentName = sc.nextLine();
            for(Restuarent x : restuarents ){
                if(x.getRestaurantName().equals(restuarentName)){
                    System.out.print("Enter Price Range : ");
                    double p1 = sc.nextDouble();
                    double p2 = sc.nextDouble();
                    sc.nextLine();
                    for(Food y : foods){
                        if(y.getFoodPrice()>=  p1 && y.getFoodPrice()<=p2 && y.getRestaurantID() == x.getID()){
                            flag = 1;
                            System.out.println(y.getFoodName());
                        }
                    }
                    if(flag == 0){
                        System.out.println("No such food item with this price range on the menu of this restaurant");
                    }
                    return ;
                }
            }
            if(flag == 0){
                System.out.println("No such restaurant with this name");
            }
        }
        if(option == 7){
            System.out.print("Enter Restuarent Name : ");
            String restuarentName = sc.nextLine();
            int flag = 0;
            for(Restuarent x : restuarents ){
                if(x.getRestaurantName().equals(restuarentName)){
                    Food costliestFood = null;
                    for(Food y : foods){
                        if(y.getRestaurantID() == x.getID()){
                            costliestFood = y;
                            flag = 1;
                            break;
                        }
                    }
                    for(Food y : foods){
                        if(y.getRestaurantID() == x.getID()){
                            if(y.getFoodPrice()>costliestFood.getFoodPrice()){
                                costliestFood = y;
                            }
                        }
                    }
                    if(flag == 0){
                        System.out.println("No Food in this restuarent !");
                        return;
                    }
                    System.out.println(costliestFood.getFoodName());
                }
                
            }
            if(flag == 0){
                System.out.println("No such restaurant with this name");
            }
        }
        if(option == 8){
            for(Restuarent x : restuarents){
                int count = 0;
                for(Food y : foods){
                    if(y.getRestaurantID() == x.getID()){
                        count++;
                    }
                }
                System.out.println(x.getRestaurantName()+" : "+count);
            }
        }
    }
    public void addRestuarent(Restuarent restuarent){
        restuarents.add(restuarent);
    }
    public boolean restuarentExist(String name){
        for(Restuarent x : restuarents){
            if(x.getRestaurantName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public void addFoodItem(String nameOfRestuarent){
        System.out.print("Enter Restuarent ID : ");
        int restaurantID = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Food's Catagory : ");
        String foodCatagory = sc.nextLine();
        System.out.print("Enter Food's Name : ");
        String foodName = sc.nextLine();
        System.out.print("Enter Food's Price : ");
        double foodPrice = sc.nextDouble();
        sc.nextLine();
        Food food = new Food(restaurantID, foodCatagory, foodName, foodPrice);
        foods.add( food);
    }
    public void addFoodExplicitly(int restaurantID,String foodCatagory,String foodName,double foodPrice){
        Food food = new Food(restaurantID, foodCatagory, foodName, foodPrice);
        foods.add( food);
    }
    public ArrayList<Food> foodList(){
        return foods;
    }
}
