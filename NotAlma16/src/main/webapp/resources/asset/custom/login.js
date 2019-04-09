function loginBtn()
{
	var param = {
			username: $("#username").val(),
			pass:$("#pass").val(),
			pass2:$("#pass2").val(),
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType:'application/json ; charset:UTF-8',
		url:'controlUser',
		data: ser_data,
		success: function(data){
		
			if (data=='OK') {
				$(location).attr('href', 'index')
			}
			else if (data=='ERROR') {
				alert("Kullanici Adi Sifrenizi kontrol ediniz");
			}
		},error:function(data){
			alert(data)
		}
			
	});
}


