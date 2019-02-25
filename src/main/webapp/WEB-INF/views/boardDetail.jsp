<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container">
		<div class="row">
		
			<div class="col-sm-7">
				<h1>게시물 상세 페이지</h1>
			
				<form id="uploadForm" method="post" enctype="multipart/form-data">
					<input class="form-control" type="text" name="title" value="${item.title}" readonly />
					<span>작성자: ${item.writer}</span>
					<span style="float: right;">
					<fmt:formatDate value="${item.regDate}" pattern="yyyy년  MM월  dd일"/>
					</span>
					<textarea class="form-control" rows="10" cols="50" style="margin-top: 10px;" readonly>${item.content}</textarea><br/>	
				</form>
				<div>첨부파일</div>
				<div id="fileList" style="overflow-y: scroll; height:150px; border: solid 1px #f1f1f1; margin-top: 8px; margin-bottom: 8px;">
				</div>
				
				<a id="deleteBoard" data-bno="${item.bno}" class="btn btn-default" >게시물삭제</a>
				
				<h5>댓글 쓰기</h5>	
				
				<table class="table">
 					<thead>
	 					<tr>
		      				<th scope="col" style="width: 20%; padding: 0px; padding-right: 4px;">
		      				<input type="text" class="form-control required" id="writer" placeholder="이름"/>	
		      				</th>
		      				<th scope="col" style="padding: 0px; padding-right: 4px;">
		      				<input type="text" class="form-control required" id="content" placeholder="내용"/>
		      				</th>				
							<th scope="col" style="padding: 0px;">
		      				<input type="button" id="replyInsert" class = "btn btn-default" style="width:100%; float: right;" value="등록"> 
		      				</th>
	    				</tr>
  					</thead>
  						  
				</table>
				<a href="#" onClick="fn_paging('${pagination.curPage }')">[목록]</a> 	
			</div>
			
			<div class="col-sm-5">
				<h1>댓글</h1>	
				
  				<div id="replyList">
					    
				  </div>
  						
			</div>
		</div>
	</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.min.js"></script>
<script id="reply-template" type="text/x-handlebars-template">

<div>
	<span>{{writer}}</span>
	<span class="btn btn-default" style="padding: 3px">삭제</span>
	<span style="float:right;">{{regDate}}</span>
</div>
<input type="text" class="form-control" value="{{rtext}}" placeholder="내용" readonly />

</script>

<script id="file-template" type="text/x-handlebars-template">
<div class="clearfix" style="padding: 8px; border-bottom: solid 1px #f1f1f1;">
<span class="btn" style="padding: 2px;">{{fileName}}</span>
<a href="/myboard/{{filePath}}" class = "btn btn-default"  style="float:right; padding: 2px;" download="{{fileName}}">내려받기</a>
<span class="btn" style="float:right; padding: 2px;">파일 크기: {{fileSize}} 바이트</span>
</div>
</script>

<script>
$(document).ready(function(){
	
	var bno = ${item.bno};
	getAllReplies(bno);
	
	$('#replyInsert').click(function(){
		
		if(check_required_inputs()){	
			
			var writer = $('#writer').val();
			var content = $('#content').val();
			
			var bno = ${item.bno};
			
			$.ajax({
				type: 'post',
				url: '/myboard/replyRest/insert',
				headers: {
					'Content-Type': 'application/json',
					'X-HTTP-Method-Override': 'post'
				},
				data: JSON.stringify({
					'bno': bno,
					'writer': writer,
					'content': content
				}),
				success: function(result) {
					alert('댓글 추가 결과: ' + result);
					$('#writer').val("");
					$('#content').val("");
					getAllReplies(bno);
				}		
			});
			
		}
		
	});
	
	function check_required_inputs() {
		var result = true;
		
		if( $('#writer').val() == "" ){
			alert('이름을 작성하세요');
			return false;
		}
		if( $('#content').val() == "" ){
			alert('내용을 작성하세요');
			return false;
		}
		
		return result;
		
	};
	
	var source = $('#reply-template').html();
	var template = Handlebars.compile(source);
	
	function getAllReplies(bno) {
		
		$.getJSON('/myboard/replyRest/selectAll/' + bno, function(data) {
			$('#replyList').empty(); 
			
			$(data).each(function() {
				var date = new Date(this.regDate);
				var dateString = date.toLocaleDateString()
						+ ' ' + date.toLocaleTimeString();
			
				var content = {
					writer: this.writer,
					regDate: dateString,
					rtext: this.content
				}
				
				var replyItem = template(content);
				$('#replyList').append(replyItem);

			});
		
		});
	}
	
	var fileSource = $('#file-template').html();
	var fileTemplate = Handlebars.compile(fileSource);
	var fileList = ${fileList};
	
	$(fileList).each(function(){
		
		var content = {
				fileName: this.fileName,
				fileSize: this.fileSize,
				filePath: this.filePath
			}
			
			var fileItem = fileTemplate(content);
			$('#fileList').append(fileItem);

		
	});
	
	$('#deleteBoard').click(function() {
		var bno = $(this).data("bno");
	
		var result = confirm("게시글 삭제 확인");
		
		if (result == true) {
			$.ajax({
				type: 'delete',
				url: '/myboard/boardRest/delete/' + bno,
				headers: {
					'Content-Type': 'application/json',
					'X-HTTP-Method-Override': 'delete'
				},
				success: function(data) {
					alert('삭제 성공');
					fn_paging('${pagination.curPage}');
				},
				erorr: function(date){
					alert('삭제 실패');
				}
			});
		} // end if
	});
	
});

function fn_paging(page){
	window.location.href = '/myboard/board?curPage='+page;
}
</script>

</body>
</html>