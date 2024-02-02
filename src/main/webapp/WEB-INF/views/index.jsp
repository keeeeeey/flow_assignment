<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <title>Spring Boot Application</title>
</head>
<body>
    <div class="body-container">
        <div class="fix-box">
            <div class="fix-box-left">
                고정 확장자
            </div>
            <div class="fix-box-right">
                <c:choose>
                    <c:when test="${not empty bat}">
                        <input type="checkbox" id="bat" class="fix-item" name="bat" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" id="bat" class="fix-item" name="bat">
                    </c:otherwise>
                </c:choose>
                <label for="bat">bat</label>

                <c:choose>
                    <c:when test="${not empty cmd}">
                        <input type="checkbox" id="cmd" class="fix-item" name="cmd" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" id="cmd" class="fix-item" name="cmd">
                    </c:otherwise>
                </c:choose>
                <label for="cmd">cmd</label>

                <c:choose>
                    <c:when test="${not empty com}">
                        <input type="checkbox" id="com" class="fix-item" name="com" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" id="com" class="fix-item" name="com">
                    </c:otherwise>
                </c:choose>
                <label for="com">com</label>

                <c:choose>
                    <c:when test="${not empty cpl}">
                        <input type="checkbox" id="cpl" class="fix-item" name="cpl" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" id="cpl" class="fix-item" name="cpl">
                    </c:otherwise>
                </c:choose>
                <label for="cpl">cpl</label>

                <c:choose>
                    <c:when test="${not empty exe}">
                        <input type="checkbox" id="exe" class="fix-item" name="exe" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" id="exe" class="fix-item" name="exe">
                    </c:otherwise>
                </c:choose>
                <label for="exe">exe</label>

                <c:choose>
                    <c:when test="${not empty scr}">
                        <input type="checkbox" id="scr" class="fix-item" name="scr" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" id="scr" class="fix-item" name="scr">
                    </c:otherwise>
                </c:choose>
                <label for="scr">scr</label>

                <c:choose>
                    <c:when test="${not empty js}">
                        <input type="checkbox" id="js" class="fix-item" name="js" checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" id="js" class="fix-item" name="js">
                    </c:otherwise>
                </c:choose>
                <label for="js">js</label>
            </div>
        </div>
        <div class="select-box">
            <div class="select-box-left">
                커스텀 확장자
            </div>
            <div class="select-box-right">
                <div class="select-box-right-input">
                    <input type="text" placeholder="확장자 입력" id="extension-name">
                    <input type="submit" value="+추가" id="add-btn">
                </div>
                <div class="select-box-right-list">
                    <div class="size-box">
                        <span id="extension-size">${size}</span>/200
                    </div>
                    <c:if test="${not empty extensionList}">
                        <c:forEach var="extension" items="${extensionList}">
                            <span class="extension-element" id="${extension.name}">
                                ${extension.name} <span class="x-btn" onclick="deleteCustomExtension('${extension.name}')">&times;</span>
                            </span>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="/js/flow.js"></script>
</body>
</html>