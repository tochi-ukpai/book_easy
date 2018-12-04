<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Checkout | Young Shall Grow Motors</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <link rel="stylesheet" href=./checkout.css>

</head>
<body>
  <main class="page payment-page">
    <section class="payment-form dark">
      <div class="container">
        <div class="block-heading">
          <h2>Checkout</h2>
        </div>
        <form>
          <div class="products">
            <h3 class="title">Seats Booked</h3>
            <div class="item">
              <span class="price">Amount</span>
              <p class="item-name">Seat No. / Trip ID</p>
              <p class="item-description">Lorem ipsum</p>
            </div>
            <div class="total">Total<span class="price">Total Amount</span></div>
          </div>
          <div class="card-details">
            <h3 class="title">Payment Info</h3>
            <div class="row">
              <div class="form-group col-sm-7">
                <label for="card-holder">CardHolder</label>
                <input id="card-holder" type="text" class="form-control" placeholder="Last Name, First Name" aria-label="Card Holder" aria-describedby="basic-addon1">
              </div>
              <div class="form-group col-sm-10">
                <label for="">Expires</label>
                <div class="input-group expiration-date">
                  <input type="month" class="form-control" placeholder="Expiry Date" aria-label="Expiry Date" aria-describedby="basic-addon1">
                </div>
              </div>
              <div class="form-group col-sm-8">
                <label for="card-number">Card Number</label>
                <input id="card-number" type="text" class="form-control" placeholder="Card Number" aria-label="Card Holder" aria-describedby="basic-addon1">
              </div>
              <div class="form-group col-sm-4">
                <label for="cvc">CVC</label>
                <input id="cvc" type="text" maxlength="3" class="form-control" placeholder="CVC" aria-label="Card Holder" aria-describedby="basic-addon1">
              </div>
               <div class="form-group col-sm-8">
                <label for="Phone Number">Phone Number</label>
                <input id="Phone Number" type="tel"  class="form-control" placeholder="Phone Number" aria-label="Phone Number" aria-describedby="basic-addon1">
              </div>
              <div class="form-group col-sm-12">
                <button type="button" class="btn btn-primary btn-block">Confirm Payment</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </section>
  </main>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>