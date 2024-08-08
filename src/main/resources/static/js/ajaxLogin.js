function EnsureEmpty(){
	$("#accountErrorMsg").text("");
	$("#pwdErrorMsg").text("");
	if($("#uaccount").val()===""){		
		$("#accountErrorMsg").text("請輸入帳號");
		return true;					
		}
	else if($("#pwd").val()===""){
		$("#pwdErrorMsg").text("請輸入密碼");
		return true;
		}
		else{
			return false;
		}
}
$(function(){
		var btn=$("#btn");
		btn.click(function(){
			if(EnsureEmpty()){
				return false;
			}
			$.ajax({
				url:'/demo/loginAjax',
				type:'post',
				data:{
					'account':$("#uaccount").val(),
					'password':$("#pwd").val()
				},
				dataType:'json',
				success:function(data){
					console.log(data);
					if(data.code=="100"){
						window.location.href="/demo/welcome"
					}
					else{
						$("#pwdErrorMsg").text(data.error);
						
					}
				},
				error:function(xhr, status, error){
					console.log("Error: " + error);
					alert("Error");
				}
			})
		})
	})