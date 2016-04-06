<?php  include ('header.php') ?>

<style type="text/css">
.hide{
	display:none;
}
.show{
	display:block;
	color: blue;
	
}
.inputfieldshere{
	
	margin-top: 22px;
	margin-left: 125px;
}
</style>

<?php include ('bidanote/gamescripts_ban.php') ?>
<?php include ('bidanote/answer_script_bn.php') ?>


<div id="mainbody">
<div class="startareaplay">

<h1>Bid a Note</h1>
  <div style="clear:both;"></div>
 
<div class="play_box">

<?php 
	 $i = $_GET['gid'];
	include ('bidanote/song'.$i.'.php'); 
	
	?>

<!-- edit now -->

<div class="display-song-wrapper">
<?php  if( $_GET['gid'] == '1'){ ?>
 <div id="start_description">
            <p> <?php echo htmlentities($description_at_start) ; ?></p>
        
</div>   
    <script >  var soundfile = "music/Bid A Note/BAN - Intro.mp3"; </script>
	<span id="gameInstructionAudio"></span> <!-- Game ko first song ko suruma instruction audio yehi bata play huncha  -->


<?php } ?>
    <div id="my_div" class="hide">
    <div id="song_title"><h1 class="display-song-no">Song <?php echo $i; ?></h1> </div>
   
    <div id="clue"  style="display:none;" class="answer_list"  ><p><?php echo htmlentities($song_clue); ?></p></div>

    <div id="reveal_answer"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer); ?> </p> </div>
   

<div class="description_homepageplayplaybox"> 



 </div>
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
    <li><a href="javascript: void(0);" id="cluebutton"  class="disable" onclick="clickAndDisable(this), showClueDiv(), playClue('<?php echo $clue_song; ?>');"> Clue </a>  &nbsp; &nbsp;</li>
    <li><a href="javascript: void(0);" id="playsongbutton" class="disable"  onclick="clickAndDisable(this), playSong('<?php echo $play_song; ?>');"> <!-- song ko extension dynamically aaucha playsong function ma -->  Play Song   </a> &nbsp; &nbsp;</li>
    
    <li><a  href="javascript: void(0);"    id="namethattunebutton"  class="bigbtn disable"  onclick="cdpause(),  nameThatTune();">  <!-- Name That Tune --></a>  &nbsp; &nbsp;</li>
</ul>
<ul class="tune-rt-btn">
   <li> <a href="javascript: void(0);" id="revealanswerbutton"  class="disable"  onclick="revealAnswer(), playReveal('<?php echo $play_reveal1; ?>' , '<?php echo $play_reveal2; ?>');"> Reveal Answer  </a> </li>
   <li> <a href="javascript: void(0);"  id="nextanswerbutton" class="disable"  onclick="nextSong(<?php echo $i; ?>,'bidanote');">  Next </a> &nbsp; &nbsp; </li>
    </ul>
</div>


<div id="user_answer"  class="inputfieldshere">

	<fieldset>
	<input type="text"   class="inputfield1"  onFocus="clearAnswerMsgRingBell();" onMouseOver="clearAnswerMsgRingBell();"    id="youranswer" name="youranswer" value="" style="display:none"  onkeydown="if (event.keyCode == 13) document.getElementById('submitanswer').click()">
     <div id="youranswer_msg" name="youranswer_msg"> </div>
     
      <div style="clear:both;"></div>
     
     <div id="selectBid" style="display:none; " >
    <div id="bid_select" >Place Your Bid</div>
    <select id="selectSecond" name="selectSecond"  onChange="enablePlayButtonNow(this);">
            <option value="">--Number Of Seconds--</option>
            <option value="1">1 second</option>
            <option value="2">2 seconds</option>
            <option value="3">3 seconds</option>
            <option value="4">4 seconds</option>
            <option value="5">5 seconds</option>
            <option value="6">6 seconds</option>
            <option value="7">7 seconds</option>
        </select>
     </div>
 
     <div style="text-align:center; margin: 0 0 0 60px">  
    <input type="submit" id="submitanswer" name="submitanswer" onClick="return validateForm('<?php echo $after_submit1;?>' , '<?php echo $after_submit2; ?>');" value="Submit Answer"  style="display:none;  margin: 0 auto;" />
     
    </div>
    
    <input type="hidden" id="inputboxanswer" name="inputboxanswer" value="<?php echo $correct_answer; ?>" />
     <input type="hidden" id="acceptableanswer" name="acceptableanswer" value="<?php echo $acceptable_answer; ?>" />
     
     
     <input type="hidden" id="acceptableanswer2" name="acceptableanswer2" value="<?php echo $acceptable_answer2; ?>" />
   
</fieldset>
    
    
    
    
    

    <span id="answer_audio"></span> 
</div>


<!-- edit now end -->

<?php $firsttrial = 0; ?>

<span id="isPrevTrialCorrect" style="display:none; font-weight: bold; font-size:30px;"><?php echo $firsttrial; ?> </span> <!-- suruma PrevTrial zero huncha -->




<?php 

	include ('game_footer.php');
	

?>

