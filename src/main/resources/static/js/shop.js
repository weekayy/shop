/**
 * 
 */

function login(){
	$("button#btnSubmit").click(function(){
		var username = $.trim($("input[name='username']").val());
	    var password = $.trim($("input[name='password']").val());

	        $.ajax({
	            type: "post",
	            url:  "http://localhost:8080/user/login",
	            data: {
	                "username": username,
	                "password": password,
	            },
	            success: function(r) {
	            	
	            	alert(r.code);
	            		if(r.code == "0" ){
	            			window.location.href ="http://localhost:8080/wel";
	            		}else{
	            			window.location.href ="http://localhost:8080/errors";
	            		}
	                } 
	        });
	});
}
/**
 * 
 */

function regist(){
	$("button#btnRegist").click(function(){
		var username = $.trim($("input[name='username']").val());
	    var password = $.trim($("input[name='password']").val());
	    var sex = $.trim($("input[name='sex']").val());
	    var phone = $.trim($("input[name='phone']").val());
	    var email = $.trim($("input[name='email']").val());
	    var sex1;
	    if(sex =="男"){
	    	sex1 = 0;
	    }else{
	    	sex1 = 1;
	    }
	    
	        $.ajax({
	            type: "post",
	            url:  "http://localhost:8080/user/regist",
	            data: {
	                "username": username,
	                "password": password,
	                "sex": sex1,
	                "phone": phone,
	                "email": email,	                
	            },
	            success: function(result) {
	            	if(result.code=="0"){
	            		 alert("注册成功");
	            		window.location.href ="http://localhost:8080/login";
	            	}
	            	
	                  
	                } 
	        });
	});
}







