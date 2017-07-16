package org.iiitb.hospital.nimhans.modals;

import java.sql.Timestamp;

public class Report {
	private int hospital_id;
	private Timestamp reportTimeStamp;

	private int old_smd_male;
	private int old_smd_female;
	private int new_smd_male;
	private int new_smd_female;

	private int old_cmd_male;
	private int old_cmd_female;
	private int new_cmd_male;
	private int new_cmd_female;

	private int old_alcohal_male;
	private int old_alcohal_female;
	private int new_alcohal_male;
	private int new_alcohal_female;

	private int old_male_reffered_to_highercenters;
	private int old_female_reffered_to_highercenters;
	private int new_male_reffered_to_highercenters;
	private int new_female_reffered_to_highercenters;

	private int old_male_suicidecases;
	private int old_female_suicidecases;
	private int new_male_suicidecases;
	private int new_female_suicidecases;

	private int old_psychiatricdisorders_male;
	private int old_psychiatricdisorders_female;
	private int new_psychiatricdisorders_male;
	private int new_psychiatricdisorders_female;

	private String remarks;
	private String hospitalType;

	public Report() {
		super();
	}

	public Report(int hospital_id, Timestamp reportTimeStamp, int old_smd_male, int old_smd_female, int new_smd_male,
			int new_smd_female, int old_cmd_male, int old_cmd_female, int new_cmd_male, int new_cmd_female,
			int old_alcohal_male, int old_alcohal_female, int new_alcohal_male, int new_alcohal_female,
			int old_male_reffered_to_highercenters, int old_female_reffered_to_highercenters,
			int new_male_reffered_to_highercenters, int new_female_reffered_to_highercenters, int old_male_suicidecases,
			int old_female_suicidecases, int new_male_suicidecases, int new_female_suicidecases,
			int old_psychiatricdisorders_male, int old_psychiatricdisorders_female, int new_psychiatricdisorders_male,
			int new_psychiatricdisorders_female, String remarks, String hospitalType) {
		super();
		this.hospital_id = hospital_id;
		this.reportTimeStamp = reportTimeStamp;
		this.old_smd_male = old_smd_male;
		this.old_smd_female = old_smd_female;
		this.new_smd_male = new_smd_male;
		this.new_smd_female = new_smd_female;
		this.old_cmd_male = old_cmd_male;
		this.old_cmd_female = old_cmd_female;
		this.new_cmd_male = new_cmd_male;
		this.new_cmd_female = new_cmd_female;
		this.old_alcohal_male = old_alcohal_male;
		this.old_alcohal_female = old_alcohal_female;
		this.new_alcohal_male = new_alcohal_male;
		this.new_alcohal_female = new_alcohal_female;
		this.old_male_reffered_to_highercenters = old_male_reffered_to_highercenters;
		this.old_female_reffered_to_highercenters = old_female_reffered_to_highercenters;
		this.new_male_reffered_to_highercenters = new_male_reffered_to_highercenters;
		this.new_female_reffered_to_highercenters = new_female_reffered_to_highercenters;
		this.old_male_suicidecases = old_male_suicidecases;
		this.old_female_suicidecases = old_female_suicidecases;
		this.new_male_suicidecases = new_male_suicidecases;
		this.new_female_suicidecases = new_female_suicidecases;
		this.old_psychiatricdisorders_male = old_psychiatricdisorders_male;
		this.old_psychiatricdisorders_female = old_psychiatricdisorders_female;
		this.new_psychiatricdisorders_male = new_psychiatricdisorders_male;
		this.new_psychiatricdisorders_female = new_psychiatricdisorders_female;
		this.remarks = remarks;
		this.hospitalType = hospitalType;
	}

	public int getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}

	public Timestamp getReportTimeStamp() {
		return reportTimeStamp;
	}

	public void setReportTimeStamp(Timestamp reportTimeStamp) {
		this.reportTimeStamp = reportTimeStamp;
	}

	public int getOld_smd_male() {
		return old_smd_male;
	}

	public void setOld_smd_male(int old_smd_male) {
		this.old_smd_male = old_smd_male;
	}

	public int getOld_smd_female() {
		return old_smd_female;
	}

	public void setOld_smd_female(int old_smd_female) {
		this.old_smd_female = old_smd_female;
	}

	public int getNew_smd_male() {
		return new_smd_male;
	}

	public void setNew_smd_male(int new_smd_male) {
		this.new_smd_male = new_smd_male;
	}

	public int getNew_smd_female() {
		return new_smd_female;
	}

	public void setNew_smd_female(int new_smd_female) {
		this.new_smd_female = new_smd_female;
	}

	public int getOld_cmd_male() {
		return old_cmd_male;
	}

	public void setOld_cmd_male(int old_cmd_male) {
		this.old_cmd_male = old_cmd_male;
	}

	public int getOld_cmd_female() {
		return old_cmd_female;
	}

	public void setOld_cmd_female(int old_cmd_female) {
		this.old_cmd_female = old_cmd_female;
	}

	public int getNew_cmd_male() {
		return new_cmd_male;
	}

	public void setNew_cmd_male(int new_cmd_male) {
		this.new_cmd_male = new_cmd_male;
	}

	public int getNew_cmd_female() {
		return new_cmd_female;
	}

	public void setNew_cmd_female(int new_cmd_female) {
		this.new_cmd_female = new_cmd_female;
	}

	public int getOld_alcohal_male() {
		return old_alcohal_male;
	}

	public void setOld_alcohal_male(int old_alcohal_male) {
		this.old_alcohal_male = old_alcohal_male;
	}

	public int getOld_alcohal_female() {
		return old_alcohal_female;
	}

	public void setOld_alcohal_female(int old_alcohal_female) {
		this.old_alcohal_female = old_alcohal_female;
	}

	public int getNew_alcohal_male() {
		return new_alcohal_male;
	}

	public void setNew_alcohal_male(int new_alcohal_male) {
		this.new_alcohal_male = new_alcohal_male;
	}

	public int getNew_alcohal_female() {
		return new_alcohal_female;
	}

	public void setNew_alcohal_female(int new_alcohal_female) {
		this.new_alcohal_female = new_alcohal_female;
	}

	public int getOld_male_reffered_to_highercenters() {
		return old_male_reffered_to_highercenters;
	}

	public void setOld_male_reffered_to_highercenters(int old_male_reffered_to_highercenters) {
		this.old_male_reffered_to_highercenters = old_male_reffered_to_highercenters;
	}

	public int getOld_female_reffered_to_highercenters() {
		return old_female_reffered_to_highercenters;
	}

	public void setOld_female_reffered_to_highercenters(int old_female_reffered_to_highercenters) {
		this.old_female_reffered_to_highercenters = old_female_reffered_to_highercenters;
	}

	public int getNew_male_reffered_to_highercenters() {
		return new_male_reffered_to_highercenters;
	}

	public void setNew_male_reffered_to_highercenters(int new_male_reffered_to_highercenters) {
		this.new_male_reffered_to_highercenters = new_male_reffered_to_highercenters;
	}

	public int getNew_female_reffered_to_highercenters() {
		return new_female_reffered_to_highercenters;
	}

	public void setNew_female_reffered_to_highercenters(int new_female_reffered_to_highercenters) {
		this.new_female_reffered_to_highercenters = new_female_reffered_to_highercenters;
	}

	public int getOld_male_suicidecases() {
		return old_male_suicidecases;
	}

	public void setOld_male_suicidecases(int old_male_suicidecases) {
		this.old_male_suicidecases = old_male_suicidecases;
	}

	public int getOld_female_suicidecases() {
		return old_female_suicidecases;
	}

	public void setOld_female_suicidecases(int old_female_suicidecases) {
		this.old_female_suicidecases = old_female_suicidecases;
	}

	public int getNew_male_suicidecases() {
		return new_male_suicidecases;
	}

	public void setNew_male_suicidecases(int new_male_suicidecases) {
		this.new_male_suicidecases = new_male_suicidecases;
	}

	public int getNew_female_suicidecases() {
		return new_female_suicidecases;
	}

	public void setNew_female_suicidecases(int new_female_suicidecases) {
		this.new_female_suicidecases = new_female_suicidecases;
	}

	public int getOld_psychiatricdisorders_male() {
		return old_psychiatricdisorders_male;
	}

	public void setOld_psychiatricdisorders_male(int old_psychiatricdisorders_male) {
		this.old_psychiatricdisorders_male = old_psychiatricdisorders_male;
	}

	public int getOld_psychiatricdisorders_female() {
		return old_psychiatricdisorders_female;
	}

	public void setOld_psychiatricdisorders_female(int old_psychiatricdisorders_female) {
		this.old_psychiatricdisorders_female = old_psychiatricdisorders_female;
	}

	public int getNew_psychiatricdisorders_male() {
		return new_psychiatricdisorders_male;
	}

	public void setNew_psychiatricdisorders_male(int new_psychiatricdisorders_male) {
		this.new_psychiatricdisorders_male = new_psychiatricdisorders_male;
	}

	public int getNew_psychiatricdisorders_female() {
		return new_psychiatricdisorders_female;
	}

	public void setNew_psychiatricdisorders_female(int new_psychiatricdisorders_female) {
		this.new_psychiatricdisorders_female = new_psychiatricdisorders_female;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	@Override
	public String toString() {
		return "Report [hospital_id=" + hospital_id + ", reportTimeStamp=" + reportTimeStamp + ", old_smd_male="
				+ old_smd_male + ", old_smd_female=" + old_smd_female + ", new_smd_male=" + new_smd_male
				+ ", new_smd_female=" + new_smd_female + ", old_cmd_male=" + old_cmd_male + ", old_cmd_female="
				+ old_cmd_female + ", new_cmd_male=" + new_cmd_male + ", new_cmd_female=" + new_cmd_female
				+ ", old_alcohal_male=" + old_alcohal_male + ", old_alcohal_female=" + old_alcohal_female
				+ ", new_alcohal_male=" + new_alcohal_male + ", new_alcohal_female=" + new_alcohal_female
				+ ", old_male_reffered_to_highercenters=" + old_male_reffered_to_highercenters
				+ ", old_female_reffered_to_highercenters=" + old_female_reffered_to_highercenters
				+ ", new_male_reffered_to_highercenters=" + new_male_reffered_to_highercenters
				+ ", new_female_reffered_to_highercenters=" + new_female_reffered_to_highercenters
				+ ", old_male_suicidecases=" + old_male_suicidecases + ", old_female_suicidecases="
				+ old_female_suicidecases + ", new_male_suicidecases=" + new_male_suicidecases
				+ ", new_female_suicidecases=" + new_female_suicidecases + ", old_psychiatricdisorders_male="
				+ old_psychiatricdisorders_male + ", old_psychiatricdisorders_female=" + old_psychiatricdisorders_female
				+ ", new_psychiatricdisorders_male=" + new_psychiatricdisorders_male
				+ ", new_psychiatricdisorders_female=" + new_psychiatricdisorders_female + ", remarks=" + remarks
				+ ", hospitalType=" + hospitalType + "]";
	}
}
