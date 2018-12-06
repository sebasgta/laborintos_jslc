package punto2.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Registro {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	private String id;
	private String name;
	private String username;
	private String email;
	private String phone;
	private String website;
	private Address address;
	private Company company;
	
	public Registro() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void insertDB() throws SQLException {
		
		
		this.address.insertDB();
		this.company.insertDB();
		Connection conexion =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433/arquitecturajava", "PruebaTecnica", "PruebaTecnica");
		String query = "INSERT INTO REGISTRO(ID,NAME,USERNAME,EMAIL,ADDRESS,PHONE,WEBSITE,COMPANY),VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = conexion.prepareStatement(query);
        pstm.setString(1, this.id);
        pstm.setString(2, this.name);
        pstm.setString(3, this.username);
        pstm.setString(3, this.email);
        pstm.setString(3, "" + this.address.get_id());
        pstm.setString(3, "" + this.phone);
        pstm.setString(3, "" + this.website);
        pstm.setString(3, "" + this.company.get_id());
        // Execute!
        pstm.execute();
 
	}
}
