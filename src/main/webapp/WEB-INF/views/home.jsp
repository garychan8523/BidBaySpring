<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

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
        <sf:form action="login" method="post" modelAttribute="login_form_blank_user">
          <div class="form-group">
            <sf:input type="text" path="username" class="form-control" id="username" name="username" placeholder="username" required="true" />
          </div>
          <div class="form-group">
            <sf:input type="password" path="hash_password" class="form-control" id="password" name="password" placeholder="password" required="true" />
          </div>
          <input type="hidden" class="form-control" id="frompage" name="frompage" value="${fromurl}" />
      </div>
      <div class="modal-footer">
        <button type="reset" class="btn btn-outline-dark">Reset</button>
        <button type="submit" class="btn btn-warning"><strong>Login</strong></button>
      </div>
	  </sf:form>
    </div>
  </div>
</div>

<div class="modal fade" id="signupModal" tabindex="-1" role="dialog" aria-labelledby="signupTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">Signup</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body col-md-6" style="margin: auto;">
        <form action="signup" method="post">
          <div class="form-group">
            <input type="text" class="form-control" id="username" name="username" placeholder="username" pattern=".{2,}" title="username must contain two or more characters" required>
          </div>
          <div class="form-group">
            <input type="password" class="form-control" id="password" name="password" placeholder="password" pattern=".{6,}" title="password must cnotain six or more characters" required>
          </div>
		  <div class="form-group">
            <input type="email" class="form-control" id="email" name="email" placeholder="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
          </div>
      </div>
      <div class="modal-footer">
        <button type="reset" class="btn btn-outline-dark">Reset</button>
        <button type="submit" class="btn btn-warning"><strong>Register</strong></button>
      </div>
	  </form>
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
	  <!--
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
	  -->
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

  <section class="jumbotron text-center">
    <div class="container">
      <h1 class="jumbotron-heading">Bidbay</h1>
      
      <p class="lead text-muted">
      <% 
	  		if(session.getAttribute("username") != null) {
	  			out.println("Welcome to Bidbay, scroll and browse the latest listing");
	  		}else{
	  			out.println("Simple auction site, sign up or login to get started.");
	  		}
	  %>
	  </p>
	  
      <p>
      <% if(session.getAttribute("username") == null) { %>
	  		<a href="#" class="btn btn-warning my-2" data-toggle="modal" data-target="#loginModal"><strong>Login</strong></a>
	  		<a href="#" class="btn btn-outline-dark my-2" style="color: #000;" data-toggle="modal" data-target="#signupModal">Sign-up</a>
	  <% }else{ %>
	  		<form action="login" method="post"><input type="hidden" class="form-control" id="frompage" name="frompage" value="${fromurl}" /><button type="submit" name="logout" value="true" class="btn btn-outline-secondary">Logout</button></form>
	  <% } %>
      </p>
    </div>
  </section>

  <div class="album py-5 bg-light">
    <div class="container">
    
	<div style="padding-bottom: 10px;">
		<span style="display: inline-block;"><h5>Latest Bid</h5></span>
		<span style="float: right;"><button type="button" class="btn btn-outline-primary btn-sm" onclick="location.href = '${pageContext.request.contextPath}/listing/add';"><strong>Add a bid event</strong></button></span>
	</div>
	
      <div class="row">
      
      <c:forEach items="${bideventPrice}" var="entry">
	
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><i class="fa fa-gift" style="position: absolute; font-size: 65px; color: #fff; margin-top: 80px; margin-left: calc(50% - 25px);"></i></text></svg>
            <div class="card-body">
              <h5>${entry.key.itemname} @ ${entry.key.quantity} for $${entry.value}</h5>
              <p class="card-text">
              <div class="card-category">${entry.key.category.categoryname}</div>
              ${entry.key.description}
              </p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href = '${pageContext.request.contextPath}/listing/${entry.key.eventid}';">Details</button>
                </div>
                <small class="text-muted">${entry.key.fromTime} by ${entry.key.user.username}</small>
              </div>
            </div>
          </div>
        </div>
        
      </c:forEach>
        
      </div>
    </div>
  </div>

</main>

<footer class="text-muted">
  <div class="container">
    <p class="float-right">
      <a href="#" style="color: #666;">Back to top</a>
    </p>
    <div class="navbar-brand"><strong><font color="#010101">Bidbay</font></strong></div>
  </div>
</footer>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
	</body>
	
</html>

