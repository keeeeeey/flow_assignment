window.onload = function () {
    loadFixedExtensions();
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

async function toggleFixedExtension(name, isChecked) {
    try {
        // 1. PATCH 요청
        await fetch('/api/extension/fixed', {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, isChecked })
        });

        // 2. 다시 목록 불러오기
        // await loadFixedExtensions();
    } catch (error) {
        console.error('고정 확장자 업데이트 실패:', error);
        alert('고정 확장자 저장에 실패했습니다.');
    }
}

async function loadFixedExtensions() {
    try {
        const response = await fetch('/api/extension/fixed');
        const result = await response.json();

        const container = document.getElementById('fixedContainer');
        container.innerHTML = '';

        result.data.forEach(item => {
            const id = `fixed-${item.name}`;
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.id = id;
            checkbox.checked = item.isChecked;
            checkbox.onclick = () => toggleFixedExtension(item.name, checkbox.checked);

            const label = document.createElement('label');
            label.htmlFor = id;
            label.textContent = item.name;

            container.appendChild(checkbox);
            container.appendChild(label);
        });

    } catch (error) {
        console.error('고정 확장자 목록 불러오기 실패:', error);
    }
}