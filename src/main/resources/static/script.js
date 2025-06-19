window.onload = function () {
    loadCustomExtensions();
};

async function loadCustomExtensions() {
    try {
        const response = await fetch('/api/extension/custom');
        if (!response.ok) throw new Error("불러오기 실패");

        const result = await response.json();
        const extensions = result.data || [];
        const container = document.getElementById('tagContainer');
        container.innerHTML = ''; // 초기화

        extensions.forEach(ext => {
            const span = document.createElement('span');
            span.className = 'tag';
            span.setAttribute('data-id', ext.id);
            span.innerHTML = `${ext.name} <button onclick="removeTag(this)">×</button>`;
            container.appendChild(span);
        });

        updateCount();
    } catch (err) {
        console.error('불러오기 오류:', err);
        alert("커스텀 확장자 목록을 불러오는 데 실패했습니다.");
    }
}

async function addExtension() {
    const input = document.getElementById('customExtInput');
    const value = input.value.trim();
    const container = document.getElementById('tagContainer');

    if (!value) return;

    try {
        const response = await fetch('/api/extension/custom', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ name: value })
        });

        if (!response.ok) {
            const error = await response.json();
            alert("추가 실패: " + (error.message || response.statusText));
            return;
        }

        // 성공 시 태그 추가
        const span = document.createElement('span');
        span.className = 'tag';
        span.innerHTML = `${value} <button onclick="removeTag(this)">×</button>`;
        container.appendChild(span);
        updateCount();
        input.value = '';
    } catch (error) {
        console.error('API 호출 중 오류 발생:', error);
        alert("서버 오류가 발생했습니다.");
    }
}

async function removeTag(button) {
    const tag = button.parentElement;
    const id = tag.getAttribute('data-id');

    try {
        const response = await fetch(`/api/extension/custom/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            const error = await response.json();
            alert("삭제 실패: " + (error.message || response.statusText));
            return;
        }

        tag.remove();
        updateCount();
    } catch (err) {
        console.error('삭제 오류:', err);
        alert("서버 오류가 발생했습니다.");
    }
}

function updateCount() {
    const count = document.querySelectorAll('#tagContainer .tag').length;
    document.getElementById('extCount').textContent = `${count}/200`;
}