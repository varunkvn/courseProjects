<style>
.disable {
    pointer-events:none;
}
.enable {
   
}

</style>

<script type = "text/javascript">

function validateForm(payoff, answer)
{
	document.getElementById('youranswer_msg').style.display = "block";
	document.getElementById('artist_msg').style.display = "block";
	document.getElementById('decade_msg').style.display = "block";
	
	var youranswer =document.getElementById('youranswer').value;
	var artist =document.getElementById('artisttype').value;
	var decade =document.getElementById('selectDecade').value;
	
	if (youranswer ==null || youranswer =="")
	{
		//  alert("Answer must be filled out");
		document.getElementById('youranswer_msg').innerHTML = 'Answer must be filled out';
		  return false;
	} else if(artist ==null || artist ==""){
		// alert("Artist must be filled out");
		document.getElementById('youranswer_msg').innerHTML = '';
		document.getElementById('artist_msg').innerHTML = 'Artist must be filled out';
		  return false;
	} else if(decade ==null || decade ==""){
		//   alert("Decade must be filled out");
		
		document.getElementById('artist_msg').innerHTML = '';
		document.getElementById('decade_msg').innerHTML = 'Decade must be filled out';
		  return false;
	}else {
		  document.getElementById('youranswer_msg').innerHTML = '';
		  document.getElementById('artist_msg').innerHTML = '';
		  document.getElementById('decade_msg').innerHTML = '';
		  
		  document.getElementById("revealanswerbutton").style="color:#FFFF00";
		 checkAnswer(payoff, answer);  
	  }
}

function nameThatTune() {
	document.getElementById('youranswer').style.display = "block";   
	document.getElementById('youranswer').focus(); //for autofucus
	
	document.getElementById('artisttype').style.display = "block";  
	document.getElementById('selectDecade').style.display = "block";   
	
	document.getElementById('submitanswer').style.display = "block";
	
	//document.getElementById('reveal_answer').style.display = "none";
	
	//document.getElementById('countdown').style.display = "block";
	//document.getElementById('countdown_msg').style.display = "none";
	
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";

}

 
 function playSong(soundfile) {
	 
	 <?php  if( $_GET['gid'] == '1'){ ?>
	document.getElementById("my_div").className="show";	
	document.getElementById("start_description").className="hide";
	document.getElementById("gameInstructionAudio").innerHTML= "<audio  pause></audio>"; //first song ho bhane  play garna sath intro pause huncha
	<?php } ?>
	
	
	document.getElementById("dummy").innerHTML=
	"<audio id='player' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('youranswer').style.display = "none";   
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('reveal_answer').style.display = "none"; 
	
	document.getElementById('answer_image').style.display = "none";
	document.getElementById('timeout_image').style.display = "none";
	
	document.getElementById('artisttype').style.display = "none";  
	document.getElementById('selectDecade').style.display = "none";
	
	
	document.getElementById('youranswer_msg').style.display = "none";
	document.getElementById('artist_msg').style.display = "none";
	document.getElementById('decade_msg').style.display = "none";
	
	document.getElementById('countdown_msg').style.display = "block"; //start ma song finish bhayepachi countdown aaucha bhancha
	
	
	document.querySelector("#player").addEventListener("ended", function(){
		
   	document.getElementById("revealanswerbutton").className="enable"; //song bajepachi balla aru button enable huncha
	document.getElementById("namethattunebutton").className="enable"; //song bajepachi balla aru button enable huncha
	countdown(); //if song ended
	});
	
	
}



function playReveal(payoff, answer) {
	document.getElementById("dummy").innerHTML=
	"<audio id='playpayoff' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	document.getElementById('reveal_answer').style.display = "none"; //if at first hide reveal text
	
	document.getElementById('countdown').style.display = "none";
	document.getElementById('answer_image').style.display = "none";
	
	document.getElementById('artisttype').style.display = "none";  
	document.getElementById('selectDecade').style.display = "none";
	
	document.getElementById('youranswer_msg').style.display = "none";
	document.getElementById('artist_msg').style.display = "none";
	document.getElementById('decade_msg').style.display = "none";
	
	document.getElementById("revealanswerbutton").style="color:#ffffff";
	
	//document.getElementById("namethattunebutton").className="disable";
	document.getElementById("nextanswerbutton").className="enable";
	document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
	
	document.querySelector("#playpayoff").addEventListener("ended", function(){
   // 	document.getElementById("dummy").innerHTML=
	//"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		//document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
		
		
		//document.querySelector("#playreveal").addEventListener("ended", function(){
    	
		//document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
//	});
	
	});
	

}



function revealAnswer() {
	document.getElementById('youranswer').style.display = "none";
	document.getElementById('submitanswer').style.display = "none";
	document.getElementById('reveal_answer').style.display = "block";
	document.getElementById('countdown').style.display = "none";
	document.getElementById('countdown_msg').style.display = "none";
}


function checkAnswer(payoff, answer) {
	var answer1 = document.getElementById("youranswer").value; 
	answer1 = answer1.replace(/[\u2018|\u2019|\u201A]/g, "\'"); //replace microsoft encoded ’ to '
	var dropdown_artist = document.getElementById("artisttype").value; 
	var dropdown_decade = document.getElementById("selectDecade").value; 
	
	var artist_answer = document.getElementById("artistanswer").value;
	var decade_answer = document.getElementById("decadeanswer").value;
	
	var textbox_correct_answer = document.getElementById("inputboxanswer").value;
	var acceptable_answer = document.getElementById("acceptableanswer").value;
	
	
	if(dropdown_artist == ''){
		
		document.getElementById('artist_msg').style.display = "block";
		
	}
	
	if(dropdown_decade == ''){
		
		document.getElementById('decade_msg').style.display = "block";
		
	}
	
	
	
	
	var errText = "";    
	
	//var isTimeOut = document.getElementById("timeout_image").value; 
	var displayValue = document.getElementById('timeout_image').style.display;
	if(displayValue != 'none'){
		document.getElementById('youranswer').style.display = "none"; //in case if time is already finished
		document.getElementById('submitanswer').style.display = "none"; //in case if time is already finished
		document.getElementById('artisttype').style.display = "none";  
		document.getElementById('selectDecade').style.display = "none";   
		
		alert("Sorry! Your time is already finished");
	} else {
			document.getElementById("nextanswerbutton").className="enable";
	
		if( (answer1.toLowerCase() ==  textbox_correct_answer.toLowerCase() || answer1.toLowerCase() ==  acceptable_answer.toLowerCase() )
				&& dropdown_artist == artist_answer && dropdown_decade == decade_answer ){
			//alert('correct');
			
			
			//var totaltrial = parseInt(document.getElementById('totalCorrect').innerHTML, 10) + 1;
			//alert(totaltrial);
			
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
			
			
			
		
			var yourscore = newcorrect * 1000;
			
			document.getElementById('answer_image').style.display = "block";
			document.getElementById('answer_image').innerHTML = '<img src="images/correct.png" alt=" " />';
			
			document.getElementById('countdown').style.display = "none"; //correct cha bhane display countdown none garne
			document.getElementById('countdown_msg').style.display = "none"; //correct cha bhane display countdown none garne
			document.getElementById("namethattunebutton").className="disable";
			
			document.getElementById('yourscore').style.display = "block";
			document.getElementById('yourscore').innerHTML = "You've won "  + yourscore + ' points';
	
		} else {
		//alert('wrong');	
			
			document.getElementById('answer_image').style.display = "block";
			document.getElementById('answer_image').innerHTML = '<img src="images/incorrect.png" alt=" " />';
			
			
				
			document.getElementById('yourscore').style.display = "none";
		}
		
	//		document.getElementById("dummy").innerHTML=
	//"<audio id='playpayoff' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
			
	//	document.querySelector("#playpayoff").addEventListener("ended", function(){
			//document.getElementById("dummy").innerHTML=
			//"<audio id='playreveal' autoplay><source src=\""+answer+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		//	document.getElementById('reveal_answer').style.display = "block"; //if song ended hide reveal text
		
			
			//document.querySelector("#playreveal").addEventListener("ended", function(){
			
			//	document.getElementById('reveal_answer').style.display = "none"; //if song ended hide reveal text
				//	document.getElementById("nextanswerbutton").className="enable";
		//	});
		
	//	});
	}

}


function nextSong(songId, gametype) {
	//alert(songId + 1);
	var newsong = songId + 1;
	var totalcorrectTillNow =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
	
	if(newsong > 5){
		
		//window.location.href = "play.php";
		window.location.href = "play.php?tpt="+totalcorrectTillNow;
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