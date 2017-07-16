package org.iiitb.hospital.nimhans.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.iiitb.hospital.nimhans.database.DataAccessObject;
import org.iiitb.hospital.nimhans.modals.Doctor;
import org.iiitb.hospital.nimhans.modals.Hospital;

public class HospitalService {
	private Connection connection;

	private DoctorService doctorService = new DoctorService();

	public HospitalService() {
		connection = DataAccessObject.getInstance().Connect();

	}

	public List<Hospital> sortHospitalsByType(List<Hospital> listOfHospital) {

		Collections.sort(listOfHospital, new Comparator<Hospital>() {
			@Override
			public int compare(Hospital o1, Hospital o2) {

				return o1.getHospitalType().compareToIgnoreCase(o2.getHospitalType());
			}
		});
		return listOfHospital;
	}

	public Hospital getHospitalInfo(int hospital_id, String hospitalType) {

		Hospital hospital = null;
		try {
			String sql = "SELECT * FROM " + hospitalType + " WHERE " + hospitalType + "_id =?";

			System.out.println("getHospitalInfo :" + sql);

			PreparedStatement pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, hospital_id);
			ResultSet rs = pstatement.executeQuery();

			if (rs.next()) {

				String name = rs.getString(2);
				String hospitalName = rs.getString(3);
				int sancBeds = rs.getInt(4);
				String G_O_NO_Date = rs.getString(5);
				hospital = new Hospital(hospital_id, name, null, hospitalName, sancBeds, G_O_NO_Date, hospitalType,
						null, null, null, null);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hospital;
	}

	public Hospital getHospitalInfoByDoctorID(int doctor_id) {

		Hospital hospital = null;
		int hospital_id = 0;
		String hospitalType = null;
		try {
			String sql = "SELECT hospital_id_fk, type FROM doctor_hospital_mapping WHERE doctor_id_fk=?";

			PreparedStatement pstatement;

			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, doctor_id);
			ResultSet rs = pstatement.executeQuery();
			if (rs.next()) {
				hospital_id = rs.getInt(1);
				hospitalType = rs.getString(2);
			}

			// find hospital information

			hospital = getHospitalInfo(hospital_id, hospitalType);
			Doctor doctor = doctorService.getDoctorInfo(doctor_id);

			hospital.setDoctor(doctor);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// add date only for first timr login and district name
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		hospital.setCurrentDateTime(sdf.format(date));

		String districtName = gethospitalDistrict(hospital_id, hospitalType);

		System.out.println(districtName + "  ");

		hospital.setDistrictName(districtName);

		return hospital;
	}

	public List<Hospital> getAllChildHospital(int hospital_id, String hospitalType) {
		List<Hospital> lisOfAllChildHospitals = new ArrayList<>();

		if (hospitalType.equalsIgnoreCase("DHC")) {

			// first find all THC corresponding to DHC
			// second find all CHC corresponding to THC
			// third find all PHC corresponding to CHC

			// first add parent hospital in list
			Hospital hospital = getHospitalInfo(hospital_id, hospitalType);
			if (hospital != null) {
				lisOfAllChildHospitals.add(hospital);

				List<Hospital> lisOfTHC = getAllHospitals(hospital_id, "THC", "DHC");

				lisOfAllChildHospitals.addAll(lisOfTHC);

				for (int i = 0; i < lisOfTHC.size(); i++) {

					int id = lisOfTHC.get(i).getHospital_id();

					List<Hospital> listOfCHC = getAllHospitals(id, "CHC", "THC");
					lisOfAllChildHospitals.addAll(listOfCHC);

					for (int j = 0; j < listOfCHC.size(); j++) {
						id = listOfCHC.get(j).getHospital_id();
						System.out.println("CHC id: " + id);

						List<Hospital> listOfPHC = getAllHospitals(id, "PHC", "CHC");
						lisOfAllChildHospitals.addAll(listOfPHC);
					}
				}
			}
		} else if (hospitalType.equalsIgnoreCase("THC")) {

			Hospital hospital = getHospitalInfo(hospital_id, hospitalType);
			if (hospital != null) {
				lisOfAllChildHospitals.add(hospital);

				List<Hospital> lisOfCHC = getAllHospitals(hospital_id, "CHC", "THC");

				lisOfAllChildHospitals.addAll(lisOfCHC);

				for (int i = 0; i < lisOfCHC.size(); i++) {
					int id = lisOfCHC.get(i).getHospital_id();

					List<Hospital> listOfPHC = getAllHospitals(id, "PHC", "CHC");
					lisOfAllChildHospitals.addAll(listOfPHC);
				}
			}

		} else if (hospitalType.equalsIgnoreCase("CHC")) {
			Hospital hospital = getHospitalInfo(hospital_id, hospitalType);
			lisOfAllChildHospitals.add(hospital);
			if (hospital != null) {
				lisOfAllChildHospitals.addAll(getAllHospitals(hospital_id, "PHC", "CHC"));
			}
		} else {
			lisOfAllChildHospitals.add(getHospitalInfo(hospital_id, hospitalType));
		}

		// lisOfAllChildHospitals = sortHospitalsByType(lisOfAllChildHospitals);

		return lisOfAllChildHospitals;
	}

	public List<Hospital> getAllHospitals(int hospital_id, String childHospitalType, String parentdHospitalType) {

		List<Hospital> listOfPHChospitals = null;
		try {

			String sql = "SELECT DISTINCT " + childHospitalType + "_id_fk FROM hospitalmapping WHERE "
					+ parentdHospitalType + "_id_fk = ?";

			System.out.println("getAllHospitals :" + sql + " id :" + hospital_id);

			PreparedStatement pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, hospital_id);
			ResultSet rs = pstatement.executeQuery();

			listOfPHChospitals = new ArrayList<>();

			while (rs.next()) {
				int PHC_hospital_id = rs.getInt(1);
				Hospital hospital = getHospitalInfo(PHC_hospital_id, childHospitalType);
				listOfPHChospitals.add(hospital);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfPHChospitals;
	}

	public Hospital getHospitalDoctorinfo(String emailId, String password) {

		int doctor_id = doctorService.getDoctorID(emailId, password);
		// System.out.println(doctor_id);
		Hospital hospital = null;
		if (doctor_id != 0) {
			int loginResult = doctorService.createLoginHistory(doctor_id);
			if (loginResult != 0) {
				hospital = getHospitalInfoByDoctorID(doctor_id);
			}
		}
		return hospital;
	}

	public String gethospitalDistrict(int hospital_id, String hospitalType) {

		String districtName = null;
		try {

			String sql = "SELECT dhc_id_fk FROM hospitalmapping WHERE " + hospitalType + "_id_fk = ?";

			System.out.println(sql);
			PreparedStatement pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, hospital_id);

			ResultSet rs = pstatement.executeQuery();
			if (rs.next()) {
				hospital_id = rs.getInt(1);
			}

			sql = "SELECT `districtName` FROM `dhc` WHERE dhc_id=?";
			System.out.println(sql + hospital_id);
			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, hospital_id);
			rs = pstatement.executeQuery();

			if (rs.next()) {

				districtName = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return districtName;
	}

}
