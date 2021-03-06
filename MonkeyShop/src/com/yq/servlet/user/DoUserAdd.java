package com.yq.servlet.user;

import com.yq.entity.LMONKEY_USER;
import com.yq.service.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/manage/admin_douseradd")
public class DoUserAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String username=request.getParameter("userName");
        String name=request.getParameter("name");
        String pwd=request.getParameter("passWord");
        String sex=request.getParameter("sex");
        String birthday=request.getParameter("birthday");
        String email=request.getParameter("email");
        String mobile=request.getParameter("mobile");
        String address=request.getParameter("address");
        //创建用户实体
        LMONKEY_USER u=new LMONKEY_USER(username,name,pwd,sex,birthday,null,email,mobile, address,1);
        //加入到数据库的用户表中
        int count =  LMONKEY_USERDao.addUser(u);
        if(count >0 ) {
            response.sendRedirect("admin_douserselect");
        } else {
            PrintWriter out = response.getWriter();

            out.write("<script>");
            out.write("alert('用户添加失败')");
            out.write("location.href='manage/admin_useradd.jsp'");
            out.write("</script>");

        }
        //成功或失败重定向

    }
}
