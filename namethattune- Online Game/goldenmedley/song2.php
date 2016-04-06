<?php $reveal_answer1= " That was “Rock Around The Clock” by Bill Haley & The Comets from 1955. "; ?>
<?php $reveal_answer2= " That was “Louie, Louie” by The Kingsman from 1963. "; ?>
<?php $reveal_answer3= "Although everyone called it the “Pina Colada” song, the correct title was “Escape”  "; ?> 
<?php $reveal_answer4= "That was “Livin’ On A Prayer” by Bon Jovi from 1986. "; ?>
<?php $reveal_answer5= " That was “Hold On” by Wilson Phillips from 1990."; ?> 
<?php $reveal_answer6= " That was “Hollaback Girl” by Gwen Stefani from 2005."; ?>
<?php $reveal_answer7= " That was “Need You Now” by Lady Antebellum from 2010."; ?>


   
   <div id="my_div"  class="hide"> <!--<h1>Golden Medley <?php echo $i; ?></h1>-->
  <span id="dummy"></span> 
   
   
 
         <div style="clear:both;"></div>
         
      <div class="description_homepageplayplaybox_golden">
      <div class="menuformedley">
      <table cellspacing="0">
      	<tbody>
        	<tr>
            	<th colspan="5">
                    <span id="yourscore" style="display:none; font-weight: bold; font-size:30px;"> </span>
                    <span id="totalCorrect" style="display:none; font-weight: bold; font-size:30px;">0</span>
                    
                    <span id="countdown_msg"  style="display:none; font-weight: bold;">Count Down will start right after the song</span>
                    <span id="countdown"   style="display:none; font-weight: bold;"></span>
                    
                    <div id="timeout_image"  style="display:none;" class="timeout_image"  >  </div>
                    
					<div id="clue"  style="display:none;" class="answer_list"  > Medley 1 Clue here: While this tune from 1994..... name that tune</div>
                    
                <div id="reveal_answer1"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer1); ?> </p>  	</div>
                <div id="reveal_answer2"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer2); ?> </p>  	</div>
                <div id="reveal_answer3"  style="display:none;" class="reveal_answer"  ><p><?php echo htmlentities($reveal_answer3); ?> </p></div>
                <div id="reveal_answer4"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer4); ?> </p></div>
                <div id="reveal_answer5"  style="display:none;" class="reveal_answer"  ><p><?php echo htmlentities($reveal_answer5); ?> </p></div>
                <div id="reveal_answer6"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer6); ?> </p>	</div>
                <div id="reveal_answer7"  style="display:none;" class="reveal_answer"  > <p><?php echo htmlentities($reveal_answer7); ?> </p></div>
                
                </th>
            </tr>
        	<tr>
            	<td> <span id="decade1">  1950's</span></td>
                <td> <a href="javascript: void(0);"  id="playsongbutton1"      onclick="clickAndDisable(this), playSong('1','music/Golden Medley Showdown/GMS-1 Rock Around The Clock/rock around the clock (clip).mp3');">  Play Song   </a> &nbsp; &nbsp;</td>
                <td><a href="javascript: void(0);"    id="namethattunebutton1"  class="disable"  onclick="cdpause(), nameThatTune(1);">  Name That Tune </a>  &nbsp; &nbsp;
     <div id="answer_image1"  style="display:none;" class="answer_image"  >  </div>
    <div id="youranswer1_msg" name="youranswer1_msg"> </div>
    <fieldset>
    <input type="text" id="youranswer1" name="youranswer1" value=""  style="display:none"> 
    <input type="submit" id="submitanswer1" name="submitanswer1" onClick="return validateForm('1','music/Golden Medley Showdown/GMS-1 Rock Around The Clock/GMS-1 50s Answer.mp3');" value="Submit"  style="display:none" />
    </fieldset>
                </td>
                <td> <a href="javascript: void(0);"    id="passthatbutton1"   onclick="cdpause(), clickAndDisableBoth('1',this), playSong('2','music/Golden Medley Showdown/GMS-2 Louie, Louie/louie louie (clip).mp3');"> Pass </a>  &nbsp; &nbsp;</td>
                <td> <a href="javascript: void(0);"  id="revealanswerbutton1" style="display:none;"  onclick=" playReveal('1', 'music/Golden Medley Showdown/GMS-1 Rock Around The Clock/GMS-1 50s Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp; </td>
            </tr>
                      
             <tr>
            	<td>  <span id="decade2">  1960's</span></td>
                <td> <a href="javascript: void(0);"  id="playsongbutton2"   class="disable"    onclick="clickAndDisable(this), playSong('2','music/Golden Medley Showdown/GMS-2 Louie, Louie/louie louie (clip).mp3');">  Play Song   </a> &nbsp; &nbsp; </td>
                <td> <a href="javascript: void(0);"    id="namethattunebutton2" class="disable"    onclick="cdpause(), nameThatTune(2);">  Name That Tune </a>  &nbsp; &nbsp;
                	<div id="answer_image1"  style="display:none;" class="answer_image"  >  </div>
    <div id="answer_image2"  style="display:none;" class="answer_image"  >  </div> 
     <div id="youranswer2_msg" name="youranswer2_msg"> </div>  
     <fieldset>
<input type="text" id="youranswer2" name="youranswer2" value=""  style="display:none">
    <input type="submit" id="submitanswer2" name="submitanswer2" onClick="return validateForm('2','music/Golden Medley Showdown/GMS-2 Louie, Louie/GMS-2 60s Answer.mp3');" value="Submit"  style="display:none" />
</fieldset></td>
                </td>
              <td> <a href="javascript: void(0);"    id="passthatbutton2" class="disable"    onclick="cdpause(), clickAndDisableBoth('2',this), playSong('3','music/Golden Medley Showdown/GMS-3 Escape/escape (clip).mp3');"> Pass </a>  &nbsp; &nbsp;</td><!--pass garda next song bajna paryo -->
               <td> <a href="javascript: void(0);"  id="revealanswerbutton2" style="display:none;"  onclick=" playReveal('2', 'music/Golden Medley Showdown/GMS-2 Louie, Louie/GMS-2 60s Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp;</td>
            </tr>
			
        <tr>
         <td>   <span id="decade3"> 1970's</span></td>
                <td> <a href="javascript: void(0);"  id="playsongbutton3"   class="disable"    onclick="clickAndDisable(this), playSong('3','music/Golden Medley Showdown/GMS-3 Escape/escape (clip).mp3');">  Play Song   </a> &nbsp; &nbsp; </td>
    
    <td><a href="javascript: void(0);"    id="namethattunebutton3" class="disable"    onclick="cdpause(), nameThatTune(3);">  Name That Tune </a>  &nbsp; &nbsp; 
    <div id="answer_image3"  style="display:none;" class="answer_image"  >  </div> 
    <div id="youranswer3_msg" name="youranswer3_msg"> </div>  
    <fieldset>
<input type="text" id="youranswer3" name="youranswer3" value=""  style="display:none">
    <input type="submit" id="submitanswer3" name="submitanswer3" onClick="return validateForm('3','music/Golden Medley Showdown/GMS-3 Escape/GMS-3 70s Answer.mp3');" value="Submit"  style="display:none" />
</fieldset></td>
    <td><a href="javascript: void(0);"    id="passthatbutton3" class="disable"    onclick="cdpause(), clickAndDisableBoth('3',this), playSong('4','music/Golden Medley Showdown/GMS-4 Livin On A Prayer/livin on a prayer (clip).mp3');"> Pass </a>  &nbsp; &nbsp;<!--pass garda next song bajna paryo -->   </td>
    
    <td><a href="javascript: void(0);"  id="revealanswerbutton3" style="display:none;"  onclick=" playReveal('3', 'music/Golden Medley Showdown/GMS-3 Escape/GMS-3 70s Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp;</td>
    
            </tr>
            
    		<tr>
    <td><span id="decade4">  1980's</span></td>
    <td>  <a href="javascript: void(0);"  id="playsongbutton4"   class="disable"    onclick="clickAndDisable(this), playSong('4','music/Golden Medley Showdown/GMS-4 Livin On A Prayer/livin on a prayer (clip).mp3');">  Play Song   </a> &nbsp; &nbsp; </td>
    
    <td> <a href="javascript: void(0);"    id="namethattunebutton4" class="disable"    onclick="cdpause(), nameThatTune(4);">  Name That Tune </a>  &nbsp; &nbsp; 
    <div id="answer_image4"  style="display:none;" class="answer_image"  >  </div> 
    <div id="youranswer4_msg" name="youranswer4_msg"> </div>  
    <fieldset>
<input type="text" id="youranswer4" name="youranswer4" value=""  style="display:none">
    <input type="submit" id="submitanswer4" name="submitanswer4" onClick="return validateForm('4','music/Golden Medley Showdown/GMS-4 Livin On A Prayer/GMS-4 80s Answer.mp3');" value="Submit"  style="display:none" />
</fieldset></td>
    <td> <a href="javascript: void(0);"    id="passthatbutton4" class="disable"    onclick="cdpause(), clickAndDisableBoth('4',this), playSong('5','music/Golden Medley Showdown/GMS-5 Hold On/hold on (clip).mp3');"> Pass </a>  &nbsp; &nbsp;<!--pass garda next song bajna paryo -->   </td>
    
    <td><a href="javascript: void(0);"  id="revealanswerbutton4" style="display:none;"  onclick=" playReveal('4', 'music/Golden Medley Showdown/GMS-4 Livin On A Prayer/GMS-4 80s Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp;</td>
    
    
     </tr>
            
            <tr>
           
         <td>  <span id="decade5"> 1990's</span></td> 
          
        <td> <a href="javascript: void(0);"  id="playsongbutton5"   class="disable"    onclick="clickAndDisable(this), playSong('5','music/Golden Medley Showdown/GMS-5 Hold On/hold on (clip).mp3');">  Play Song   </a> &nbsp; &nbsp; </td>
        
        <td> <a href="javascript: void(0);"    id="namethattunebutton5" class="disable"    onclick="cdpause(), nameThatTune(5);">  Name That Tune </a>  &nbsp; &nbsp;
        <div id="answer_image5"  style="display:none;" class="answer_image"  >  </div> 
        <div id="youranswer5_msg" name="youranswer5_msg"> </div>  
       <fieldset>
 <input type="text" id="youranswer5" name="youranswer5" value=""  style="display:none">
        <input type="submit" id="submitanswer5" name="submitanswer5" onClick="return validateForm('5','music/Golden Medley Showdown/GMS-5 Hold On/GMS-5 90s Answer.mp3');" value="Submit"  style="display:none" />
</fieldset> </td>
        <td> <a href="javascript: void(0);"    id="passthatbutton5" class="disable"    onclick="cdpause(), clickAndDisableBoth('5',this), playSong('6','music/Golden Medley Showdown/GMS-6 Hollaback Girl/hollaback girl (clip).mp3');"> Pass </a>  &nbsp; &nbsp;<!--pass garda next song bajna paryo -->  </td>
        
        <td> <a href="javascript: void(0);"  id="revealanswerbutton5" style="display:none;"  onclick=" playReveal('5', 'music/Golden Medley Showdown/GMS-5 Hold On/GMS-5 90s Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp;</td>
       
        
      </tr>
			
            <tr>
        <td><span id="decade6">   2000's</span></td> 
    
        <td>    <a href="javascript: void(0);"  id="playsongbutton6"   class="disable"    onclick="clickAndDisable(this), playSong('6','music/Golden Medley Showdown/GMS-6 Hollaback Girl/hollaback girl (clip).mp3');">  Play Song   </a> &nbsp; &nbsp;</td>
        
        <td> <a href="javascript: void(0);"    id="namethattunebutton6" class="disable"    onclick="cdpause(), nameThatTune(6);">  Name That Tune </a>  &nbsp; &nbsp;
        <div id="answer_image6"  style="display:none;" class="answer_image"  >  </div> 
        <div id="youranswer6_msg" name="youranswer6_msg"> </div>  
       <fieldset>
 <input type="text" id="youranswer6" name="youranswer6" value=""  style="display:none">
        <input type="submit" id="submitanswer6" name="submitanswer6" onClick="return validateForm('6','music/Golden Medley Showdown/GMS-6 Hollaback Girl/GMS-6 2000s Answer.mp3');" value="Submit"  style="display:none" />
</fieldset></td>
        <td>   <a href="javascript: void(0);"    id="passthatbutton6" class="disable"    onclick="cdpause(), clickAndDisableBoth('6',this), playSong('7','music/Golden Medley Showdown/GMS-7 Need You Now/need you now (clip).mp3');"> Pass </a>  &nbsp; &nbsp;<!--pass garda next song bajna paryo -->  </td>
        
        <td> <a href="javascript: void(0);"  id="revealanswerbutton6" style="display:none;"  onclick=" playReveal('6', 'music/Golden Medley Showdown/GMS-6 Hollaback Girl/GMS-6 2000s Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp;</td>
     
        
    </tr>
			
     <tr>
    <td><span id="decade7"> 1910's</span></td> 
    
    <td> <a href="javascript: void(0);"  id="playsongbutton7"   class="disable"    onclick="clickAndDisable(this), playSong('7','music/Golden Medley Showdown/GMS-7 Need You Now/need you now (clip).mp3');">  Play Song   </a> &nbsp; &nbsp;</td>
    <td>  <a href="javascript: void(0);"    id="namethattunebutton7" class="disable"    onclick="cdpause(), nameThatTune(7);">  Name That Tune </a>  &nbsp; &nbsp;
    <div id="answer_image7"  style="display:none;" class="answer_image"  >  </div>
   <div id="youranswer7_msg" name="youranswer7_msg"> </div>  
    <fieldset>
<input type="text" id="youranswer7" name="youranswer7" value=""  style="display:none">
    <input type="submit" id="submitanswer7" name="submitanswer7" onClick="return validateForm('7','music/Golden Medley Showdown/GMS-7 Need You Now/GMS-7 2010s Answer.mp3');" value="Submit"  style="display:none" />
</fieldset></td>
    <td> <a href="javascript: void(0);"    id="passthatbutton7" class="disable"    onclick="cdpause(), clickAndDisableBoth('7',this), playSong('7','music/Golden Medley Showdown/GMS-7 Need You Now/need you now (clip).mp3');"> Pass </a>  &nbsp; &nbsp;<!--pass garda next song bajna paryo -->  </td>
    <td> <a href="javascript: void(0);"  id="revealanswerbutton7" style="display:none;"  onclick=" playReveal('7', 'music/Golden Medley Showdown/GMS-7 Need You Now/GMS-7 2010s Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp;</td>
  
   
    </tr>
            
        </tbody>
      </table>
      
      </div>
      
  <div class="clear"></div>
    
    
    <!-- <a href="javascript: void(0);"  id="revealanswerbutton" style="display:none;"  onclick=" playReveal('', 'music/Golden Medley Showdown/GMS-1 Rock Around The Clock/TT-1 Answer.mp3');"> Reveal Answer  </a> &nbsp; &nbsp;
    <a href="javascript: void(0);" id="nextanswerbutton" style="display:none;" onclick="nextSong(<?php echo $i; ?>,'tunetopics');">  Next </a> &nbsp; &nbsp;-->
</div>

<div id="user_answer" >
	
     
        
    <input type="hidden" id="inputboxanswer1" name="inputboxanswer1" value="Rock Around The Clock" />
    <input type="hidden" id="inputboxanswer2" name="inputboxanswer2" value="Louie, Louie" />
    <input type="hidden" id="inputboxanswer3" name="inputboxanswer3" value="Escape" />
    <input type="hidden" id="inputboxanswer4" name="inputboxanswer4" value="Livin' On A Prayer" />
    <input type="hidden" id="inputboxanswer5" name="inputboxanswer5" value="Hold On" />
    <input type="hidden" id="inputboxanswer6" name="inputboxanswer6" value="Hollaback Girl" />
    <input type="hidden" id="inputboxanswer7" name="inputboxanswer7" value="Need You Now" />
    
    <input type="hidden" id="acceptableanswer1" name="acceptableanswer1" value="Rock Around The Clock" />
    <input type="hidden" id="acceptableanswer2" name="acceptableanswer2" value="Louie Louie" />
    <input type="hidden" id="acceptableanswer3" name="acceptableanswer3" value="Escape" />
    <input type="hidden" id="acceptableanswer4" name="acceptableanswer4" value="Living On A Prayer" />
    <input type="hidden" id="acceptableanswer5" name="acceptableanswer5" value="Hold On" />
    <input type="hidden" id="acceptableanswer6" name="acceptableanswer6" value="Hollenback Girl" />
    <input type="hidden" id="acceptableanswer7" name="acceptableanswer7" value="I Need You Now" />
     
    
    
    
    <span id="answer_audio"></span> 
</div>


<a href="javascript: void(0);" id="nextanswerbutton" style="display:none;" onclick="nextSong(<?php echo $i; ?>,'goldenmedley');">  Next </a> &nbsp; &nbsp;
