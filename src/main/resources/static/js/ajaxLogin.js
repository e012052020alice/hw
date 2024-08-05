$(function(){
		var btn=$("#btn");
		btn.click(function(){
			$.ajax({
				url:'/demo/loginAjax',
				type:'post',
				data:{
					'account':$("#uaccount").val(),
					'password':$("#pwd").val()
				},
				dataType:'json',
				success:function(data){
					console.log(data.error);
					console.log(data.code);
					if(data.code=="100"){
						window.location.href="/demo/welcome"
					}
					else{
						$("#btn").nextAll("span").remove();
						var msg=$("#btn");
						msg.after("<br><br><span style='color:red;'>"+data.error+"</span>");
					}
				},
				error:function(xhr, status, error){
					console.log("Error: " + error);
					alert("Error");
				}
			})
		})
	})