<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Registration Page">
    <meta name="author" content="YSG">
    <link rel="icon" href="#">

    <title>Register | Young Shall Grow Motors</title>

   
    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href=../css/register.css rel="stylesheet">
  </head>

  <body class="text-center">
    <form class="form-signin" action = 'register'>
      <img class="mb-4" src="#" alt="" width="300" height="200">
      <h1 class="h3 mb-3 font-weight-normal">Register</h1>
       
       <label for="inputFirstName" class="sr-only">First Name</label>
      <input type="text" id="inputFirstName" class="form-control" placeholder="First Name" required autofocus name = 'firstN'>

       <label for="inputLastName" class="sr-only">Last Name</label>
      <input type="text" id="inputLastName" class="form-control" placeholder="Last Name" required autofocus name ='lastN'>

      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus name = 'email'>

      <label for="inputPhoneNumber" class="sr-only">Phone Number</label>
      <input type="tel" id="inputPhoneNumber" class="form-control" placeholder="Phone Number" required autofocus name = 'phone'>

      <label for="inputAddress" class="sr-only">Address</label>
      <input type="text" id="inputAddress" class="form-control" placeholder="Home Address" required autofocus>

      <label for="inputUsername" class="sr-only">Username</label>
      <input type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus name ='username'>
     
	  <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputPassword" class="form-control" placeholder="Password" required name = 'pass'>

      <label for="inputPassword" class="sr-only">Password-repeat</label>
      <input type="password" id="inputPassword" class="form-control" placeholder="Confirm Password" required>
   
      <small>By creating an account you agree to our <a href="#">Terms & Privacy</a></small>
      <small> Already have an account? <a href="./login.jsp">Log in</a></small>

      <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>

      <p class="mt-5 mb-3 text-muted">&copy; Young Shall Grow Motors 2017-2018</p>
    
    </form>
  </body>
</html>