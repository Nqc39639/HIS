package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;
import com.neuedu.his.entity.Patient;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PatientDao {
    public boolean add(Patient patient) throws SQLException {
        String sql = "insert into register(caseNumber,realName,gender,cardNumber,birthdate,age,homeAddress,visitDate,noon,deptmentId,employeeId,registLevelId,registMethodId,isBook,registMoney,visitState) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setString(1, patient.getCaseNumber());
        ps.setString(2, patient.getRealName());
        ps.setString(3, patient.getGender());
        ps.setString(4, patient.getIdNumber());
        ps.setDate(5, Date.valueOf(patient.getBirthdate()));
        ps.setInt(6, patient.getAge());
        ps.setString(7, patient.getAddress());
        ps.setString(8, patient.getVisitDate());
        ps.setString(9, patient.getNoon());
        ps.setInt(10, patient.getDepartmentId());
        ps.setInt(11, patient.getDoctorId());
        ps.setInt(12, patient.getRegistLevel());
        ps.setInt(13, patient.getPaymentMethodId());
        ps.setString(14, patient.getOnOffSwitch());
        ps.setInt(15, patient.getAmountDue());
        ps.setInt(16, 1);
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public boolean delete(Patient patient) throws SQLException {
        String sql = "delete from register where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, patient.getId());
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public boolean update(Integer id) throws SQLException {
        String sql = "update register set visitState=? where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, 4);
        ps.setInt(2, id);
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public List<Patient> selectAll() throws SQLException {
        String sql = "select id,caseNumber,realName,employeeId,visitState,visitDate,deptmentId from register";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        List<Patient> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setCaseNumber(rs.getString("caseNumber"));
            EmployeeDao employeeDao = new EmployeeDao();
            Map<Integer, String> map1 = employeeDao.selectAll();
            patient.setDoctorName(map1.get(rs.getInt("employeeId")));
            patient.setRealName(rs.getString("realName"));
            DeptmentDao deptmentDao = new DeptmentDao();
            Map<Integer, String> map2 = deptmentDao.selectAll();
            patient.setDeptmentName(map2.get(rs.getInt("deptmentId")));
            patient.setVisitState(rs.getInt("visitState"));
            patient.setVisitDate(rs.getString("visitDate"));
            list.add(patient);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }

    public List<Patient> selectByCR(String caseNumber, String realName) throws SQLException {
        StringBuffer sql = new StringBuffer("select id,caseNumber,realName,gender,cardNumber,age,employeeId,visitState,visitDate,deptmentId from register where ");
        PreparedStatement ps = null;
        List<Patient> list = new ArrayList<>();
        if ((caseNumber == null || caseNumber.trim().equals("")) && (realName != null && !realName.trim().equals(""))) {
            sql.append("realName like ?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            realName = "%" + realName + "%";
            ps.setString(1, realName);
        } else if ((caseNumber != null && !caseNumber.trim().equals("")) && (realName == null || realName.trim().equals(""))) {
            sql.append("caseNumber=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setString(1, caseNumber);
        } else {
            sql.append("caseNumber=? and realName like ?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            realName = "%" + realName + "%";
            ps.setString(1, caseNumber);
            ps.setString(2, realName);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setCaseNumber(rs.getString("caseNumber"));
            patient.setRealName(rs.getString("realName"));
            EmployeeDao employeeDao = new EmployeeDao();
            String doctorName = employeeDao.selectById(rs.getInt("employeeId"));
            patient.setDoctorName(doctorName);
            patient.setVisitDate(rs.getString("visitDate"));
            patient.setVisitState(rs.getInt("visitState"));
            DeptmentDao deptmentDao = new DeptmentDao();
            String deptmentName = deptmentDao.selectById(rs.getInt("deptmentId"));
            patient.setDeptmentName(deptmentName);
            list.add(patient);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }

    public List<Patient> selectByVD(String visitState, String department) throws SQLException {
        StringBuffer sql = new StringBuffer("select id,caseNumber,realName,employeeId,visitState,visitDate,deptmentId from register where ");
        PreparedStatement ps = null;
        List<Patient> list = new ArrayList<>();
        if ((visitState == null || visitState.trim().equals("")) && (department != null && !department.trim().equals(""))) {
            sql.append("deptmentId=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(department));
        } else if ((visitState != null && !visitState.trim().equals("")) && (department == null || department.trim().equals(""))) {
            sql.append("visitState=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(visitState));
        } else {
            sql.append("visitState=? and deptmentId=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(visitState));
            ps.setInt(2, Integer.parseInt(department));
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // 每一个对象是一个患者
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setCaseNumber(rs.getString("caseNumber"));
            patient.setRealName(rs.getString("realName"));
            EmployeeDao employeeDao = new EmployeeDao();
            String doctorName = employeeDao.selectById(rs.getInt("employeeId"));
            patient.setDoctorName(doctorName);
            patient.setVisitDate(rs.getString("visitDate"));
            patient.setVisitState(rs.getInt("visitState"));
            DeptmentDao deptmentDao = new DeptmentDao();
            String deptmentName = deptmentDao.selectById(rs.getInt("deptmentId"));
            patient.setDeptmentName(deptmentName);
            list.add(patient);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }

    public Patient selectByC(String caseNumber) throws SQLException {
        String sql = "select id,realName,gender,cardNumber,age,homeAddress,employeeId from register where caseNumber like ?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        caseNumber = "%" + caseNumber + "%";
        ps.setString(1, caseNumber);
        Patient patient = new Patient();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            patient.setId(rs.getInt("id"));
            patient.setCaseNumber(caseNumber);
            patient.setRealName(rs.getString("realName"));
            patient.setIdNumber(rs.getString("cardNumber"));
            patient.setGender(rs.getString("gender"));
            patient.setAge(rs.getInt("age"));
            patient.setAddress(rs.getString("homeAddress"));
            patient.setDoctorId(rs.getInt("employeeId"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return patient;
    }

    public Patient selectByI(Integer id) throws SQLException {
        String sql = "select caseNumber,realName,gender,cardNumber,birthdate,age,homeAddress,visitDate,deptmentId,employeeId from register where id=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, id);
        Patient patient = new Patient();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            patient.setCaseNumber(rs.getString("caseNumber"));
            patient.setRealName(rs.getString("realName"));
            patient.setGender(rs.getString("gender"));
            patient.setIdNumber(rs.getString("cardNumber"));
            patient.setBirthdate(rs.getString("birthdate"));
            patient.setAge(rs.getInt("age"));
            patient.setAddress(rs.getString("homeAddress"));
            patient.setVisitDate(rs.getDate("visitDate").toString());
            patient.setDepartmentId(rs.getInt("deptmentId"));
            DeptmentDao deptmentDao = new DeptmentDao();
            String deptmentName = deptmentDao.selectById(rs.getInt("deptmentId"));
            patient.setDeptmentName(deptmentName);
            patient.setDoctorId(rs.getInt("employeeId"));
            EmployeeDao employeeDao = new EmployeeDao();
            String employeeName = employeeDao.selectById(rs.getInt("employeeId"));
            patient.setDoctorName(employeeName);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return patient;
    }

    public Patient selectByR(String realName) throws SQLException {
        String sql = "select id,caseNumber,gender,cardNumber,age from register where realName like ?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        realName = "%" + realName + "%";
        ps.setString(1, realName);
        Patient patient = new Patient();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            patient.setId(rs.getInt("id"));
            patient.setRealName(realName);
            patient.setCaseNumber(rs.getString("caseNumber"));
            patient.setIdNumber(rs.getString("cardNumber"));
            patient.setGender(rs.getString("gender"));
            patient.setAge(rs.getInt("age"));
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return patient;
    }
}