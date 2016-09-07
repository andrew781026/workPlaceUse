

} ]);



function dateFormat(format,date){
	var dayString ;
	if(format==="yyyyMMdd")	dayString = date.toISOString().substring(0, 4)+date.toISOString().substring(5, 7)+date.toISOString().substring(8,10);
	
	return dayString ;
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

