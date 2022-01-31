package tr.com.akarcesme.dal;

import tr.com.akarcesme.core.ObjectHelper;

import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.KategoriContract;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.KategoriContract;

public class KategoriDal extends ObjectHelper implements DALInterfaces<KategoriContract> {

	@Override
	public void Insert(KategoriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Kategori (Adi, ParentId) VALUES ('" + entity.getAdi() + "',"
					+ entity.getParentId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetAll() {
		List<KategoriContract> dataContract = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
			while (resultSet.next()) {
				contract = new KategoriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setParentId(resultSet.getInt("ParentId"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

	@Override
	public KategoriContract Delete(KategoriContract entity) {
		return null;
	}

	@Override
	public void update(KategoriContract entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE kategori SET Adi ='" + entity.getAdi() + "', ParentId ="
					+ entity.getParentId() + " WHERE Id=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetById(int id) {
		return null;
	}

	public List<KategoriContract> GetSearchKategori(String kategoriAdi) {
		List<KategoriContract> dataContract = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT * FROM kategori WHERE Adi LIKE '" + "%" + kategoriAdi + "%" + "'");
			while (rs.next()) {
				KategoriContract contract = new KategoriContract();
				contract.setAdi(rs.getString("Adi"));
				contract.setParentId(rs.getInt("ParentId"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}
}
