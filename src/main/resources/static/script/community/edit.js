let communityId = window.location.href.split('edit/')[1];
const $btn = document.querySelector('.on');
const $title = document.getElementById('title');
const $content = document.getElementById('content');

function init() {
    fetch("/api/getCommunity/" + communityId, {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => res.json())
    .then(community => {
        console.log(community)
        if(community.status !== 401) {
            document.getElementById('title').value = community.title;
            document.getElementById('content').value = community.content;
        }
    });
}

function update() {
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
                    id : communityId,
                    title : titleValue,
                    username : name,
                    content : contentValue
                }
                fetch("/api/updateCommunity", {
                    method: 'put',
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

$btn.addEventListener('click', update);

init();