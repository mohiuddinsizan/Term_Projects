package rds.foodhub.dto;

import java.io.Serializable;

public class RestaurantLoginDTO implements Serializable {

    int garbage;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getID(){
        return this.ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }

    private String userName;
    private String password;
    private int  ID;
    private boolean status;
}
