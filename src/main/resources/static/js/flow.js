const checkboxList = document.querySelectorAll('.fix-item');
for (let i = 0; i < checkboxList.length; i++) {
    const value = checkboxList[i].name;
    checkboxList[i].addEventListener('click', function() {
        if ($(this).prop('checked')) {
            const data = {
                'name': value
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
                }
            })
        } else {
            $.ajax({
                type: "DELETE",
                url: "/" + value,
                success: function (res) {
                    console.log(res);
                },
                error: function (e) {
                    console.log(e);
                }
            })
        }
    })
}

const addBtn = document.querySelector('#add-btn');
addBtn.addEventListener("click", function () {
    const value = document.querySelector('#extension-name').value;
    if (!!value?.trim()) {
        const data = {
            'name': value
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
            }
        })
    } else {
        alert("값을 입력해주세요.");
    }
})

function deleteExtension (param) {
    $.ajax({
        type: "DELETE",
        url: "/" + param,
        success: function (res) {
            console.log(res);
        },
        error: function (e) {
            console.log(e);
        }
    })
}