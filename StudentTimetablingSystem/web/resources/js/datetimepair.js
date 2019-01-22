$(function(){
	$('.datepair .time').timepicker({
		'showDuration': true,
		'timeFormat': 'H:i',
		'step': 15
	});

	$('.datepair .date').datepicker({
		'format': 'dd/mm/yyyy',
		'autoclose': true
	});

	$('.datepair').datepair();
});