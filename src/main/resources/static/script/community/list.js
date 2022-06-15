const $contents = document.querySelector('.board_list');

function init() {
    fetch("/api/getCommunity", {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => res.json())
    .then(community => {
        console.log(community)
        if(community.status !== 401) {
            let str ='<div class="top">\n' +
                '                    <div class="num">번호</div>\n' +
                '                    <div class="title">제목</div>\n' +
                '                    <div class="writer">글쓴이</div>\n' +
                '                    <div class="date">작성일</div>\n' +
                '                </div>';
            for(let i=0; i<community.length; i++) {
                str += `<div>
                                <div class="num">${i+1}</div>
                                <div class="title"><a href="/community/${community[i].id}">${community[i].title}</a></div>
                                <div class="writer">${community[i].username}</div>
                                <div class="date">${community[i].write_date}</div>
                            </div>`
            }
            $contents.innerHTML = str;
        } else {
            $contents.innerHTML = '<div><div>로그인해주세요.</div></div>'
        }
    });
}

init();
