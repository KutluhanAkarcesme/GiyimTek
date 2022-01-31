package tr.com.akarcesme.dal;

import tr.com.akarcesme.core.ObjectHelper;

import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.KategoriContract;
import tr.com.akarcesme.types.MusteriContract;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;

public class MusteriDal extends ObjectHelper implements DALInterfaces<MusteriContract> {

	@Override
	public void Insert(MusteriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO musteri (MusteriAdi,Telefon,Adres,SehirId) VALUES ('" + entity.getMusteriAdi() + "','"
							+ entity.getTelefon() + "','" + entity.getAdres() + "'," + entity.getSehirId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<MusteriContract> GetAll() {
		List<MusteriContract> dataContract = new ArrayList<MusteriContract>();
		Connection connection = getConnection();
		MusteriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM musteri");
			while (resultSet.next()) {
				contract = new MusteriContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setMusteriAdi(resultSet.getString("MusteriAdi"));
				contract.setAdres(resultSet.getString("Adres"));
				contract.setSehirId(resultSet.getInt("SehirId"));
				contract.setTelefon(resultSet.getString("Telefon"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

	public void DeleteMusteri(MusteriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM musteri WHERE Id=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MusteriContract Delete(MusteriContract entity) {

		return null;
	}

	public void updateMusteriAdi(MusteriContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE musteri SET MusteriAdi=" + "'" + entity.getMusteriAdi() + "'" + "WHERE Id="
					+ entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(MusteriContract entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE musteri SET MusteriAdi='" + entity.getMusteriAdi() + "', Telefon='"
					+ entity.getTelefon() + "', Adres='" + entity.getAdres() + "', SehirId=" + entity.getSehirId()
					+ " WHERE Id=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MusteriContract> GetById(int id) {
		return null;
	}

	public List<MusteriContract> GetSearchMusteri(String musteriAdi) {
		List<MusteriContract> dataContract = new ArrayList<MusteriContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT * FROM musteri WHERE MusteriAdi LIKE '" + "%" + musteriAdi + "%" + "'");
			while (rs.next()) {
				MusteriContract contract = new MusteriContract();
				contract.setMusteriAdi(rs.getString("MusteriAdi"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

}
