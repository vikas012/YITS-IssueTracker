$(document).ready(function() {
		$("#chooseFile1").click(function() {
			alert("chooseFile1");
			$("#two").show();
			$("#chooseFile1").hide();
		});

		$("#chooseFile2").click(function() {
			alert("chooseFile2");
			$("#three").show();
			$("#chooseFile2").hide();
		});
		
		 var text_max = 100;
		 
		 $('#textarea_character_limit1').html('( Maximum '+text_max + ' character allowed )');
		 $('#textarea1').keyup(function() {
			 
		      var text_length = $('#textarea1').val().length;
		      var text_remaining = text_max - text_length;
		      $('#textarea_character_limit1').html('( ' +text_remaining + ' character remaining )');
		     
		 });
		 
		 $('#textarea_character_limit2').html('( Maximum '+text_max + ' character allowed )');
		 $('#textarea2').keyup(function() {
			 
		      var text_length = $('#textarea2').val().length;
		      var text_remaining = text_max - text_length;
		      $('#textarea_character_limit2').html('( ' +text_remaining + ' character remaining )');
		     
		  });
		 
		 $('#textarea_character_limit3').html('( Maximum '+text_max + ' character allowed )');
		 $('#textarea3').keyup(function() {
			 
		      var text_length = $('#textarea3').val().length;
		      var text_remaining = text_max - text_length;
		      $('#textarea_character_limit3').html('( ' +text_remaining + ' character remaining )');
		     
		  });
			
});
