
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class App {


    public static void main(String[] args)throws Exception {
        RestuarentManager restuarentManager = new RestuarentManager();

        // file input 
        BufferedReader reader = new BufferedReader(new FileReader("restaurant.txt"));
        String line;
        while(true)
        {
            line = reader.readLine();
            if(line == null) break;
            String [] tokens = line.split(",");
            int catCount = tokens.length - 5;
            String [] cats = new String[3];
            System.arraycopy(tokens, 5, cats, 0, catCount);
            for(int i = catCount; i < 3; i++)
            {
                cats[i] = "";
            }
            for(int i = 0;i<3;i++){
                if(cats[i]!= ""){
                    CatagoryHolder cat = new CatagoryHolder(cats[i]);
                    restuarentManager.addCatagories(cat);
                }
            }
            restuarentManager.addRestuarent(new Restuarent(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]), tokens[3], tokens[4],cats[0],cats[1],cats[2]));
        }
        reader.close();
        
        //
        BufferedReader reader2 = new BufferedReader(new FileReader("Menu.txt"));
        String line2;
        while(true){
            line = reader2.readLine();
            if(line == null) break;
            String [] tokens2 = line.split(",");
            //System.out.println(tokens2[0]+tokens2[1]+tokens2[2]+tokens2[3]);
            restuarentManager.addFoodExplicitly(Integer.parseInt(tokens2[0]),tokens2[1], tokens2[2],Double.parseDouble(tokens2[3]));
        }
        reader2.close();
        Scanner scanner = new Scanner(System.in);
        int choice;



        while(true){
            System.out.println("Main Menu : ");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");
            System.out.print("Enter Choice : ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 1){
                for(;;){
                    System.out.println("Restuarent Searching Option :");
                    System.out.println("1) By Name");
                    System.out.println("2) By Score");
                    System.out.println("3) By Category");
                    System.out.println("4) By Price");
                    System.out.println("5) By Zip Code");
                    System.out.println("6) Different Category Wise List of Restaurants");
                    System.out.println("7) Back to Main Menu");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    if(option == 7) break;
                    else restuarentManager.searchRestuarent(option);
                }
            }
            if(choice == 2){
                for(;;){
                    System.out.println("Food Searching Option : ");
                    System.out.println("1) By Name");
                    System.out.println("2) By Name in a Given Restaurant");
                    System.out.println("3) By Category");
                    System.out.println("4) By Category in a Given Restaurant");
                    System.out.println("5) By Price Range");
                    System.out.println("6) By Price Range in a Given Restaurant");
                    System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                    System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                    System.out.println("9) Back to Main Menu");
                    int option = scanner.nextInt();
                    scanner.nextLine();
                    if(option == 9) break ;
                    else restuarentManager.searchFoodItem(option);
                }
            }
            if(choice == 3){
                System.out.print("Enter id : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter name : ");
                String name = scanner.nextLine();
                System.out.print("Enter score : ");
                double score = scanner.nextFloat();
                scanner.nextLine();
                System.out.print("Enter price : ");
                String price = scanner.nextLine();
                System.out.print("Enter zip code : ");
                String zip_code = scanner.nextLine();
                System.out.print("Enter First catagory : ");
                String catagory = scanner.nextLine();
                if(catagory.equals("")){

                }
                else{
                    CatagoryHolder cat = new CatagoryHolder(catagory);
                    restuarentManager.addCatagories(cat);
                }
                System.out.print("Enter Second catagory : ");
                String catagory2 = scanner.nextLine();
                if(catagory2.equals("")){

                }
                else{
                    CatagoryHolder cat2 = new CatagoryHolder(catagory2);
                    restuarentManager.addCatagories(cat2);
                }
                System.out.print("Enter Third catagory : ");
                String catagory3 = scanner.nextLine();
                if(catagory3.equals("")){

                }
                else{
                    CatagoryHolder cat3 = new CatagoryHolder(catagory3);
                    restuarentManager.addCatagories(cat3);
                }
                Restuarent restuarent = new Restuarent(id, name, score, price, zip_code, catagory,catagory2,catagory3);
                restuarentManager.addRestuarent(restuarent);
            }
            if(choice == 4){
                System.out.print("Enter Restuarent Name : ");
                String restuarentName = scanner.nextLine();
                if(restuarentManager.restuarentExist(restuarentName)){
                    restuarentManager.addFoodItem(restuarentName);
                }
                else System.out.println("No such restaurant with this name ");
            }
            if(choice == 5)
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter("restaurant.txt"));
                ArrayList<Restuarent> restuarents = restuarentManager.getRestuarents();
                for(Restuarent restuarent : restuarents)
                {
                    writer.write(restuarent.getID() + "," + restuarent.getRestaurantName() + "," + restuarent.getScore() + "," + restuarent.getPrice() + "," + restuarent.getZipCode() + ",");
                    ArrayList<String> cats = restuarent.getCatagory();
                    for(String cat : cats)
                    {
                        if(cat=="" ) break;
                        writer.write(cat + ",");
                    }
                    writer.write("\n");
                }
                writer.close();
                BufferedWriter writer2 = new BufferedWriter(new FileWriter("Menu.txt"));
                ArrayList <Food> foods = restuarentManager.foodList();
                for(Food x : foods){
                    writer.write(x.getRestaurantID()+","+x.getCatagory()+","+x.getFoodName()+","+x.getFoodPrice());
                }
                writer2.close();

                break;
            }
        } 
    }

    
}
