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
      <sf:form action="${pageContext.request.contextPath}/login" method="post" modelAttribute="login_form_blank_user">
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
		<a href="/Bidbay"><< Back</a>
      <div class="row">
        <div class="col-md-4 listing">
          <div class="card mb-4 shadow-sm listing-card">
            <svg class="bd-placeholder-img card-img-top listing-img" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><i class="fa fa-gift" style="position: absolute; font-size: 65px; color: #333; height: 100%; margin-top: 15%; margin-left: calc(17.5% - 25px);"></i></text></svg>
			
            <div class="card-body">
				<h3># ${bidevent.eventid} / ${bidevent.itemname} <c:if test="${hasend != null}">[Bid has ended]</c:if></h3>
				<span class="metatitle" style="color: #999">start from: ${bidevent.fromTime}</span>
				<span class="metatitle" style="color: #999; margin-left: 10px;">end on: ${bidevent.toTime}</span>
				<span class="metatitle" style="color: #999; margin-left: 10px;">status: 
				<c:if test="${hasend != null}">
					ended
				</c:if>
				<c:if test="${hasend == null}">
					active
				</c:if>
				</span>
				
				<p class="bidmeta" style="margin-top: 10px;">
					<span class="metatitle">Sold from: </span>${bidevent.user.username}
					<br><span class="metatitle">Category: </span>${bidevent.category.categoryname}
					<br><span class="metatitle">Quantity: </span>${bidevent.quantity}
					<br><span class="metatitle">Bid start from: </span>$${bidevent.fromPrice}
					<br><span class="metatitle">Description: </span>${bidevent.description}
				</p>
				
				<div class="d-flex justify-content-between align-items-center">
				
					<c:choose>
				         <c:when test = "${currentbid != null}">
				            <span>
								Current bid: 
								<h3>$${currentbid}</h3>
							</span>
				         </c:when>

				         <c:otherwise>
				            <span>
								From price: 
								<h3>$${bidevent.fromPrice}</h3>
							</span>
				         </c:otherwise>
      				</c:choose>
					
					<c:if test="${hasend == null}">
						<% if(session.getAttribute("username") != null) { %>
							<form action="addBid" method="POST">
								<div class="btn-group" style="">
									<input type="text" class="form-control" id="bidamount" name="bidamount" placeholder="bid amount" required>
									<input type="hidden" class="form-control" id="eid" name="eid" value="${eid}" />
									<input type="hidden" class="form-control" id="requesturi" name="requesturi" value="${fromurl}" />
									<button type="submit" class="btn btn-sm btn-outline-secondary">Bid</button>
								</div>
							</form>
		  				<% } else { %>
		  					<a href="#" class="btn btn-warning my-2" data-toggle="modal" data-target="#loginModal"><strong>Login</strong></a>
		  				<% } %>
	  				</c:if>
	  				
				</div><br>
			  
			  <c:if test="${currentbid != null}">
	              <table class="table table-sm">
					  <thead>
					    <tr>
					      <th scope="col">Price</th>
					      <th scope="col">Made By</th>
					      <th scope="col">Timestamp</th>
					    </tr>
					  </thead>
					  <tbody>
					  	
					  	<c:forEach items="${bidrecord}" var="record">
					    	<tr>
					    	<th>$${record.price}</th>
							<td>${record.user.username}</td>
							<td>${record.timestamp}</td>
					    	</tr>
						</c:forEach>
					
					  </tbody>
					</table>
				</c:if>

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
