<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="domain.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test insert room</title>
</head>
<body>
	<table>
	<tr>
		<td><b>ID</b></td>
		<td><b>Capacity</b></td>
		<td><b>Room Area</b></td>
		<td><b>Room type</b></td>
	</tr>
	<%
		Room room = (Room)request.getAttribute("room");
	%>
		<tr>
			<td>
				${room.getRoomId()}
			</td>
			<td>
				${room.getCapacity()}
			</td>
			<td>
				${room.getRoomArea()}
			</td>
			<td>
				${room.getRoomType()}
			</td>
		</tr>
	</table>
</body>

</body>
</html>