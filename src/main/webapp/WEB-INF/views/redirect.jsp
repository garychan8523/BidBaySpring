<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="Gary Chan">
		
		<title>Bidbay</title>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="icon" href="favicon.ico">
	</head>

	<body>

		<header>
  <div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container d-flex justify-content-between">
      <div class="navbar-brand d-flex align-items-center">
        <i class="fa fa-money" style="font-size: 25px; margin-right: 10px; color: #ff9900;"></i>
        <strong><font color="#fefefe">Bidbay</font></strong>
      </div>
    </div>
  </div>
</header>

<main role="main">

  <div class="album py-5 bg-light" style="height: calc(100% - 56px);">
    <div class="container">

      <div class="row">
      
      <c:choose>
      	<c:when test="${msg != null}">
        	${msg}, 
    	</c:when>
	  </c:choose>

		Redirecting to 
		<c:if test="${fromuri != null}">
			<a href="/Bidbay${fromuri}" style="margin: 0 5px 0 5px;">previous page</a>
		</c:if>
		<c:if test="${fromuri == null}">
			<a href="${pageContext.request.contextPath}" style="margin: 0 5px 0 5px;">homepage</a>
		</c:if>
		 ...
		 
	  </div>
	
	</div>
  </div>
  
</main>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	</body>
	
</html>

