package org.iiitb.hospital.nimhans.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.iiitb.hospital.nimhans.database.DataAccessObject;
import org.iiitb.hospital.nimhans.modals.Doctor;

public class DoctorService {
	private Connection connection;

	public DoctorService() {
		connection = DataAccessObject.getInstance().Connect();
	}

	public int validateUserName(String emailId) {
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement("select doctor_id_fk from login where userName LIKE  ? ");
			pst.setString(1, emailId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return 1; // userName exists
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
		// userName doesn't exist
	}

	public int getDoctorID(String emailId, String password) {
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement("select doctor_id_fk from login where userName =  ? AND password = ?");

			pst.setString(1, emailId.trim());
			pst.setString(2, password.trim());

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				// doctor exist

				int doctor_id = rs.getInt(1);
				System.out.println(doctor_id);
				return doctor_id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return false;
		// doctor not found
		return 0;
	}

	public Doctor getDoctorInfo(int doctor_id) {
		Doctor doctor = null;

		try {
			String sql = "SELECT fname,mname,lname,specialization FROM doctor WHERE doctor_id = ?";
			PreparedStatement pstatement;

			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, doctor_id);
			ResultSet rs = pstatement.executeQuery();
			if (rs.next()) {

				String firstName = rs.getString(1);
				String middleName = rs.getString(2);
				String lastName = rs.getString(3);
				String specialization = rs.getString(4);
				doctor = new Doctor(doctor_id, firstName, middleName, lastName, specialization);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doctor;
	}

	public Doctor getDoctorFullName(int hospital_id, String type) {
		Doctor doctor = null;

		try {
			String sql = "SELECT doctor_id_fk, type FROM doctor_hospital_mapping WHERE 	hospital_id_fk=? AND type LIKE ?";

			PreparedStatement pstatement;

			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, hospital_id);
			pstatement.setString(2, "%" + type + "%");
			ResultSet rs = pstatement.executeQuery();
			if (rs.next()) {
				int doctor_id = rs.getInt(1);
				doctor = getDoctorInfo(doctor_id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctor;
	}

	public int createLoginHistory(int doctor_id) {
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement("INSERT INTO loginhistory(`doctor_id_fk`, `timeStamp`) VALUES (?,?)");
			pst.setInt(1, doctor_id);

			java.util.Date date = new java.util.Date();
			Timestamp timstamp = new Timestamp(date.getTime());
			pst.setTimestamp(2, timstamp);

			int result = pst.executeUpdate();

			if (result > 0) {
				return 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
