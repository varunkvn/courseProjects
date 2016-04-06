<script type="text/javascript" language="javascript">
<?php  if( $_GET['gid'] == '1'){ ?>
	window.onload=function()  //executes when the page finishes loading
	{
		
		
		
		document.getElementById("playsongbutton").className="enable"; //if ring bell ko first game ho bhane clue enable garna laai
		cdreset(); //page reload huna sath countdown timer reset huncha
		
		document.getElementById("gameInstructionAudio").innerHTML=
		"<audio id='welcomeringbell' autoplay><source src=\""+soundfile+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
		
		document.querySelector("#welcomeringbell").addEventListener("ended", function(){
		// done playing
	  
		func1(); //if song ended
		  cdreset(); //page reload huna sath countdown timer reset huncha
		});
		
		
	};
<?php  } else { ?>
	window.onload=function() 
	{
		
		func2();  //if it is not first song do not play song and show menu instantly.
		//setTimeout(func1, 1000); 
		  cdreset(); //page reload huna sath countdown timer reset huncha
		  
		
		  func3();
		  
		
		 
	};
<?php  } ?>

</script>

<script> 
function func1()
{
	document.getElementById("my_div").className="show";	
	document.getElementById("start_description").className="hide";
	document.getElementById("playsongbutton").className="enable";
}
function func2()
{
	document.getElementById("my_div").className="show";	
	document.getElementById("playsongbutton").className="enable";
	
}



function clickAndDisable(link) {

 	// disable subsequent clicks
	 link.onclick = function(event) {
		
	 }
}   

function func3(){
		 document.getElementById('playsongbutton').click();
}


function clearAnswerMsg(id) {
	//alert(id);
	document.getElementById('youranswer'+id+'_msg').innerHTML = '';
}



function clearAnswerMsgRingBell() {
	document.getElementById('youranswer_msg').innerHTML = '';
}



</script>
