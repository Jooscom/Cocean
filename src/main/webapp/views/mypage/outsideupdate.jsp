<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

<form action="outaddressupdate" method="post">
<input type="hidden" name="addressNumber" value="${outaddress.addressNumber}"/>
<table>

         <tr>
             <!--공백문자-->
             <th>이&nbsp;&nbsp;&nbsp;&nbsp;름</th>
             <th>
                 <input type="text" name="name" placeholder="이름을 입력해주세요." value="${outaddress.name}"/>
             </th>
         </tr>
         <tr>
             <th>직책</th>
             <th>
                 <input type="text" name="positionLevel" placeholder="직책을 입력해주세요." value="${outaddress.positionLevel}" />
             </th>
         </tr>
         <tr>
             <th>부서</th>
             <th>
                 <input type="text" name="departmentName"placeholder="부서를 입력해주세요." value="${outaddress.departmentName}" />
             </th>
         </tr>
          <tr>
             <th>직급</th>
             <th>
                 <input type="text" name="rankLevel" placeholder="직급을 입력해주세요."  value="${outaddress.rankLevel}"/>
             </th>
         </tr>
          <tr>
             <th>전화번호</th>
             <th>
                 <input type="text" name="phoneNumber" placeholder="전화번호를 입력해주세요." value="${outaddress.phoneNumber}"/>
             </th>
         </tr>
    
         <tr>
             <th colspan="2">
                 <input type="submit" value="수정" />
                 <input type="button" onclick="location.href='./list'" value="리스트"/><!-- 수정하기 주소 따로 가져오기 -->
             </th> 
     </table>
 </form>     
        
</body>
<script>





</script>
</html>