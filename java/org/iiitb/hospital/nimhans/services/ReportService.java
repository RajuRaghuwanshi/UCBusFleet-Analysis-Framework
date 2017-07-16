package org.iiitb.hospital.nimhans.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.iiitb.hospital.nimhans.database.DataAccessObject;
import org.iiitb.hospital.nimhans.modals.Hospital;
import org.iiitb.hospital.nimhans.modals.Report;

public class ReportService {
	private Connection connection;
	private HospitalService hospitalService = new HospitalService();
	private DoctorService doctorService = new DoctorService();
	public ReportService() {
		connection = DataAccessObject.getInstance().Connect();
	}

	public Report getReportInfo(int hospital_id, String hospitalType) {

		Report report = null;
		try {
			String sql = "SELECT `reportTimeStamp`, `old_smd_male`, `old_smd_female`, `new_smd_male`, `new_smd_female`, `old_cmd_male`, `old_cmd_female`, `new_cmd_male`, `new_cmd_female`, `old_alcohal_male`, `old_alcohal_female`, `new_alcohal_male`, `new_alcohal_female`, `old_male_reffered_to_highercenters`, `old_female_reffered_to_highercenters`, `new_male_reffered_to_highercenters`, `new_female_reffered_to_highercenters`, `remarks`, `old_psychiatricdisorders_male`, `old_psychiatricdisorders_female`, `new_psychiatricdisorders_male`, `new_psychiatricdisorders_female`,`old_male_suicidecases`, `old_female_suicidecases`, `new_male_suicidecases`, `new_female_suicidecases` FROM report WHERE hospital_id_fk = ? AND LOWER(hospitalType) = ?";
			PreparedStatement pstatement;

			pstatement = connection.prepareStatement(sql);
			pstatement.setInt(1, hospital_id);
			pstatement.setString(2, hospitalType.toLowerCase());
			ResultSet rs = pstatement.executeQuery();
			if (rs.next()) {

				Timestamp reportTimeStamp = rs.getTimestamp("reportTimeStamp");
				int old_smd_male = rs.getInt("old_smd_male");
				int old_smd_female = rs.getInt("old_smd_female");
				int new_smd_male = rs.getInt("new_smd_male");
				int new_smd_female = rs.getInt("new_smd_female");
				int old_cmd_male = rs.getInt("old_cmd_male");
				int old_cmd_female = rs.getInt("old_cmd_female");
				int new_cmd_male = rs.getInt("new_cmd_male");
				int new_cmd_female = rs.getInt("new_cmd_female");
				int old_alcohal_male = rs.getInt("old_alcohal_male");
				int old_alcohal_female = rs.getInt("old_alcohal_female");
				int new_alcohal_male = rs.getInt("new_alcohal_male");
				int new_alcohal_female = rs.getInt("new_alcohal_female");
				int old_male_reffered_to_highercenters = rs.getInt("old_male_reffered_to_highercenters");
				int old_female_reffered_to_highercenters = rs.getInt("old_female_reffered_to_highercenters");
				int new_male_reffered_to_highercenters = rs.getInt("new_male_reffered_to_highercenters");
				int new_female_reffered_to_highercenters = rs.getInt("new_female_reffered_to_highercenters");
				String remarks = rs.getString("remarks");
				int old_psychiatricdisorders_male = rs.getInt("old_psychiatricdisorders_male");
				int old_psychiatricdisorders_female = rs.getInt("old_psychiatricdisorders_female");
				int new_psychiatricdisorders_male = rs.getInt("new_psychiatricdisorders_male");
				int new_psychiatricdisorders_female = rs.getInt("new_psychiatricdisorders_female");

				int old_male_suicidecases = rs.getInt("old_male_suicidecases");
				int old_female_suicidecases = rs.getInt("old_female_suicidecases");
				int new_male_suicidecases = rs.getInt("new_male_suicidecases");
				int new_female_suicidecases = rs.getInt("new_female_suicidecases");

				report = new Report(hospital_id, reportTimeStamp, old_smd_male, old_smd_female, new_smd_male,
						new_smd_female, old_cmd_male, old_cmd_female, new_cmd_male, new_cmd_female, old_alcohal_male,
						old_alcohal_female, new_alcohal_male, new_alcohal_female, old_male_reffered_to_highercenters,
						old_female_reffered_to_highercenters, new_male_reffered_to_highercenters,
						new_female_reffered_to_highercenters, old_male_suicidecases, old_female_suicidecases,
						new_male_suicidecases, new_female_suicidecases, old_psychiatricdisorders_male,
						old_psychiatricdisorders_female, new_psychiatricdisorders_male, new_psychiatricdisorders_female,
						remarks, hospitalType);
				System.out.println(report);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;

	}

	public Report setReportInfo(Report report) {
		try {
			String sql = "INSERT INTO `report`(`hospital_id_fk`, `reportTimeStamp`, `old_smd_male`, `old_smd_female`, `new_smd_male`, `new_smd_female`, `old_cmd_male`, `old_cmd_female`, `new_cmd_male`, `new_cmd_female`, `old_alcohal_male`, `old_alcohal_female`, `new_alcohal_male`, `new_alcohal_female`, `old_male_reffered_to_highercenters`, `old_female_reffered_to_highercenters`, `new_male_reffered_to_highercenters`, `new_female_reffered_to_highercenters`, `remarks`, `old_psychiatricdisorders_male`, `old_psychiatricdisorders_female`, `new_psychiatricdisorders_male`, `new_psychiatricdisorders_female`, `hospitalType`,`old_male_suicidecases`, `old_female_suicidecases`, `new_male_suicidecases`, `new_female_suicidecases`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstatement;

			pstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstatement.setInt(1, report.getHospital_id());

			java.util.Date date = new java.util.Date();
			Timestamp timstamp = new Timestamp(date.getTime());
			pstatement.setTimestamp(2, timstamp);

			pstatement.setInt(3, report.getOld_smd_male());
			pstatement.setInt(4, report.getOld_smd_female());
			pstatement.setInt(5, report.getNew_smd_male());
			pstatement.setInt(6, report.getNew_smd_female());

			pstatement.setInt(7, report.getOld_cmd_male());
			pstatement.setInt(8, report.getOld_cmd_female());
			pstatement.setInt(9, report.getNew_cmd_male());
			pstatement.setInt(10, report.getNew_cmd_female());

			pstatement.setInt(11, report.getOld_alcohal_male());
			pstatement.setInt(12, report.getOld_alcohal_female());
			pstatement.setInt(13, report.getNew_alcohal_male());
			pstatement.setInt(14, report.getNew_alcohal_female());

			pstatement.setInt(15, report.getOld_male_reffered_to_highercenters());
			pstatement.setInt(16, report.getOld_female_reffered_to_highercenters());
			pstatement.setInt(17, report.getNew_male_reffered_to_highercenters());
			pstatement.setInt(18, report.getNew_female_reffered_to_highercenters());

			pstatement.setString(19, report.getRemarks());

			pstatement.setInt(20, report.getOld_psychiatricdisorders_male());
			pstatement.setInt(21, report.getOld_psychiatricdisorders_female());
			pstatement.setInt(22, report.getNew_psychiatricdisorders_male());
			pstatement.setInt(23, report.getNew_psychiatricdisorders_female());

			pstatement.setString(24, report.getHospitalType());

			pstatement.setInt(25, report.getOld_male_suicidecases());
			pstatement.setInt(26, report.getOld_female_suicidecases());
			pstatement.setInt(27, report.getNew_male_suicidecases());
			pstatement.setInt(28, report.getNew_female_suicidecases());

			int result = pstatement.executeUpdate();

			System.out.println(report);

			if (result >= 1) {
				report.setReportTimeStamp(timstamp);
				return report;
			} else {
				report = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("set report inffo :" + report);
		return report;

	}

	public List<Hospital> getReportStatus(int hospital_id, String hospitalType) {

		List<Hospital> listOfhospital = hospitalService.getAllChildHospital(hospital_id, hospitalType);
		List<Hospital> list = getHospitalsWhoSubmittedReport(hospitalType);

		System.out.println(listOfhospital.size());

		// listOfhospital.removeAll(list);
		for (int i = 0; i < listOfhospital.size(); i++) {

			if (list.contains(listOfhospital.get(i))) {
				
				int id = listOfhospital.get(i).getHospital_id();
				String type = listOfhospital.get(i).getHospitalType();
				listOfhospital.get(i).setReportStatus(1);
				listOfhospital.get(i).setDoctor(doctorService.getDoctorFullName(id, type));
				listOfhospital.get(i).setHospital_report(
						getReportInfo(listOfhospital.get(i).getHospital_id(), listOfhospital.get(i).getHospitalType()));

			} else {
				listOfhospital.get(i).setReportStatus(0);
			}

		}
		return listOfhospital;

	}

	public List<Hospital> getHospitalsWhoSubmittedReport(String hospitalType) {

		List<Hospital> listOfHospital = null;

		StringBuilder sb = new StringBuilder();
		if (hospitalType.equalsIgnoreCase("DHC")) {
			sb.append("(hospitalType = 'DHC' or hospitalType ='THC' or hospitalType ='CHC' or hospitalType ='PHC')");
		} else if (hospitalType.equalsIgnoreCase("THC")) {
			sb.append("(hospitalType ='THC' or hospitalType ='CHC' or hospitalType ='PHC')");
		} else if (hospitalType.equalsIgnoreCase("CHC")) {
			sb.append("(hospitalType ='CHC' or hospitalType ='PHC')");
		} else {
			sb.append("(hospitalType ='PHC')");
		}

		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		String sql = "SELECT hospital_id_fk,hospitalType FROM report WHERE (reportTimeStamp LIKE ?) AND "
				+ sb.toString();
		PreparedStatement pstatement;

		try {
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, sdf.format(myDate) + "%");
			ResultSet rs = pstatement.executeQuery();
			listOfHospital = new ArrayList<>();

			System.out.println(sql + "   " + sdf.format(myDate) + "%");

			while (rs.next()) {
				int hospital_id = rs.getInt(1);
				String type = rs.getString(2);
				Hospital hospital = hospitalService.getHospitalInfo(hospital_id, type);
				
				listOfHospital.add(hospital);
			}
			System.out.println(listOfHospital.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfHospital;
	}

}
