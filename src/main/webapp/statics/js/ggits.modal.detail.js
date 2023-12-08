const tableView = function (size, title="title" , menuArr, activeNum = 1) {
    var tableItem ="";
    var menuSize = menuArr.length;
    var maxSize = 5;
    if(size > maxSize){
        size = maxSize;
    }

    //header
    tableItem += "<table class='modal_table mt16'>";
    tableItem += "<colgroup>";
    tableItem += "<col style='width:10%'>";
    tableItem += "<col style='width:90%'>";
    tableItem += "</colgroup>";
    tableItem += "<thead>";
    tableItem += "<tr>";
    tableItem += "<th scope='col'>번호";
    tableItem += "</th>";
    tableItem += "<th scope='col'>"+title+"</th></tr></thead>";

    //body
    tableItem += "<tbody>"
    
    for (var i = 0; i < size; i++){
        var test = i;
        if(activeNum){
            test = i+5;
        }
        tableItem += "<tr>"
        tableItem += "<td>"+(i+1)+"</td>"
        tableItem += "<td>"+menuArr[test]+"</td>"
        tableItem += "</tr>"
    }

    //footer
    tableItem += "</tbody></table>"
    return $(tableItem);
}

const tableUserView = function (size, title , userList) {
    var tableItem ="";

    //header
    tableItem += "<table class='modal_table'>";
    tableItem += "<colgroup>";
    tableItem += "<col style='width:5%'>";
    tableItem += "<col style='width:5%'>";
    tableItem += "<col style='width:35%'>";
    tableItem += "<col style='width:15%'>";
    tableItem += "<col style='width:25%'>";
    tableItem += "<col style='width:15%'>";
    tableItem += "</colgroup>";
    tableItem += "<thead>";
    tableItem += "<tr>";
    tableItem += "<th scope='col'></th>";
    tableItem += "<th scope='col'>번호</th>";
    tableItem += "<th scope='col'>사용자 ID</th>";
    tableItem += "<th scope='col'>이름</th>";
    tableItem += "<th scope='col'>그룹</th>";
    tableItem += "<th scope='col'>사용자상태</th>";
	tableItem += "</tr>";
	tableItem += "</thead>";

    //body
    tableItem += "<tbody>"
    for (var i = 0; i < userList.length; i++){
        tableItem += "<tr>";
        tableItem += "<td><input type='checkbox' id='test_"+i+"' data-value='"+userList[i].oprtrId+"' onclick='fnChkTest(0)'></td>";
        tableItem += "<td>"+userList[i].rownum+"</td>";
        tableItem += "<td>"+userList[i].oprtrEmail+"</td>";
        tableItem += "<td>"+userList[i].oprtrEmail+"</td>";
        tableItem += "<td>"+userList[i].grpNm+"</td>";
        tableItem += "<td>"+userList[i].oprtrSttsCd+"</td>";
        tableItem += "</tr>";
    }
	
    //footer
    tableItem += "</tbody></table>"
    return $(tableItem);
}



const pageNation = function(activeNum,listCnt,totalCnt){
    var pgBody = "";
    var size =  parseInt(totalCnt)%parseInt(listCnt) == 0 ? parseInt(totalCnt)/parseInt(listCnt) : parseInt(totalCnt)/parseInt(listCnt)+1;

    var maxSize = 10;
    if(size > maxSize){
        size = maxSize;
    }

    //header
    pgBody += "<div class='modal_page_wrap'><ul class='pagination'><li><a href='#' class='first'>처음 페이지</a></li>";
    pgBody += "<li><a href='#' class='arrow left'><</a></li>";

    //body
    for(var i = 1; i <= size; i++){
        if(activeNum == i){
            pgBody += "<li><a href='javascript:onclick=pageMove("+i+")' class='active num'>"+i+"</a></li>"
        } else  {
            pgBody += "<li><a href='javascript:onclick=pageMove("+i+")' class='num'>"+i+"</a></li>"
        }
    } 

    //footer
    pgBody += "<li><a href='#' class='arrow right'>></a></li>"
    pgBody += "<li><a href='#' class='last'>마지막 페이지</a></li></ul></div>";
    return $(pgBody);
};