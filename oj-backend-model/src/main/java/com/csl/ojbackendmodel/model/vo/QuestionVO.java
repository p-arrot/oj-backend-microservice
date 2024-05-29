package com.csl.ojbackendmodel.model.vo;

import cn.hutool.json.JSONUtil;

import com.csl.ojbackendmodel.model.dto.question.JudgeConfig;
import com.csl.ojbackendmodel.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class QuestionVO {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json数组）
     */
    private List<String> tags;


    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;


    /**
     * 判题配置（json)
     */
    private JudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户id
     */
    private Long userId;

    private UserVO userVO;




    private static final long serialVersionUID = 1L;

    public static Question voToObj(QuestionVO questionVO){

        if(questionVO==null){
            return null;
        }

        Question question=new Question();
        BeanUtils.copyProperties(questionVO,question);
        String tagsJsonStr = JSONUtil.toJsonStr(questionVO.getTags());
        String judgeConfigJsonStr = JSONUtil.toJsonStr(questionVO.getJudgeConfig());
        question.setTags(tagsJsonStr);
        question.setJudgeConfig(judgeConfigJsonStr);

        return question;
    }

    public static QuestionVO objToVo(Question question) {


        if (question == null) {
            return null;
        }

        QuestionVO questionVO = new QuestionVO();

        BeanUtils.copyProperties(question, questionVO);
        questionVO.setTags(JSONUtil.toList(question.getTags(), String.class));
        questionVO.setJudgeConfig(JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class));

        return questionVO;
    }

}
