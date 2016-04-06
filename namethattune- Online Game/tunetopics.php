<?php  include ('header.php') ?>
<style type="text/css">
.hide{
	display:none;
}
.show{
	display:block;
	color: blue;
	
}


ul.tune-lt-btn li {/* Play medley button milauna*/
    display: inline-block;
    float: left;
    height: 84px;
    margin: 0 1px 0 -4px; 
}

</style>

<?php include ('gamescripts.php') ?>
<?php include ('tunetopics/answer_script_tt'.$_GET['gid'].'.php') ?>


<div id="mainbody">
<div class="startareaplay">
            <h1>Tune Topics</h1>
            <div style="clear:both;"></div>
 
<div class="play_box">

<?php 
	 $i = $_GET['gid'];
	include ('tunetopics/song'.$i.'.php'); 
	?>


<div class="display-song-wrapper">
<?php  if( $_GET['gid'] == '1'){ ?>

<div id="start_description"> 
   <p> <?php echo htmlentities($description_at_start) ; ?></p>
</div>

    <script >  var soundfile = "music/Tune Topics/TT Intro.mp3"; </script>
	<span id="gameInstructionAudio"></span> <!-- Game ko first song ko suruma instruction audio yehi bata play huncha  -->


<?php } ?>

   <div id="my_div" class="hide">
   <div id="song_title"><h1 class="display-song-no">Medley <?php echo $i; ?></h1> </div>
   
    <div id="clue"  style="display:none;" class="answer_list"  ><p><?php echo htmlentities($song_clue); ?></p></div>

    <div id="reveal_answer"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer); ?> </p> </div>
   

<div class="description_homepageplayplaybox">  </div>
<div style="clear:both;"></div>

<span id="yourscore" style="display:none;font-weight: bold; font-size:30px;"> </span>

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
    <li><a href="javascript: void(0);" id="cluebutton"   class="disable"   onclick="clickAndDisable(this), showClueDiv(), playClue('<?php echo $clue_song; ?>');"> Clue </a>  &nbsp; &nbsp; </li>
   <li> <a href="javascript: void(0);"  id="playsongbutton" class="disable"     onclick="clickAndDisable(this), playSong('<?php echo $play_song; ?>');">Play Medley</a> &nbsp; &nbsp;</li>
   <li> <a  href="javascript: void(0);"   class="disable" id="namethattunebutton" onclick="cdpause(), nameThatTune();">  </a>  &nbsp; &nbsp;</li>
  </ul>
     <ul class="tune-rt-btn">

   <li> <a href="javascript: void(0);"  id="revealanswerbutton"  class="disable"  onclick="revealAnswer(), playReveal('<?php echo $play_reveal1; ?>');"> Reveal Answer  </a> &nbsp; &nbsp;</li>
    <li><a href="javascript: void(0);" id="nextanswerbutton" class="disable"  onclick="nextSong(<?php echo $i; ?>,'tunetopics');">  Next </a> &nbsp; &nbsp;
    </li>
    </ul>
    </h2>

</div>


<div id="user_answer"  class="inputfieldshere">

<fieldset class="tunetopic-input-wrap">
	<input type="text"   class="inputfield1" onFocus="clearAnswerMsg(1);" onMouseOver="clearAnswerMsg(1);"  id="youranswer1" name="youranswer1" value="" style="display:none">    
 <div id="youranswer1_msg" name="youranswer1_msg"> </div>
  <?php  if( $_GET['gid'] == '1'){ ?>
    <input type="text"   class="inputfield1"  onFocus="clearAnswerMsg(2);"  onMouseOver="clearAnswerMsg(2);" id="youranswer2"  name="youranswer2" value="" style="display:none"  onkeydown="if (event.keyCode == 13) document.getElementById('submitanswer').click()"> <!-- first game ho bhane second input ma enter click garda submit answer huncha -->
     <div id="youranswer2_msg" name="youranswer2_msg"> </div>   
   <?php } ?>
     <?php  if( $_GET['gid'] == '2'){ ?>
      <input type="text"   class="inputfield1"  onFocus="clearAnswerMsg(2);"  onMouseOver="clearAnswerMsg(2);" id="youranswer2"  name="youranswer2" value="" style="display:none">
     <div id="youranswer2_msg" name="youranswer2_msg"> </div>   <!-- second game ho bhane second input ma enter click garda submit answer hudaina -->
     
     	<input type="text"   class="inputfield1" onFocus="clearAnswerMsg(3);" onMouseOver="clearAnswerMsg(3);"  id="youranswer3" name="youranswer3" value="" style="display:none">    
 <div id="youranswer3_msg" name="youranswer4_msg"> </div>
    <input type="text"   class="inputfield1"  onFocus="clearAnswerMsg(4);"  onMouseOver="clearAnswerMsg(4);" id="youranswer4"  name="youranswer4" value="" style="display:none"  onkeydown="if (event.keyCode == 13) document.getElementById('submitanswer').click()">
     <div id="youranswer4_msg" name="youranswer4_msg"> </div>   
     
     <?php } ?>
     <div style="clear:both;"></div>  
    <input type="submit" id="submitanswer" name="submitanswer" onClick="return validateForm('<?php echo $after_submit1; ?>');" value="Submit Answer"  style="display:none" />    
    <input type="hidden" id="inputboxanswer1" name="inputboxanswer1" value="<?php echo $correct_answer1; ?>" />
    <input type="hidden" id="inputboxanswer2" name="inputboxanswer2" value="<?php echo $correct_answer2; ?>" />
    
     <input type="hidden" id="acceptableanswer1" name="acceptableanswer1" value="<?php echo $acceptable_answer1; ?>" />
      <input type="hidden" id="acceptableanswer2" name="acceptableanswer2" value="<?php echo $acceptable_answer2; ?>" />
     
    
     <?php  if( $_GET['gid'] == '2'){ ?>
     	<input type="hidden" id="inputboxanswer3" name="inputboxanswer3" value="<?php echo $correct_answer3; ?>" />
    <input type="hidden" id="inputboxanswer4" name="inputboxanswer4" value="<?php echo $correct_answer4; ?>" />
    
    <input type="hidden" id="acceptableanswer3" name="acceptableanswer3" value="<?php echo $acceptable_answer3; ?>" />
      <input type="hidden" id="acceptableanswer4" name="acceptableanswer4" value="<?php echo $acceptable_answer4; ?>" />
     
     <?php } ?>
     
    </fieldset>

 
     
    
  
    <span id="answer_audio"></span> 
</div>



<?php $firsttrial = 0; ?>

<span id="isPrevTrialCorrect" style="display:none; font-weight: bold; font-size:30px;"><?php echo $firsttrial; ?> </span> <!-- suruma PrevTrial zero huncha -->
<?php 

	include ('game_footer.php');
	

?>
