<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main class="main">
    <div class="main_container">
        <c:import url="/WEB-INF/jsp/ggits/common/inc_map_control.jsp"></c:import>
    </div>
</main>
<script>

    // const map = new GITSMapCore("map").init("성남시");
    window.map = new GITSMapCore("map").init();
    $(function(){
        gitsApp.setMap(map);
    })
</script>