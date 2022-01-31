package tr.com.akarcesme.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.akarcesme.complex.types.SatisContractComplex;
import tr.com.akarcesme.core.ObjectHelper;
import tr.com.akarcesme.interfaces.DALInterfaces;
import tr.com.akarcesme.types.IllerContract;
import tr.com.akarcesme.types.KategoriContract;

public class illerDal extends ObjectHelper implements DALInterfaces<IllerContract> {

	@Override
	public void Insert(IllerContract entity) {

	}

	@Override
	public List<IllerContract> GetAll() {
		List<IllerContract> dataContract = new ArrayList<IllerContract>();
		Connection connection = getConnection();
		IllerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM iller");
			while (resultSet.next()) {
				contract = new IllerContract();
				contract.setIlNo(resultSet.getInt("ilNo"));
				contract.setIsim(resultSet.getString("isim"));

				dataContract.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContract;
	}

	@Override
	public IllerContract Delete(IllerContract entity) {
		return null;
	}

	@Override
	public void update(IllerContract entity) {

	}

	@Override
	public List<IllerContract> GetById(int id) {
		return null;
	}

}
