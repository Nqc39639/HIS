package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeptmentDao {
    public String selectById(Integer id) throws SQLException {
        String sql = "select deptName from department where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        String deptName = null;
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            deptName = rs.getString("deptName");
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return deptName;
    }

    public Map<Integer, String> selectAll() throws SQLException {
        String sql = "select id,deptName from department";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ResultSet rs = ps.executeQuery();
        Map<Integer, String> map = new HashMap<>();
        while (rs.next()) {
            map.put(rs.getInt("id"), rs.getString("deptName"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return map;
    }
}
