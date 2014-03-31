package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javabeans.Mensaje;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Operaciones;

@WebServlet("/controlador")
public class Controlador extends HttpServlet{
	
	public void service(HttpServletRequest request,HttpServletResponse response)
					throws ServletException, IOException{
		String op = request.getParameter("operacion");
		
		if(op.equals("envio"))
			response.sendRedirect("envio.jsp");
		
		if(op.equals("grabar")){
			Mensaje men = (Mensaje)request.getAttribute("mensa");
			Operaciones oper = new Operaciones();
			oper.grabaMensaje(men);
			response.sendRedirect("inicio.html");
		}
		
		if(op.equals("muestra"))
			response.sendRedirect("mostrar.html");
		
		if(op.equals("ver")){
			Operaciones oper = new Operaciones();			
			ArrayList mensajes = oper.getMessages(request.getParameter("nombre"));
			request.setAttribute("mensajes", mensajes);
			RequestDispatcher rd = request.getRequestDispatcher("/ver.jsp");
			rd.forward(request, response);					
		}
	}
	
}
