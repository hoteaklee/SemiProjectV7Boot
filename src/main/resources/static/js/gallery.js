// 이미지 첨부 조건 검사
const checkAttachs = () => {
    let checkOk = true;
    const attachs = document.querySelector("#attachs");

    // 이미지 첨부파일이 하나 이상이라면
    if ( 'files' in attachs && attachs.files.length > 0){ // 첨부파일에 접근할수 있고 && 첨부파일이 하나 이상이라면 뜻
        // 첨부파일들이 이미지인지 검사
        for (attach of attachs.files){
        console.log(attach.name + ',' + attach.type + ',' + attach.size);
        //이미지파일의 MIME 형식 : image/jpg, image/png, image/gif,image/jpeg
            if (!attach.type.startsWith('image')) { //어테치 타입이 이미지로 시작하지 않는다면
                alert('첨부하려는 파일은 반드시 이미지여야 합니다 !!');
                checkOk = false;
            }
        }
    } else {
        alert('하나 이상의 이미지를 선택하세요!!!')
        checkOk = false
    }

    return checkOk;
};
// 새글쓰기
const writebtn = document.querySelector("#writebtn");
writebtn?.addEventListener('click', () => {
    const galfrm = document.forms.galfrm;
    if (galfrm.title.value === '') alert('제목을 작성하세요!');
    else if (galfrm.content.value === '') alert('본문을 작성하세요!');
    else if (!checkAttachs()) alert('이미지 첨부 조건 불일치!!');
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
