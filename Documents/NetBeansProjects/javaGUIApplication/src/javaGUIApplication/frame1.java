package javaGUIApplication;
import java.awt.*;
import javax.swing.*;
import java.sql.*;


public class frame1 {
    
 
     public static void main(String[] args) {
         //db connection
         dbConnectivity db=new dbConnectivity(); 
        
    //set frame  
JFrame frame = new JFrame("HealthFirst Pharmacy");   
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(800, 500);
frame.getContentPane().setBackground(new Color(0x123456));

//main panel
JPanel panel=new JPanel();
panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
panel.setBackground(new Color(245,247,250));
panel.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));

//title
JPanel titlePanel=new JPanel();
titlePanel.setBackground(new Color(245,247,250));
titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
JLabel titleLabel=new JLabel("HealthFirst Pharmacy");
titleLabel.setFont(new Font("SansSerif",Font.BOLD,22));
titleLabel.setForeground(new Color(0,102,204));

titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
titlePanel.add(titleLabel);
titlePanel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
panel.add(titlePanel);

//set the username panel
JPanel userNamePanel=new JPanel(new GridLayout(1,2,10,0));
JLabel userName = new JLabel("Username:",SwingConstants.RIGHT);
userName.setFont(new Font("SansSerif",Font.PLAIN,13));
JTextField userNameField=new JTextField(15);
userNameField.setFont(new Font("SansSerif",Font.PLAIN,13));
userNamePanel.add(userName);
userNamePanel.add(userNameField);
userNamePanel.setMaximumSize(new Dimension(400,40));
userNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userNamePanel.setBackground(new Color(245,247,250));
userNamePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

//set the user password panel
JPanel userPasswordPanel=new JPanel(new GridLayout(1,2,10,0));
JLabel userPassword= new JLabel("Password:",SwingConstants.RIGHT);
userPassword.setFont(new Font("SansSerif",Font.PLAIN,13));
JPasswordField userPasswordField= new JPasswordField(15);
userPasswordField.setFont(new Font("SansSerif",Font.PLAIN,13));
userPasswordPanel.add(userPassword);
userPasswordPanel.add(userPasswordField);
userPasswordPanel.setMaximumSize(new Dimension(400,40));
userPasswordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
userPasswordPanel.setBackground(new Color(245,247,250));
userPasswordPanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

//user roles
JPanel roles=new JPanel(new GridLayout(1,2,10,0));
JLabel roleLabel=new JLabel("Role:",SwingConstants.RIGHT);
roleLabel.setFont(new Font("SansSerif",Font.PLAIN,13));
JPanel radioPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
radioPanel.setBackground(new Color(245,247,250));
JRadioButton admin = new JRadioButton("Admin");
JRadioButton cashier = new JRadioButton("Cashier");
admin.setFont(new Font("SansSerif",Font.PLAIN,13));
cashier.setFont(new Font("SansSerif",Font.PLAIN,13));
admin.setBackground(new Color(245,247,250));
cashier.setBackground(new Color(245,247,250));

ButtonGroup group=new ButtonGroup();
group.add(admin);
group.add(cashier);
radioPanel.add(admin);
radioPanel.add(cashier);
roles.add(roleLabel);
roles.add(radioPanel);
roles.setMaximumSize(new Dimension(400,40));
roles.setAlignmentX(Component.CENTER_ALIGNMENT);
roles.setBackground(new Color(245,247,250));
roles.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));

//login Button
JPanel loginBtn=new JPanel();
loginBtn.setBackground(new Color(245,247,250));
loginBtn.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
JButton button=new JButton("Login");
button.setPreferredSize(new Dimension(180,38));
button.setBackground(new Color(0,102,204));
button.setForeground(Color.WHITE);
button.setFont(new Font("SansSerif",Font.BOLD,14));
button.setFocusPainted(false);
button.setBorderPainted(false);
button.setCursor(new Cursor(Cursor.HAND_CURSOR));
loginBtn.add(button);

//adding panels to main panel 
panel.add(userNamePanel);
panel.add(userPasswordPanel);
panel.add(roles);
panel.add(loginBtn);

//adding the main panel to frame
frame.add(panel, BorderLayout.CENTER);
frame.setLocationRelativeTo(null);
frame.setVisible(true);

        
        button.addActionListener(e->{
        
        if(userNameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(frame,"Please enter userName");
            return;
        
        }
        
        if(userPasswordField.getPassword().length==0){
            JOptionPane.showMessageDialog(frame,"Please enter Password");
            return;
        }
        
        if(!admin.isSelected() && !cashier.isSelected()){
            JOptionPane.showMessageDialog(frame,"Choose Role");
            return;
        }
        
        
        
        //get values from fields
        
    String username = userNameField.getText();
    String password = new String(userPasswordField.getPassword());

    String sql = "SELECT role, full_name, user_id FROM users WHERE username = ? AND password = ?";

    try {
        PreparedStatement pst = db.con.prepareStatement(sql);

        // fill in the ? placeholders
        pst.setString(1, username);  
        pst.setString(2, password);  

        //run the query
        ResultSet rs = pst.executeQuery();

        //check if there's any row
       if (rs.next()) {
           
    String role = rs.getString("role");
    String fullName = rs.getString("full_name");
    int userId = rs.getInt("user_id");

 
    String selectedRole = admin.isSelected() ? "Admin" : "Cashier";

    if (!role.equals(selectedRole)) {
        JOptionPane.showMessageDialog(frame,
            "Incorrect role selected. Please try again.");
        return;
    }

    // Save to session //for later use
    Session.loggedInName = fullName;
    Session.loggedInRole = role;
    Session.loggedInUserId = userId;

    // Redirect
    if (role.equals("Admin")) {
        new adminRoleClass();
    } else {
        new cashierRoleClass();
    }

   
    frame.dispose();

} else {
    JOptionPane.showMessageDialog(frame,
        "Invalid username or password. Please try again.");
}

    } catch (SQLException sqlEx) {
        System.out.println(sqlEx.getMessage());
    }
    
  
});
        
        
        
        
        
        
        
        
     }
     
     
     
     
     
     
     
     }

