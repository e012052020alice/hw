function EnsureEmpty(){
	if($("#uaccount").val()===""){
		
		$("#errorMsg").text("請輸入帳號");
		console.log("帳號");
		return true;					
		}
	else if($("#pwd").val()===""){
		$("#errorMsg").text("請輸入密碼");
		console.log("密碼");
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
					console.log(data.error);
					console.log(data.code);
					if(data.code=="100"){
						window.location.href="/demo/welcome"
					}
					else{
						$("#errorMsg").text(data.error);
						
					}
				},
				error:function(xhr, status, error){
					console.log("Error: " + error);
					alert("Error");
				}
			})
		})
	})