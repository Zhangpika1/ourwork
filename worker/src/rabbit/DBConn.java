/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rabbit;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class DBConn {

  private  Connection conn  = null;
  private Statement stmt = null;
    private  ResultSet rs=null;
    String username="root";
        String password ="123456";

    public DBConn (){   //构造方法      



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //conn = DriverManager.getConnection(url,username,password);
        }catch(ClassNotFoundException e){
            System.err.println(e.getMessage());
        } //catch (SQLException ex) {
            //Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }
    
    public int Check(String sql){
      //  int result = 0;
        try{
             conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/salarydb?characterEncoding=utf8&serverTimezone=GMT%2B8",username,password);
             stmt = conn.createStatement();
             rs  = stmt.executeQuery(sql);
            if(rs.next()){
                return 1 ;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public ResultSet Search(String sql){    //建立查询

  
        try {
            conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/salarydb?characterEncoding=utf8&serverTimezone=GMT%2B8",username,password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
        return rs;
    }

    public int Update(String sql){      //操作数据库
     
        int result = 0;
        try {
             conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/salarydb?characterEncoding=utf8&serverTimezone=GMT%2B8",username,password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
               result=0;
        }
        return result;
    }

   /**
	 * 关闭数据库连接
	 */
	public void close(){
		try{
			if(rs != null){
				rs.close();
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		
		try{
			if(stmt != null){
				stmt.close();
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		
		try{
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}


}
