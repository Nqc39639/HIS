package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;
import com.neuedu.his.entity.MedicalTechnology;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicalTechnologyDao {
    public MedicalTechnology selectById(Integer id) throws SQLException {
        String sql = "select techName,techPrice from medicalTechnology where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        MedicalTechnology medicaltechnology = new MedicalTechnology();
        while (rs.next()) {
            medicaltechnology.setId(id);
            medicaltechnology.setTechName(rs.getString("techName"));
            medicaltechnology.setTechPrice(rs.getDouble("techPrice"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return medicaltechnology;
    }

    public MedicalTechnology selectByTn(String techName) throws SQLException {
        String sql = "select id from medicalTechnology where techName like ?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setString(1, techName);
        ResultSet rs = ps.executeQuery();
        MedicalTechnology medicaltechnology = new MedicalTechnology();
        while (rs.next()) {
            medicaltechnology.setId(rs.getInt("id"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return medicaltechnology;
    }
}
