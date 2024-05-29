package com.csl.ojbackendmodel.model.vo;

import cn.hutool.json.JSONUtil;

import com.csl.ojbackendcommon.common.ErrorCode;
import com.csl.ojbackendcommon.exception.BusinessException;
import com.csl.ojbackendmodel.model.dto.questionsubmit.JudgeInfo;
import com.csl.ojbackendmodel.model.entity.QuestionSubmit;
import com.csl.ojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class QuestionSubmitVO {
    /**
     * id
     */

    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息（json）
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态（0-待判题；1-判题中；2-成功；3-失败）
     */
    private String status;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 创建用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户信息
     */
    private UserVO userVO;

    /**
     * 题目信息
     */
    private QuestionVO questionVO;

    /**
     * vo to obj
     * @param questionSubmitVO
     * @return QuestionSubmit
     */
    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO){

        if(questionSubmitVO==null){
            return null;
        }

        QuestionSubmit questionSubmit=new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO,questionSubmit);
        String judgeInfoStr = JSONUtil.toJsonStr(questionSubmitVO.getJudgeInfo());
        questionSubmit.setJudgeInfo(judgeInfoStr);

        QuestionSubmitStatusEnum questionSubmitStatusEnum = QuestionSubmitStatusEnum.getEnumByText(questionSubmitVO.status);
        if (questionSubmitStatusEnum==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"提交状态错误");
        }
        questionSubmit.setStatus(questionSubmitStatusEnum.getValue());
        return questionSubmit;
    }

    /**
     * obj to vo
     * @param questionSubmit
     * @return QuestionSubmitVO
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {


        if (questionSubmit == null) {
            return null;
        }

        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();

        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);

        JudgeInfo judgeInfo = JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class);
        questionSubmitVO.setJudgeInfo(judgeInfo);

        QuestionSubmitStatusEnum questionSubmitStatusEnum=QuestionSubmitStatusEnum.getEnumByValue(questionSubmit.getStatus());
        if(questionSubmitStatusEnum==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"提交状态错误");
        }
        questionSubmitVO.setStatus(questionSubmitStatusEnum.getText());
        return questionSubmitVO;
    }


}
