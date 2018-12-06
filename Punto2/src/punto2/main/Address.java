package punto2.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
	
	private int _id;
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private Geo geo;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public Address() {
		super();
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	public int insertDB() throws SQLException {
		
		
		this.geo.insertDB();
		Connection conexion =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433/arquitecturajava", "PruebaTecnica", "PruebaTecnica");
		String query = "INSERT INTO ADDRES(STREET,SUITE,CITY,ZIPCODE,GEO) VALUES(?,?,?)";
        PreparedStatement pstm = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, this.street);
        pstm.setString(2, this.suite);
        pstm.setString(3, this.city);
        pstm.setString(3, this.zipcode);
        pstm.setString(3, "" + this.geo.get_id());
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

	
}
