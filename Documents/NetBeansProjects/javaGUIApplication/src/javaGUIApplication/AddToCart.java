package javaGUIApplication;
import java.math.BigDecimal;

public class AddToCart {
   
    private String medicineName;
    private String medicineType;
    private int quantity;
    private BigDecimal price;
    private int medicineID;
    

    public AddToCart(String medicineName, String medicineType, int quantity, BigDecimal price,int medicineID) {
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.quantity = quantity;
        this.price = price;
        this.medicineID=medicineID;
        
    };
    
     public BigDecimal getPrice() {
        return this.price; 
    }

    public int getQuantity() {
        return this.quantity; 
    }
     public String getMedicineName() {
        return this.medicineName; 
    }
      public String getMedicineType() {
        return this.medicineType; 
    }
       public int getMedicineID() {
        return this.medicineID; 
    }
}

