package tr.com.akarcesme.dal;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.types.KategoriContract;
import tr.com.akarcesme.types.UrunlerContract;
import tr.com.akarcesme.interfaces.DALInterfaces;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;

public class UrunlerDal extends ObjectHelper implements DALInterfaces<UrunlerContract> {

	@Override
	public void Insert(UrunlerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Urunler (Adi, KategoriId,Tarih,Fiyat)" + "VALUES ('" + entity.getAdi()
					+ "'," + entity.getKategoriId() + ",'" + entity.getTarih() + "'," + entity.getFiyat() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<UrunlerContract> GetAll() {
		List<UrunlerContract> dataContract = new ArrayList<UrunlerContract>();
		Connection connection = getConnection();
		UrunlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
			while (resultSet.next()) {
				contract = new UrunlerContract();
				contract.setId(resultSet.getInt("Id"));
				contract.setAdi(resultSet.getString("Adi"));
				contract.setKategoriId(resultSet.getInt("KategoriId"));
				contract.setTarih(resultSet.getString("Tarih"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	} 

	@Override
	public UrunlerContract Delete(UrunlerContract entity) {
		return null;
	}

	@Override
	public void update(UrunlerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE urunler SET Adi='" + entity.getAdi() + "', KategoriId='"
					+ entity.getKategoriId() + "', Fiyat=" + entity.getFiyat() + " WHERE Id=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<UrunlerContract> GetById(int id) {
		return null;
	}

	public List<UrunlerContract> GetSearchUrun(String urunAdi) {
		List<UrunlerContract> dataContract = new ArrayList<UrunlerContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM urunler WHERE Adi LIKE '" + "%" + urunAdi + "%" + "'");
			while (rs.next()) {
				UrunlerContract contract = new UrunlerContract();
				contract.setAdi(rs.getString("Adi"));
				contract.setKategoriId(rs.getInt("KategoriId"));
				contract.setTarih(rs.getString("Tarih"));
				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

}
