import java.util.ArrayList;
public class Restuarent {
    
    private ArrayList<String> Catagory = new ArrayList<String>();
    private int ID;
    private String Name;
    private double Score;
    private String Price;
    private String zip_code;
    
    Restuarent(int ID,String Name,double Score,String Price,String zip_code,String... cats){
        this.ID = ID;
        this.Name = Name;
        this.Score = Score;
        this.Price = Price;
        this.zip_code = zip_code;
        for(String x : cats){
            if(x == null) break;
            this.Catagory.add(x);
        }
    }

    public ArrayList<String> getCatagory() {
        return Catagory;
    }
    
    public void printDetails(){
        System.out.println(this.ID);
        System.out.println(this.Name);
        System.out.println(this.Score);
        System.out.println(this.Price);
        System.out.println(this.zip_code);
        System.out.println(Catagory);
    }

    public String getRestaurantName(){
        return this.Name;
    }

    public double getScore(){
        return this.Score;
    }

    public int getID(){
        return this.ID;
    }

    public boolean containCatagory(String cats){
        for(String x : Catagory){
            if(x.equals(cats)){
                return true;
            }
        }
        return false;
    }

    public String getPrice(){
        return this.Price;
    }

    public String getZipCode(){
        return this.zip_code;
    }
    // public void addFoodItemInRestuarent(Food food){
    //     for(Food x : foods){
    //         if(x.getFoodName().equals(food.getFoodName())){
    //             return;
    //         }
    //     }
    //     foods.add(food);
    // }
    // public boolean containThisFood(String foodName){
    //     for(Food x : foods){
    //         if(x.getFoodName().equals(foodName)){
    //             return true;   
    //         }
    //     }
    //     return false;
    // }
    // public String foodOfSameCatagory(String catagory){
    //     for(Food x : foods){
    //         if(x.getCatagory().equals(catagory)){
    //             return x.getCatagory();
    //         }
    //     }
    //     return "";
    // }
    // public boolean foodInRange(double price1,double price2){
    //     int flag = 0;
    //     for(Food x : foods){
    //         if(x.getFoodPrice()<=price2 && x.getFoodPrice()>=price1){
    //             System.out.println(x.getFoodName()+" ");
    //             flag = 1;
    //         }
    //     }
    //     if(flag == 0) return false;
    //     else return true;
    // }
    // public void costliestItem(){
    //     double max = -100000;
    //     Food costlyFood = new Food();
    //     for(Food x : foods){
    //         if(x.getFoodPrice()>=max){
    //             max = x.getFoodPrice();
    //             costlyFood = x;
    //         }
    //     }
    //     System.out.println(costlyFood.getFoodName());
    // }
    // public int totalFoods(){
    //     return foods.size();
    // }
}
