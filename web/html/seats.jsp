<!DOCTYPE html>
<html>
<head>
<title>Seat Availability | Young Shall Grow Motors</title>

<link rel="stylesheet" type="text/css" href="../css/jquery.seat-charts.css">
<link href=../css/seats.css rel="stylesheet" type="text/css" media="all" />
<script src=../js/jquery-1.11.0.min.js></script>
<script src=../js/jquery.seat-charts.js></script>
</head>
<body>
<div class="content">
	<div class="main">
		<h2>Book Seats</h2>
		<div class="wrapper">
			<div id="seat-map">
				<div class="front-indicator"><h3>Bus Layout</h3></div>
			</div>
			<div class="booking-details">
						<div id="legend"></div>
						<h3> Selected Seats (<span id="counter">0</span>):</h3>
						<ul id="selected-seats" class="scrollbar scrollbar1"></ul>
						
						Total: <b>N <span id="total">0</span></b>
						
                                                <a href="./checkout.html"><button class="checkout-button" type="submit">Proceed to Checkout</button></a>

			</div>
			<div class="clear"></div>
		</div>
		<script>
				var firstSeatLabel = 1;
			
				$(document).ready(function() {
					var $cart = $('#selected-seats'),
						$counter = $('#counter'),
						$total = $('#total'),
						sc = $('#seat-map').seatCharts({
						map: [
							'ff_ff',
							'ff_ff',
							'ff_ff',
							'ee_ee',
							'ee_ee',
							'ee_ee',
							'ee_ee',
							'ee_ee',
							'eeeee',
						],
						seats: {
							f: {
								price   : 50,
								classes : 'first-class', //your custom CSS class
								category: 'First Class'
							},
							e: {
								price   : 10,
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
								$('<li>'+this.data().category+' : Seat no. '+this.settings.label+': <b>N'+this.data().price+'</b> <a href="#" class="cancel-cart-item">Remove</a></li>')
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