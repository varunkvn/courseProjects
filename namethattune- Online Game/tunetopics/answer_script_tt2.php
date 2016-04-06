<style>
.disable {
    pointer-events:none;
}
.enable {
   
}

</style>

<script type = "text/javascript">
function showClueDiv() {
	document.getElementById('clue').style.display = "block";
	
	document.getElementById('youranswer1').style.display = "none";
	document.getElementById('youranswer2').style.display = "none";
	document.getElementById('youranswer3').style.display = "none";
	document.getElementById('youranswer4').style.display = "none";
	
	document.getElementById('youranswer1_msg').style.display = "none";
	document.getElementById('youranswer2_msg').style.display = "none";
	document.getElementById('youranswer3_msg').style.display = "none";
	document.getElementById('youranswer4_msg').style.display = "none";
	
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('reveal_answer').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	
	

} 


function validateForm(payoff, answer)
{

	document.getElementById('youranswer1_msg').style.display = "block";
	var youranswer1 =document.getElementById('youranswer1').value;
	
	document.getElementById('youranswer2_msg').style.display = "block";
	var youranswer2 =document.getElementById('youranswer2').value;
	
	document.getElementById('youranswer3_msg').style.display = "block";
	var youranswer3 =document.getElementById('youranswer3').value;
	
	document.getElementById('youranswer4_msg').style.display = "block";
	var youranswer4 =document.getElementById('youranswer4').value;
	
	
	
	if (youranswer1 ==null || youranswer1 =="")
	{
		//  alert("Answer must be filled out");
		document.getElementById('youranswer1_msg').innerHTML = 'Answer must be filled out';
		  return false;
	
	} 
	else if (youranswer2 ==null || youranswer2 =="")
	{
		//  alert("Answer must be filled out");
		 document.getElementById('youranswer1_msg').innerHTML = '';
		document.getElementById('youranswer2_msg').innerHTML = 'Answer must be filled out';
		  return false;
	
	} else if (youranswer3 ==null || youranswer3 =="")
	{
		//  alert("Answer must be filled out");
		 document.getElementById('youranswer2_msg').innerHTML = '';
		document.getElementById('youranswer3_msg').innerHTML = 'Answer must be filled out';
		  return false;
	
	}  else if (youranswer4 ==null || youranswer4 =="")
	{
		//  alert("Answer must be filled out");
		 document.getElementById('youranswer3_msg').innerHTML = '';
		document.getElementById('youranswer4_msg').innerHTML = 'Answer must be filled out';
		  return false;
	
	} else {
		  document.getElementById('youranswer4_msg').innerHTML = '';
		  document.getElementById("revealanswerbutton").style="color:#FFFF00";
		 checkAnswer(payoff, answer);  
	  }
}


function nameThatTune() {
	document.getElementById('youranswer1').style.display = "block";  
	document.getElementById('youranswer1').focus();
	document.getElementById('youranswer2').style.display = "block";   
	document.getElementById('youranswer3').style.display = "block";  
	document.getElementById('youranswer4').style.display = "block";  
	 
	document.getElementById('submitanswer').style.display = "block";
	//document.getElementById('clue').style.display = "none";
	
	//document.getElementById('countdown').style.display = "block";
	//document.getElementById('countdown_msg').style.display = "none";
	
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	

}


function playClue(soundfile) {
	document.getElementById("dummy").innerHTML=
	"<audio id='playclue' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('countdown').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	
	document.getElementById('youranswer1_msg').style.display = "none";
	document.getElementById('youranswer2_msg').style.display = "none";
	document.getElementById('youranswer3_msg').style.display = "none";
	document.getElementById('youranswer4_msg').style.display = "none";
	
	
	document.querySelector("#playclue").addEventListener("ended", function(){
    
		//document.getElementById('clue').style.display = "none"; //if song ended hide reveal text
		document.getElementById("playsongbutton").className="enable"; //clue bajepachi balla aru button enable huncha
	});


}
 
 
 function playSong(soundfile) {
	document.getElementById("dummy").innerHTML=
	"<audio id='player' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('youranswer1').style.display = "none";  
	document.getElementById('youranswer2').style.display = "none";  
	document.getElementById('youranswer3').style.display = "none";  
	document.getElementById('youranswer4').style.display = "none";    
	 
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	//document.getElementById('clue').style.display = "none";
	document.getElementById('youranswer1_msg').style.display = "none";
	document.getElementById('youranswer2_msg').style.display = "none";
	document.getElementById('youranswer3_msg').style.display = "none";
	document.getElementById('youranswer4_msg').style.display = "none";
	
	document.getElementById('countdown_msg').style.display = "block"; //start ma song finish bhayepachi countdown aaucha bhancha
	
	
	document.querySelector("#player").addEventListener("ended", function(){
    	document.getElementById("revealanswerbutton").className="enable"; //clue bajepachi balla aru button enable huncha
		document.getElementById("namethattunebutton").className="enable"; //song bajepachi balla aru button enable huncha
		countdown(); //if song ended
	});
	
	
	
}

function revealAnswer() {
	document.getElementById('youranswer1').style.display = "none";
	document.getElementById('youranswer2').style.display = "none";
	document.getElementById('youranswer3').style.display = "none";  
	document.getElementById('youranswer4').style.display = "none"; 
	
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('clue').style.display = "none";
	document.getElementById('reveal_answer').style.display = "block";
	document.getElementById('countdown').style.display = "none";
	document.getElementById('countdown_msg').style.display = "none";
	
	document.getElementById('youranswer1_msg').style.display = "none";
	document.getElementById('youranswer2_msg').style.display = "none";
	document.getElementById('youranswer3_msg').style.display = "none";
	document.getElementById('youranswer4_msg').style.display = "none";

}

function playReveal( answer) {
	
	
	document.getElementById('reveal_answer').style.display = "none"; //if at first hide reveal text
	document.getElementById('countdown').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	
	//document.getElementById("namethattunebutton").className="disable";
	document.getElementById("nextanswerbutton").className="enable";
	document.getElementById("revealanswerbutton").style="color:#ffffff";
	
	
    	document.getElementById("dummy").innerHTML=
	"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
		
		
		document.querySelector("#playreveal").addEventListener("ended", function(){
    	
		//document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
	
	
	});
	
	

}

function checkAnswer(answer) {
	var answer1 = document.getElementById("youranswer1").value; 
	var answer2 = document.getElementById("youranswer2").value; 
	var answer3 = document.getElementById("youranswer3").value; 
	var answer4 = document.getElementById("youranswer4").value; 
	
	var errText = "";    
	
	
	var textbox_correct_answer1 = document.getElementById("inputboxanswer1").value;
	var textbox_correct_answer2 = document.getElementById("inputboxanswer2").value;
	var textbox_correct_answer3 = document.getElementById("inputboxanswer3").value;
	var textbox_correct_answer4 = document.getElementById("inputboxanswer4").value;
	
	var acceptable_answer1 = document.getElementById("acceptableanswer1").value;
	var acceptable_answer2 = document.getElementById("acceptableanswer2").value;
	var acceptable_answer3 = document.getElementById("acceptableanswer3").value;
	var acceptable_answer4 = document.getElementById("acceptableanswer4").value;
	
	
	//var isTimeOut = document.getElementById("timeout_image").value; 
	var displayValue = document.getElementById('timeout_image').style.display;
	if(displayValue != 'none'){
		document.getElementById('youranswer1').style.display = "none"; //in case if time is already finished
		document.getElementById('youranswer2').style.display = "none"; //in case if time is already finished
		document.getElementById('youranswer3').style.display = "none"; //in case if time is already finished
		document.getElementById('youranswer4').style.display = "none"; //in case if time is already finished
		
		document.getElementById('submitanswer').style.display = "none"; //in case if time is already finished
		alert("Sorry! Your time is already finished");
	} else {
		document.getElementById("nextanswerbutton").className="enable";
		
		if( (answer1.toLowerCase() ==  textbox_correct_answer1.toLowerCase() || answer1.toLowerCase() ==  acceptable_answer1.toLowerCase() )
		&& ( answer2.toLowerCase() ==  textbox_correct_answer2.toLowerCase() || answer2.toLowerCase() ==  acceptable_answer2.toLowerCase() ) 
		&& (  answer3.toLowerCase() ==  textbox_correct_answer3.toLowerCase() ||  answer3.toLowerCase() ==  acceptable_answer3.toLowerCase() ) 
		&& ( answer4.toLowerCase() ==  textbox_correct_answer4.toLowerCase() || answer4.toLowerCase() ==  acceptable_answer4.toLowerCase())
		){
			//alert('correct');
			<!-- add score starts here -->
			var istriedprevAndCorrect = parseInt(document.getElementById('isPrevTrialCorrect').innerHTML, 10);
			var totalcorrect =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
			
			if(istriedprevAndCorrect == 0){
				var newcorrect = totalcorrect + 1;			
				document.getElementById('isPrevTrialCorrect').innerHTML = 1;	//ek patak milayo bhane 1 set garcha so next time score add hunna
			} else {
				var newcorrect = totalcorrect ;			
				
			}
			document.getElementById('totalCorrect').innerHTML = newcorrect;	
			var yourscore = newcorrect * 1000;
			<!-- add score ends here -->
			document.getElementById('countdown').style.display = "none";	//correct cha bhane display countdown none garne
			document.getElementById('countdown_msg').style.display = "none"; 
			document.getElementById("namethattunebutton").className="disable";
			
			document.getElementById('answer_image').style.display = "block";
			document.getElementById('answer_image').innerHTML = '<img src="images/correct.png" alt=" " />';
			
			document.getElementById('yourscore').style.display = "block";
			document.getElementById('yourscore').innerHTML = "You've won "  + yourscore + ' points';
			
			
	
		} else {
		//alert('wrong');	
			document.getElementById('answer_image').style.display = "block";
			document.getElementById('answer_image').innerHTML = '<img src="images/incorrect.png" alt=" " />';
			
			document.getElementById('yourscore').style.display = "none";
		}
		
	
		//	document.getElementById("dummy").innerHTML=
		//	"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
			
			
		//	document.querySelector("#playreveal").addEventListener("ended", function(){
			
				//document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
		//		document.getElementById("nextanswerbutton").className="enable";
		
		
	//	});
	
	}

}

function nextSong(songId, gametype) {
	//alert(songId + 1);
	var newsong = songId + 1;
	var totalcorrectTillNow =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
	
	if(newsong > 2){
		//window.location.replace("play.php");
		window.location.href = "play.php?ttt="+totalcorrectTillNow;
	} else {
		window.location.replace(gametype+".php?gid="+newsong+"&t="+totalcorrectTillNow);
	}
	
}


</script>
<script>
    
    var CCOUNT = 21;
    
    var t, count;
    
    function cddisplay() {
        // displays time in span
		
        document.getElementById('countdown').innerHTML = count;
		//alert(count);
		 if (count == 0) {
			document.getElementById('countdown').style.display = "none";
			document.getElementById('countdown_msg').style.display = "none";
			document.getElementById('answer_image').style.display = "none";
			document.getElementById('timeout_image').style.display = "block";
			document.getElementById('timeout_image').innerHTML = '<img src="images/timeout.png" alt=" " />';
			
			document.getElementById("namethattunebutton").className="disable"; 
			document.getElementById('youranswer1').style.display = "none"; //in case if time is already finished
			document.getElementById('youranswer2').style.display = "none"; //in case if time is already finished
			document.getElementById('youranswer3').style.display = "none"; //in case if time is already finished
			document.getElementById('youranswer4').style.display = "none"; //in case if time is already finished
	
			document.getElementById('submitanswer').style.display = "none";
		 } 
		 
		 
    };
    
    function countdown() {
        // starts countdown
        cddisplay();
        if (count == 0) {
            // time is up
				document.getElementById("countdown_msg").style.display = "none"; 
        } else {
            count--;
            t = setTimeout("countdown()", 1000);
			 
			document.getElementById('timeout_image').style.display = "none";
			document.getElementById('answer_image').style.display = "none";
			document.getElementById('countdown').style.display = "block";
			//document.getElementById('countdown_msg').style.display = "none";
			
			document.getElementById("song_title").style.display = "none"; //song bajepachi song title hatchha
			document.getElementById("countdown_msg").style.fontSize = '42px';
			document.getElementById("countdown_msg").innerHTML  = "Countdown"; //song bajepachi "countdown" text aaucha			
			document.getElementById("countdown_msg").style.display = "block"; 
			
			document.getElementById("countdown").innerHTML = count;
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