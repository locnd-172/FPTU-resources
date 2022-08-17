function billingFunction() {
  ///////
  var pi = document.getElementById('same');//////
  ////
  var sname = 'shippingName';
  var sz = 'shippingZip';
  var bn = 'billingName';
  var bz = 'billingZip';
  var nameOfShippppp = document.getElementById(sname).value;
  var zipOfSillll = document.getElementById(sz).value;

  if (pi.checked == true) {
    document.getElementById(bn).value = nameOfShippppp;
    document.getElementById(bz).value = zipOfSillll;
  }
  else {
    /////////////////////////////
    var nonnnnn = "";
    document.getElementById(bn).value = nonnnnn;
    ///
    document.getElementById(bz).value = nonnnnn;
  }
}