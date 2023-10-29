package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;
import com.neuedu.his.entity.DrugInfo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugInfoDao {
    public boolean add(String drugCode, String drugName, String drugFormat, String manufacturer, String drugType, String drugPrice, String creationDate, String quantity) throws SQLException {
        String sql = "insert into drugInfo(drugCode,drugName,drugFormat,manufacturer,drugType,drugPrice,creationDate,quantity) value(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setString(1, drugCode);
        ps.setString(2, drugName);
        ps.setString(3, drugFormat);
        ps.setString(4, manufacturer);
        ps.setString(5, drugType);
        ps.setBigDecimal(6, new BigDecimal(drugPrice));
        ps.setDate(7, Date.valueOf(creationDate));
        ps.setInt(8, Integer.parseInt(quantity));
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public boolean delete(Integer id) throws SQLException {
        String sql = "delete from drugInfo where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public boolean update(String drugCode, String drugName, String drugFormat, String manufacturer, String drugType, String drugPrice, String creationDate, String quantity) throws SQLException {
        String sql = "update drugInfo set drugName=?,drugFormat=?,manufacturer=?,drugType=?,drugPrice=?,creationDate=?,quantity=? where drugCode like ?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setString(1, drugName);
        ps.setString(2, drugFormat);
        ps.setString(3, manufacturer);
        ps.setString(4, drugType);
        ps.setBigDecimal(5, new BigDecimal(drugPrice));
        ps.setDate(6, Date.valueOf(creationDate));
        ps.setInt(7, Integer.parseInt(quantity));
        ps.setString(8, drugCode);
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public List<DrugInfo> selectAll() throws SQLException {
        String sql = "select id,drugCode,drugName,drugFormat,manufacturer,drugType,drugPrice,creationDate,quantity from drugInfo";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        List<DrugInfo> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DrugInfo drugInfo = new DrugInfo();
            drugInfo.setId(rs.getInt("id"));
            drugInfo.setDrugCode(rs.getString("drugCode"));
            drugInfo.setDrugName(rs.getString("drugName"));
            drugInfo.setDrugFormat(rs.getString("drugFormat"));
            drugInfo.setManufacturer(rs.getString("manufacturer"));
            drugInfo.setDrugType(rs.getString("drugType"));
            drugInfo.setDrugPrice(rs.getDouble("drugPrice"));
            drugInfo.setCreationDate(String.valueOf(rs.getDate("creationDate")));
            drugInfo.setQuantity(rs.getInt("quantity"));
            list.add(drugInfo);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }

    public DrugInfo selectById(Integer id) throws SQLException {
        String sql = "select drugCode,drugName,drugFormat,manufacturer,drugType,drugPrice,creationDate,quantity from drugInfo where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        DrugInfo drugInfo = new DrugInfo();
        while (rs.next()) {
            drugInfo.setId(id);
            drugInfo.setDrugCode(rs.getString("drugCode"));
            drugInfo.setDrugName(rs.getString("drugName"));
            drugInfo.setDrugFormat(rs.getString("drugFormat"));
            drugInfo.setManufacturer(rs.getString("manufacturer"));
            drugInfo.setDrugType(rs.getString("drugType"));
            drugInfo.setDrugPrice(rs.getDouble("drugPrice"));
            drugInfo.setCreationDate(rs.getString("creationDate"));
            drugInfo.setQuantity(rs.getInt("quantity"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return drugInfo;
    }

    public DrugInfo selectByC(String drugCode) throws SQLException {
        String sql = "select id,drugCode,drugName,drugFormat,manufacturer,drugType,drugPrice,creationDate,quantity from drugInfo where drugCode like ?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setString(1, drugCode);
        ResultSet rs = ps.executeQuery();
        DrugInfo drugInfo = new DrugInfo();
        while (rs.next()) {
            drugInfo.setId(rs.getInt("id"));
            drugInfo.setDrugCode(rs.getString("drugCode"));
            drugInfo.setDrugName(rs.getString("drugName"));
            drugInfo.setDrugFormat(rs.getString("drugFormat"));
            drugInfo.setManufacturer(rs.getString("manufacturer"));
            drugInfo.setDrugType(rs.getString("drugType"));
            drugInfo.setDrugPrice(rs.getDouble("drugPrice"));
            drugInfo.setCreationDate(rs.getString("creationDate"));
            drugInfo.setQuantity(rs.getInt("quantity"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return drugInfo;
    }

    public List<DrugInfo> selectByCT(String drugCode, String drugType) throws SQLException {
        StringBuffer sql = new StringBuffer("select id,drugCode,drugName,drugFormat,manufacturer,drugType,drugPrice,creationDate,quantity from drugInfo where ");
        PreparedStatement ps = null;
        List<DrugInfo> list = new ArrayList<>();
        if ((drugCode == null || drugCode.trim().equals("")) && (drugType != null && !drugType.trim().equals(""))) {
            sql.append("drugType like ?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setString(1, drugType);
        } else if ((drugCode != null && !drugCode.trim().equals("")) && (drugType == null || drugType.trim().equals(""))) {
            sql.append("drugCode like ?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setString(1, drugCode);
        } else {
            sql.append("drugCode like ? and drugType like ?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setString(1, drugCode);
            ps.setString(2, drugType);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DrugInfo drugInfo = new DrugInfo();
            drugInfo.setId(rs.getInt("id"));
            drugInfo.setDrugCode(rs.getString("drugCode"));
            drugInfo.setDrugName(rs.getString("drugName"));
            drugInfo.setDrugFormat(rs.getString("drugFormat"));
            drugInfo.setManufacturer(rs.getString("manufacturer"));
            drugInfo.setDrugType(rs.getString("drugType"));
            drugInfo.setDrugPrice(rs.getDouble("drugPrice"));
            drugInfo.setCreationDate(rs.getString("creationDate"));
            drugInfo.setQuantity(rs.getInt("quantity"));
            list.add(drugInfo);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }
}