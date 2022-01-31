package tr.com.akarcesme.types;

public class IllerContract {
	private int ilNo;
	private String isim;

	public int getIlNo() {
		return ilNo;
	}

	public void setIlNo(int ilNo) {
		this.ilNo = ilNo;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	public String toString() {
		return ilNo +" "+ isim;
	}

}
