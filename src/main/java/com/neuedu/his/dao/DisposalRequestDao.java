package com.neuedu.his.dao;

import com.neuedu.his.common.MySQLdbUtils;
import com.neuedu.his.entity.DisposalRequest;
import com.neuedu.his.entity.MedicalTechnology;
import com.neuedu.his.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisposalRequestDao {
    public boolean update(String registerId, String medicalTechnologyId) throws SQLException {
        String sql = "update disposalRequest set expenseState=? where registerId=? and medicalTechnologyId=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, 2);
        ps.setInt(2, Integer.parseInt(registerId));
        ps.setInt(3, Integer.parseInt(medicalTechnologyId));
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public boolean delete(String registerId, String medicalTechnologyId) throws SQLException {
        String sql = "delete from disposalRequest where registerId=? and medicalTechnologyId=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, Integer.parseInt(registerId));
        ps.setInt(2, Integer.parseInt(medicalTechnologyId));
        if (ps.executeUpdate() > 0) {
            return true;
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return false;
    }

    public List<DisposalRequest> selectByC(String caseNumber) throws SQLException {
        PatientDao patientDao = new PatientDao();
        Patient patient = patientDao.selectByC(caseNumber);
        String sql = "select medicalTechnologyId,creationTime,expenseState from disposalRequest where registerId=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, patient.getId());
        ResultSet rs = ps.executeQuery();
        List<DisposalRequest> list = new ArrayList<>();
        while (rs.next()) {
            MedicalTechnologyDao medicaltechnologyDao = new MedicalTechnologyDao();
            MedicalTechnology medicaltechnology = medicaltechnologyDao.selectById(rs.getInt("medicalTechnologyId"));
            DisposalRequest disposalRequest = new DisposalRequest();
            disposalRequest.setMedicalTechnologyId(rs.getInt("medicalTechnologyId"));
            disposalRequest.setTechName(medicaltechnology.getTechName());
            disposalRequest.setTechPrice(medicaltechnology.getTechPrice());
            disposalRequest.setCreationTime(rs.getString("creationTime"));
            disposalRequest.setExpenseState(rs.getInt("expenseState"));
            disposalRequest.setCaseNumber(caseNumber);
            disposalRequest.setCardNumber(patient.getIdNumber());
            disposalRequest.setGender(patient.getGender());
            disposalRequest.setAge(patient.getAge());
            disposalRequest.setRealName(patient.getRealName());
            disposalRequest.setRegisterId(patient.getId());
            list.add(disposalRequest);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }

    public List<DisposalRequest> selectByR(String realName) throws SQLException {
        PatientDao patientDao = new PatientDao();
        Patient patient = patientDao.selectByR(realName);
        String sql = "select medicalTechnologyId,creationTime,expenseState from disposalRequest where registerId=?";
        PreparedStatement ps = MySQLdbUtils.getPreparedStatement(sql);
        ps.setInt(1, patient.getId());
        ResultSet rs = ps.executeQuery();
        List<DisposalRequest> list = new ArrayList<>();
        while (rs.next()) {
            MedicalTechnologyDao medicaltechnologyDao = new MedicalTechnologyDao();
            MedicalTechnology medicaltechnology = medicaltechnologyDao.selectById(rs.getInt("medicalTechnologyId"));
            DisposalRequest disposalRequest = new DisposalRequest();
            disposalRequest.setMedicalTechnologyId(rs.getInt("medicalTechnologyId"));
            disposalRequest.setTechName(medicaltechnology.getTechName());
            disposalRequest.setTechPrice(medicaltechnology.getTechPrice());
            disposalRequest.setCreationTime(rs.getString("creationTime"));
            disposalRequest.setExpenseState(rs.getInt("expenseState"));
            disposalRequest.setCaseNumber(patient.getCaseNumber());
            disposalRequest.setCardNumber(patient.getIdNumber());
            disposalRequest.setGender(patient.getGender());
            disposalRequest.setAge(patient.getAge());
            disposalRequest.setRealName(realName);
            disposalRequest.setRegisterId(patient.getId());
            list.add(disposalRequest);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }

    public List<DisposalRequest> selectByCR(String caseNumber, String realName) throws SQLException {
        List<DisposalRequest> list = new ArrayList<>();
        if ((realName == null || realName.trim().equals(""))) {
            list = selectByC(caseNumber);
        } else {
            list = selectByR(realName);
        }
        return list;
    }

    public List<DisposalRequest> selectByDM(String disposalState, String medicalTechnologyId) throws SQLException {
        StringBuffer sql = new StringBuffer("select registerId,medicalTechnologyId,creationTime from disposalRequest where ");
        PreparedStatement ps = null;
        List<DisposalRequest> list = new ArrayList<>();
        if ((disposalState == null || disposalState.trim().equals("")) && (medicalTechnologyId != null && !medicalTechnologyId.trim().equals(""))) {
            sql.append("medicalTechnologyId=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(medicalTechnologyId));
        } else if ((disposalState != null && !disposalState.trim().equals("")) && (medicalTechnologyId == null || medicalTechnologyId.trim().equals(""))) {
            sql.append("disposalState=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(disposalState));
        } else {
            sql.append("disposalState=? and medicalTechnologyId=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(disposalState));
            ps.setInt(2, Integer.parseInt(medicalTechnologyId));
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PatientDao patientDao = new PatientDao();
            Patient patient = patientDao.selectByI(rs.getInt("registerId"));
            MedicalTechnologyDao medicaltechnologyDao = new MedicalTechnologyDao();
            MedicalTechnology medicaltechnology = medicaltechnologyDao.selectById(rs.getInt("medicalTechnologyId"));
            DisposalRequest disposalRequest = new DisposalRequest();
            disposalRequest.setMedicalTechnologyId(rs.getInt("medicalTechnologyId"));
            disposalRequest.setTechName(medicaltechnology.getTechName());
            disposalRequest.setTechPrice(medicaltechnology.getTechPrice());
            disposalRequest.setCreationTime(rs.getString("creationTime"));
            disposalRequest.setRealName(patient.getRealName());
            disposalRequest.setCaseNumber(patient.getCaseNumber());
            disposalRequest.setRegisterId(rs.getInt("registerId"));
            list.add(disposalRequest);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }

    public List<DisposalRequest> selectByME(String medicalTechnologyId, String expenseState) throws SQLException {
        StringBuffer sql = new StringBuffer("select registerId,medicalTechnologyId,creationTime,expenseState from disposalRequest where ");
        PreparedStatement ps = null;
        List<DisposalRequest> list = new ArrayList<>();
        if ((expenseState == null || expenseState.trim().equals("")) && (medicalTechnologyId != null && !medicalTechnologyId.trim().equals(""))) {
            sql.append("medicalTechnologyId=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(medicalTechnologyId));
        } else if ((expenseState != null && !expenseState.trim().equals("")) && (medicalTechnologyId == null || medicalTechnologyId.trim().equals(""))) {
            sql.append("expenseState=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(expenseState));
        } else {
            sql.append("expenseState=? and medicalTechnologyId=?");
            ps = MySQLdbUtils.getPreparedStatement(sql.toString());
            ps.setInt(1, Integer.parseInt(expenseState));
            ps.setInt(2, Integer.parseInt(medicalTechnologyId));
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            PatientDao patientDao = new PatientDao();
            Patient patient = patientDao.selectByI(rs.getInt("registerId"));
            MedicalTechnologyDao medicaltechnologyDao = new MedicalTechnologyDao();
            MedicalTechnology medicaltechnology = medicaltechnologyDao.selectById(rs.getInt("medicalTechnologyId"));
            DisposalRequest disposalRequest = new DisposalRequest();
            disposalRequest.setMedicalTechnologyId(rs.getInt("medicalTechnologyId"));
            disposalRequest.setTechName(medicaltechnology.getTechName());
            disposalRequest.setTechPrice(medicaltechnology.getTechPrice());
            disposalRequest.setCreationTime(rs.getString("creationTime"));
            disposalRequest.setRealName(patient.getRealName());
            disposalRequest.setCaseNumber(patient.getCaseNumber());
            disposalRequest.setRegisterId(rs.getInt("registerId"));
            disposalRequest.setExpenseState(rs.getInt("expenseState"));
            list.add(disposalRequest);
        }
        MySQLdbUtils.close(MySQLdbUtils.conn, ps, null);
        return list;
    }
}
