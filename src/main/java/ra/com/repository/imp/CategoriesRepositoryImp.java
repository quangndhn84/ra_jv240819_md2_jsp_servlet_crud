package ra.com.repository.imp;

import ra.com.entity.Categories;
import ra.com.repository.CategoriesRepository;
import ra.com.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriesRepositoryImp implements CategoriesRepository {
    @Override
    public List<Categories> findAll() {
        List<Categories> listCategories = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_categories()}");
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<Categories>();
            while (rs.next()) {
                Categories catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalog_id"));
                catalog.setCatalogName(rs.getString("catalog_name"));
                catalog.setDescription(rs.getString("catalog_description"));
                catalog.setStatus(rs.getBoolean("catalog_status"));
                listCategories.add(catalog);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    @Override
    public boolean save(Categories catalog) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call insert_categories(?,?,?)}");
            callSt.setString(1, catalog.getCatalogName());
            callSt.setString(2, catalog.getDescription());
            callSt.setBoolean(3, catalog.isStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public Categories findById(int catalogId) {
        Categories catalog = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_categories_by_id(?)}");
            callSt.setInt(1, catalogId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                catalog = new Categories();
                catalog.setCatalogId(rs.getInt("catalog_id"));
                catalog.setCatalogName(rs.getString("catalog_name"));
                catalog.setDescription(rs.getString("catalog_description"));
                catalog.setStatus(rs.getBoolean("catalog_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return catalog;
    }

    @Override
    public boolean update(Categories catalog) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_categories(?,?,?,?)}");
            callSt.setInt(1, catalog.getCatalogId());
            callSt.setString(2, catalog.getCatalogName());
            callSt.setString(3, catalog.getDescription());
            callSt.setBoolean(4, catalog.isStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public boolean delete(int catalogId) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_categories(?)}");
            callSt.setInt(1, catalogId);
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}
