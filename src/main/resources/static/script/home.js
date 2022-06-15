const $login = document.getElementById('login');
let length = 0;
function showPopup() {window.open("/write_plan", "플래너", "width=600, height=300, left=100, top=50");}
function showProfile() {window.open("/profile", "프로필", "width=800, height=600, left=100, top=50");}
function init() {
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
                for(let i=0; i<Object(plan).length; i++) {
                    if(!plan[i].activate) {
                        length ++;
                    }
                }
                document.getElementById('planCnt').innerHTML = length;
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
            res.text().then(function(username) {
                document.getElementById('username').innerHTML = '환영합니다.    '+ username +'님    <button id="logout">logout</button>';
                const $logout = document.getElementById('logout');
                $logout.addEventListener('click', () => {
                    localStorage.removeItem('jwt');
                    location.href = '/login';
                });
            })
        }
    });

    fetch("/api/getStudyTime/" + new Date().toISOString().substring(0, 10), {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => res.json())
        .then(studyTime => {
            if(studyTime.status !== 401) {
                document.getElementById('studyTime').innerHTML = Math.ceil(studyTime.time / 3600) + 'h';
            }
        });

}

$login.addEventListener('click', () => {
    location.href = '/login';
})

init();