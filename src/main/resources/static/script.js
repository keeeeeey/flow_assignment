$(window).on('load', function () {
    loadFixedExtensions();
    loadCustomExtensions();
});

function loadCustomExtensions() {
    $.ajax({
        url: '/api/extension/custom',
        method: 'GET',
        success: function (result) {
            const extensions = result.data || [];
            const $container = $('#tagContainer').empty();

            $.each(extensions, function (_, ext) {
                const $span = $('<span>', {
                    class: 'tag',
                    'data-id': ext.id,
                    html: `${ext.name} <button onclick="removeTag(this)">×</button>`
                });
                $container.append($span);
            });

            updateCount();
        },
        error: function (xhr) {
            console.error('불러오기 오류:', xhr);
            alert("커스텀 확장자 목록을 불러오는 데 실패했습니다.");
        }
    });
}

function addCustomExtension() {
    const value = $('#customExtInput').val().trim();
    const $container = $('#tagContainer');

    if (!value) return;

    $.ajax({
        url: '/api/extension/custom',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ name: value }),
        success: function (result) {
            const id = result.data.id;

            const $span = $('<span>', {
                class: 'tag',
                'data-id': id,
                html: `${value} <button onclick="removeTag(this)">×</button>`
            });

            $container.append($span);
            updateCount();
            $('#customExtInput').val('');
        },
        error: function (xhr) {
            let message = '추가 실패';

            try {
                const error = xhr.responseJSON;
                message = `${error.message}\n`;

                if (Array.isArray(error.errors) && error.errors.length > 0) {
                    message += "\n아래와 같은 이유로 추가 실패:\n";
                    $.each(error.errors, function (_, err) {
                        message += `- ${err.reason}\n`;
                    });
                }
            } catch (e) {
                console.error('응답 파싱 실패:', e);
            }

            alert(message);
            loadCustomExtensions();
        }
    });
}

function removeTag(button) {
    const $tag = $(button).parent();
    const id = $tag.data('id');

    $.ajax({
        url: `/api/extension/custom/${id}`,
        method: 'DELETE',
        success: function () {
            $tag.remove();
            updateCount();
        },
        error: function (xhr) {
            console.error('삭제 오류:', xhr);
            alert("삭제 실패: " + (xhr.responseJSON?.message || '알 수 없는 오류'));
            loadCustomExtensions();
        }
    });
}

function updateCount() {
    const count = $('#tagContainer .tag').length;
    $('#extCount').text(`${count}/200`);
}

function loadFixedExtensions() {
    $.ajax({
        url: '/api/extension/fixed',
        method: 'GET',
        success: function (result) {
            const $container = $('#fixedContainer').empty();

            $.each(result.data, function (_, item) {
                const id = `fixed-${item.name}`;
                const $checkbox = $('<input>', {
                    type: 'checkbox',
                    id: id,
                    checked: item.isChecked
                }).on('click', function () {
                    toggleFixedExtension(item.name, this.checked);
                });

                const $label = $('<label>', {
                    for: id,
                    text: item.name
                });

                $container.append($checkbox).append($label);
            });
        },
        error: function (xhr) {
            console.error('고정 확장자 목록 불러오기 실패:', xhr);
        }
    });
}

function toggleFixedExtension(name, isChecked) {
    $.ajax({
        url: '/api/extension/fixed',
        method: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({ name, isChecked }),
        error: function (xhr) {
            console.error('고정 확장자 업데이트 실패:', xhr);
            alert("업데이트 실패: " + (xhr.responseJSON?.message || '알 수 없는 오류'));
            loadFixedExtensions();
        }
    });
}