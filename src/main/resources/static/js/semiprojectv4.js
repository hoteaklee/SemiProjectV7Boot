// header 로고 클릭 이벤트
let logo = document.querySelector('#logo');
logo.addEventListener('click', () => {
    location.href = '/';
});

// ------------------------------- login
const loginbtn = document.querySelector('#loginbtn');
const lgoutbtn = document.querySelector('#lgoutbtn');
const lgfrm = document.forms.lgnfrm;

loginbtn?.addEventListener('click', () => {
    if (lgfrm.userid.value === '') alert('아이디를 입력하세요!!');
    else if (lgfrm.passwd.value === '') alert('비밀번호를 입력하세요!!');
    else {
        lgfrm.method = 'post';
        lgfrm.action = '/login';
        lgfrm.submit();
    }
});

lgoutbtn?.addEventListener('click', () => {
    location.href = '/logout';
});
