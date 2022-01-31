package tr.com.akarcesme.types;

public class AccountsContract {
	private int id;
	private int yetkiId;
	private int personelId;
	private String sifre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYetkiId() {
		return yetkiId;
	}

	public void setYetkiId(int yetkiId) {
		this.yetkiId = yetkiId;
	}

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	@Override
	public String toString() {
		return id + " " + personelId + " " + sifre + " " + yetkiId;
	}

}
