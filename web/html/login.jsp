
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Login Page">
    <meta name="author" content="YSG">
    <link rel="icon" href="#" width="100%" height="100%">

    <title>Login | Young Shall Grow Motors</title>

   
    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="../css/login.css" rel="stylesheet">
  </head>

  <body class="text-center">
      <form class="form-signin" action="login">
      <img class="mb-4" src="#" alt="" width="300" height="180">
      <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="inputEmail" class="form-control" placeholder="Email address" name='email' required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputPassword" class="form-control" placeholder="Password" name='pass' required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>

            <small> If you do not have an account, register <a href="./register.jsp"> here</a></small>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <p class="mt-5 mb-3 text-muted">&copy; Young Shall Grow Motors 2017-2018</p>
    </form>
  </body>
</html>