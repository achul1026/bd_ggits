<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${0 < paging.totalCount}">
    <div class="dashboard-pg-wrap">
		<ul class="dashboard-pg">
          <li><a href="javascript:pageMove('<c:out value='${paging.prevPageNo}'/>')" class="arrow left"> &lt; </a></li>
           <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
                <c:choose>
                    <c:when test="${i eq paging.pageNo}">
           			  <li><a href="javascript:pageMove('<c:out value='${i}'/>')" class="active num"><c:out value='${i}'/></a></li>
                    </c:when>
                    <c:otherwise>
                      <li><a href="javascript:pageMove('<c:out value='${i}'/>')" class="num"><c:out value='${i}'/></a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
          <li><a href="javascript:pageMove('<c:out value='${paging.nextPageNo}'/>')" class="arrow right"> &gt; </a></li>
		</ul>
	</div>
</c:if>