function billingFunction() {
  ///////
  var pi = document.getElementById('same');//////
  ////
  var sname = 'shippingName';
  var sz = 'shippingZip';
  var biiiiin = 'billingName';
  var bzzzzzz = 'billingZip';
  var nameOfShippppp =     document.getElementById(sname).value;
  var zipOfSillll = document.getElementById(sz).value;
  
  if (pi.checked == true) { 
    document.getElementById(biiiiin).value = nameOfShippppp;
    document.getElementById(bzzzzzz).value = zipOfSillll;
  } 
  else {
    /////////////////////////////
    var nonnnnn = "";
    document.getElementById(bn).value = nonnnnn;
    ///
    document.getElementById(bz).value = nonnnnn;
  }
}