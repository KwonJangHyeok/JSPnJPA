<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type='text/javascript'>
	$(document).ready(function() {

		$('#Login').click(function()
		{
			var _id = $('#userid').val();
			var _pw = $('#password').val();
			//alert("@@@");
			var dt ={'id':_id, 'pw':_pw};
			
			
			$.ajax(
			{
				url:'./LoginCheck.do',
				data:dt,
				success : function(data)
				{
					
					if(data =='1')//success
					{
						window.open('Admin');
						window.close();
					}
					
					else alert('id, pw 체크 요망');	
				}
				
				
			});
		});
		
	})


</script>
</head>
<body>
	<div>
		<label>ID</label>
		<input type='text' id='userid' placeholder = "ID"></input>
		<label>PW</label>
		<input type='password' id='password' placeholder = "pw"></input>
		<button class="btn-pos-2 commandButton" id="Login">로그인</button>
	</div>
</body>
</html>