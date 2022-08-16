$.ajax({
	url: '/backboard/boardlist',
	method: 'get',
	data: 'currentPage=1',
	success: function (jsonObj) {
		if (jsonObj.status == 1) {
			let pageBeanObj = jsonObj.t;

			//게시글 div를 원본으로 한다. 복제본만든다
			let $board = $('div.board').first();
			let $boardParent = $board.parent();
			$(pageBeanObj.list).each(function (index, board) {
				console.log(index, board);
				let $boardCopy = $board.clone();//복제본
				$boardCopy.find("div.board_no").html(board.boardNo);
				$boardCopy.find("div.board_parent_no").html(board.boardParentNo);
				$boardCopy.find("div.board_title").html(board.boardTitle);
				$boardCopy.find("div.board_dt").html(board.boardDt);
				$boardCopy.find("div.board_id").html(board.boardId);
				$boardCopy.find("div.board_viewcount").html(board.boardViewcount);
				$boardParent.append($boardCopy);
			});

			let $pagegroup = $('div.pagegroup')
			let $pagegroupHtml = '';
			if(pageBeanObj.startPage > 1){
				$pagegroupHtml += 'PREV';
			}
			for(let i=pageBeanObj.startPage; i<=pageBeanObj.endPage;i++){
				$pagegroupHtml += '&nbsp;&nbsp;';
				$pagegroupHtml += i;
			}
			if(pageBeanObj.endPage < pageBeanObj.totalPage){
				$pagegroupHtml += '&nbsp;&nbsp;';
				$pagegroupHtml += 'NEXT';
			}

			$pagegroup.html($pagegroupHtml);
			/*pageBeanObj.startPage
			pageBeanObj.endPage
			pageBeanObj.totalPage
			pageBeanObj.currentPage*/
		} else {
			alert(jsonObj.msg);
		}
	},
	error: function (jqXHR) {
		alert("에러:" + jqXHR.status);
	}
});