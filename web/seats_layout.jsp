<%-- 
    Document   : seats_layout
    Created on : Nov 30, 2018, 12:09:58 AM
    Author     : tochukwu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Seat Availability | Young Shall Grow Motors</title>

<link rel="stylesheet" type="text/css" href="./css/jquery.seat-charts.css">
<link href=./css/seats.css rel="stylesheet" type="text/css" media="all" />
<script src=./js/jquery-1.11.0.min.js></script>
<script src=./js/jquery.seat-charts.js></script>
</head>
<body>
<div class="content">
	<div class="main">
                <input type="hidden" id="price" value = '<s:property value="#attr.seats.get(0).getTrip().getPrice()" />'/>
                <input type="hidden" id="type" value = '<s:property value="#attr.seats.get(0).getTrip().getBus().getType()"/>'/>
		<h2>Book Seats</h2>
		<div class="wrapper">
			<div id="seat-map">
				<div class="front-indicator"><h3>Bus Layout</h3></div>
			</div>
                   
			<div class="booking-details">
                            <form action="checkout">
						<div id="legend"></div>
						<h3> Selected Seats (<span id="counter">0</span>):</h3>
						<ul id="selected-seats" class="scrollbar scrollbar1" name ="seats" name="cart"></ul>
						
						Total: <b>N <span id="total">0</span></b>
						
                                                <button class="checkout-button" type="submit">Proceed to Checkout</button>
                            </form>

			</div>
			<div class="clear"></div>
		</div>
		<script>
//                    var user = session.getAttribute("account");
//                    console.log(user);
				var firstSeatLabel = 1;
                                var price = parseInt(document.getElementById("price").value);
                                var type = new String(document.getElementById("type").value);
                                var mini = ['__ee_','eeee_','eeee_','eeee_','eeee_'];
                                var luxury = ['ee_ee','ee_ee','ee_ee','ee_ee','ee_ee','ee_ee','ee_ee','ee_ee','ee_ee','ee_ee','ee_ee','eeeee'];
                                var positions = null;
                                if (type.toLowerCase()==="luxury"){
                                    positions = luxury;
                                }else{
                                    positions = mini;
                                };
                                
				$(document).ready(function() {
					var $cart = $('#selected-seats'),
						$counter = $('#counter'),
						$total = $('#total'),
						sc = $('#seat-map').seatCharts({
						map: positions,
						seats: {
							f: {
								price   : price,
								classes : 'first-class', //your custom CSS class
								category: 'First Class'
							},
							e: {
								price   : price,
								classes : 'economy-class', //your custom CSS class
								category: 'Economy Class'
							}					
						
						},
						naming : {
							top : false,
							getLabel : function (character, row, column) {
								return firstSeatLabel++;
							},
						},
						legend : {
							node : $('#legend'),
							items : [
								[ 'f', 'available',   'First Class' ],
								[ 'e', 'available',   'Economy Class'],
								[ 'f', 'unavailable', 'Booked']
							]					
						},
						click: function () {
							if (this.status() == 'available') {
								//create a new <li> which we'll add to the cart items
								$('<li value='+this.settings.label+'>Seat no. '+this.settings.label+': <b>N'+this.data().price+'</b> <a href="#" class="cancel-cart-item">Remove</a></li>')
									.attr('id', 'cart-item-'+this.settings.id)
									.data('seatId', this.settings.id)
									.appendTo($cart);
								
								/*
								 * update the counter and total
								 *
								 * .find function will not find the current seat, because it will change its stauts only after return
								 * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.
								 */
								$counter.text(sc.find('selected').length+1);
								$total.text(recalculateTotal(sc)+this.data().price);
								
								return 'selected';
							} else if (this.status() == 'selected') {
								//update the counter
								$counter.text(sc.find('selected').length-1);
								//and total
								$total.text(recalculateTotal(sc)-this.data().price);
							
								//remove the item from our cart
								$('#cart-item-'+this.settings.id).remove();
							
								//seat has been vacated
								return 'available';
							} else if (this.status() == 'unavailable') {
								//seat has been already booked
								return 'unavailable';
							} else {
								return this.style();
							}
						}
					});

					//this will handle "remove" link clicks
					$('#selected-seats').on('click', '.cancel-cart-item', function () {
						// trigger Click event on the appropriate seat, so we don't have to repeat the logic here
						sc.get($(this).parents('li:first').data('seatId')).click();
					});

					//scenario some seats have already been booked
					sc.get(['1_4', '4_1', '7_1', '7_2']).status('unavailable');
                                        
                                        console.log(sc.map);
			
			});

			function recalculateTotal(sc) {
				var total = 0;
			
				//find every selected seat and sum its price
				sc.find('selected').each(function () {
					total += this.data().price;
				});
				
				return total;
			}
                        
		</script>
	</div>
	<p class="copy_rights">&copy; Young Shall Grow Motors 2017-2018 </p>
</div>
<script src="js/jquery.nicescroll.js"></script>
<script src="js/scripts.js"></script>
</body>
</html>
