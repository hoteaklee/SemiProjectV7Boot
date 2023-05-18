const findtype = document.querySelector("#findtype");
const findkey = document.querySelector("#findkey");
const findbtn = document.querySelector("#findbtn");
const newbtn = document.querySelector("#newbtn");

findbtn?.addEventListener('click', () => {
    if (findkey.value === '') { alert('검색어를 입력하세요!!'); return; }
    let query = `/board/find?ftype=${findtype.value}&fkey=${findkey.value}&cpg=1`;
    location.href = query;
});

newbtn?.addEventListener('click', () => {
   location.href = '/board/write';
});


const writebtn = document.querySelector("#writebtn");
writebtn?.addEventListener('click', () => {
    const bdfrm = document.forms.bdfrm;
    if (bdfrm.title.value === '') alert('제목을 작성하세요!');
    else if (bdfrm.content.value === '') alert('본문을 작성하세요!');
    else if (grecaptcha.getResponse() === '') alert('자동가입방지를 확인하세요!');
    else {
        bdfrm.method = 'post';
        bdfrm.submit();
    }
});

const prevbtn = document.querySelector("#prevbtn");
const nextbtn = document.querySelector("#nextbtn");
const listbtn = document.querySelector("#listbtn");
prevbtn?.addEventListener('click', () => {});

nextbtn?.addEventListener('click', () => {});

listbtn?.addEventListener('click', () => {
    location.href = '/board/list?cpg=1'; });
