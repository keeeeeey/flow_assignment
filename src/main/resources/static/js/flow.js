const checkboxList = $('.fix-item');

checkboxList.on('click', function () {
    const value = $(this).prop('name');
    if ($(this).prop('checked')) {
        createExtension(value);
    } else {
        deleteExtension(value);
    }
});

const addBtn = $('#add-btn');
addBtn.on('click', function () {
    const inputBox = $('#extension-name');
    const inputData = inputBox.val();

    if (inputData.length > 20) {
        alert("글자 수는 20자 이하만 가능합니다.");
        return;
    }

    let size = Number($("#extension-size").text());
    if (size >= 200) {
        alert("커스텀 확장자는 최대 200개만 추가가 가능합니다.");
        return;
    }

    if (!!inputData.trim()) {
        createExtension(inputData).then(function () {
            createHtmlElement(inputData);
            $("#extension-size").text(size += 1);
        });
    } else {
        alert("값을 입력해주세요.");
    }

    inputBox.val("");
});

function deleteCustomExtension(param) {
    deleteExtension(param).then(function () {
        deleteHtmlElement(param);
        let size = Number($("#extension-size").text());
        $("#extension-size").text(size -= 1);
    });
}

function createExtension(param) {
    const data = {
        'name': param
    };

    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "POST",
            url: "/",
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            success: function (res) {
                console.log(res);
                resolve();
            },
            error: function (e) {
                if (e.responseJSON.code === 'E0007') {
                    alert("이미 차단된 확장자입니다.");
                } else if (e.responseJSON.code === 'E0005') {
                    alert("유효하지 않는 입력값입니다.");
                } else {
                    alert("저장 실패");
                }
                reject(e);
            }
        });
    });
}

function deleteExtension(param) {
    return new Promise(function (resolve, reject) {
        $.ajax({
            type: "DELETE",
            url: "/" + param,
            success: function (res) {
                resolve();
            },
            error: function (e) {
                alert("이미 삭제된 차단 확장자입니다.");
                reject(e);
            }
        });
    });
}

function createHtmlElement(param) {
    const parentElement = $(".select-box-right-list");
    const newElement = $("<span>").html(param + " ").addClass("extension-element").attr("id", param);
    const closeBtn = $("<span>").html("&times").addClass("x-btn").on("click", function () {
        deleteCustomExtension(param);
    });
    newElement.append(closeBtn);
    parentElement.append(newElement);
}

function deleteHtmlElement(param) {
    const deleteElement = $("#" + param);
    deleteElement.remove();
}
