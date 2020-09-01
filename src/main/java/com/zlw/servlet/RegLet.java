package com.zlw.servlet;

import com.zlw.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zlw
 * @create 2020-09-01 9:11
 */
@WebServlet(name = "RegLet")
public class RegLet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 接收客户端信息
        String username = request.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String password = request.getParameter("password");
        System.out.println(username + "--" + password);
        // 新建服务对象
        Service serv = new Service();
        //查询当前的用户是否已经被注册过了
        boolean aBoolean = serv.queryOne(username);
        boolean loged = false;
        if (!aBoolean) {
             loged = serv.register(username, password);
            // 验证处理
            if (loged) {
                System.out.print("Succss");
                request.getSession().setAttribute("username", username);
            } else {
                System.out.print("Failed");
            }
        } else {

        }
        // 返回信息到客户端
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("用户名：" + username);
        out.print("密码：" + password);
        out.print("注册状态：" + aBoolean);
        out.print("添加是否成功：" + loged);
        out.flush();
        out.close();
    }

}
