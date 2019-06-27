package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.User;
import service.UserSvc;
import utils.JavaBeanToString;

public class UserCtrl extends  HttpServlet{
   public UserCtrl(){
       super();;
   }

   @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
       System.out.println(("post start23333"));
       res.setCharacterEncoding("UTF-8");
       res.setContentType("application/json; charset=UTF-8");

       PrintWriter out = res.getWriter();
       UserSvc uSvc = new UserSvc();

       String operationType = req.getParameter("type");
       System.out.println(operationType);



       if(operationType == null){
           out.println(-1);
           return;
       }

       if(operationType.equals("getOneUserInfo")){
           int id = Integer.parseInt( req.getParameter("id"));
           try {
               System.out.println("testabc1232323");
               User user =  uSvc.get(id);
               out.println(123);

               out.write(JavaBeanToString.toString(user));
           }catch (IllegalAccessException e){
               e.printStackTrace();
           }

           return;
       }

       if(operationType.equals("addOneUser")){
           String username = req.getParameter("username");
           System.out.println(username);
           String password = req.getParameter("password");
           int age = Integer.parseInt( req.getParameter("age"));
           String profilePic = req.getParameter("profilePic");
           String gender = req.getParameter("gender");
           String job = req.getParameter("job");

           User newUser = new User();
           newUser.setUsername(username);
           newUser.setPassword(password);
           newUser.setProfilePic(profilePic);
           newUser.setAge(age);
           newUser.setjob(job);
           newUser.setgender(gender);
           boolean addRes = uSvc.add(newUser);
           out.write(addRes?"success":"fail");
           return;
       }
   }
}
