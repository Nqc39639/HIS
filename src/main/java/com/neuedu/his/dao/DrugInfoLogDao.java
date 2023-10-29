package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;
import com.neuedu.his.entity.DrugInfo;
import com.neuedu.his.entity.DrugInfoLog;
import com.neuedu.his.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrugInfoLogDao {
    public boolean delete(Integer id) throws SQLException {
        String sql = "delete from prescriptionLog where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public boolean update(String caseNumber, String drugCode, String drugNumber) throws SQLException {
        String sql = "update prescriptionLog set drugNumber=? where registerId=? and drugId=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        PatientDao patientDao = new PatientDao();
        DrugInfoDao drugInfoDao = new DrugInfoDao();
        Patient patient = patientDao.selectByC(caseNumber);
        DrugInfo drugInfo = drugInfoDao.selectByC(drugCode);
        ps.setInt(1, Integer.parseInt(drugNumber));
        ps.setInt(2, patient.getId());
        ps.setInt(3, drugInfo.getId());
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public List<DrugInfoLog> selectByCT(String drugCode, String Time) throws SQLException {
        StringBuffer sql = new StringBuffer("select id,registerId,drugId,drugNumber,Time,operation from prescriptionLog where ");
        PreparedStatement ps = null;
        List<DrugInfoLog> list = new ArrayList<>();
        if ((drugCode == null || drugCode.trim().equals("")) && (Time != null && !Time.trim().equals(""))) {
            sql.append("Time like ?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setString(1, Time + "%");
        } else if ((drugCode != null && !drugCode.trim().equals("")) && (Time == null || Time.trim().equals(""))) {
            sql.append("drugId=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            DrugInfoDao drugInfoDao = new DrugInfoDao();
            DrugInfo drugInfo = drugInfoDao.selectByC(drugCode);
            ps.setInt(1, drugInfo.getId());
        } else {
            sql.append("drugId=? and Time like ?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            DrugInfoDao drugInfoDao = new DrugInfoDao();
            DrugInfo drugInfo = drugInfoDao.selectByC(drugCode);
            ps.setInt(1, drugInfo.getId());
            ps.setString(2, Time + "%");
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DrugInfoLog drugInfoLog = new DrugInfoLog();
            drugInfoLog.setId(rs.getInt("id"));
            drugInfoLog.setRegisterId(rs.getInt("registerId"));
            PatientDao patientDao = new PatientDao();
            Patient patient = patientDao.selectByI(rs.getInt("registerId"));
            drugInfoLog.setCaseNumber(patient.getCaseNumber());
            drugInfoLog.setRealName(patient.getRealName());
            drugInfoLog.setGender(patient.getGender());
            drugInfoLog.setCardNumber(patient.getIdNumber());
            drugInfoLog.setBirthdate(patient.getBirthdate());
            drugInfoLog.setAge(patient.getAge());
            drugInfoLog.setHomeAddress(patient.getAddress());
            drugInfoLog.setVisitDate(patient.getVisitDate());
            drugInfoLog.setDepartmentName(patient.getDeptmentName());
            drugInfoLog.setEmployeeName(patient.getDoctorName());
            drugInfoLog.setDrugId(rs.getInt("drugId"));
            DrugInfoDao drugInfoDao = new DrugInfoDao();
            DrugInfo drugInfo = drugInfoDao.selectById(rs.getInt("drugId"));
            drugInfoLog.setDrugCode(drugInfo.getDrugCode());
            drugInfoLog.setDrugName(drugInfo.getDrugName());
            drugInfoLog.setDrugFormat(drugInfo.getDrugFormat());
            drugInfoLog.setManufacturer(drugInfo.getManufacturer());
            drugInfoLog.setDrugType(drugInfo.getDrugType());
            drugInfoLog.setDrugPrice(drugInfo.getDrugPrice());
            drugInfoLog.setCreationDate(drugInfo.getCreationDate());
            drugInfoLog.setDrugNumber(rs.getInt("drugNumber"));
            drugInfoLog.setTime(rs.getDate("Time").toString());
            drugInfoLog.setOperation(rs.getString("operation"));
            list.add(drugInfoLog);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }
}