<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page errorPage="WEB-INF/jsp/showErrorMessage.jsp"%>
<html>
<head>
<title>JAW Motors</title>
</head>
<body>

	<h1>JAW Motors</h1>

	<p>
		<%-- Use here JSP include directive to include "showImage.jsp" --%>
	</p>

	<a
		href="controller?action=viewCarList<%-- Set the value for 'action' parameter to 'viewCarList' --%>">View
		Inventory</a>
</body>
<!-- hyperlink text before ? should be exactly equal to the controller id without the initial / -->
</html>