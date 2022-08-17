function bookNow(){
    var input = document.getElementsByTagName('input')[0].value;
    var input1 = document.getElementsByTagName('input')[1].value;
    var input2 = document.getElementsByTagName('input')[2].value;
    var input3 = document.getElementsByTagName('input')[3].value;

    var myWindows = window.open('abc','myWindows',"width = 300px, heigh = 300px");
    myWindows.document.write('<p><b> Welcome to book a tour our. Please check your booking information:</b></p>');
    myWindows.document.write('Tour: ' + input + '<br><br>');
    myWindows.document.write('Your Name: ' + input1 + '<br><br>');
    myWindows.document.write('Phone: ' + input2 + '<br><br>');
    myWindows.document.write('Number of Travelers: ' + input3 + '<br><br>');
}