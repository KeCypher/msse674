<%@ page session="true" %>
<html>
	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
		<title>Icaria Travel Reservation System, Docker Version, This is a rebuild</title>
	</head>
	<body>
		<div class="container">
			 <h1>User Use Cases:</h1>
			 <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				  <li class="nav-item" role="presentation">
				    <button class="nav-link active" id="pills-register-tab" data-bs-toggle="pill" data-bs-target="#pills-register" type="button" role="tab" aria-controls="pills-register" aria-selected="true">Register</button>
				  </li>
				  <li class="nav-item" role="presentation">
				    <button class="nav-link" id="pills-login-tab" data-bs-toggle="pill" data-bs-target="#pills-login" type="button" role="tab" aria-controls="pills-login" aria-selected="false">Login</button>
				  </li>
			</ul>
				<div class="tab-content" id="pills-tabContent">
				<div class="tab-pane fade show active" id="pills-register" role="tabpanel" aria-labelledby="pills-register-tab">
					<div class="card">
					  	<div class="card-body">
					   		<form action="<%=request.getContextPath()%>/register" method="post" id="registration">
					   			<input type="hidden" name="sessionid" value="<%= session.getId() %>">  
					    		<div class="form-group row">
					     			<label for="firstName" class="col-sm-2 col-form-label">First Name:</label>
					     			<div class="col-sm-7">
					     				<input type="text" class="form-control" name="firstName" placeholder="John">
					     			</div>
					    		</div>
					
					    		<div class="form-group row">
								     <label for="lastName" class="col-sm-2 col-form-label">Last Name:</label>
								     <div class="col-sm-7">
								      	<input type="text" class="form-control" name="lastName" placeholder="Doe">
								     </div>
							    </div>
							    
					
							    <div class="form-group row">
								     <label for="email" class="col-sm-2 col-form-label">Email Address:</label>
								     <div class="col-sm-7">
								      	<input type="text" class="form-control" name="email" placeholder="jdoe@place.com">
								     </div>
							    </div>
					
							    <div class=" form-group row">
								     <label for="username" class="col-sm-2 col-form-label">Username:</label>
								     <div class="col-sm-7">
								      	<input type="text" class="form-control" name="username" placeholder="Enter Desired Username">
								     </div>
							    </div>
					
							    <div class="form-group row">
								     <label for="password" class="col-sm-2 col-form-label">Password:</label>
								     <div class="col-sm-7">
								      	<input type="password" class="form-control" name="password" placeholder="Enter Password">
								     </div>
							    </div>
					
							    <div class="form-group row">
								     <label for="confirm-password" class="col-sm-2 col-form-label">Confirm Password:</label>
								     <div class="col-sm-7">
								      	<input type="password" class="form-control" name="password2" placeholder="Confirm your Password">
								     </div>
							    </div>
					
					    		<button type="submit" class="btn btn-primary" value="registration">Submit</button>
					   		</form>
					  	</div>
					 </div>
				</div>
				<div class="tab-pane fade" id="pills-login" role="tabpanel" aria-labelledby="pills-login-tab">
					<div class="card">
					  	<div class="card-body">
					   		<form action="<%=request.getContextPath()%>/login" method="post" id="loginform">
					   			<input type="hidden" name="sessionid" value="<%= session.getId() %>">  
					
							    <div class=" form-group row">
								     <label for="username" class="col-sm-2 col-form-label">Username:</label>
								     <div class="col-sm-7">
								      	<input type="text" class="form-control" name="username" placeholder="Enter user name">
								     </div>
							    </div>
					
							    <div class="form-group row">
								     <label for="lastName" class="col-sm-2 col-form-label">Password</label>
								     <div class="col-sm-7">
								      <input type="password" class="form-control" name="password" placeholder="Enter Password">
								     </div>
							    </div>
					
					    		<button type="submit" class="btn btn-primary" value="loginform">Submit</button>
					   		</form>
					  	</div>
					 </div>
				</div>
			</div>
		</div>
	</body>
</html>
