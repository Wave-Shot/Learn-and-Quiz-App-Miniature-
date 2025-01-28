import javax.faces.bean.ManagedBean; 
import javax.faces.bean.SessionScoped; 
import java.sql.*; 
@ManagedBean(name="obj") 
@SessionScoped 
public class Detail{ 
 private String name,error=""; 
 private String code,gender,phonenumber,currenteducation,country,mailid; 
 private int age,score=0,prev_score=0; 
 private String q1,q2,q3; 
 public Detail() { 
 } 
 public String getName() { 
 return name; 
 } 
 public void setName(String name) { 
 this.name = name; 
 } 
 public String getCode() { 
 return code; 
 } 
 public void setCode(String code) { 
 this.code = code; 
 } 
 public String getError(){ 
 return error; 
 } 
 public String getGender() { 
 return gender; 
 } 
 public void setGender(String gender) { 
 this.gender = gender; 
 } 
 public String getPhonenumber() { 
 return phonenumber; 
 } 
 public void setPhonenumber(String phonenumber) { 
 this.phonenumber = phonenumber; 
 } 
 public String getCurrenteducation() { 
 return currenteducation; 
 } 
 public void setCurrenteducation(String currenteducation) { 
 this.currenteducation = currenteducation; 
 } 
 public String getCountry() { 
 return country; 
 } 
 public void setCountry(String country) { 
 this.country = country; 
 } 
 public String getMailid() { 
 return mailid; 
 } 
 public void setMailid(String emailid) { 
 this.mailid = emailid; 
 } 
 public int getAge() { 
 return age; 
 } 
 public void setAge(int age) { 
 this.age = age; 
 } 
 public String getQ1() { 
 return q1; 
 } 
 public void setQ1(String q1) { 
 this.q1 = q1; 
 } 
 public String getQ2() { 
 return q2; 
 } 
 public void setQ2(String q2) { 
 this.q2 = q2; 
 } 
 public String getQ3() { 
 return q3; 
 } 
 public void setQ3(String q3) { 
 this.q3 = q3; 
 } 
 public int getScore() { 
 return score; 
 } 

    public int getPrev_score() {
        return prev_score;
    }

    public void setPrev_score(int prev_score) {
        this.prev_score = prev_score;
    }

   
 
 public String register(){ 
 
 
 
 try{ 
 Class.forName("com.mysql.jdbc.Driver"); 
 Connection con=DriverManager.getConnection( 
"jdbc:mysql://localhost:3306/website","root",""); 
 Statement stmt=con.createStatement(); 
 String sql=String.format("insert into user_detail values('%s','%s','%d','%s','%s','%s','%s','%s')", name,code,age,gender,phonenumber,mailid,currenteducation,country); 
  String s=String.format("insert into score values('%s','%d')", name,score);
  stmt.executeUpdate(s);
 stmt.executeUpdate(sql); 
 
 } 
 catch(Exception e){ 
 System.out.println(e); 
 } 
 return "index"; 
 
 } 
 public String score(){
      try{
          Class.forName("com.mysql.jdbc.Driver"); 
                 Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/website","root",""); 
                 Statement stmt=con.createStatement(); 
                 ResultSet rs=stmt.executeQuery("select * from score");
                 while(rs.next()){
                     if(name.equals(rs.getString(1))){
                         prev_score=rs.getInt(2);
                         break;
                     }
                 }
      }
      catch(Exception e){
          
      }
      score=0;
 if(score<30){ 
 if(q1.equals("yes")){ 
 score+=10; 
 } 
 if(q2.equals("class")){ 
 score+=10; 
 } 
 if(q3.equals("abstraction")){ 
 score+=10; 
 } 
 } 
 System.out.println(q1+" "+q2+" "+q3);
 
       try{
           int flag=0;
                    Class.forName("com.mysql.jdbc.Driver"); 
                 Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/website","root",""); 
                 Statement stmt=con.createStatement(); 
                 ResultSet rs=stmt.executeQuery("select * from score");
                 while(rs.next()){
                     flag=1;
                     if(name.equals(rs.getString(1))){
                        String s=String.format("update score set mark=%d where name='%s'",score,name);
                        stmt.executeUpdate(s); 
                        break;
                     }
                 }
                 if(flag==0){
                      
                   //stmt.executeUpdate(sql); 
                 }
                
 
                 
        } catch(Exception e){

        }
       
 return "score"; 
 
 } 
 public String login(){ 
 int flag=0; 
 System.out.println("Employee detail inserted"); 
 System.out.println(name+" "+code+" "); 
 try{ 
 Class.forName("com.mysql.jdbc.Driver"); 
 Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/website","root",""); 
 Statement stmt=con.createStatement(); 
 ResultSet rs=stmt.executeQuery("select * from user_detail"); 
 while(rs.next()){ 
 if(name.equals(rs.getString(1))){ 
 flag=1; 
 if(code.equals(rs.getString(2))){ 
 setAge(Integer.parseInt(rs.getString(3))); 
 setGender(rs.getString(4)); 
 setPhonenumber(rs.getString(5)); 
 setMailid(rs.getString(6)); 
 setCurrenteducation(rs.getString(7)); 
 setCountry(rs.getString(8)); 
 return "success"; 
 } 
 else{ 
 error="Please check your code to login...."; 
 return "fail"; 
 } 
 } 
 } 
 if(flag==0){ 
 error="Register first"; 
 return "fail"; 
 } 
 } 
 catch(Exception e){ 
 System.out.println(e); 
 } 
 
 return "success"; 
 } 
 
} 