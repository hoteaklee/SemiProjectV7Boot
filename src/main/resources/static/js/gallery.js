// 이미지 첨부 조건 검사
const checkAttachs = () => {

};
// 새글쓰기
const writebtn = document.querySelector("#writebtn");
writebtn?.addEventListener('click', () => {
    const galfrm = document.forms.galfrm;
    if (galfrm.title.value === '') alert('제목을 작성하세요!');
    else if (galfrm.content.value === '') alert('본문을 작성하세요!');
    else if (!checkAttathachs()) alert('이미지 첨부 조건 불일치!!');
    else if (grecaptcha.getResponse() === '') alert('자동가입방지를 확인하세요!');
    else {
        galfrm.method = 'post';
        galfrm.enctype = "multipart/form-data";
        galfrm.submit();
    }
});

//목록보기
//새글쓰기 버튼
const newbtn = document.querySelector("#newbtn");
newbtn?.addEventListener('click', () => {
    location.href = '/gallery/write';
});

//본문보기
