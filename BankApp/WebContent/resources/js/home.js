$(document).ready(function(){
	$(".tab").click(function(){
		var x=$(this).attr('id');
		if(x=='login'){
			$('#register').removeClass('select');
			$('#login').addClass('select');
			$('#loginform').show();
			$('#registerform').hide();
		}
		else{
			$('#login').removeClass('select');
			$('#register').addClass('select');
			$('#registerform').show();
			$('#loginform').hide();
		}
	});
});