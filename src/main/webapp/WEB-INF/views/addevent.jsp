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
		<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/favicon.ico" rel="icon">
	</head>

	<body>
	
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">Login</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body col-md-6" style="margin: auto;">
        <form action="${pageContext.request.contextPath}/login" method="post">
          <div class="form-group">
            <input type="text" class="form-control" id="username" name="username" placeholder="username" required>
          </div>
          <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="password" required>
          </div>
          <input type="hidden" class="form-control" id="frompage" name="frompage" value="${requesturi}" />
      </div>
      <div class="modal-footer">
        <button type="reset" class="btn btn-outline-dark">Reset</button>
        <button type="submit" class="btn btn-warning"><strong>Login</strong></button>
      </div>
	  </form>
    </div>
  </div>
</div>

</div>

</div>
		<header>
  <div class="navbar navbar-dark bg-dark shadow-sm">
    <div class="container d-flex justify-content-between">
      <a href="home" class="navbar-brand d-flex align-items-center">
        <i class="fa fa-money" style="font-size: 25px; margin-right: 10px; color: #ff9900;"></i>
        <strong><font color="#fefefe">Bidbay</font></strong>
      </a>
	  <div><font color="#fff">Hello, <strong>
	  <% 
	  		if(session.getAttribute("username") != null) {
	  			out.println(session.getAttribute("username"));
	  		}else{
	  			out.println("Guest");
	  		}
	  %>
	  </strong></font></div>
    </div>
  </div>
</header>

<main role="main">

  <div class="album py-5 bg-light" style="margin-top: 40px;">
    <div class="container">
		<a href="${pageContext.request.contextPath}"><< Back</a>
      <div class="row">
        <div class="col-md-4 listing">
          <div class="card mb-4 shadow-sm listing-card">
            <svg class="card-img-top listing-img" style="background-color: #efefef;"xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><i class="fa fa-gift" style="position: absolute; font-size: 65px; color: #333; height: 100%; margin-top: 15%; margin-left: calc(17.5% - 25px);"></i></text></svg>
			
            <div class="card-body">
				<h3>Add a Bid Event</h3>
				<span class="metatitle" style="color: #999">*Required</span>
				
				<form action="addEvent" method="POST">
  <div class="form-group">
    <label for="productname">Product name</label>
    <input type="text" class="form-control" id="productname" name="productname" placeholder="product name" Required>
  </div>
  
  <div class="form-group">
    <label for="quantity">Quantity</label>
    <input type="number" min="0" class="form-control" id="quantity" name="quantity" placeholder="quantity" Required>
  </div>
  
  <div class="form-group">
    <label for="fromprice">From price</label>
    <input type="number" min="0" class="form-control" id="fromprice" name="fromprice" placeholder="price the bid start from" value="0" Required>
  </div>
  
  <div class="form-group">
    <label for="totime">Ends on</label>
    <input type="date" min="${today}" class="form-control" id="totime" name="totime" placeholder="mm/dd/yyyy" Required>
  </div>
 
  
  <div class="form-group">
    <label for="category">Select a category</label>
    <select class="form-control" id="category" name="category">
    	<c:forEach items="${category}" var="record">
    		<option value="${record.categoryid}">${record.categoryname}</option>
		</c:forEach>
    </select>
  </div>
  
  <div class="form-group">
    <label for="description">Description</label>
    <textarea class="form-control" id="description" name="description" rows="3" Required></textarea>
  </div>
  
  <input type="hidden" class="form-control" id="requesturi" name="requesturi" value="${fromurl}" />
  
  <div class="modal-footer" style="border-top: none;">
  	<button type="button" class="btn btn-outline-dark btn-sm" onclick="location.href = '${pageContext.request.contextPath}';">Back</button>
  	<button type="reset" class="btn btn-outline-danger btn-sm">Reset</button>
  	<button type="submit" class="btn btn-warning btn-sm"><strong>Submit</strong></button>
  </div>
</form>

            </div>
          </div>
        </div>

      </div>
    </div>
  </div>

</main>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	</body>
	
</html>
