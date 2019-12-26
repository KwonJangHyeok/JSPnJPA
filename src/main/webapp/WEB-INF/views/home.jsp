<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>담당자 검색</title>
<style type="text/css">
table.type08 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-left: 1px solid #ccc;
    margin: 20px 10px;
}

table.type08 thead th {
    padding: 10px;
    font-weight: bold;
    border-top: 1px solid #ccc;
    border-right: 1px solid #ccc;
    border-bottom: 2px solid #c00;
    background: #dcdcd1;
}
table.type08 tbody th {
    width: 120px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    background: #ececec;
}
table.type08 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
</style>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<!-- <script type="text/javascript" src="../../common/jquery-latest.js"></script> -->
<script type="text/javascript">

var auth = "${auth}"; 
$(document).ready(function() {

	 var v0=$('#select').val();
	
     $.ajax({
      url : 'Search.do/',
      dataType : 'json',
      error : function(request, status, error) {
	},
	
      success : function(data)
      {
        var str ='';
		//alert(str);


        str+='<Thead>';
        str+= '<TR id='+'tbHead'+'>';
        str+=   '<TH>회사</TH>';
        str+=   '<TH>시스템명</TH>';
        str+=   '<TH>담당자</TH>';
        str+=   '<TH>연락처</TH>';
        str+= '</TR>';
        str+='</Thead>';

        var values=[];
        values=data;
        var i =0;
        //alert(values[0].IDX);
        $.each(values , function(i,item)
            {
             //alert(item);
             str+='<TR>';
             str += '<TD>' + item.company + '</TD>';
             str += '<TD>' + item.system + '</TD>';
             str += '<TD>' + item.pic + '</TD>';
             str += '<TD>' + item.contact + '</TD>';
             str+='</TR>';
            });

       // alert(str);
        $("#boardList").append(str);
       }
    });
     
     
    
    $('#search').click(function(){

      var v0=$('#select').val();
      var v1=$('#searchKey').val();
      var vv={"type":v0, "word":v1};

       $.ajax({
        url : 'Search1.do/',
        data : vv,
        dataType : 'json',
        error : function(request, status, error) {
		
        	var str="code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error;
				 $("#boardList").empty();
				$("#boardList").append(str);	
		},

        success : function(data)
        {
          var str ='';
          str+='<Thead>';
          str+= '<TR id='+'tbHead'+'>';
          str+=   '<TH>회사</TH>';
          str+=   '<TH>시스템명</TH>';
          str+=   '<TH>담당자</TH>';
          str+=   '<TH>연락처</TH>';
          str+= '</TR>';
          str+='</Thead>';

          var values=[];
          values=data;
          var i =0;

          $.each(values , function(i,item)
              {
	        	  str+='<TR>';
	              str += '<TD>' + item.company + '</TD>';
	              str += '<TD>' + item.system + '</TD>';
	              str += '<TD>' + item.pic + '</TD>';
	              str += '<TD>' + item.contact + '</TD>';
	              str+='</TR>';
              });
          $("#boardList").empty();
          $("#boardList").append(str);
         }
      });
    }); 
    $('#Edit').click(function(){
    	
    	window.open('./LoginAdmin','',width=200,height=100); 
    	
    })
    
    if(auth =='admin')
    {
    	$("#boardList").setAttribute(contenteditable,'true');
    	$("#tbHead").setAttribute(contenteditable,'false');
    }
    	
    
  
});



</script>

</head>
<body>
  <div class="w100 pt20">
    <select id="select">
      <option value="NameSys">시스템 명</option>
      <option value="NamePer">담당자</option>
    </select>
    
    <input type="text" id='searchKey' width='10px' placeholder="검색어 입력..."/>
    <button class="btn-pos-2 commandButton" id="search">조회</button>
    <button class="btn-pos-2 commandButton" id="Edit">관리</button>
    <div>
      <table id="boardList" border="1" class = "type08">

      </table>
    </div>

  </div>
</body>
</html>