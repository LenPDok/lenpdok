const myUL = document.getElementById('myUL');
const $btn = document.querySelector('.addBtn');

function save() {
    let data = document.getElementById('myInput').value;
    fetch("/api/savePlan", {
        method: 'post',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        },
        body: data
    }).then( res => {
        if(res.status === 401) {
            alert('권한이 없습니다.');
        } else {
            res.text().then(mes => alert(mes));
            location.reload();
        }
    })
}

function checked(id) {
    fetch("/api/checkPlan/" + id, {
        method: 'put',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then( res => {
        if(res.status === 401) {
            alert('권한이 없습니다.');
        }
    })
}

function display() {
    fetch("/api/getPlan", {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => res.json())
    .then(plan => {
        console.log(plan)
        if(plan.status !== 401) {
            let str = '';
            for(let i = 0; i< Object.keys(plan).length; i++) {
                str += `<li id="${plan[i].id}" class="${(plan[i].activate) ? 'checked': ''}">${plan[i].title}<span class="close" id="${plan[i].id}">\u00D7</span></li>`;
            }
            myUL.innerHTML = str;

            let $list = document.querySelector('ul');
            $list.addEventListener('click', function(ev) {
                if (ev.target.tagName === 'LI') {
                    if(ev.target.className === 'checked') {
                        ev.target.className = '';
                        checked(ev.target.id);
                    } else {
                        ev.target.className = 'checked';
                        checked(ev.target.id);
                    }
                }
            }, false);

            let $close = document.querySelectorAll('.close');
            for(let i=0; i<$close.length; i++) {
                $close[i].addEventListener('click', (ev) => {
                    if(confirm('삭제하시겠습니까?')) {
                        fetch("/api/deletePlan/" + ev.target.id, {
                            method: 'delete',
                            headers: {
                                'content-type': 'application/json',
                                'Authorization': localStorage.getItem('jwt')
                            }
                        }).then( res => {
                            if(res.status === 401) {
                                alert('권한이 없습니다.');
                            } else {
                                res.text().then(mes => alert(mes));
                                location.reload();
                            }
                        })
                    }
                })
            }
        }
    });
}

$btn.addEventListener('click', save);

display();