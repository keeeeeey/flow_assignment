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
    if (!!value?.trim()) {
        createExtension(value);
    } else {
        alert("값을 입력해주세요.");
    }
})

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