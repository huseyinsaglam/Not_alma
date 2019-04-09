function addUser()
{
	var param = {
			username: $("#username").val(),
			name:$("#name").val(),
			surname:$("#surname").val(),
			e_mail:$("#email").val(),
			pass:$("#pass").val(),
			pass2:$("#pass2").val(),
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType:'application/json ; charset:UTF-8',
		url:'addUser',
		data: ser_data,
		success: function(data){
			if(data==1){
				alert("parolalar eslesmiyor");
			}
			else if (data=="OK") {
				alert("Basariyla uye olundu");
			}
			else if (data=="ERROR") {
				alert("Bir hata olustu");
			}
		},error:function(data){
			alert(data)
		}
			
	});
}


