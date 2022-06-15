const $btn = document.querySelector('.on');
const $title = document.getElementById('title');
const $content = document.getElementById('content');

function write() {
    let titleValue = $title.value;
    let contentValue = $content.value;
    let data = {};
    fetch("/api/currentUsername", {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => {
        if(res.status !== 401) {
            res.text().then(function(name) {
                data = {
                    title : titleValue,
                    username : name,
                    content : contentValue
                }
                fetch("/api/writeCommunity", {
                    method: 'post',
                    headers: {
                        'content-type': 'application/json',
                        'Authorization': localStorage.getItem('jwt')
                    },
                    body: JSON.stringify(data)
                }).then(res => res.text().then((msg) => {
                    alert(msg);
                    location.href='/community/board';
                }))
            })
        }
    });
}

$btn.addEventListener('click', write);