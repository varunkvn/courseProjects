<script>
    
    var CCOUNT = 60;
    
    var t, count;
    
    function cddisplay() {
        // displays time in span
        document.getElementById('timespan').innerHTML = count;
    };
    
    function countdown() {
        // starts countdown
        cddisplay();
        if (count == 0) {
            // time is up
        } else {
            count--;
            t = setTimeout("countdown()", 1000);
        }
    };
    
    function cdpause() {
        // pauses countdown
        clearTimeout(t);
    };
    
    function cdreset() {
        // resets countdown
        cdpause();
        count = CCOUNT;
        cddisplay();
    };
    
</script>

<body onload="cdreset()">
<span id="timespan"></span>
<input type="button" value="Start" onclick="countdown()">
<input type="button" value="Stop" onclick="cdpause()">
<input type="button" value="Reset" onclick="cdreset()">
</body>