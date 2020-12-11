<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<html>
<head>
<title>Leader Board</title>
<link rel="stylesheet" href="css/style.css">
<script src="js/script.js"></script>
</head>
<body onload="getpageData();">
<div id="addteamform">
<div>
<input class="iinput" type="text" placeholder="Enter Team Name" id="team_name"><br><br><br>
<button class="iinput" style="background-color:#2AA4C8" onclick="submitform()">OK</button>
</div>
</div>

<div id="playgameform">

  <div>
	<input class="iinput" type="text" placeholder="Enter Team A" id="team1"><br><br><br>
</div><div>
	<input class="iinput" type="text" placeholder="Enter Team B" id="team2"><br><br><br>
  </div>
<select class="iinput" id="resultteam" style = "margin-bottom:30px";'><br><br><br>
<option value="">Select the team won</option>
<option id="teamA" value="teamA"> Team A</option>
<option id="teamB" value="teamB">Team B</option>
<option id="tied" value="tied">Tied</option>
 <br><br><br> <input class="iinput" type="submit" onclick="checkdata()"><br><br><br>
</select>

</div>
</div>
 <div class="out_div">
         <div class="inner">
            <img src="/CODA/images/25.6.19_header.jpg" style="width:230px">
         </div>
         <center>
            <h1 style="margin-top:100px">THE GAMERS CLUB!</h1>
         </center>
      </div>

<br><br><br><br><br><br>

<div style="background:black">
<button id="lboard" class="btn" onclick="leaderboard();"><b>LEADER BOARD</b></button>
<button id="playgame" class="btn" onclick="playgame()"><b>PLAY GAME</b></button>
<button id="addteam" class="btn" onclick="addTeam();"><b>ADD TEAM</b></button>
</div>

<br><br><br><br><br><br>
<div id="maindiv">
<table id="leaderboard">
<table>
<br><br><br><br><br><br>
<center>
<div>
<button id="prev" onclick="decrement()">prev<<</button>
<span id="pgno"></span>
<script>document.getElementById("pgno").innerHTML = pagenumber;</script>
<button id="next" onclick="increment()">next>></button>
</div>
</center>
</div>
</body>
<html>
