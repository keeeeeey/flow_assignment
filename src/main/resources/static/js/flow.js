const checkboxList = document.querySelectorAll('.fix-item');
for (let i = 0; i < checkboxList.length; i++) {
    const value = checkboxList[i].name;
    checkboxList[i].addEventListener('click', function() {
        if ($(this).prop('checked')) {
            createExtension(value);
        } else {
            deleteExtension(value);
        }
    })
}

const addBtn = document.querySelector('#add-btn');
addBtn.addEventListener("click", function () {
    const value = document.querySelector('#extension-name').value;

    if (value.length > 20) {
        alert("글자 수는 20자 이하만 가능합니다.");
        return;
    }

    let size = Number(document.querySelector("#extension-size").textContent);
    if (size >= 200) {
        alert("커스텀 확장자는 최대 200개만 추가가 가능합니다.");
        return;
    }

    if (!!value?.trim()) {
        createExtension(value);
        createHtmlElement(value);
        $("#extension-size").text(size += 1);
    } else {
        alert("값을 입력해주세요.");
    }
})

function deleteCustomExtension (param) {
    deleteExtension(param);
    deleteHtmlElement(param);
    let size = Number(document.querySelector("#extension-size").textContent);
    $("#extension-size").text(size -= 1);
}

function createHtmlElement (param) {
    const parentElement = document.querySelector(".select-box-right-list");
    const newElement = document.createElement("span");
    newElement.innerHTML = param + " ";
    newElement.className = "extension-element";
    newElement.id = param;
    const closeBtn = document.createElement("span");
    closeBtn.innerHTML = "&times";
    closeBtn.className = "x-btn";
    closeBtn.onclick = function () {
        deleteCustomExtension(param);
    }
    newElement.appendChild(closeBtn);
    parentElement.appendChild(newElement);
}

function deleteHtmlElement (param) {
    const parentElement = document.querySelector(".select-box-right-list");
    const deleteElement = document.querySelector("#" + param);
    parentElement.removeChild(deleteElement);
}

function createExtension (param) {
    const data = {
        'name': param
    }

    $.ajax({
        type: "POST",
        url: "/",
        data: JSON.stringify(data),
        contentType : 'application/json; charset=utf-8',
        success: function (res) {
            console.log(res);
        },
        error: function (e) {
            console.log(e);
            alert("이미 차단된 확장자입니다.");
        }
    })
}

function deleteExtension (param) {
    $.ajax({
        type: "DELETE",
        url: "/" + param,
        success: function (res) {
            console.log(res);
        },
        error: function (e) {
            console.log(e);
            alert("존재하지 않은 차단 확장자입니다.");
        }
    })
}