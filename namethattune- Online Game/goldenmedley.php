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
    height: 109px;
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

<?php include ('goldenmedley/gamescripts_gm.php') ?>
<?php //include ('goldenmedley/answer_script_gm'.$_GET['gid'].'.php') ?>

<?php include ('goldenmedley/answer_script_gm.php');  ?>
<div id="mainbody" >
<div class="startareaplay">
<h1>Golden Medley </h1>
<div style="clear:both;"></div>

<div  class="play_box" >




<?php 
	 $i = $_GET['gid'];
	include ('goldenmedley/song'.$i.'.php'); 
?>
 
</div>
<?php $firsttrial = 0; ?>

<span id="isPrevTrialCorrect" style="display:none; font-weight: bold; font-size:30px;"><?php echo $firsttrial; ?> </span> <!-- suruma PrevTrial zero huncha -->

<?php 	include ('game_footer.php'); ?>
