<?php session_start(); //it must come  BEFORE the <html> tag ?>
<?php session_destroy(); ?>
<?php include ('header.php') ?>
<style>

.footer {
    color: #FFFFFF;
    font-family: Verdana,Geneva,sans-serif;
    font-size: 12px;
    margin-top: 10px;
    padding-top: 110px;
    text-align: center;
}

</style>
<script>

function playWelcomeSongandNTT(homesong, nttthemesong) {
	document.getElementById("dummy").innerHTML=
		"<audio id='playpayoff' autoplay><source src=\""+homesong+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
	
		document.querySelector("#playpayoff").addEventListener("ended", function(){
			document.getElementById("dummy").innerHTML=
			"<audio id='playreveal' autoplay><source src=\""+nttthemesong+"\" type=\"audio/mpeg\" autostart=\"true\" loop=\"false\" /></audio>";
			
		
		});
	
	

}


window.onload=function()  //executes when the page finishes loading
{
	playWelcomeSongandNTT('music/homepage/home.mp3','music/homepage/NTT Theme.mp3')
}

</script>
<div id="mainbody" >


<!--<audio autoplay>
  
  <source src="music/homepage/home.mp3" type="audio/mpeg">
  
</audio>-->
<span id="dummy"></span> 



	
    
    	<div class="startarea">
            <a href="play.php" class="startbtn"></a>
            <p>
                <a href="history.php">HISTORY</a> •  <a href="contact.php">CONTACT</a>
            </p>
        </div>
        
        <div class="description_homepage">
        	
            <p style="font-size:16px; font-family:Verdana, Geneva, sans-serif;">Welcome to “Name That Tune”, one of the greatest game shows in television 

history. We’re here to test your knowledge of songs, rack up some points and 

have some fun. Get ready to NAME THAT TUNE!</p>
            
   
    
    </div>
  
</div>
   <?php include ('footer.php') ?>

