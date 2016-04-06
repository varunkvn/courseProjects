<?php session_start(); //it must come  BEFORE the <html> tag ?>
<?php  
	
	$_SESSION['there_is_session'] = 0;
	if(isset($_GET['rtbt'])) { // id index exists
		$_SESSION['rtbt'] = $_GET['rtbt'];
		$_SESSION['there_is_session'] = 1; 
	}
	
	if(isset($_GET['tpt'])) { // id index exists
		$_SESSION['tpt'] = $_GET['tpt'];
		$_SESSION['there_is_session'] = 1; 
	}
	
	if(isset($_GET['ttt'])) { // id index exists
		$_SESSION['ttt'] = $_GET['ttt'];
		$_SESSION['there_is_session'] = 1; 
	}
	
	if(isset($_GET['bant'])) { // id index exists
		$_SESSION['bant'] = $_GET['bant'];
		$_SESSION['there_is_session'] = 1; 
	}
	
	if(isset($_GET['gmt'])) { // id index exists
		$_SESSION['gmt'] = $_GET['gmt'];
		$_SESSION['there_is_session'] = 1; 
	}




?>
<?php include ('header.php') ?>
<audio autoplay>
  
  <source src="music/homepage/NTT Theme.mp3" type="audio/mpeg">
  
</audio>
<div id="mainbody"  >

<div class="startareaplay">
            <h1>Select a Game:</h1>
            <div style="clear:both;"></div>

 <h2>
		   <a href="ringbell.php?gid=1&t=0" class="ringthatbell"></a>
           <a href="tunetopics.php?gid=1&t=0" class="tunetopics"></a>
           <a href="tripleplay.php?gid=1&t=0" class="tripleplay"></a>
           <a href="bidanote.php?gid=1&t=0" class="bidanote"></a>
           <a href="goldenmedley.php?gid=1&t=0" class="goldenmedleyshowdown"></a>
</h2>


 </div>
        <div style="clear:both;"></div>
      <!--  <div class="description_homepageplay">
        	
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. In at arcu ac orci lobortis porta. Fusce ut diam luctus, euismod eros sit amet, fermentum risus. Curabitur justo nisi, varius eu interdum sodales, ullamcorper vel ligula. Quisque varius tempor semper. Cras luctus libero quam, quis posuere metus tristique in. Nullam sed ornare odio. Sed sit amet consequat sem. Morbi interdum arcu nisi, et pretium sem dictum fermentum. Duis rutrum accumsan tortor, vitae mattis leo gravida non. Nunc tellus augue, ullamcorper eget accumsan a, ornare ut nisl.</p>
            
       	</div>-->

</div>



<?php include ('footer.php') ?>

