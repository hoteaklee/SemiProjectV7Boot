package zzyzzy.springboot.semiprojectv7.utils;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import zzyzzy.springboot.semiprojectv7.model.GalAttach;
import zzyzzy.springboot.semiprojectv7.model.PdsAttach;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("galutils")
public class GalleryUtils {
    //이미지 파일  저장 위치
    @Value("${saveImgDir}") private String saveImgDir;

    public String makeUUID() {
        String uuid = LocalDate.now() + "" + LocalTime.now();
        uuid = uuid.replace("-", "")
                .replace(":", "").replace(".", "");
        return uuid;
    }


    public GalAttach processUpload(List<MultipartFile> attachs, Map<String, Object> ginfo) {
        // 이미지 첨부파일명과 사이지는 먼저, 리스트 형태로 저장했다가 문자열로 변환
        //업로드할 파일 정보 취득
        GalAttach ga = new GalAttach();
        ga.setGno((Integer) ginfo.get("gno"));
        List<String> fnames = new ArrayList<>();
        List<String> fsizes = new ArrayList<>();

        // 첨부된 파일들에 대한 반복처리
        for (MultipartFile attach : attachs) {
            String fname = attach.getOriginalFilename();

            // 파일 확장자 크기 추출
            int pos = fname.lastIndexOf(".") + 1; //맨마지막 점위치를 찾고 점위치 에서 +1해줘서 다음칸부터 출력
            String ext = fname.substring(pos);
            String fsize = (attach.getSize() / 1024) + "";// + "" (문자로 저장하기우해써줌)

            // 저장시 사용할 파일이름 생성
            // 파일이름 형식 : 파일이름UUID.확장자
            String savefname = fname.substring(0, pos - 1);   //파일이름 추출
            String fullname = savefname +  ginfo.get("uuid") + "." + ext;
            savefname = saveImgDir + fullname;

            try {
                //첨부파일을 파일시스템에 저장
                attach.transferTo(new File(savefname)); // 업로드한 파일 저장하기

                //첨부파일 정보를 리스트에 저장
                fnames.add(fullname);  // 파일이름UUID.확장자 가 저장됨
                fsizes.add(fsize);
                System.out.println(fname + "," + fsize + "," + savefname);
            } catch (Exception ex) {
                System.out.println("업로드중 오류 발생!!");
                ex.printStackTrace();
            }
        } //for
        // 수집된 첨부파일 정보를 ga에 저장
        // join( 구분자, 리스트변수 ) : 요소 1;요소 2;요소 3;
        ga.setFname( String.join(";", fnames) );
        ga.setFsize( String.join(";", fsizes) );
        System.out.println(ga.getFname() + " " + ga.getFsize() );

        return ga;



    }

    public void makeThumbail(GalAttach ga, Object uuid) {
        // 업로드된 이미지들 중 첫번째 이미지에 대해 썸내일 생성
        // 파일이름들을 ; 으로 나눈 뒤 첫번째 파일명만 추출
        String basename = ga.getFname().split(";")[0]; //세미콜론으로 나줘서 0번쨰꺼를 가져옴

        //서버에 업로드된 파일 경로로 재정의 : 썸내일 생성시 참고할 이미지파일
        String refname = saveImgDir + basename;

        // 썸내일 이미지 경로 정의
        String thumbname = saveImgDir + "_thumbs/small_" + basename;

        System.out.println("refname="+ refname + "thumbname="+thumbname);

        try {
            //원본이미지를 읽어서 메모리에 이미지객체(캠퍼스)를 생성
            BufferedImage img = ImageIO.read(new File(refname)); //이미지파일을 읽어서 버퍼함

            int imgW = Math.min(img.getHeight(), img.getWidth()) / 2 ;  // 2로 나누면 확줄어둠
            int imgH = imgW;

            //지정한 위치를 기준으로 잘라냄
            // crop(대상, x좌표, y좌표, 잘라낼너비, 잘라낼높이, 투명도)
            BufferedImage scaleImg = Scalr.crop(img,
                    (img.getWidth() - imgW) / 2,    //crop할 좌표
                    (img.getHeight() - imgH) / 2,
                    imgW, imgH,     // crop할 이미지 크기
                    null);

            // 잘라낸 이미지를 330x350 크기를 재조정
            BufferedImage resizeImg = Scalr.resize(scaleImg,330,350,null);

            // 재조정한 이미지를 실제 경로에 저장
            ImageIO.write(resizeImg, "png", new File(thumbname));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }









}
