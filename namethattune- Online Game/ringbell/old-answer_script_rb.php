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
	document.getElementById('youranswer').style.display = "none";
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('reveal_answer').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	document.getElementById('youranswer_msg').style.display = "none";

} 


function validateForm(payoff, answer)
{
	document.getElementById('youranswer_msg').style.display = "block";
	var youranswer =document.getElementById('youranswer').value;
	
	if (youranswer ==null || youranswer =="")
	{
		//  alert("Answer must be filled out");
		document.getElementById('youranswer_msg').innerHTML = 'Answer must be filled out';
		  return false;
	
	}else {
		  document.getElementById('youranswer_msg').innerHTML = '';
		 checkAnswer(payoff, answer);  
	  }
}


function nameThatTune() {
	document.getElementById('youranswer').style.display = "block";   
	document.getElementById('submitanswer').style.display = "block";
	document.getElementById('clue').style.display = "none";
	
	document.getElementById('countdown').style.display = "block";
	
	
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	
	 cddisplay();
}


function playClue(soundfile) {
	document.getElementById("dummy").innerHTML=
	"<audio id='playclue' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('countdown').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	document.getElementById('youranswer_msg').style.display = "none";
	
	
	document.querySelector("#playclue").addEventListener("ended", function(){
    
		document.getElementById('clue').style.display = "none"; //if song ended hide reveal text
		document.getElementById("playsongbutton").className="enable"; //clue bajepachi balla aru button enable huncha
	});


}
 
 
 function playSong(soundfile) {
	document.getElementById("dummy").innerHTML=
	"<audio id='player' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('youranswer').style.display = "none";   
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	document.getElementById('clue').style.display = "none";
	document.getElementById('youranswer_msg').style.display = "none";
	
	document.getElementById('countdown_msg').style.display = "block"; //start ma song finish bhayepachi countdown aaucha bhancha
	
	document.querySelector("#player").addEventListener("ended", function(){
  
  	document.getElementById("revealanswerbutton").className="enable"; //song bajepachi balla aru button enable huncha
	document.getElementById("namethattunebutton").className="enable"; //song bajepachi balla aru button enable huncha
	
	
	
		
		
		countdown();
	});
	
	
}

function revealAnswer() {
	document.getElementById('youranswer').style.display = "none";
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('clue').style.display = "none";
	document.getElementById('reveal_answer').style.display = "block";
	document.getElementById('countdown').style.display = "none";
	document.getElementById('countdown_msg').style.display = "none";
	
	document.getElementById('youranswer_msg').style.display = "none";

}

function playReveal(payoff, answer) {
	document.getElementById("dummy").innerHTML=
	"<audio id='playpayoff' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	document.getElementById('reveal_answer').style.display = "none"; //if at first hide reveal text
	document.getElementById('countdown').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	
	
	document.getElementById("namethattunebutton").className="disable";
	document.getElementById("nextanswerbutton").className="enable";
	
	document.querySelector("#playpayoff").addEventListener("ended", function(){
    	document.getElementById("dummy").innerHTML=
	"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
		
		
		
		document.querySelector("#playreveal").addEventListener("ended", function(){
    	
		document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
		
	});
	
	});
	
	

}



function checkAnswer(payoff, answer) {
	var answer1 = document.getElementById("youranswer").value; 
	var errText = "";    
	
	
	var textbox_correct_answer = document.getElementById("inputboxanswer").value;
	
	
	//var isTimeOut = document.getElementById("timeout_image").value; 
	var displayValue = document.getElementById('timeout_image').style.display;
	if(displayValue != 'none'){
		document.getElementById('youranswer').style.display = "none"; //in case if time is already finished
		document.getElementById('submitanswer').style.display = "none"; //in case if time is already finished
		alert("Sorry! Your time is already finished");
	} else {
		
		document.getElementById("nextanswerbutton").className="enable";
	
		if(answer1.toLowerCase().replace(/ /g, '') ==  textbox_correct_answer.toLowerCase().replace(/ /g, '') ){
			//here .replace(/ /g, '') removes multiple whitespaces
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
				
			
			document.getElementById('answer_image').style.display = "block";
			document.getElementById('answer_image').innerHTML = '<img src="images/correct.png" alt=" " />';
			
			document.getElementById('countdown_msg').style.display = "none"; //correct cha bhane display countdown none garne
			document.getElementById('countdown').style.display = "none"; //correct cha bhane display countdown none garne
			document.getElementById("namethattunebutton").className="disable";
						
			
			document.getElementById('yourscore').style.display = "block";
			document.getElementById('yourscore').innerHTML = "You've won "  + yourscore + ' points';
	
		} else {
		//alert('wrong');	
			document.getElementById('answer_image').style.display = "block";
			document.getElementById('answer_image').innerHTML = '<img src="images/incorrect.png" alt=" " />';
			
			document.getElementById('yourscore').style.display = "none";
		}
		
		document.getElementById("dummy").innerHTML=
	"<audio id='playpayoff' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
			
		document.querySelector("#playpayoff").addEventListener("ended", function(){
			document.getElementById("dummy").innerHTML=
			"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
			document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
			
			
			document.querySelector("#playreveal").addEventListener("ended", function(){
			
				document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
				
				document.getElementById("nextanswerbutton").className="enable";
			});
		
		});
	
	}

}

function nextSong(songId, gametype) {
	//alert(songId + 1);
	var newsong = songId + 1;
	var totalcorrectTillNow =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
	
	if(newsong > 5){
		//window.location.replace("play.php");
		window.location.href = "play.php";
	} else {
		window.location.replace(gametype+".php?gid="+newsong+"&t="+totalcorrectTillNow);
	}
	
}


</script>
<script>
    
    var CCOUNT = 11;
    
    var t, count;
    
    function cddisplay() {
        // displays time in span
		
        document.getElementById('countdown').innerHTML = count;
		//alert(count);
		 if (count == 0) {
			document.getElementById('countdown').style.display = "none";
			document.getElementById('answer_image').style.display = "none";
			document.getElementById('timeout_image').style.display = "block";
			document.getElementById('timeout_image').innerHTML = '<img src="images/timeout.png" alt=" " />';
			
			document.getElementById('youranswer').style.display = "none";
			document.getElementById('submitanswer').style.display = "none";
			
			
				document.getElementById("namethattunebutton").className="disable"; 
				document.getElementById("nextanswerbutton").className="enable";
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