package punto1.main;

import org.tempuri.CalculatorSoapProxy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConsumirPunto1 {

	private CalculatorSoapProxy instance;
	
	private int add(Double numero1, Double numero2) throws RemoteException, SQLException{
		return connectDB("ADD",instance.add(numero1.intValue(),numero2.intValue()));	
	}
	
	private int substract(Double numero1, Double numero2) throws RemoteException,SQLException {
		// TODO Auto-generated method stub
		return connectDB("SUBSTRACT",instance.subtract(numero1.intValue(),numero2.intValue()));	
	}
	private int multiply(Double numero1, Double numero2) throws RemoteException,SQLException {
		// TODO Auto-generated method stub
		return connectDB("MULTIPLY",instance.multiply(numero1.intValue(),numero2.intValue()));	
	}
	private int divide(Double numero1, Double numero2) throws RemoteException,SQLException {
		// TODO Auto-generated method stub
		return connectDB("DIVIDE",instance.divide(numero1.intValue(),numero2.intValue() != 0 ? numero2.intValue() : 1));	
	}
	
	public ConsumirPunto1(){
		if( instance == null ) {
			instance = new CalculatorSoapProxy();
		}
	}
	
	private int connectDB(String operacion,int result) throws SQLException {
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
			ConsumirPunto1 agregar = new ConsumirPunto1();
			
			Double numero1 = null;
			Double numero2 = null;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Por favor ingrese el primer numero");
			System.out.println("Por favor ingrese el segundo numero");
			boolean numberComplete = true;
			do{
				try {
					numero1 = Double.valueOf(br.readLine()); 
					numero2 = Double.valueOf(br.readLine());
					numberComplete = false;
				}catch(Exception ex){
					System.out.println("Ingrese numeros validos");
				}
			}while(numberComplete);
			System.out.println("Resultado Suma:" +agregar.add(numero1,numero2));
			System.out.println("Resultado Resta:" +agregar.substract(numero1,numero2));
			System.out.println("Resultado Multiplicacion:" +agregar.multiply(numero1,numero2));
			System.out.println("Resultado Division:" +agregar.divide(numero1,numero2));
			
		}catch(Exception e) {
			System.out.println("Se encontro un error en la ejecucion del programa:" + e.getMessage());
		}
	}

	

}
