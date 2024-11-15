package com.lotteon.dto.page;

import com.lotteon.dto.FaqDTO;
import lombok.*;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class FaqPageResponseDTO {

    private List<FaqDTO> faqdtoList;
//
//    private String type1;
//    private String type2;
    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    private Long childId;
    private Long parentId;

//    private String type;
//    private String keyword;

    @Builder
    public FaqPageResponseDTO(PageRequestDTO pageRequestDTO, List<FaqDTO> faqdtoList, int total) {
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.faqdtoList = faqdtoList;
        this.childId = pageRequestDTO.getChildId();
        this.parentId = pageRequestDTO.getParentId();
//        this.type1 = pageRequestDTO.getType1();
//        this.type2 = pageRequestDTO.getType2();
//        this.type = pageRequestDTO.getType();
//        this.keyword = pageRequestDTO.getKeyword();

        this.startNo = total - ((pg - 1) * size); //첫번째 글 번호
        this.end = (int) (Math.ceil(this.pg / 10.0))* 10; //마지막 페이지 번호
        this.start = this.end - 9; //첫번째 페이지 번호

        int last = (int) (Math.ceil(total / (double)size)); // 필요한 페이지 갯수
        this.end = end > last ? last : end; // 마지막 글번호가 필요한 페이지 갯수보다 크면 필요한 페이지 갯수로 세팅
        this.prev = this.start > 1; // 1보다 크면 이전버튼
        this.next = total > this.end * this.size; //마지막 페이지번호보다 글갯수가 더 클때 다음 버튼
    }


}