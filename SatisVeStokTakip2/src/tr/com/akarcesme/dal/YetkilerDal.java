package tr.com.akarcesme.dal;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.KategoriContract;
import tr.com.akarcesme.types.PersonelContract;
import tr.com.akarcesme.types.YetkilerContract;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;

public class YetkilerDal extends ObjectHelper implements DALInterfaces<YetkilerContract> {

	@Override
	public void Insert(YetkilerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Yetkiler (Adi) VALUES ('" + entity.getAdi() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<YetkilerContract> GetAll() {
		List<YetkilerContract> dataContract = new ArrayList<YetkilerContract>();
		Connection connection = getConnection();
		YetkilerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Yetkiler");
			while (resultSet.next()) {
				contract = new YetkilerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

	@Override
	public YetkilerContract Delete(YetkilerContract entity) {
		return null;
	}

	@Override
	public void update(YetkilerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE yetkiler SET Adi ='" + entity.getAdi() + "' WHERE Id ="
					+ entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<YetkilerContract> GetById(int id) {
		return null;
	}

	public List<YetkilerContract> GetSearchYetki(String yetkiAdi) {
		List<YetkilerContract> dataContract = new ArrayList<YetkilerContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT * FROM yetkiler WHERE Adi LIKE '" + "%" + yetkiAdi + "%" + "'");
			while (rs.next()) {
				YetkilerContract contract = new YetkilerContract();
				contract.setAdi(rs.getString("Adi"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

}
