package indi.yuri.antispam.service.impl;

import indi.yuri.antispam.common.config.RootNode;
import indi.yuri.antispam.common.pojo.Node;
import indi.yuri.antispam.service.AntiSpamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.Set;

import static indi.yuri.antispam.common.constant.AntiSpamConstant.BAD_WORDS_NODE_ROOT_NAME;

/**
 * @author yurizhang
 * @date 2020/9/14 18:05
 */
@Service
public class AntiSpamServiceImpl implements AntiSpamService {
    @Resource
    private RootNode rootNode;
    /**
     * 检查是否存在屏蔽词
     *
     * @param input
     * @return
     */
    @Override
    public boolean checkBadWords(String input) {
        if (StringUtils.isEmpty(input)){
            return false;
        }
        Set<String> badWordSet = getMatchedString(input);
        if (CollectionUtils.isEmpty(badWordSet)){
            return false;
        }
        return true;
    }

    /**
     * 获取匹配的字符串
     *
     * @param input
     * @return
     */
    @Override
    public Set<String> getMatchedString(String input) {
        if (StringUtils.isEmpty(input)){
            return null;
        }
        Node badWordsNode = rootNode.getNodeByName(BAD_WORDS_NODE_ROOT_NAME);
        if (badWordsNode == null){
            return null;
        }
        return Node.matchNodes(input, badWordsNode);
    }
}
