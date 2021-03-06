package student.action;


import com.opensymphony.xwork2.ActionSupport;

import student.dto.RegisterStudentDTO;


@SuppressWarnings("serial")
public class RegAction extends ActionSupport{
 private String message;
private String  name;
 private String dob;
 private String course;
 private String qualification;
 private String mobile;
 private String email;
 private String paymethod;
 private String ddno;
 private String chekno;
 private double depamount;
 private String password;
 
 
 
 public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
 
/* @Override
	public void validate() {
	   
	 if(paymethod==null){
			addFieldError("paymethod","please select payment method");
		}
	 if(depamount<5000||depamount>100000){
			addFieldError("message", "enter amount between 5000 to 100000");
	  
	}
	 if(name.isEmpty()||mobile.isEmpty()){
			addFieldError("message", "name/mobile Cannot be blank");
	 }
	 }
 
 
 */
 @Override
	public void validate() {
		try {
			double due=RegisterStudentDTO.getFee(depamount,course);
			  if(due<0){
				  addFieldError("message","deposit valid amount less than or equal to course fee");
			  }
		} catch (Exception e) {
		
		}
	}
 
	

	public String getName() {
	return name;
}




public void setName(String name) {
	this.name = name;
}




public String getDob() {
	return dob;
}




public void setDob(String dob) {
	this.dob = dob;
}




public String getCourse() {
	return course;
}




public void setCourse(String course) {
	this.course = course;
}




public String getQualification() {
	return qualification;
}




public void setQualification(String qualification) {
	this.qualification = qualification;
}




public String getMobile() {
	return mobile;
}




public void setMobile(String mobile) {
	this.mobile = mobile;
}




public String getEmail() {
	return email;
}




public void setEmail(String email) {
	this.email = email;
}




public String getPaymethod() {
	return paymethod;
}




public void setPaymethod(String paymethod) {
	this.paymethod = paymethod;
}




public String getDdno() {
	return ddno;
}




public void setDdno(String ddno) {
	this.ddno = ddno;
}




public String getChekno() {
	return chekno;
}




public void setChekno(String chekno) {
	this.chekno = chekno;
}


public double getDepamount() {
	return depamount;
}


public void setDepamount(double depamount) {
	this.depamount = depamount;
}



	@Override
	public String execute() throws Exception {
		Boolean status = false;
		//System.out.println("this is under execute!!"+name+dob+qualification+mobile+email+paymethod+ddno+chekno+depamount);
		try{
	  status=RegisterStudentDTO.doRegister(name,dob,course,qualification,mobile,email,paymethod,ddno,chekno,depamount,password);
		}catch(Exception e){
			System.out.println("this is"+e.toString());
			addFieldError("message", "DUPLICATE ENTRY");
			return ERROR;
		}
		
		if(status){
		 // RegisterStudentDTO.updateAvailableSeat(course);
		return SUCCESS;
	     }
		
	   else { 
		   addFieldError("message", "Student Already Registered with Us");
		   return INPUT;}
	    }
	
}
