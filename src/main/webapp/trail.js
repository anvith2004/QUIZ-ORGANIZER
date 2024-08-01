const timer = document.querySelector("#timer");
const play = document.querySelector("#play");
const pause = document.querySelector("#pause");
const submit = document.querySelector("#submit");
let a = timer.innerHTML;
const myArray = a.split(":");
let hr = get(myArray[0]);
let min = get(myArray[1]);
let sec = get(myArray[2]);
var inter, stat = "pause"; 
var i = 0;
start();
play.addEventListener("click",start);
pause.addEventListener("click",stop);
//timer.innerHTML = hr+":"+min+":"+sec;
function get(y)
{
	if (y[0] == 0)
	{
		y = y[1];
	}
	return parseInt(y);
}

function start()
{
	if (stat == "pause")
	{
		inter = setInterval(watch,1000);
		stat = "play";
	}
}

function zero(x)
{
	if (x/10 < 1)
	{
		x = "0" + x;
	}
	return x;
}

function conv()
{
    sec --;
	if (sec == -1)
	{
		sec = 59;
		min --;
	}
	if (min == -1)
	{
		min = 59;
		hr --;
	}
}

function watch()
{
	conv();
	timer.innerHTML = zero(hr)+":"+zero(min)+":"+zero(sec);
	if (timer.innerHTML == "00:00:00")
	{
		submit.click();
	}
	
}

function stop() 
{
	if (stat == "play")
	{
		clearInterval(inter);
		stat = "pause";
	}
}