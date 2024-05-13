<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">오늘의 할일 목록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Todo List Page
				<button id="regBtn" type="button"
					class="btn-primary pull-right btn-xs btn">할일 등록</button>
			</div>
			<!-- <div class="text-right">
				<a href="/board/register">게시글 등록</a>
			</div> -->
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>할 일</th>
							<th>작성자</th>
							<th>마감일</th>
							<th>수행여부</th>
						</tr>
					</thead>

					<c:forEach var="todo" items="${list}">
						<tr>
							<td>${todo.tno}</td>
							<td><a href="/todo/get?tno=${todo.tno}">${todo.title}</a></td>
							
							<!-- 상세 조회에서 페이지 이동을 위한 수정 -->
							<%-- <td><a class="move" href="${todo.tno}">${todo.title}</a></td> --%>
							<td>${todo.writer}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${todo.duedate}"></fmt:formatDate></td>
							<td>${todo.finished == 0 ? "미완료" : "완료"}</td>
						</tr>
					</c:forEach>
				</table>
				<!-- /.table-responsive -->

				<!-- 검색창 -->
			<%-- 	<div class="row">
					<div class="col-lg-12">
						<form id="searchForm" action="/board/list" method="get">
							<select name="type">
								<option value=" ${pageMaker.cri.type == null? 'selected':''}">--</option>
								<option value="T" <c:out value="${pageMaker.cri.type eq 'T' ? 'selected':'' }"/>>제목</option>
								<option value="C" <c:out value="${pageMaker.cri.type eq 'C' ? 'selected':'' }"/>>내용</option>
								<option value="W" <c:out value="${pageMaker.cri.type eq 'W' ? 'selected':'' }"/>>작성자</option>
								<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC' ? 'selected':'' }"/>>제목 or 내용</option>
								<option value="TW" <c:out value="${pageMaker.cri.type eq 'TW' ? 'selected':'' }"/>>제목 or 작성자</option>
								<option value="CW" <c:out value="${pageMaker.cri.type eq 'CW' ? 'selected':'' }"/>>내용 or 작성자</option>
								<option value="TCW" <c:out value="${pageMaker.cri.type eq 'TCW' ? 'selected':'' }"/>>제목 or 내용 or 작성자</option>
							</select> <input type="test" name="keyword" value="${pageMaker.cri.keyword }">
							<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"> <input
								type="hidden" name="amount" value="${pageMaker.cri.amount}">
							<button class="btn btn-default">검색</button>
						</form>
					</div>
				</div> --%>

				<!-- Page -->
	<%-- 			<div class="dataTables_paginate paging_simple_numbers"
					id="dataTables-example_paginate">
					<ul class="pagination">
					<li class="paginate_button next" aria-controls="dataTales_example" tabindex="0"
						id="dataTales_example_next"><a href="${pageMaker.first}">처음</a></li>
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="paginate_button ${pageMaker.cri.pageNum==num?"active":""}">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage +1}">Next</a></li>
						</c:if>
						<li class="paginate_button last" aria-controls="dataTales_example" tabindex="0"
						id="dataTales_example_last"><a href="${pageMaker.last}">마지막</a></li>
					</ul>
				</div> --%>

				<!-- 상세페이지 처리를 위한 form tag 추가 -->
				<form id="actionForm" action="/todo/list" method="get">
				</form>

				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body">처리가 완료되었습니다.</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<!-- <button type="button" class="btn btn-primary">Savechanges</button> -->
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>

<script type="text/javascript">

  $(document).ready(
    function() {
        const result = '<c:out value="${result}"/>';

        checkModal(result);

        $("#regBtn").on("click", function() {
            self.location = "/todo/register";
        })

        const actionForm = $("#actionForm");
        $(".paginate_button a").on("click", function(e){
            e.preventDefault();
            console.log("click임");
            actionForm.submit();
        })

        $(".move").on("click", function(e){
            console.log("move임");
            e.preventDefault();
            actionForm.append(
                    "<input type='hidden' name='bno' value='" 
                    + $(this).attr("href") + "'>");
            actionForm.attr("action", "/todo/get");
            actionForm.submit();
        })

        /* 검색 버튼의 이벤트 처리 */
        const searchForm = $("#searchForm");

        $("#searchForm button").on("click", function(e){
            if (!searchForm.find("option:selected").val()){
              alert("검색 종류를 선택하세요.")
              return false;
            }

            if(!searchForm.find("input[name='keyword']").val()){
                alert("검색어를 입력하세요.");
                return false;
            }

            searchForm.find("input[name='pageNum']").val("1");
            e.preventDefault();

            searchForm.submit();
        })

        function checkModal(result) {
            if (result === '') {
                return;
            }

            if (parseInt(result) > 0) {
                $(".modal-body").html(
                        "게시글 " + parseInt(result) + " 번이 등록되었습니다.");
            }
            $("#myModal").modal("show");
        }

    });
</script>
<%@ include file="../includes/footer.jsp"%>