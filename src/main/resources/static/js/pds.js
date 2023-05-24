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