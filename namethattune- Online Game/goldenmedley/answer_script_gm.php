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
	
	//document.getElementById('submitanswer').style.display = "none";
	document.getElementById('reveal_answer').style.display = "none";
	
	document.getElementById('timeout_image').style.display = "none";
	
	

} 



function validateForm(id, payoff)
{
	//alert(id);
	document.getElementById('youranswer'+id+'_msg').style.display = "block";
	var  youranswer = document.getElementById('youranswer'+id).value;
	
	
	
	if (youranswer ==null || youranswer =="")
	{
		//  alert("Answer must be filled out");
		document.getElementById('youranswer'+id+'_msg').innerHTML = 'Answer must be filled out';
		  return false;
	
	} 
	 else {
		  document.getElementById('youranswer'+id+'_msg').innerHTML = '';
		 checkAnswer(id,payoff);  
		 
		 <!-- play reveal even if correct answer or not starts here -->
		// if(id == 1){
//			//playRevealInstantly('1', 'music/Golden Medley Showdown/GMS-1 Rock Around The Clock/GMS-1 50s Answer.mp3');
//			playRevealInstantly('1', 'music/Golden Medley Showdown/GMS-1 Rock Around The Clock/rock around the clock (payoff).mp3');
//			
//		 } else if (id == 2){
//		//	playRevealInstantly('2', 'music/Golden Medley Showdown/GMS-2 Louie, Louie/GMS-2 60s Answer.mp3');
//			playRevealInstantly('2', 'music/Golden Medley Showdown/GMS-2 Louie, Louie/louie louie (payoff).mp3');
//		} else if (id == 3){
//		//	playRevealInstantly('3', 'music/Golden Medley Showdown/GMS-3 Escape/GMS-3 70s Answer.mp3');
//			playRevealInstantly('3', 'music/Golden Medley Showdown/GMS-3 Escape/escape (payoff).mp3');
//		} else if (id == 4){
//			//	playRevealInstantly('4', 'music/Golden Medley Showdown/GMS-4 Livin On A Prayer/GMS-4 80s Answer.mp3');
//				playRevealInstantly('4', 'music/Golden Medley Showdown/GMS-4 Livin On A Prayer/livin on a prayer (payoff).mp3');
//		} else if (id == 5){
//			//	playRevealInstantly('5', 'music/Golden Medley Showdown/GMS-5 Hold On/GMS-5 90s Answer.mp3');
//				playRevealInstantly('5', 'music/Golden Medley Showdown/GMS-5 Hold On/hold on (payoff).mp3');
//		} else if (id == 6){
//				//playRevealInstantly('6', 'music/Golden Medley Showdown/GMS-6 Hollaback Girl/GMS-6 2000s Answer.mp3');
//				playRevealInstantly('6', 'music/Golden Medley Showdown/GMS-6 Hollaback Girl/hollaback girl (payoff).mp3');
//		} else if (id == 7){
//			//	playRevealInstantly('7', 'music/Golden Medley Showdown/GMS-7 Need You Now/GMS-7 2010s Answer.mp3');
//				playRevealInstantly('7', 'music/Golden Medley Showdown/GMS-7 Need You Now/need you now (payoff).mp3');
//		} else {
//			
//		}
			<!-- play reveal even if correct answer or not ends here -->
		 
		 
		
		 
	  }
	  
	 
}


function nameThatTune(songid) {
	
	document.getElementById('submitanswer'+songid).style.display = "block";
	document.getElementById('youranswer'+songid).style.display = "block";
	document.getElementById('youranswer'+songid).focus();
	
	document.getElementById('clue').style.display = "none";
	
	document.getElementById('countdown').style.display = "block";
	document.getElementById('countdown_msg').style.display = "none";
	
	
	document.getElementById('timeout_image').style.display = "none";
	

}


function playClue(soundfile) {
	document.getElementById("dummy").innerHTML=
	"<audio id='playclue' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
	document.getElementById('countdown').style.display = "none";

	document.getElementById('timeout_image').style.display = "none";
	
	document.getElementById('youranswer1_msg').style.display = "none";
	document.getElementById('youranswer2_msg').style.display = "none";
	document.getElementById('youranswer3_msg').style.display = "none";
	document.getElementById('youranswer4_msg').style.display = "none";
	
	
	document.querySelector("#playclue").addEventListener("ended", function(){
    
		document.getElementById('clue').style.display = "none"; //if song ended hide reveal text
		document.getElementById("playsongbutton").className="enable"; //clue bajepachi balla aru button enable huncha
	});


}
 
 
 function playSong(buttonid,soundfile) {
	 
	 <?php  if( $_GET['gid'] == '1'){ ?>
	document.getElementById("my_div").className="show";	
	document.getElementById("start_description").className="hide";
	document.getElementById("gameInstructionAudio").innerHTML= "<audio  pause></audio>"; //first song ho bhane clue play garna sath intro pause huncha
		document.getElementById("auto_hide_after_desc").className="hide";	
		
		document.getElementById("description_homepage_gm").style.display="block";
		
	<?php } ?>
	
	  if(buttonid == 8){
		 prevbuttonid = parseInt(buttonid) - 1;
		 document.getElementById("playsongbutton"+prevbuttonid).className="disable"; 
		 document.getElementById("passthatbutton"+prevbuttonid).className="enable";
	  } else {
		 document.getElementById("playsongbutton"+buttonid).className="disable"; 
		 document.getElementById("passthatbutton"+buttonid).className="enable"; 
	  }
	 
	 document.getElementById('start_description').style.display = "none";
	 
	
	 if(buttonid > 1) {
			var prevId = parseInt(buttonid) - 1;
			document.getElementById('reveal_answer'+prevId).style.display = "none"; //pahile ko reveal text show cha bhane hide garna laai
	}
		 
	  if(buttonid < 8){ //last patak pass garda same song repeat nagarna laai
		document.getElementById("dummy").innerHTML=
		"<audio id='player' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		
	  }
	
	for(i=1; i<=7;i++){	
		if(i != buttonid){
			 //document.getElementById("decade"+i).style.textDecoration=" none";
			 document.getElementById("decade"+i).style="color:#ABABAB";
			document.getElementById("playsongbutton"+i).style="color:#ABABAB";
			 document.getElementById("passthatbutton"+i).style="color:#ABABAB";
			  
		} else {
			
			  document.getElementById("decade"+buttonid).style="color:yellow";
			  document.getElementById("playsongbutton"+i).style="color:#ffffff";
			   document.getElementById("passthatbutton"+i).style="color:#ffffff";
		}
		
	}
	
	  
	 if(buttonid == 8){
		 buttonid = parseInt(buttonid) - 1;
		document.getElementById("decade"+buttonid).style="color:#ABABAB";
			document.getElementById("playsongbutton"+buttonid).style="color:#ABABAB";
			 document.getElementById("passthatbutton"+buttonid).style="color:#ABABAB"; 
			 
			 for(i=1; i<=7;i++){
				  document.getElementById("revealanswerbutton"+i).style="color:#ffffff";
			 }
			 
		
	 }
	
	
	document.getElementById('timeout_image').style.display = "none";
	document.getElementById('clue').style.display = "none";
	
	
	document.getElementById('youranswer'+buttonid+'_msg').style.display = "none";
	
	
	document.getElementById('countdown_msg').style.display = "block"; //start ma song finish bhayepachi countdown aaucha bhancha
	
	
	document.querySelector("#player").addEventListener("ended", function(){
    	//document.getElementById("revealanswerbutton").className="enable"; //clue bajepachi balla aru button enable huncha
		countdown(); //if song ended
		 
		  document.getElementById("namethattunebutton"+buttonid).className="enable"; 
		 document.getElementById("playsongbutton"+buttonid).style.textDecoration = "line-through";
		 document.getElementById("namethattunebutton"+buttonid).style="color:#ffffff";
		
	});
	
	
	
}

function playRevealInstantly(id, payoff) {
	
		cdpause();
		
	//	document.getElementById('reveal_answer'+id).style.display = "block"; //if song ended hide reveal text
		
    	document.getElementById("dummy").innerHTML=
	"<audio id='playreveal' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		
		document.getElementById('countdown').style.display = "none";
		document.getElementById('countdown_msg').style.display = "none";
		
		
		 document.getElementById("decade"+id).style="color:yellow";
		
		document.querySelector("#playreveal").addEventListener("ended", function(){
    	
		document.getElementById('reveal_answer'+id).style.display = "none"; //if song ended hide reveal text
	});
	
	

}

function playReveal(id, payoff) {
	
		cdpause();

    	document.getElementById("dummy").innerHTML=
	"<audio id='playreveal' autoplay><source src=\""+payoff+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		
		document.getElementById('countdown').style.display = "none";
		document.getElementById('countdown_msg').style.display = "none";
		
		 
		  
	
		for(i=1; i<=7; i++){
			if(i == id){
				document.getElementById('reveal_answer'+id).style.display = "block"; //if song ended hide reveal text
				
			} else {
				document.getElementById('reveal_answer'+i).style.display = "none"; 
			}
			
			document.getElementById("playsongbutton"+i).style="color:#ABABAB";
			 document.getElementById("decade"+i).style="color:#ABABAB";
		}
		
		 document.getElementById("decade"+id).style="color:yellow";
		
		document.querySelector("#playreveal").addEventListener("ended", function(){
    	
		//document.getElementById('reveal_answer'+id).style.display = "none"; //if song ended hide reveal text
	});
	
	

}

function checkAnswer(id,song) {
	var answer = document.getElementById("youranswer"+id).value; 
	 
	
	var errText = "";    
	
	
	var textbox_correct_answer = document.getElementById("inputboxanswer"+id).value;
	var acceptable_answer = document.getElementById("acceptableanswer"+id).value;
	
	
	document.getElementById("namethattunebutton"+id).className="disable";
	
	
	//var isTimeOut = document.getElementById("timeout_image").value; 
	var displayValue = document.getElementById('timeout_image').style.display;
	if(displayValue != 'none'){
		document.getElementById('youranswer'+id).style.display = "none"; //in case if time is already finished
	
		
		//document.getElementById('submitanswer').style.display = "none"; //in case if time is already finished
		alert("Sorry! Your time is already finished");
	} else {
	
		if( answer.toLowerCase() ==  textbox_correct_answer.toLowerCase() || answer.toLowerCase() ==  acceptable_answer.toLowerCase() ){
			//alert('correct');
			<!-- add score starts here -->
			var istriedprevAndCorrect = parseInt(document.getElementById('isPrevTrialCorrect').innerHTML, 10);
			var totalcorrect =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
			
			
			if(istriedprevAndCorrect == 0){
				var newcorrect = totalcorrect + 1;			
				document.getElementById('isPrevTrialCorrect').innerHTML = 1;	//ek patak milayo bhane 1 set garcha so next time score add hunna
			} else {
				var newcorrect = totalcorrect +1 ;			
				
			}
			document.getElementById('totalCorrect').innerHTML = newcorrect;	
			var yourscore = newcorrect * 1000;
			<!-- add score ends here -->
			
				document.getElementById('youranswer'+id).style.display = "none";	 //milyo bhane input field nadekhaune
				document.getElementById('submitanswer'+id).style.display = "none";	 //milyo bhane input field nadekhaune
				
				var nextsongid = parseInt(id) + 1;//correct cha bhane arko play button enable garna laai
				document.getElementById("passthatbutton"+id).className="disable"; 
				
			
				 if( nextsongid < 8){
					  document.getElementById("playsongbutton"+nextsongid).className="enable"; 
					  document.getElementById("decade"+nextsongid).style="color:yellow";
					  document.getElementById("playsongbutton"+nextsongid).style="color:#ffffff";
					  
					  
				 } else {
						 
						 for(var i= 1;i<=7;i++){
							 document.getElementById("revealanswerbutton"+i).style="color:#ffffff";
							document.getElementById('revealanswerbutton'+i).style.display = "block";
							
							
						 }
			
					document.getElementById("nextanswerbutton").className="reveal-ans-wrap";
					document.getElementById('nextanswerbutton').style.display = "block";
					
					document.getElementById("viewoverallscore").className="overall-score-wrap";
					document.getElementById('viewoverallscore').style.display = "block"; 
				 }
				 
			 	
				  document.getElementById("namethattunebutton"+id).style="color:#ABABAB";
				  document.getElementById("passthatbutton"+id).style="color:#ABABAB";
				  document.getElementById("playsongbutton"+id).style="color:#ABABAB";
				  document.getElementById("decade"+id).style="color:#ABABAB";
				 
			
			document.getElementById('answer_image'+id).style.display = "block";
			document.getElementById('answer_image'+id).innerHTML = '<img  src="images/correct.png"  alt=" " />';
			
			document.getElementById('yourscore').style.display = "block";
			document.getElementById('yourscore').innerHTML = "You've won "  + yourscore + ' points';
	
		} else {
		//alert('wrong');	
		var totalcorrect =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
		var yourscore = totalcorrect * 1000; //no new current score
		
		document.getElementById("passthatbutton"+id).className="enable"; 
		
		var nextsongId = parseInt(id) + 1; 
		 if( nextsongId < 8){
			document.getElementById("playsongbutton"+nextsongId).style="color:#ffffff";
			document.getElementById("playsongbutton"+nextsongId).className="enable";  //answer wrong bhayepani next song enable huncha
		 }
		
			document.getElementById('answer_image'+id).style.display = "block";
			document.getElementById('answer_image'+id).innerHTML = '<img    src="images/incorrect.png" alt=" " />';
			
			document.getElementById('yourscore').style.display = "block";
			//document.getElementById('yourscore').innerHTML = 'Wrong! You have total '  + yourscore + ' points';
		}
		
	
			//document.getElementById("dummy").innerHTML=
//			"<audio id='playreveal' autoplay><source src=\""+song+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
//			document.getElementById('reveal_answer'+id).style.display = "block"; //if song ended hide reveal text
//			
//			
//			document.querySelector("#playreveal").addEventListener("ended", function(){
//			
//				document.getElementById('reveal_answer'+id).style.display = "none"; //if song ended hide reveal text
//				document.getElementById("nextanswerbutton").className="enable";
//		
//		
//		});
	
	}

}

function nextSong(songId, gametype) {
	//alert(songId + 1);
	var newsong = songId + 1;
	var totalcorrectTillNow =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
	
	if(newsong > 1){
		window.location.href = "play.php";
		//window.location.href = "overallscore.php?gmt="+totalcorrectTillNow;
	} else {
		window.location.replace(gametype+".php?gid="+newsong+"&t="+totalcorrectTillNow);
	}
	
}

function gotoOverallScorePage(songId, gametype) {
	//alert(songId + 1);
	var newsong = songId + 1;
	var totalcorrectTillNow =parseInt(document.getElementById('totalCorrect').innerHTML, 10);
	
	if(newsong > 1){
		
		window.location.href = "overallscore.php?gmt="+totalcorrectTillNow;
	} else {
		window.location.replace(gametype+".php?gid="+newsong+"&t="+totalcorrectTillNow);
	}
	
}


function clickAndDisableBoth(id,link) {

 	// disable subsequent clicks
	
	document.getElementById("namethattunebutton"+id).className="disable";
	document.getElementById("playsongbutton"+id).className="disable";
	document.getElementById("passthatbutton"+id).className="disable";
	
	document.getElementById("answer_image"+id).style.display = "none";
	
	document.getElementById('youranswer'+id).style.display = "none";
	document.getElementById('submitanswer'+id).style.display = "none";
	
	
	
	var newid = parseInt(id) + 1; //enable next song just after current song
	
	
	//document.getElementById("namethattunebutton"+newid).className="enable";
	document.getElementById("namethattunebutton"+id).style="color:#ABABAB";
	
	if(id == 7)
	{
			
			
		for(var i= 1;i<=7;i++){
			document.getElementById('revealanswerbutton'+i).style.display = "block";
			
			document.getElementById("nextanswerbutton").className="reveal-ans-wrap";
			document.getElementById('nextanswerbutton').style.display = "block";
			
			document.getElementById("viewoverallscore").className="overall-score-wrap";
			document.getElementById('viewoverallscore').style.display = "block";
		}
		
		
			 
	} else {
		document.getElementById("playsongbutton"+newid).className="enable";
		document.getElementById("passthatbutton"+newid).className="enable";	
	}
	
	 link.onclick = function(event) {
		
	 }
}   



</script>
<script>
    
   var CCOUNT = 31;
    
    var t, count;
    
    function cddisplay() {
        // displays time in span
		
        document.getElementById('countdown').innerHTML = count;
		//alert(count);
		 if (count == 0) {
			document.getElementById('countdown').style.display = "none";
			
			document.getElementById('timeout_image').style.display = "block";
			document.getElementById('timeout_image').innerHTML = '<img src="images/timeout.png" alt=" " />';
			
			document.getElementById("nextanswerbutton").className="reveal-ans-wrap";
			document.getElementById('nextanswerbutton').style.display = "block";
			
			document.getElementById("viewoverallscore").className="overall-score-wrap";
			document.getElementById('viewoverallscore').style.display = "block"; 
			
			
			for(var i= 1;i<=7;i++){
				document.getElementById('revealanswerbutton'+i).style.display = "block";
				document.getElementById("namethattunebutton"+i).className="disable";
				document.getElementById("passthatbutton"+i).className="disable";
				
				
				document.getElementById("decade"+i).style="color:#ABABAB";
				document.getElementById("playsongbutton"+i).style="color:#ABABAB";
				document.getElementById("namethattunebutton"+i).style="color:#ABABAB";
				document.getElementById("passthatbutton"+i).style="color:#ABABAB"; 
				document.getElementById("revealanswerbutton"+i).style="color:#ffffff";
				
			}
			
		 } 
		 
		 
    };
    
    function countdown() {
        // starts countdown
        cddisplay();
        if (count == 0) {
            // time is up
			
			
        } else {
            count--;
            t = setTimeout("countdown()", 1000);
			 
			document.getElementById('timeout_image').style.display = "none";
			
			document.getElementById('countdown').style.display = "block";
			document.getElementById('countdown_msg').style.display = "none";
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