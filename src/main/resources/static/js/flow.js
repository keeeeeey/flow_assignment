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
                type: "delete",
                url: "http://localhost:8080/" + value,
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