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
		  document.getElementById("revealanswerbutton").style="color:#FFFF00";
		 checkAnswer(payoff, answer);  
	  }
}


function nameThatTune() {
	document.getElementById('selectSecond').style.display = "block";   
	
	document.getElementById('youranswer').style.display = "block";   
	document.getElementById('youranswer').focus(); //for autofucus
	
	document.getElementById('submitanswer').style.display = "block";
	//document.getElementById('clue').style.display = "none";
	
	//document.getElementById('countdown').style.display = "block";
	//document.getElementById('countdown_msg').style.display = "none";
	
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	
	document.getElementById('selectBid').style.display = "none";
	document.getElementById('selectSecond').style.display = "none";
	
	

}


function playClue(soundfile) {
	
	<?php  if( $_GET['gid'] == '1'){ ?>
	document.getElementById("my_div").className="show";	
	document.getElementById("start_description").className="hide";
	document.getElementById("gameInstructionAudio").innerHTML= "<audio  pause></audio>"; //first song ho bhane clue play garna sath intro pause huncha
	<?php } ?>
	
	document.getElementById("dummy").innerHTML=
	"<audio id='playclue' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('countdown').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	document.getElementById('youranswer_msg').style.display = "none";
	
	
	document.querySelector("#playclue").addEventListener("ended", function(){
    
		document.getElementById('clue').style.display = "none"; 
		document.getElementById("selectBid").style.display = "block"; 
	});


}
 
 
 function playSong(soundfilewithoutextension) {
	 
	var clipid = document.getElementById('selectSecond').value;
	soundfile = soundfilewithoutextension+' ' + clipid +'.mp3';
	//alert(soundfile);
	document.getElementById("dummy").innerHTML=
	"<audio id='player' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('youranswer').style.display = "none";   
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	//document.getElementById('clue').style.display = "none";
	document.getElementById('youranswer_msg').style.display = "none";
	
	document.getElementById('countdown_msg').style.display = "block"; //start ma song finish bhayepachi countdown aaucha bhancha
	
	document.querySelector("#player").addEventListener("ended", function(){
		document.getElementById("revealanswerbutton").className="enable"; //song bajepachi balla aru button enable huncha
		document.getElementById("namethattunebutton").className="enable"; //song bajepachi balla aru button enable huncha
		
   		countdown(); //if song ended
		document.getElementById('selectBid').style.display = "none";
		document.getElementById('selectSecond').style.display = "none";
	});
	
	
}

function revealAnswer() {
	document.getElementById('youranswer').style.display = "none";
	
	document.getElementById('clue').style.display = "none";
	document.getElementById('reveal_answer').style.display = "block";
	document.getElementById('countdown').style.display = "none";
	document.getElementById('countdown_msg').style.display = "none";
	
	document.getElementById('youranswer_msg').style.display = "none";
	
//	document.getElementById("namethattunebutton").className="disable";
	document.getElementById("nextanswerbutton").className="enable";
	
//	document.getElementById('youranswer').style.display = "none";
	document.getElementById('submitanswer').style.display = "none";
	

}

function playReveal(payoff, answer) {
	document.getElementById("dummy").innerHTML=
	"<audio id='playpayoff' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	document.getElementById('reveal_answer').style.display = "none"; //if at first hide reveal text
	document.getElementById('countdown').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	
		document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
	document.getElementById("revealanswerbutton").style="color:#ffffff";	
		
	//document.querySelector("#playpayoff").addEventListener("ended", function(){
    //	document.getElementById("dummy").innerHTML=
//	"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
		
	//	document.querySelector("#playreveal").addEventListener("ended", function(){
    	
	//	document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
//		document.getElementById("nextanswerbutton").className="enable";
//	});
	
	//});
	
	

}



function checkAnswer(payoff, answer) {
	var answer1 = document.getElementById("youranswer").value; 
	var errText = "";    
	
	
	var textbox_correct_answer = document.getElementById("inputboxanswer").value;
	var acceptable_answer = document.getElementById("acceptableanswer").value;
	
	var acceptable_answer2 = document.getElementById("acceptableanswer2").value;
	
	
	
	//var isTimeOut = document.getElementById("timeout_image").value; 
	var displayValue = document.getElementById('timeout_image').style.display;
	if(displayValue != 'none'){
		document.getElementById('youranswer').style.display = "none"; //in case if time is already finished
		document.getElementById('submitanswer').style.display = "none"; //in case if time is already finished
		alert("Sorry! Your time is already finished");
	} else {
		document.getElementById("nextanswerbutton").className="enable";
		
		if(answer1.toLowerCase() ==  textbox_correct_answer.toLowerCase() || answer1.toLowerCase() ==  acceptable_answer.toLowerCase()  || answer1.toLowerCase() ==  acceptable_answer2.toLowerCase() ){
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
			
			document.getElementById('countdown').style.display = "none"; //correct cha bhane display countdown none garne
			document.getElementById('countdown_msg').style.display = "none"; 
			
			document.getElementById("namethattunebutton").className="disable";
			
			
			document.getElementById('yourscore').style.display = "block";
			document.getElementById('yourscore').innerHTML = "You've won "  + yourscore + ' points';
	
		} else {
		//alert('wrong');	
			document.getElementById('answer_image').style.display = "block";
			document.getElementById('answer_image').innerHTML = '<img src="images/incorrect.png" alt=" " />';
			
			document.getElementById('yourscore').style.display = "none";
		}
		
	//	document.getElementById("dummy").innerHTML=
	//"<audio id='playpayoff' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
			
	//	document.querySelector("#playpayoff").addEventListener("ended", function(){
		//	document.getElementById("dummy").innerHTML=
		//	"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		//	document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
			
			
		//	document.querySelector("#playreveal").addEventListener("ended", function(){
			
				
		//		document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
				
		//		document.getElementById("nextanswerbutton").className="enable";
		//	});
		
	//	});
	
	}

}

function nextSong(songId, gametype) {
	//alert(songId + 1);
	var newsong = songId + 1;
	var totalcorrectTillNow =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
	
	if(newsong > 7){
		
		window.location.href = "play.php?bant="+totalcorrectTillNow;
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