package punto2.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Geo {

	private int _id;
	private String lat;
	private String lng;
	public String getLat() {
		return lat;
	}
	public Geo() {
		super();
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	public int insertDB() throws SQLException {
		
		
		Connection conexion =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433/arquitecturajava", "PruebaTecnica", "PruebaTecnica");
		String query = "INSERT INTO GEO(LAT,LNT) VALUES(?,?)";
        PreparedStatement pstm = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, this.lat);
        pstm.setString(2, this.lng);
        // Execute!
        pstm.execute();
 
        ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()) {
            // Value of ID (Index 1 by table design).
            this._id = rs.getInt(1);
        }
        return _id;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
}
