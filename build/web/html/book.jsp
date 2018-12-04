<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>Book a Trip | Young Shall Grow Motors</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href=../css/bootstrap.min.css />

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href=../css/book.css />

</head>

<body>
	<div id="booking" class="section">
		<div class="section-center">
			<div class="container">
				<div class="row">
					<div class="col-md-7 col-md-push-5">
						<div class="booking-cta">
							<h1>Book now!</h1>
							<p>Travel the Lagos-Yola route with Young Shall Grow Motors.
							</p>
						</div>
					</div>
					<div class="col-md-4 col-md-pull-7">
						<div class="booking-form">
                                                    <form action="getTrip">
								<div class="form-group">
									<span class="form-label">From</span>
                                                                        <select class="form-control" name="source">
												<option>Lagos</option>
												<option>Yola</option>
											</select>
											<span class="select-arrow"></span>
								</div>
								<div class="form-group">
									<span class="form-label">To</span>
                                                                        <select class="form-control" name="destination">
												<option>Yola</option>
												<option>Lagos</option>
											</select>
											<span class="select-arrow"></span>
									</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<span class="form-label">Departure Date</span>
											<input class="form-control" type="date" name="trip_date" required>
										</div>
									</div>
<!--									<div class="col-sm-6">
										<div class="form-group">
											<span class="form-label">Arrival Date</span>
											<input class="form-control" type="date" required>
										</div>
									</div>-->
								</div>
<!--								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<span class="form-label">Adults</span>
											<select class="form-control">
												<option>1</option>
												<option>2</option>
												<option>3</option>
											</select>
											<span class="select-arrow"></span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group">
											<span class="form-label">Children</span>
											<select class="form-control">
												<option>1</option>
												<option>2</option>
												<option>3</option>
											</select>
											<span class="select-arrow"></span>
										</div>
									</div>
								</div>-->
								<div class="form-btn">
                                                                    <button class="submit-btn" type="submit">Book seats</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>