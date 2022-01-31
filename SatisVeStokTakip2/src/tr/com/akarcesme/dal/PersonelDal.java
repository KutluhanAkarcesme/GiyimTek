package tr.com.akarcesme.dal;

import tr.com.akarcesme.types.KategoriContract;
import tr.com.akarcesme.types.MusteriContract;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.core.ObjectHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;

public class PersonelDal extends ObjectHelper implements DALInterfaces<PersonelContract> {

	@Override
	public void Insert(PersonelContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Personel (AdiSoyadi, Email)" + "VALUES ('" + entity.getAdiSoyadi()
					+ "','" + entity.getEmail() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<PersonelContract> GetAll() {
		List<PersonelContract> dataContract = new ArrayList<PersonelContract>();
		Connection connection = getConnection();
		PersonelContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Personel");
			while (resultSet.next()) {
				contract = new PersonelContract();
				contract.setId(resultSet.getInt("ýd"));
				contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
				contract.setEmail(resultSet.getString("Email"));

				dataContract.add(contract); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}
	@Override
	public PersonelContract Delete(PersonelContract entity) {
		return null;
	}

	@Override
	public void update(PersonelContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE personel SET AdiSoyadi ='" + entity.getAdiSoyadi() + "', Email ='"
					+ entity.getEmail() + "' WHERE ýd=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<PersonelContract> GetById(int id) {
		return null;
	}

	public List<PersonelContract> GetSearchPersonel(String adiSoyadi) {
		List<PersonelContract> dataContract = new ArrayList<PersonelContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT * FROM personel WHERE AdiSoyadi LIKE '" + "%" + adiSoyadi + "%" + "'");
			while (rs.next()) {
				PersonelContract contract = new PersonelContract();
				contract.setAdiSoyadi(rs.getString("AdiSoyadi"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

}
