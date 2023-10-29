package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;
import com.neuedu.his.entity.DrugInfo;
import com.neuedu.his.entity.Medicine;
import com.neuedu.his.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineDao {
    public void updateAll(String caseNumber) throws SQLException {
        String logSql = "insert into prescriptionLog(registerId,drugId,drugNumber,Time,operation) values(?,?,?,STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s'),?)";
        PreparedStatement logPs = MySQLdbUtils.getPreparedStatement(logSql);
        List<Medicine> list = selectByC(caseNumber, 1);
        for (Medicine medicine : list) {
            logPs.setInt(1, medicine.getRegisterId());
            logPs.setInt(2, medicine.getDrugId());
            logPs.setInt(3, medicine.getDrugNumber());
            String Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            logPs.setString(4, Time);
            logPs.setString(5, "退药");
            logPs.executeUpdate();
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, logPs, null);
        String sql = "update prescription set drugNumber=0,drugState=0 where registerId=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        PatientDao patientDao = new PatientDao();
        Patient patient = patientDao.selectByC(caseNumber);
        ps.setInt(1, patient.getId());
        ps.executeUpdate();
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
    }

    public void updateSelectedAll(Integer id) throws SQLException {
        String logSql = "insert into prescriptionLog(registerId,drugId,drugNumber,Time,operation) values(?,?,?,STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s'),?)";
        PreparedStatement logPs = MySQLdbUtils.getPreparedStatement(logSql);
        Medicine medicine = selectById(id);
        logPs.setInt(1, medicine.getRegisterId());
        logPs.setInt(2, medicine.getDrugId());
        logPs.setInt(3, medicine.getDrugNumber());
        String Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logPs.setString(4, Time);
        logPs.setString(5, "退药");
        logPs.executeUpdate();
        MySQLdbUtils.close(MySQLdbUtils.conn, logPs, null);
        String sql = "update prescription set drugNumber=0,drugState=0 where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
    }

    public void updateSelectedNumber(Integer id, Integer number) throws SQLException {
        String logSql = "insert into prescriptionLog(registerId,drugId,drugNumber,Time,operation) values(?,?,?,STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s'),?)";
        PreparedStatement logPs = MySQLdbUtils.getPreparedStatement(logSql);
        Medicine medicine = selectById(id);
        logPs.setInt(1, medicine.getRegisterId());
        logPs.setInt(2, medicine.getDrugId());
        logPs.setInt(3, number);
        String Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logPs.setString(4, Time);
        logPs.setString(5, "退药");
        logPs.executeUpdate();
        number = medicine.getDrugNumber() - number;
        MySQLdbUtils.close(MySQLdbUtils.conn, logPs, null);
        String sql = "update prescription set drugNumber=?,drugState=0 where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, number);
        ps.setInt(2, id);
        ps.executeUpdate();
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
    }

    public void updateById(Integer id) throws SQLException {
        String sql = "update prescription set drugState=1 where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        String logSql = "insert into prescriptionLog(registerId,drugId,drugNumber,Time,operation) values(?,?,?,STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s'),?)";
        PreparedStatement logPs = MySQLdbUtils.getPreparedStatement(logSql);
        Medicine medicine = selectById(id);
        logPs.setInt(1, medicine.getRegisterId());
        logPs.setInt(2, medicine.getDrugId());
        logPs.setInt(3, medicine.getDrugNumber());
        String Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logPs.setString(4, Time);
        logPs.setString(5, "发药");
        logPs.executeUpdate();
        MySQLdbUtils.close(MySQLdbUtils.conn, logPs, null);
    }

    public Medicine selectById(Integer id) throws SQLException {
        String sql = "select registerId,drugId,drugNumber from prescription where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        Medicine medicine = new Medicine();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            medicine.setRegisterId(rs.getInt("registerId"));
            medicine.setDrugId(rs.getInt("drugId"));
            medicine.setDrugNumber(rs.getInt("drugNumber"));
        }
        return medicine;
    }

    public List<Medicine> selectByC(String caseNumber, Integer n) throws SQLException {
        List<Medicine> list = new ArrayList<>();
        PatientDao patientDao = new PatientDao();
        Patient patient = patientDao.selectByC(caseNumber);
        if (patient.getId() == null) {
            return null;
        }
        EmployeeDao employeeDao = new EmployeeDao();
        String employee = employeeDao.selectById(patient.getDoctorId());
        String sql = "select id,drugId,drugNumber,creationTime,expenseState from prescription where registerId=? and drugState=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, patient.getId());
        ps.setInt(2, n);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            DrugInfoDao drugInfoDao = new DrugInfoDao();
            DrugInfo drugInfo = drugInfoDao.selectById(rs.getInt("drugId"));
            Medicine medicine = new Medicine();
            medicine.setId(rs.getInt("id"));
            medicine.setRegisterId(patient.getId());
            medicine.setCaseNumber(caseNumber);
            medicine.setRealName(patient.getRealName());
            medicine.setCardNumber(patient.getIdNumber());
            medicine.setHomeAddress(patient.getAddress());
            medicine.setEmployee(employee);
            medicine.setDrugId(rs.getInt("drugId"));
            medicine.setDrugCode(drugInfo.getDrugCode());
            medicine.setDrugName(drugInfo.getDrugName());
            medicine.setDrugPrice(drugInfo.getDrugPrice());
            medicine.setDrugNumber(rs.getInt("drugNumber"));
            medicine.setCreationTime(rs.getString("creationTime"));
            medicine.setExpenseState(rs.getInt("expenseState"));
            list.add(medicine);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }
}
