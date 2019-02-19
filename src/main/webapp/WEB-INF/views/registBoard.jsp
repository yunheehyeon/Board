<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
			
			<h1>게시물 작성</h1>
			
			<form id="uploadForm" method="post" class="clearfix" enctype="multipart/form-data">
				<input id="title" class="form-control" type="text" name="title" placeholder="제목"/><br/>
				<input id="name" class="form-control" type="text" name="writer" placeholder="이름"><br/>
				<textarea id="content" class="form-control" rows="10" cols="50" placeholder="내용작성"></textarea><br/>
				<label class = "addFile btn btn-default" for="files" style="float: left;">파일 추가</label>
				<input id="files" type="file" class="files" style="display: none" />	
			</form>

			<div id="fileList" style="overflow-y: scroll; height:150px; border: solid 1px #f1f1f1; margin-top: 8px; margin-bottom: 8px;">
			</div>
			
			<a class= "btn btn-default" href="/myboard/board" style="float:right;">취소</a>
			<button id="boardInsertBtn" data-check = "0" class= "btn btn-default" style="float:right;">등록</button>
			
			
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>

<script>
$(document).ready(function(){
	
	$('#Progress_Loading').hide();
	
	var fileBuffer = [];
	
	$("#files").on('change', function() {
		const target = document.getElementById('uploadForm');
		
		console.log(target);
		
		Array.prototype.push.apply(fileBuffer, target[3].files);
		
		var html = '';
		
		$("#files").val("");
		
		console.log(fileBuffer);
		
		$.each(fileBuffer, function(index, file) {
			const fileName = file.name;
			
			html += '<div class="file clearfix" style="padding: 8px; border-bottom: solid 1px #f1f1f1;">';
			html += '<span class="btn" style="padding: 2px;">'+fileName+'</span>'
			html += '<span id="removeImg" class="btn btn-default" style="float:right; padding: 2px;"> 삭제 </span>';
			html += '</div>';
			
			$('#fileList').html(html);
		});
		
	});
	
	$(document).on('click', '#removeImg', function() {
		
		const fileIndex = $(this).parent().index();
		fileBuffer.splice(fileIndex, 1);
		console.log(fileIndex);
		$('#fileList > div:eq(' + fileIndex + ')').remove();

		console.log(fileBuffer);
	});
	
	$(document).on("click",'#boardInsertBtn',function() {
		var sendCheck = $('#boardInsertBtn').data('check');
				
		if(check_required_inputs() && sendCheck == '0'){
			var title = $('#title').val();
			var name = $('#name').val();
			var content = $('#content').val();
			$('#boardInsertBtn').data('check', '1');
			boardUpload(title, name, content);
		}
	});
	
	function check_required_inputs() {
		var result = true;
		
		if( $('#title').val() == "" ){
			alert('제목을 작성하세요');
			return false;
		}
		if( $('#name').val() == "" ){
			alert('이름을 작성하세요');
			return false;
		}
		if( $('#content').val() == "" ){
			alert('내용을 작성하세요');
			return false;
		}
		
		return result;
		
	};
	
	function boardUpload(title, name, content) {
		
		var form = new FormData();

		for (i = 0; i < fileBuffer.length; i++) {
			form.append("uploadfile", fileBuffer[i]);
			console.log(form.length);
		};
			
		form.append("title", title);
		form.append("name", name);
		form.append("content", content);
		
		var url;
		
		$.ajax({
			type : 'post',
			url : '/myboard/boardRest/insert',
			data : form,
			processData : false,
			contentType : false,
			success : function(data) {
				alert("성공");
				window.location.href = '/myboard/board';
			},
			error : function(error) {
				alert("파일 업로드에 실패하였습니다.");
				$('#boardInsertBtn').data('check', '0');
			}
		});
			
	};
	
});

</script>
	
</body>
</html>