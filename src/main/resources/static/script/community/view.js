let id = window.location.href.split('community/')[1];
let username = '';

function init() {
    fetch("/api/getCommunity/" + id, {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => res.json())
    .then(community => {
        console.log(community)
        if(community.status !== 401) {
            document.getElementById('id').innerText = community.id;
            document.getElementById('username').innerText = community.username;
            document.getElementById('title').innerText = community.title;
            document.getElementById('write_date').innerText = community.write_date;
            document.getElementById('content').innerText = community.content;
        } else {
            $contents.innerHTML = '<div><div>로그인해주세요.</div></div>'
        }
    });

    fetch("/api/currentUsername", {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => {
        if(res.status !== 401) {
            res.text().then(function(name) {
                username = name;
                if(username === document.getElementById('username').innerText) {
                    document.querySelector('.bt_wrap').innerHTML += `<a href="/community/edit/${id}">수정</a><button type="button" id="delete">삭제</button>`;
                    const $btn = document.getElementById('delete');
                    $btn.addEventListener('click', () => {
                        if(confirm('삭제하시겠습니까?')) {
                            fetch("/api/deleteCommunity/" + id, {
                                method: 'delete',
                                headers: {
                                    'content-type': 'application/json',
                                    'Authorization': localStorage.getItem('jwt')
                                }
                            }).then(res => res.text().then(function(msg) {
                                alert(msg);
                                location.href='/community/board';
                            }))
                        }
                    })
                }
            })
        }
    });
}

init();