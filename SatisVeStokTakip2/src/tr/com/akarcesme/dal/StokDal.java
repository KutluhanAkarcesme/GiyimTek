package tr.com.akarcesme.dal;

import tr.com.akarcesme.complex.types.StokContractComplex;
import tr.com.akarcesme.complex.types.StokContractTotalComplex;
import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.StokContract;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;

public class StokDal extends ObjectHelper implements DALInterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {

		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(
					"INSERT INTO Stok (PersonelId, UrunId, Tarih, Adet) VALUES (" + entity.getPersonelId() + ","
							+ entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<StokContractComplex> GetAllStok() {
		List<StokContractComplex> dataContract = new ArrayList<StokContractComplex>();
		Connection connection = getConnection();
		StokContractComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stok.Id, AdiSoyadi, Adi, Adet, stok.Tarih FROM stok"
					+ " LEFT JOIN urunler ON stok.UrunId = urunler.Id"
					+ " LEFT JOIN personel ON stok.PersonelId = personel.ýd ORDER BY stok.Id DESC");
			while (resultSet.next()) {
				contract = new StokContractComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("urunler.Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setTarih(resultSet.getString("stok.Tarih"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;

	}

	public List<StokContractTotalComplex> GetTotalStok() {
		List<StokContractTotalComplex> dataContract = new ArrayList<StokContractTotalComplex>();
		Connection connection = getConnection();
		StokContractTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT SUM(Adet) as toplam,stok.Id,AdiSoyadi,Adi,Adet,stok.Tarih FROM stok "
							+ "LEFT JOIN urunler ON stok.UrunId = urunler.Id "
							+ "LEFT JOIN personel ON stok.PersonelId = personel.ýd GROUP BY UrunId");
			while (resultSet.next()) {
				contract = new StokContractTotalComplex();
				contract.setId(resultSet.getInt("Id"));
				contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
				contract.setUrunAdi(resultSet.getString("urunler.Adi"));
				contract.setAdet(resultSet.getInt("Adet"));
				contract.setTarih(resultSet.getString("stok.Tarih"));
				contract.setToplam(resultSet.getInt("toplam"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;

	}

	@Override
	public List<StokContract> GetAll() {

		return null;
	}

	@Override
	public StokContract Delete(StokContract entity) {
		return null;
	}

	@Override
	public void update(StokContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE stok SET UrunId=" + entity.getUrunId() + ", PersonelId="
					+ entity.getPersonelId() + ", Tarih='" + entity.getTarih() + "', Adet=" + entity.getAdet()
					+ " WHERE Id=" + entity.getId() + "");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<StokContract> GetById(int id) {
		return null;
	}

}
