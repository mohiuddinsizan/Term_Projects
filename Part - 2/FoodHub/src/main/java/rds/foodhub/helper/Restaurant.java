// import java.util.ArrayList;
package rds.foodhub.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
    private int Id;
    private String Name;
    private double Score;
    private String Price;
    private String Zip_Code;
    private ArrayList<String> Categories = new ArrayList<String>();

    public Restaurant(int Id, String Name, double Score, String Price, String Zip_Code, ArrayList<String> Categories) {
        this.Id = Id;
        this.Name = Name;
        this.Score = Score;
        this.Price = Price;
        this.Zip_Code = Zip_Code;
        this.Categories = Categories;
    }

//    public int getId() {
//        return this.Id;
//    }
//
//    public String getName() {
//        return this.Name;
//    }
//
//    public double getScore() {
//        return this.Score;
//    }
//
//    public String getPrice() {
//        return this.Price;
//    }
//
//    public String getZip_code() {
//        return this.Zip_Code;
//    }
//
//    public List<String> getCategories() {
//        return Categories;
//    }
//
    public String getCatString()
    {
        String ret = "";
        for (String i : Categories) {
            ret += i + ", ";
        }
        return ret;
    }
//
//    boolean isCatagory(String catagory) {
//        for (String i : Categories) {
//            if (i.equals(catagory)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void displayInfo() {
//        System.out.println("Id: " + Id);
//        System.out.println("Name: " + Name);
//        System.out.println("Score: " + Score);
//        System.out.println("Price: " + Price);
//        System.out.println("Zipcode: " + Zip_Code);
//        System.out.print("Categories: ");
//        for (String i : Categories) {
//            System.out.print(i + ", ");
//        }
//        System.out.println();
//    }

    public String getRestaurantName(){
        return this.Name;
    }

    public double getScore(){
        return this.Score;
    }

    public int getID(){
        return this.Id;
    }

    public boolean containCatagory(String cats){
        for(String x : Categories){
            if(x.toLowerCase().equals(cats.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public ArrayList<String> getCategories(){
        return Categories;
    }
    public String getPrice(){
        return this.Price;
    }

    public String getZipCode(){
        return this.Zip_Code;
    }
}
