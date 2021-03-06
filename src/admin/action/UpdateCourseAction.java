package admin.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale.Category;

import com.database.DataBaseConnection;
import com.opensymphony.xwork2.ActionSupport;

import generalDAO.CourseDAO;
import pojo.Courses;


/**
 * @author inayat
 *
 */
@SuppressWarnings("serial")
public class UpdateCourseAction extends ActionSupport {
      
	private String courseName;
	private String course_category;
	private int total;
	private int available;
	private String is_available;
	private String duration;
	private double fee;
	
	
	

	public String getCourse_category() {
		return course_category;
	}

	public void setCourse_category(String course_category) {
		this.course_category = course_category;
	}

	public String getIs_available() {
		return is_available;
	}

	public void setIs_available(String is_available) {
		this.is_available = is_available;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}


	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String delete() throws Exception {
		Connection cn=DataBaseConnection.connect();
		String sql="delete from courses where course_name='"+courseName+"'";
	    Statement st=	cn.createStatement();
	    int i=0;
	    try{
	    i=st.executeUpdate(sql);
	    }catch(Exception e){
	    	addFieldError("message","course cannot be deleted because some students are enrolled in this course.Unenroll all student under this course before deletion!!");
			return INPUT;
	    }
	    st.close();
	     cn.close();
		if(i>0){
			addFieldError("message", courseName+": deleted Successfully!!!");
			return SUCCESS;
		}else if(i==0){
			addFieldError("message","Something went Wrong!!");
			return INPUT;
		}
		else
			return ERROR;
	}
	
	
	public String edit() throws Exception{
		Connection cn=DataBaseConnection.connect();
		String sql="UPDATE courses SET course_name=?,category=?,"
				+ "available=?,total=?,isavailable=?,duration=?,fee=? "
				+ "WHERE course_name='"+courseName+"'";
		System.out.println(getCourseName());
		System.out.println(sql);
	   PreparedStatement st=cn.prepareStatement(sql);
	   st.setString(1,courseName);
	   st.setString(2, course_category);
	   st.setInt(3, available);
	   st.setInt(4, total);
	   st.setString(5, is_available);
	   st.setString(6,duration);
	   st.setDouble(7, fee);
	   int i=st.executeUpdate();
	    st.close();
	    cn.close();
	    System.out.println(course_category);
	    System.out.println(getIs_available());
	    if(i>0){
		addFieldError("message","CHANGE SAVED!");
		return SUCCESS;
	    }else if(i==0){
	    	addFieldError("message","CHANGES CANNOT BE SAVE");
	    return INPUT;
	    }else{
	    	addFieldError("message","CHANGES CANNOT BE SAVE");
	    	return ERROR;
	    }
	    	
		
	}
	
	/*public String fetchDetail() throws ClassNotFoundException, SQLException{
		Courses c=CourseDAO.getCourseDetailByName(courseName);
		return null;
	}*/
	public String addCourse() throws ClassNotFoundException, SQLException{
		Connection cn=DataBaseConnection.connect();
		String sql="INSERT INTO courses(course_name,category,available,total,isavailable,duration,fee)"+
		"values(?,?,?,?,?,?,?)";
		 /*  System.out.println("name=+"+courseName);
		   System.out.println("name=+"+course_category);
		   System.out.println("name=+"+available);                  //testing...........
		   System.out.println("name=+"+total);
		   System.out.println("name=+"+is_available);
		   System.out.println("name=+"+duration);
		   System.out.println("name=+"+fee);*/
	   PreparedStatement st=cn.prepareStatement(sql);
	   st.setString(1,courseName);
	   st.setString(2, course_category);
	   st.setInt(3, available);
	   st.setInt(4, total);
	   st.setString(5, is_available);
	   st.setString(6,duration);
	   st.setDouble(7, fee);
	   int i=0;
	   try{
	  i=st.executeUpdate();
	   }catch(Exception e){
		   addFieldError("message","NAME already exist");
		    return INPUT;
	   }
	    st.close();
	    cn.close();
	 
	    if(i>0){
		addFieldError("message","New Course added!");
		return SUCCESS;
	    }else if(i==0){
	    	addFieldError("message","TRY PROVIDING UNIQUE COURSE NAME!!!");
	    return INPUT;
	    }else{
	    	addFieldError("message","SOME ERROR OCUURED");
	    	return ERROR;
	    }
	    	
	}
}
