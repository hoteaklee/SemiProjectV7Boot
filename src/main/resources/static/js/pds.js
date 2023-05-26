// 새글쓰기
const writebtn = document.querySelector("#writebtn");
writebtn?.addEventListener('click', () => {
    const pdsfrm = document.forms.pdsfrm;
    if (pdsfrm.title.value === '') alert('제목을 작성하세요!');
    else if (pdsfrm.content.value === '') alert('본문을 작성하세요!');
    else if (grecaptcha.getResponse() === '') alert('자동가입방지를 확인하세요!');
    else {
        pdsfrm.method = 'post';
        pdsfrm.enctype = "multipart/form-data";
        pdsfrm.submit();
    }
});

//목록보기
//새글쓰기 버튼
const newbtn = document.querySelector("#newbtn");
newbtn?.addEventListener('click', () => {
    location.href = '/pds/write';
});

//본문보기
//댓글쓰기
const rpnewbtn = document.querySelector("#rpnewbtn");
rpnewbtn?.addEventListener('click', () => {
    const rpfrm = document.forms.rpfrm;
    if (rpfrm.reply.value === '') alert('댓글을 작성하세요!');
    else if (rpfrm.userid.value === '') alert('작성자 없어요!');
    else if (rpfrm.pno.value === '') alert('본문글번호 없어요!');
    else {
        rpfrm.method = 'post';
        rpfrm.action = '/pds/replyok';
        rpfrm.submit();
    }
});