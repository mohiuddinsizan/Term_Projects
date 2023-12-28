package rds.foodhub.helper;

import java.io.Serializable;

public class Food implements Serializable {

  private int RestaurantId;
  private String Category;
  private String Name;
  private double Price;

  public Food(int RestaurantId, String Category, String Name, double Price) {
      this.RestaurantId = RestaurantId;
      this.Category = Category;
      this.Name = Name;
      this.Price = Price;
  }

  public int getRestaurantId() {
    return this.RestaurantId;
  }

  public String getRestaurantIdString() {
    return this.RestaurantId + "";
  }

  public String getCategory() {
    return this.Category;
  }

  public String getFoodName() {
    return this.Name;
  }

  public double getFoodPrice() {
    return this.Price;
  }

  public String getPriceString() { return Double.toString(this.Price); }

//  public void displayInfo() {
//    System.out.println("ID: " + RestaurantId);
//    System.out.println("Category: " + Category);
//    System.out.println("Name: " + Name);
//    System.out.println("Price: " + Price);
//    System.out.println();
//  }

}
