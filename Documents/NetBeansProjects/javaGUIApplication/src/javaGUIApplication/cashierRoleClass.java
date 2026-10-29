package javaGUIApplication;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.math.BigDecimal;

public class cashierRoleClass {
  dbConnectivity db=new dbConnectivity(); 
  
    //Cashier GUI
    public cashierRoleClass(){
        
       
    JFrame Cashier =new JFrame("Cashier");
    Cashier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
            
   JPanel contentPanel=new JPanel();
Cashier.add(contentPanel);
contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
contentPanel.setBackground(new Color(245,247,250));
contentPanel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

//title
JLabel titleLabel=new JLabel("Point of Sale");
titleLabel.setFont(new Font("SansSerif",Font.BOLD,20));
titleLabel.setForeground(new Color(0,102,204));
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
contentPanel.add(titleLabel);

//name of medicine
JPanel medicinePanel=new JPanel(new GridLayout(1,2,10,0));
JLabel medLabel=new JLabel("Medicine Name:",SwingConstants.RIGHT);
medLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField medField=new JTextField(15);
medField.setFont(new Font("SansSerif",Font.PLAIN,13));
medicinePanel.add(medLabel);
medicinePanel.add(medField);
medicinePanel.setMaximumSize(new Dimension(600,40));
medicinePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
medicinePanel.setBackground(new Color(245,247,250));
medicinePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
contentPanel.add(medicinePanel);

//Medicine type
JPanel comboPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel medicineTypeLabel=new JLabel("Type:",SwingConstants.RIGHT);
medicineTypeLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
String[]medicineType={"Tablet","Capsule","Syrup","Injection","Cream"};
JComboBox <String>comboBox=new JComboBox<>(medicineType);
comboBox.setFont(new Font("SansSerif",Font.PLAIN,13));
comboPanel.add(medicineTypeLabel);
comboPanel.add(comboBox);
comboPanel.setMaximumSize(new Dimension(600,40));
comboPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
comboPanel.setBackground(new Color(245,247,250));
comboPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
contentPanel.add(comboPanel);

//quantity
JPanel quantityPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel quantityLabel=new JLabel("Quantity:",SwingConstants.RIGHT);
quantityLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField quantityField=new JTextField(15);
quantityField.setFont(new Font("SansSerif",Font.PLAIN,13));
quantityPanel.add(quantityLabel);
quantityPanel.add(quantityField);
quantityPanel.setMaximumSize(new Dimension(600,40));
quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
quantityPanel.setBackground(new Color(245,247,250));
quantityPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
contentPanel.add(quantityPanel);

//buttons row — Add to Cart + Check Availability side by side
JPanel actionBtnPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
actionBtnPanel.setBackground(new Color(245,247,250));
actionBtnPanel.setBorder(BorderFactory.createEmptyBorder(15,0,5,0));
actionBtnPanel.setMaximumSize(new Dimension(600,50));
actionBtnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

JButton AddcartButton=new JButton("Add to Cart");
AddcartButton.setPreferredSize(new Dimension(160,36));
AddcartButton.setBackground(new Color(0,102,204));
AddcartButton.setForeground(Color.WHITE);
AddcartButton.setFont(new Font("SansSerif",Font.BOLD,13));
AddcartButton.setFocusPainted(false);
AddcartButton.setBorderPainted(false);
AddcartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

JButton availabilityBtn=new JButton("Check Availability");
availabilityBtn.setPreferredSize(new Dimension(160,36));
availabilityBtn.setBackground(new Color(0,153,76));
availabilityBtn.setForeground(Color.WHITE);
availabilityBtn.setFont(new Font("SansSerif",Font.BOLD,13));
availabilityBtn.setFocusPainted(false);
availabilityBtn.setBorderPainted(false);
availabilityBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

actionBtnPanel.add(AddcartButton);
actionBtnPanel.add(availabilityBtn);
contentPanel.add(actionBtnPanel);

//total label
JLabel totalLabel=new JLabel("Total: R0.00");
totalLabel.setFont(new Font("SansSerif",Font.BOLD,16));
totalLabel.setForeground(new Color(0,102,204));
totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
totalLabel.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
contentPanel.add(totalLabel);

//bottom buttons row — Clear Cart + Checkout side by side
JPanel bottomBtnPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
bottomBtnPanel.setBackground(new Color(245,247,250));
bottomBtnPanel.setMaximumSize(new Dimension(600,50));
bottomBtnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

JButton ClearcartButton=new JButton("Clear Cart");
ClearcartButton.setPreferredSize(new Dimension(160,36));
ClearcartButton.setBackground(new Color(204,0,0));
ClearcartButton.setForeground(Color.WHITE);
ClearcartButton.setFont(new Font("SansSerif",Font.BOLD,13));
ClearcartButton.setFocusPainted(false);
ClearcartButton.setBorderPainted(false);
ClearcartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

JButton checkoutButton=new JButton("Checkout");
checkoutButton.setPreferredSize(new Dimension(160,36));
checkoutButton.setBackground(new Color(0,102,204));
checkoutButton.setForeground(Color.WHITE);
checkoutButton.setFont(new Font("SansSerif",Font.BOLD,13));
checkoutButton.setFocusPainted(false);
checkoutButton.setBorderPainted(false);
checkoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

bottomBtnPanel.add(ClearcartButton);
bottomBtnPanel.add(checkoutButton);
contentPanel.add(bottomBtnPanel);




    //Cart
    ArrayList<AddToCart>cart=new ArrayList<>();
    
    //add to cart button
    AddcartButton.addActionListener(e21->{
        
    
        String medicineName=medField.getText().trim();
        String SelectedMedicine_Type=(String)comboBox.getSelectedItem();
        
        
        if(medicineName.isEmpty()){
            JOptionPane.showMessageDialog(Cashier,"Please enter Medicine Name");
            return;
        
        }
        
        if(quantityField.getText().isEmpty()){
            JOptionPane.showMessageDialog(Cashier,"Please enter Quantity");
            return;
        
        }
        
       
        int medicineId=0;
        BigDecimal price=BigDecimal.ZERO;
        int quantityInStock=0;
        
        
        try{
         int quantity=Integer.parseInt(quantityField.getText().trim());
         
        String sql="SELECT medicine_id,medicine_type,price,quantity_in_stock from medicines"
                + " WHERE  name=?";
        
        PreparedStatement pst=db.con.prepareStatement(sql);
        
        pst.setString(1, medicineName);
        
        ResultSet rs=pst.executeQuery();
        
        if(rs.next()){
        
            medicineId=rs.getInt("medicine_id");//initialize medicine id
            price=rs.getBigDecimal("price");//initialize price
            quantityInStock=rs.getInt("quantity_in_stock");//initialize quanntity
            String medType=rs.getString("medicine_type");
     
            //check if customer's quantity is > than quantity in stock
            if(quantity>quantityInStock){
            JOptionPane.showMessageDialog(Cashier,"Only "+ quantityInStock+" left");
            return;
            }
            
            //check if user selected available type
            if(!medType.equalsIgnoreCase(SelectedMedicine_Type)){
            JOptionPane.showMessageDialog(Cashier,SelectedMedicine_Type + " not Avilable, Only: "+ medType );
            return;
            }
            
            AddToCart item=new AddToCart(medicineName,SelectedMedicine_Type,quantity,price,medicineId);
            cart.add(item);
            
            
           
            BigDecimal Total = BigDecimal.ZERO;
            for (AddToCart cartItem : cart) {
     
            BigDecimal itemSubtotal = cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));//convert quantity to bigDecimal then price*quantity
        
            Total = Total.add(itemSubtotal);
            
            totalLabel.setText("Total: R"+ Total); 
           
            
            }   
            JOptionPane.showMessageDialog(Cashier,"Item added to cart");
            
        }
        else{
          JOptionPane.showMessageDialog(Cashier,"Medicine not found");
          return;
        }
        
        } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(Cashier,
                "Please enter a number");
        return;
    }
       
        catch(SQLException sqlEx){
            System.out.println(sqlEx.getMessage());
            return;
         }
       
   
         
         
     });
        
    
    //check medicine availability
    availabilityBtn.addActionListener(e21->{
        
    String medicineName=medField.getText().trim();
    String type=comboBox.getSelectedItem().toString();
    
        
        
        if(medicineName.isEmpty()){
            JOptionPane.showMessageDialog(Cashier,"Please enter Medicine Name");
            return;
        
        }
        
        if(quantityField.getText().isEmpty()){
            JOptionPane.showMessageDialog(Cashier,"Please enter Quantity");
            return;
        
        }
        
    try{
        int quantity=Integer.parseInt(quantityField.getText().trim());
        
    String sql="SELECT name,medicine_type,price,quantity_in_stock FROM medicines WHERE name=?";
    PreparedStatement pst=db.con.prepareStatement(sql);
    pst.setString(1,medicineName);
    
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
        
        int quantityInStock=rs.getInt("quantity_in_stock");
        
        //check quantity first
        if(quantityInStock>0){
        JOptionPane.showMessageDialog(Cashier,"Medicine Available"+"\n"
                                        +"Name: "+rs.getString("name")
                                        +" | " +"Price: " +rs.getBigDecimal("price")
                                         +" | "+"Type: "+rs.getString("medicine_type") );
        }
        else{JOptionPane.showMessageDialog(Cashier,"Medicine not available");}
    }
    
    
    }catch(NumberFormatException nfEx){
          JOptionPane.showMessageDialog(Cashier,"Please enter a number");
         return;
      }
    
    catch(SQLException sqlEx){
    System.out.println(sqlEx.getMessage());
    return;
    }
    });
    
    
    
    
         //clear cart button
      ClearcartButton.addActionListener(e22->{
          
       cart.clear();
       JOptionPane.showMessageDialog(Cashier,"Cart cleared");
       
       //clear the fields
       medField.setText("");
       quantityField.setText("");
       
      //set comboBox back to first item
       comboBox.setSelectedIndex(0);
       totalLabel .setText("Total:R0.00");
       
      });
         
     
      
      
      
      //checkout button
      checkoutButton.addActionListener(e23 -> {
    
    if (cart.isEmpty()) {
    JOptionPane.showMessageDialog(Cashier, "Cart is empty!");
    return;
}
    
    JPanel checkOutPanel = new JPanel();
    checkOutPanel.setLayout(new BoxLayout(checkOutPanel, BoxLayout.Y_AXIS));
    checkOutPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    
    
    JPanel medicineNamePanel = new JPanel();
    JLabel MedicineName = new JLabel("HEALTHFIRST PHARMACY RECEIPT");
    MedicineName.setFont(new Font("SansSerif", Font.BOLD, 18));
    medicineNamePanel.add(MedicineName);
    checkOutPanel.add(medicineNamePanel);
    
  
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Medicine");
    model.addColumn("Type");
    model.addColumn("Quantity");
    model.addColumn("Price");
    
   
    BigDecimal[] checkoutTotal = {BigDecimal.ZERO};//array to hold the price
 
    for (AddToCart cartItem : cart) {
        model.addRow(new Object[]{
            cartItem.getMedicineName(),
            cartItem.getMedicineType(),
            cartItem.getQuantity(),
            cartItem.getPrice()
        });
         BigDecimal itemSubtotal = cartItem.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
         checkoutTotal[0]=checkoutTotal[0].add(itemSubtotal);
    }
    
   
    JTable table = new JTable(model);
    table.setFillsViewportHeight(true);
    table.setRowHeight(24); 
    
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(750, 200)); 
    scrollPane.setBorder(BorderFactory.createTitledBorder(" Purchased Items "));
    checkOutPanel.add(scrollPane);
    
 
    JPanel billPanel = new JPanel(new GridLayout(3, 2, 10, 8));
    billPanel.setBorder(BorderFactory.createEmptyBorder(15, 5, 10, 5));
    
    
    JLabel total = new JLabel("Total: R"+checkoutTotal[0]);
    total.setFont(new Font("SansSerif", Font.BOLD, 14));
    
    JLabel cashier = new JLabel("Cashier:"+Session.loggedInName);
    JLabel paymentMethod = new JLabel("Payment Method:");
    
    String[] paymentMethods = {"Cash", "Card", "Medical Aid"};
    JComboBox<String> comboB = new JComboBox<>(paymentMethods);
    
    JButton backBtn = new JButton("Back");

    backBtn.addActionListener(e12 -> {
    contentPanel.removeAll();
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(medicinePanel);
    contentPanel.add(comboPanel);
    contentPanel.add(quantityPanel);
    contentPanel.add(actionBtnPanel);
    contentPanel.add(totalLabel);
    contentPanel.add(bottomBtnPanel);
    contentPanel.revalidate();
    contentPanel.repaint();
});
    
    checkOutPanel.add(billPanel);
    
   
    
    
    
    billPanel.add(cashier);
    billPanel.add(paymentMethod);
    billPanel.add(comboB);
    billPanel.add(total);
    billPanel.add(backBtn);
   
    
    checkOutPanel.add(billPanel);
    
    
    //Pay Buttn)
    JPanel payPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    JButton payBtn = new JButton("Pay");
    payBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
    payBtn.setBackground(new Color(34, 139, 34)); 
    payBtn.setForeground(Color.WHITE);            
    payBtn.setFocusPainted(false);
    payBtn.setPreferredSize(new Dimension(120, 35));
    payPanel.add(payBtn);
    
    checkOutPanel.add(payPanel);
    
    //Pay button action listerner
     payBtn.addActionListener(e24->{
     
     try{
     
     String sql="INSERT INTO sales (total_amount,user_id)"
             + "VALUES(?,?)";
     
     PreparedStatement pst=db.con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
     pst.setBigDecimal(1,checkoutTotal[0]);
     pst.setInt(2,Session.loggedInUserId);
     
     //I assume theres a payment processing happening before adding this to database and showing payment sucessful message
     int rows=pst.executeUpdate();
     
     //get the saleID
    int saleId = 0;
    ResultSet generatedKeys = pst.getGeneratedKeys();
    if (generatedKeys.next()) {
    saleId = generatedKeys.getInt(1);
}
     if(rows>0){
     
     JOptionPane.showMessageDialog(Cashier, "Payment Successful✅");
     
     //get the quantity medicines bought 
     String sql2="SELECT quantity_in_stock FROM medicines WHERE name=? AND medicine_type=?";
     PreparedStatement pst2=db.con.prepareStatement(sql2);
     ResultSet rs2;
     int updatedQuantity;
     
     //update the quantity_in_stock in medicine table
     String sql3="UPDATE medicines SET quantity_in_stock=? "
             + "WHERE name=? AND medicine_type=?";
     PreparedStatement pst3=db.con.prepareStatement(sql3);
   
     for(AddToCart cartItem:cart){
         pst2.setString(1,cartItem.getMedicineName());
         pst2.setString(2,cartItem.getMedicineType());
         rs2=pst2.executeQuery();
         
         if(rs2.next()){
             int quantity=rs2.getInt("quantity_in_stock");
             updatedQuantity=quantity-cartItem.getQuantity();
            
             pst3.setInt(1,updatedQuantity);
             pst3.setString(2,cartItem.getMedicineName());
             pst3.setString(3,cartItem.getMedicineType());
             pst3.executeUpdate();
         }
     }
   
     String  sql4="INSERT INTO sale_items (sale_id, medicine_id,quantity_sold,price_at_sale) "
               + "VALUES(?,?,?,?)";
     PreparedStatement pst4=db.con.prepareStatement(sql4);
     
     for(AddToCart cartItem : cart){
         pst4.setInt(1,saleId); 
         pst4.setInt(2, cartItem.getMedicineID());
         pst4.setInt(3,cartItem.getQuantity());
         pst4.setBigDecimal(4,cartItem.getPrice());
        
        // System.out.println(cartItem.getQuantity());
       //  System.out.println(cartItem.getPrice());
         
         pst4.executeUpdate();
     }
     //JOptionPane.showMessageDialog(Cashier,"Item added to sale items");
     
     cart.clear(); 
   
};
     
     }catch(SQLException sqlEx){
     System.out.println(sqlEx.getMessage());
     }

     
 
     
     
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     });
    
    
    
    
    
    
    contentPanel.removeAll();
    contentPanel.setLayout(new BorderLayout());
    contentPanel.add(checkOutPanel, BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
});

        

   
    
    
    
    
    
    
    
    
    Cashier.setSize(800, 500);
    Cashier.setVisible(true);
    
    
    
    
    
    
    };
    
    
    
    
    public static void main(String[]args){
    
    new cashierRoleClass();
    
    
    
    
    };
};
