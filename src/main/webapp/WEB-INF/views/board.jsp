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
			<div class="col-sm-2">
			</div>
			
			<div class="col-sm-8">
				
				<h1>게시판</h1>
				<a  class= "btn btn-default" href="/myboard/board/regist">게시물 작성</a>		
				
				<table class="table">
 					<thead>
	 					<tr>
		      				<th scope="col" style="width: 15%;">#</th>
		      				<th scope="col">제목</th>
		      				<th scope="col" style="width: 15%;">작성자</th>
		      				<th scope="col" style="width: 20%;">작성일</th>
	    				</tr>
  					</thead>
			  	
				  	<tbody id="board">
					   
					    
				  	</tbody>
			  
				</table>
				
				<div>
                    <c:if test="${pagination.curRange ne 1 }">
                        <a href="#" onClick="fn_paging(1)">[처음]</a> 
                    </c:if>
                    <c:if test="${pagination.curPage ne 1}">
                        <a href="#" onClick="fn_paging('${pagination.prevPage }')">[이전]</a> 
                    </c:if>
                    
                    <c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
                        <c:choose>
                            <c:when test="${pageNum eq  pagination.curPage}">
                                <span style="font-weight: bold;"><a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a></span> 
                            </c:when>
                            <c:otherwise>
                                <a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a> 
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    
                    <c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
                        <a href="#" onClick="fn_paging('${pagination.nextPage }')">[다음]</a> 
                    </c:if>
                    <c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
                        <a href="#" onClick="fn_paging('${pagination.pageCnt }')">[끝]</a> 
                    </c:if>
                </div>
									
			</div>
			
			<div class="col-sm-2">
			</div>
		</div>
	</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.12/handlebars.min.js"></script>

<script  id="boardItem" type="text/x-handlebars-template">
<tr>
	<th scope="row">{{bno}}</th>
	<td><a href="/myboard/board/boardDetail?bno={{bno}}&curPage={{curPage}}">{{title}}<span style="font-size: xx-small;">{{fileCheck}}<span><a/></td>
	<td>{{writer}}</td>
	<td>{{regDate}}</td>
</tr>
</script>

<script>
$(document).ready(function(){
	var boardItemSource = $('#boardItem').html();
	
	var boardItemTemplate = Handlebars.compile(boardItemSource);

	var boardList = ${boardList};
						
	$(boardList).each(function(){
		var date = new Date(this.regDate);
		var dateString = date.toLocaleDateString();
		var file;
		if(this.fileCheck == 1 ){
			file = '[파일]';
		}
		
		var content = {
			bno: this.bno,
			title: this.title,
			writer: this.writer,
			regDate: dateString,
			curPage: ${pagination.curPage},
			fileCheck: file
		};
		
		var boardItem = boardItemTemplate(content);
		$('#board').append(boardItem);
		
	});
			
});

function fn_paging(page){
	window.location.href = '/myboard/board?curPage='+page;
}
</script>

</body>
</html>