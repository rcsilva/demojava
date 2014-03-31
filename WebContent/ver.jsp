<%@page import="javabeans.Mensaje"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Ver</title>
</head>
<body>
	<center>
		<%String nombre = request.getParameter("nombre");%>
		<h1>
			Mensajes para <%=nombre%>
		</h1>
		<table border=1>
			<tr>
				<th>Remitente</th>
				<th>Mensaje</th>			
			</tr>
			
		<%  boolean men=false;
			ArrayList mensajes = (ArrayList)request.getAttribute("mensajes");
			if(mensajes!=null)
			//si existen mensajes para ese destinatario
			//se generará una tabla con los mismos
			for(int i=0;i<mensajes.size();i++){
				Mensaje m=(Mensaje)mensajes.get(i);
			if((m.getDestino()).equalsIgnoreCase(nombre)){
			men=true;
		%>
			
			<tr>
				<td><%=m.getRemite()%></td>
				<td><%=m.getTexto()%></td>
			</tr>
			<%}
			}
			if(!men){%>
			<!--si no hay mensajes se envía al usuario
			a la página nomensajes.jsp-->
			<jsp:forward page="nomensajes.jsp"/>
			<%}%>
		</table>
		<br/><br/>
		<a href="inicio.html">Inicio</a>
	</center>
</body>
</html>