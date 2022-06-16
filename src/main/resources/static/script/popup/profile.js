const $day = ['월', '화', '수', '목', '금', '토', '일'];
const $graph = document.querySelector('.axis_x');

function getMonday(d) {
    d = new Date(d);
    let day = d.getDay(),
        diff = d.getDate() - day + (day == 0 ? -6:1);
    return diff;
}

function init() {
    fetch("/api/getStudyTime", {
        method: 'get',
        headers: {
            'content-type': 'application/json',
            'Authorization': localStorage.getItem('jwt')
        }
    }).then(res => res.json())
    .then(studyTime => {
        let day = parseInt(getMonday(new Date()));
        let index = 0;
        let str = '';
        let time = 0;
        let timeTotal = 0;
        let dataType = '';
        console.log(studyTime);
        for(let i=0; i<7; i++) {
            if(studyTime.length === 0) {
                str = `<li class="item">
                        <div class="text_box">
                          <strong class="day">${day}(${$day[i]})</strong>
                          <span class="time">0시간</span>
                        </div>
                        <button type="button" class="graph">
                          <span class="time data1" style="0%;"><span class="blind">data1</span></span>
                        </button>
                      </li>`
                day ++;
                $graph.innerHTML += str;
                continue;
            }
            if(index !== 6) {
                if(parseInt(studyTime[index].date.split('-')[2]) === day) {
                    time = studyTime[index].time/3600;
                    if(time >= 0 && time < 3) {
                        dataType = 'data1';
                    } else if(time >= 3 && time < 6) {
                        dataType = 'data2';
                    } else if(time >= 6 && time < 9) {
                        dataType = 'data3';
                    } else {
                        dataType = 'data4';
                    }
                    str = `<li class="item">
                        <div class="text_box">
                          <strong class="day">${day}(${$day[i]})</strong>
                          <span class="time">${(time < 0) ? '1시간 미만': '약' + Math.ceil(time) + '시간'}</span>
                        </div>
                        <button type="button" class="graph">
                          <span class="time ${dataType}" style="height:${(time/12 * 100 > 100) ? 100 : time/12 * 100}%;"><span class="blind">${dataType}</span></span>
                        </button>
                      </li>`
                    index ++;
                    day ++;
                    timeTotal += time;
                    $graph.innerHTML += str;
                } else {
                    str = `<li class="item">
                        <div class="text_box">
                          <strong class="day">${day}(${$day[i]})</strong>
                          <span class="time">0시간</span>
                        </div>
                        <button type="button" class="graph">
                          <span class="time data1" style="0%;"><span class="blind">data1</span></span>
                        </button>
                      </li>`
                    day ++;
                    $graph.innerHTML += str;
                }
            } else {
                str = `<li class="item">
                        <div class="text_box">
                          <strong class="day">${day}(${$day[i]})</strong>
                          <span class="time">0시간</span>
                        </div>
                        <button type="button" class="graph">
                          <span class="time data1" style="0%;"><span class="blind">data1</span></span>
                        </button>
                      </li>`
                day ++;
                $graph.innerHTML += str;
            }
        }
        document.getElementById('total').innerText = timeTotal.toFixed(1) + '시간';
        document.getElementById('average').innerText = ((studyTime.length === 0) ? 0:(timeTotal/Object(studyTime.length)).toFixed(1)) + '시간';
    });
}

init();