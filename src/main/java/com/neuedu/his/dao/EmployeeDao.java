package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDao {
    public Integer selectByDRN(Integer deptmentId, Integer registLevelId, String noon) throws SQLException {
        String sql = "select id from employee where deptmentId=? and registLevelId=? and noon=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, deptmentId);
        ps.setInt(2, registLevelId);
        ps.setString(3, noon);
        ResultSet rs = ps.executeQuery();
        Integer id = 0;
        while (rs.next()) {
            id = rs.getInt("id");
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return id;
    }

    public String selectById(Integer id) throws SQLException {
        String sql = "select realName from employee where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        String realName = null;
        while (rs.next()) {
            realName = rs.getString("realName");
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return realName;
    }

    public Map<Integer, String> selectAll() throws SQLException {
        String sql = "select id,realName from employee";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ResultSet rs = ps.executeQuery();
        Map<Integer, String> map = new HashMap<>();
        while (rs.next()) {
            map.put(rs.getInt("id"), rs.getString("realName"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return map;
    }
}