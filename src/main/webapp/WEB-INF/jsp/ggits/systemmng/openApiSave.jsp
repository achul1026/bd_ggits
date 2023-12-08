<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <div class="main_container">
        <aside class="snb_container">
            <div class="snb_wrap">
                <h3 class="side_title">Open API 등록하기</h3>
                <div class="side_txt">Open API를 등록 합니다.</div>
            </div>
        </aside>
        <section class="main_section">
            <div class="contents_wrap">
                <div class="group">
                    <div class="group_text">API 이름</div>
                    <input type="text" placeholder="API 이름을 입력해주세요." class="input_same group_box">
                </div>
                <div class="group">
                    <div class="group_text">API 설명</div>
                    <input type="text" placeholder="API 설명을 입력해주세요" class="input_same group_box">
                </div>
                <div class="group">
                    <div class="group_text">사용자 목록</div>
                    <input type="text" placeholder="사용자를 검색해주세요." class="input_same group_box">
		            <button id="searchUserBtn"class="is-darkgreen-btn group_search_btn ml8">추가</button>
                </div>
            </div>
        </section>
    </div>
</main>
