
function OpenNewWindow( URL , window_name , window_width , window_height ) {
	
	var positionX = ( screen.width - window_width ) / 2 ;
	var positionY = ( screen.height - window_height ) / 2 ;
	
	features = "width="+window_width+",height="+window_height+",top="+positionY+",left="+positionX;
	var newWindow = window.open(URL, window_name, features);
	
}


function dateFormat(format,date){
	var dayString ;
	if(format==="yyyyMMdd"){	
		dayString = date.toISOString().substring(0, 4)+date.toISOString().substring(5, 7)+date.toISOString().substring(8, 10);
	}	
	
	return dayString ;
}

function numberFormat(digit,number){
	
	var length = number.toString().length ;
	var zeroString = '' ;
	var returnString = '' ;
	
	for (var int = 0; int < digit; int++) {
		zeroString = zeroString + 0 ;
	}
	
	
	returnString = ( zeroString + number ).slice(length, digit+length) ;
	
	return returnString ;
}

// note: month is 0 based, just like Dates in js
function getWeeksInMonth(year, month) {
	var weeks = [], weekDays = [], firstDate = new Date(year, month, 1), lastDate = new Date(
			year, month + 1, 0), numDays = lastDate.getDate();
	
	var start = 1;
	
	// 7 - 當月一號為週幾
	var end = 7 - firstDate.getDay();
	while (start <= numDays) {
		
		weeks.push({
			start : start,
			end : end
		});

		var flag;
		if (end - 6 < 0) {
			flag = end - 6;
		} else {
			flag = start;
		}

		weekDays.push({
			sunday : new Date(year, month, flag),
			monday : new Date(year, month, flag + 1),
			tuesday : new Date(year, month, flag + 2),
			wednesday : new Date(year, month, flag + 3),
			thursday : new Date(year, month, flag + 4),
			friday : new Date(year, month, flag + 5),
			saturday : new Date(year, month, flag + 6)
		});

		start = end + 1;
		end = end + 7;
		if (end > numDays) {
			end = numDays;

		}

	}
	return weekDays;
}

