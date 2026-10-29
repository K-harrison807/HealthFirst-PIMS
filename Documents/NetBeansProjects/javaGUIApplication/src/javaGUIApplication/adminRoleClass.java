package javaGUIApplication;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.math.BigDecimal;

public class adminRoleClass {
    
   dbConnectivity db=new dbConnectivity();
    
    
    public adminRoleClass(){
        
        
    //admin GUI
    
    JFrame admin=new JFrame("adminFrame");
    admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    
    JMenuBar menubar=new JMenuBar();
    //Menu
    JMenu usersMenu= new JMenu("Users"); //CRUD
    JMenu medicineMenu=new JMenu("Medicine");//CRID
    JMenu supplierMenu=new JMenu("Suppliers");//CRUD
    JMenu Report=new JMenu("Reports");//REPORTS
    
    //MenuItem for users
    JMenuItem createUser=new JMenuItem("Create");
    JMenuItem updateUser=new JMenuItem("Update");
    JMenuItem readUsers=new JMenuItem("Read");
    JMenuItem deleteUser=new JMenuItem("Delete");
    
    //adding user menuItem to JMenu
    usersMenu.add(createUser);
    usersMenu.add(updateUser);
    usersMenu.add(readUsers);
    usersMenu.add(deleteUser);
    
    //MenuItem for Medicine
    JMenuItem  createMed=new JMenuItem("Create");
    JMenuItem updateMed=new JMenuItem("Update");
    JMenuItem readMed=new JMenuItem("Read");
    JMenuItem deleteMed=new JMenuItem("Delete");
    
    //adding medicine menuItem to JMenu
    medicineMenu.add(createMed);
    medicineMenu.add(updateMed);
    medicineMenu.add(readMed);
    medicineMenu.add(deleteMed);
    
    //MenuItem for Suppliers 
    JMenuItem createSupp=new JMenuItem("Create");
    JMenuItem updateSupp= new JMenuItem("Update");
    JMenuItem readSupp= new JMenuItem("Read");
    JMenuItem deleteSupp=new JMenuItem("Delete");
    
    //Adding supplier menuItem to JMenu
    supplierMenu.add(createSupp);
    supplierMenu.add(updateSupp);
    supplierMenu.add(readSupp);
    supplierMenu.add(deleteSupp);
    
   
    //MenuItems for report tab
    JMenuItem SalesReport=new JMenuItem("Sales Report");
    JMenuItem expiryReport=new JMenuItem("Expiry Report");
    JMenuItem lowStock=new JMenuItem("Low Stock Report");

    
    //adding salesItem menuItems to JMenu 
    Report.add(SalesReport);
    Report.add(expiryReport);
    Report.add(lowStock);
    
    
    //adding JMenu to menubar
   menubar.add(usersMenu);
   menubar.add(medicineMenu);
   menubar.add(supplierMenu);
   menubar.add(Report);
   menubar.setBackground(Color.LIGHT_GRAY);
   menubar.setForeground(Color.WHITE);
   
   admin.setJMenuBar(menubar);


   
   
   JPanel contentPanel = new JPanel();//content panel to reuse the same window if something is clicked

   admin.add(contentPanel);
   

JPanel phamarcyNamePanel = new JPanel();
phamarcyNamePanel.setLayout(new BoxLayout(phamarcyNamePanel, BoxLayout.Y_AXIS));
phamarcyNamePanel.setBackground(new Color(245, 247, 250));

//parmacy name
JLabel pharmacyNameLabel = new JLabel("HEALTHFIRST PHARMACY");
pharmacyNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
pharmacyNameLabel.setForeground(Color.black);
pharmacyNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
pharmacyNameLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
phamarcyNamePanel.add(pharmacyNameLabel);
phamarcyNamePanel.add(Box.createVerticalStrut(20)); 

 
 
 
 JPanel adminLandingPanel = new JPanel(new GridBagLayout()); 
adminLandingPanel.setBackground(new Color(245, 247, 250));


JLabel timerLabel= new JLabel("", SwingConstants.CENTER);
timerLabel.setOpaque(true);
timerLabel.setBackground(Color.WHITE);
timerLabel.setBorder(BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
    BorderFactory.createEmptyBorder(25, 40, 25, 40)
));
adminLandingPanel.add(timerLabel);

// automatic background timer thread to keep the clock updating live
javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
    String currentTime = new java.text.SimpleDateFormat("hh:mm:ss a").format(new java.util.Date());
    
    //  HTML styling
    timerLabel.setText("<html><center>"
        + "<font size='4' color='#646E78'><b>PHARMACY CONTROL PANEL</b></font><br><br>"
        + "<font size='3' color='#888888'>Welcome back, </font>"
        + "<font size='5' color='#0066CC'><b>" + Session.loggedInName + "</b></font><br><br>"
        + "<font size='7' color='#222222'><b>" + currentTime + "</b></font><br><br>"
        + "<font size='3' color='#00994C'>● <b>SYSTEM ONLINE</b></font> "
        + "<font size='3' color='#888888'>| Secure Connection Active</font>"
        + "</center></html>");
});
timer.start(); //start the timer



contentPanel.setLayout(new BorderLayout());
contentPanel.add(phamarcyNamePanel,BorderLayout.NORTH);
contentPanel.add(adminLandingPanel, BorderLayout.CENTER);

 
 
 
 
   //view users
   readUsers.addActionListener(e->{
   
   String sqlQuery="SELECT * FROM users ";
   
   try{
   PreparedStatement pst=db.con.prepareStatement(sqlQuery);
   ResultSet rs= pst.executeQuery();
   
   //creating table 
   DefaultTableModel model=new DefaultTableModel();
   
   model.addColumn("ID");
   model.addColumn("Username");
   model.addColumn("Password");
   model.addColumn("Role");
   model.addColumn("Full Name");
   
   while(rs.next()){
   model.addRow(new Object[]{
   
   rs.getInt("user_id"),
   rs.getString("username"),
   rs.getString("password"),
   rs.getString("role"),
   rs.getString("full_name")
   });
   
   
   }
  JTable table = new JTable(model);

table.setRowHeight(30);
table.setFont(new Font("SansSerif", Font.PLAIN, 13));
table.setGridColor(Color.LIGHT_GRAY);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.WHITE);
table.setShowVerticalLines(true);
table.setShowHorizontalLines(true);

// column widths
table.getColumnModel().getColumn(1).setPreferredWidth(300);
table.getColumnModel().getColumn(2).setPreferredWidth(300);
table.getColumnModel().getColumn(3).setPreferredWidth(200);
table.getColumnModel().getColumn(4).setPreferredWidth(600);

// header styling
JTableHeader header = table.getTableHeader();
header.setFont(new Font("SansSerif", Font.BOLD, 13));
header.setBackground(Color.BLUE);
header.setForeground(Color.WHITE);
header.setPreferredSize(new Dimension(header.getWidth(), 35));

// alternating row colours
table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            c.setBackground(row % 2 == 0
                    ? Color.WHITE
                    : new Color(240, 245, 255));
        }

        return c;
    }
});

// add table to content panel
JScrollPane scrollPane = new JScrollPane(table);

contentPanel.removeAll();
contentPanel.setLayout(new BorderLayout());
contentPanel.add(scrollPane, BorderLayout.CENTER);
contentPanel.revalidate();
contentPanel.repaint();
   
   }catch(SQLException sqlEx){
       System.out.println(sqlEx.getMessage())
               ;}
   
  
   });
   
   
   
   //add users
   createUser.addActionListener(e->{
       
       JPanel panel=new JPanel();
panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
panel.setBackground(new Color(245,247,250));
panel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));
     
      
      //set the username panel
JPanel userNamePanel=new JPanel(new GridLayout(1,2,10,0));
JLabel userName = new JLabel("Username",SwingConstants.RIGHT);
userName.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField userNameField=new JTextField(15);
userNameField.setFont(new Font("SansSerif",Font.PLAIN,13));
userNamePanel.add(userName);
userNamePanel.add(userNameField);
userNamePanel.setMaximumSize(new Dimension(600,40));
userNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userNamePanel.setBackground(new Color(245,247,250));
userNamePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

//set the user password panel
JPanel userPasswordPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel userPassword= new JLabel("Password",SwingConstants.RIGHT);
userPassword.setFont(new Font("SansSerif",Font.PLAIN,13));
JPasswordField userPasswordField= new JPasswordField(15);
userPasswordField.setFont(new Font("SansSerif",Font.PLAIN,13));
userPasswordPanel.add(userPassword);
userPasswordPanel.add(userPasswordField);
userPasswordPanel.setMaximumSize(new Dimension(600,40));
userPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userPasswordPanel.setBackground(new Color(245,247,250));
userPasswordPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

//full name
JPanel fullnamePanel=new JPanel(new GridLayout(1,2,10,0));
JLabel fullname=new JLabel("Full Name",SwingConstants.RIGHT);
fullname.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField fullnameField=new JTextField(15);
fullnameField.setFont(new Font("SansSerif",Font.PLAIN,13));
fullnamePanel.add(fullname);
fullnamePanel.add(fullnameField);
fullnamePanel.setMaximumSize(new Dimension(600,40));
fullnamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
fullnamePanel.setBackground(new Color(245,247,250));
fullnamePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

//set role
JPanel roles=new JPanel(new GridLayout(1,2,10,0));
roles.setMaximumSize(new Dimension(600,40));
roles.setAlignmentX(Component.CENTER_ALIGNMENT);
roles.setBackground(new Color(245,247,250));
roles.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
JLabel roleLabel=new JLabel("Role",SwingConstants.RIGHT);
roleLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JPanel radioPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
radioPanel.setBackground(new Color(245,247,250));
JRadioButton adminBtn = new JRadioButton("Admin");
JRadioButton cashierBtn = new JRadioButton("Cashier");
adminBtn.setFont(new Font("SansSerif",Font.PLAIN,13));
cashierBtn.setFont(new Font("SansSerif",Font.PLAIN,13));
adminBtn.setBackground(new Color(245,247,250));
cashierBtn.setBackground(new Color(245,247,250));
ButtonGroup group=new ButtonGroup();
group.add(adminBtn);
group.add(cashierBtn);
radioPanel.add(adminBtn);
radioPanel.add(cashierBtn);
roles.add(roleLabel);
roles.add(radioPanel);

//create button
JPanel createButtonPanel=new JPanel();
createButtonPanel.setBackground(new Color(245,247,250));
createButtonPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
JButton createButton=new JButton("Create User");
createButton.setPreferredSize(new Dimension(180,38));
createButton.setBackground(new Color(0,102,204));
createButton.setForeground(Color.WHITE);
createButton.setFont(new Font("SansSerif",Font.BOLD,13));
createButton.setFocusPainted(false);
createButton.setBorderPainted(false);
createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
createButtonPanel.add(createButton);

//adding the sub panels to main panel
panel.add(fullnamePanel);
panel.add(userNamePanel);
panel.add(userPasswordPanel);
panel.add(roles);
panel.add(createButtonPanel);

contentPanel.removeAll();
contentPanel.add(panel);
contentPanel.revalidate();
contentPanel.repaint();


    createButton.addActionListener(e2->{
        
        if(fullnameField.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(admin,"Please enter Full Name");
            return;
        
        }
        
        if(userNameField.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(admin,"Please enter userName");
            return;
        
        }
        
        if(new String(userPasswordField.getPassword()).trim().isEmpty()){
            JOptionPane.showMessageDialog(admin,"Please enter Password");
            return;
        }
        
        if(!adminBtn.isSelected() && !cashierBtn.isSelected()){
            JOptionPane.showMessageDialog(admin,"Choose Role");
            return;
        }
   
        //getting user inputs
        String username=userNameField.getText();
        String fullName=fullnameField.getText();
        String password=new String(userPasswordField.getPassword());
        String role;
        
        if(adminBtn.isSelected()){
        role="admin";
        }else{role="cashier";}
        
        
   try{
   String sql="INSERT INTO users(username,password,role,full_name)VALUES(?,?,?,?)";
   PreparedStatement pst=db.con.prepareStatement(sql);
   
   pst.setString(1,username);
   pst.setString(2,password);
   pst.setString(3,role);
   pst.setString(4,fullName);
   
   pst.executeUpdate();
   
   JOptionPane.showMessageDialog(
    admin,
    "User created successfully!",
    "Success",
    JOptionPane.INFORMATION_MESSAGE
);
   
   
   
   
   }catch(NumberFormatException nFE){
     JOptionPane.showMessageDialog(admin,"Please enter a number");
     return;
     }
   
   catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());}
   
   
   
   
   });
   
   });
   
   //update user
   updateUser.addActionListener(e3->{
       
       JPanel findPanel=new JPanel();
findPanel.setLayout(new BoxLayout(findPanel,BoxLayout.Y_AXIS));
findPanel.setBackground(new Color(245,247,250));
findPanel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

//title
JLabel titleLabel=new JLabel("Update User");
titleLabel.setFont(new Font("SansSerif",Font.BOLD,20));
titleLabel.setForeground(new Color(0,102,204));
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
findPanel.add(titleLabel);

//User ID panel
JPanel userIdPanel=new JPanel(new GridLayout(1,2,10,0));
userIdPanel.setMaximumSize(new Dimension(600,40));
userIdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userIdPanel.setBackground(new Color(245,247,250));
JLabel userIdLabel=new JLabel("User ID",SwingConstants.RIGHT);
userIdLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField userIdField=new JTextField(15);
userIdField.setFont(new Font("SansSerif",Font.PLAIN,13));
userIdPanel.add(userIdLabel);
userIdPanel.add(userIdField);
findPanel.add(userIdPanel);

//find user id button
JPanel findBtnPanel=new JPanel();
findBtnPanel.setBackground(new Color(245,247,250));
findBtnPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
JButton findButton=new JButton("Find Medicine");
findButton.setPreferredSize(new Dimension(180,38));
findButton.setBackground(new Color(0,102,204));
findButton.setForeground(Color.WHITE);
findButton.setFont(new Font("SansSerif",Font.BOLD,13));
findButton.setFocusPainted(false);
findButton.setBorderPainted(false);
findButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
findBtnPanel.add(findButton);
findPanel.add(findBtnPanel);

contentPanel.removeAll();
contentPanel.add(findPanel);
contentPanel.revalidate();
contentPanel.repaint();
          
      //action listener for find button
      findButton.addActionListener(e4->{
          
       JPanel updatePanel = new JPanel();
updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
updatePanel.setBackground(new Color(245,247,250));
updatePanel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

// title
JLabel titlelabel = new JLabel("Update User");
titlelabel.setFont(new Font("SansSerif",Font.BOLD,20));
titlelabel.setForeground(new Color(0,102,204));
titlelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titlelabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
updatePanel.add(titleLabel);

// username panel
JPanel userNamePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel userName = new JLabel("Username", SwingConstants.RIGHT);
userName.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField userNameField = new JTextField(15);
userNameField.setFont(new Font("SansSerif",Font.PLAIN,13));
userNamePanel.add(userName);
userNamePanel.add(userNameField);
userNamePanel.setMaximumSize(new Dimension(600,40));
userNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userNamePanel.setBackground(new Color(245,247,250));

// password panel
JPanel userPasswordPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel userPassword = new JLabel("Password", SwingConstants.RIGHT);
userPassword.setFont(new Font("SansSerif",Font.PLAIN,13));
JPasswordField userPasswordField = new JPasswordField(15);
userPasswordField.setFont(new Font("SansSerif",Font.PLAIN,13));

userPasswordPanel.add(userPassword);
userPasswordPanel.add(userPasswordField);
userPasswordPanel.setMaximumSize(new Dimension(600,40));
userPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userPasswordPanel.setBackground(new Color(245,247,250));

// full name panel
JPanel fullnamePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel fullname = new JLabel("Full Name", SwingConstants.RIGHT);
fullname.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField fullnameField = new JTextField(15);
fullnameField.setFont(new Font("SansSerif",Font.PLAIN,13));
fullnamePanel.add(fullname);
fullnamePanel.add(fullnameField);
fullnamePanel.setMaximumSize(new Dimension(600,40));
fullnamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
fullnamePanel.setBackground(new Color(245,247,250));

// role panel
JPanel roles = new JPanel(new GridLayout(1,2,10,0));
JLabel roleLabel = new JLabel("Role", SwingConstants.RIGHT);
roleLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JPanel radioPanel = new JPanel();
radioPanel.setBackground(new Color(245,247,250));
JRadioButton adminBtn = new JRadioButton("Admin");
JRadioButton cashierBtn = new JRadioButton("Cashier");

adminBtn.setFont(new Font("SansSerif",Font.PLAIN,13));
cashierBtn.setFont(new Font("SansSerif",Font.PLAIN,13));

adminBtn.setBackground(new Color(245,247,250));
cashierBtn.setBackground(new Color(245,247,250));

ButtonGroup group = new ButtonGroup();
group.add(adminBtn);
group.add(cashierBtn);

radioPanel.add(adminBtn);
radioPanel.add(cashierBtn);

roles.add(roleLabel);
roles.add(radioPanel);
roles.setMaximumSize(new Dimension(600,40));
roles.setAlignmentX(Component.CENTER_ALIGNMENT);
roles.setBackground(new Color(245,247,250));

// add panels to update panel
updatePanel.add(userNamePanel);
updatePanel.add(userPasswordPanel);
updatePanel.add(fullnamePanel);
updatePanel.add(roles);

// update button panel
JPanel updateButtonPanel = new JPanel();
updateButtonPanel.setBackground(new Color(245,247,250));
updateButtonPanel.setBorder(
    BorderFactory.createEmptyBorder(20,0,0,0)
);

JButton updateButton = new JButton("Update User");
updateButton.setPreferredSize(new Dimension(180,38));
updateButton.setBackground(new Color(0,102,204));
updateButton.setForeground(Color.WHITE);
updateButton.setFont(new Font("SansSerif",Font.BOLD,13));
updateButton.setFocusPainted(false);
updateButton.setBorderPainted(false);
updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

updateButtonPanel.add(updateButton);
updatePanel.add(updateButtonPanel);
     
     //get the userID
     String userId= userIdField.getText().trim();//trim() empty spaces
     
     //check if field is empty
     if(userId.isEmpty()){
     JOptionPane.showMessageDialog(admin,"Please enter a valid number");
     return;
     }
     
   
     try{
         
    int id=Integer.parseInt(userId);
     
     String sql="SELECT * FROM users WHERE user_id=?";
     PreparedStatement pst=db.con.prepareStatement(sql);
     pst.setInt(1,id);
     ResultSet rs=pst.executeQuery();
     
      //set the values into fields for easy editing
 if(rs.next()){
     userNameField.setText(rs.getString("username"));
     userPasswordField.setText(rs.getString("password"));
     fullnameField.setText(rs.getString("full_name"));
     
     String role=rs.getString("role");
     
     if(role.equals("Admin")){
     adminBtn.setSelected(true);
     }
     else{cashierBtn.setSelected(true);};
         
      
      }
 
   else{
      JOptionPane.showMessageDialog(admin,"UserID not found");
       return;};
       
     
     
     }catch(NumberFormatException nFE){
     JOptionPane.showMessageDialog(admin,"Please enter a number");
     return;
     }
     
     
     catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());return;}
     
     
     
     
     //...............update button...............................//
    updateButton.addActionListener(e5->{
    
        String password=new String(userPasswordField.getPassword()).trim();
        //check if fields are empty
    if(userNameField.getText().trim().isEmpty() || password.isEmpty()
            || fullnameField.getText().trim().isEmpty()){
        
     JOptionPane.showMessageDialog(admin,"All fields must be filled");   
    return;
    }
   
 try{
     
   String Id= userIdField.getText().trim();//trim() empty spaces
   int userID=Integer.parseInt(Id);
   
      String role;

    if (adminBtn.isSelected()) {
        role = "Admin";
    } else {
        role = "Cashier";
    }

    String sql="UPDATE users SET username=?,password=?,role=?,full_name=?"
            + "WHERE user_id=?";
    
   PreparedStatement pst=db.con.prepareStatement(sql);
   
   
   pst.setString(1,userNameField.getText());
   pst.setString(2,password);
   pst.setString(3,role);
   pst.setString(4,fullnameField.getText());
   pst.setInt(5,userID);
   
   int rows = pst.executeUpdate();

        if (rows > 0) {
            JOptionPane.showMessageDialog(admin, "User updated successfully");
        } else {
            JOptionPane.showMessageDialog(admin, "No user was updated");
        }
   }catch(NumberFormatException nFE){
     JOptionPane.showMessageDialog(admin,"Please enter a number");
     return;
     }
     
 
 
    catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());return;};
        
        
        
        
    
    });
    
    
    
     
     
     //adding the sub panels to main panel
    updatePanel.add(fullnamePanel);
    updatePanel.add(userNamePanel);
    updatePanel.add(userPasswordPanel);
    updatePanel.add(roles);
    updatePanel.add(updateButtonPanel);

    contentPanel.removeAll();
    contentPanel.add(updatePanel);

    contentPanel.revalidate();
    contentPanel.repaint();
    
   
   
    });
   
   });
   
   //delete user
   deleteUser.addActionListener(e6->{
       
    JPanel deletePanel = new JPanel();
deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
deletePanel.setBackground(new Color(245,247,250));
deletePanel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

// title
JLabel titleLabel = new JLabel("Delete User");
titleLabel.setFont(new Font("SansSerif",Font.BOLD,20));
titleLabel.setForeground(new Color(204,0,0));
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
deletePanel.add(titleLabel);

// User ID panel
JPanel userIdPanel = new JPanel(new GridLayout(1,2,10,0));
userIdPanel.setMaximumSize(new Dimension(600,40));
userIdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userIdPanel.setBackground(new Color(245,247,250));

JLabel userIdLabel = new JLabel("User ID",SwingConstants.RIGHT);
userIdLabel.setFont(new Font("SansSerif",Font.PLAIN,13));

JTextField userIdField = new JTextField(15);
userIdField.setFont(new Font("SansSerif",Font.PLAIN,13));

userIdPanel.add(userIdLabel);
userIdPanel.add(userIdField);
deletePanel.add(userIdPanel);

// Delete button panel
JPanel deleteButton_Panel = new JPanel();
deleteButton_Panel.setBackground(new Color(245,247,250));
deleteButton_Panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

JButton deleteBtn = new JButton("Delete User");
deleteBtn.setPreferredSize(new Dimension(180,38));
deleteBtn.setBackground(new Color(204,0,0));
deleteBtn.setForeground(Color.WHITE);
deleteBtn.setFont(new Font("SansSerif",Font.BOLD,13));
deleteBtn.setFocusPainted(false);
deleteBtn.setBorderPainted(false);
deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

deleteButton_Panel.add(deleteBtn);
deletePanel.add(deleteButton_Panel);

// Refresh content panel
contentPanel.removeAll();
contentPanel.add(deletePanel);
contentPanel.revalidate();
contentPanel.repaint();

    
   deleteBtn.addActionListener(e7->{
   
    try{
      
     String userId= userIdField.getText().trim();//trim() empty spaces
     
     //check if field is empty
     if(userId.isEmpty()){
     JOptionPane.showMessageDialog(admin,"Please enter a valid number");
     return;
     }
     
     int id=Integer.parseInt(userId);
    
        
    String sql="DELETE FROM users WHERE user_id=?";
    PreparedStatement pst=db.con.prepareStatement(sql);
   
    
    pst.setInt(1, id);
    int rows =pst.executeUpdate();
    
    if(rows>0){JOptionPane.showMessageDialog(admin,"User deleted successfully");
   }
    else{JOptionPane.showMessageDialog(admin,"UserID not found");};
    
   
   }catch(NumberFormatException nFE){
     JOptionPane.showMessageDialog(admin,"Please enter a number");
     return;
     }
    
    catch(SQLException sqlEx){
   System.out.println(sqlEx.getMessage());
   return;
   }
            
            
   });
   
   
   });
   
   //create supplier
   createSupp.addActionListener(e7->{
   
    JPanel panel = new JPanel();
panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
panel.setBackground(new Color(245,247,250));
panel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

// title
JLabel titleLabel = new JLabel("Create Supplier");
titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
titleLabel.setForeground(Color.BLUE);
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
panel.add(titleLabel);

// name panel
JPanel NamePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel Name = new JLabel("Name", SwingConstants.RIGHT);
Name.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField NameField = new JTextField(15);
NameField.setFont(new Font("SansSerif", Font.PLAIN, 13));

NamePanel.add(Name);
NamePanel.add(NameField);
NamePanel.setMaximumSize(new Dimension(600,40));
NamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
NamePanel.setBackground(new Color(245,247,250));

// contact person panel
JPanel ContactPersonPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel contactPersonLabel = new JLabel("Contact Person", SwingConstants.RIGHT);
contactPersonLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField contactPersonField = new JTextField(15);
contactPersonField.setFont(new Font("SansSerif", Font.PLAIN, 13));

ContactPersonPanel.add(contactPersonLabel);
ContactPersonPanel.add(contactPersonField);
ContactPersonPanel.setMaximumSize(new Dimension(600,40));
ContactPersonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
ContactPersonPanel.setBackground(new Color(245,247,250));

// phone panel
JPanel phonePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel phoneLabel = new JLabel("Phone", SwingConstants.RIGHT);
phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField phoneField = new JTextField(15);
phoneField.setFont(new Font("SansSerif", Font.PLAIN, 13));

phonePanel.add(phoneLabel);
phonePanel.add(phoneField);
phonePanel.setMaximumSize(new Dimension(600,40));
phonePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
phonePanel.setBackground(new Color(245,247,250));

// email panel
JPanel emailPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel emailLabel = new JLabel("Email", SwingConstants.RIGHT);
emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField emailField = new JTextField(15);
emailField.setFont(new Font("SansSerif", Font.PLAIN, 13));

emailPanel.add(emailLabel);
emailPanel.add(emailField);
emailPanel.setMaximumSize(new Dimension(600,40));
emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
emailPanel.setBackground(new Color(245,247,250));

// address panel
JPanel addressPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel addressLabel = new JLabel("Address", SwingConstants.RIGHT);
addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField addressField = new JTextField(15);
addressField.setFont(new Font("SansSerif", Font.PLAIN, 13));

addressPanel.add(addressLabel);
addressPanel.add(addressField);
addressPanel.setMaximumSize(new Dimension(600,40));
addressPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
addressPanel.setBackground(new Color(245,247,250));

// create supplier button panel
JPanel createSuppPanel = new JPanel();
createSuppPanel.setBackground(new Color(245,247,250));
createSuppPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

JButton CreateBtn = new JButton("Create Supplier");
CreateBtn.setPreferredSize(new Dimension(180,38));
CreateBtn.setBackground(Color.BLUE);
CreateBtn.setForeground(Color.WHITE);
CreateBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
CreateBtn.setFocusPainted(false);
CreateBtn.setBorderPainted(false);
CreateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

createSuppPanel.add(CreateBtn);

// add panels
panel.add(NamePanel);
panel.add(ContactPersonPanel);
panel.add(phonePanel);
panel.add(emailPanel);
panel.add(addressPanel);
panel.add(createSuppPanel);

// refresh content panel
contentPanel.removeAll();
contentPanel.add(panel);
contentPanel.revalidate();
contentPanel.repaint();
   
   CreateBtn.addActionListener(e8->{
   
   if(NameField.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(admin,"Please enter Name");
            return;
        
        }
        
    if(contactPersonField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter Contact Person");
        return;

    }

    if(phoneField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter Phone");
        return;
    }

    if(emailField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter Email");
        return;
    }

     if(addressField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter address");
        return;
    }
        
        
        //getting supplier inputs
        String nameField= NameField.getText();
        String contactPerson=contactPersonField.getText();
        String phone=phoneField.getText();
        String email=emailField.getText();
        String address=addressField.getText();
        
        
   try{
   String sql="INSERT INTO suppliers (name,contact_person,phone,email,address)VALUES(?,?,?,?,?)";
   PreparedStatement pst=db.con.prepareStatement(sql);
   
   pst.setString(1,nameField);
   pst.setString(2,contactPerson);
   pst.setString(3,phone);
   pst.setString(4,email);
   pst.setString(5,address);
   
   pst.executeUpdate();
   
   JOptionPane.showMessageDialog(
    admin,
    "Supplier created successfully!",
    "Success",
    JOptionPane.INFORMATION_MESSAGE
);
   
   
   
   
   }catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());}
   
   
   });
   
   
   });
   //view suppliers
   readSupp.addActionListener(e9->{
   
       try{
       
       String sql="SELECT * FROM suppliers";
       PreparedStatement pst=db.con.prepareStatement(sql);
       ResultSet rs=pst.executeQuery();
       
       DefaultTableModel model=new DefaultTableModel();
       
      model.addColumn("ID");
      model.addColumn("Name");
      model.addColumn("Contact Person");
      model.addColumn("Phone");
      model.addColumn("Email");
      model.addColumn("Address");
      
      while(rs.next()){
      
      model.addRow(new Object[]{
      
      rs.getInt("supplier_id"),
       rs.getString("name"),
       rs.getString("contact_person"),
       rs.getString("phone"),
       rs.getString("email"),
       rs.getString("address"),
      
      });
      
      
      }
     JTable table = new JTable(model);

table.setRowHeight(30);
table.setFont(new Font("SansSerif", Font.PLAIN, 13));
table.setGridColor(Color.LIGHT_GRAY);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.WHITE);
table.setShowVerticalLines(true);
table.setShowHorizontalLines(true);

// column widths
table.getColumnModel().getColumn(0).setPreferredWidth(200);
table.getColumnModel().getColumn(1).setPreferredWidth(600);
table.getColumnModel().getColumn(2).setPreferredWidth(600);
table.getColumnModel().getColumn(3).setPreferredWidth(200);
table.getColumnModel().getColumn(4).setPreferredWidth(600);
table.getColumnModel().getColumn(5).setPreferredWidth(700);

// header styling
JTableHeader header = table.getTableHeader();
header.setFont(new Font("SansSerif", Font.BOLD, 13));
header.setBackground(Color.BLUE);
header.setForeground(Color.WHITE);
header.setPreferredSize(new Dimension(header.getWidth(), 35));

// alternating row colours
table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            c.setBackground(row % 2 == 0
                    ? Color.WHITE
                    : new Color(240, 245, 255));
        }

        return c;
    }
});

// add table to content panel
JScrollPane scrollPane = new JScrollPane(table);

contentPanel.removeAll();
contentPanel.setLayout(new BorderLayout());
contentPanel.add(scrollPane, BorderLayout.CENTER);
contentPanel.revalidate();
contentPanel.repaint();
       
       
       }catch(SQLException sqlEx){
       System.out.println(sqlEx.getMessage());
       }
   
   
   
   });
   
   //update supplier
   updateSupp.addActionListener(e10->{
       
       JPanel panel = new JPanel();
panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
panel.setBackground(new Color(245,247,250));
panel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

// title
JLabel titleLabel = new JLabel("Find Supplier");
titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
titleLabel.setForeground(Color.BLUE);
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
panel.add(titleLabel);

// Supplier ID panel
JPanel suppIdPanel = new JPanel(new GridLayout(1,2,10,0));
suppIdPanel.setMaximumSize(new Dimension(600,40));
suppIdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
suppIdPanel.setBackground(new Color(245,247,250));

JLabel suppIdLabel = new JLabel("Supplier ID", SwingConstants.RIGHT);
suppIdLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

JTextField suppIdField = new JTextField(15);
suppIdField.setFont(new Font("SansSerif", Font.PLAIN, 13));

suppIdPanel.add(suppIdLabel);
suppIdPanel.add(suppIdField);
panel.add(suppIdPanel);

// Find button panel
JPanel findBtn = new JPanel();
findBtn.setBackground(new Color(245,247,250));
findBtn.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

JButton findButton = new JButton("Find Supplier");
findButton.setPreferredSize(new Dimension(180,38));
findButton.setBackground(Color.BLUE);
findButton.setForeground(Color.WHITE);
findButton.setFont(new Font("SansSerif", Font.BOLD, 13));
findButton.setFocusPainted(false);
findButton.setBorderPainted(false);
findButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

findBtn.add(findButton);
panel.add(findBtn);

// refresh content panel
contentPanel.removeAll();
contentPanel.add(panel);
contentPanel.revalidate();
contentPanel.repaint();
       
       
      
          
      //a button to find  
      findButton.addActionListener(e11->{
          
    JPanel updatePanel = new JPanel();
updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
updatePanel.setBackground(new Color(245,247,250));
updatePanel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

// title
JLabel titlelabel = new JLabel("Update Supplier");
titlelabel.setFont(new Font("SansSerif", Font.BOLD, 20));
titlelabel.setForeground(Color.BLUE);
titlelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titlelabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
updatePanel.add(titlelabel);

// supplier name panel
JPanel suppNamePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel suppNameLabel = new JLabel("Name", SwingConstants.RIGHT);
suppNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField suppNameField = new JTextField(15);
suppNameField.setFont(new Font("SansSerif", Font.PLAIN, 13));

suppNamePanel.add(suppNameLabel);
suppNamePanel.add(suppNameField);
suppNamePanel.setMaximumSize(new Dimension(600,40));
suppNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
suppNamePanel.setBackground(new Color(245,247,250));

// contact person panel
JPanel contactPersonPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel contactPersonLabel = new JLabel("Contact Person", SwingConstants.RIGHT);
contactPersonLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField contactPersonField = new JTextField(15);
contactPersonField.setFont(new Font("SansSerif", Font.PLAIN, 13));

contactPersonPanel.add(contactPersonLabel);
contactPersonPanel.add(contactPersonField);
contactPersonPanel.setMaximumSize(new Dimension(600,40));
contactPersonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
contactPersonPanel.setBackground(new Color(245,247,250));

// phone panel
JPanel phonePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel phoneLabel = new JLabel("Phone", SwingConstants.RIGHT);
phoneLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField phoneField = new JTextField(15);
phoneField.setFont(new Font("SansSerif", Font.PLAIN, 13));

phonePanel.add(phoneLabel);
phonePanel.add(phoneField);
phonePanel.setMaximumSize(new Dimension(600,40));
phonePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
phonePanel.setBackground(new Color(245,247,250));

// email panel
JPanel emailPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel emailLabel = new JLabel("Email", SwingConstants.RIGHT);
emailLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField emailField = new JTextField(15);
emailField.setFont(new Font("SansSerif", Font.PLAIN, 13));

emailPanel.add(emailLabel);
emailPanel.add(emailField);
emailPanel.setMaximumSize(new Dimension(600,40));
emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
emailPanel.setBackground(new Color(245,247,250));

// address panel
JPanel addressPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel addressLabel = new JLabel("Address", SwingConstants.RIGHT);
addressLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField addressField = new JTextField(15);
addressField.setFont(new Font("SansSerif", Font.PLAIN, 13));

addressPanel.add(addressLabel);
addressPanel.add(addressField);
addressPanel.setMaximumSize(new Dimension(600,40));
addressPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
addressPanel.setBackground(new Color(245,247,250));

// update button panel
JPanel updateButtonPanel = new JPanel();
updateButtonPanel.setBackground(new Color(245,247,250));
updateButtonPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

JButton updateButton = new JButton("Update Supplier");
updateButton.setPreferredSize(new Dimension(180,38));
updateButton.setBackground(Color.BLUE);
updateButton.setForeground(Color.WHITE);
updateButton.setFont(new Font("SansSerif", Font.BOLD, 13));
updateButton.setFocusPainted(false);
updateButton.setBorderPainted(false);
updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

updateButtonPanel.add(updateButton);

// add panels
updatePanel.add(suppNamePanel);
updatePanel.add(contactPersonPanel);
updatePanel.add(phonePanel);
updatePanel.add(emailPanel);
updatePanel.add(addressPanel);
updatePanel.add(updateButtonPanel);
     
     //get the supplierID
     String suppId= suppIdField.getText().trim();
    
     if(suppId.isEmpty()){
     JOptionPane.showMessageDialog(admin,"Please enter a valid number");
     return;
     }
     
     
     try{
         
     int id=Integer.parseInt(suppId);
     
     
       
     String sql="SELECT * FROM suppliers WHERE supplier_id=?";
     PreparedStatement pst=db.con.prepareStatement(sql);
     pst.setInt(1,id);
     ResultSet rs=pst.executeQuery();
     
      //set the values into fields for easy editing
 if(rs.next()){
     
     suppNameField.setText(rs.getString("name"));
     contactPersonField.setText(rs.getString("contact_person"));
     phoneField.setText(rs.getString("phone"));
     emailField.setText(rs.getString("email"));
     addressField.setText(rs.getString("address"));
      
      
      }
 
   else{
      JOptionPane.showMessageDialog(admin,"Supplier ID not found");
       return;
    };
       
     
     
     }catch(NumberFormatException nFE){
     JOptionPane.showMessageDialog(admin,"Please enter a number");
      return;
     }
     
     catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());};
     
     
     //...............update button...............................//
    updateButton.addActionListener(e12->{
    
     if(suppNameField.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(admin,"Please enter Name");
            return;
        
        }
        
    if(contactPersonField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter Contact Person");
        return;

    }

    if(phoneField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter Phone");
        return;
    }

    if(emailField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter Email");
        return;
    }

     if(addressField.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(admin,"Please enter address");
        return;
    } 
        
   try{
    int id=Integer.parseInt(suppIdField.getText().trim());
    
       String sql="UPDATE suppliers SET name=?,contact_person=?,phone=?,email=?,"
                   +"address=?WHERE supplier_id=?";

          PreparedStatement pst=db.con.prepareStatement(sql);


          pst.setString(1,suppNameField.getText());
          pst.setString(2,contactPersonField.getText());
          pst.setString(3,phoneField.getText());
          pst.setString(4,emailField.getText());
          pst.setString(5,addressField.getText());
          pst.setInt(6, id);

       

          int rows = pst.executeUpdate();

        if (rows > 0) {
            JOptionPane.showMessageDialog(admin, "Supplier updated successfully");
        } else {
            JOptionPane.showMessageDialog(admin, "No supplier was updated");
        }
        
  }catch(NumberFormatException nFE){
     JOptionPane.showMessageDialog(admin,"Please enter a number");
     
  }
   
   
   
   catch(SQLException sqlEx){System.out.println(sqlEx.getMessage());};

          
          
          
        });
    
updatePanel.add(suppNamePanel);
updatePanel.add(contactPersonPanel);
updatePanel.add(phonePanel);
updatePanel.add(emailPanel);
updatePanel.add(addressPanel);
updatePanel.add(updateButtonPanel);

contentPanel.removeAll();
contentPanel.add(updatePanel);
contentPanel.revalidate();
contentPanel.repaint();

          });


   
   
 });
  
   //delete supplier
   deleteSupp.addActionListener(e12->{
   
    JPanel deletePanel = new JPanel();
deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
deletePanel.setBackground(new Color(245,247,250));
deletePanel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

// title
JLabel titleLabel = new JLabel("Delete Supplier");
titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
titleLabel.setForeground(Color.RED);
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
deletePanel.add(titleLabel);

// Supplier ID panel
JPanel suppIdPanel = new JPanel(new GridLayout(1,2,10,0));
suppIdPanel.setMaximumSize(new Dimension(600,40));
suppIdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
suppIdPanel.setBackground(new Color(245,247,250));

JLabel suppIdLabel = new JLabel("Supplier ID", SwingConstants.RIGHT);
suppIdLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

JTextField suppIdField = new JTextField(15);
suppIdField.setFont(new Font("SansSerif", Font.PLAIN, 13));

suppIdPanel.add(suppIdLabel);
suppIdPanel.add(suppIdField);
deletePanel.add(suppIdPanel);

// Delete button panel
JPanel deleteButton_Panel = new JPanel();
deleteButton_Panel.setBackground(new Color(245,247,250));
deleteButton_Panel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

JButton deleteBtn = new JButton("Delete Supplier");
deleteBtn.setPreferredSize(new Dimension(180,38));
deleteBtn.setBackground(Color.RED);
deleteBtn.setForeground(Color.WHITE);
deleteBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
deleteBtn.setFocusPainted(false);
deleteBtn.setBorderPainted(false);
deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

deleteButton_Panel.add(deleteBtn);
deletePanel.add(deleteButton_Panel);

// refresh content panel
contentPanel.removeAll();
contentPanel.add(deletePanel);
contentPanel.revalidate();
contentPanel.repaint();

   deleteBtn.addActionListener(e7->{
       
    String id=suppIdField.getText().trim();//trim() empty spaces
     
     //check if field is empty
     if(id.isEmpty()){
     JOptionPane.showMessageDialog(admin,"Please enter a valid number");
     return;
     }
     
       
    try{
        
    
    int suppId=Integer.parseInt(id); 
        
    String sql="DELETE FROM suppliers WHERE supplier_id=?";
    PreparedStatement pst=db.con.prepareStatement(sql);
    
    
    pst.setInt(1, suppId);
    int rows =pst.executeUpdate();
    
    if(rows>0){JOptionPane.showMessageDialog(admin,"Supplier deleted successfully");
   }
    else{JOptionPane.showMessageDialog(admin,"Supplier ID not found");};
    
   
   }catch(NumberFormatException nfEx){
          JOptionPane.showMessageDialog(admin,"Please enter a number");
         return;
      }
    
    catch(SQLException sqlEx){
   System.out.println(sqlEx.getMessage());
   return;
   }
            
            
   });
   
   
   
   
   
   
   
   
   });
   
   
   createMed.addActionListener(e13->{
       
   
  JPanel panel=new JPanel();
panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
panel.setBackground(new Color(245,247,250));
panel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

//title
JLabel titleLabel=new JLabel("Add New Medicine");
titleLabel.setFont(new Font("SansSerif",Font.BOLD,20));
titleLabel.setForeground(new Color(0,102,204));
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
panel.add(titleLabel);

//name panel
JPanel namePanel=new JPanel(new GridLayout(1,2,10,0));
JLabel nameLabel=new JLabel("Name",SwingConstants.RIGHT);
nameLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField nameField=new JTextField(15);
nameField.setFont(new Font("SansSerif",Font.PLAIN,13));
namePanel.add(nameLabel);
namePanel.add(nameField);
namePanel.setMaximumSize(new Dimension(600,40));
namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
namePanel.setBackground(new Color(245,247,250));

//company panel
JPanel companyPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel companyLabel=new JLabel("Company",SwingConstants.RIGHT);
companyLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField companyField=new JTextField(15);
companyField.setFont(new Font("SansSerif",Font.PLAIN,13));
companyPanel.add(companyLabel);
companyPanel.add(companyField);
companyPanel.setMaximumSize(new Dimension(600,40));
companyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
companyPanel.setBackground(new Color(245,247,250));

//types of medicine
JPanel comboPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel medicineTypeLabel=new JLabel("Medicine Type:",SwingConstants.RIGHT);
medicineTypeLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
String[]medicineType={"Tablet","Capsule","Syrup","Injection","Cream"};
JComboBox <String>comboBox=new JComboBox<>(medicineType);
comboBox.setFont(new Font("SansSerif",Font.PLAIN,13));
comboPanel.add(medicineTypeLabel);
comboPanel.add(comboBox);
comboPanel.setMaximumSize(new Dimension(600,40));
comboPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
comboPanel.setBackground(new Color(245,247,250));

//price panel
JPanel pricePanel=new JPanel(new GridLayout(1,2,10,0));
JLabel priceLabel=new JLabel("Price",SwingConstants.RIGHT);
priceLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField priceField=new JTextField(15);
priceField.setFont(new Font("SansSerif",Font.PLAIN,13));
pricePanel.add(priceLabel);
pricePanel.add(priceField);
pricePanel.setMaximumSize(new Dimension(600,40));
pricePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
pricePanel.setBackground(new Color(245,247,250));

//quantity in stock panel
JPanel quantityPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel quantityLabel=new JLabel("Quantity",SwingConstants.RIGHT);
quantityLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField quantityField=new JTextField(15);
quantityField.setFont(new Font("SansSerif",Font.PLAIN,13));
quantityPanel.add(quantityLabel);
quantityPanel.add(quantityField);
quantityPanel.setMaximumSize(new Dimension(600,40));
quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
quantityPanel.setBackground(new Color(245,247,250));

//reorder level panel
JPanel reOrderPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel reOrderLabel=new JLabel("Reorder Level",SwingConstants.RIGHT);
reOrderLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField reorderField=new JTextField(15);
reorderField.setFont(new Font("SansSerif",Font.PLAIN,13));
reOrderPanel.add(reOrderLabel);
reOrderPanel.add(reorderField);
reOrderPanel.setMaximumSize(new Dimension(600,40));
reOrderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
reOrderPanel.setBackground(new Color(245,247,250));

//expiry panel
JPanel expiryPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel expiryLabel=new JLabel("Expiry Date(YYYY-MM-DD)",SwingConstants.RIGHT);
expiryLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField expiryField=new JTextField(15);
expiryField.setFont(new Font("SansSerif",Font.PLAIN,13));
expiryPanel.add(expiryLabel);
expiryPanel.add(expiryField);
expiryPanel.setMaximumSize(new Dimension(600,40));
expiryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
expiryPanel.setBackground(new Color(245,247,250));

//supplier id panel
JPanel supplierIDPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel supplierIDLabel=new JLabel("Supplier ID",SwingConstants.RIGHT);
supplierIDLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField supplierField=new JTextField(15);
supplierField.setFont(new Font("SansSerif",Font.PLAIN,13));
supplierIDPanel.add(supplierIDLabel);
supplierIDPanel.add(supplierField);
supplierIDPanel.setMaximumSize(new Dimension(600,40));
supplierIDPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
supplierIDPanel.setBackground(new Color(245,247,250));

//button panel
JPanel btnPanel=new JPanel();
btnPanel.setBackground(new Color(245,247,250));
btnPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
JButton createBtn=new JButton("Create Medicine");
createBtn.setPreferredSize(new Dimension(180,38));
createBtn.setBackground(new Color(0,102,204));
createBtn.setForeground(Color.WHITE);
createBtn.setFont(new Font("SansSerif",Font.BOLD,13));
createBtn.setFocusPainted(false);
createBtn.setBorderPainted(false);
createBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
btnPanel.add(createBtn);
   
   
   
   createBtn.addActionListener(e14->{
   
       //check the fields for for empty space
       //check if user submit empty fields
    if(nameField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Name");
         return;

     }

     if(companyField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Company name");
         return;

     }

     if(priceField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Price");
         return;
     }
      if(quantityField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Quantity");
         return;
     }
       if(reorderField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter ReOreder Level");
         return;
     }
        if(expiryField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Expiry Date");
         return;
     }
         if(supplierField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Suplier ID");
         return;
     }
       
       
       
   
   String priceText=priceField.getText();
   String quantityText=quantityField.getText();
   String reorderText=reorderField.getText();
   String expiryText=expiryField.getText();
   String supplierIdText=supplierField.getText();
   
   

try{
    //convert the text to correct data types
   
   BigDecimal price=new BigDecimal(priceText);
   int quantity=Integer.parseInt(quantityText);
   int reorder_Level=Integer.parseInt(reorderText);
   java.sql.Date expiryDate=java.sql.Date.valueOf(expiryText);
   int supplierID=Integer.parseInt(supplierIdText);

String sql="INSERT INTO medicines(name,company,medicine_type,price,"
        + "quantity_in_stock,reorder_level,expiry_date,supplier_id)"
        + "VALUES(?,?,?,?,?,?,?,?)";

PreparedStatement pst=db.con.prepareStatement(sql);

pst.setString(1,nameField.getText());
pst.setString(2,companyField.getText());
pst.setString(3,comboBox.getSelectedItem().toString());//convert to string
pst.setBigDecimal(4,price);//convert to big Decimal
pst.setInt(5,quantity);//convert to int
pst.setInt(6,reorder_Level);//convert to int
pst.setDate(7,expiryDate);//convert to date
pst.setInt(8,supplierID);//convert to int

pst.executeUpdate();

JOptionPane.showMessageDialog(admin,"Medicine created successully");



}catch(SQLException sqlEx){
    JOptionPane.showMessageDialog(admin,"Supplier ID does not exist");
    }
catch(NumberFormatException ex){
   JOptionPane.showMessageDialog(admin,"Enter valid number on(price,quantity,reorder_Level,supplierID");
    ;} 
catch (IllegalArgumentException ex) {
    JOptionPane.showMessageDialog(admin,"Enter a valid date in YYYY-MM-DD format");
   
};
   
   
   });
   
   
   
   
   panel.add(namePanel);
   panel.add(companyPanel);
   panel.add(comboPanel);
   panel.add(pricePanel);
   panel.add(quantityPanel);
   panel.add(reOrderPanel);
   panel.add(expiryPanel);
   panel.add(supplierIDPanel);
   panel.add(btnPanel);
   
   
   contentPanel.removeAll();
   contentPanel.add(panel);
   contentPanel.revalidate();
   contentPanel.repaint();
   
   
   });
   
   readMed.addActionListener(e14->{
   
      JPanel panel = new JPanel(new BorderLayout());
     
try{

String sql="SELECT * FROM medicines";

PreparedStatement pst=db.con.prepareStatement(sql);

ResultSet rs=pst.executeQuery();

DefaultTableModel model=new DefaultTableModel();

model.addColumn("MedicineID");
model.addColumn("Name");
model.addColumn("Company");
model.addColumn("Medicine_Type");
model.addColumn("Price");
model.addColumn("quantity_in_Stock");
model.addColumn("reOrder_Level");
model.addColumn("Expiry_Date");
model.addColumn("supplierID");

while(rs.next()){

model.addRow(new Object[]{

    rs.getInt("medicine_id"),
    rs.getString("name"),
    rs.getString("company"),
    rs.getString("medicine_type"),
    rs.getBigDecimal("price"),
    rs.getInt("quantity_in_stock"),
    rs.getInt("reorder_level"),
    rs.getDate("expiry_date"),
    rs.getInt("supplier_id")

});

};
JTable table = new JTable(model);
table.setRowHeight(30);
table.setFont(new Font("SansSerif", Font.PLAIN, 13));
table.setGridColor(Color.LIGHT_GRAY);
table.setSelectionBackground(Color.BLUE);
table.setSelectionForeground(Color.WHITE);
table.setShowVerticalLines(true);
table.setShowHorizontalLines(true);


table.getColumnModel().getColumn(0).setPreferredWidth(80);
table.getColumnModel().getColumn(1).setPreferredWidth(150);
table.getColumnModel().getColumn(2).setPreferredWidth(150);
table.getColumnModel().getColumn(3).setPreferredWidth(100);
table.getColumnModel().getColumn(4).setPreferredWidth(80);
table.getColumnModel().getColumn(5).setPreferredWidth(120);
table.getColumnModel().getColumn(6).setPreferredWidth(100);
table.getColumnModel().getColumn(7).setPreferredWidth(100);
table.getColumnModel().getColumn(8).setPreferredWidth(80);

// header styling
JTableHeader header = table.getTableHeader();
header.setFont(new Font("SansSerif", Font.BOLD, 13));
header.setBackground(Color.BLUE);
header.setForeground(Color.WHITE);
header.setPreferredSize(new Dimension(header.getWidth(), 35));

// alternating row colours
table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
@Override
public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    if (!isSelected) {
        c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 245, 255));
    }
    return c;
}
});

JScrollPane scrollPane = new JScrollPane(table);
panel.add(scrollPane, BorderLayout.CENTER);

contentPanel.removeAll();
contentPanel.setLayout(new BorderLayout());
contentPanel.add(panel, BorderLayout.CENTER);
   contentPanel.revalidate();
   contentPanel.repaint();
   
    rs.close();
    pst.close();

}catch(SQLException sqlEx){
System.out.println(sqlEx.getMessage());

};
      
   
   
  
   });
   
   deleteMed.addActionListener(e15->{
   
 JPanel panel=new JPanel();
panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
panel.setBackground(new Color(245,247,250));
panel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

//title
JLabel titleLabel=new JLabel("Delete Medicine");
titleLabel.setFont(new Font("SansSerif",Font.BOLD,20));
titleLabel.setForeground(new Color(204,0,0));
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
panel.add(titleLabel);

//medecine ID panel
JPanel medPanel=new JPanel(new GridLayout(1,2,10,0));
medPanel.setMaximumSize(new Dimension(600,40));
medPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
medPanel.setBackground(new Color(245,247,250));
JLabel medLabel=new JLabel("Medicine ID",SwingConstants.RIGHT);
medLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField medField=new JTextField(15);
medField.setFont(new Font("SansSerif",Font.PLAIN,13));
medPanel.add(medLabel);
medPanel.add(medField);
panel.add(medPanel);

//delete button
JPanel deleteBtnPanel=new JPanel();
deleteBtnPanel.setBackground(new Color(245,247,250));
deleteBtnPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
JButton deleteBtn=new JButton("Delete Medicine");
deleteBtn.setPreferredSize(new Dimension(180,38));
deleteBtn.setBackground(new Color(204,0,0));
deleteBtn.setForeground(Color.WHITE);
deleteBtn.setFont(new Font("SansSerif",Font.BOLD,13));
deleteBtn.setFocusPainted(false);
deleteBtn.setBorderPainted(false);
deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
deleteBtnPanel.add(deleteBtn);
panel.add(deleteBtnPanel);

contentPanel.removeAll();
contentPanel.add(panel);
contentPanel.revalidate();
contentPanel.repaint();
  
  
  deleteBtn.addActionListener(e16->{
  
     String medID= medField.getText().trim();//trim() empty spaces
     
     //check if field is empty
     if(medID.isEmpty()){
     JOptionPane.showMessageDialog(admin,"Please enter a valid number");
     return;
     }
     
     
      
      try{
     int medicineID=Integer.parseInt(medID);
      String sql="DELETE FROM medicines WHERE medicine_id=?";
      
      PreparedStatement pst=db.con.prepareStatement(sql);
      pst.setInt(1,medicineID);
      int rows=pst.executeUpdate();
      
      if(rows>0){
          JOptionPane.showMessageDialog(admin,"Medicine deleted successfully");
      }else{
      JOptionPane.showMessageDialog(admin,"Medicine ID not found");
      };
      
      
    
      
      }catch(NumberFormatException nfEx){
          JOptionPane.showMessageDialog(admin,"Please enter a number");
      }
      
    catch(SQLException sqlEx){
  System.out.println(sqlEx);

    }
     
      
  
  
  
  });
 
   
   
   
   });
   
   
   updateMed.addActionListener(e17->{
   
       JPanel findPanel=new JPanel();
findPanel.setLayout(new BoxLayout(findPanel,BoxLayout.Y_AXIS));
findPanel.setBackground(new Color(245,247,250));
findPanel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

//title
JLabel titleLabel=new JLabel("Update Medicine");
titleLabel.setFont(new Font("SansSerif",Font.BOLD,20));
titleLabel.setForeground(new Color(0,102,204));
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
findPanel.add(titleLabel);

//medicine ID panel
JPanel medicineIdPanel=new JPanel(new GridLayout(1,2,10,0));
medicineIdPanel.setMaximumSize(new Dimension(600,40));
medicineIdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
medicineIdPanel.setBackground(new Color(245,247,250));
JLabel medicineIdLabel=new JLabel("Medicine ID",SwingConstants.RIGHT);
medicineIdLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField medicineIdField=new JTextField(15);
medicineIdField.setFont(new Font("SansSerif",Font.PLAIN,13));
medicineIdPanel.add(medicineIdLabel);
medicineIdPanel.add(medicineIdField);
findPanel.add(medicineIdPanel);

//find button id button
JPanel findBtnPanel=new JPanel();
findBtnPanel.setBackground(new Color(245,247,250));
findBtnPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
JButton findBtn=new JButton("Find Medicine");
findBtn.setPreferredSize(new Dimension(180,38));
findBtn.setBackground(new Color(0,102,204));
findBtn.setForeground(Color.WHITE);
findBtn.setFont(new Font("SansSerif",Font.BOLD,13));
findBtn.setFocusPainted(false);
findBtn.setBorderPainted(false);
findBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
findBtnPanel.add(findBtn);
findPanel.add(findBtnPanel);

contentPanel.removeAll();
contentPanel.add(findPanel);
contentPanel.revalidate();
contentPanel.repaint();
       
       
       
       
      
       
 findBtn.addActionListener(e18->{
     
     //get the medicine ID
     String medID=medicineIdField.getText().trim();//trim() empty spaces
     
     //check if field is empty
     if(medID.isEmpty()){
     JOptionPane.showMessageDialog(admin,"Please enter a valid number");
     return;
     }
     
     try{
         
     int medicineID=Integer.parseInt(medID);
         
     String sql="SELECT * FROM medicines WHERE medicine_id=?";
     PreparedStatement pst=db.con.prepareStatement(sql);
     pst.setInt(1,medicineID);
     ResultSet rs=pst.executeQuery();
     
     if(!rs.next()){
     JOptionPane.showMessageDialog(admin,"Medicine ID not found");
     return;
     }
     
  }catch(SQLException sqlEx){
    System.out.println(sqlEx.getMessage());
    return;
    
  }catch(NumberFormatException nfEx){
     JOptionPane.showMessageDialog(admin,"Please enter number");
     return;
     }
JPanel panel = new JPanel();
panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
panel.setBackground(new Color(245,247,250));
panel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

// title
JLabel titlelabel = new JLabel("Update Medicine");
titlelabel.setFont(new Font("SansSerif", Font.BOLD, 20));
titlelabel.setForeground(Color.BLUE);
titlelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titlelabel.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
panel.add(titlelabel);

// name panel
JPanel namePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel nameLabel = new JLabel("Name", SwingConstants.RIGHT);
nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField nameField = new JTextField(15);
nameField.setFont(new Font("SansSerif", Font.PLAIN, 13));

namePanel.add(nameLabel);
namePanel.add(nameField);
namePanel.setMaximumSize(new Dimension(600,40));
namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
namePanel.setBackground(new Color(245,247,250));

// company panel
JPanel companyPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel companyLabel = new JLabel("Company", SwingConstants.RIGHT);
companyLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField companyField = new JTextField(15);
companyField.setFont(new Font("SansSerif", Font.PLAIN, 13));

companyPanel.add(companyLabel);
companyPanel.add(companyField);
companyPanel.setMaximumSize(new Dimension(600,40));
companyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
companyPanel.setBackground(new Color(245,247,250));

// medicine type panel
JPanel comboPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel medicineTypeLabel = new JLabel("Type", SwingConstants.RIGHT);
medicineTypeLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

String[] medicineType = {"Tablet", "Capsule", "Syrup", "Injection", "Cream"};
JComboBox<String> comboBox = new JComboBox<>(medicineType);
comboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));

comboPanel.add(medicineTypeLabel);
comboPanel.add(comboBox);
comboPanel.setMaximumSize(new Dimension(600,40));
comboPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
comboPanel.setBackground(new Color(245,247,250));

// price panel
JPanel pricePanel = new JPanel(new GridLayout(1,2,10,0));
JLabel priceLabel = new JLabel("Price", SwingConstants.RIGHT);
priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField priceField = new JTextField(15);
priceField.setFont(new Font("SansSerif", Font.PLAIN, 13));

pricePanel.add(priceLabel);
pricePanel.add(priceField);
pricePanel.setMaximumSize(new Dimension(600,40));
pricePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
pricePanel.setBackground(new Color(245,247,250));

// quantity panel
JPanel quantityPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel quantityLabel = new JLabel("Quantity", SwingConstants.RIGHT);
quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField quantityField = new JTextField(15);
quantityField.setFont(new Font("SansSerif", Font.PLAIN, 13));

quantityPanel.add(quantityLabel);
quantityPanel.add(quantityField);
quantityPanel.setMaximumSize(new Dimension(600,40));
quantityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
quantityPanel.setBackground(new Color(245,247,250));

// reorder level panel
JPanel reOrderPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel reOrderLabel = new JLabel("Reorder Level", SwingConstants.RIGHT);
reOrderLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField reorderField = new JTextField(15);
reorderField.setFont(new Font("SansSerif", Font.PLAIN, 13));

reOrderPanel.add(reOrderLabel);
reOrderPanel.add(reorderField);
reOrderPanel.setMaximumSize(new Dimension(600,40));
reOrderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
reOrderPanel.setBackground(new Color(245,247,250));

// expiry panel
JPanel expiryPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel expiryLabel = new JLabel("Expiry Date (YYYY-MM-DD)", SwingConstants.RIGHT);
expiryLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField expiryField = new JTextField(15);
expiryField.setFont(new Font("SansSerif", Font.PLAIN, 13));

expiryPanel.add(expiryLabel);
expiryPanel.add(expiryField);
expiryPanel.setMaximumSize(new Dimension(600,40));
expiryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
expiryPanel.setBackground(new Color(245,247,250));

// supplier ID panel
JPanel supplierIDPanel = new JPanel(new GridLayout(1,2,10,0));
JLabel supplierIDLabel = new JLabel("Supplier ID", SwingConstants.RIGHT);
supplierIDLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
JTextField supplierField = new JTextField(15);
supplierField.setFont(new Font("SansSerif", Font.PLAIN, 13));

supplierIDPanel.add(supplierIDLabel);
supplierIDPanel.add(supplierField);
supplierIDPanel.setMaximumSize(new Dimension(600,40));
supplierIDPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
supplierIDPanel.setBackground(new Color(245,247,250));

// button panel
JPanel btnPanel = new JPanel();
btnPanel.setBackground(new Color(245,247,250));
btnPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

JButton updateBtn = new JButton("Update Medicine");
updateBtn.setPreferredSize(new Dimension(180,38));
updateBtn.setBackground(Color.BLUE);
updateBtn.setForeground(Color.WHITE);
updateBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
updateBtn.setFocusPainted(false);
updateBtn.setBorderPainted(false);
updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

btnPanel.add(updateBtn);

// add panels
panel.add(namePanel);
panel.add(companyPanel);
panel.add(comboPanel);
panel.add(pricePanel);
panel.add(quantityPanel);
panel.add(reOrderPanel);
panel.add(expiryPanel);
panel.add(supplierIDPanel);
panel.add(btnPanel);
   
 
  
  try{
    int medicineID=Integer.parseInt(medicineIdField.getText().trim());
       
     String sql="SELECT * FROM medicines WHERE medicine_id=?";
     PreparedStatement pst=db.con.prepareStatement(sql);
     pst.setInt(1,medicineID);
     ResultSet rs=pst.executeQuery();
     
     if(rs.next()){
     nameField.setText(rs.getString("name"));
     companyField.setText(rs.getString("company"));
     comboBox.setSelectedItem(rs.getString("medicine_type"));
     priceField.setText(rs.getBigDecimal("price").toString());
     quantityField.setText(String.valueOf(rs.getInt("quantity_in_stock")));
     reorderField.setText(String.valueOf(rs.getInt("reorder_level")));
     expiryField.setText(rs.getDate("expiry_date").toString());
     supplierField.setText(String.valueOf(rs.getInt("supplier_id")));
     }
     
  }catch(SQLException sqlEx){
    System.out.println(sqlEx.getMessage());
    return; 
  }
    
  
   
   contentPanel.removeAll();
   contentPanel.add(panel);
   contentPanel.revalidate();
   contentPanel.repaint();
   
   
   ///update button to update the database
    updateBtn.addActionListener(e19->{
        
        
        
         //check the fields for for empty space
       //check if user submit empty fields
    if(nameField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Name");
         return;

     }

     if(companyField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Company name");
         return;

     }

     if(priceField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Price");
         return;
     }
      if(quantityField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Quantity");
         return;
     }
       if(reorderField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter ReOreder Level");
         return;
     }
        if(expiryField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Expiry Date");
         return;
     }
         if(supplierField.getText().trim().isEmpty()){
         JOptionPane.showMessageDialog(admin,"Please enter Suplier ID");
         return;
     }
       
        
       
   
      
      
   try{
       
      int medicineID=Integer.parseInt(medicineIdField.getText().trim());
       
      int suppplierID=  Integer.parseInt(supplierField.getText().trim()); 
        //supplier id must match the one in supplier table
  String sql2 = "SELECT supplier_id FROM suppliers WHERE supplier_id = ?";
  PreparedStatement pst2 = db.con.prepareStatement(sql2);
  pst2.setInt(1, suppplierID);
  ResultSet rs2 = pst2.executeQuery();
  
  if(!rs2.next()){
  JOptionPane.showMessageDialog(admin,"Supplier ID "+suppplierID+" does not exist");
  return;
  };
      
           
       
   String sql="UPDATE medicines SET name=?,company=?,medicine_type=?,"
           + "price=?,quantity_in_stock=?,reorder_level=?,expiry_date=?,"
           + "supplier_id=? WHERE medicine_id=?";
           
   PreparedStatement pst=db.con.prepareStatement(sql);
  
   
   pst.setString(1,nameField.getText());
   pst.setString(2,companyField.getText());
   pst.setString(3,comboBox.getSelectedItem().toString());
   pst.setBigDecimal(4,new BigDecimal(priceField.getText()));//convert to Big Decimal
   pst.setInt(5,Integer.parseInt(quantityField.getText()));//convert to int
   pst.setInt(6,Integer.parseInt(reorderField.getText()));//convert to int
   pst.setDate(7, java.sql.Date.valueOf(expiryField.getText()));//convert to date
   pst.setInt (8,suppplierID);
   pst.setInt(9,medicineID);
   
  int rows = pst.executeUpdate();
  

    if (rows > 0) {
        JOptionPane.showMessageDialog(admin, "Medicine updated successfully");
    } else {
        JOptionPane.showMessageDialog(admin, "No Medicine was updated");
    }
  
   }catch(SQLException sqlEx){
   System.out.println(sqlEx.getMessage());
   return;
   }
   catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(admin,
            "Please enter a number");
    return;
    }
    catch (IllegalArgumentException ex) {
    JOptionPane.showMessageDialog(admin,"Enter a valid date in YYYY-MM-DD format");
    return;
    }
  
   
   
             });
    
    
      });
      
      
});
   
       //.. . . .     .. . . .. . . .. .  ///
  
   
   lowStock.addActionListener(e20->{
       
      JPanel panel = new JPanel(new BorderLayout(0, 15));
       
       
    JLabel heading = new JLabel("Low Stock Report", SwingConstants.CENTER);
    heading.setFont(new Font("SansSerif", Font.BOLD, 22));
    heading.setForeground(Color.black);
   
    panel.add(heading, BorderLayout.NORTH);
    
   
   try{
   String sql="SELECT * FROM medicines WHERE quantity_in_stock<=reorder_level";
   PreparedStatement pst=db.con.prepareStatement(sql);
   
   ResultSet rs=pst.executeQuery();
   
   DefaultTableModel model=new DefaultTableModel();
   
   model.addColumn("Name");
   model.addColumn("Medicine Type");
   model.addColumn("Qty in Stock");
   model.addColumn("ReOrder Level");
   
   while (rs.next()) {
    model.addRow(new Object[]{
       
        rs.getString("name"),
        rs.getString("medicine_type"),
        rs.getInt("quantity_in_stock"),
        rs.getInt("reorder_level"),
        
    });
};

JTable table=new JTable(model);

   table.getColumnModel().getColumn(0).setPreferredWidth(300);
   table.getColumnModel().getColumn(1).setPreferredWidth(200);
   table.getColumnModel().getColumn(2).setPreferredWidth(200);
   table.getColumnModel().getColumn(3).setPreferredWidth(300);
   
   panel.add(new JScrollPane(table));
   
   JTableHeader header = table.getTableHeader();
   header.setFont(new Font("SansSerif", Font.BOLD, 13));
   header.setBackground(Color.red);
   header.setForeground(Color.WHITE);
   header.setPreferredSize(new Dimension(header.getWidth(), 35));
   
   contentPanel.removeAll();
   contentPanel.add(panel);
   contentPanel.revalidate();
   contentPanel.repaint();
   
    rs.close();
    pst.close();
   
   }catch(SQLException sqlEx){
   System.out.println(sqlEx.getMessage());
   
   }
   
   
   });
   
   expiryReport.addActionListener(e21->{
       
    JPanel panel = new JPanel(new BorderLayout(0, 15));
       
       
    JLabel heading = new JLabel("Expiry Report", SwingConstants.CENTER);
    heading.setFont(new Font("SansSerif", Font.BOLD, 22));
    heading.setForeground(Color.black);
   
    panel.add(heading, BorderLayout.NORTH);
    
   try{
  String sql="SELECT * FROM medicines "
          + "WHERE expiry_date <= DATE_ADD(CURDATE(), INTERVAL 30 DAY) "
          + "ORDER BY expiry_date ASC";
  PreparedStatement pst=db.con.prepareStatement(sql);
  ResultSet rs=pst.executeQuery();
  
   DefaultTableModel model=new DefaultTableModel();
   
   
   model.addColumn("Name");
   model.addColumn("Medicine Type");
   model.addColumn("Expiry Date");
   
   
   while (rs.next()) {
    model.addRow(new Object[]{
        
        rs.getString("name"),
        rs.getString("medicine_type"),
        rs.getDate("expiry_date"),
        
    });
};


JTable table=new JTable(model);

  table.getColumnModel().getColumn(0).setPreferredWidth(250);
   table.getColumnModel().getColumn(1).setPreferredWidth(200);
   table.getColumnModel().getColumn(2).setPreferredWidth(250);
  
   
   panel.add(new JScrollPane(table));
   
    JTableHeader header = table.getTableHeader();
    header.setFont(new Font("SansSerif", Font.BOLD, 13));
    header.setBackground(Color.green);
    header.setForeground(Color.WHITE);
    header.setPreferredSize(new Dimension(header.getWidth(), 35));

   contentPanel.removeAll();
   contentPanel.add(panel);
   contentPanel.revalidate();
   contentPanel.repaint();
   
    rs.close();
    pst.close();
   
  

  
  
   }catch(SQLException sqlEx){
   
       System.out.println(sqlEx.getMessage());
   };
   
   
   }); 
   
 SalesReport.addActionListener(e22 -> {

    JPanel panel = new JPanel(new BorderLayout(65, 0));

    try {
        // sales table
        String sql = "SELECT * FROM sales";
        PreparedStatement pst = db.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Sale ID");
        model.addColumn("Sale Date");
        model.addColumn("Total Amount");
        model.addColumn("User ID");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("sale_id"),
                rs.getTimestamp("sale_date"),
                rs.getBigDecimal("total_amount"),
                rs.getInt("user_id")
            });
        }

        
        JTable salesTable = new JTable(model);
        salesTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        salesTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        salesTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        salesTable.getColumnModel().getColumn(3).setPreferredWidth(60);

        JScrollPane salesScroll = new JScrollPane(salesTable);
        salesScroll.setBorder(BorderFactory.createTitledBorder("Sales Transactions"));
        panel.add(salesScroll, BorderLayout.WEST);
        
        JTableHeader header1 = salesTable.getTableHeader();
        header1.setFont(new Font("SansSerif", Font.BOLD, 13));
        header1.setBackground(Color.green);
        header1.setForeground(Color.WHITE);
        header1.setPreferredSize(new Dimension(header1.getWidth(), 35));

        rs.close();
        pst.close();

        // sale items table
        String sql2 = "SELECT si.sale_item_id, si.sale_id, m.name, si.quantity_sold, si.price_at_sale " +
                      "FROM sale_items si " +
                      "JOIN medicines m ON si.medicine_id = m.medicine_id";

        PreparedStatement pst2 = db.con.prepareStatement(sql2);
        ResultSet rs2 = pst2.executeQuery();

        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("Item ID");
        model2.addColumn("Sale ID");
        model2.addColumn("Medicine ID");
        model2.addColumn("Qty Sold");
        model2.addColumn("Price At Sale");

        while (rs2.next()) {
            model2.addRow(new Object[]{
                rs2.getInt("sale_item_id"),
                rs2.getInt("sale_id"),
                rs2.getString("name"),
                rs2.getInt("quantity_sold"),
                rs2.getBigDecimal("price_at_sale")
            });
        }

        JTable itemsTable = new JTable(model2);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(100);

        JScrollPane itemsScroll = new JScrollPane(itemsTable);
        itemsScroll.setBorder(BorderFactory.createTitledBorder("Sale Items"));
       
        panel.add(itemsScroll, BorderLayout.EAST);
        
        JTableHeader header2 = itemsTable.getTableHeader();
        header2.setFont(new Font("SansSerif", Font.BOLD, 13));
        header2.setBackground(Color.blue);
        header2.setForeground(Color.WHITE);
        header2.setPreferredSize(new Dimension(header2.getWidth(), 35));

        rs2.close();
        pst2.close();

    } catch (SQLException sqlEx) {
        System.out.println(sqlEx.getMessage());
    }

    contentPanel.removeAll();
    contentPanel.add(panel);
    contentPanel.revalidate();
    contentPanel.repaint();
});
   
   
   
   
    admin.setSize(800, 500);
    admin.setVisible(true);
    
   
    
    
       
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   };
    
    
    
    
    
    public static void main(String[]args){
    
    new adminRoleClass();
    //new dbConnectivity();
    
    };
    
};
