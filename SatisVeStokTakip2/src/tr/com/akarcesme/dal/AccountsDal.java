package tr.com.akarcesme.dal;


import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.AccountsContract;
import tr.com.akarcesme.types.KategoriContract;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;

public class AccountsDal extends ObjectHelper implements DALInterfaces<AccountsContract> {

	@Override
	public void Insert(AccountsContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Accounts (PersonelId, YetkiId,Sifre) VALUES (" + entity.getPersonelId()
					+ "," + entity.getYetkiId() + ",'" + entity.getSifre() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public AccountsContract GetPersonelIdVeSifre(int personelId, String sifre) {

		AccountsContract contract = new AccountsContract();
		List<AccountsContract> listele = new ArrayList<AccountsContract>();
		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery(
					"SELECT * FROM accounts WHERE PersonelId=" + personelId + " AND Sifre= '" + sifre.trim() + "'");
			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contract;
	}

	public AccountsContract GetYetkiId(int personelId) {

		AccountsContract contract = new AccountsContract();
		List<AccountsContract> listele = new ArrayList<AccountsContract>();
		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts WHERE PersonelId=" + personelId + "");
			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
			}
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contract;
	}
	
	public List<AccountsContract> GetPersonelId() {

		List<AccountsContract> listele = new ArrayList<AccountsContract>();
		AccountsContract contract ;
		Connection baglanti = getConnection();

		try {
			Statement sorgu = baglanti.createStatement();
			ResultSet rs = sorgu.executeQuery("SELECT PersonelId FROM accounts ");
			while (rs.next()) {
				contract = new AccountsContract();
				contract.setPersonelId(rs.getInt("PersonelId"));
				
				listele.add(contract);
			}
			sorgu.close();
			baglanti.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return  listele;
	}

	@Override
	public List<AccountsContract> GetAll() {

		return null;
	}

	@Override
	public AccountsContract Delete(AccountsContract entity) {
		return null;
	}
	
	@Override
	public void update(AccountsContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE accounts SET YetkiId =" + entity.getYetkiId() + ", Sifre= " + entity.getSifre() + " WHERE PersonelId ="
					+ entity.getPersonelId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<AccountsContract> GetById(int id) {
		return null;
	}
	 

}
