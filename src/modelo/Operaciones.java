package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javabeans.Mensaje;

public class Operaciones {
	
	public static Connection getConnection(){
		Connection cn = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			cn = DriverManager.getConnection("jdbc:mysql://localhost/carlos","root","root");			
		} catch (Exception e) {
			e.printStackTrace();							
		}
		return cn;
	}
	
	//creamos la clase getMessages
	public static ArrayList<Mensaje> getMessages(String destino){
		
		Connection conexion = null;		
		ArrayList<Mensaje> mensaje = null; 
		Statement st;
		ResultSet rs;
		
		try {
			conexion = getConnection();
			st = conexion.createStatement();
			
			String squery;
			squery = "select * from empleado where destinatario='"+destino+"'";
			rs = st.executeQuery(squery);
			
			mensaje = new ArrayList<Mensaje>();
			
			while(rs.next()){				
				Mensaje m = new Mensaje(rs.getString("remitente"),
						rs.getString("destinatario"),
						rs.getString("texto"));				
				mensaje.add(m);				
			}
			conexion.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}					
		return mensaje;					
	}
	
	public static void main(String[] args){
		System.out.println("Hello...");			
		
		System.out.println(getMessages("prueba"));		
	}
	
	//creamos la clase grabarMensaje
	public void grabaMensaje(Mensaje m){
		Connection cn;
		Statement st;
		ResultSet rs;
		try{
			cn=getConnection();
			st=cn.createStatement();
			String tsql;		
			tsql="Insert into empleado values('";
			tsql+=m.getDestino()+"','"+
				  m.getRemite()+"','"+
				  m.getTexto()+"')";
			st.execute(tsql);
			cn.close();
		}catch(Exception e){
			e.printStackTrace();}
		}		
}
