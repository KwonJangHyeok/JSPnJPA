<!DOCTYPE html>
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
table.type08 {
    width: 70%;
    
}
table.type08 tbody th {
    
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    background: #ececec;
}
table.type08 td {
    
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}

.Editable
{
	contenteditable : true;
}
</style>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<!-- <script type="text/javascript" src="../../common/jquery-latest.js"></script> -->
<script type="text/javascript">
function tableToJson(table) { // 변환 함수
    var data = [];

    var headers = ["idx", "company", "system","pic","contact"];
    
    /* for(var i=0; i<table.rows[0].cells.length; i++) {
        headers[i] = table.rows[0].cells[i].innerHTML.toLowerCase().replace(/ /gi,'');
    } */

    for(var i=1; i<table.rows.length; i++) {
        var tableRow = table.rows[i];
        var rowData = {};

        for(var j=0; j<tableRow.cells.length; j++) {
            rowData[headers[j]] = tableRow.cells[j].innerHTML;
        }
        data.push(rowData);
    }

    return data;
}

$(document).ready(function() {

	 var v0=$('#select').val();
	
     $.ajax({
      url : 'AdminSearch.do/',
      dataType : 'json',
      error : function(request, status, error) {
	},
	
      success : function(data)
      {
        var str ='';
		//alert(str);

		
        str+='<Thead>';
        str+= '<TR id='+'tbHead'+'>';
        str+=   '<TH style="display:none;">번호 </TH>';
        str+=   '<TH style="width: 10.0%">회사</TH>';
        str+=   '<TH style="width: 40.00%">시스템명</TH>';
        str+=   '<TH style="width: 15.00%">담당자</TH>';
        str+=   '<TH style="width: 15.00%">연락처</TH>';
        str+=   '<TH style="width: 7.00%">삭제</TH>';
        str+= '</TR>';
        str+='</Thead>';

        var values=[];
        values=data;
        var i =0;
        //alert(values[0].IDX);
        str+='<tbody id="tableBody">';
        $.each(values , function(i,item)
            {
             //alert(item);
             
             str+='<TR>';
             str += '<TD style=\'display:none\'>' + item.idx + '</TD>';
             str += '<TD>' + item.company 	+ '</TD>';
             str += '<TD>' + item.system 	+ '</TD>';
             str += '<TD>' + item.pic 		+ '</TD>';
             str += '<TD>' + item.contact	+ '</TD>';
             str += '<TD contenteditable=\'false\'><span contenteditable=\'false\'><button class="delBtn">삭제</button><span></TD>';
             str+='</TR>';
            });
		str+='</tbody>';
       // alert(str);
        $("#boardList").append(str);
        
        var idx=0;
    	$('#tableBody').find('tr').each(function() 
    	{
            $(this).find('td').each(function()
            {
            	$(this).attr('tabindex', idx++);
            })
        });  
       }
	
	
	
	
    });//end of ajax
     
     
    
    $('#search').click(function(){

      var v0=$('#select').val();
      var v1=$('#searchKey').val();
      var vv={"type":v0, "word":v1};

       $.ajax({
        url : 'AdminSearch1.do/',
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
          str+=   '<TH style="display:none;">번호 </TH>';
          str+=   '<TH style="width: 10.0%">회사</TH>';
          str+=   '<TH style="width: 40.00%">시스템명</TH>';
          str+=   '<TH style="width: 15.00%">담당자</TH>';
          str+=   '<TH style="width: 15.00%">연락처</TH>';
          str+=   '<TH style="width: 7.00%">삭제</TH>';
          str+= '</TR>';
          str+='</Thead>';

          var values=[];
          values=data;
          var i =0;
          //if(values.length==0)return;
		  str+='<tbody id="tableBody">';
		  
          $.each(values , function(i,item)
              {
        	  		
	        	  str+='<TR>';
	        	  //str += '<TD style="display:none;">' + item.idx + '</TD>';
	        	  str += '<TD style=\'display:none\'>' + item.idx + '</TD>';
	              str += '<TD>' + item.company 	+ '</SPAN></TD>';
	              str += '<TD>' + item.system 	+ '</SPAN></TD>';
	              str += '<TD>' + item.pic 		+ '</SPAN></TD>';
	              str += '<TD>' + item.contact	+ '</SPAN></TD>';
	              str += '<TD contenteditable=\'false\'><span contenteditable=\'false\'><button class="delBtn">삭제</button><span></TD>';
	              str+='</TR>';
              });
          $("#boardList").empty();
          $("#boardList").append(str);
          str+='</tbody>';
  
          
         }
      });
    }); 
    
    
    

    
    $('#Edit').click(function(){
    	
    	var jsonObj = tableToJson(document.getElementById("boardList")); // table id를 던지고 함수를 실행한다.
        //alert(JSON.stringify(jsonObj));
		
    	var vv=jsonObj;
    	vv = {'dataList' : vv};
    	vv=JSON.stringify(vv);
    	//alert(vv);
        $.ajax({
        		data : {'dataList' : vv}, //JSON.stringify(encodeURI(vv)),
        		url : 'update.do/',	
        		type : 'POST',
        		success :function(data)
        		{
        			alert("End");
        			$('#search').trigger("click");
        		}
        	});
        	
        
    });
    
    $(document).on('click', ".delBtn", function() {
        //var liId = $(this).parent("li").attr("id");
        var clickedRow = $(this).parent().parent().parent();
   
        var dataList ={'id':clickedRow.find('td').eq(0).html()};
        $.ajax(
        {
        	data : dataList,
        	url : 'delete.do/',
        	type : 'POST',
        	success : function(data)
        	{
        		//alert("Finish");
        	}
        	
        });
        clickedRow.remove();
        
    });
    
    
	//$('#Add').click(function(){
	$(document).on('click', "#Add", function() 
	{
		//$('#Add').insertRow($('#tablebody').rows.length);
		var lastNumber = Number($('#tableBody').find('tr').last().find('td').eq(0).html());
		lastNumber+=1;
    	var str="";
    	
		str+='<TR>';
	    //str += '<TD style="display:none";>'+lastNumber+'</TD>';
	    str += '<TD style=\'display:none\'></TD>';
	    str += '<TD></TD>';
	    str += '<TD></TD>';
	    str += '<TD></TD>';
	    str += '<TD></TD>';
	    str += '<TD contenteditable=\'false\'><span class=\'Editable\'><button class="delBtn">삭제</button><span></TD>';
	    str+='</TR>';
	    
		$('#boardList > tbody:last').append(str);
		
		var idx=0;
		$('#tableBody').find('tr').each(function() 
		{
	        $(this).find('td').each(function()
	        {
	        	$(this).attr('tabindex', idx++);
	        })
	    });
		
		/*
		 
		*/
    });
	
    
   
    	
    
  
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
    
    
    <div>
      <table id="boardList" border="1" class = "type08" contenteditable='true'>

      </table>
    </div>
		<button class="btn-pos-2 commandButton" id="Add">행추가</button>
		<button class="btn-pos-2 commandButton" id="Edit">추가내용 저장</button>
	 </div>
	 <div>
	 삭제된 내용은 복구가 불가능합니다. 주의해 주시기바랍니다.
	 </div>
</body>
</html>