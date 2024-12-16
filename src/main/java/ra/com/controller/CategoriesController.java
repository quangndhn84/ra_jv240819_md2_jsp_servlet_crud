package ra.com.controller;

import ra.com.entity.Categories;
import ra.com.repository.imp.CategoriesRepositoryImp;
import ra.com.service.CategoriesService;
import ra.com.service.imp.CategoriesServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesController", value = "/CategoriesController")
public class CategoriesController extends HttpServlet {
    private CategoriesService categoriesService;

    public CategoriesController() {
        categoriesService = new CategoriesServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. Lấy tham số action truyền trên URL
        String action = request.getParameter("action");
        if (action.equals("findAll")) {
            findAllCategories(request, response);
        } else if (action.equals("initUpdate")) {
            //1. Thực hiện lấy thông tin catalog theo id
            int catalogId = Integer.parseInt(request.getParameter("catalogId"));
            Categories catalog = categoriesService.findById(catalogId);
            //2. set catalog vào request
            request.setAttribute("catalog", catalog);
            //3. Chuyển sang trang updateCatalog.jsp
            request.getRequestDispatcher("views/updateCatalog.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            //1. Lấy mã danh mục cần xóa trong request
            int catalogId = Integer.parseInt(request.getParameter("catalogId"));
            //2. Gọi service thực hiện xoas
            boolean result = categoriesService.delete(catalogId);
            //3. Xử lý kết quả
            if (result) {
                //Thực hiện gọi sang findAllCategories lấy lại danh sách danh mục và hiển thị trên categories.jsp
                findAllCategories(request, response);
            } else {
                //Chuyển sang trang error.jsp
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }

        }
    }

    public void findAllCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categories> listCategories = categoriesService.findAll();
        //2. add listCategories vào request
        request.setAttribute("listCategories", listCategories);
        //3. Chuyển sang trang categories.jsp và forward toàn bộ request và response
        request.getRequestDispatcher("views/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equals("create")) {
            //1. Lấy dữ liệu trên form thêm mới
            Categories catalog = new Categories();
            catalog.setCatalogName(request.getParameter("catalogName"));
            catalog.setDescription(request.getParameter("description"));
            catalog.setStatus(Boolean.parseBoolean(request.getParameter("status")));
            //2. Goi service thuc hien them mới
            boolean result = categoriesService.save(catalog);
            //3. Xử lý kết quả
            if (result) {
                //Thực hiện gọi sang findAllCategories lấy lại danh sách danh mục và hiển thị trên categories.jsp
                findAllCategories(request, response);
            } else {
                //Chuyển sang trang error.jsp
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        } else if (action.equals("update")) {
            //1. Lấy dữ liệu trên form cập nhật
            Categories catalog = new Categories();
            catalog.setCatalogId(Integer.parseInt(request.getParameter("catalogId")));
            catalog.setCatalogName(request.getParameter("catalogName"));
            catalog.setDescription(request.getParameter("description"));
            catalog.setStatus(Boolean.parseBoolean(request.getParameter("status")));
            //2. Gọi service cập nhật dữ liệu trong db
            boolean result = categoriesService.update(catalog);
            //3. Xử lý kết quả
            if (result) {
                //Thực hiện gọi sang findAllCategories lấy lại danh sách danh mục và hiển thị trên categories.jsp
                findAllCategories(request, response);
            } else {
                //Chuyển sang trang error.jsp
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }
    }
}