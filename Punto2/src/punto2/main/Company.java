package punto2.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Company {
	
	
	public int get_id() {
		return _id;
	}

	private int _id ;
	private String name;
	private String catchPhrase;
	private String bs;
	public String getName() {
		return name;
	}
	public Company() {
		super();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatchPhrase() {
		return catchPhrase;
	}
	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}
	public String getBs() {
		return bs;
	}
	public void setBs(String bs) {
		this.bs = bs;
	}
	
	public int insertDB() throws SQLException {
		
		
		Connection conexion =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433/arquitecturajava", "PruebaTecnica", "PruebaTecnica");
		String query = "INSERT INTO COMPANY(NAME,CATCH,BS) VALUES(?,?,?)";
        PreparedStatement pstm = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, this.name);
        pstm.setString(2, this.catchPhrase);
        pstm.setString(3, this.bs);
        // Execute!
        pstm.execute();
 
        ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()) {
            // Value of ID (Index 1 by table design).
            this._id = rs.getInt(1);
        }
        return _id;
	}
}
