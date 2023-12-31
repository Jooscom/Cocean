<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<title>Insert title here</title>
<style>
table, th, td{
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px 10px;
}

</style>
</head>
<body>


<jsp:include page="../side.jsp"></jsp:include>

	
	

	<label for="date">
        <input type="date" id="pfirstsearchdate" value="" />
        ~
        <input type="date" id="plastsearchdate" value="" />
        <input type="button" id="psearchButton" class="comm-btn" value="검색" />
    </label>
	<table>
		<thead>
        	<tr>
        		<th>번호</th>
				<th>날짜</th>
				<th>출근시간</th>
				<th>퇴근시간</th>
        	</tr>
		</thead>
    	<tbody id="worklist">
    	</tbody>
	</table>

	
	<!--  
<form action="gowork" method="get">
        <input type="submit" value="출석" name="goButton">
    </form>
    -->
    <form method="post" action="/save-timestamp">
        <button type="submit">출근 버튼</button>
    </form>
    <p th:text="${msg}"></p>
    
    <form action="leavework" method="get">
        <input type="submit" value="퇴근" name="leaveButton">
    </form>
	
	
</body>
<script>
const today = new Date();
const yesterday = new Date(today);
yesterday.setDate(today.getDate() - 1);

const year = today.getFullYear();
const month = (today.getMonth() + 1).toString().padStart(2, '0'); 
const firstDayOfMonth = year + '-' + month + '-01';


const formattedYesterday = yesterday.getFullYear() + '-' + (yesterday.getMonth() + 1).toString().padStart(2, '0') + '-' + yesterday.getDate().toString().padStart(2, '0');

function formatDateFromTimestamp(timestamp) {
    var date = new Date(timestamp);
    var year = date.getFullYear(); 
    var month = ('0' + (date.getMonth() + 1)).slice(-2);
    var day = ('0' + date.getDate()).slice(-2);
    return year + '-' + month + '-' + day;
}



//두 개의 input 요소에 formattedYesterday 값을 설정
const pfirstSearchDateInput = document.getElementById('pfirstsearchdate');
const plastSearchDateInput = document.getElementById('plastsearchdate');

pfirstSearchDateInput.value = firstDayOfMonth;
plastSearchDateInput.value = formattedYesterday;

drawwork(pfirstSearchDateInput.value,plastSearchDateInput.value);


function drawwork(pfirstSearchDate, plastSearchDate) {
    $.ajax({
        type: 'GET',
        url: 'worklist',
        data: { 'pfirstsearchdate': pfirstSearchDate, 'plastsearchdate': plastSearchDate },
        dataType: 'json',
        success: function (data) {
        	console.log(data)
        	var content ='';   	
        	data.work.forEach(function(item, idx){
	    		idx += 1;
	    		content +='<tr>';
	    		content +='<td>'+item.workID+'</td>';
	    		content +='<td>'+item.workDate+'</td>';
	    		content +='<td>'+item.gowork.substring(11,19)+'</td>';
	    		content +='<td>'+item.leavework.substring(11,19)+'</td>';
	    		content +='</tr>';
	    	});
        	$('#worklist').empty();
			$('#worklist').append(content);
        },
        error: function (error) {
            console.error(error);
        }
    });
}

//출퇴근 날짜 검색
document.getElementById('psearchButton').addEventListener('click', function () {
	  const pfirstSearchDate = pfirstSearchDateInput.value;
	  const plastSearchDate = plastSearchDateInput.value;
	  drawwork(pfirstSearchDate, plastSearchDate);
	});


























</script>
</html>