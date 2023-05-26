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

//대댓글 쓰기 모달 띄우기
let modal = null;
const showComment =  (refno) => {
    const frefno = document.querySelector("#refno");
    const cmtModal = document.querySelector("#cmtModal");

    frefno.value = refno;

    let mymodal = null; // 대댓글 모달창 띄우기
    try{
        mymodal = new bootstrap.Modal(cmtModal,{});
        modal = mymodal;
    } catch (e){}

    mymodal.show();
};

//대댓글 쓰기
const cmtbtn = document.querySelector("#cmtbtn");
cmtbtn?.addEventListener('click', () => {
    const cmtfrm = document.forms.cmtfrm;
    if (cmtfrm.reply.value === '') alert('댓글을 작성하세요!');
    else if (cmtfrm.userid.value === '') alert('작성자 없어요!');
    else if (cmtfrm.pno.value === '') alert('본문글번호 없어요!');
    else if (cmtfrm.refno.value === '') alert('대댓글번호 없어요!');
    else {
        cmtfrm.method = 'post';
        cmtfrm.action = '/pds/rreplyok';
        cmtfrm.submit();
    }
});