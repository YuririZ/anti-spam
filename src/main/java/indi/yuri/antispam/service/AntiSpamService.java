package indi.yuri.antispam.service;

import java.util.Set;

/**
 * @author yurizhang
 * @date 2020/9/14 18:03
 */
public interface AntiSpamService {
    /**
     * 检查是否存在屏蔽词
     *
     * @param input
     * @return
     */
    boolean checkBadWords(String input);

    /**
     * 获取匹配的字符串
     *
     * @param input
     * @return
     */
    Set<String> getMatchedString(String input);
}
