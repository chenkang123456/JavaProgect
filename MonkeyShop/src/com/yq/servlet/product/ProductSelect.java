package com.yq.servlet.product;

import com.yq.entity.LMONKEY_PRODUCT;
import com.yq.service.LMONKEY_PRODUCTDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manage/admin_productselect")
public class ProductSelect extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<LMONKEY_PRODUCT> plist = LMONKEY_PRODUCTDao.selectAll();

        request.setAttribute("plist",plist);
        request.getRequestDispatcher("admin_product.jsp").forward(request,response);
    }
}
