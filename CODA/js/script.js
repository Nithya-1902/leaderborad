

var pagenumber = 1;
var countrynames = [];
function autocomplete(inp, arr) {
  var currentFocus;
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;
      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      this.parentNode.appendChild(a);
      for (i = 0; i < arr.length; i++) {
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          b = document.createElement("DIV");
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
          b.addEventListener("click", function(e) {
              inp.value = this.getElementsByTagName("input")[0].value;
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
 
  function addActive(x) {
    if (!x) return false;
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    x[currentFocus].classList.add("autocomplete-active");
  }
  function removeActive(x) {
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  function closeAllLists(elmnt) {
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
        x[i].parentNode.removeChild(x[i]);
      }
    }
  }
  document.addEventListener("click", function (e) {
      closeAllLists(e.target);
  });
}

function getTeamNames(){
	var url = "/CODA/populateTeamNames";
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		countrynames = JSON.parse(xmlhttp.responseText);
		attachEvent();
	}
	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}
function getpageData(){
leaderboard();
	var url = "/CODA/DisplayLeaderBoard?pageno="+pagenumber;
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		if(xmlhttp.responseText == "eof"){
document.getElementById("next").disabled = true;
		}else{
            var result=JSON.parse(xmlhttp.responseText);
var HTMLString = "<tr style=\"background-color:#2AA4C8;color:white\"><td style=\"border-color:#fff;\">TeamName</td><td style=\"border-color:#fff;\">Wins</td><td style=\"border-color:#fff;\">Losses</td><td style=\"border-color:#fff;\">Ties</td><td style=\"border-color:#fff;\">Score</td></tr>";
for(var i=0;i<result.length;i++){
HTMLString += ("<tr><td>"+result[i].team_name+"</td>"+"<td>"+result[i].wins+"</td>"+"<td>"+result[i].losses+"</td>"+"<td>"+result[i].ties+"</td>"+"<td>"+result[i].score+"</td><tr>");
}

document.getElementById("leaderboard").innerHTML = HTMLString;
		}
	}
	};
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}
function decrement(){
if(pagenumber>1){
pagenumber=pagenumber-1;
getpageData();
document.getElementById("pgno").innerHTML = pagenumber;
}else{
document.getElementById("prev").disabled = true;
}
document.getElementById("next").disabled = false;
}
function increment(){

pagenumber = pagenumber+1;
getpageData();
if(pagenumber>1){
document.getElementById("prev").disabled = false;
}
document.getElementById("pgno").innerHTML = pagenumber;
}

function addTeam(){
document.getElementById("addteamform").style.display="block";
}
function leaderboard(){
  document.getElementById("lboard").style.textDecoration = "underline overline";
  document.getElementById("addteam").style.textDecoration = "none";
document.getElementById("playgame").style.textDecoration = "none";
document.getElementById("maindiv").style.display="block";
document.getElementById("addteamform").style.display="none";
document.getElementById("playgameform").style.display="none";
}
function attachEvent(){
autocomplete(document.getElementById("team1"), countrynames);
autocomplete(document.getElementById("team2"), countrynames);
}
function playgame(){
getTeamNames();
  document.getElementById("playgame").style.textDecoration = "underline overline";
  document.getElementById("addteam").style.textDecoration = "none";
document.getElementById("lboard").style.textDecoration = "none";
document.getElementById("maindiv").style.display="none";
document.getElementById("addteamform").style.display="none";
document.getElementById("playgameform").style.display="block";
}
function addTeam(){
  document.getElementById("addteam").style.textDecoration = "underline overline";
document.getElementById("lboard").style.textDecoration = "none";
  document.getElementById("playgame").style.textDecoration = "none";
document.getElementById("maindiv").style.display="none";
document.getElementById("addteamform").style.display="block";
document.getElementById("playgameform").style.display="none";
}
function submitform(){
event.preventDefault();
var url = "/CODA/AddTeam?team_name="+document.getElementById("team_name").value;
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	var result = document.getElementById("team_name").value;
	document.getElementById("team_name").value="";
	alert(result+" added");
	}
	};
	xmlhttp.open("POST", url, true);
	xmlhttp.send();
}

function checkdata(){
var teamA=document.getElementById("team1").value;
var teamB=document.getElementById("team2").value;
var result=document.getElementById("resultteam").value;
if(teamA == teamB ){
alert("Enter different team names");
}else if(teamA.length <1){
alert("Enter team A");
}else if(teamB.length<1){
alert("Enter team B");
}else if(result.length<1){
alert("Enter result");
}else{
var url = "/CODA/PlayGame";
var params="teamA="+teamA+"&teamB="+teamB+"&result="+result;
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	alert(xmlhttp.responseText);
	}
	};
	xmlhttp.open("POST", url, true);
xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send(params);
}

}
