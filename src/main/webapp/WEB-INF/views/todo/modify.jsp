<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-6">
			<h1 class="page-header">게시글 수정</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-10">
			<div class="panel panel-default">
				<div class="panel-heading">todo modify</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-10">
						<form role="form" action="/todo/modify" method="post">
						<!-- 페이지 번호 이동을 위한 추가 -->
		<%-- 					<input type="hidden" name="pageNum" value="${cri.pageNum}">
							<input type="hidden" name="amount" value="${cri.amount}">
							<input type="hidden" name="type" value="${cri.type}">
							<input type="hidden" name="keyword" value="${cri.keyword}"> --%>
								<div class="form-group">
									<label>번호</label>
									<input class="form-control" name="tno" value="${todo.tno}" readonly>
								</div>
								<div class="form-group">
									<label>할 일</label>
									<input class="form-control" name="title" value="${todo.title}" required>
								</div>
								<div class="form-group">
									<label>작성자</label> <input
										class="form-control" name="writer" value="${todo.writer}" readonly>
								</div>
								<div class="form-group">
									<label>마감일</label> <input
										type="date" class="form-control" name="duedate" required>
								</div>
								<div class="form-group">
									<label>수행여부</label> <input
										type="checkbox"class="finishedCheckbox" name="finished" value="${todo.finished == 1 ? 'checked' : ''}">
								</div>
						<button type="submit" data-oper="modify" class="btn btn-default">글수정</button>
						<button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
						<button type="submit" data-oper="list" class="btn btn-info">목록</button>
						</form>
					</div>
					<!-- /.col-lg-6 (nested) -->
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<script type="text/javascript">
		$(document).ready(function(){
			const formObj = $("form");
			
			$("button").on("click", function(e){
				e.preventDefault();		//submit의 기본 동작 해제
				const operation = $(this).data("oper");
				
				console.log(operation);
				
				if(operation === 'remove'){
					formObj.attr("action","/todo/remove")
							.attr("method","POST")
				}else if(operation === "modify"){
					formObj.attr("action","/todo/modify")
							.attr("method","POST")
				}else if(operation === "list"){
					formObj.attr("action","/todo/list")
							.attr("method","GET")
					const pageNumTag = $("input[name='pageNum']").clone();
					const amountTag = $("input[name='amount']").clone();
					const typeTag = $("input[name='type']").clone();
					const keywordTag = $("input[name='keyword']").clone();
					
					formObj.empty();
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					formObj.append(typeTag);
					formObj.append(keywordTag);
				}
				
				formObj.submit();  //submit을 무력화 시켰기 때문에 한번 날려줘야함
			})
			$('.finishedCheckbox').change(function(){
				if($(this).is(':checked')){
					$(this).val(1);
				} else {
					$(this).val(0);
				}
			});
		})
		
		
	</script>
	
	<%@ include file="../includes/footer.jsp"%>
</body>
</html>