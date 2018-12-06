package punto2.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestClient {

	private static int connectDB(String operacion,int result) throws SQLException {
		Connection conexion =  DriverManager.getConnection("jdbc:sqlserver://localhost:1433/arquitecturajava", "PruebaTecnica", "PruebaTecnica");
	    String query = "Insert into OPERACION(operacion,resultado) values(?,?)";
	    PreparedStatement preparedStmt = conexion.prepareStatement(query);
	    preparedStmt.setString(1, operacion);
	    preparedStmt.setInt(2, result);
	    preparedStmt.executeUpdate();
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<Registro>> registro =
			        restTemplate.exchange("https://jsonplaceholder.typicode.com/users",
			                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Registro>>() { });
			registro.getBody();
			for( Registro r : registro.getBody() ) {
				r.insertDB();
			}
        } catch (Exception e) {
            System.out.println("Fallo en la ejecucion de la aplicacion:- " + e);
        }
		
	}

}
