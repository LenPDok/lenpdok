function login() {
    let $name = document.querySelector("#username-field");
    let $pwd = document.querySelector("#password-field");
    fetch("/api/authenticate", {
        method: 'post',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify({
            username: $name.value,
            password: $pwd.value
        })
    }).then(res => res.json())
        .then(token => {
            if(token.status !== 401) {
                localStorage.setItem("jwt", "Bearer " + token.token);
                alert("로그인 되었습니다");
                location.href = "/main";
            } else {
                alert("아이디 또는 비밀번호를 확인해주세요.");
            }
        });
}

const $loginButton = document.querySelector("#login-form-submit");
$loginButton.addEventListener("click", login);

if(localStorage.getItem('jwt')) {
    localStorage.removeItem('jwt');
}