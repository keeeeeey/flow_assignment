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
                <input type="checkbox" id="bat" class="fix-item" name="bat">
                <label for="bat">bat</label>
                <input type="checkbox" id="cmd" class="fix-item" name="cmd">
                <label for="cmd">cmd</label>
                <input type="checkbox" id="com" class="fix-item" name="com">
                <label for="com">com</label>
                <input type="checkbox" id="cpl" class="fix-item" name="cpl">
                <label for="cpl">cpl</label>
                <input type="checkbox" id="exe" class="fix-item" name="exe">
                <label for="exe">exe</label>
                <input type="checkbox" id="scr" class="fix-item" name="scr">
                <label for="scr">scr</label>
                <input type="checkbox" id="js" class="fix-item" name="js">
                <label for="js">js</label>
            </div>
        </div>
        <div class="select-box">
            <div class="select-box-left">
                커스텀 확장자
            </div>
            <div class="select-box-right">
                <div class="select-box-right-input">
                    <form action="">
                        <input type="text" placeholder="확장자 입력">
                    </form>
                    <button>+추가</button>
                </div>
                <div class="select-box-right-textarea">
                    <textarea name="extension-list" id="extension-list" cols="30" rows="10"></textarea>
                </div>
            </div>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="/js/flow.js"></script>
</body>
</html>