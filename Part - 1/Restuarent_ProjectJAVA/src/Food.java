public class Food {
    private int RestuarentID;
    private String Catagory;
    private String Name;
    private double Price;
    Food(int RestuarentID,String Catagory,String Name,double Price){
        this.RestuarentID = RestuarentID;
        this.Catagory = Catagory;
        this.Name = Name;
        this.Price = Price;
    }
    Food(){
        
    }
    public int getRestaurantID(){
        return this.RestuarentID;
    }
    public String getCatagory(){
        return this.Catagory;
    }
    public String getFoodName(){
        return this.Name;
    }
    public double getFoodPrice(){
        return this.Price;
    }
}
