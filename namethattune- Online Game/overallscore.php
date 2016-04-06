<?php session_start(); //it must come  BEFORE the <html> tag ?>
<?php 
	if(isset($_GET['gmt']) && $_GET['gmt'] > 0) { // id index exists //golden medley pachi sidhai yei page ma aaune bhayekole
		$_SESSION['gmt'] = $_GET['gmt'];
		
		$_SESSION['there_is_session'] = 1; 
	}
?>
<?php //	echo "Total RingBell Score is: ".$_SESSION['rtbt'] * 1000; ?>

<?php  include ('header.php') ?>



<style type="text/css">
.hide{
	display:none;
}
.show{
	display:block;
	color: blue;
	
}


.description_homepageplayplaybox_golden P {
    color: #FFFFFF;
    height: 40px;
    margin-left: 31px;
    padding-top: 9px;
    text-align: center;
    width: auto;
}
.display-song-wrapper{  clear: both;
    margin: 0 auto;
    overflow: hidden;
    width: 480px;
	text-align:center; height:auto; padding-top:30px;padding-bottom:20px;}
</style>


<?php //include ('goldenmedley/answer_script_gm'.$_GET['gid'].'.php') ?>


<div id="mainbody" >
<div class="startareaplay">
<h1>Total Score </h1>
<div style="clear:both;"></div>

<div  class="play_box" >


<div class="description_homepageplayplaybox_golden">
      <div class="menuformedley">
      <table cellspacing="0">
      	<tbody>
        	<tr>
            	<th colspan="5">
               
                         
                <div id="reveal_answer1"  class="reveal_answer"  > 
                     <p><?php  if(isset($_SESSION['rtbt'])  && $_SESSION['rtbt'] > 0 ){ echo "Total RingBell Score is: ".$_SESSION['rtbt'] * 1000; echo "<br />"; } ?>
                     <?php  if(isset($_SESSION['tpt'])  && $_SESSION['tpt'] > 0 ){ echo "Total Triple Play Score is: ".$_SESSION['tpt'] * 1000;  echo "<br />" ; } ?> 	
                     <?php  if(isset($_SESSION['ttt'])  && $_SESSION['ttt'] > 0 ){ echo "Total Triple Play Score is: ".$_SESSION['ttt'] * 1000;   echo "<br />"; } ?> 
                     <?php  if(isset($_SESSION['bant'])  && $_SESSION['bant'] > 0 ){ echo "Total Bid A Note Score is: ".$_SESSION['bant'] * 1000;  echo "<br />" ;} ?>	             	     
					 <?php  if(isset($_SESSION['gmt']) && $_SESSION['gmt'] > 0 ){ echo "Total Golden Medley Score is: ".$_SESSION['gmt'] * 1000;  echo "<br />" ; } ?>  
					 
                 
                 <?php  if( $_SESSION['there_is_session'] < 1) { echo "No records to display"; }  ?>
                 
                 </p> 
                 
                 </div>
                </th>
            </tr>
   
        </tbody>
      </table>
      
      </div>
      
  <div class="clear"></div>
    
    

</div>




</div>
<?php $firsttrial = 0; ?>

<span id="isPrevTrialCorrect" style="display:none; font-weight: bold; font-size:30px;"><?php echo $firsttrial; ?> </span> <!-- suruma PrevTrial zero huncha -->

<?php 	include ('game_footer.php'); ?>
