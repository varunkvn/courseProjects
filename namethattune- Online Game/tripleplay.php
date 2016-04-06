<?php include ('header.php') ?>

<style type="text/css">
.hide{
	display:none;
}
.show{
	display:block;
	color: blue;

}



</style>

<?php include ('tripleplay/gamescripts_tp.php') ?>
<?php include ('tripleplay/answer_script_tp.php') ?>


<div id="mainbody">
<div class="startareaplay">
<h1>Triple Play </h1>
<div style="clear:both;"></div>
 
<div class="play_box">



<?php 
	 $i = $_GET['gid'];
	include ('tripleplay/song'.$i.'.php');
	
?>

<!-- edit now -->

<div class="display-song-wrapper">
<?php  if( $_GET['gid'] == '1'){ ?>

 <div id="start_description">
       <p> <?php echo htmlentities($description_at_start) ; ?></p>
        
        </div>
 
   <script >  var soundfile = "music/Triple Play/TP  Intro.mp3"; </script>
	<span id="gameInstructionAudio"></span> <!-- Game ko first song ko suruma instruction audio yehi bata play huncha  -->


<?php } ?>
<div id="my_div" class="hide">
 <div id="song_title"><h1 class="display-song-no">Song <?php echo $i; ?></h1> </div>

 <div id="reveal_answer"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer); ?> </p> </div>

   

<div class="description_homepageplayplaybox">  </div>
<div style="clear:both;"></div>

<span id="yourscore" style="display:none; font-weight: bold; font-size:30px;"> </span>
<span id="totalCorrect" style="display:none; font-weight: bold; font-size:30px;"> <?php echo $_GET['t']; ?></span>


<span id="countdown_msg"  style="display:none; ">Count Down will start right after the song</span>
<div class="clear"></div>
<span id="countdown"   style="display:none; font-weight: bold;"></span>
 <div id="timeout_image"  style="display:none;" class="timeout_image"  >  </div>
 <div id="answer_image"  style="display:none;" class="answer_image"  >  </div>


</div>
</div>
<div class="clear"></div>

<span id="dummy"></span> 
<div class="tune-btn-wrapper">
<ul class="tune-lt-btn">
<li><a href="javascript: void(0);" id="cluebutton"  class="disable" >  &nbsp; &nbsp; &nbsp; &nbsp; </a>  &nbsp; &nbsp;</li>
 <li><a href="javascript: void(0);"   id="playsongbutton" class="disable"  onclick="clickAndDisable(this), playSong('<?php echo $play_song; ?>');">  Play Song   </a> &nbsp; &nbsp; </li>
  
  <li> <a href="javascript: void(0);"    id="namethattunebutton"  class="bigbtn disable"  onclick="cdpause(), nameThatTune();">  <!-- Name That Tune --> </a>  &nbsp; &nbsp; </li>
</ul>
<ul class="tune-rt-btn">
    <li> <a href="javascript: void(0);" id="revealanswerbutton"  class="disable"    onclick="revealAnswer(),  playReveal('<?php echo $play_reveal1;?>','<?php echo $play_reveal2;?>');"> Reveal Answer  </a>
&nbsp; &nbsp;</li>
     <li> <a href="javascript: void(0);"  id="nextanswerbutton" class="disable"   onclick="nextSong(<?php echo $i; ?>,'tripleplay');">  Next </a> &nbsp; &nbsp;</li>
 </ul>
</div>

<div id="user_answer"  class="inputfieldshere">

<fieldset style="width:238px;">
	<input type="text"  class="inputfield1"  onFocus="clearAnswerMsgRingBell();" onMouseOver="clearAnswerMsgRingBell();"    id="youranswer" name="youranswer" value="" style="display:none">
    <div id="youranswer_msg" name="youranswer_msg"> </div>
    
   <?php echo $artist_dropdown; ?>
    <div id="artist_msg" name="artist_msg"> </div>
    
    <?php $decade_dropdown = '<select id="selectDecade" name="selectDecade" style="display:none">
	<option value="50s">1950\'s</option>
	<option value="60s">1960\'s</option>
	<option value="70s">1970\'s</option>
	<option value="80s">1980\'s</option>
	<option value="90s">1990\'s</option>
	<option value="00s">2000\'s</option>
	<option value="10s">2010\'s</option>
	</select>';
 ?>
 
    <?php echo $decade_dropdown; ?>
    
  <div id="decade_msg" name="decade_msg"> </div>
    
     <div style="text-align:center; margin: 0 0 0 60px">  
    <input type="submit" id="submitanswer" name="submitanswer" onClick="return validateForm('<?php echo $after_submit1; ?>','<?php echo $after_submit2; ?>');" value="Submit Answer"  style="display:none; margin:0px;" />
    </div>
    
     <input type="hidden" id="inputboxanswer" name="inputboxanswer" value="<?php echo $correct_answer; ?>" />
      <input type="hidden" id="acceptableanswer" name="acceptableanswer" value="<?php echo $acceptable_answer; ?>" />
      
    <input type="hidden" id="artistanswer" name="artistanswer" value="<?php echo $correct_artist; ?>" />
    <input type="hidden" id="decadeanswer" name="decadeanswer" value="<?php echo $correct_decade;?>" />
    </fieldset>
   

    
    
  
    
    <span id="answer_audio"></span> 

</div>

</div>
<!-- edit end -->



<?php $firsttrial = 0; ?>

<span id="isPrevTrialCorrect" style="display:none; font-weight: bold; font-size:30px;"><?php echo $firsttrial; ?></span> <!-- suruma PrevTrial zero huncha -->


<?php 

	include ('game_footer.php');	
?>