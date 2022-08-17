function billingFunction() {
  /////////////////////////////
  var pi = document.getElementById('same');//////
  /////////////////////////////
  var nameOfShippppp =     document.getElementById('shippingName').value;
  var zipOfSillll = document.getElementById('shippingZip').value;
  
  if (pi.checked == true) { //////
    document.getElementById('billingName').value = nameOfShippppp;
    document.getElementById('billingZip').value = zipOfSillll;
  } 
  else {
    /////////////////////////////
    var nonnnnn = "";
    document.getElementById('billingName').value = nonnnnn;
    /////////////////////////////
    document.getElementById('billingZip').value = nonnnnn;
  }
}